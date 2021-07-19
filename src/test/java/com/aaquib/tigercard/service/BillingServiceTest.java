package com.aaquib.tigercard.service;

import com.aaquib.tigercard.factory.ZoneFactoryImpl;
import com.aaquib.tigercard.repository.HourSpecificationRepository;
import com.aaquib.tigercard.repository.RouteRepository;
import com.aaquib.tigercard.repository.fare.FareSpecificationRepository;
import com.aaquib.tigercard.repository.fare.cap.DailyCapRepository;
import com.aaquib.tigercard.repository.fare.cap.WeeklyCapRepository;
import com.aaquib.tigercard.service.fare.FareService;
import com.aaquib.tigercard.service.fare.cap.DailyFareCappingService;
import com.aaquib.tigercard.service.fare.cap.FareCapFinder;
import com.aaquib.tigercard.service.fare.cap.FareCappingService;
import com.aaquib.tigercard.service.fare.cap.WeeklyFareCappingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    private BillingService billingService;

    @BeforeEach
    void setUp() {
        TransformerService transformerService = new TransformerService(new HourSpecificationService(new HourSpecificationRepository()),
                new RouteService(new RouteRepository()), new ZoneFactoryImpl());
        FareService fareService = new FareService(new FareSpecificationRepository());
        List<FareCappingService> fareCappingServiceList = List.of(new DailyFareCappingService(new FareCapFinder(new DailyCapRepository())),
                new WeeklyFareCappingService(new FareCapFinder(new WeeklyCapRepository())));
        billingService = new BillingService(transformerService, fareService, fareCappingServiceList);
    }


    @Test
    void test_DailyLimitReached_FareIs120() {
        assertEquals(120, billingService.execute(BillingInput.INPUT_DAILY_LIMIT_REACHED));
    }

    @Test
    void test_DailyLimitNotReached_FareIs100() {
        assertEquals(100, billingService.execute(BillingInput.INPUT_DAILY_LIMIT_NOT_REACHED));
    }

    @Test
    void test_WeeklyLimitReached_FareIs600() {
        assertEquals(600, billingService.execute(BillingInput.INPUT_WEEKLY_LIMIT_REACHED));
    }

    @Test
    void test_WeeklyLimitNotReached_FareIs565() {
        assertEquals(565, billingService.execute(BillingInput.INPUT_WEEKLY_LIMIT_NOT_REACHED));
    }
}