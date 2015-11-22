package com.nilzor.presenterexample.helpers;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.nilzor.presenterexample.R;
import com.nilzor.presenterexample.databinding.ObservableString;

public class EditTextHelper {
    @BindingAdapter({"android:text"})
    public static void bindEditText(EditText view, final ObservableString bindableString) {
        if (view.getTag(R.id.textBound) == null) {
            view.setTag(R.id.textBound, true);
            view.addTextChangedListener(new TextWatcher() {
                @Override public void afterTextChanged(Editable s) { }
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bindableString.set(s.toString());
                }
            });
        }
        String newValue = bindableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }
}
