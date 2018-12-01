package com.depromeet.hanriver.hanrivermeetup.service;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Weather;
import com.depromeet.hanriver.hanrivermeetup.network.APIUtiles;
import com.depromeet.hanriver.hanrivermeetup.network.WeatherAPIService;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherService {
    private static final WeatherService ourInstance = new WeatherService();
    private WeatherAPIService mService;

    public static WeatherService getInstance(){
        return ourInstance;
    }

    private WeatherService(){}

    public void setService(String token,String id){
        mService = APIUtiles.getWeatherService(token,id);
    }

    public Observable<Response<Weather>> getWeather(){
        return mService.getWeather()
                .subscribeOn(Schedulers.io())
                .doOnNext( res -> {
            if(res.code() == HttpsURLConnection.HTTP_OK){
                res.body();
            }
        });
    }

}

