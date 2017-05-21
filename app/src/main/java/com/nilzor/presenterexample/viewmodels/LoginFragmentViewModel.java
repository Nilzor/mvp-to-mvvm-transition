package com.nilzor.presenterexample.viewmodels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.res.Resources;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.view.View;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.LiveDataField;
import com.nilzor.presenterexample.databinding.TwoWayBoundBoolean;
import com.nilzor.presenterexample.databinding.TwoWayBoundString;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;

import java.util.Random;

public class LoginFragmentViewModel extends ViewModel {
    public LiveDataField<String> numberOfUsersLoggedIn = new LiveDataField<>();
    public TwoWayBoundBoolean isExistingUserChecked = new TwoWayBoundBoolean();
    public ObservableInt emailBlockVisibility = new ObservableInt();
    public TwoWayBoundString loginOrCreateButtonText = new TwoWayBoundString();
    public TwoWayBoundString username = new TwoWayBoundString("");
    public TwoWayBoundString password = new TwoWayBoundString("");
    public TwoWayBoundString email = new TwoWayBoundString("");
    public ObservableField<String> passwordError = new ObservableField<>(); // Todo: Fix TwoWayBoundString to work here
    private boolean mIsLoadStarted;
    private AppNavigator mAppNavigator;
    private ToastPresenter mToastPresenter;
    private Resources mResources;

    public LoginFragmentViewModel(AppNavigator appNavigator, ToastPresenter toastPresenter, Resources resources, LifecycleOwner lifecycleOwner) {
        mAppNavigator = appNavigator;
        mToastPresenter = toastPresenter;
        mResources = resources; // You might want to abstract this for testability
        numberOfUsersLoggedIn = new LiveDataField<>();
        setInitialState();
        updateDependentViews();
        hookUpDependencies();
    }

    public boolean isLoadStarted() {
        return mIsLoadStarted;
    }

    private void setInitialState() {
        numberOfUsersLoggedIn.set("...");
        isExistingUserChecked.set(true);
    }

    private void hookUpDependencies() {
        isExistingUserChecked.addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                updateDependentViews();
            }
        });
    }

    public void updateDependentViews() {
        resetErrors();
        if (isExistingUserChecked.get()) {
            emailBlockVisibility.set(View.GONE);
            loginOrCreateButtonText.set(mResources.getString(R.string.log_in));
        }
        else {
            emailBlockVisibility.set(View.VISIBLE);
            loginOrCreateButtonText.set(mResources.getString(R.string.create_user));
        }
    }

    public void loadAsync() {
        mIsLoadStarted = true;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Simulating some asynchronous task fetching data from a remote server
                try {Thread.sleep(2000);} catch (Exception ex) {};
                numberOfUsersLoggedIn.set("" + new Random().nextInt(1000));
                return null;
            }
        }.execute((Void) null);
    }

    public void logInClicked(View view) {
        boolean isValid = validateInput();
        if (isValid) {
            attemptLoginOrCreate();
        }
    }

    /** Validate input data */
    public boolean validateInput() {
        resetErrors();
        String newPasswordError = null;
        if (!isExistingUserChecked.get()) {
            if (password.get().length() < 8) {
                newPasswordError = "Password must be at least 8 characters long";
            }
            else {
            }
        }
        passwordError.set(newPasswordError);
        return newPasswordError == null;
    }

    public void attemptLoginOrCreate() {
        // Illustrating the need for calling back to the view though testable interfaces.
        boolean ok = true;
        if (isExistingUserChecked.get()) {
            if (!password.get().contains("a")) {
                mToastPresenter.showShortToast("Invalid username or password");
                ok = false;
            }
        }
        if (ok) {
            mAppNavigator.gotoMainScreen();
        }
    };

    private void resetErrors() {
        passwordError.set(null);
    }

    public static class Factory implements ViewModelProvider.Factory {
        private LifecycleOwner mLifecycleOwner;
        private AppNavigator mAppNavigator;
        private ToastPresenter mToastPresenter;
        private Resources mResources;

        public Factory(AppNavigator appNavigator, ToastPresenter toastPresenter, Resources resources) {
            mAppNavigator = appNavigator;
            mToastPresenter = toastPresenter;
            mResources = resources;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new LoginFragmentViewModel(mAppNavigator, mToastPresenter, mResources, mLifecycleOwner);
        }
    }
}
