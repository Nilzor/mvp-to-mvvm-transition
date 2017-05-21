package com.nilzor.presenterexample.databinding;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.util.Log;

import java.util.HashMap;

/**
 * Extending a LiveData object with Observable interface.
 * Doesn't really add much to Android Data binding since
 * @param <T>
 */
public class LiveDataField<T> extends LiveData<T> implements android.databinding.Observable {
    HashMap<OnPropertyChangedCallback, Observer> map = new HashMap<>();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        Observer observer = object -> onPropertyChangedCallback.onPropertyChanged(LiveDataField.this, 0);
        map.put(onPropertyChangedCallback, observer);
        LifecycleOwner lifecycleOwner = ProcessLifecycleOwner.get();
        // We're using the process life cycle here since the data binding framework takes care
        // of events to dead fragments / activities anyway.
        // Injecting the lifecycle from a fragment would not have given us any gain.
        observe(lifecycleOwner, observer);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        Observer observer = map.get(onPropertyChangedCallback);
        if (observer != null) {
            removeObserver(observer);
            map.remove(observer);
        }
    }

    public void set(T value) {
        super.postValue(value);
    }
}
