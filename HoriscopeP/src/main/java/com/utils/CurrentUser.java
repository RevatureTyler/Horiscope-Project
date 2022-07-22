package com.utils;

import com.models.HoroscopeUser;
import com.persistence.HoroscopeUserDAO;

public class CurrentUser {

    public static HoroscopeUser currentUser;

    public static Integer user_id;
    public static String username;
    public static String password;

    public static String employee_type;

    public CurrentUser(Integer user_id, String username, String password){

        CurrentUser.user_id = user_id;
        CurrentUser.username = username;
        CurrentUser.password = password;

    }

    public static void setCurrentUser(HoroscopeUser currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static HoroscopeUser getCurrentUser() {
        return currentUser;
    }

    public static Integer getUser_id() {
        return user_id;
    }

    public static void setUser_id(Integer user_id) {
        CurrentUser.user_id = user_id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        CurrentUser.password = password;
    }

    public static String getEmployee_type() {
        return employee_type;
    }

    public static void setEmployee_type(String employee_type) {
        CurrentUser.employee_type = employee_type;
    }
}