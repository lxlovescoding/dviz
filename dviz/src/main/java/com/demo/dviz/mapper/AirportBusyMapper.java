package com.demo.dviz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dviz.entity.AirportBusy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AirportBusyMapper extends BaseMapper<AirportBusy> {
    @Select("SELECT * FROM airport_busy ORDER BY flight_time DESC")
    List<AirportBusy> getAll();
}
