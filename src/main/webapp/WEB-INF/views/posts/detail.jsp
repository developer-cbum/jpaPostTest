<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>게시판 상세</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/css/common.css"/>
    <link rel="stylesheet" href="/css/list.css"/>
    <link rel="stylesheet" href="/css/detail.css"/>
    <link rel="icon" href="/images/favicon.ico"/>
</head>
<body>
<div class="detail-container">
    <jsp:include page="../header/header.jsp"/>
    <section>
        <div class="detail-wrap">
            <div class="post-btn-wrap">
                <button type="button" class="btn btn-primary back-list">리스트로 돌아가기</button>
            </div>
            <div class="mb-3">
                <label for="title">제목</label>
                <div class="form-control" id="title">
                    <c:out value="${post.postTitle}"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="writer">작성자</label>
                <div class="form-control" id="writer">
                    <c:out value="${post.memberName}"/>
                </div>
            </div>
            <c:if test="${fn:length(files) != 0}">
                <div class="mb-3">
                    <label for="formFile">첨부 파일</label>
                    <div class="form-control" type="file" id="formFile">
                        <c:forEach items="${files}" var="file">
                            <div class="file-wrap">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
                                    <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
                                    <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
                                </svg>
                                <a class="p-1 rounded" style="color:#0d6efd; font-size: 14px"
                                   href="/files/download?fileName=${file.filePath}/${file.fileUuid}_${file.fileName}">${file.fileName}
                                </a><span style="font-size: 12px">(<fmt:formatNumber value="${file.fileSize/1024}" pattern=".00"/>KB)</span><br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <div class="mb-3">
                <label for="content">내용</label>
                <div class="form-control last-content" id="content">
                    <pre><c:out value="${post.postContent}"/></pre>
                </div>
            </div>
            <div class="btn-wrap">
                <c:if test="${sessionScope.id == post.memberId}">
                    <div class="post-btn-wrap modify-btn-wrap">
                        <button type="button" class="btn btn-primary modify-btn"
                                onclick="location.href='/posts/modify/${post.postId}'">수정
                        </button>
                    </div>
                    <div class="post-btn-wrap delete-btn-wrap">
                        <button type="button" class="btn btn-primary delete-btn"
                                onclick="location.href='/posts/remove/${post.postId}'">삭제
                        </button>
                    </div>
                </c:if>
            </div>
        </div>
    </section>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
    $(document).ready(function () {
        const $backToListBtn = $('.back-list');
        $backToListBtn.on("click", $(document), () => {
            location.href = "/posts/list"
        })
    });
</script>

</html>
