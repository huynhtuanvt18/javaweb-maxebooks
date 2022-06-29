package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.UserSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DUNGHUYNH
 */
public class AccessManager {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static UserSession checkLogin(String username, String password) throws SQLException {
        try {
            String query = "select * from Users where username = ? and password = ?";
            List<UserSession> listAccount = new ArrayList<>();
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAccount.add(new UserSession(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("role"),
                        rs.getDate("active_date"),
                        rs.getInt("user_id")
                        
                ));
            }
            return listAccount.get(0);

        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
    //Trả về true nếu có tồn tại username

    public static boolean isHaveUsename(String username) throws SQLException {

        String query = "select [username] from [Users] where username = ?";
        String user = null;
        try {
            conn = DBUtils.getConnection();

            if (conn != null) {
                ps = conn.prepareStatement(query);
                ps.setString(1, username);

                rs = ps.executeQuery();
                if (rs.next()) {
                    user = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user != null;
    }

    public static boolean insertAccount(String username, String password, String firstname, String lastname) {
        long i = System.currentTimeMillis();
        Date x = new Date(i);
        try {
            String query = "insert into Users(username,password,firstname,lastname,role,active_date) values (?,?,?,?,?,?)";
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, "user");
            ps.setDate(6,x);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
 }
}
