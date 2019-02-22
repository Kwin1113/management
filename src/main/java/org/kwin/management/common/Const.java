package org.kwin.management.common;

public class Const {
    public static String CURRENT_USER = "currentUser";

    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(String currentUser) {
        CURRENT_USER = currentUser;
    }
}
