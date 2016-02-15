package ebrithilapi.areas.t411.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import ebrithilapi.areas.t411.model.Torrent;

public class TorrentChooserService {
	public long minSize;
	public long maxSize;
	public List<String> language;
	public List<String> blurayQuality;
	public List<String> dvdQuality;
	public List<String> uselessElements;
	private List<String> _allUselessElements;
	
	public TorrentChooserService(){
		this.minSize = 1000000000; // 1 Gb 
		this.maxSize = Long.parseLong("3590000000"); // 3.5 Gb
		
		String[] languages = {"TRUEFRENCH", "FRENCH", "MULTI", "VFF", "FR", "VF"};
		this.language = Arrays.asList(languages);
		
		String[] bluerayQuality = {"BDRIP", "BD", "Bluray", "BRRIP", ".*HDLight.*", "1080p", "Blu-Ray"};
		this.blurayQuality = Arrays.asList(bluerayQuality);
		
		String[] dvdQuality = {".*DVD.*", "DIVX"};
		this.dvdQuality = Arrays.asList(dvdQuality);
		
		String[] uselessElements = {"XVID", "720p", "VFQ", "VOSTFR", "VO", "NTSC", "ISO", "PAL", "3D"
				, "Mkv", "Ac3", "AVC", "STV", ".*Rip.*", "SD", "FANSUB",
				"UNRATED", "Director's Cut", "WEB-DL", "x264"};
		this.uselessElements = Arrays.asList(uselessElements);
	}
	
	// NAME MANIPULATION
	public String removeUselessChar(String name){
		name = name.replace(".", " ");
		name = name.replace(" - ", " ");
		name = name.replace("_", " ");
		name = name.replace("][", " ");
		name = name.replace("[", "");
		name = name.replace("]", "");
		name = name.replace(")(", " ");
		name = name.replace("(", "");
		name = name.replace(")", "");
		name = name.replace("  ", " ");
		return name;
	}
	
	public String getCleanName(String name){
		if(name == null || name == "") return "";
		
		// Remove (XYZ)
		name = name.replaceAll("\\(.*\\)", "");
		
		// Concat useless elements
		List<String> allUselessElements = this.getAllUselessElements();
		
		name = this.removeUselessChar(name);
		List<String> elements = Arrays.asList(name.split(" "));
		
		// Find the first useless elements
		int i = 0;
		boolean foundUselessElement = false;
		
		while(!foundUselessElement && i < elements.size()){			
			for(String uselessElement : allUselessElements){
				Pattern pattern = Pattern.compile("^" + uselessElement + "$", Pattern.CASE_INSENSITIVE);
				
				if(pattern.matcher(elements.get(i)).find()){
					foundUselessElement = true;
				}
			}
			
			i = foundUselessElement ? i : i + 1;
		}
		
		if(i == 0){
			return null;
		}
		
		// Remove last and/or first element if it is a year
		List<String> elementToKeep;
		String pattern = "^(18|19|20|21)[0-9]{2}$";
		int startIndex = elements.size() > 0 && elements.get(0).matches(pattern) ? 1 : 0;
		int stopIndex = elements.size() > 0 && elements.get(i - 1).matches(pattern) ? i - 1 : i;
		
		elementToKeep = elements.subList(startIndex, stopIndex);
		
		
		name = "";
		
		for(i = 0; i< elementToKeep.size() ; i++){
			name += elementToKeep.get(i);
			name += i == elementToKeep.size() - 1 ? "" : " ";
		}
		
		return name;
	}
	
	// CORE
	public List<String> getAllUselessElements(){
		if(this._allUselessElements == null){
			List<String> allUselessElements = new ArrayList<String>();
			allUselessElements.addAll(this.blurayQuality);
			allUselessElements.addAll(this.dvdQuality);
			allUselessElements.addAll(this.language);
			allUselessElements.addAll(this.uselessElements);
			this._allUselessElements = allUselessElements;
		}
		
		return this._allUselessElements;
	}
	
	// CHOOSER
	public boolean checkRules(Torrent torrent){
		String name = this.removeUselessChar(torrent.name);
		List<String> elements = Arrays.asList(name.split(" "));
		boolean qualityOK = false;
		boolean languageOK = false;
		
		// Quality
		for(String element : elements){
			for(String quality : this.blurayQuality){
				if(quality.equalsIgnoreCase(element)){
					qualityOK = true;
				}
			}
		}
		
		// Language
		for(String element : elements){
			for(String language : this.language){
				if(language.equalsIgnoreCase(element)){
					languageOK = true;
				}
			}
		}
		
		// Size
		boolean sizeOK = torrent.size > this.minSize && torrent.size < this.maxSize;
		
		return qualityOK && languageOK && sizeOK;
	}
}
