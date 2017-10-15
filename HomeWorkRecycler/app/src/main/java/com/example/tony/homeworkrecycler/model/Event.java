package com.example.tony.homeworkrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Tony on 25.09.2017.
 */

public class Event implements Parcelable {
    private String eventDescription;

    private String name;

    private int photoId;

    protected Event(Parcel in) {
        eventDescription = in.readString();
        name = in.readString();
        photoId = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Event(int id, int photoId, String eventDescription, String name, Date date) {
        this.eventDescription = eventDescription;
        this.name = name;
        this.photoId = photoId;
        this.id = id;
        this.date = date;

    }

    public String getEventDescription() {

        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventDescription);
        dest.writeString(name);
        dest.writeInt(photoId);
        dest.writeInt(id);
    }
}
