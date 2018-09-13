package com.script972.carjacking.helpers;

import android.content.Context;
import android.widget.EditText;

import com.script972.carjacking.R;

import java.util.regex.Pattern;

public class ValidatorHelper {

    /**
     * Method wich controll validation email
     * @param edtEmail
     * @return
     */
    public static boolean validateEmail(EditText edtEmail, Context context) {
        if(!ValidatorHelper.validateEmailPattern(edtEmail.getText().toString())){
            edtEmail.setError(context.getResources().getString(R.string.e_invalid_email));
            return false;
        }else{
            return true;
        }
    }

    /**
     * Method wich controll validation password
     * @param edt
     * @return
     */
    public static boolean validatePassword(EditText edt, Context context) {
        if(edt.getText().toString().isEmpty() && edt.getText().toString().length()<6){
            edt.setError(context.getResources().getString(R.string.e_invalid_password));
            return false;
        }else{
            return true;
        }
    }


    /**
     * Method for validation email String
     * @param input email string
     * @return validate or not
     */
    private static boolean validateEmailPattern(String input){
        Pattern pattern;
        final String ePattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(ePattern);
        return pattern.matcher(input).matches();
    }

}
