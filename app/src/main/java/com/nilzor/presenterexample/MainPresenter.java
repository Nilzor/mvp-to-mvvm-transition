package com.nilzor.presenterexample;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public class MainPresenter implements MvpPresenter<MainActivityFragment> {
    @Override
    public void attachView(MainActivityFragment view) {
        view.setInitialState();
    }

    @Override
    public void detachView(boolean retainInstance) {

    }
}
