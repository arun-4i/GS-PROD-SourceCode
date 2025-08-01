package com.mobile.integration.grandstores.Utils.JsonUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

@Component
public class JsonUtils {
    
    public Map<String, Object> getJsonArrayChildValue(
                                Map<String, Object> jsonMap, 
                                String childElementName){
        Map<String, Object> mapls=new HashMap<>();
        JSONObject jsonObject = new JSONObject(jsonMap);
        JSONArray jsonArray = (JSONArray) jsonObject.get(childElementName);
        // 
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);
            Iterator<String> keys = job.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = job.get(key);
                // System.out.println(key+"==>>="+value);
                mapls.put(key, value);
            }
        }
    return mapls;
    }


    public Map<String, Object> putJsonArrayChildValue(
                                Map<String, Object> jsonMap, 
                                String childElementName, 
                                String userId,
                                String userName){
        Map<String, Object> mapls=new HashMap<>();
        JSONObject jsonObject = new JSONObject(jsonMap);
        JSONArray jsonArray = (JSONArray) jsonObject.get(childElementName);
        // 
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);
            Iterator<String> keys = job.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = job.get(key);
                // System.out.println(key+"==>>="+value);
                mapls.put(key, value);
            }
        }

        // mapls.put(key, value)
        // items.get(0).put("P_TOKEN", "-1"); 
        // items.get(0).put("P_REFRESH_TOKEN", "-1");

    return mapls;
    }




    public JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
        JSONObject jsonData = new JSONObject();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map<?, ?>) {
                value = getJsonFromMap((Map<String, Object>) value);
            }
            jsonData.put(key, value);
        }
        return jsonData;
    }

}