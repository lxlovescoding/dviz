package com.demo.dviz;

import com.demo.dviz.mapper.AirportBusyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DvizApplicationTests {
    @Autowired
    AirportBusyMapper airportBusyMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("zzp", "1");
    }

}
