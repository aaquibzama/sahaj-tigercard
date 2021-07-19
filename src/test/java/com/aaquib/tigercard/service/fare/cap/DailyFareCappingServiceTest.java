package com.aaquib.tigercard.service.fare.cap;

import com.aaquib.tigercard.entity.HourType;
import com.aaquib.tigercard.entity.RouteID;
import com.aaquib.tigercard.entity.Trip;
import com.aaquib.tigercard.service.fare.cap.DailyFareCappingService;
import com.aaquib.tigercard.service.fare.cap.FareCapFinder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DailyFareCappingServiceTest {

    FareCapFinder dailyCapFinder;

    DailyFareCappingService service;

    @BeforeAll
    void setUp() {
        dailyCapFinder = Mockito.mock(FareCapFinder.class);
        service = new DailyFareCappingService(dailyCapFinder);
    }

    @Test
    void testExecute_DailyLimitReached() {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, Integer.valueOf(35), RouteID.TWO_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, Integer.valueOf(35), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-19"), HourType.PEAK, Integer.valueOf(35), RouteID.ONE_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.PEAK, Integer.valueOf(30), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.PEAK, Integer.valueOf(30), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.NON_PEAK, Integer.valueOf(25), RouteID.ONE_ONE));
        tripList.add(new Trip(LocalDate.parse("2021-07-20"), HourType.NON_PEAK, Integer.valueOf(20), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.NON_PEAK, Integer.valueOf(20), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.NON_PEAK, Integer.valueOf(20), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.PEAK, Integer.valueOf(25), RouteID.TWO_TWO));
        tripList.add(new Trip(LocalDate.parse("2021-07-21"), HourType.NON_PEAK, Integer.valueOf(20), RouteID.TWO_TWO));

        Mockito.when(dailyCapFinder.execute(Mockito.any())).thenReturn(120).thenReturn(100).thenReturn(80);
        service.execute(tripList);
        assertEquals(10, tripList.get(4).getFare());
        assertEquals(0, tripList.get(5).getFare());
        assertEquals(30, tripList.get(6).getFare());
        assertEquals(15, tripList.get(9).getFare());
        assertEquals(0, tripList.get(10).getFare());
        assertEquals(0, tripList.get(11).getFare());
        assertEquals(25, tripList.get(12).getFare());
        assertEquals(10, tripList.get(15).getFare());
        assertEquals(0, tripList.get(16).getFare());
        assertEquals(0, tripList.get(17).getFare());
    }
}