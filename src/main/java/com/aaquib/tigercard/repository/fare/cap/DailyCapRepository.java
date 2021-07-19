package com.aaquib.tigercard.repository.fare.cap;

import com.aaquib.tigercard.entity.FareCap;
import com.aaquib.tigercard.entity.RouteID;

import java.util.List;

public class DailyCapRepository implements FareCapRepository {

    private final static List<FareCap> DAILY_CAP_LIST = List.of(
            new FareCap(RouteID.ONE_ONE, 100),
            new FareCap(RouteID.ONE_TWO, 120),
            new FareCap(RouteID.TWO_ONE, 120),
            new FareCap(RouteID.TWO_TWO, 80)
    );

    public FareCap findByRouteId(RouteID routeID) {
        return DAILY_CAP_LIST.parallelStream().filter(fareCap -> routeID.equals(fareCap.getRouteId()))
                .findFirst().orElse(null);
    }

}
