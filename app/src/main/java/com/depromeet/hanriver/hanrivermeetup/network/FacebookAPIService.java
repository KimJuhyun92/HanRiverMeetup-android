package com.depromeet.hanriver.hanrivermeetup.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FacebookAPIService {
    @GET("{userID}/picture?type=large")
    Observable<ResponseBody> getProfileById(@Path("userID") String userID);
}
