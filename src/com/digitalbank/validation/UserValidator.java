package com.digitalbank.validation;

public class UserValidator {
    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}