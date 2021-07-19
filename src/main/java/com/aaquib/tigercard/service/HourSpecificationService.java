package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.repository.HourSpecificationRepository;

import java.time.DayOfWeek;

public class HourSpecificationService {

    private final HourSpecificationRepository repository;

    public HourSpecificationService(HourSpecificationRepository repository) {
        this.repository = repository;
    }

    public HourType getHourID(DayOfWeek dayOfWeek, Integer journeyHour) {
        return repository.findByDayAndJourneyHour(dayOfWeek, journeyHour).getCategory();
    }
}
