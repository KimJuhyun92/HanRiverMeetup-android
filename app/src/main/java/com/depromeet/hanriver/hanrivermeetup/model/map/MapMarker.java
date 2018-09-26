package com.depromeet.hanriver.hanrivermeetup.model.map;

public class MapMarker {
    private String map_seq;
    private String lat;
    private String lng;
    private String map_category_seq;

    public String getMap_seq() {
        return map_seq;
    }

    public void setMap_seq(String map_seq) {
        this.map_seq = map_seq;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMap_category_seq() {
        return map_category_seq;
    }

    public void setMap_category_seq(String map_category_seq) {
        this.map_category_seq = map_category_seq;
    }
}