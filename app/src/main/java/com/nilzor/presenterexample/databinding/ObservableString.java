package com.nilzor.presenterexample.databinding;

import android.databinding.BaseObservable;
import android.databinding.BindingConversion;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.io.Serializable;

/**
 * An observable class that holds a String.
 * <p>
 * Observable field classes may be used instead of creating an Observable object:
 * <pre><code>public class MyDataObject {
 *     public final ObservableString name = new ObservableString();
 *     public final ObservableField<Address> address = new ObservableField<Address>();
 * }</code></pre>
 * Fields of this type should be declared final because bindings only detect changes in the
 * field's value, not of the field itself.
 * <p>
 * This class is parcelable and serializable but callbacks are ignored when the object is
 * parcelled / serialized. Unless you add custom callbacks, this will not be an issue because
 * data binding framework always re-registers callbacks when the view is bound.
 */
public class ObservableString extends BaseObservable implements Serializable {
    static final long serialVersionUID = 1L;
    private String mValue;


    /**
     * Creates an empty observable object
     */
    public ObservableString() {
    }

    /**
     * Wraps the given object and creates an observable object
     *
     * @param value The value to be wrapped as an observable.
     */
    public ObservableString(String value) {
        mValue = value;
    }

    /**
     * @return the stored value.
     */
    public String get() {
        return mValue;
    }

    /**
     * Set the stored value.
     */
    public void set(String value) {
        if (value == null && mValue == null) return;
        if ((value == null && mValue != null) || !value.equals(mValue)) {
            mValue = value;
            notifyChange();
        }
    }

    /** Sets the stored value without notifying of change */
    public void setSilently(String value) {
        mValue = value;
    }


    @BindingConversion
    public static String observableStringToString(ObservableString string) {
        return string.get();
    }
}
