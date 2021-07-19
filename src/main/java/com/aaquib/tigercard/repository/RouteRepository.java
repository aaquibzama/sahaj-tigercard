package com.aaquib.tigercard.repository;

import com.aaquib.tigercard.entity.Route;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.ZoneID;

import java.util.List;

public class RouteRepository {

    private static final List<Route> TRIP_SPECIFICATION_DATA = List.of(
            new Route(ZoneID.ZONE_ONE, ZoneID.ZONE_ONE, RouteID.ONE_ONE),
            new Route(ZoneID.ZONE_ONE, ZoneID.ZONE_TWO, RouteID.ONE_TWO),
            new Route(ZoneID.ZONE_TWO, ZoneID.ZONE_ONE, RouteID.TWO_ONE),
            new Route(ZoneID.ZONE_TWO, ZoneID.ZONE_TWO, RouteID.TWO_TWO)
    );

    public Route findBySourceZoneAndDestinationZone(ZoneID sourceZoneID, ZoneID destinationZoneID) {
        return TRIP_SPECIFICATION_DATA.parallelStream().filter(route ->
                route.getSourceZoneID().equals(sourceZoneID)
                && route.getDestinationZoneID().equals(destinationZoneID)
        ).findFirst().orElse(null);
    }
}
