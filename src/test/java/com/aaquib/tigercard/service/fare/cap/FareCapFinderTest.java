package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.*;
import com.aaquib.tigercard.repository.fare.cap.DailyCapRepository;
import com.aaquib.tigercard.repository.fare.cap.FareCapRepository;
import com.aaquib.tigercard.repository.fare.cap.WeeklyCapRepository;
import com.aaquib.tigercard.service.fare.cap.FareCapFinder;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FareCapFinderTest {

    private FareCapRepository repository;

    private FareCapFinder service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(DailyCapRepository.class);
        service = new FareCapFinder(repository);
    }

    @Test
    void testExecute_DailyCap_120() {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, null, RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, null, RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, null, RouteID.ONE_TWO));

        Mockito.when(repository.findByRouteId(Mockito.any(RouteID.class)))
                .thenReturn(new FareCap(RouteID.TWO_ONE,120))
                .thenReturn(new FareCap(RouteID.ONE_ONE,100))
                .thenReturn(new FareCap(RouteID.TWO_TWO,80))
                .thenReturn(new FareCap(RouteID.ONE_TWO,120));

        assertEquals(120, service.execute(tripList));
    }

    @Test
    void testExecute_DailyCap_100() {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, null, RouteID.TWO_TWO));

        Mockito.when(repository.findByRouteId(Mockito.any(RouteID.class)))
                .thenReturn(new FareCap(RouteID.ONE_ONE,100))
                .thenReturn(new FareCap(RouteID.TWO_TWO,80));

        assertEquals(100, service.execute(tripList));
    }

    @Test
    void testExecute_WeeklyCap_500() {
        FareCapRepository repository = Mockito.mock(WeeklyCapRepository.class);
        service = new FareCapFinder(repository);
        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, null, RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, null, RouteID.TWO_TWO));

        Mockito.when(repository.findByRouteId(Mockito.any(RouteID.class)))
                .thenReturn(new FareCap(RouteID.ONE_ONE,500))
                .thenReturn(new FareCap(RouteID.TWO_TWO,400));

        assertEquals(service.execute(tripList), 500);
    }

}