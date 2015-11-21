package com.nilzor.presenterexample.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nilzor.presenterexample.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void logInClicked(View view) {
        ((LoginFragment) getFragmentManager().findFragmentById(R.id.main_login_fragment)).loginClicked();
    }
}
