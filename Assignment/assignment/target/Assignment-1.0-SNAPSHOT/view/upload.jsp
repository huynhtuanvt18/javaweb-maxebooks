<%-- 
    Document   : upload
    Created on : Mar 17, 2022, 9:55:42 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Max E-books upload</title>
        <script type="text/javascript"> (function() { var css = document.createElement('link'); css.href = 'https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'; css.rel = 'stylesheet'; css.type = 'text/css'; document.getElementsByTagName('head')[0].appendChild(css); })();</script>
        <link rel="stylesheet" href="../css/uploadstyle.css" type="text/css">
        
        <style>
            #footer {
                position: fixed;
                padding: 10px 10px 10px 10px;
                bottom: 0px;
                width: 100%;
                /* Height of the footer*/
                height: 60px;
            }</style>

    </head>
    <body>
        <c:url var="homeLink" value="${request.contextPath}/Access/home"/>

        <form action="/assignment/book/upload" method="POST" enctype='multipart/form-data'>

            <h1><strong>Share your knowledge to the world.</strong> </h1>

            <div class="form-group">
                <label for="title">Title <span>The title of your book.</span></label>
                <input type="text" name="title" required="required" id="title" class="form-controll"/>
            </div>
            <div class="form-group ">
                <label for="photo">Book file <span>Put your pdf file here.</span></label>
                <input type="file" name="file" id="file" required="required" class="form-controll"/>
            </div>

            <div class="form-group ">
                <select name="category">
                    <option value="Computer">Computer</option>
                    <option value="English">English</option>
                    <option value="Japanese">Japanese</option>
                    <option value="Musical">Musical</option>
                    <option value="Health">Health</option>
                </select>
            </div>
             <div class="form-group ">
                <input type="hidden" name="authorName" value="${currentAccount.username}">
                <input type="hidden" name="authorID" value="${currentAccount.user_id}">
             </div>
            <div class="form-group">
                <input type="submit" value="Upload file"/>
            </div>
           
        </form>

        <link href='https://fonts.googleapis.com/css?family=Lato:100,200,300,400,500,600,700' rel='stylesheet' type='text/css'>

        <div id="footer">
            <a href="${homeLink}" class="back-to-article"
               target="_self">Back
                to home page</a>
        </div>
    </body>
</html>
