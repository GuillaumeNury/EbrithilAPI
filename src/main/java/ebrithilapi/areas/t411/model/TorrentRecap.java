package ebrithilapi.areas.t411.model;

import java.util.ArrayList;
import java.util.List;

public class TorrentRecap {
	private List<Torrent> torrents;
	private String name;
	private int _score;
	private int _leechers;
	private int _seeders;
	private int _downloads;
	
	public TorrentRecap(Torrent torrent) {
		super();
		this.torrents = new ArrayList<>();
		this.torrents.add(torrent);
		this.name = torrent.cleanedName;
		this._score = -1;
		this._leechers = -1;
		this._seeders = -1;
		this._downloads = -1;
	}
	
	public int getScore(){
		if(this._score == -1){
			this._score = 0;
			this.torrents.forEach(t -> { this._score += t.getScore(); });
		}
		
		return this._score;
	}
	
	public int getSeeders(){
		if(this._seeders == -1){
			this._seeders = 0;
			this.torrents.forEach(t -> { this._seeders += t.seeders; });
		}
		
		return this._seeders;
	}
	
	public int getLeechers(){
		if(this._leechers == -1){
			this._leechers = 0;
			this.torrents.forEach(t -> { this._leechers += t.leechers; });
		}
		
		return this._leechers;
	}
	
	public int getDownloads(){
		if(this._downloads == -1){
			this._downloads = 0;
			this.torrents.forEach(t -> { this._downloads += t.times_completed; });
		}
		
		return this._downloads;
	}

	
	public List<Torrent> getTorrents() {
		return this.torrents;
	}
	

	public String getName() {
		return this.name;
	}
	
	public void addTorrent(Torrent torrent){
		this.torrents.add(torrent);
		this._score = -1;
		this._leechers = -1;
		this._seeders = -1;
		this._downloads = -1;
	}	
}
