package com.horaceb.newsreader.data.model;

import com.google.gson.annotations.SerializedName;

public class TwitterTokenType {

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

}
