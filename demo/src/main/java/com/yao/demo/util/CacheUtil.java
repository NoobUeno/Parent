//package com.yao.demo.util;
//
//import com.ruoyi.iot.bean.IotNodeInfo;
//import com.ruoyi.iot.bo.IotNodeInfoBO;
//import com.ruoyi.iot.common.IotConstants;
//import com.ruoyi.iot.entity.LinkageAlarm;
//import com.ruoyi.iot.entity.VideoNodeInfo;
//import com.ruoyi.iot.mapper.IotNodeMapper;
//import com.ruoyi.iot.service.IotVideoNodeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @Entity: CacheUtil
// * @Author:
// * @Date: 2023/7/22
// * @Time: 15:45
// * @Description：<描述>
// **/
//@Component
//public class CacheUtil {
//
//    private static IotNodeMapper nodeMapper;
//
//    private static IotVideoNodeService videoNodeService;
//
//
//    private static RedisTemplate redisTemplate;
//
//    @Autowired
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        CacheUtil.redisTemplate = redisTemplate;
//    }
//
//    @Autowired
//    public  void setNodeMapper(IotNodeMapper nodeMapper) {
//        CacheUtil.nodeMapper = nodeMapper;
//    }
//
//    @Autowired
//    public  void setVideoNodeService(IotVideoNodeService videoNodeService) {
//        CacheUtil.videoNodeService = videoNodeService;
//    }
//
//    public static void CacheSensorNodeList(){
//        Long size = redisTemplate.opsForList().size(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST);
//        for (long i = 0; i < size; i++) {
//            redisTemplate.opsForList().leftPop(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST);
//        }
//
//        List<IotNodeInfoBO> nodeInfoBOList = nodeMapper.selectNodeAndSensor();
//        for (IotNodeInfoBO node:nodeInfoBOList
//             ) {
//            redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,node);
//        }
//    }
//
//    public static void addCacheSensorNodeList(IotNodeInfo nodeInfo){
//        redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,nodeInfo);
//    }
//
//    public static void updateCacheSensorNodeList(IotNodeInfo nodeInfo){
//        List<IotNodeInfoBO> nodeInfoList =  redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,0,-1);
//        for (int i = 0; i < nodeInfoList.size(); i++) {
//            if (nodeInfoList.get(i).getId().equals(nodeInfo.getId())){
//                redisTemplate.opsForList().set(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,i,nodeInfo);
//            }
//        }
//    }
//
//    public static void deleteCacheSensorNodeList(String id){
//        List<IotNodeInfoBO> nodeInfoList =  redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,0,-1);
//        for (int i = 0; i < nodeInfoList.size(); i++) {
//            if (nodeInfoList.get(i).getId().equals(id)){
//                redisTemplate.opsForList().remove(IotConstants.COLLECTION_ELEMENT_NAME.SENSOR_NODE_LIST,i,nodeInfoList.get(i));
//            }
//        }
//    }
//
//    public static void CacheVideoNodeList(){
//
//        Long size = redisTemplate.opsForList().size(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST);
//        for (long i = 0; i < size; i++) {
//            redisTemplate.opsForList().leftPop(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST);
//        }
//
//        List<VideoNodeInfo> nodeInfoList = videoNodeService.findVideoNodeByCondition(null, null);
//        for (VideoNodeInfo videoNode: nodeInfoList
//             ) {
//            redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,videoNode);
//        }
//    }
//
//    public static void addCacheVideoNodeList(VideoNodeInfo videoNode){
//        redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,videoNode);
//    }
//
//    public static void updateCacheVideoNodeList(VideoNodeInfo videoNode){
//        List<VideoNodeInfo> videoNodeInfoList =  redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,0,-1);
//        for (int i = 0; i < videoNodeInfoList.size(); i++) {
//            if (videoNodeInfoList.get(i).getId().equals(videoNode.getId())){
//                redisTemplate.opsForList().set(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,i,videoNode);
//            }
//        }
//    }
//
//    public static void deleteCacheVideoNodeList(String id){
//        List<VideoNodeInfo> videoNodeInfoList =  redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,0,-1);
//        for (int i = 0; i < videoNodeInfoList.size(); i++) {
//            if (videoNodeInfoList.get(i).getId().equals(id)){
//                redisTemplate.opsForList().remove(IotConstants.COLLECTION_ELEMENT_NAME.VIDEO_NODE_LIST,i,videoNodeInfoList.get(i));
//            }
//        }
//    }
//
//    public static void updateLinkageAlarmCache(LinkageAlarm alarm){
//        List<LinkageAlarm> alarmList = redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.LINKAGE_TRIGGER_LIST,0,-1);
//        for (int i = 0; i < alarmList.size(); i++) {
//            if (alarmList.get(i).getId().equals(alarm.getId())){
//                redisTemplate.opsForList().set(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST,i,alarm);
//            }
//        }
//
//        List<LinkageAlarm> todayAlarmList = redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.LINKAGE_TRIGGER_LIST_WILL_EXPIRE,0,-1);
//        for (int i = 0; i < todayAlarmList.size(); i++) {
//            if (todayAlarmList.get(i).getId().equals(alarm.getId())){
//                redisTemplate.opsForList().set(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST_WILL_EXPIRE,i,alarm);
//            }
//        }
//    }
//
//    public static void deleteLinkageAlarmCache(String id){
//        List<LinkageAlarm> alarmList = redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.LINKAGE_TRIGGER_LIST,0,-1);
//        for (int i = 0; i < alarmList.size(); i++) {
//            if (alarmList.get(i).getId().equals(id)){
//                redisTemplate.opsForList().remove(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST,1,alarmList.get(i));
//            }
//        }
//
//        List<LinkageAlarm> todayAlarmList = redisTemplate.opsForList().range(IotConstants.COLLECTION_ELEMENT_NAME.LINKAGE_TRIGGER_LIST_WILL_EXPIRE,0,-1);
//        for (int i = 0; i < todayAlarmList.size(); i++) {
//            if (todayAlarmList.get(i).getId().equals(id)){
//                redisTemplate.opsForList().remove(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST_WILL_EXPIRE,1,todayAlarmList.get(i));
//            }
//        }
//    }
//
//    public static void addLinkageAlarmCache(LinkageAlarm alarm){
//        redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST,alarm);
//        redisTemplate.opsForList().leftPush(IotConstants.COLLECTION_ELEMENT_NAME.LOCATION_TRIGGER_LIST_WILL_EXPIRE,alarm);
//    }
//
//}
