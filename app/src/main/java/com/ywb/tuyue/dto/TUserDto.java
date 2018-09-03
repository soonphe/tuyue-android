package com.ywb.tuyue.dto;

import org.litepal.annotation.Column;

/**
 * @Author soonphe
 * @Date 2018-08-31 19:12
 * @Descprition 用户数据传输DTO
 */

public class TUserDto {

    private String phone;
    private String imcode;
    private int age;
    private int sex;
    private String createtime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImcode() {
        return imcode;
    }

    public void setImcode(String imcode) {
        this.imcode = imcode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
