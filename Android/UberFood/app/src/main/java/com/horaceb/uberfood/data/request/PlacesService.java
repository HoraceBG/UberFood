package com.horaceb.uberfood.data.request;

import com.horaceb.uberfood.data.model.Places;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class PlacesService implements RequestInterceptor {

    private PlaceRequestService webService;

    // TODO: Look up api key from a properties file
    private static final String API_KEY_PARAM = "key";
    private static final String API_KEY = "AIzaSyDbJ1tltsmXxE-M5e4a6SYTPPNlxXWrTgA";
    String PLACES_API_URL = "https://maps.googleapis.com/maps/api/place";

    public PlacesService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(PLACES_API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(this)
                .build();

        webService = restAdapter.create(PlaceRequestService.class);
    }

    public PlaceRequestService getWebService() {
        return webService;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(API_KEY_PARAM, API_KEY);
    }

    public interface PlaceRequestService {

        /**
         * opennow = true
         * rankby = prominence/ distance
         * types = restaurant
         * <p/>
         * radius (distance in metres)
         * minprice/ maxprice (0 - 4)
         *
         * @return
         */


        @GET("/search/json?types=restaurant")
        void getRestaurants(@Query("location") final String location, @Query("radius") final int radius, final Callback<Places> callback);

        @GET("/search/json?types=restaurant")
        Places getRestaurants(@Query("location") final String location, @Query("radius") final int radius);

        @GET("/details")
        void getRestaurantDetails(@Query("placeId") final String placeId);
    }

}
