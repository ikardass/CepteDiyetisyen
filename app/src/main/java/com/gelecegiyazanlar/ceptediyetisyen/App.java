package com.gelecegiyazanlar.ceptediyetisyen;

import android.app.Application;

import com.google.firebase.FirebaseApp;


public class App extends Application {
    private static App androidApp;

    public static App getInstance() {
        return androidApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        androidApp = this;
        FirebaseApp.initializeApp(this);
}
}
