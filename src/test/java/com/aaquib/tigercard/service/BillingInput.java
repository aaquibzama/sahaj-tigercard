package com.aaquib.tigercard.service;

import com.aaquib.tigercard.model.Journey;

import java.util.List;

public class BillingInput {

    public static final List<Journey> INPUT_DAILY_LIMIT_REACHED = List.of(
            new Journey("2021-07-12", "10:20", 2, 1),
            new Journey("2021-07-12", "10:45", 1, 1),
            new Journey("2021-07-12", "16:15", 1, 1),
            new Journey("2021-07-12", "18:15", 1, 1),
            new Journey("2021-07-12", "19:00", 1, 2)
    );

    public static final List<Journey> INPUT_DAILY_LIMIT_NOT_REACHED = List.of(
            new Journey("2021-07-13", "10:20", 1, 1),
            new Journey("2021-07-13", "10:45", 1, 1),
            new Journey("2021-07-13", "16:15", 2, 2),
            new Journey("2021-07-13", "19:00", 2, 2)
    );

    public static final List<Journey> INPUT_WEEKLY_LIMIT_REACHED = List.of(
            new Journey("2021-07-12", "10:20", 2, 1),
            new Journey("2021-07-12", "10:45", 1, 1),
            new Journey("2021-07-12", "16:15", 1, 1),
            new Journey("2021-07-12", "18:15", 1, 1),
            new Journey("2021-07-12", "19:00", 1, 2),

            new Journey("2021-07-13", "10:20", 2, 1),
            new Journey("2021-07-13", "10:45", 1, 1),
            new Journey("2021-07-13", "16:15", 1, 1),
            new Journey("2021-07-13", "18:15", 1, 1),
            new Journey("2021-07-13", "19:00", 1, 2),

            new Journey("2021-07-14", "10:20", 2, 1),
            new Journey("2021-07-14", "10:45", 1, 1),
            new Journey("2021-07-14", "16:15", 1, 1),
            new Journey("2021-07-14", "18:15", 1, 1),
            new Journey("2021-07-14", "19:00", 1, 2),

            new Journey("2021-07-15", "10:20", 2, 1),
            new Journey("2021-07-15", "10:45", 1, 1),
            new Journey("2021-07-15", "16:15", 1, 1),
            new Journey("2021-07-15", "18:15", 1, 1),
            new Journey("2021-07-15", "19:00", 1, 2),

            new Journey("2021-07-16", "10:20", 2, 1),
            new Journey("2021-07-16", "10:45", 1, 1),
            new Journey("2021-07-16", "16:15", 1, 1),
            new Journey("2021-07-16", "18:15", 1, 1),
            new Journey("2021-07-16", "19:00", 1, 2),

            new Journey("2021-07-17", "10:20", 2, 1),
            new Journey("2021-07-17", "10:45", 1, 1),
            new Journey("2021-07-17", "16:15", 1, 1),
            new Journey("2021-07-17", "18:15", 1, 1),
            new Journey("2021-07-17", "19:00", 1, 2)
    );

    public static final List<Journey> INPUT_WEEKLY_LIMIT_NOT_REACHED = List.of(
            new Journey("2021-07-12", "10:20", 2, 1),
            new Journey("2021-07-12", "10:45", 1, 1),
            new Journey("2021-07-12", "16:15", 1, 1),
            new Journey("2021-07-12", "18:15", 1, 1),
            new Journey("2021-07-12", "19:00", 1, 2),

            new Journey("2021-07-13", "10:20", 2, 1),
            new Journey("2021-07-13", "10:45", 1, 1),
            new Journey("2021-07-13", "16:15", 1, 1),
            new Journey("2021-07-13", "18:15", 1, 1),
            new Journey("2021-07-13", "19:00", 1, 2),

            new Journey("2021-07-14", "10:20", 2, 1),
            new Journey("2021-07-14", "10:45", 1, 1),
            new Journey("2021-07-14", "16:15", 1, 1),
            new Journey("2021-07-14", "18:15", 1, 1),
            new Journey("2021-07-14", "19:00", 1, 2),

            new Journey("2021-07-15", "10:20", 2, 1),
            new Journey("2021-07-15", "10:45", 1, 1),
            new Journey("2021-07-15", "16:15", 1, 1),
            new Journey("2021-07-15", "18:15", 1, 1),
            new Journey("2021-07-15", "19:00", 1, 2),

            new Journey("2021-07-16", "10:20", 2, 1),
            new Journey("2021-07-16", "10:45", 1, 1),
            new Journey("2021-07-16", "16:15", 1, 1)
    );
}
