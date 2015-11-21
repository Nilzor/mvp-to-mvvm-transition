package com.nilzor.presenterexample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.FragmentLoginBinding;
import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;
import com.nilzor.presenterexample.viewmodels.LoginFragmentViewModel;

public class LoginFragment extends Fragment {
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
        mViewModel = new LoginFragmentViewModel(navigator, toastPresenter, getResources());
        mBinding.setData(mViewModel);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ensureModelDataIsLodaded();
    }

    private void ensureModelDataIsLodaded() {
        if (!mViewModel.isLoaded()) {
            mViewModel.loadAsync();
        }
    }
}
