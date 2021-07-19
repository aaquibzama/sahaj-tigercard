package com.aaquib.tigercard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {
    private LocalDate journeyDate;

    private HourType hourType;

    private Integer fare;

    private RouteID routeID;
}
