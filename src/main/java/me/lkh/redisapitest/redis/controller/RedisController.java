package me.lkh.redisapitest.redis.controller;

import me.lkh.redisapitest.redis.service.RedisService;
import me.lkh.redisapitest.redis.vo.list.ListRequestVO;
import me.lkh.redisapitest.redis.vo.list.ListResponseVO;
import me.lkh.redisapitest.redis.vo.map.MapRequestVO;
import me.lkh.redisapitest.redis.vo.map.MapResponseVO;
import me.lkh.redisapitest.redis.vo.set.SetRequestVO;
import me.lkh.redisapitest.redis.vo.set.SetResponseVO;
import me.lkh.redisapitest.redis.vo.sortedset.SortedSetRequestVO;
import me.lkh.redisapitest.redis.vo.sortedset.SortedSetResponseVO;
import me.lkh.redisapitest.redis.vo.string.StringRequestVO;
import me.lkh.redisapitest.redis.vo.string.StringResponseVO;
import me.lkh.redisapitest.redis.vo.ResponseCode;
import me.lkh.redisapitest.util.RedisUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * string 추가
     * @param requestBody key: 키, value: 값
     * @return
     */
    @PostMapping("/string")
    public StringResponseVO setString(@RequestBody StringRequestVO requestBody){

        StringResponseVO result = new StringResponseVO();
        Map<String, Object> infoMap;

        if(requestBody.getKey() == null || requestBody.getValue() == null) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.getKey();
            String value = requestBody.getValue();
            redisService.setStringData(key, value);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }

        result.setInfoMap(infoMap);

        return result;
    }

    /**
     * String 조회
     * @param key : 키
     * @return 값
     */
    @GetMapping("/string/{key}")
    public StringResponseVO getString(@PathVariable("key") String key){

        StringResponseVO result = new StringResponseVO();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);

        try{
            result.setStringData(redisService.getStringData(key));
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.setInfoMap(infoMap);
        return result;
    }

    /**
     * 리스트 추가
     * @param requestBody key: 키, value : 값들
     * @return
     */
    @PostMapping("/list")
    public ListResponseVO setList(@RequestBody ListRequestVO requestBody){

        ListResponseVO result = new ListResponseVO();
        Map<String, Object> infoMap;


        if(requestBody.getKey() == null || requestBody.getValue() == null) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            try{
                String key = requestBody.getKey();
                List<String> value = requestBody.getValue();
                System.out.println(value);

                redisService.setListData(key, value);
                infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);

            } catch(Exception e){
                e.printStackTrace();
                infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
            }
        }

        result.setInfoMap(infoMap);

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
    public ListResponseVO getList(@PathVariable("key") String key,
                                       @RequestParam(required = false) Integer index,
                                       @RequestParam(required = false) Integer startIndex,
                                       @RequestParam(required = false) Integer lastIndex){

        ListResponseVO result = new ListResponseVO();
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

        result.setDataList(dataList);
        result.setInfoMap(infoMap);

        return result;
    }

    /**
     * set에 값 추가
     * @param requestBody key: 키, value : 값
     * @return
     */
    @PostMapping("/set")
    public SetResponseVO setSet(@RequestBody SetRequestVO requestBody){

        SetResponseVO result = new SetResponseVO();
        Map<String, Object> infoMap;

        if(requestBody.getKey() == null || requestBody.getValue() == null) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.getKey();
            String value = requestBody.getValue();
            redisService.setSetData(key, value);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }

        result.setInfoMap(infoMap);

        return result;
    }

    /**
     * set에서 값 조회
     * @param key : 키
     * @return
     */
    @GetMapping("/set/{key}")
    public SetResponseVO getSet(@PathVariable("key") String key){

        SetResponseVO result = new SetResponseVO();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);

        try{
            result.setData(redisService.getSetData(key));
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.setInfoMap(infoMap);
        return result;
    }

    /**
     * Map 추가
     * @param requestBody
     * @return
     */
    @PostMapping("/map")
    public MapResponseVO setMap(@RequestBody MapRequestVO requestBody){

        MapResponseVO result = new MapResponseVO();
        Map<String, Object> infoMap;

        if(requestBody.getKey() == null || requestBody.getMapKey()  == null|| requestBody.getMapValue() == null) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            String key = requestBody.getKey();
            String mapKey = requestBody.getMapKey();
            String mapValue = requestBody.getMapValue();

            redisService.setMapData(key, mapKey, mapValue);

            infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        }
        result.setInfoMap(infoMap);

        return result;
    }
    /**
     * map 조회
     * @param key : 키
     * @return 값
     */
    @GetMapping("/map/{key}")
    public MapResponseVO getMap(@PathVariable("key") String key){

        MapResponseVO result = new MapResponseVO();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);

        try{
            result.setData(redisService.getMapData(key));
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.setInfoMap(infoMap);
        return result;
    }

    @PostMapping("/sortedSet")
    public SortedSetResponseVO setSortedSet(@RequestBody SortedSetRequestVO requestBody){

        SortedSetResponseVO result = new SortedSetResponseVO();
        Map<String, Object> infoMap;

        if(requestBody.getKey() == null || requestBody.getValue() == null || requestBody.getScore() == null) {
            infoMap = RedisUtil.getInfoMap(ResponseCode.INVALID_PARAMETER, "필수 파라미터 누락");
        } else{
            try{
                String key = requestBody.getKey();
                String value = requestBody.getValue();
                int score = requestBody.getScore();

                redisService.setSortedSetData(key, value, score);

                infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
            } catch(Exception e){
                e.printStackTrace();
                infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
            }
        }

        result.setInfoMap(infoMap);

        return result;
    }

    /**
     *
     * @param key
     * @param isDesc N: 오름차순, Y:내림차순
     * @return
     */
    @GetMapping("/sortedSet/{key}")
    public SortedSetResponseVO getsortedSet(@PathVariable("key") String key,
                                            @RequestParam(required = false) String isDesc){

        SortedSetResponseVO result = new SortedSetResponseVO();
        Map<String, Object> infoMap = RedisUtil.getInfoMap(ResponseCode.SUCCESS);
        boolean isDescBoolean = false;
        try{
            if("Y".equals(isDesc)){
                isDescBoolean = true;
            }
            result.setData(redisService.getSortedSetData(key, isDescBoolean));
        } catch(Exception e){
            e.printStackTrace();
            infoMap = RedisUtil.getInfoMap(ResponseCode.FAILED);
        }

        result.setInfoMap(infoMap);
        return result;
    }
}
