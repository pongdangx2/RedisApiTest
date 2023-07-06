package me.lkh.redisapitest.redis.service.impl;

import me.lkh.redisapitest.redis.service.RedisService;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setStringData(String key, String value) {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(key, value);
    }

    @Override
    public String getStringData(String key) {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
        return stringObjectValueOperations.get(key).toString();
    }

    @Override
    public void setListData(String key, List<String> values) {
        ListOperations<String, Object> stringObjectListOperations = redisTemplate.opsForList();
        stringObjectListOperations.rightPushAll(key, values);
    }

    @Override
    public List<Object> getListData(String key) {

        ListOperations<String, Object> stringObjectListOperations = redisTemplate.opsForList();
        return stringObjectListOperations.range(key, 0, -1);
    }

    @Override
    public void setSetData(String key, String value) {
        SetOperations<String, Object> stringObjectSetOperations = redisTemplate.opsForSet();
        stringObjectSetOperations.add(key, value);
    }

    @Override
    public Set<Object> getSetData(String key) {
        SetOperations<String, Object> stringObjectSetOperations = redisTemplate.opsForSet();
        return stringObjectSetOperations.members(key);
    }

    @Override
    public void setMapData(String key, String mapKey, String mapValue){
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        stringObjectObjectHashOperations.put(key, mapKey, mapValue);
    }

    @Override
    public Map<Object, Object> getMapData(String key){
        HashOperations<String, Object, Object> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        return stringObjectObjectHashOperations.entries(key);
    }

    @Override
    public void setSortedSetData(String key, String value, int score){
        ZSetOperations<String, Object> stringObjectZSetOperations = redisTemplate.opsForZSet();
        stringObjectZSetOperations.add(key, value, score);
    }

    @Override
    public Set<Object> getSortedSetData(String key, boolean isDesc){
        ZSetOperations<String, Object> stringObjectZSetOperations = redisTemplate.opsForZSet();
        if(isDesc){
            return stringObjectZSetOperations.reverseRange(key, 0, -1);
        } else {
            return stringObjectZSetOperations.range(key, 0, -1);
        }
    }
}