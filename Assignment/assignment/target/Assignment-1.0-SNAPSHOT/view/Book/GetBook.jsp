<%-- 
    Document   : GetBook
    Created on : Mar 20, 2022, 4:57:03 PM
    Author     : truon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.fptuni.prj301.demo.utils.DBUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String id = request.getParameter("title");
    try {
        Connection con = DBUtils.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from Books where book_name=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("pdf");
            byte byteArray[] = blob.getBytes(1, (int) blob.length());
            response.setContentType("application/pdf");
            OutputStream os = response.getOutputStream();
            os.write(byteArray);
            os.flush();
            os.close();
        } else {
            System.out.println("No image found with this id.");
        }
    } catch (Exception e) {
        out.println(e);
    }
%>
