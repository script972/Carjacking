package com.script972.carjacking.helpers;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHelper {

    /**
     * Method for closing keyboard
     *
     * @param activity
     */
    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView!=null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

}
