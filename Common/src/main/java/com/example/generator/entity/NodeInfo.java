package com.yao.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NodeInfo {

    private String id;

    private String name;

    private String device_code;

    private String iot_node_type;

    private String iot_protocal_category;

    private String seq;

    private String scene_id;

    private String delete_flag;

    private String iot_node_status;

    private String template_id;

    private String img_id;

    private String dis_img;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date maintenance_time;

    private String lonLat;

    private String infos;

    private String frequency;

    private String aid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date atime;

    private String mid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mtime;

    private String visualcode;

    private String address;

    private String qr_code;

}