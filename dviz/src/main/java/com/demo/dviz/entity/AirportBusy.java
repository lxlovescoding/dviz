package com.demo.dviz.entity;

import lombok.Data;

@Data
public class AirportBusy {
    private Integer id;
    private String airport;
    private Long flightTime;
}
