package com.cencosud.scan_commons.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by fbarrios80 on 18-04-18.
 */

public abstract class RutMaskEditText {
    private static final String mask8 = "#.###.###-#";
    private static final String mask9 = "##.###.###-#";
    private static final String mask10 = "###.###.###-#";

    public static String unmask(String s) {
        return s.replaceAll("[^k-kK-K0-9]+", "");
    }

    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = RutMaskEditText.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 8:
                        mask = mask8;
                        break;
                    case 9:
                        mask = mask9;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String getDefaultMask(String str) {
        String defaultMask = mask8;
        if (str.length() <= 12) {
            defaultMask = mask9;
        }
        return defaultMask;
    }
}