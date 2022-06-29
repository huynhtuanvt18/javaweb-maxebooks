<%-- 
    Document   : login
    Created on : 12 Mar 2022, 15:23:28
    Author     : minhh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Max E-books login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../css/loginstyle.css"/>
    </head>
    <body>
        <c:url var="loginLink" value="${request.contextPath}/Access/login"/>
        <c:url var="homeLink" value="${request.contextPath}/Access/home"/>
        <c:url var="signupLink" value="${request.contextPath}/view/signup.jsp"/>
        <div class="login-form">
            <form action="${loginLink}" method="post">
                <h2 class="text-center">Login</h2>   
                <div class="form-group has-error">
                    <input type="text" class="form-control" value="${lastInputUsername}" name="username" placeholder="Username" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" value="${lastInputPassword}" name="password" placeholder="Password" required="required">
                </div>
                <c:if test="${loginMess == false}"> 
                    <div style="color:red">The username or password you entered is not matched to any account.</div>
                </c:if>        
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
                </div>
            </form>
            <p class="text-center small">Don't have an account? <a href="${signupLink}">Sign up here!</a></p>
        </div>
        <div id="footer">
            <a href="${homeLink}" class="back-to-article"
               target="_self">Back
                to home page</a>
        </div>
    </body>
</html>