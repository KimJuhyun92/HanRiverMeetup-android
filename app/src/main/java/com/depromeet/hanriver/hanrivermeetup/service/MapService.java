package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.map.MapMarker;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.MapAPISerivce;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MapService {

    private static final MapService ourInstance = new MapService();
    private MapAPISerivce mService;

    public static MapService getInstance() {
        return ourInstance;
    }

    private MapService() {
    }

    public void setService(String token, String id) {
        mService = APIUtiles.getMapService(token, id);
    }

    public Observable<Response<List<MapMarker>>> getMarkerList(int marker_seq) {
        return mService.getMarkerList(marker_seq)
                .subscribeOn(Schedulers.io())
                .doOnNext(res -> {
                    if (res.code() == HttpsURLConnection.HTTP_OK) {
                        res.body();
                    }
                });
    }
}
