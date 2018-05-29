package com.framgia.music_16.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Artist implements Parcelable {
    private String mAvartarUrl;
    private int mId;
    private String mUsername;

    public String getAvartarUrl() {
        return mAvartarUrl;
    }

    public void setAvartarUrl(String avartarUrl) {
        mAvartarUrl = avartarUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public static Creator<Artist> getCREATOR() {
        return CREATOR;
    }

    protected Artist(Parcel in) {
        mAvartarUrl = in.readString();
        mId = in.readInt();
        mUsername = in.readString();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAvartarUrl);
        dest.writeInt(mId);
        dest.writeString(mUsername);
    }

    class ArtistEntry {
        public static final String USER_ID = "user_id";
        public static final String USER_NAME = "username";
        public static final String AVATAR_URL = "avatar_url";
    }
}
