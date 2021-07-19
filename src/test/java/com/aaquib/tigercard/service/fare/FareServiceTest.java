package com.aaquib.tigercard.service.fare;

import com.aaquib.tigercard.entity.FareSpecification;
import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.repository.fare.FareSpecificationRepository;
import com.aaquib.tigercard.service.fare.FareService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FareServiceTest {

    private FareSpecificationRepository repository;

    private FareService service;

    @BeforeAll
    void setUp() {
        repository = Mockito.mock(FareSpecificationRepository.class);
        service = new FareService(repository);
    }

    @Test
    void testGetFare() {
        FareSpecification fareSpecification = new FareSpecification(HourType.NON_PEAK, RouteID.TWO_TWO, 20);
        Mockito.when(repository.findByHourTypeAndRouteType(Mockito.any(HourType.class), Mockito.any(RouteID.class)))
                .thenReturn(fareSpecification);
        assertEquals(service.getFare(HourType.NON_PEAK, RouteID.TWO_TWO), 20);
    }

}