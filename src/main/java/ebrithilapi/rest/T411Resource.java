package ebrithilapi.rest;

import java.io.IOException;
import java.util.List;
import org.apache.http.ParseException;
import ebrithilapi.areas.t411.service.T411Service;
import ebrithilapi.areas.t411.model.Torrent;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component @RestxResource
public class T411Resource {

	@GET("/t411/films/week")
	@PermitAll
	public List<Torrent> test() throws ParseException, IOException{
		T411Service t411Service = new T411Service();
		return t411Service.getTopWeekTorrentFilms();
	}
}
