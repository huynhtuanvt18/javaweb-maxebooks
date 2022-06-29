<%-- 
    Document   : Reading.jsp
    Created on : Mar 20, 2022, 4:54:49 PM
    Author     : truon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@page import="java.util.List,com.fptuni.prj301.demo.model.Book" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="canonical" href="https://html5-templates.com/" />
        <title>Max E-books</title>
        <meta name="description" content="book store ">
        <!--Stylesheet-->
        <style>
            html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video{
                margin:0;
                padding:0;
                border:0;
                outline:0;
                font-size:100%;
                vertical-align:baseline;
                background:transparent
            }
            #footer {
                position: fixed;
                padding: 10px 10px 10px 10px;
                bottom: 0px;
                width: 100%;
                /* Height of the footer*/
                height: 60px;
            }
            body{
                line-height:1;
                font-family:arial
            }
            h1{
                font-size:25px
            }
            h2{
                font-size:21px
            }
            h3{
                font-size:18px
            }
            h4{
                font-size:16px
            }
            article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{
                display:block
            }
            nav ul{
                list-style:none
            }
            blockquote,q{
                quotes:none
            }
            blockquote:before,blockquote:after,q:before,q:after{
                content:none
            }
            a{
                margin:0;
                padding:0;
                font-size:100%;
                vertical-align:baseline;
                background:transparent
            }
            a.foo:visited{
                color:blue;
            }
            ins{
                background-color:#ff9;
                color:#000;
                text-decoration:none
            }
            mark{
                background-color:#ff9;
                color:#000;
                font-style:italic;
                font-weight:bold
            }
            del{
                text-decoration:line-through
            }
            abbr[title],dfn[title]{
                border-bottom:1px dotted;
                cursor:help
            }
            table{
                border-collapse:collapse;
                border-spacing:0
            }
            hr{
                display:block;
                height:1px;
                border:0;
                border-top:1px solid #ccc;
                margin:1em 0;
                padding:0
            }
            input,select{
                vertical-align:middle
            }
            body,html{
                background-color:#FFF
            }
            header{
                background:#6f9898;
                padding:10px 30px;
                margin:auto;
                position:fixed;
                left:0;
                right:0;
                top:0;
                z-index:999;
                height:40px
            }
            nav{
                display:inline-block
            }
            nav ul li a{
                background:#fff;
                padding:2px 6px;
                font-size:14px;
                text-decoration:none;
                font-weight:bold;
                color:#344;
                border-radius:5px
            }
            nav ul li a:hover{
                color:#000
            }
            nav ul li{
                display:inline-block;
                margin:10px
            }
            nav ul{
                list-style:none
            }
            article{
                border-bottom:2px dotted #998;
                padding-bottom:20px;
                margin-bottom:20px
            }
            article h2{
                font-weight:normal;
                margin-bottom:12px
            }
            footer{
                background:#344;
                max-width:1000px;
                margin:0 -20px;
                clear:both;
                text-align:right
            }
            footer p{
                padding:20px;
                color:#FFF
            }
            address{
                padding:10px 20px 30px 10px
            }
            aside > div{
                margin:10px auto;
                background:#344;
                min-height:50px;
                padding:30px 10px;
                text-align:center;
                color:#FFF
            }
            a#logo{
                vertical-align: middle;
                font-size:40px;
                color:#344;
                font-weight:bold;
                display:block;
                text-decoration:none;
                text-align:center;
                line-height:40px;
                padding:60px 5px
            }
            body > section{
                max-width:1000px;
                margin:auto;
                padding:30px 0px;
                border-bottom:1px solid #999;
                color:#333
            }
            #sidebarBackTop{
                background:#286983;
                color:#FFF;
                opacity:0.8;
                cursor:pointer;
                display:block;
                padding:5px
            }
            #sidebar a.back2Top{
                text-decoration:none;
                text-align:center;
                background:#344;
                color:#FFF;
                font-weight:bold;
                padding:10px;
                display:block
            }
            #sidebar a.back2Top:hover{
                background:#456
            }
            #sidebarContent{
                height:90%;
                width:300px;
                background:#9dc1c1;
                padding:15px
            }
            footer a{
                color:#FFF
            }
            #main{
                margin-left:320px
            }
            #wrapall{
                padding-top:40px
            }
            #header{
                max-width:1000px;
                overflow:hidden;
                height:40px
            }
            #wrapall,#header{
                max-width:1100px;
                margin:0 auto
            }
            #headerLeft,#headerRight{
                display:inline-block;
                vertical-align:middle
            }
            #headerLeft{
                text-align:center;
                width:100px
            }
            #headerRight{
                height:40px
            }
            #sidebar{
                float:left;
                position:absolute
            }
            #page{
                padding:20px
            }
            #menuToggle,#mobileMenuToggle{
                background:#FFF;
                cursor:pointer;
                display:inline-block;
                font-size:40px;
                width:40px;
                font-weight:bold;
                text-align:center;
                height:40px;
                line-height:40px;
                color:#344;
                border-radius:10px
            }
            #mobileMenuToggle,#mobileLogo{
                vertical-align:middle;
                display:none;
                font-size:30px
            }
            #menuToggle:hover{
                color:#FFF;
                background:#344
            }
            .socialButtons{
                float:right;
                padding-top:8px
            }
            .socialButtons a{
                display:inline-block;
                cursor:pointer;
                background:#0570e6;
                padding:2px;
                width:33px;
                text-align:center;
                height:20px;
                font-weight:bold;
                color:#FFF;
                text-decoration:none;
                line-height:20px;
                font-size:20px;
                border-radius:5px;
                vertical-align:middle
            }
            .socialButtons a.linkedin{
                background:#24568e
            }
            .socialButtons a.youtube{
                background:#c00
            }
            .socialButtons a svg{
                width:16px;
                height:16px;
                fill:#FFF
            }
            .stick #sidebarContent{
                position:fixed;
                z-index:900;
                bottom:0px
            }
            #stick-here{
                background:red
            }
            .sidebarToggle #sidebar{
                display:none
            }
            .sidebarToggle #main{
                margin-left:0
            }
            @media screen and (max-width:1100px){
                header{
                    padding:10px
                }
                #headerLeft{
                    width:auto
                }
                #headerRight{
                    width:60%
                }
            }
            @media screen and (max-width:820px){
                #sidebar{
                    display:none
                }
                #main{
                    margin-left:0
                }
                .sidebarToggle #sidebar{
                    background:red;
                    display:block
                }
                .stick #sidebarContent{
                    bottom:auto
                }
                a#logo{
                    font-size:20px;
                    line-height:20px;
                    padding:10px 5px
                }
                aside > div{
                    min-height:20px;
                    padding:10px
                }
                .sidebarToggle #menuToggle{
                    background:#344;
                    color:#FFF
                }
            }
            @media screen and (max-width:600px){
                #headerLeft{
                    float:right
                }
                #headerRight{
                    height:auto;
                    position:absolute;
                    width:auto;
                    top:46px;
                    background:#6f9898;
                    left:0;
                    display:none
                }
                .showMobileMenu #headerRight{
                    display:block
                }
                nav ul li a{
                    display:block;
                    background:transparent;
                    color:#FFF;
                    text-align:center;
                    font-size:16px;
                    padding:3px 29px;
                    line-height:25px
                }
                .showMobileMenu #mobileMenuToggle{
                    background:#344;
                    color:#FFF
                }
                nav ul li{
                    display:block;
                    margin:0
                }
                .socialButtons{
                    display:none
                }
                header{
                    padding:3px 10px
                }
                #page{
                    padding: 20px 10px
                }
                #wrapall{
                    padding-top:46px
                }
                #sidebarContent{
                    right:0;
                    background:#6f9898
                }
                #mobileLogo{
                    display:inline-block;
                    font-size:17px;
                    padding:0 3px 0 10px;
                    font-weight:bold;
                    color:#FFF;
                    text-decoration:none
                }
                #mobileMenuToggle{
                    display:inline-block
                }
            }
            .back-to-article {
                color: #fff;
                text-transform: uppercase;
                font-size: 12px;
                position: absolute;
                right: 20px;
                top: 20px;
                text-decoration: none;
                display: inline-block;
                background: rgba(0, 0, 0, 0.6);
                padding: 10px 18px;
                transition: all 0.3s ease-in-out;
                opacity: 0.6;
            }
            .back-to-article:hover {
                opacity: 1;
                background: rgba(0, 0, 0, 0.8);
            }

            p1{
                color: #BB6464;
            }

            p2{
                color: #1572A1;
            }

            s1{
                width: 10px;
                height: 5px;

            }

            h2{
                font-size: 20px;
            }

            p{
                font-size: 30px;
            }
            #w{
                color: #fff;
            }
        </style>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
    </head>
    <body>
        <c:url var="loginLink" value="${request.contextPath}/view/login.jsp"/>
        <c:url var="signupLink" value="${request.contextPath}/view/signup.jsp"/>
        <c:url var="uploadLink" value="${request.contextPath}/book/upload"/>
        <c:url var="homeLink" value="${request.contextPath}/book/home"/>
        <c:url var="logOutLink" value="${request.contextPath}/Access/logout"/>
        <c:url var="bookLink" value="${request.contextPath}/book"/>

        <header>
            <div id="header">
                <c:if test="${currentAccount == null}">
                    <div style="padding-top:8px;float: right;">
                        <a href="${loginLink}">Login</a>|
                        <a href="${signupLink}">Sign up</a>
                    </div>
                </c:if>
                <c:if test="${currentAccount != null}">
                    <div style="padding-top:8px;float: right;">
                        <a href="#" style="text-decoration: none; color: #fff">Welcome ${currentAccount.username} </a>|
                        <a href="${logOutLink}">Log out</a>
                    </div>
                </c:if>		
                <c:if test="${currentAccount.role=='admin'}">
                    <div style="padding-top:8px;float: right;">
                        <a href="${adminLink}">Admin</a> | 
                    </div>
                </c:if>	

                <!--                <div id="mobileMenuToggle" title="Menu">M</div>
                                <a href="${homeLink}" id="mobileLogo">Max E-books</a>
                                <div id="headerLeft">
                                    <div id="menuToggle">&equiv;</div>
                                </div>-->
                <div id="headerRight">
                    <nav>  
                        <ul>

                            <nav>  
                                <ul>
                                    <li><a href="${homeLink}">Home</a>
                                    <li><a rel="nofollow" href="${uploadLink}">Upload</a>
                                    <li><a rel="nofollow" href="https://www.facebook.com/Max-Ebooks-Support-105724482065019">Help</a>
                                    <li>
                                        <form action="/assignment/book/search">
                                            <input type="text" name="search">
                                            <input type="submit" value="Search">
                                        </form>
                                    </li>
                                </ul>
                            </nav>

                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div id="wrapall">
            <br>
            <%
                String title = request.getParameter("title");
            %>
            <embed src="http://localhost:8080/assignment/view/Book/GetBook.jsp?title=<%=title%>" width="1366px" height="768px" type="application/pdf"/>
            <footer id="footer">
                <p><a rel="nofollow" href="https://github.com/dunghuynh-teaching/prj301-se1602-04" target="_blank" rel="nofollow">Click here to contact us</a></p>
            </footer>
        </div>

    </body>
</html>