package com.aaquib.tigercard.repository.fare.cap;

import com.aaquib.tigercard.entity.FareCap;
import com.aaquib.tigercard.entity.RouteID;

import java.util.List;

public class WeeklyCapRepository implements FareCapRepository {
    private final static List<FareCap> WEEKLY_CAP_LIST = List.of(
            new FareCap(RouteID.ONE_ONE, 500),
            new FareCap(RouteID.ONE_TWO, 600),
            new FareCap(RouteID.TWO_ONE, 600),
            new FareCap(RouteID.TWO_TWO, 400)
    );

    public FareCap findByRouteId(RouteID routeID) {
        return WEEKLY_CAP_LIST.parallelStream().filter(fareCap -> routeID.equals(fareCap.getRouteId()))
                .findFirst().orElse(null);
    }
}
