package ebrithilapi.areas.t411.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import ebrithilapi.areas.t411.model.Torrent;
import ebrithilapi.areas.t411.model.TorrentRecap;

public class TorrentRecapService {
	public List<TorrentRecap> recapTorrents(List<Torrent> torrents){
		HashMap<String, TorrentRecap> recap = new HashMap<>();
		
		for(Torrent torrent : torrents){
			if(recap.containsKey(torrent.cleanedName)){
				recap.get(torrent.cleanedName).addTorrent(torrent);
			}
			else{
				recap.put(torrent.cleanedName, new TorrentRecap(torrent));
			}
		}
		
		List<TorrentRecap> result = new ArrayList<>(recap.values());
		
		Collections.sort(result, new Comparator<TorrentRecap>() {
			public int compare(TorrentRecap t1, TorrentRecap t2){
				return t2.getScore() - t1.getScore();
			}
		});
		
		return result;
	}
}
