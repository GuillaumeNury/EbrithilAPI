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
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume
 */
public class RideService {
    public ArrayList<TimeTable> getNextBus(ArrayList<Ride> rides){
        ArrayList<TimeTable> timetables = new ArrayList<>();
        
        rides.forEach( r -> {
            try {
                timetables.addAll(r.getTimetable());
            } catch (IOException ex) {
                Logger.getLogger(RideService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        timetables.sort(new Comparator<TimeTable>(){
            @Override
            public int compare(TimeTable tt1, TimeTable tt2){
                return tt1.getWaitingTime() - tt2.getWaitingTime();
            }
        });
        
        return timetables;
    }
    
    public String nextBusToString(ArrayList<Ride> rides){
        String res = "";
        
        for(TimeTable tt : this.getNextBus(rides)){
            if(!tt.isTerminated()){
                res += tt.ride.getLine().getName() + " - " + tt.toShortString() + "\n";
            }
        }
        
        return res;
    }
    
    public void printNextBus(ArrayList<Ride> rides){
        System.out.println(this.nextBusToString(rides));
    }
}
