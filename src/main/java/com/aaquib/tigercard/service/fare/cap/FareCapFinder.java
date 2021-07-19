package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.repository.fare.cap.FareCapRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FareCapFinder {
    FareCapRepository repository;

    public FareCapFinder(FareCapRepository repository) {
        this.repository = repository;
    }

    public Integer execute(List<Trip> tripList) {
        Set<RouteID> uniqueTrips = tripList.parallelStream().map(trip -> trip.getRouteID()).collect(Collectors.toSet());
        Integer applicableCap = Integer.MIN_VALUE;
        for (RouteID routeID : uniqueTrips) {
            Integer currentCap = repository.findByRouteId(routeID).getCapAmount();
            applicableCap = applicableCap <= currentCap ? currentCap : applicableCap;
        }
        return applicableCap;
    }

}
