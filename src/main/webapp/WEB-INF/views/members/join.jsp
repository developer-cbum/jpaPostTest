<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/join.css">
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
            <form action="/members/join" method="post">
                <div class="mb-3">
                    <label for="member-name" class="form-label">이름</label>
                    <input type="text"  name="memberName" class="form-control" id="member-name" placeholder="이름">
                    <div id="name-pass" class="valid-feedback">
                    </div>
                    <div id="name-fail" class="invalid-feedback">
                    </div>
                </div>
                <div class="mb-3">
                    <label for="member-email" class="form-label">이메일 주소</label>
                    <input type="email" name="memberEmail" class="form-control" id="member-email" placeholder="example@email.com">
                    <div id="email-pass" class="valid-feedback">
                    </div>
                    <div id="email-fail" class="invalid-feedback">
                    </div>
                </div>
                <div class="mb-3">
                    <label for="member-password" class="form-label">비밀번호</label>
                    <input type="password" name="memberPassword" class="form-control" id="member-password" placeholder="비밀번호">
                    <div id="password-pass" class="valid-feedback">
                    </div>
                    <div id="password-fail" class="invalid-feedback">
                    </div>
                </div>
                <div class="mb-3">
                    <label for="check-member-password" class="form-label">비밀번호 확인</label>
                    <input type="password" class="form-control" id="check-member-password" placeholder="비밀번호 확인">
                    <div id="check-password-pass" class="valid-feedback">
                    </div>
                    <div id="check-password-fail" class="invalid-feedback">
                    </div>
                </div>
                <div class="member-btn-wrap">
                    <button type="button" class="btn btn-primary login-btn">로그인</button>
                    <button type="submit" class="btn btn-primary join-btn" disabled="true">회원가입</button>
                </div>
            </form>
        </div>
    </section>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/js/join.js"></script>
</html>