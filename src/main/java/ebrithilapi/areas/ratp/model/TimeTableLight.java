/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.areas.ratp.model;
import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class TimeTableLight {
	public String timeToGo;
    public String busName;

    public TimeTableLight(String busName, String timeToGo) {
        this.busName = busName;
        this.timeToGo = timeToGo;
    }
    
    public static ArrayList<TimeTableLight> fromTimeTableList(ArrayList<TimeTable> timetables){
    	ArrayList<TimeTableLight> res = new ArrayList<>();
    	timetables.forEach(tt -> {
    		res.add(new TimeTableLight(tt.ride.getLine().getName(), tt.toShortString()));
    	});
    	
    	return res;
    }
    
    
}
