/*
 * Copyright (C) 2016 venshine.cn@gmail.com
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
package com.ywb.tuyue.utils;

import com.blankj.utilcode.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author soonphe
 * @Date 2018-08-21 13:16
 * @Description 正则验证工具类
 */
public class VerifyUtils {

    /**
     * 邮件格式验证
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)" +
                        "+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 邮件格式验证（字符串格式匹配）
     *
     * @param email
     * @return
     */
    public static boolean isEmail2(String email) {
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return email.matches(expr);
    }

    /**
     * 手机号格式验证
     *
     * @param number
     * @return
     */
    public static boolean isMobileNumber(String number) {
        Pattern p = Pattern.compile("^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * url格式验证
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }

    /**
     * 验证字符串是否仅为字母或数字
     *
     * @param data
     * @return
     */
    public static boolean isNumberAndLetter(String data) {
        String expr = "^[A-Za-z0-9]+$";
        return data.matches(expr);
    }

    /**
     * 验证字符串是否仅为数字
     *
     * @param data
     * @return
     */
    public static boolean isNumber(String data) {
        String expr = "^[0-9]+$";
        return data.matches(expr);
    }

    /**
     * 验证字符串是否仅为字母
     *
     * @param data
     * @return
     */
    public static boolean isLetter(String data) {
        String expr = "^[A-Za-z]+$";
        return data.matches(expr);
    }

    /**
     * 验证字符串是否仅为中文
     *
     * @param data
     * @return
     */
    public static boolean isChinese(String data) {
        String expr = "^[\u0391-\uFFE5]+$";
        return data.matches(expr);
    }

    /**
     * 验证字符串是否包含中文
     *
     * @param data
     * @return
     */
    public static boolean isContainsChinese(String data) {
        String chinese = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(data)) {
            for (int i = 0; i < data.length(); i++) {
                String temp = data.substring(i, i + 1);
                boolean flag = temp.matches(chinese);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证字符串是否为中国身份证号
     *
     * @param data
     * @return
     */
    public static boolean isChineseCard(String data) {
        String expr = "^[0-9]{17}[0-9xX]$";
        return data.matches(expr);
    }

    /**
     * 邮政编码验证
     *
     * @param data
     * @return
     */
    public static boolean isPostCode(String data) {
        String expr = "^[0-9]{6,10}";
        return data.matches(expr);
    }

}
