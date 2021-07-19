package com.aaquib.tigercard.repository.fare;

import com.aaquib.tigercard.entity.FareSpecification;
import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;

import java.util.List;

public class FareSpecificationRepository {
    private final static List<FareSpecification> FARE_LIST = List.of(
            new FareSpecification(HourType.PEAK, RouteID.ONE_ONE, 30),
            new FareSpecification(HourType.NON_PEAK, RouteID.ONE_ONE, 25),
            new FareSpecification(HourType.PEAK, RouteID.ONE_TWO, 35),
            new FareSpecification(HourType.NON_PEAK, RouteID.ONE_TWO, 30),
            new FareSpecification(HourType.PEAK, RouteID.TWO_ONE, 35),
            new FareSpecification(HourType.NON_PEAK, RouteID.TWO_ONE, 30),
            new FareSpecification(HourType.PEAK, RouteID.TWO_TWO, 25),
            new FareSpecification(HourType.NON_PEAK, RouteID.TWO_TWO, 20)
    );

    public FareSpecification findByHourTypeAndRouteType(HourType hourType, RouteID routeType) {
        return FARE_LIST.parallelStream().filter(fareSpecification ->
                hourType.equals(fareSpecification.getHourType())
                && routeType.equals(fareSpecification.getRouteId())
        ).findFirst().orElse(null);
    }


}
