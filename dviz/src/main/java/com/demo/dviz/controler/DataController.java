package com.demo.dviz.controler;

import com.alibaba.fastjson2.JSONArray;
import com.demo.dviz.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DataController {
    @Autowired
    private DataService dataService;

    @GetMapping("/airport_count_data")
    public JSONArray getAirportCountData() {
        return dataService.getAirportCountData();
    }

    @GetMapping("/airline_count_data")
    public JSONArray getAirlineCountData() {
        return dataService.getAirlineCountData();
    }

    @GetMapping("/airline_date_data")
    public JSONArray getAirlineDateData() {
        return dataService.getAirlineDateData();
    }

    @GetMapping("/airline_time_data")
    public JSONArray getAirlineTimeData() {
        return dataService.getAirlineTimeData();
    }
}
