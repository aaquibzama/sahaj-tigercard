package com.aaquib.tigercard.repository;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.HourSpecification;
import org.junit.jupiter.api.*;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HourSpecificationRepositoryTest {

    private HourSpecificationRepository repository;

    @BeforeAll
    void setUp() {
        repository = new HourSpecificationRepository();
    }

    @Test
    void testFindByDay_WeekDay() {
        List<HourSpecification> hourSpecificationList = repository.findByDay(DayOfWeek.MONDAY);
        for (HourSpecification specification : hourSpecificationList) {
            assertEquals(specification.getDayOfWeek(), DayOfWeek.MONDAY);
        }
    }

    @Test
    void testFindByDay_WeekEnd() {
        List<HourSpecification> hourSpecificationList = repository.findByDay(DayOfWeek.SUNDAY);
        for (HourSpecification specification : hourSpecificationList) {
            assertEquals(specification.getDayOfWeek(), DayOfWeek.SUNDAY);
        }
    }

    @Test
    void findByDayAndJourneyHour_WeekDayAndPeakHour() {
        HourSpecification specification = repository.findByDayAndJourneyHour(DayOfWeek.MONDAY, 700);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.TUESDAY, 1030);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.WEDNESDAY, 1700);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.THURSDAY, 2000);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.FRIDAY, 830);
        assertEquals(specification.getCategory(), HourType.PEAK);
    }

    @Test
    void findByDayAndJourneyHour_WeekDayAndNonPeakHour() {
        HourSpecification specification = repository.findByDayAndJourneyHour(DayOfWeek.MONDAY, 1031);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.TUESDAY, 1659);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.WEDNESDAY, 659);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.WEDNESDAY, 2323);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.THURSDAY, 2001);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.FRIDAY, 1400);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
    }

    @Test
    void findByDayAndJourneyHour_WeekEndAndPeakHour() {
        HourSpecification specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 900);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SUNDAY, 1100);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 1800);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SUNDAY, 2200);
        assertEquals(specification.getCategory(), HourType.PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 930);
        assertEquals(specification.getCategory(), HourType.PEAK);
    }

    @Test
    void findByDayAndJourneyHour_WeekEndAndNonPeakHour() {
        HourSpecification specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 1101);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SUNDAY, 1759);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 2201);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SUNDAY, 2359);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SATURDAY, 759);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
        specification = repository.findByDayAndJourneyHour(DayOfWeek.SUNDAY, 430);
        assertEquals(specification.getCategory(), HourType.NON_PEAK);
    }
}