package com.nilzor.presenterexample;

import android.app.Application;

public class App extends Application {
    public static ViewModelHolder ViewModels;

    @Override
    public void onCreate() {
        super.onCreate();
        ViewModels = new ViewModelHolder();
    }
}
