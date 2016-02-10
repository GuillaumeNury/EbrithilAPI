package ebrithilapi.areas.t411.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import ebrithilapi.areas.core.ClientAPIFactory;
import ebrithilapi.areas.t411.model.Auth;
import ebrithilapi.areas.t411.model.Torrent;
import ebrithilapi.areas.t411.model.TorrentRaw;

public class T411Service {
	private String token;
	private String BASE_URL = "https://api.t411.in";
	private String LOGIN_PATH = "/auth";
	private String TORRENT_OF_THE_WEEK_PATH = "/torrents/top/week";
	private String CATEGORY_FILM = "Film";

	public T411Service() {
		super();
		this.token = null;
	}
	
	// FILTER
	public List<Torrent> getFilms(List<Torrent> torrents){
		List<Torrent> result = new ArrayList<>();
		
		torrents.forEach(t -> { if(t.category.equals(this.CATEGORY_FILM)) result.add(t); });
		
		return result;
	}
	
	// GET TORRENTS
	public ArrayList<Torrent> getTopWeekTorrents() throws ClientProtocolException, ParseException, IOException{
		ClientAPIFactory<TorrentRaw[]> client = new ClientAPIFactory<>(TorrentRaw[].class);
		TorrentRaw[] torrents = client.doGet(this.BASE_URL + this.TORRENT_OF_THE_WEEK_PATH, getHeadersForAPI());
		
		return this.formatTorrentRaws(torrents);
	}
	
	public List<Torrent> getTopWeekTorrentFilms() throws ClientProtocolException, ParseException, IOException{
		return this.getFilms(this.getTopWeekTorrents());
	}
	
	// CORE
	public ArrayList<Torrent> formatTorrentRaws(TorrentRaw[] torrents){
		ArrayList<Torrent> result = new ArrayList<>();
		for(TorrentRaw torrent : torrents){
			result.add(Torrent.fromTorrentRaw(torrent));
		}
		
		return result;
	}
	
	// AUTHENTICATION
	public String getToken() throws ParseException, IOException{
		if(this.token == null){
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("username", "ebrithil30"));
			params.add(new BasicNameValuePair("password", "oblivion"));
			
			ClientAPIFactory<Auth> client = new ClientAPIFactory<Auth>(Auth.class);
			Auth auth = client.doPost(this.BASE_URL + this.LOGIN_PATH, params);
			
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
