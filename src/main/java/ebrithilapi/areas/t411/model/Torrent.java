package ebrithilapi.areas.t411.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Torrent {
	public long id;
	public String name;
	public String category;
	public int seeders;
	public int leechers;
	public boolean isVerified;
	public DateTime added;
	public long size;
	public String readableSize;
	public long times_completed;
	
	public Torrent(long id, String name, String category, int seeders, int leechers, boolean isVerified, DateTime added,
			long size, String readableSize, long times_completed) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.seeders = seeders;
		this.leechers = leechers;
		this.isVerified = isVerified;
		this.added = added;
		this.size = size;
		this.readableSize = readableSize;
		this.times_completed = times_completed;
	}
	
	public static Torrent fromTorrentRaw(TorrentRaw torrent){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime added = formatter.parseDateTime(torrent.added);
		
		return new Torrent(
				torrent.id,
				torrent.name,
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
}
