package com.ywb.tuyue.dto;

import com.ywb.tuyue.entity.TUser;

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

    public TUserDto(TUser tUser) {
        this.phone = tUser.getPhone();
        this.imcode = tUser.getImcode();
        this.age = tUser.getAge();
        this.sex = tUser.getSex();
        this.createtime = tUser.getCreatetime();
    }

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
