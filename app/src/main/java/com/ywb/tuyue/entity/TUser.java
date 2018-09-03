package com.ywb.tuyue.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-30 18:03
 * @Descprition 平板注册用户
 */

public class TUser extends LitePalSupport {


    /**
     * age : string
     * avatar : string
     * city : string
     * createtime : 2018-08-30T06:17:30.635Z
     * delflag : true
     * id : 0
     * imcode : string
     * nickname : string
     * phone : string
     * realname : string
     * remark : string
     * sex : string
     * updatetime : 2018-08-30T06:17:30.635Z
     */
    private int id;
    private String phone;
    private String imcode;
    @Column(nullable = false, defaultValue = "0")
    private int age;
    @Column(nullable = false, defaultValue = "0")
    private int sex;

    private String avatar;
    private String city;
    private String nickname;
    private String realname;
    private String remark;
    private String createtime;
    private String updatetime;
    private boolean delflag;

    public TUser(int age, String createtime, String imcode, String phone, int sex) {
        this.age = age;
        this.createtime = createtime;
        this.imcode = imcode;
        this.phone = phone;
        this.sex = sex;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public boolean isDelflag() {
        return delflag;
    }

    public void setDelflag(boolean delflag) {
        this.delflag = delflag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImcode() {
        return imcode;
    }

    public void setImcode(String imcode) {
        this.imcode = imcode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
