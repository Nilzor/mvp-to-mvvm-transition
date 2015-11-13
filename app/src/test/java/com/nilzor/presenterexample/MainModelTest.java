package com.nilzor.presenterexample;

import android.content.res.Resources;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MainModelTest {
    @Captor
    ArgumentCaptor<String> mStringCaptor = ArgumentCaptor.forClass(String.class);

    // Method name format following Roy Osherove's structure of:
    //    UnitOfWork_StateUnderTest_ExpectedBehavior

    @Test
    public void loginClicked_existingUserWrongCredentials_errorToastShown() {
        ToastPresenter toast = mock(ToastPresenter.class);
        Resources resources = mock(Resources.class);
        MainModel model = new MainModel(toast, resources);
        model.isExistingUserChecked.set(true);
        model.username.set("someGuy2006");
        model.password.set("definitely not the correct password");
        model.logInClicked(null);
        verify(toast).showShortToast(mStringCaptor.capture());
        assertEquals("Invalid username or password", mStringCaptor.getValue());
    }
}
