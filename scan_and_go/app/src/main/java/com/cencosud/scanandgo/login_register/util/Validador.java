package com.cencosud.scanandgo.login_register.util;

import android.util.Log;

/**
 * Created by fbarrios80 on 11-04-18.
 */

public class Validador {

    public static boolean validaFormatoRut(String rut) {
        boolean validacion = false;

        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException num) {
            Log.d("Validador", "NumberFormatException: " + num);
        } catch (Exception e) {
            Log.d("Validador", "Exception: " + e);
        }
        return validacion;
    }
}