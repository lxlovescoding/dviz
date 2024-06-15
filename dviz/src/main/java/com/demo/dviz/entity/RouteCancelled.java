package com.demo.dviz.entity;

import lombok.Data;

@Data
public class RouteCancelled {
    private Integer id;
    private String route;
    private String cancelled;
}
