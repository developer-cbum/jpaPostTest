<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/login.css">
    <link rel="icon" href="/images/favicon.ico"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous"
    />
</head>
<body>
<div class="login-wrap">
    <section>
        <div class="logo-wrap">
            <img src="/images/logo2.png" alt="">
        </div>
        <div class="login-wrap">
            <form action="/members/login" method="post">
                <div class="mb-3">
                    <label for="member-email" class="form-label">이메일 주소</label>
                    <input type="email" class="form-control" id="member-email" name="memberEmail" placeholder="example@email.com">
                </div>
                <div class="mb-3">
                    <label for="member-password" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="member-password" name="memberPassword" placeholder="password">
                </div>
                <div class="member-btn-wrap">
                    <button type="button" class="btn btn-primary join-btn">회원가입</button>
                    <button type="submit" class="btn btn-primary login-btn">로그인</button>
                </div>
            </form>
        </div>
    </section>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    let joinStatus = [${join}];
    let loginStatus = [${loginStatus}];
    if(joinStatus[0]){
        alert("회원가입완료");
    }
    if(loginStatus[0]){
        alert("아이디나 비밀번호를 확인해주세요")
    }

</script>
<script src="/js/login.js"></script>
</html>