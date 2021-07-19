package com.aaquib.tigercard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Journey {

    private String journeyDate;

    private String journeyTime;

    private Integer sourceZone;

    private Integer destinationZone;
}
