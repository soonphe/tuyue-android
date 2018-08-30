package com.ywb.tuyue.entity;

/**
 * @Author soonphe
 * @Date 2018-08-30 18:03
 * @Descprition 平板注册用户
 */

public class TUser {


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

    private String age;
    private String avatar;
    private String city;
    private String createtime;
    private boolean delflag;
    private int id;
    private String imcode;
    private String nickname;
    private String phone;
    private String realname;
    private String remark;
    private String sex;
    private String updatetime;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
