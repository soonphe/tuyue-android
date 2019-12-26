/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ywb.tuyue.components.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        try {
            String     body = value.string();
            Log.e("body",body);
            JSONObject json = new JSONObject(body);
            String   resultCode = json.optString("resultCode");
            String   msg  = json.optString("message", "");
            if ("200".equals(resultCode)) {

                if (json.has("data")) {
                    Object data = json.get("data");

                    if (data==null||data.toString().equals("null")){
                        return (T) new Object();
                    }
                    //判断是否为json，String类型直接返回
                    if (isJson(data.toString())){
                        return adapter.fromJson(data.toString());
                    }else{
                        return (T)data.toString();
                    }
                } else {
                    throw new RuntimeException(msg);
                }
            } else if("1".equals(resultCode)){
                return (T) new ArrayList<>();
            }
//            else if(CodeEnum.stateOf(code)!=null){
//                return (T) new Object();
//            }
            else {
                    LogUtils.e("-" + msg);
                    throw new RuntimeException(msg);
                }
            } catch (Exception e) {
            LogUtils.e("-" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }

    private static boolean isJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            LogUtils.e("bad json: " + json);
            return false;
        }
    }
}
