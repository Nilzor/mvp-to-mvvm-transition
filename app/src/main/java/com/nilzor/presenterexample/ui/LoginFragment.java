package com.nilzor.presenterexample.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.App;
import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.FragmentLoginBinding;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;
import com.nilzor.presenterexample.viewmodels.LoginFragmentViewModel;

public class LoginFragment extends Fragment {
    private static final String TAG = "MVVM";
    private FragmentLoginBinding mBinding;
    private LoginFragmentViewModel mViewModel;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mBinding = FragmentLoginBinding.bind(view);
        mViewModel = getOrCreateViewModel();
        mBinding.setData(mViewModel);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ensureModelDataIsLodaded();
    }

    private void ensureModelDataIsLodaded() {
        if (!mViewModel.isLoadStarted()) {
            mViewModel.loadAsync();
        }
    }

    public LoginFragmentViewModel getOrCreateViewModel() {
        LoginFragmentViewModel vm = App.ViewModels.get(LoginFragmentViewModel.class);
        if (vm == null) {
            Activity activity = getActivity();
            ToastPresenter toastPresenter = new ToastPresenter(activity.getApplicationContext());
            AppNavigator navigator = new AppNavigator(activity);
            vm = new LoginFragmentViewModel(navigator, toastPresenter, getResources());
            Log.d(TAG, "Created viewmodel for LoginFragment");
            App.ViewModels.put(vm);
        } else {
            Log.d(TAG, "Reusing viewmmodel for LoginFragment");
        }
        return vm;
    }
}
