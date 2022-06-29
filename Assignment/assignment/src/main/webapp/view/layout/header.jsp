
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

        <div id="mobileMenuToggle" title="Menu">M</div>
        <a href="${homeLink}" id="mobileLogo">Max E-books</a>
        <div id="headerLeft">
            <div id="menuToggle">&equiv;</div>
        </div>
        <div id="headerRight">
            <nav>  
                <ul>

                    <nav>  
                        <ul>
                            <li><a href="${homeLink}">Home</a>
                            <li><a rel="nofollow" href="https://htmlg.com/html-editor/">Highly recommend</a>
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