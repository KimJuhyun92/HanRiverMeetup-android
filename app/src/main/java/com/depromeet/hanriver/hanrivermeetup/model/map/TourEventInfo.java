package com.depromeet.hanriver.hanrivermeetup.model.map;

public class TourEventInfo {
    private String title;
    private String addr;
    private String image;
    private String hompage;
    private String tel;
    private double mapx;
    private double mapy;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHompage() {
        return hompage;
    }

    public void setHompage(String hompage) {
        this.hompage = hompage;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
