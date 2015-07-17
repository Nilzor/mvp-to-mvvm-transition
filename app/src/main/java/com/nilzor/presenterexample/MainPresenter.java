package com.nilzor.presenterexample;

import android.os.Handler;
import android.os.Message;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public class MainPresenter implements MvpPresenter<MainActivityFragment> {
    MainModel mModel;
    private MainActivityFragment mView;

    public MainPresenter() {
        mModel = new MainModel();
    }

    @Override
    public void attachView(MainActivityFragment view) {
        mView = view;
        view.setInitialState();
        updateViewFromModel();
        ensureModelDataIsLoaded();
    }

    @Override
    public void detachView(boolean retainInstance) {
        mView = null;
    }

    private void ensureModelDataIsLoaded() {
        if (!mModel.isLoaded()) {
            mModel.loadAsync(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    updateViewFromModel();
                    return true;
                }
            });
        }
    }

    /** Notifies the views of the current value of "numberOfUsersLoggedIn", if any */
    private void updateViewFromModel() {
        if (mView != null && mModel.isLoaded()) {
            mView.setNumberOfLoggedIn(mModel.numberOfUsersLoggedIn);
        }
    }
}
