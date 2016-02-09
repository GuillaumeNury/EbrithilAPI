/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.model;

import ebrithilapi.ratp.service.TimeTableService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume
 */
public class Ride {
    private Line line;
    private boolean departureToTerminus;
    private Station fromStation;
    private ArrayList<TimeTable> timetable;

    public Ride(Line line, boolean departureToTerminus, Station fromStation) {
        this.line = line;
        this.departureToTerminus = departureToTerminus;
        this.fromStation = fromStation;
        this.timetable = null;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public boolean isDepartureToTerminus() {
        return departureToTerminus;
    }

    public void setDepartureToTerminus(boolean departureToTerminus) {
        this.departureToTerminus = departureToTerminus;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public ArrayList<TimeTable> getTimetable() throws IOException {
        if(this.timetable == null){
            this.timetable = TimeTableService.getTimeTableService().getTimeTable(this);
        }
        
        return timetable;
    }
    
    public String getTimeTableToString(){
        String res = "";
        
        try {
            TimeTable next = this.getTimetable().get(0);
            TimeTable after = this.getTimetable().size() > 1 ? this.getTimetable().get(1) : null;
            
            if(next.isTerminated()){
                res = next.toString();
            }
            else{
                res = "Le prochain bus ";
                res += next.toString();
                
                if(!after.isTerminated()){
                    res += " (prochain dans " + after.getWaitingTime() + " minute" + (after.getWaitingTime() > 1 ? "s" : "") + ").";
                }
                else{
                    res += ".";
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Ride.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void printTimeTable(){
        System.out.println(this.line.getName() + " :");
        System.out.println(this.getTimeTableToString());
    }
}
