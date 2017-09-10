package com.nilzor.presenterexample.viewmodels;

import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.AsyncTask;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.ObservableString;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;

import java.util.Random;

public class LoginFragmentViewModel {
    public ObservableField<String> numberOfUsersLoggedIn = new ObservableField<>();
    public ObservableBoolean isExistingUserChecked = new ObservableBoolean();
    public ObservableInt emailBlockVisibility = new ObservableInt();
    public ObservableString loginOrCreateButtonText = new ObservableString();
    public ObservableString username = new ObservableString("");
    public ObservableString password = new ObservableString("");
    public ObservableString email = new ObservableString("");
    public ObservableField<String> passwordError = new ObservableField<>(); // Todo: Fix ObservableString to work here
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

    public void onExistingOrNewUserChanged(View view, boolean newState) {
        isExistingUserChecked.set(newState);
        updateDependentViews();
    }

    public void onPasswordChanged(Editable e) {
        password.setSilently(e.toString());
    }

    public void onUsernameChanged(Editable e) {
        username.setSilently(e.toString());
    }

    public void onEmailChanged(Editable e) {
        email.setSilently(e.toString());
    }

    public void onEmailTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("Test", "Email text changed. Now: " + s);
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
}
