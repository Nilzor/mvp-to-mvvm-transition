package com.nilzor.presenterexample.databinding;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.nilzor.presenterexample.R;

import java.io.Serializable;

/**
 * An observable class that holds a Boolean, including event listener logic that will
 * set data back to the observable upon change in the UI layer.
 * @implNote  Would have extend ObservableBoolean, if it wasn't for the fact that we're
 * unable to override the 'android:checked' BindingAdapter for that class type */
public class TwoWayBoundBoolean extends BaseObservable implements Parcelable, Serializable {
    private boolean mValue;

    /**
     * Creates an TwoWayBoundBoolean with the given initial value.
     *
     * @param value the initial value for the ObservableBoolean
     */
    public TwoWayBoundBoolean(boolean value) {
        mValue = value;
    }

    /**
     * Creates an TwoWayBoundBoolean with the initial value of <code>false</code>.
     */
    public TwoWayBoundBoolean() { }

    /**
     * @return the stored value.
     */
    public boolean get() {
        return mValue;
    }

    /**
     * Set the stored value.
     */
    public void set(boolean value) {
        if (value != mValue) {
            mValue = value;
            notifyChange();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mValue ? 1 : 0);
    }

    public static final Parcelable.Creator<ObservableBoolean> CREATOR = new Parcelable.Creator<ObservableBoolean>() {
        @Override
        public ObservableBoolean createFromParcel(Parcel source) {
            return new ObservableBoolean(source.readInt() == 1);
        }

        @Override
        public ObservableBoolean[] newArray(int size) {
            return new ObservableBoolean[size];
        }
    };


    // CompoundButton covers CheckBox, ToggleButton, RadioButton and Switch
    @BindingAdapter("android:checked")
    public static void bindRadioButton(CompoundButton view, final TwoWayBoundBoolean observableBoolean) {
        if (view.getTag(R.id.textBound) == null) {
            view.setTag(R.id.textBound, true);
            view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    observableBoolean.set(isChecked);
                }
            });
        }
        view.setChecked(observableBoolean.get());
    }
}
