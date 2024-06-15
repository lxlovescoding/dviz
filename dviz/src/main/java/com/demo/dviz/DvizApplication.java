package com.demo.dviz;

import com.demo.dviz.mapper.AirportBusyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DvizApplication {

    public static void main(String[] args) {
        SpringApplication.run(DvizApplication.class, args);
    }

}
