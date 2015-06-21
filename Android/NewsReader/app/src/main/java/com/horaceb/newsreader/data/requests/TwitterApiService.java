package com.horaceb.newsreader.data.requests;


import java.util.List;

import com.horaceb.newsreader.data.model.Tweet;
import com.horaceb.newsreader.data.model.TwitterTokenType;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

public interface TwitterApiService {

    String API_URL = "https://api.twitter.com";

    String CONSUMER_KEY = "6NMejZVm12IUgGoT9BE4t3rCa";
    String CONSUMER_SECRET = "gBm4ytfYFazS2HK1zb4Xi2Z4UZt5IOaFhf1r1LHxe1uCAT4RCs";
    String ACCESS_TOKEN = "206177044-CvJjpHLXsDLKlVAhkm5ZuVEm7vpvY5DRj5YRj5lU";
    String ACCESS_TOKEN_SECRET = "206177044-XUbkkHwaYr2BvktqpxQ5qeHCR0gyjv16jIVnHWoBuA3we";

    String BEARER_TOKEN_CREDENTIALS = CONSUMER_KEY + ":" + CONSUMER_SECRET;

    @GET("/1.1/statuses/user_timeline.json")
    List<Tweet> fetchUserTimeline(
            @Header("Authorization") String authorization,
            @Query("count") final int count,
            @Query("screen_name") final String screenName,
            Callback<Object> response);

    @FormUrlEncoded
    @POST("/oauth2/token")
    void getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            Callback<TwitterTokenType> response
    );

}
