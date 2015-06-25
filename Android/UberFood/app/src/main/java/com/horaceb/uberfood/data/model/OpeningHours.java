package com.horaceb.uberfood.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OpeningHours implements Parcelable {

    private String openNow;

    private String[] weekdayText;

    public String getOpenNow() {
        return openNow;
    }

    public void setOpenNow(String open_now) {
        this.openNow = open_now;
    }

    public String[] getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(String[] weekdayText) {
        this.weekdayText = weekdayText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openNow);
        dest.writeStringArray(this.weekdayText);
    }

    public OpeningHours() {
    }

    private OpeningHours(Parcel in) {
        this.openNow = in.readString();
        this.weekdayText = in.createStringArray();
    }

    public static final Parcelable.Creator<OpeningHours> CREATOR = new Parcelable.Creator<OpeningHours>() {
        public OpeningHours createFromParcel(Parcel source) {
            return new OpeningHours(source);
        }

        public OpeningHours[] newArray(int size) {
            return new OpeningHours[size];
        }
    };
}
