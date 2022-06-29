/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.controller;

import com.fptuni.prj301.demo.dbmanager.AccessManager;
import com.fptuni.prj301.demo.model.UserSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DUNGHUYNH
 */
public class AccessController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {

        String path = request.getPathInfo();
         log(path);
        if (path.equals("/login")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserSession currentAccount = AccessManager.checkLogin(username, password);
            if (currentAccount != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentAccount", currentAccount);
                response.sendRedirect(request.getContextPath() + "/Access/home");
            } else {
                request.setAttribute("lastInputUsername", username);
                request.setAttribute("lastInputPassword", password);
                request.setAttribute("loginMess", false);
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
            }
        } else if (path.equals("/signup")) {

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm_password = request.getParameter("confirm_password");
            String termOfUse = request.getParameter("termOfUse");

            boolean signupFlag = true;
            String errorMess = "";
            if (firstname == null) {
                signupFlag = false;
                errorMess = "Missing First Name";
            } else if (signupFlag && firstname.length() > 100) {
                signupFlag = false;
                errorMess = "Exceeded First Name's max length (100 character)";
            }

            if (signupFlag && lastname == null) {
                signupFlag = false;
                errorMess = "Missing Last Name";
            } else if (signupFlag && lastname.length() > 100) {
                signupFlag = false;
                errorMess = "Exceeded Last Name's max length (100 character)";
            }

            if (signupFlag && username == null) {
                signupFlag = false;
                errorMess = "Missing Username";
            } else if (signupFlag && username.length() > 100) {
                signupFlag = false;
                errorMess = "Exceeded Username's max length (100 character)";
            }

            if (signupFlag && password == null) {
                signupFlag = false;
                errorMess = "Missing Password";
            } else if (signupFlag && password.length() > 100) {
                signupFlag = false;
                errorMess = "Exceeded Password's max length (200 character)";
            }

            if (signupFlag && confirm_password == null) {
                signupFlag = false;
                errorMess = "Missing Confirm Password";
            } else if (signupFlag && confirm_password.length() > 100) {
                signupFlag = false;
                errorMess = "Exceeded Confirm Password's max length (200 character)";
            }

            if (signupFlag && "off".equals(termOfUse)) {
                signupFlag = false;
                errorMess = "Missing Agree Term of Use";
            }
            if (signupFlag && AccessManager.isHaveUsename(username)) {
                signupFlag = false;
                errorMess = "Account has already existed";
            }
            if (signupFlag && !password.equals(confirm_password)) {
                signupFlag = false;
                errorMess = "Confirm Password did not match";
            }
//        Save to Database
            if (signupFlag) {
                boolean rs = AccessManager.insertAccount(username, password, firstname, lastname);
                if (!rs) {
                    signupFlag = false;
                    errorMess = "Cannot insert your Account";
                } else {
                    UserSession currentAccount = AccessManager.checkLogin(username, password);
                    request.getSession().setAttribute("currentAccount", currentAccount);
                  response.sendRedirect(request.getContextPath() + "/Access/home");
                    //response.sendRedirect(request.getContextPath()+"HomeController");
                    return;
                }
            }
            request.setAttribute("lastInputFirstname", firstname);
            request.setAttribute("lastInputLastname", lastname);
            request.setAttribute("lastInputUsername", username);
            request.setAttribute("lastInputPassword", password);
            request.setAttribute("lastInputConfirm_password", confirm_password);
            request.setAttribute("signupFlag", signupFlag);
            request.setAttribute("errorMess", errorMess);
            request.getRequestDispatcher("/view/signup.jsp").forward(request, response);

        } else if (path.equals("/home")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null) {
                response.sendRedirect(request.getContextPath() + "/book/home");
            } else {
                UserSession currentAccount = AccessManager.checkLogin(username, password);
                HttpSession ss = request.getSession(true);
                ss.setAttribute("currentAccount", currentAccount);

                if (currentAccount != null) {
                    request.setAttribute("username", username);
                    RequestDispatcher rd = request.getRequestDispatcher("/book/home");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/book/home");
                    rd.forward(request, response);
                }
            }

        } else if (path.equals("/logout")) {
            UserSession currentAccount = (UserSession) request.getSession().getAttribute("currentAccount");
            System.out.println(currentAccount);
            if (currentAccount != null) {
                request.getSession().removeAttribute("currentAccount");
                System.out.println("Done clear");
            }

            response.sendRedirect(request.getContextPath() + "/Access/home");
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
