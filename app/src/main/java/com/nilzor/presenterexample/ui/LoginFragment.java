package com.nilzor.presenterexample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.FragmentLoginBinding;
import com.nilzor.presenterexample.viewmodels.LoginFragmentViewModel;
import com.nilzor.presenterexample.wrappers.EditTextHelper;
import com.nilzor.presenterexample.wrappers.ToastPresenter;

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
        mViewModel = new LoginFragmentViewModel(toastPresenter, getResources());
        mBinding.setData(mViewModel);
        EditTextHelper.bindOnChangeListener(mBinding.username, this::uiToModel);
        EditTextHelper.bindOnChangeListener(mBinding.password, this::uiToModel);
        return view;
    }

    private void uiToModel() {
        mBinding.unbind();
        mViewModel.password.set(mBinding.password.getText().toString());
        mViewModel.username.set(mBinding.username.getText().toString());
        //mBinding.setData(mViewModel);
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
