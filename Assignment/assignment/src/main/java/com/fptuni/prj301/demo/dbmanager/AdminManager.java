/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Book;
import com.fptuni.prj301.demo.model.UserSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class AdminManager {
    private static ArrayList<UserSession> userList;
    private static ArrayList<Book> bookList;
    public List<Book> adminBookList(){
        ArrayList<Book> list;
        list= new ArrayList<Book>();
        String sql = "select * from Books";
         try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Book(rs.getString("book_name"), rs.getString("author_name"), rs.getString("category"), rs.getDate("upload_date"), new Long(rs.getLong("book_id")), new Boolean(rs.getString("check"))));
            }

            ps.close();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public List<UserSession> adminUserList(){
        ArrayList<UserSession> list;
        list= new ArrayList<UserSession>();
        String sql = "select * from Users";
         try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new UserSession(rs.getString("username"), rs.getString("password"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("role"), rs.getDate("active_date"), rs.getInt("user_id")));
            }
            ps.close();
            conn.close();
           

        } catch (Exception ex) {
            Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public List<Book> requestBook(){
         ArrayList<Book> list;
        list= new ArrayList<Book>();
        String sql = "select * from Books";
         try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getString("check").equals("false"))
                list.add(new Book(rs.getString("book_name"), rs.getString("author_name"), rs.getString("category"), rs.getDate("upload_date"), new Long(rs.getLong("book_id")), new Boolean(rs.getString("check"))));
            }

            ps.close();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public void CheckUpload(String check, String title){
        if(check == null) check="false";
        try {
            Connection connect= DBUtils.getConnection();
            String query = "update Books set [check] = ? where book_name = ?";
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setString(1, check);
            preparedStmt.setString(2, title);

      // execute the java preparedstatement
            preparedStmt.executeUpdate();
      
            connect.close();
        } catch (Exception e) {
            Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void DeleteUpload(Long bookId){
        String sql ="delete from Authors where book_id = ?";
        String sql1 ="delete from Books where book_id = ?";
        try {
            Connection connect = DBUtils.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setLong(1, bookId);
            ps.execute();
            ps.close();
            ps=connect.prepareStatement(sql1);
            ps.setLong(1, bookId);
            ps.execute();
            ps.close();
            connect.close();
            
        } catch (Exception e) {
            Logger.getLogger(AdminManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

