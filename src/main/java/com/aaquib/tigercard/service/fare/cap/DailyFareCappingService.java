package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.Trip;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class DailyFareCappingService extends FareCappingService {

    private final FareCapFinder dailyCapFinder;

    public DailyFareCappingService(FareCapFinder dailyCapFinder) {
        this.dailyCapFinder = dailyCapFinder;
    }

    public void execute(List<Trip> tripList) {
        Map<DayOfWeek, List<Trip>> dailyTripMap = tripList.stream()
                .collect(Collectors.groupingBy(
                        trip -> trip.getJourneyDate().getDayOfWeek(),
                        LinkedHashMap::new,
                        Collectors.toCollection(ArrayList::new)
                        )
                );

        dailyTripMap.forEach((dayOfWeek, dailyTripList) -> {
            Integer applicableDailyCap = dailyCapFinder.execute(tripList);
            calculateCappedFare(dailyTripList, applicableDailyCap);
        });
    }
}
