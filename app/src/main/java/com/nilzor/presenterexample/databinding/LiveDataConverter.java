package com.nilzor.presenterexample.databinding;

import android.arch.lifecycle.LiveData;
import android.databinding.BindingConversion;

public class LiveDataConverter {
    @BindingConversion
    public static String fromLiveData(LiveData<String> data) {
        return data.getValue();
    }
}
