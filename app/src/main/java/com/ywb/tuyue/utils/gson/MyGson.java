package com.ywb.tuyue.utils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * @Author soonphe
 * @Date 2018-08-31 18:58
 * @Descprition 自定义gson解析
 */

public class MyGson {

    private Gson gson;

    public MyGson() {
        gson = new Gson();
    }

    public MyGson(Gson gson) {
        this.gson = gson;
    }

    public <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T fromJson(JsonElement json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJson(Object src) {
        return gson.toJson(src);
    }

}
