package com.cencosud.scanandgo.car.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by joseamaro on 21-05-18.
 */

public class TextUtils {

    public static String decimalFormat(double decimal){


        Locale locale = new Locale("es","CL");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat formato = new DecimalFormat("'$' #,###", symbols);
        String valorFormateado = formato.format(decimal);
        return valorFormateado;

    }

    public static String decimalFormatNegative(double decimal){

        Locale locale = new Locale("es","CL");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat formato = new DecimalFormat("'$' - #,###", symbols);
        String valorFormateado = formato.format(decimal);
        return valorFormateado;

    }
}
