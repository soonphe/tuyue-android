package com.ywb.tuyue.constants;


/**
 * @Author soonphe
 * @Date 2018-08-21 09:41
 * @Description 状态码常量
 */
public enum CodeEnum {

    //通用
    SUCCESS("200", "获取成功"),
    NOHTTP("201", "您的网络状况存在问题，调用服务失败！"),
    FAIL("1001", "获取信息失败"),
    PARAMFAIL("1002", "参数为空或所传参数类型不正确"),
    REPEATSUBMIT("1003", "重复提交"),

    //用户   100-199
    USERISNOT("101", "用户不存在"),
    LOGINFAIL("102", "您的密码错误!"),
    CAPTCHFAIL("103", "验证码错误"),
    TOKENFAIL("104", "token验证失败"),
    AUDITFAIL("105", "禁用"),
    AUDITING("106", "审核中"),
    SESSIONFAIL("107", "session失效"),
    PHONEREPEAT("108", "您的手机号已被注册"),
    PHONEFAIL("109", "您的手机号还没有注册"),
    OPENLOGIN("110", "请完善个人信息"),
    HASNEXT("111", "可继续加载元素"),
    ISBLACK("113", "已加入黑名单"),
    NOBLACK("114", "未加入黑名单"),
    NOADDRESS("117", "地址参数错误"),

    FILEMISS("118", "文件不存在"),
    FILETOOBIG("119", "文件过大"),
    FILEUPLOADFAIL("120", "文件上传失败"),

    EXCEPTION("301", "请求存在异常");

    private String code;
    private String message;


    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CodeEnum valOf(String authCode){
        for (CodeEnum codeEnum :values()){
            if (codeEnum.getCode().equals(authCode+"")){
                return codeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
