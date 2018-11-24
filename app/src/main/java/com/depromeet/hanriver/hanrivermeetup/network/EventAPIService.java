package com.depromeet.hanriver.hanrivermeetup.network;

import com.depromeet.hanriver.hanrivermeetup.model.map.TourEventInfo;
import com.depromeet.hanriver.hanrivermeetup.model.timeline.EventVO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface EventAPIService {
    @GET("events")
    Observable<Response<List<EventVO>>> getEvents();

    @GET("tour/events/recently")
    Observable<Response<List<TourEventInfo>>> getTourEvents();
}
