package me.lkh.redisapitest.util;

import me.lkh.redisapitest.redis.vo.ResponseCode;

import java.util.HashMap;
import java.util.Map;

public class RedisUtil {

    public static Map<String, Object> getInfoMap(ResponseCode responseCode){
        Map<String, Object> result = new HashMap<>();

        result.put("ResponseCode", responseCode.getStatusCode());
        result.put("ResponseMessage", responseCode.getStatusMessage());

        return result;
    }

    public static Map<String, Object> getInfoMap(ResponseCode responseCode, String message){
        Map<String, Object> result = new HashMap<>();

        result.put("ResponseCode", responseCode.getStatusCode());
        result.put("ResponseMessage", responseCode.getStatusMessage());
        result.put("Message", message);

        return result;
    }
}
