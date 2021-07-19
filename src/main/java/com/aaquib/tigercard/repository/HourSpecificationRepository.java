package com.aaquib.tigercard.repository;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.HourSpecification;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

public class HourSpecificationRepository {

    private static final List<HourSpecification> HOUR_SPECIFICATION_DATA = List.of(
            new HourSpecification(DayOfWeek.MONDAY, 700, 1030, HourType.PEAK),
            new HourSpecification(DayOfWeek.MONDAY, 1700, 2000, HourType.PEAK),
            new HourSpecification(DayOfWeek.MONDAY, 1031, 1659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.MONDAY, 2001, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.MONDAY, 0, 659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.TUESDAY, 700, 1030, HourType.PEAK),
            new HourSpecification(DayOfWeek.TUESDAY, 1700, 2000, HourType.PEAK),
            new HourSpecification(DayOfWeek.TUESDAY, 1031, 1659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.TUESDAY, 2001, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.TUESDAY, 0, 659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.WEDNESDAY, 700, 1030, HourType.PEAK),
            new HourSpecification(DayOfWeek.WEDNESDAY, 1700, 2000, HourType.PEAK),
            new HourSpecification(DayOfWeek.WEDNESDAY, 1031, 1659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.WEDNESDAY, 2001, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.WEDNESDAY, 0, 659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.THURSDAY, 700, 1030, HourType.PEAK),
            new HourSpecification(DayOfWeek.THURSDAY, 1700, 2000, HourType.PEAK),
            new HourSpecification(DayOfWeek.THURSDAY, 1031, 1659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.THURSDAY, 2001, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.THURSDAY, 0, 659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.FRIDAY, 700, 1030, HourType.PEAK),
            new HourSpecification(DayOfWeek.FRIDAY, 1700, 2000, HourType.PEAK),
            new HourSpecification(DayOfWeek.FRIDAY, 1031, 1659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.FRIDAY, 2001, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.FRIDAY, 0, 659, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SATURDAY, 900, 1100, HourType.PEAK),
            new HourSpecification(DayOfWeek.SATURDAY, 1800, 2200, HourType.PEAK),
            new HourSpecification(DayOfWeek.SATURDAY, 1101, 1759, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SATURDAY, 2201, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SATURDAY, 0, 759, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SUNDAY, 900, 1100, HourType.PEAK),
            new HourSpecification(DayOfWeek.SUNDAY, 1800, 2200, HourType.PEAK),
            new HourSpecification(DayOfWeek.SUNDAY, 1101, 1759, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SUNDAY, 2201, 2359, HourType.NON_PEAK),
            new HourSpecification(DayOfWeek.SUNDAY, 0, 759, HourType.NON_PEAK)
    );

    public List<HourSpecification> findByDay(DayOfWeek day) {
        return HOUR_SPECIFICATION_DATA.parallelStream()
                .filter(hourSpecification -> hourSpecification.getDayOfWeek().equals(day))
                .collect(Collectors.toList());
    }

    public HourSpecification findByDayAndJourneyHour(DayOfWeek day, Integer journeyHour) {
        return HOUR_SPECIFICATION_DATA.parallelStream()
                .filter(hourSpecification ->
                        hourSpecification.getDayOfWeek().equals(day)
                        && journeyHour <= hourSpecification.getEndHour()
                        && journeyHour >= hourSpecification.getStartHour()
                ).findFirst().orElse(null);
    }
}
