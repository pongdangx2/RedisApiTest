package me.lkh.redisapitest.redis.controller;

import me.lkh.redisapitest.redis.service.RedisService;
import me.lkh.redisapitest.redis.vo.ResponseCode;
import me.lkh.redisapitest.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lkh")
public class RedisController {

    @Autowired
    private RedisService redisService;

    /**
     * string 추가
     * @param requestBody key: 키, value: 값
     * @return
     */
    @PostMapping("/string")
    public Map<String, Object> setString(@RequestBody Map<String, Object> requestBody){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap;

        if(!requestBody.containsKey("key") || !requestBody.containsKey("value")) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.get("key").toString();
            String value = requestBody.get("value").toString();
            redisService.setStringData(key, value);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }

        result.put("Info", infoMap);

        return result;
    }

    /**
     * String 조회
     * @param key : 키
     * @return 값
     */
    @GetMapping("/string/{key}")
    public Map<String, Object> getString(@PathVariable("key") String key){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        Map<String, Object> dataMap = new HashMap<>();

        try{
            dataMap.put("data", redisService.getStringData(key));
            dataMap.put("key", key);
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.put("Info", infoMap);
        result.put("Data", dataMap);
        return result;
    }

    /**
     * 리스트 추가
     * @param requestBody key: 키, value : 값들
     * @return
     */
    @PostMapping("/list")
    public Map<String, Object> setList(@RequestBody Map<String, Object> requestBody){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap;


        if(!requestBody.containsKey("key") || !requestBody.containsKey("value")) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            try{
                String key = requestBody.get("key").toString();
                List<String> value = (List<String>) requestBody.get("value");

                redisService.setListData(key, value);
                infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);

            } catch(Exception e){
                e.printStackTrace();
                infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
            }
        }

        result.put("Info", infoMap);

        return result;
    }

    /**
     *
     * @param key : key
     * @param index
     * @param startIndex
     * @param lastIndex : index, startIndex와 lastIndex가 모두 있으면 index번째 배열의 startIndex부터 lastIndex까지 조회. 그외는 전체 조회
     * @return
     */
    @GetMapping("/list/{key}")
    public Map<String, Object> getList(@PathVariable("key") String key,
                                       @RequestParam(required = false) Integer index,
                                       @RequestParam(required = false) Integer startIndex,
                                       @RequestParam(required = false) Integer lastIndex){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        List<Object> dataList = new ArrayList<>();


        try{
            if(startIndex == null || lastIndex == null){
                dataList = redisService.getListData(key);
            } else{
                dataList = (List<Object>) redisService.getListData(key).get(index);
                dataList = dataList.subList(startIndex, lastIndex);
            }
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.put("Info", infoMap);
        result.put("Data", dataList);
        return result;
    }

    /**
     * set에 값 추가
     * @param requestBody key: 키, value : 값
     * @return
     */
    @PostMapping("/set")
    public Map<String, Object> setSet(@RequestBody Map<String, Object> requestBody){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap;

        if(!requestBody.containsKey("key") || !requestBody.containsKey("value")) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.get("key").toString();
            String value = requestBody.get("value").toString();
            redisService.setSetData(key, value);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }

        result.put("Info", infoMap);

        return result;
    }

    /**
     * set에서 값 조회
     * @param key : 키
     * @return
     */
    @GetMapping("/set/{key}")
    public Map<String, Object> getSet(@PathVariable("key") String key){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        Map<String, Object> dataMap = new HashMap<>();

        try{
            dataMap.put("data", redisService.getSetData(key));
            dataMap.put("key", key);
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.put("Info", infoMap);
        result.put("Data", dataMap);
        return result;
    }

    /**
     * Map 추가
     * @param requestBody
     * @return
     */
    @PostMapping("/map")
    public Map<String, Object> setMap(@RequestBody Map<String, Object> requestBody){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap;

        if(!requestBody.containsKey("key") || !requestBody.containsKey("mapKey") || !requestBody.containsKey("mapValue")) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.get("key").toString();
            String mapKey = requestBody.get("mapKey").toString();
            String mapValue = requestBody.get("mapValue").toString();

            redisService.setMapData(key, mapKey, mapValue);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }
        result.put("Info", infoMap);
        return result;
    }
    /**
     * map 조회
     * @param key : 키
     * @return 값
     */
    @GetMapping("/map/{key}")
    public Map<String, Object> getMap(@PathVariable("key") String key){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        Map<String, Object> dataMap = new HashMap<>();

        try{
            dataMap.put("data", redisService.getMapData(key));
            dataMap.put("key", key);
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.put("Info", infoMap);
        result.put("Data", dataMap);
        return result;
    }

    @PostMapping("/sortedSet")
    public Map<String, Object> setSortedSet(@RequestBody Map<String, Object> requestBody){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap;

        if(!requestBody.containsKey("key") || !requestBody.containsKey("value") || !requestBody.containsKey("score")) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            try{
                String key = requestBody.get("key").toString();
                String value = requestBody.get("value").toString();
                int score = Integer.parseInt(requestBody.get("score").toString());

                redisService.setSortedSetData(key, value, score);

                infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
            } catch(Exception e){
                e.printStackTrace();
                infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
            }
        }

        result.put("Info", infoMap);

        return result;
    }

    /**
     *
     * @param key
     * @param isDesc N: 오름차순, Y:내림차순
     * @return
     */
    @GetMapping("/sortedSet/{key}")
    public Map<String, Object> getsortedSet(@PathVariable("key") String key,
                                            @RequestParam(required = false) String isDesc){

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        Map<String, Object> dataMap = new HashMap<>();
        boolean isDescBoolean = false;
        try{
            if("Y".equals(isDesc)){
                isDescBoolean = true;
            }
            dataMap.put("data", redisService.getSortedSetData(key, isDescBoolean));
            dataMap.put("key", key);
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.put("Info", infoMap);
        result.put("Data", dataMap);
        return result;
    }
}
