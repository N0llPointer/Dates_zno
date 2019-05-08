package com.nollpointer.dates_zno.app;

import androidx.multidex.MultiDexApplication;

import com.flurry.android.FlurryAgent;
import com.nollpointer.dates_zno.api.WikipediaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends MultiDexApplication {
    private static WikipediaApi wiki;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .withCaptureUncaughtExceptions(true)
                .build(this, "JY3HGNNDK6RQJ9NZB43X");

        retrofit = new Retrofit.Builder()
                .baseUrl("https://ru.wikipedia.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        wiki = retrofit.create(WikipediaApi.class);
    }

    public static WikipediaApi getApi() {
        return wiki;
    }
}
