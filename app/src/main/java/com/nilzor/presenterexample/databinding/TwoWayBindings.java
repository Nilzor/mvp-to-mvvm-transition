package com.nilzor.presenterexample.databinding;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.nilzor.presenterexample.R;

@SuppressWarnings("unused")
public class TwoWayBindings {

    @BindingConversion
    public static String observableStringToString(ObservableString string) {
        return string.get();
    }

    //@BindingAdapter({"android:text"})
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
