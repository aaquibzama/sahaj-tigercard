package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.Trip;

import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeeklyFareCappingService extends FareCappingService {

    private final FareCapFinder weeklyCapFinder;

    public WeeklyFareCappingService(FareCapFinder weeklyCapFinder) {
        this.weeklyCapFinder = weeklyCapFinder;
    }

    public void execute(List<Trip> tripList) {
        Map<Integer, List<Trip>> weeklyTripMap = tripList.stream()
                .collect(Collectors.groupingBy(
                        trip -> trip.getJourneyDate().get(WeekFields.ISO.weekOfMonth()),
                        LinkedHashMap::new,
                        Collectors.toCollection(ArrayList::new)
                        )
                );

        weeklyTripMap.forEach((dayOfWeek, weeklyTripList) -> {
            Integer applicableWeeklyCap = weeklyCapFinder.execute(tripList);
            calculateCappedFare(weeklyTripList, applicableWeeklyCap);
        });
    }
}
