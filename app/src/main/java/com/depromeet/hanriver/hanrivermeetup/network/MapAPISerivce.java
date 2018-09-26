package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.map.MapMarker;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MapAPISerivce {

    @GET("mapMarkers/{map_category_seq}")
    Observable<Response<List<MapMarker>>> getMarkerList(@Path("map_category_seq")int category_seq);
}
