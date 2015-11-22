package com.nilzor.presenterexample.viewmodels;

import android.content.res.Resources;

import com.nilzor.presenterexample.helpers.AppNavigator;
import com.nilzor.presenterexample.helpers.ToastPresenter;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginFragmentViewModelTest {

    @Captor
    ArgumentCaptor<String> mStringCaptor = ArgumentCaptor.forClass(String.class);

    // Method name format following Roy Osherove's structure of:
    //    UnitOfWork_StateUnderTest_ExpectedBehavior

    @Test
    public void testValidate_newUserShortPassword_errorSet() throws Exception {
        LoginFragmentViewModel model = createBlankViewModel();
        model.password.set("Asdf");
        model.isExistingUserChecked.set(false);
        model.validateInput();
        assertNotNull(model.passwordError.get());
    }

    @Test
    public void testValidate_newUserValidPassword_noError() throws Exception {
        LoginFragmentViewModel model = createBlankViewModel();
        model.password.set("Some loinger password");
        model.isExistingUserChecked.set(false);
        model.validateInput();
        assertNull(model.passwordError.get());
    }

    @Test
    public void loginClicked_existingUserWrongCredentials_errorToastShown() {
        ToastPresenter toast = mock(ToastPresenter.class);
        Resources resources = mock(Resources.class);
        LoginFragmentViewModel model = new LoginFragmentViewModel(mock(AppNavigator.class), toast, resources);
        model.isExistingUserChecked.set(true);
        model.username.set("someGuy2006");
        model.password.set("wrong");
        model.logInClicked(null);
        verify(toast).showShortToast(mStringCaptor.capture());
        assertEquals("Invalid username or password", mStringCaptor.getValue());
    }

    private LoginFragmentViewModel createBlankViewModel() {
        ToastPresenter toast = mock(ToastPresenter.class);
        Resources resources = mock(Resources.class);
        LoginFragmentViewModel model = new LoginFragmentViewModel(mock(AppNavigator.class), toast, resources);
        return model;
    }
}