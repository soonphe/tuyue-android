package com.ywb.tuyue.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * @Author soonphe
 * @Date 2018-08-30 15:26
 * @Descprition
 */

public class TStats extends LitePalSupport  implements Serializable {


    /**
     * book : 0
     * booktime : 0
     * city : 0
     * citytime : 0
     * createdate : 2018-08-30T06:17:30.601Z
     * food : 0
     * foodtime : 0
     * game : 0
     * gametime : 0
     * id : 0
     * imcode : string
     * ismobile : true
     * movies : 0
     * moviestime : 0
     * music : 0
     * musictime : 0
     * phone : string
     * regtime : 2018-08-30T06:17:30.601Z
     * subway : 0
     * subwaytime : 0
     */

    private int id;
    private String createdate;//创建时间yyyy-MM-dd
    private String phone;
    private String imcode;
    private String regtime; //注册时间yyyy-MM-dd HH:mm:ss
    private boolean ismobile;

    @Column(nullable = false, defaultValue = "1")   //创建时默认解锁一次
    private int openlock;
    @Column(nullable = false, defaultValue = "0")
    private int book;
    @Column(nullable = false, defaultValue = "0")
    private int booktime;
    @Column(nullable = false, defaultValue = "0")
    private int city;
    @Column(nullable = false, defaultValue = "0")
    private int citytime;
    @Column(nullable = false, defaultValue = "0")
    private int food;
    @Column(nullable = false, defaultValue = "0")
    private int foodtime;
    @Column(nullable = false, defaultValue = "0")
    private int game;
    @Column(nullable = false, defaultValue = "0")
    private int gametime;
    @Column(nullable = false, defaultValue = "0")
    private int movies;
    @Column(nullable = false, defaultValue = "0")
    private int moviestime;
    @Column(nullable = false, defaultValue = "0")
    private int music;
    @Column(nullable = false, defaultValue = "0")
    private int musictime;
    @Column(nullable = false, defaultValue = "0")
    private int subway;
    @Column(nullable = false, defaultValue = "0")
    private int subwaytime;

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getBooktime() {
        return booktime;
    }

    public void setBooktime(int booktime) {
        this.booktime = booktime;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCitytime() {
        return citytime;
    }

    public void setCitytime(int citytime) {
        this.citytime = citytime;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getOpenlock() {
        return openlock;
    }

    public void setOpenlock(int openlock) {
        this.openlock = openlock;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFoodtime() {
        return foodtime;
    }

    public void setFoodtime(int foodtime) {
        this.foodtime = foodtime;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getGametime() {
        return gametime;
    }

    public void setGametime(int gametime) {
        this.gametime = gametime;
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

    public boolean isIsmobile() {
        return ismobile;
    }

    public void setIsmobile(boolean ismobile) {
        this.ismobile = ismobile;
    }

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public int getMoviestime() {
        return moviestime;
    }

    public void setMoviestime(int moviestime) {
        this.moviestime = moviestime;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getMusictime() {
        return musictime;
    }

    public void setMusictime(int musictime) {
        this.musictime = musictime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public int getSubway() {
        return subway;
    }

    public void setSubway(int subway) {
        this.subway = subway;
    }

    public int getSubwaytime() {
        return subwaytime;
    }

    public void setSubwaytime(int subwaytime) {
        this.subwaytime = subwaytime;
    }
}
