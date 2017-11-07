package com.tony.d.alarmclock2.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tony on 29.10.2017.
 */

public class AlarmItem implements Parcelable {

    private String time;

    private int isSwitchedOn;

    private String description;

    private int idd;

    public AlarmItem() {
    }

    public AlarmItem(String time, String description, int isSwitchedOn,int idd) {
        this.time = time;
        this.isSwitchedOn = isSwitchedOn;
        this.description = description;
        this.idd = idd;
    }


    protected AlarmItem(Parcel in) {
        time = in.readString();
        isSwitchedOn = in.readInt();
        description = in.readString();
        idd = in.readInt();
    }

    public static final Creator<AlarmItem> CREATOR = new Creator<AlarmItem>() {
        @Override
        public AlarmItem createFromParcel(Parcel in) {
            return new AlarmItem(in);
        }

        @Override
        public AlarmItem[] newArray(int size) {
            return new AlarmItem[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isSwitchedOn() {
        return isSwitchedOn;
    }

    public void setSwitchedOn(int switchedOn) {
        isSwitchedOn = switchedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsSwitchedOn() {
        return isSwitchedOn;
    }

    public void setIsSwitchedOn(int isSwitchedOn) {
        this.isSwitchedOn = isSwitchedOn;
    }

    public int getIdd() {
        return idd;
    }

    public void setId(int idd) {
        this.idd = idd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(time);
        parcel.writeInt(isSwitchedOn);
        parcel.writeString(description);
        parcel.writeInt(idd);
    }
}
