package com.yao.common.entity;

import lombok.Data;

/**
 * @Entity: Capture
 * @Author:
 * @Date: 2023/6/29
 * @Time: 11:37
 * @Description：<描述>
 **/
@Data
public class Capture {

    private String head;

    private String videoNo;

    private String trackId;

    private String enterTimestamp;

    private String bindingInfo;

    private String movingDirection;

    private String captureFaceBase64;

    private String captureFace;

    private String deviceId;

    private String leaveTimestamp;

    private String movingStatus;

    private String humanAttr;

    private String catchTimestamp;

    private String targetResult;

    private String chnDetectType;

    private String detectionResult;

    private String channelName;

    private String trackIdTimes;

    private String objType;

    private String fullImageBase64;

    private String fullImage;



}
