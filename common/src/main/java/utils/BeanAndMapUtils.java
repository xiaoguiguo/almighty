package utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class BeanAndMapUtils {

    // map 转 java 对象
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        String jsonStr = JSONObject.toJSONString(map);
        return JSONObject.parseObject(jsonStr, clazz);
    }

    // java 对象转 map
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null) {
            return null;
        }
        String jsonStr = JSONObject.toJSONString(obj);
        return JSONObject.parseObject(jsonStr);
    }
}
