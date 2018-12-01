package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.meeting.Weather;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface WeatherAPIService {

    @GET("weather/")
    Observable<Response<Weather>> getWeather();

}
