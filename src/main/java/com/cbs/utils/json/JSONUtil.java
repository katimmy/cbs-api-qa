package com.cbs.utils.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.List;

public class JSONUtil {

    /**
     * Get json object from a given json file
     * @param json
     * @return
     */
    public static JSONObject getJSONObjectFromFile(String json) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (JSONObject) object;
    }

    /**
     * Deserialize JSON object using pojo
     * @param jsonString
     * @param clazz
     * @return
     */
    public static Object getClassObject(String jsonString, Class clazz) {
        Object object = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Get list from json object
     * @param key
     * @param object
     * @return
     */
    public static List<String> getListFromJSONObject(String key, JSONObject object) {
        try {
            return new ObjectMapper().readValue(object.get(key).toString(),
                    new TypeReference<List<String>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get string from json object
     * @param key
     * @param object
     * @return
     */
    public static String getStringFromJSONObject(String key, JSONObject object) {
        try {
            return object.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
