package com.nilzor.presenterexample.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastPresenter {
    private final Context mContext;

    /** @param appContext ApplicationContext. Will not accept contet tied to activity or fragment in order to avoid memory leaks */
    public ToastPresenter(Context appContext) {
        if (appContext != appContext.getApplicationContext()) throw new IllegalArgumentException("Context must be appContext");
        mContext = appContext;
    }

    public void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }
}
