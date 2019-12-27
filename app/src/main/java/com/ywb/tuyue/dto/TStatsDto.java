package com.ywb.tuyue.dto;

import com.ywb.tuyue.entity.TStats;
import com.ywb.tuyue.entity.TUser;

import org.litepal.annotation.Column;

/**
 * @Author soonphe
 * @Date 2018-08-31 19:12
 * @Descprition 统计数据传输DTO
 */
public class TStatsDto {

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

    public TStatsDto(TStats tStats) {
        this.createdate = tStats.getCreatedate();
        this.phone = tStats.getPhone();
        this.imcode = tStats.getImcode();
        this.regtime = tStats.getRegtime();
        this.ismobile = tStats.isIsmobile();
        this.openlock = tStats.getOpenlock();
        this.book = tStats.getBook();
        this.booktime = tStats.getBooktime();
        this.city = tStats.getCity();
        this.citytime = tStats.getCitytime();
        this.food = tStats.getFood();
        this.foodtime = tStats.getFoodtime();
        this.game = tStats.getGame();
        this.gametime = tStats.getGametime();
        this.movies = tStats.getMovies();
        this.moviestime = tStats.getMoviestime();
        this.music = tStats.getMusic();
        this.musictime = tStats.getMusictime();
        this.subway = tStats.getSubway();
        this.subwaytime = tStats.getSubwaytime();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
