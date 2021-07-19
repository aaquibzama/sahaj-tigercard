package com.aaquib.tigercard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FareSpecification {

    private HourType hourType;

    private RouteID routeId;

    private Integer fare;
}
