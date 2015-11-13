package com.nilzor.presenterexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nilzor.presenterexample.databinding.FragmentMainBinding;

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
