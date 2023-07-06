package me.lkh.redisapitest.redis.vo.sortedset;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SortedSetResponseVO {

    private Map<String, Object> infoMap;
    private Set<Object> data;

    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, Object> infoMap) {
        this.infoMap = infoMap;
    }

    public Set<Object> getData() {
        return data;
    }

    public void setData(Set<Object> data) {
        this.data = data;
    }
}
