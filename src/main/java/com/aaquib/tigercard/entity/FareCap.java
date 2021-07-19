package com.aaquib.tigercard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareCap {
    private RouteID routeId;

    private Integer capAmount;
}
