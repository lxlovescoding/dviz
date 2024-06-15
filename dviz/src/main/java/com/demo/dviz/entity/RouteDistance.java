package com.demo.dviz.entity;

import lombok.Data;

@Data
public class RouteDistance {
    private Integer id;
    private String origin;
    private String dest;
    private Double distance;
    private Integer count;
}
