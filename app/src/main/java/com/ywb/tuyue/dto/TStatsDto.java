package com.ywb.tuyue.dto;

import org.litepal.annotation.Column;

/**
 * @Author soonphe
 * @Date 2018-08-31 19:12
 * @Descprition 统计数据传输DTO
 */

public class TStatsDto {

    private int id;
    private String createdate;//创建时间yyyy-MM-dd
    private String phone;
    private String imcode;
    private String regtime; //注册时间yyyy-MM-dd HH:mm:ss
    private boolean ismobile;

    private int openlock;
    private int book;
    private int booktime;
    private int city;
    private int citytime;
    private int food;
    private int foodtime;
    private int game;
    private int gametime;
    private int movies;
    private int moviestime;
    private int music;
    private int musictime;
    private int subway;
    private int subwaytime;
}
