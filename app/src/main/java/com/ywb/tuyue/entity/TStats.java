package com.ywb.tuyue.entity;

import org.litepal.crud.LitePalSupport;

/**
 * @Author soonphe
 * @Date 2018-08-30 15:26
 * @Descprition
 */

public class TStats extends LitePalSupport {


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

    private int book;
    private int booktime;
    private int city;
    private int citytime;
    private String createdate;
    private int food;
    private int foodtime;
    private int game;
    private int gametime;
    private int id;
    private String imcode;
    private boolean ismobile;
    private int movies;
    private int moviestime;
    private int music;
    private int musictime;
    private String phone;
    private String regtime;
    private int subway;
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
