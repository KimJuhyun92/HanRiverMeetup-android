package com.depromeet.hanriver.hanrivermeetup.model.meeting;

public class Weather {
    private int weather_seq;
    private String t1h;
    private String sky;
    private String pty;
    private String tmn;
    private String tmx;

    public int getWeather_seq() {
        return weather_seq;
    }

    public void setWeather_seq(int weather_seq) {
        this.weather_seq = weather_seq;
    }

    public String getT1h() {
        return t1h;
    }

    public void setT1h(String t1h) {
        this.t1h = t1h;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
    }

    public String getTmn() {
        return tmn;
    }

    public void setTmn(String tmn) {
        this.tmn = tmn;
    }

    public String getTmx() {
        return tmx;
    }

    public void setTmx(String tmx) {
        this.tmx = tmx;
    }
}

