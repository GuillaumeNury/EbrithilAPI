/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.model;

import java.util.ArrayList;

/**
 *
 * @author Guillaume
 */
public class Line {
    private String id;
    private String name;
    private Station terminus;
    private Station departure;
    private ArrayList<Station> stations;

    public Line(String id, String name) {
        this.id = id;
        this.name = name;
        this.stations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getTerminus() {
        return terminus;
    }

    public void setTerminus(Station terminus) {
        this.terminus = terminus;
    }

    public Station getDeparture() {
        return departure;
    }

    public void setDeparture(Station departure) {
        this.departure = departure;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }
    
    
}
