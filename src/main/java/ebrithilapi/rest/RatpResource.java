package ebrithilapi.rest;

import java.util.ArrayList;
import ebrithilapi.ratp.data.MainProvider;
import ebrithilapi.ratp.model.Ride;
import ebrithilapi.ratp.model.TimeTable;
import ebrithilapi.ratp.model.TimeTableLight;
import ebrithilapi.ratp.service.RideService;
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
