package com.aaquib.tigercard.factory;

import com.aaquib.tigercard.entity.ZoneID;

public interface ZoneFactory {
    ZoneID getZoneID(String zoneNumber);
}
