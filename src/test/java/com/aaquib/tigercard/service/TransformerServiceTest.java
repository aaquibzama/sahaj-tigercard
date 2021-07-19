package com.aaquib.tigercard.service;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.entity.ZoneID;
import com.aaquib.tigercard.factory.ZoneFactoryImpl;
import com.aaquib.tigercard.model.Journey;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransformerServiceTest {

    HourSpecificationService hourService;

    RouteService routeService;

    ZoneFactoryImpl zoneFactoryImpl;

    TransformerService service;

    @BeforeAll
    void setUp() {
        hourService = Mockito.mock(HourSpecificationService.class);
        routeService = Mockito.mock(RouteService.class);
        zoneFactoryImpl = Mockito.mock(ZoneFactoryImpl.class);
        service = new TransformerService(hourService, routeService, zoneFactoryImpl);
    }

    @Test
    void testExecute() {
        Journey journey = new Journey();
        journey.setJourneyTime("08:45");
        journey.setJourneyDate("2021-07-18");
        journey.setSourceZone(1);
        journey.setDestinationZone(2);

        Trip trip  = new Trip();
        trip.setRouteID(RouteID.ONE_TWO);
        trip.setHourType(HourType.PEAK);
        trip.setJourneyDate(LocalDate.parse("2021-07-18"));

        Mockito.when(zoneFactoryImpl.getZoneID(Mockito.any()))
                .thenReturn(ZoneID.ZONE_ONE)
                .thenReturn(ZoneID.ZONE_TWO);

        Mockito.when(hourService.getHourID(Mockito.any(DayOfWeek.class), Mockito.any(Integer.class)))
                .thenReturn(HourType.PEAK);

        Mockito.when(routeService.getRouteID(Mockito.any(ZoneID.class), Mockito.any(ZoneID.class)))
                .thenReturn(RouteID.ONE_TWO);

        assertEquals(service.execute(journey), trip);
    }
}