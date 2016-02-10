package ebrithilapi.areas.t411.model;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Torrent {
	public static String BASE_T411_URL = "https://www.t411.in";
	
	public long id;
	public String name;
	public String cleanedName;
	public String link;
	public String downloadLink;
	public String category;
	public int seeders;
	public int leechers;
	public boolean isVerified;
	public DateTime added;
	public long size;
	public String readableSize;
	public long times_completed;
	private int _score; 
	
	public Torrent(long id, String name, String link, String downloadLink, String category, int seeders, int leechers, boolean isVerified, DateTime added,
			long size, String readableSize, long times_completed) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.downloadLink = downloadLink;
		this.category = category;
		this.seeders = seeders;
		this.leechers = leechers;
		this.isVerified = isVerified;
		this.added = added;
		this.size = size;
		this.readableSize = readableSize;
		this.times_completed = times_completed;
		this._score = -1;
	}
	
	public static Torrent fromTorrentRaw(TorrentRaw torrent){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime added = formatter.parseDateTime(torrent.added);
		
		return new Torrent(
				torrent.id,
				torrent.name,
				Torrent.BASE_T411_URL + "/torrents/" + torrent.rewritename,
				Torrent.BASE_T411_URL + "/torrents/download?id=" + torrent.id,
				torrent.categoryname,
				torrent.seeders,
				torrent.leechers,
				torrent.isVerified == 1 ? true : false,
				added,
				torrent.size,
				Torrent.getReadableSize(torrent.size),
				torrent.times_completed
				);
	}
	
	public static String getReadableSize(long size){
		long minimizedSize;
		String unit;
		
		if(size < 1000){
			minimizedSize = size;
			unit = "b";
		}
		else if(size < 1000000){
			minimizedSize = size;
			unit = "kb";
		}
		else if(size < 1000000000){
			minimizedSize = size / 1000;
			unit = "Mb";
		}
		else{
			minimizedSize = size / 1000000;
			unit = "Gb";
		}

		if(minimizedSize < 100000){
			return (minimizedSize/1000) + "." + ((minimizedSize/100)-(minimizedSize/1000*10)) + " " + unit;
		}
		
		return (minimizedSize/1000) + " " + unit;
	}

	public int getScore(){
		if(this._score == -1){
			float days = Days.daysBetween(this.added, DateTime.now()).getDays();
			float downloads = this.times_completed;
			float activeSL = this.seeders + this.leechers;		
			
			double score = downloads
					* Math.pow((1 + (activeSL)/downloads),3)
					* (1 + 0.2 * days);
			
			this._score = (int) score;
		}
		
		return this._score;
	}

}
