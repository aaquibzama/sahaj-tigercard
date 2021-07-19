package com.aaquib.tigercard.repository;

import com.aaquib.tigercard.entity.Route;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.ZoneID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RouteIDRepositoryTest {

    private RouteRepository repository;

    @BeforeAll
    void setUp() {
        repository = new RouteRepository();
    }

    @Test
    void testFindBySourceZoneAndDestinationZone_SameZone() {
        Route route = repository.findBySourceZoneAndDestinationZone(ZoneID.ZONE_ONE, ZoneID.ZONE_ONE);
        assertEquals(RouteID.ONE_ONE, route.getRouteID());
        route = repository.findBySourceZoneAndDestinationZone(ZoneID.ZONE_TWO, ZoneID.ZONE_TWO);
        assertEquals(RouteID.TWO_TWO, route.getRouteID());
    }

    @Test
    void testFindBySourceZoneAndDestinationZone_DifferentZone() {
        Route route = repository.findBySourceZoneAndDestinationZone(ZoneID.ZONE_ONE, ZoneID.ZONE_TWO);
        assertEquals(RouteID.ONE_TWO, route.getRouteID());
        route = repository.findBySourceZoneAndDestinationZone(ZoneID.ZONE_TWO, ZoneID.ZONE_ONE);
        assertEquals(RouteID.TWO_ONE, route.getRouteID());
    }
}