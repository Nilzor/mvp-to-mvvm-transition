package com.nilzor.presenterexample.helpers;

import android.content.Context;
import android.content.Intent;

import com.nilzor.presenterexample.ui.MainActivity;

public class AppNavigator {
    private final Context mContext;

    public AppNavigator(Context context) {
        mContext = context;
    }

    public void gotoMainScreen() {
        mContext.startActivity(new Intent(mContext, MainActivity.class));
    }
}
