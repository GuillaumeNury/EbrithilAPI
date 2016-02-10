package ebrithilapi.rest;

import java.util.ArrayList;

import ebrithilapi.areas.ratp.data.MainProvider;
import ebrithilapi.areas.ratp.model.Ride;
import ebrithilapi.areas.ratp.model.TimeTable;
import ebrithilapi.areas.ratp.model.TimeTableLight;
import ebrithilapi.areas.ratp.service.RideService;
import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component @RestxResource
public class RatpResource {
	@GET("/ratp/nextbus")
	@PermitAll
	public ArrayList<TimeTableLight> getNextBus(){
		ArrayList<Ride> rides = new ArrayList<>();
        rides.add(MainProvider.getRideProvider().AllerL310);
        rides.add(MainProvider.getRideProvider().AllerL320);
        
        RideService rideService = new RideService();
        ArrayList<TimeTable> res = rideService.getNextBus(rides);
        
        return TimeTableLight.fromTimeTableList(res);
	}
}
