package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.HourSpecification;
import com.aaquib.tigercard.repository.HourSpecificationRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HourSpecificationServiceTest {

    private HourSpecificationService service;

    private HourSpecificationRepository repository;

    @BeforeAll
    void setUp() {
        repository = Mockito.mock(HourSpecificationRepository.class);
        service = new HourSpecificationService(repository);
    }

    @Test
    void testGetHourID() {
        HourSpecification specification = new HourSpecification(DayOfWeek.MONDAY, 700, 1100, HourType.PEAK);
        Mockito.when(repository.findByDayAndJourneyHour(Mockito.any(DayOfWeek.class), Mockito.any(Integer.class)))
                .thenReturn(specification);
        assertEquals(service.getHourID(DayOfWeek.MONDAY, 800), specification.getCategory());
    }
}