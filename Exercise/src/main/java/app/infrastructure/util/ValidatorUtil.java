package app.infrastructure.util;

import java.util.regex.Pattern;

/*
  Utilidades para validar email, contraseña, teléfono y numérico.
*/
public class ValidatorUtil {

    private static final Pattern EMAIL = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    private static final Pattern PHONE = Pattern.compile("^\\d{1,10}$");
    private static final Pattern NUMERIC = Pattern.compile("^\\d+$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD.matcher(password).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE.matcher(phone).matches();
    }

    public static boolean isNumeric(String s) {
        return s != null && NUMERIC.matcher(s).matches();
    }
}
