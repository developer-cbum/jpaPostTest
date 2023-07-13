<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<header class="header">
    <div class="header-wrap">
        <a href="/posts/list"><div class="main-logo"><img src="/images/logo2.png" alt="" /></div></a>
        <div class="header-right-wrap">
            <c:choose>
                <c:when test="${sessionScope.id eq null}">
                    <a href="/posts/list"><div class="home">Home</div></a><span>|</span>
                    <a href="/members/login"><div class="login">Login</div></a>
                </c:when>
                <c:otherwise>
                    <a href="/posts/list"><div class="home">Home</div></a><span style="margin-right: 5px">|</span>
                    <a href="/members/logout"><div class="login">Logout</div></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
<section class="banner">
    <img src="/images/logo.png" alt="" />
</section>
</body>
</html>