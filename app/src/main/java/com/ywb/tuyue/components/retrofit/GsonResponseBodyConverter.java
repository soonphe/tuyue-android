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

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.ywb.tuyue.constants.CodeEnum;

import org.json.JSONObject;

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
    public T convert(@NonNull ResponseBody value) {
        try {
            String body = value.string();
            JSONObject json = new JSONObject(body);
            String resultCode = json.optString("resultCode");
            if (CodeEnum.SUCCESS.getCode().equals(resultCode)) {
                Object data = json.opt("data");
                if (data == null || data.toString().equals("null")) {
                    return (T) new Object();
                }
                return (T)adapter.fromJson(data.toString());
            } else {
                String msg = json.optString("message", "");
                LogUtils.e("resultCode not equals 200,msg-" + msg);
                throw new RuntimeException(msg);
            }
        } catch (Exception e) {
            LogUtils.e("-" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }

    /**
     * 验证字符串是否为json格式
     *
     * @param json 字符串
     * @return
     */
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
