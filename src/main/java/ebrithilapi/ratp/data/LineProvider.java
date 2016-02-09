/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebrithilapi.ratp.data;

import ebrithilapi.ratp.model.Line;

/**
 *
 * @author Guillaume
 */
public class LineProvider {
    public Line L320;
    public Line L310;
    private StationProvider stationProvider;

    public LineProvider(StationProvider stationProvider) {
        this.stationProvider = stationProvider;
        
        this.L310 = new Line("B310", "Bus 310");
        this.L320 = new Line("B320", "Bus 320");
        
        this.L310.addStation(this.stationProvider.MairieNoisyLeGrand310);
        this.L320.addStation(this.stationProvider.MairieNoisyLeGrand320);
    }
}
