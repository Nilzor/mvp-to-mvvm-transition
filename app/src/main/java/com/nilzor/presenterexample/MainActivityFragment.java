package com.nilzor.presenterexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nilzor.presenterexample.databinding.FragmentMainBinding;
import com.nilzor.presenterexample.helpers.EditTextHelper;

public class MainActivityFragment extends Fragment {
    private FragmentMainBinding mBinding;
    private MainModel mViewModel;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mBinding = FragmentMainBinding.bind(view);
        ToastPresenter toastPresenter = new ToastPresenter(getActivity().getApplicationContext());
        mViewModel = new MainModel(toastPresenter, getResources());
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
