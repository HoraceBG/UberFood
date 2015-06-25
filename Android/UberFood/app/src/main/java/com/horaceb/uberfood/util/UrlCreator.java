package com.horaceb.uberfood.util;

import android.content.res.Resources;
import android.net.Uri;

import com.horaceb.uberfood.R;

/**
 * Builds a URL that we can use to get a photo from the Google maps API.
 * <p/>
 * Created by HoraceBG on 24/06/2015.
 */
public class UrlCreator {

    public static String createPhotoUrl(final Resources resources, final String photoReference) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("maps.googleapis.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("place")
                .appendPath("photo")
                .appendQueryParameter("photoreference", photoReference)
                .appendQueryParameter("maxwidth", "800")
                .appendQueryParameter("key", resources.getString(R.string.google_places_key))
                .build();
        return builder.build().toString();
    }

    public static String createStaticMapUrl(final String lat, final String lng) {
        String latLong = String.format("%s,%s", lat, lng);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("maps.google.com")
                .appendPath("maps")
                .appendPath("api")
                .appendPath("staticmap")
                .appendQueryParameter("center", latLong)
                .appendQueryParameter("zoom", "15")
                .appendQueryParameter("size", "800x400")
                .appendQueryParameter("markers", latLong)
                .build();
        return builder.build().toString();
    }

    public static String createGoogleMapsUrl(final String lat, final String lng) {
        String latLong = String.format("@%s,%s,20z", lat, lng);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.google.com")
                .appendPath("maps")
                .build();
        return builder.build().toString().concat(latLong);
    }

//    public static String createUberUrl(final String lat, final String lng) {
////        uber://?action=setPickup&pickup=my_location
//        Uri.Builder builder = new Uri.Builder();
//        builder.scheme("uber")
//                .appendQueryParameter("pickup[latitude]", lat)
//                .appendQueryParameter("pickup[longitude]", lng)
//
//    }
}
