package com.nilzor.presenterexample;

import android.os.AsyncTask;
import android.os.Handler;
import java.util.Random;

public class MainModel {
    public Integer numberOfUsersLoggedIn;
    private boolean mIsLoaded;
    public boolean isLoaded() {
        return mIsLoaded;
    }

    public void loadAsync(final Handler.Callback onDoneCallback) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Simulating some asynchronous task fetching data from a remote server
                try {Thread.sleep(2000);} catch (Exception ex) {};
                numberOfUsersLoggedIn = new Random().nextInt(1000);
                mIsLoaded = true;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                onDoneCallback.handleMessage(null);
            }
        }.execute((Void) null);
    }
}
