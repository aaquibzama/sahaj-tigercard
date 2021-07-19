package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.Trip;

import java.util.List;

public abstract class FareCappingService {
    public abstract void execute(List<Trip> tripList);

    protected void calculateCappedFare(List<Trip> tripList, Integer applicableCap) {
        Integer totalFare = Integer.valueOf(0);
        for (Trip trip : tripList) {
            if (totalFare.equals(applicableCap)) {
                trip.setFare(0);
                continue;
            }
            if (totalFare + trip.getFare() > applicableCap) {
                trip.setFare(applicableCap - totalFare);
                totalFare = applicableCap;
            } else {
                totalFare += trip.getFare();
            }
        }
    }
}
