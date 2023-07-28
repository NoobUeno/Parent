package com.yao.common.entity;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Indicator {

    private String id;

    private String videoNo;

    private Integer is_indicator;

}