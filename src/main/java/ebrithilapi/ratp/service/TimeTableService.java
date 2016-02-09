/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.service;

import ebrithilapi.ratp.model.Ride;
import ebrithilapi.ratp.model.TimeTable;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Guillaume
 */
public class TimeTableService {
    public static String BASE_URL = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/%s/%s/%s";
    
    private static TimeTableService _TimeTableService;
    public static TimeTableService getTimeTableService(){
        if(_TimeTableService == null){
            _TimeTableService = new TimeTableService();
        }
        
        return _TimeTableService;
    }
    
    public String generateQueryUrl(Ride ride){
        return String.format(BASE_URL,
                ride.getLine().getId(),
                ride.getFromStation().getId(),
                ride.isDepartureToTerminus() ? "A" : "R");
    }
    
    public ArrayList<TimeTable> getTimeTable(Ride ride) throws IOException{
        String url = this.generateQueryUrl(ride);
        ArrayList<TimeTable> result = new ArrayList<>();
                
        Document source = Jsoup.connect(url).get();        
        Elements tds = source.select("div#prochains_passages table tbody tr td:last-child");
        
        tds.forEach((td) -> result.add(new TimeTable(ride, td.html())));
        
        return result;
    }
}
