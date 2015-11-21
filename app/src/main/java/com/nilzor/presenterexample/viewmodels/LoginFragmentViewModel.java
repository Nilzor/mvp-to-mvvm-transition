package com.nilzor.presenterexample.viewmodels;

import android.content.res.Resources;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.view.View;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;

import java.util.Random;

public class LoginFragmentViewModel {
    public ObservableField<String> numberOfUsersLoggedIn = new ObservableField<>();
    public ObservableField<Boolean> isExistingUserChecked = new ObservableField<>();
    public ObservableField<Integer> emailBlockVisibility = new ObservableField<>();
    public ObservableField<String> loginOrCreateButtonText = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();
    private boolean mIsLoaded;
    private AppNavigator mAppNavigator;
    private ToastPresenter mToastPresenter;
    private Resources mResources;

    public LoginFragmentViewModel(AppNavigator appNavigator, ToastPresenter toastPresenter, Resources resources) {
        mAppNavigator = appNavigator;
        mToastPresenter = toastPresenter;
        mResources = resources; // You might want to abstract this for testability
        setInitialState();
        updateDependentViews();
        hookUpDependencies();
    }
    public boolean isLoaded() {
        return mIsLoaded;
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
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Simulating some asynchronous task fetching data from a remote server
                try {Thread.sleep(2000);} catch (Exception ex) {};
                numberOfUsersLoggedIn.set("" + new Random().nextInt(1000));
                mIsLoaded = true;
                return null;
            }
        }.execute((Void) null);
    }

    public void logInClicked() {
        boolean isValid = validateInput();
        if (isValid) {
            attemptLoginOrCreate();
        }
    }

    /** Validate input data */
    public boolean validateInput() {
        resetErrors();
        if (!isExistingUserChecked.get()) {
            if (password.get().length() < 8) {
                passwordError.set("Password must be at least 8 characters long");
            }
            else {
            }
        }
        return passwordError.get() == null;
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
}
