package com.nilzor.presenterexample.helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextHelper {
    public interface AfterTextChangedInterface {
        void afterTextChanged();
    }

    public static void bindOnChangeListener(EditText editText, AfterTextChangedInterface afterTextChangedListener) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                afterTextChangedListener.afterTextChanged();
            }
        });
    }
}
