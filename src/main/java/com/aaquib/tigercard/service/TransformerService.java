package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.entity.ZoneID;
import com.aaquib.tigercard.factory.ZoneFactoryImpl;
import com.aaquib.tigercard.model.Journey;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TransformerService {

    private final HourSpecificationService hourService;

    private final RouteService routeService;

    private final ZoneFactoryImpl zoneFactoryImpl;

    public TransformerService(HourSpecificationService hourService, RouteService routeService, ZoneFactoryImpl zoneFactoryImpl) {
        this.hourService = hourService;
        this.routeService = routeService;
        this.zoneFactoryImpl = zoneFactoryImpl;
    }

    public Trip execute(Journey journey) {
        LocalDate journeyDate = LocalDate.parse(journey.getJourneyDate());
        DayOfWeek dayOfWeek = journeyDate.getDayOfWeek();
        Integer journeyHour = parseJourneyHour(journey.getJourneyTime());
        ZoneID sourceZone = zoneFactoryImpl.getZoneID(String.valueOf(journey.getSourceZone()));
        ZoneID destinationZone = zoneFactoryImpl.getZoneID(String.valueOf(journey.getDestinationZone()));

        Trip trip = new Trip();
        trip.setJourneyDate(journeyDate);
        trip.setHourType(hourService.getHourID(dayOfWeek, journeyHour));
        trip.setRouteID(routeService.getRouteID(sourceZone, destinationZone));
        return trip;
    }

    private Integer parseJourneyHour(String time) {
        time = time.replaceAll("[^\\d]", "");
        time = time.trim();
        return Integer.parseInt(time);
    }
}
