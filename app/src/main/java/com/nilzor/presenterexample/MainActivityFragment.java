package com.nilzor.presenterexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.hannesdorfmann.mosby.mvp.MvpView;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivityFragment extends MvpFragment<MainActivityFragment, MainPresenter> implements MvpView {
    @InjectView(R.id.username)
    TextView mUsername;

    @InjectView(R.id.password)
    TextView mPassword;

    @InjectView(R.id.newUserRb)
    RadioButton mNewUserRb;

    @InjectView(R.id.returningUserRb)
    RadioButton mReturningUserRb;

    @InjectView(R.id.loginOrCreateButton)
    Button mLoginOrCreateButton;

    @InjectView(R.id.email_block)
    ViewGroup mEmailBlock;

    @InjectView(R.id.loggedInUserCount)
    TextView mLoggedInUserCount;

    public MainActivityFragment() {
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachEventListeners();
    }

    private void attachEventListeners() {
        mNewUserRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateDependentViews();
            }
        });
        mReturningUserRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateDependentViews();
            }
        });
    }

    /** Prepares the initial state of the view upon startup */
    public void setInitialState() {
        mReturningUserRb.setChecked(true);
        updateDependentViews();
    }

    /** Shows/hides email field and sets correct text of login button depending on state of radio buttons */
    public void updateDependentViews() {
        if (mReturningUserRb.isChecked()) {
            mEmailBlock.setVisibility(View.GONE);
            mLoginOrCreateButton.setText(R.string.log_in);
        }
        else {
            mEmailBlock.setVisibility(View.VISIBLE);
            mLoginOrCreateButton.setText(R.string.create_user);
        }
    }

    public void setNumberOfLoggedIn(int numberOfLoggedIn) {
        mLoggedInUserCount.setText(""  + numberOfLoggedIn);
    }

    @OnClick(R.id.loginOrCreateButton)
    public void loginOrCreate() {
        if (mNewUserRb.isChecked()) {
            Toast.makeText(getActivity(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
