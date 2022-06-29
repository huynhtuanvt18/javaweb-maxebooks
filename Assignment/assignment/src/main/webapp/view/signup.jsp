<%-- 
    Document   : signup
    Created on : Mar 17, 2022, 9:46:57 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <title>Max E-books</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="../css/signupstyle.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
    </head>
    <body>
        <c:url var="homeLink" value="${request.contextPath}/Access/home"/>
        <c:url var="loginLink" value="${request.contextPath}/view/login.jsp"/>
        <c:url var="signupLink" value="${request.contextPath}/Access/signup"/>

        <div class="signup-form">
            <form action="${signupLink}" method="POST">
             <h2>Sign Up</h2>
                <p>Please fill in this form to create an account!</p>
                <hr>
                <div class="form-group">
                    <div class="row">
                        <div class="col"><input value="${lastInputFirst_name}" type="text" maxlength="100" class="form-control" name="firstname" placeholder="First Name" required="required"></div>
                        <div class="col"><input value="${lastInputLast_name}" type="text" maxlength="100" class="form-control" name="lastname" placeholder="Last Name" required="required"></div>
                    </div>        	
                </div>
                <div class="form-group">
                    <input type="text" value="${lastInputUsername}" maxlength="100" class="form-control" name="username" placeholder="Username" required="required">
                </div>
                <div class="form-group">
                    <input type="password" value="${lastInputPassword}" class="form-control" maxlength="200" name="password" placeholder="Password" required="required">
                </div>
                <div class="form-group">
                    <input type="password" value="${lastInputConfirm_password}" class="form-control" maxlength="200" name="confirm_password" placeholder="Confirm Password" required="required">
                </div>        
                <div class="form-group">
                    <label class="form-check-label"><input type="checkbox" name="termOfUse" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                </div>
                <div class="form-group">
                     <c:if test="${signupFlag == false}"> 
                        <div style="color:red">${errorMess}</div>
                    </c:if>    
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-lg">Sign Up</button>
                </div>
            </form>
          
            <p class="text-center small">Have already an account? <a href="${loginLink}">Login ?</a></p>
        </div>
        <div id="footer">
            <a href="${homeLink}" class="back-to-article"
               target="_self">Back
                to home page</a>
        </div>
    </body>
</html>