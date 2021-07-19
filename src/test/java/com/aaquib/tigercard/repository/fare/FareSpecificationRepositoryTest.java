package com.aaquib.tigercard.repository.fare;

import com.aaquib.tigercard.entity.FareSpecification;
import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.repository.fare.FareSpecificationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FareSpecificationRepositoryTest {

    private FareSpecificationRepository repository;

    @BeforeAll
    void setUp() {
        repository = new FareSpecificationRepository();
    }

    @Test
    void testFindByHourTypeAndRouteType() {
        FareSpecification fareSpecification = new FareSpecification(HourType.NON_PEAK, RouteID.TWO_TWO, 20);
        assertEquals(repository.findByHourTypeAndRouteType(HourType.NON_PEAK, RouteID.TWO_TWO), fareSpecification);
    }
}