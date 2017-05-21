package com.nilzor.presenterexample.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.FragmentLoginBinding;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;
import com.nilzor.presenterexample.viewmodels.LoginFragmentViewModel;

public class LoginFragment extends LifecycleFragment {
    private FragmentLoginBinding mBinding;
    private LoginFragmentViewModel mViewModel;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mBinding = FragmentLoginBinding.bind(view);
        ToastPresenter toastPresenter = new ToastPresenter(getActivity().getApplicationContext());
        AppNavigator navigator = new AppNavigator(getActivity());

        LoginFragmentViewModel.Factory factory = new LoginFragmentViewModel.Factory(navigator, toastPresenter, getResources());
        mViewModel = ViewModelProviders.of(this, factory).get(LoginFragmentViewModel.class);
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
}
