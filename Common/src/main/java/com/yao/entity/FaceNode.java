package com.yao.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FaceNode {

    private String id;

    private String name;

    private String device_code;

    private Integer iot_node_type;

    private String iot_protocal_category;

    private Integer seq;

    private Integer scene_id;

    private Integer delete_flag;

    private Integer iot_node_status;

    private Integer template_id;

    private Integer img_id;

    private String dis_img;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date maintenance_time;

    private String point;

    private String infos;

    private Integer frequency;

    private Integer aid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date atime;

    private Integer mid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mtime;

    private String visualcode;

    private String address;

    private String qr_code;

    private String ip;

    private Integer port;

    private String username;

    private String password;

}