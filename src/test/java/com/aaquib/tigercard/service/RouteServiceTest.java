package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.Route;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.ZoneID;
import com.aaquib.tigercard.repository.RouteRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteServiceTest {

    private RouteRepository repository;

    private RouteService service;

    @BeforeAll
    void setUp() {
        repository = Mockito.mock(RouteRepository.class);
        service = new RouteService(repository);
    }

    @Test
    void testGetRouteID() {
        Route route = new Route(ZoneID.ZONE_TWO, ZoneID.ZONE_ONE, RouteID.TWO_ONE);
        Mockito.when(repository.findBySourceZoneAndDestinationZone(Mockito.any(ZoneID.class), Mockito.any(ZoneID.class)))
                .thenReturn(route);
        assertEquals(service.getRouteID(ZoneID.ZONE_TWO, ZoneID.ZONE_ONE), route.getRouteID());
    }
}