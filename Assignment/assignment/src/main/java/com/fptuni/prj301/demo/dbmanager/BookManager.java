/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Book;
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
public class BookManager {
    private static ArrayList<Book> list;
    public List<Book> listUser(){
        ArrayList<Book> list;
        list= new ArrayList<Book>();
        String sql = "select * from Books";
         try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getString("check").equals("true")){
                list.add(new Book(rs.getString("book_name"), rs.getString("author_name"), rs.getString("category"), rs.getDate("upload_date"), new Long(rs.getLong("book_id"))));
                }
            }

            ps.close();
            conn.close();

        } catch (Exception ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    public Book detailBook(String title){
         String sql = "select book_name, author_name, category, upload_date, book_id from Books where book_name ='"+title+"'";
        try {

            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();;
            Book book = new Book();
            while (rs.next()) {
               book.setTitle(rs.getString("book_name"));
               book.setAuthor(rs.getString("author_name"));
               book.setCategory(rs.getString("category"));
               book.setUploadDate(rs.getDate("upload_date"));
               book.setBookID(new Long(rs.getLong("book_id")));
            }
            ps.close();
            conn.close();
            return book;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
     public List<Book> search(String searchText,String field){
        ArrayList<Book> resultList;
        resultList = new ArrayList<Book>();
        String sqlSearch = "select * from Books where "+ field +" like '%"+ searchText+ "%'";
        try {
            Connection connect = DBUtils.getConnection();
            PreparedStatement ps = connect.prepareStatement(sqlSearch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               if(rs.getString("check").equals("true")){
               resultList.add(new Book(rs.getString("book_name"), rs.getString("author_name"), rs.getString("category"),rs.getDate("upload_date"),new Long(rs.getLong("book_id"))));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
     public List<String> getComment(Long cmtID){
         ArrayList<String> resultList;
        resultList = new ArrayList<String>();
        String sqlSearch = "SELECT tc.book_id, tc.Content, tu.username , tc.user_id FROM Comments tc LEFT JOIN Users tu ON tc.user_id = tu.user_id WHERE tc.book_id= ?";
        try {
             Connection connect = DBUtils.getConnection();
            PreparedStatement ps = connect.prepareStatement(sqlSearch);
            ps.setLong(1, cmtID);
            ResultSet rs = ps.executeQuery();;
            while (rs.next()){
               if(rs.getString("username")!=null){ 
               resultList.add(rs.getString("username")+" : " +rs.getString("Content"));}
               else{
                   resultList.add("Unknow :"+ rs.getString("Content"));
               }
            }
            ps.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
     }
     public void writeComment(String comment, Long cmtID, Long userID){
         String sql="insert into Comments (book_id, content, user_id) values(?,?,?)";
         int result = 0;
         try {
             Connection  connection= DBUtils.getConnection();
             PreparedStatement ps =connection.prepareStatement(sql);
             ps.setLong(1,cmtID);
             ps.setString(2, comment);
             ps.setLong(3, userID);
             result=ps.executeUpdate();
             ps.close();
             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
          public void writeCommentWithoutLogin(String comment, Long cmtID){
         String sql="insert into Comments (book_id, content) values(?,?)";
         int result = 0;
         try {
             Connection  connection= DBUtils.getConnection();
             PreparedStatement ps =connection.prepareStatement(sql);
             ps.setLong(1,cmtID);
             ps.setString(2, comment);
             result=ps.executeUpdate();
             ps.close();
             connection.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
