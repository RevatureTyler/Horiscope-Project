package com.persistence;

import com.models.HoroscopeUser;
import com.utils.ConnectionManager;
import com.utils.CustomCRUDInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HoroscopeUserDAO implements CustomCRUDInterface<HoroscopeUser> {

    Connection connection;
    private Integer horoscope_user_id;
    private String mood;

    public HoroscopeUserDAO() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Integer create(HoroscopeUser horoscopeUser) {
        try {
            String sql = "INSERT INTO horoscope_user (horoscope_user_id,first_name,last_name,email,username,passw,horoscope,mood) VALUES (DEFAULT,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, horoscopeUser.getFirst_name());
            pstmt.setString(2, horoscopeUser.getLast_name());
            pstmt.setString(3, horoscopeUser.getEmail());
            pstmt.setString(4, horoscopeUser.getUsername());
            pstmt.setString(5, horoscopeUser.getPassw());
            pstmt.setString(6, horoscopeUser.getHoroscope());
            pstmt.setString(7, horoscopeUser.getMood());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();

            return rs.getInt("horoscope_user_id");

        } catch (Exception e) {
            System.out.println("Error in HoroscopeUserDAO create() method " + e.getMessage());
        }
        return null;
    }

    @Override
    public HoroscopeUser read(Integer id) {
        try {
            String sql = "SELECT * FROM horoscope_user WHERE horoscope_user_id=?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            HoroscopeUser horoscopeUser = new HoroscopeUser(horoscope_user_id, mood);

            while (rs.next()) {
                horoscopeUser.setHoroscope_user_id(rs.getInt("horoscope_user_id"));
                horoscopeUser.setFirst_name(rs.getString("first_name"));
                horoscopeUser.setLast_name(rs.getString("last_name"));
                horoscopeUser.setEmail(rs.getString("email"));
                horoscopeUser.setUsername(rs.getString("username"));
                horoscopeUser.setPassw(rs.getString("passw"));
                horoscopeUser.setHoroscope(rs.getString("horoscope"));
                horoscopeUser.setMood(rs.getString("mood"));
            }
            //System.out.println("Read Testing Worked"); //Used in testing read() method

            return horoscopeUser;

        } catch (Exception e) {
            System.out.println("Error in HoroscopeUserDAO read() method " + e.getMessage());
        }
        return null;
    }

    @Override
    public HoroscopeUser update(HoroscopeUser horoscopeUser) {
        try{
            String sql = "UPDATE horoscope_user SET mood=? WHERE horoscope_user_id=?";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, horoscopeUser.getMood());
            pstmt.setInt(2,horoscopeUser.getHoroscope_user_id());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()){
                horoscopeUser.setMood(rs.getString("mood"));
            }

            return horoscopeUser;

        } catch (Exception e){
            System.out.println("Error in HoroscopeUserDAO update() method "+e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public HoroscopeUser loginHoroscopeUser(String username, String password) {
        try{
            String sql = "SELECT * FROM horoscope_user WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,username);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && rs.getString("passw").equals(password)){
                return new HoroscopeUser(rs.getInt("horoscope_user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("passw"),
                        rs.getString("horoscope"),
                        rs.getString("mood"));

            }

        } catch (Exception e){
            System.out.println("Error in HoroscopeUserDAO loginHoroscopeUser() method "+e.getMessage());
        }
        return null;
    }
}