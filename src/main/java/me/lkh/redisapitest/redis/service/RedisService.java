package me.lkh.redisapitest.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    void setStringData(String key, String value);
    String getStringData(String key);
    void setListData(String key, List<String> values);
    List<Object> getListData(String key);
    void setSetData(String key, String value);
    Set<Object> getSetData(String key);
    void setMapData(String key, String mapKey, String mapValue);
    Map<Object, Object> getMapData(String key);
    void setSortedSetData(String key, String value, int score);
    Set<Object> getSortedSetData(String key, boolean isDesc);
}
