package com.aaquib.tigercard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private ZoneID sourceZoneID;

    private ZoneID destinationZoneID;

    private RouteID routeID;
}
