package ebrithilapi.areas.t411.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import ebrithilapi.areas.core.ClientAPIFactory;
import ebrithilapi.areas.t411.config.AuthenticationConfig;
import ebrithilapi.areas.t411.model.Auth;
import ebrithilapi.areas.t411.model.Torrent;
import ebrithilapi.areas.t411.model.TorrentCategory;
import ebrithilapi.areas.t411.model.TorrentRaw;

public class T411Service {
	private String token;
	public static String BASE_URL = "https://api.t411.in";
	private String LOGIN_PATH = "/auth";
	private String TORRENT_OF_THE_WEEK_PATH = "/torrents/top/week";

	public T411Service() {
		super();
		this.token = null;
	}
	
	// FILTER
	public List<Torrent> filterByCategory(List<Torrent> torrents, String category){
		torrents.removeIf(t -> !t.category.equals(category));
		return torrents;
	}
	
	// SORT
	public List<Torrent> sortByLeecher(List<Torrent> torrents){
		Collections.sort(torrents, new Comparator<Torrent>() {
			public int compare(Torrent t1, Torrent t2){
				return t2.leechers - t1.leechers;
			}
		});
		
		return torrents;
	}
	
	public List<Torrent> sortBySeeder(List<Torrent> torrents){
		Collections.sort(torrents, new Comparator<Torrent>() {
			public int compare(Torrent t1, Torrent t2){
				return t2.seeders - t1.seeders;
			}
		});
		
		return torrents;
	}
	
	public List<Torrent> sortByTimeCompleted(List<Torrent> torrents){
		Collections.sort(torrents, new Comparator<Torrent>() {
			public int compare(Torrent t1, Torrent t2){
				return (int)(t2.times_completed - t1.times_completed);
			}
		});
		
		return torrents;
	}
	
	// GET TORRENTS
	public ArrayList<Torrent> getTopWeekTorrents() throws ClientProtocolException, ParseException, IOException{
		ClientAPIFactory<TorrentRaw[]> client = new ClientAPIFactory<>(TorrentRaw[].class);
		TorrentRaw[] torrents = client.doGet(T411Service.BASE_URL + this.TORRENT_OF_THE_WEEK_PATH, getHeadersForAPI());
		
		return this.formatTorrentRaws(torrents);
	}
	
	public List<Torrent> getTopWeekTorrentFilms() throws ClientProtocolException, ParseException, IOException{
		return this.filterByCategory(this.getTopWeekTorrents(), TorrentCategory.CATEGORY_FILM);
	}
	
	// CORE
	public ArrayList<Torrent> formatTorrentRaws(TorrentRaw[] torrents){
		TorrentChooserService chooser = new TorrentChooserService();
		ArrayList<Torrent> result = new ArrayList<>();
		
		for(TorrentRaw torrent : torrents){
			Torrent t = Torrent.fromTorrentRaw(torrent);
			t.cleanedName = chooser.getCleanName(torrent.name);
			result.add(t);
		}
		
		return result;
	}
	
	// AUTHENTICATION
	public String getToken() throws ParseException, IOException{
		if(this.token == null){
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("username", AuthenticationConfig.username));
			params.add(new BasicNameValuePair("password", AuthenticationConfig.password));
			
			ClientAPIFactory<Auth> client = new ClientAPIFactory<Auth>(Auth.class);
			Auth auth = client.doPost(T411Service.BASE_URL + this.LOGIN_PATH, params);
			
			this.token = auth.getToken();
		}
		
		return this.token;
	}

	public List<NameValuePair> getHeadersForAPI() throws ParseException, IOException{
		List<NameValuePair> result = new ArrayList<>();
		result.add(new BasicNameValuePair("authorization", this.getToken()));
		
		return result;
	}
}
