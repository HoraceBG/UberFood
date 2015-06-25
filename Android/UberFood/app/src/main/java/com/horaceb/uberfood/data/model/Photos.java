package com.horaceb.uberfood.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Photos implements Parcelable {

    @SerializedName("photo_reference")
    private String photoReference;

    private String height;

    private String[] htmlAttributions;

    private String width;

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String[] getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(String[] htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photoReference);
        dest.writeString(this.height);
        dest.writeStringArray(this.htmlAttributions);
        dest.writeString(this.width);
    }

    public Photos() {
    }

    private Photos(Parcel in) {
        this.photoReference = in.readString();
        this.height = in.readString();
        this.htmlAttributions = in.createStringArray();
        this.width = in.readString();
    }

    public static final Parcelable.Creator<Photos> CREATOR = new Parcelable.Creator<Photos>() {
        public Photos createFromParcel(Parcel source) {
            return new Photos(source);
        }

        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}