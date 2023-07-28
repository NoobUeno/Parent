package com.yao.common.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sensor {

    private String id;

    private String name;

    private Integer measure_unit_type;

    private Integer iot_sensor_type;

    private Integer iot_sensor_category;

    private Integer node_id;

    private String sensor_device_id;

    private Integer port_id;

    private String str_sdata;

    private Integer seq;

    private Integer delete_flag;

    private Integer iot_sensor_status;

    private Integer sdata_degree;

    private String formula_up;

    private String formula_down;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date register_time;

    private Integer data_type;

    private Integer param_type;

    private String param_names;

    private String param_config;

    private String infos;

    private Integer store_strage;

    private Integer aid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date atime;

    private Integer mid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mtime;

}