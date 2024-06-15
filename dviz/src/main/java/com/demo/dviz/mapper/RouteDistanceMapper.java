package com.demo.dviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dviz.entity.RouteDistance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouteDistanceMapper extends BaseMapper<RouteDistance> {
    @Select("SELECT * FROM route_distance ORDER BY count DESC")
    List<RouteDistance> getAllOrderByCount();
}
