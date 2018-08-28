package com.cencosud.scan_commons.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fbarrios80 on 18-04-18.
 */

public abstract class Validator {

    public static final String PATTERN_NUMBER = "(?=.*[0-9])";
    public static final String PATTERN_UPPER_CASE = "(?=.*[A-Z])";
    public static final String PATTERN_LOWER_CASE = "(?=.*[a-z])";
    public static final String PATTERN_ALPHANUMERIC = "(?=.*[a-zA-Z0-9])";
    public static final String PATTERN_SPECIAL_CHARACTER = "(?=.*[@#$%^&+=])";

    public static boolean validateRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = getWithoutMask(rut);
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    public static boolean validateEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean validateCharactersSpace(CharSequence target) {
        final String accents = "ÁÉÍÓÚáéíóú";
        Pattern pattern = Pattern.compile("[a-zA-Z\\s" + accents + "]+");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static String getWithoutMask(String rut) {
        String alphaOnly = rut.replaceAll("[ .,-]", "");
        return alphaOnly;
    }

    public static boolean validatePasswordFormat(boolean upperCase,
                                                 boolean lowerCase,
                                                 boolean numbers,
                                                 boolean alphaNumeric,
                                                 boolean specialCharacter,
                                                 String password) {

        StringBuilder builder = new StringBuilder(1);

        if (upperCase) builder.append(PATTERN_UPPER_CASE);
        if (lowerCase) builder.append(PATTERN_LOWER_CASE);
        if (numbers) builder.append(PATTERN_NUMBER);
        if (alphaNumeric) builder.append(PATTERN_ALPHANUMERIC);
        if (specialCharacter) builder.append(PATTERN_SPECIAL_CHARACTER);

        final String PATTERN = "^" + builder + ".{1,}$";
        return password.matches(PATTERN);
    }
}
