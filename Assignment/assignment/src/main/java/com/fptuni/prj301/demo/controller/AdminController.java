/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AdminManager;
import com.fptuni.prj301.demo.dbmanager.BookManager;
import com.fptuni.prj301.demo.model.Book;
import com.fptuni.prj301.demo.model.UserSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author truon
 */
public class AdminController extends HttpServlet {
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         String path = request.getPathInfo();
         log(path);
         if (path.equals("/home")) {
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/admin.jsp");
            rd.forward(request, response);
        }else if(path.equals("/books")){
            AdminManager manager = new AdminManager();
            List<Book> list = manager.adminBookList();
            request.setAttribute("bookList", list);
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/book.jsp");
            rd.forward(request, response);
        }else if (path.equals("/users")){
            AdminManager manager = new AdminManager();
            List<UserSession> list = manager.adminUserList();
            request.setAttribute("userList", list);
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/user.jsp");
            rd.forward(request, response);
        } 
         else if(path.equals("/request")){
            AdminManager manager = new AdminManager();
            List<Book> list = manager.requestBook();
            request.setAttribute("requestList", list);
            RequestDispatcher rd = request.getRequestDispatcher("/view/admin/request.jsp");
            rd.forward(request, response);
        }else if(path.equals("/checked")){
            String title = request.getParameter("title");
            String check = request.getParameter("check");
            AdminManager manager = new AdminManager();
            manager.CheckUpload(check, title);
            response.sendRedirect(request.getContextPath() + "/admin/request");
        }else if(path.equals("/delete")){
            Long bookId = new Long(request.getParameter("bookid"));
            AdminManager manager = new AdminManager();
            manager.DeleteUpload(bookId);
            response.sendRedirect(request.getContextPath() + "/admin/request");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AccessController.class.getName()).log(Level.SEVERE, null, ex);
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
