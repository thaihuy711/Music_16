package com.framgia.music_16.utils;

import com.framgia.music_16.BuildConfig;

public final class Constant {

    public static final String BASE_URL = "http://api.soundcloud.com";
    public static final String CLIENT_ID = "?client_id=" + BuildConfig.API_KEY;
    public static final String GENRES_URL = BASE_URL + "/tracks" + CLIENT_ID + "&genres=";
    public static final String ARTIST_URL = BASE_URL + "/users" + CLIENT_ID;
    public static final String SEARCH_URL = BASE_URL + "/tracks" + CLIENT_ID + "&q=";

    private Constant() {

    }
}
