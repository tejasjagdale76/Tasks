package com.kiranacademy.rpms.util;

public final class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isValidPercentage(double percentage) {
        return percentage >= 0 && percentage <= 100;
    }

    public static boolean isValidBacklogCount(int backlogCount) {
        return backlogCount >= 0;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("\\d{10}");
    }
}
