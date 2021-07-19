package com.aaquib.tigercard.factory;

import com.aaquib.tigercard.entity.ZoneID;

import java.util.Map;

public class ZoneFactoryImpl implements ZoneFactory {

    private static final Map<String, ZoneID> ZONE_MAP = Map.of("1", ZoneID.ZONE_ONE,
            "2", ZoneID.ZONE_TWO);

    public ZoneID getZoneID(String zoneNumber) {
        return ZONE_MAP.get(zoneNumber);
    }
}
