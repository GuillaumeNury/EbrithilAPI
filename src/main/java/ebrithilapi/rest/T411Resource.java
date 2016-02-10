package ebrithilapi.rest;

import java.io.IOException;
import java.util.List;
import org.apache.http.ParseException;
import ebrithilapi.areas.t411.service.T411Service;
import ebrithilapi.areas.t411.service.TorrentChooserService;
import ebrithilapi.areas.t411.service.TorrentRecapService;
import ebrithilapi.areas.t411.model.Torrent;
import ebrithilapi.areas.t411.model.TorrentRecap;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component @RestxResource
public class T411Resource {

	@GET("/t411/films/week")
	@PermitAll
	public List<Torrent> topFilmsOfTheWeek() throws ParseException, IOException{
		T411Service t411Service = new T411Service();
		List<Torrent> topWeekTorrentFilms = t411Service.getTopWeekTorrentFilms();
		topWeekTorrentFilms = t411Service.sortBySeeder(topWeekTorrentFilms);
		
		return topWeekTorrentFilms;
	}
	
	@GET("/t411/films/week/auto")
	@PermitAll
	public List<TorrentRecap> autoFilmsOfTheWeek() throws ParseException, IOException{
		List<Torrent> topWeekTorrentFilms = this.topFilmsOfTheWeek();

		// Retrieve torrent of films that match rules
		TorrentChooserService chooser = new TorrentChooserService();
		topWeekTorrentFilms.removeIf(t -> !chooser.checkRules(t));
		
		// Recap
		TorrentRecapService recapService = new TorrentRecapService();

		return recapService.recapTorrents(topWeekTorrentFilms);
	}
}
