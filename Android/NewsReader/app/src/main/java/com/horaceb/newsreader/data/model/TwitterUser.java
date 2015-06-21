package com.horaceb.newsreader.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HoraceBG on 13/06/2015.
 */
public class TwitterUser {

    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_image_url")
    public String profileImageUrl;

}