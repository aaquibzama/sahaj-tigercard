package com.aaquib.tigercard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourSpecification {
    private DayOfWeek dayOfWeek;

    private Integer startHour;

    private Integer endHour;

    private HourType category;
}
