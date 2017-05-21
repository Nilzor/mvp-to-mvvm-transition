package com.nilzor.presenterexample;

import java.util.HashMap;

/**
 * A holder for view models to allow them to survive configuration changes
 */
public class ViewModelHolder {
    HashMap<Class, Object> map = new HashMap<>();

    public void put(Object viewModel) {
        map.put(viewModel.getClass(), viewModel);
    }

    public <T> T get(Class<T> clazz) {
        Object vm = map.get(clazz);
        if (vm == null) return null;
        return (T) vm;
    }
}


