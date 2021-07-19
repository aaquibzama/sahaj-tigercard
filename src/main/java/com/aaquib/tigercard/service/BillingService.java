package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.factory.ZoneFactoryImpl;
import com.aaquib.tigercard.model.Journey;
import com.aaquib.tigercard.repository.*;
import com.aaquib.tigercard.repository.fare.FareSpecificationRepository;
import com.aaquib.tigercard.repository.fare.cap.DailyCapRepository;
import com.aaquib.tigercard.repository.fare.cap.WeeklyCapRepository;
import com.aaquib.tigercard.service.fare.FareService;
import com.aaquib.tigercard.service.fare.cap.DailyFareCappingService;
import com.aaquib.tigercard.service.fare.cap.FareCapFinder;
import com.aaquib.tigercard.service.fare.cap.FareCappingService;
import com.aaquib.tigercard.service.fare.cap.WeeklyFareCappingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingService {

    private final TransformerService transformerService;

    private final FareService fareService;

    private final List<FareCappingService> fareCappingServiceList;

    public BillingService(TransformerService transformerService, FareService fareService, List<FareCappingService> fareCappingServiceList) {
        this.transformerService = transformerService;
        this.fareService = fareService;
        this.fareCappingServiceList = fareCappingServiceList;
    }

    public Integer execute(List<Journey> journeyList) {
        // Transform user input to standardized bean
        List<Trip> tripList = journeyList.parallelStream()
                .map(transformerService::execute)
                .collect(Collectors.toList());

        // Calculate fare for each individual trip
        tripList.forEach(trip -> {
            Integer fare = fareService.getFare(trip.getHourType(), trip.getRouteID());
            trip.setFare(fare);
        });

        // Apply daily & weekly caps on the total trips
        fareCappingServiceList.forEach(fareCappingService -> fareCappingService.execute(tripList));
        return tripList.parallelStream().mapToInt(Trip::getFare).sum();
    }

    public static void main(String[] args) {
        TransformerService transformerService = new TransformerService(new HourSpecificationService(new HourSpecificationRepository()),
                new RouteService(new RouteRepository()), new ZoneFactoryImpl());
        FareService fareService = new FareService(new FareSpecificationRepository());
        List<FareCappingService> fareCappingServiceList = List.of(new DailyFareCappingService(new FareCapFinder(new DailyCapRepository())),
                new WeeklyFareCappingService(new FareCapFinder(new WeeklyCapRepository())));
        BillingService billingService = new BillingService(transformerService, fareService, fareCappingServiceList);
        Integer bill = billingService.execute(List.of(
                new Journey("MONDAY", "10:20", 2, 1),
                new Journey("MONDAY", "10:45", 1, 1),
                new Journey("MONDAY", "16:15", 1, 1),
                new Journey("MONDAY", "18:15", 1, 1),
                new Journey("MONDAY", "19:00", 1, 2)
            ));
        System.out.println(bill);
        List<Journey> journeyList = List.of(
                new Journey("MONDAY", "10:20", 2, 1),
                new Journey("MONDAY", "10:45", 1, 1),
                new Journey("MONDAY", "16:15", 1, 1),
                new Journey("WEDNESDAY", "18:15", 1, 1),
                new Journey("WEDNESDAY", "19:00", 1, 2),
                new Journey("TUESDAY", "10:20", 2, 1),
                new Journey("TUESDAY", "10:45", 1, 1),
                new Journey("THURSDAY", "16:15", 1, 1),
                new Journey("FRIDAY", "18:15", 1, 1),
                new Journey("FRIDAY", "19:00", 1, 2)


        );
        Map<String, List<Journey>> myMap = journeyList.stream().collect(Collectors.groupingBy(Journey::getJourneyDate, HashMap::new, Collectors.toCollection(ArrayList::new)));
        System.out.println(myMap);
    }

}
