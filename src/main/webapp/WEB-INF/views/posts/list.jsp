<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>게시판 메인</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous"
    />
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <link rel="stylesheet" href="/css/list.css"/>
    <link rel="icon" href="/images/favicon.ico"/>
</head>
<body>
<%--<c:set var="pageSize" value="${pagination.size}"/>--%>
<%--<c:set var="nowPage" value="${pagination.number +1}"/>--%>
<%--<c:set var="totalPages" value="${pagination.totalPages}"/>--%>
<%--<c:set var="first" value="${pagination.first}"/>--%>
<%--<c:set var="last" value="${pagination.last}"/>--%>
<%--<c:set var="endPage" value="${((nowPage-1)/pageSize+1) * pageSize}"/>--%>
<%--<c:set var="startPage" value="${endPage-pageSize + 1}"/>--%>
<%--<c:set var="realEndPage" value="${endPage < totalPages ? endPage : totalPages}"/>--%>
<div class="container">
    <jsp:include page="../header/header.jsp"/>
    <section>
        <div class="content-wrap">
            <div class="content-top">
                <div>
                    <div class="post-list">게시글 목록</div>
                    <div class="post-total"><c:out value="${pagination.total}개"/></div>
                </div>
                <div class="post-btn-wrap">
                    <button type="button" class="btn btn-primary">게시글 등록</button>
                </div>
            </div>
            <ul class="content-ul"></ul>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${pagination.prev}">
                        <li class="page-item">
                            <a class="page-link" href="/posts/list?page=${pagination.startPage-1}">
                                <span class="material-symbols-outlined" style="font-size: 13px;">keyboard_double_arrow_left</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${pagination.page != 0}">
                        <li class="page-item">
                            <a class="page-link direction-page-btn" href="/posts/list?page=${pagination.page-1}">
                                <span class="material-symbols-outlined" style="position: relative;top: -1.5px;font-size: 10px;">
                                    arrow_back_ios
                                </span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage-1}">
                        <c:choose>
                            <c:when test="${i eq pagination.page}">
                                <li class="page-item active"><a class="page-link"><c:out value="${i+1}"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="/posts/list?page=${i}"><c:out
                                        value="${i+1}"/></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pagination.page != pagination.endPage-1}">
                        <li class="page-item">
                            <a class="page-link direction-page-btn" href="/posts/list?page=${pagination.page+1}">
                                <span class="material-symbols-outlined" style="position: relative;top: -1.5px;font-size: 10px;">
                                    arrow_forward_ios
                                </span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${pagination.next}">
                        <li class="page-item">
                            <a class="page-link" href="/posts/list?page=${pagination.endPage}">
                                <span class="material-symbols-outlined" style="font-size: 13px;">keyboard_double_arrow_right</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </section>
</div>
</body>
<script>
    let posts = ${posts};
    let id = [${sessionScope.id}];
</script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/js/elapsedTime.js"></script>
<script src="/js/list.js"></script>
</html>
