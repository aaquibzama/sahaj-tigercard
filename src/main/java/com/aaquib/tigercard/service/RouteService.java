package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.ZoneID;
import com.aaquib.tigercard.repository.RouteRepository;

public class RouteService {

    private final RouteRepository repository;

    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }

    public RouteID getRouteID(ZoneID sourceZoneID, ZoneID destinationZoneID) {
        return repository.findBySourceZoneAndDestinationZone(sourceZoneID, destinationZoneID).getRouteID();
    }
}
