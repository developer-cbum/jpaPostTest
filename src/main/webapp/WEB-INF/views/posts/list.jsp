<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>게시판 메인</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/css/common.css" />
    <link rel="stylesheet" href="/css/list.css" />
    <link rel="icon" href="/images/favicon.ico"/>
</head>
<body>
<div class="container">
    <jsp:include page="../header/header.jsp" />
    <section>
        <div class="content-wrap">
            <div class="content-top">
                <div>
                    <div class="post-list">게시글 목록</div>
                    <div class="post-total"><c:out value="${pagination.total}개"/></div>
                </div>
                <div class="post-btn-wrap"><button type="button" class="btn btn-primary">게시글 등록</button></div>
            </div>
            <ul class="content-ul"></ul>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                <c:if test="${pagination.prev}">
                    <li class="page-item"><a class="page-link" href="/posts/list?page=${pagination.startPage-1}"><</a></li>
                </c:if>
                <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
                    <c:choose>
                        <c:when test="${i eq pagination.page+1}">
                            <li class="page-item active"><a class="page-link"><c:out value="${i}"/></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/posts/list?page=${i}"><c:out value="${i}"/></a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pagination.next}">
                    <li class="page-item"><a class="page-link" href="/posts/list?page=${pagination.endPage+1}">></a></li>
                </c:if>
                </ul>
            </nav>
        </div>
    </section>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    let posts = ${posts};

    let id = [${sessionScope.id}];
    console.log(id);
</script>
<script src="/js/elapsedTime.js"></script>
<script src="/js/list.js"></script>
</html>
