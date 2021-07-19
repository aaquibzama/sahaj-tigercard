package com.aaquib.tigercard.repository.fare.cap;

import com.aaquib.tigercard.entity.FareCap;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.repository.fare.cap.DailyCapRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DailyCapRepositoryTest {

    private DailyCapRepository repository;

    @BeforeAll
    void setUp() {
        repository = new DailyCapRepository();
    }

    @Test
    void testFindByRouteId() {
        FareCap fareCap = new FareCap(RouteID.ONE_ONE, 100);
        assertEquals(repository.findByRouteId(RouteID.ONE_ONE), fareCap);
    }
}