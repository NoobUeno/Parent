package com.example.generator.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scene {

    private String id;

    private String name;

    private Integer pid;

    private Integer user_id;

    private Integer delete_flag;

    private String img_id;

    private String dis_img;

    private Double lon;

    private Double lat;

    private String description;

    private Integer iot_scene_type;

    private Integer guard_status;

    private String remark;

    private String province;

    private Integer province_code;

    private String city;

    private Integer city_code;

    private Integer seq;

    private Integer aid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date atime;

    private Integer mid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mtime;

    private String area_code;

}