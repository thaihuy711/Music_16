package com.framgia.music_16.utils;

import android.support.annotation.StringDef;

import com.framgia.music_16.BuildConfig;

public final class Constant {

    @StringDef({Genres.ALTERNATIVEROCK, Genres.AMBIENT, Genres.CLASSICAL,
            Genres.COUNTRY, Genres.ROCK})
    public @interface Genres {
        String ALTERNATIVEROCK = "alternativerock";
        String AMBIENT = "ambient";
        String CLASSICAL = "classical";
        String COUNTRY = "country";
        String ROCK = "rock";
    }

    public static final String BASE_URL = "http://api.soundcloud.com";
    public static final String CLIENT_ID = "?client_id=" + BuildConfig.API_KEY;
    public static final String GENRES_URL = BASE_URL + "/tracks" + CLIENT_ID +
            "&linked_partitioning=1&genres=";
    public static final String ARTIST_URL = BASE_URL + "/users" + CLIENT_ID;
    public static final String SEARCH_URL = BASE_URL + "/tracks" + CLIENT_ID + "&q=";
    public static final String NEXT_HREF = "next_href";
    public static final String COLLECTION = "collection";
    public static final String USER = "user";
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECT_TIMEOUT = 15000;

    private Constant() {

    }
}
