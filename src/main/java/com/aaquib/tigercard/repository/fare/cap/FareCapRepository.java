package com.aaquib.tigercard.repository.fare.cap;

import com.aaquib.tigercard.entity.FareCap;
import com.aaquib.tigercard.entity.RouteID;

public interface FareCapRepository {
    public FareCap findByRouteId(RouteID routeID);
}
