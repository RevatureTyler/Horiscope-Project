package com.models;

public class HoroscopeUser {

    private Integer horoscope_user_id;

    private String first_name;

    private String last_name;

    private String email;

    private String username;

    private String passw;

    private String horoscope;

    private String mood;

    public HoroscopeUser(Integer horoscope_user_id, String mood) {}

    public HoroscopeUser(int horoscope_user_id, String first_name, String last_name, String email, String username, String passw, String horoscope, String mood) {
        this.horoscope_user_id = horoscope_user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.passw = passw;
        this.horoscope = horoscope;
        this.mood = mood;
    }

    public HoroscopeUser(String first_name, String last_name, String email, String username, String passw, String horoscope, String mood) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.passw = passw;
        this.horoscope = horoscope;
        this.mood = mood;
    }

    public HoroscopeUser(String first_name, String last_name, String email, String username, String passw, String horoscope) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.passw = passw;
        this.horoscope = horoscope;
    }

    public HoroscopeUser(String username, String passw) {
        this.username=username;
        this.passw=passw;
    }

    public int getHoroscope_user_id() {
        return horoscope_user_id;
    }

    public void setHoroscope_user_id(int horoscope_user_id) {
        this.horoscope_user_id = horoscope_user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}