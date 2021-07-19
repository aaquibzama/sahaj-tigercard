package com.aaquib.tigercard.service.fare;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.repository.fare.FareSpecificationRepository;

public class FareService {

    private final FareSpecificationRepository repository;

    public FareService(FareSpecificationRepository repository) {
        this.repository = repository;
    }

    public Integer getFare(HourType hourType, RouteID routeType) {
        return repository.findByHourTypeAndRouteType(hourType, routeType).getFare();
    }
}
