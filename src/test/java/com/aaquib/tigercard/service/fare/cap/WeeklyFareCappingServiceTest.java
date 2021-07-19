package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.service.fare.cap.FareCapFinder;
import com.aaquib.tigercard.service.fare.cap.WeeklyFareCappingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WeeklyFareCappingServiceTest {

    FareCapFinder weeklyFareCapFinder;

    WeeklyFareCappingService service;

    @BeforeAll
    void setUp() {
        weeklyFareCapFinder = Mockito.mock(FareCapFinder.class);
        service = new WeeklyFareCappingService(weeklyFareCapFinder);
    }

    @Test
    void testExecute_WeeklyLimitReached() {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.PEAK, Integer.valueOf(35), RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.PEAK, Integer.valueOf(10), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-12"), HourType.PEAK, Integer.valueOf(0), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.PEAK, Integer.valueOf(30), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.PEAK, Integer.valueOf(30), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.PEAK, Integer.valueOf(15), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.NON_PEAK, Integer.valueOf(0), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-13"), HourType.NON_PEAK, Integer.valueOf(0), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.NON_PEAK, Integer.valueOf(20), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.NON_PEAK, Integer.valueOf(10), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.PEAK, Integer.valueOf(0), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-14"), HourType.NON_PEAK, Integer.valueOf(0), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.PEAK, Integer.valueOf(35), RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.PEAK, Integer.valueOf(10), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-15"), HourType.PEAK, Integer.valueOf(0), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.PEAK, Integer.valueOf(35), RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.PEAK, Integer.valueOf(10), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-16"), HourType.PEAK, Integer.valueOf(0), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.PEAK, Integer.valueOf(35), RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.PEAK, Integer.valueOf(10), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-17"), HourType.PEAK, Integer.valueOf(0), RouteID.ONE_TWO));

        Mockito.when(weeklyFareCapFinder.execute(Mockito.any())).thenReturn(600);
        service.execute(tripList);
        assertEquals(25, tripList.get(31).getFare());
        assertEquals(0, tripList.get(32).getFare());
        assertEquals(0, tripList.get(33).getFare());
        assertEquals(0, tripList.get(34).getFare());
    }
}