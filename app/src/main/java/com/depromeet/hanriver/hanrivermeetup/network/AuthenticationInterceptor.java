package com.depromeet.hanriver.hanrivermeetup.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;
    private String id;

    public AuthenticationInterceptor(String token,String id) {
        this.authToken = token;
        this.id = id;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("hangang_token", authToken);

        builder = builder
                .header("user_id", id);

        Request request = builder.build();
        return chain.proceed(request);
    }
}