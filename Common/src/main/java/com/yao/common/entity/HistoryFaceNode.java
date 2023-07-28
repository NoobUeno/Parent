package com.yao.common.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryFaceNode {

    private String id;

    private String node_id;

    private Integer status;

    private Integer is_success;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_time;

    private String username;

    private String password;

    private String internet_protocol;

    private Integer port;

}