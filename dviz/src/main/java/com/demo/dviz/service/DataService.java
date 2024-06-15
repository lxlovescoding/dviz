package com.demo.dviz.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.demo.dviz.entity.AirlineDate;
import com.demo.dviz.entity.AirlineTime;
import com.demo.dviz.entity.AirportBusy;
import com.demo.dviz.entity.RouteDistance;
import com.demo.dviz.mapper.AirlineDateMapper;
import com.demo.dviz.mapper.AirlineTimeMapper;
import com.demo.dviz.mapper.AirportBusyMapper;
import com.demo.dviz.mapper.RouteDistanceMapper;
import com.demo.dviz.utils.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AirportBusyMapper airportBusyMapper;
    @Autowired
    private RouteDistanceMapper routeDistanceMapper;
    @Autowired
    private AirlineDateMapper airlineDateMapper;
    @Autowired
    private AirlineTimeMapper airlineTimeMapper;

    @Scheduled(fixedDelay = 10 * 1000)
    public void saveDataInRedis() {
        saveAirportCountData();
        saveAirlineCountData();
        saveAirlineDateData();
        saveAirlineTimeData();
    }

    public JSONArray getAirportCountData() {
        return JSONArray.parse(redisTemplate.opsForValue().get(RedisKey.AIRPORT_COUNT_KEY));
    }

    public JSONArray getAirlineCountData() {
        return JSONArray.parse(redisTemplate.opsForValue().get(RedisKey.AIRLINE_COUNT_KEY));
    }

    public JSONArray getAirlineDateData() {
        return JSONArray.parse(redisTemplate.opsForValue().get(RedisKey.AIRLINE_DATE_KEY));
    }

    public JSONArray getAirlineTimeData() {
        return JSONArray.parse(redisTemplate.opsForValue().get(RedisKey.AIRLINE_TIME_KEY));
    }

    private void saveAirportCountData() {
        List<AirportBusy> airportBusyList = airportBusyMapper.getAll();
        JSONArray ret = new JSONArray();
        for (AirportBusy airportBusy : airportBusyList) {
            JSONObject e = new JSONObject();
            e.put("name", airportBusy.getAirport());
            e.put("value", airportBusy.getFlightTime());
            ret.add(e);
        }
        redisTemplate.opsForValue().set(RedisKey.AIRPORT_COUNT_KEY, ret.toString());
    }

    private void saveAirlineCountData() {
        List<RouteDistance> routeList = routeDistanceMapper.getAllOrderByCount();
        JSONArray ret = new JSONArray();
        for (RouteDistance route : routeList) {
            JSONObject e = new JSONObject();
            e.put("name", route.getOrigin() + "-" + route.getDest());
            e.put("value", route.getCount());
            e.put("origin", route.getOrigin());
            e.put("dest", route.getDest());
            e.put("distance", route.getDistance());
            ret.add(e);
        }
        redisTemplate.opsForValue().set(RedisKey.AIRLINE_COUNT_KEY, ret.toString());
    }

    private void saveAirlineDateData() {
        List<AirlineDate> airlineDateList = airlineDateMapper.selectList(null);
        JSONArray ret = new JSONArray();
        for (AirlineDate airlineDate : airlineDateList) {
            JSONObject e = new JSONObject();
            e.put("date", airlineDate.getDate());
            e.put("count", airlineDate.getCount());
            ret.add(e);
        }
        redisTemplate.opsForValue().set(RedisKey.AIRLINE_DATE_KEY, ret.toString());
    }

    private void saveAirlineTimeData() {
        List<AirlineTime> airlineTimes = airlineTimeMapper.selectList(null);
        JSONArray ret = new JSONArray();
        for (AirlineTime airlineTime : airlineTimes) {
            JSONObject e = new JSONObject();
            e.put("index", airlineTime.getHour());
            e.put("count", airlineTime.getCount());
            e.put("type", airlineTime.getType());
            ret.add(e);
        }
        redisTemplate.opsForValue().set(RedisKey.AIRLINE_TIME_KEY, ret.toString());
    }
}
