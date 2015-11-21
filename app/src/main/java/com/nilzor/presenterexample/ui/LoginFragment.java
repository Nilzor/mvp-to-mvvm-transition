package com.nilzor.presenterexample.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.viewmodels.LoginFragmentViewModel;
import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.wrappers.ToastPresenter;
import com.nilzor.presenterexample.databinding.FragmentMainBinding;

public class LoginFragment extends Fragment {
    private FragmentMainBinding mBinding;
    private LoginFragmentViewModel mViewModel;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mBinding = FragmentMainBinding.bind(view);
        ToastPresenter toastPresenter = new ToastPresenter(getActivity().getApplicationContext());
        mViewModel = new LoginFragmentViewModel(toastPresenter, getResources());
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
