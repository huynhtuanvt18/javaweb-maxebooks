/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AccessManager;
import com.fptuni.prj301.demo.dbmanager.BookManager;
import com.fptuni.prj301.demo.model.Book;
import com.fptuni.prj301.demo.model.UserSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author DUNGHUYNH
 */
@MultipartConfig
public class BookController extends HttpServlet {

    PrintWriter out;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        log(path);
        if (path.equals("/home")) {
            BookManager manager = new BookManager();
            List<Book> list = manager.listUser();
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("/view/home.jsp");
            rd.forward(request, response);
        } else if (path.equals("/download")) {
            String title = request.getParameter("title");
            RequestDispatcher rd = request.getRequestDispatcher("/view/Book/GetBook.jsp");
            rd.forward(request, response);
        } else if (path.equals("/detail")) {
            String title = request.getParameter("title");
            BookManager manager = new BookManager();
            Book book = manager.detailBook(title);
            List<String> comment = manager.getComment(book.getBookID());
            request.setAttribute("commentList", comment);
            request.setAttribute("object", book);

            RequestDispatcher rd = request.getRequestDispatcher("/view/Book/detail1.jsp");
            rd.forward(request, response);
        } else if (path.equals("/search")) {
            String search;
            if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
                search = request.getParameter("search");
                BookManager mn = new BookManager();
                List<Book> searchResult = mn.search(search, "book_name");
                request.setAttribute("list", searchResult);
                RequestDispatcher rd = request.getRequestDispatcher("/view/home.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/book/home");
            }
        }else if (path.equals("/upload")) {
               
                
            HttpSession ss = request.getSession();
            UserSession us =  (UserSession) ss.getAttribute("currentAccount");
            if (us == null){
                 response.sendRedirect(request.getContextPath()+"/view/login.jsp");
                 return;
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("/view/upload.jsp");
                rd.forward(request, response);
            }
        } else if (path.equalsIgnoreCase("/read")) {
            String title = request.getParameter("title");
            RequestDispatcher rd = request.getRequestDispatcher("/view/Book/Reading.jsp");
            rd.forward(request, response);
        } else if (path.equals("/category")) {
            String category;
            if (request.getParameter("category") != null && !request.getParameter("category").equals("")) {
                category = request.getParameter("category");
                BookManager mn = new BookManager();
                List<Book> searchResult = mn.search(category, "category");
                if (!searchResult.isEmpty()) {
                    request.setAttribute("list", searchResult);
                    RequestDispatcher rd = request.getRequestDispatcher("/view/home.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/book/home");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/book/home");
            }
        } else if (path.equals("/comment")) {
            String title = request.getParameter("title");
            if (request.getParameter("cmt") != null && !request.getParameter("cmt").equals("")){
            HttpSession ss = request.getSession();
            UserSession us =  (UserSession) ss.getAttribute("currentAccount");
            if (us != null){
            Long bookID= new Long(request.getParameter("bookId"));
            String content = request.getParameter("cmt");
            Long userID = new Long(request.getParameter("userId"));
            BookManager manager = new BookManager();
            manager.writeComment(content, bookID,userID);
            }
            else{
            Long bookID= new Long(request.getParameter("bookId"));
            String content = request.getParameter("cmt");
            BookManager manager = new BookManager();
            manager.writeCommentWithoutLogin(content, bookID);
            }
            }
            response.sendRedirect(request.getContextPath() + "/book/detail?title=" + title);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        int result = 0;
        int rs1=0;
        Part part = request.getPart("file");
        long i = System.currentTimeMillis();
        Date x = new Date(i);
        String y=request.getParameter("author");
        Long bookId=null;
        if (part != null) {
            try {
                Connection con = DBUtils.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into Books (book_name,pdf,category,author_name,upload_date,[check]) values(?,?,?,?,?,?)");
                InputStream is = part.getInputStream();
                ps.setString(1, request.getParameter("title"));
                ps.setBlob(2, is);
                ps.setString(3, request.getParameter("category"));
                ps.setString(4, request.getParameter("authorName"));
                ps.setDate(5, x);
                ps.setString(6, "false");
                result = ps.executeUpdate();
                if (result > 0) {
                    ps.close();
                    ps=con.prepareStatement("select book_id from Books where book_name = ?");
                    ps.setString(1,request.getParameter("title"));
                    ResultSet rs= ps.executeQuery();
                    while(rs.next()){
                        bookId= new Long(rs.getLong("book_id"));
                    }
                    ps.close();
                    ps = con.prepareStatement("insert into Authors (book_id, name, user_id) values(?,?,?)");
                    ps.setLong(1, bookId);
                    ps.setString(2, y);
                    ps.setString(3, request.getParameter("authorID"));
                    rs1= ps.executeUpdate();
                    if(rs1>0){
                        response.sendRedirect(request.getContextPath() + "/book/home");
                    }
                    
                } else {
                    log("wrong");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
