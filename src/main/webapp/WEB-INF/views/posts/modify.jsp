<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>게시판 수정</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/css/common.css"/>
    <link rel="stylesheet" href="/css/list.css"/>
    <link rel="stylesheet" href="/css/write.css"/>
    <link rel="icon" href="/images/favicon.ico"/>
</head>
<body>
<div class="write-container">
    <jsp:include page="../header/header.jsp"/>
    <section>
        <form id="post-form" action="/posts/modify/${post.postId}" method="post">
            <div class="write-wrap">
                <input type="hidden" name="postId" value="${post.postId}">
                <input type="hidden" name="memberId" value="${sessionScope.id}">
                <div class="mb-3">
                    <label for="post-title">제목</label>
                    <input type="text" class="form-control" id="post-title" name="postTitle" placeholder="제목을 입력해주세요" value="${post.postTitle}">
                </div>
                <div class="mb-3">
                    <label for="post-writer">작성자</label>
                    <div class="form-control post-writer" id="post-writer"><c:out value="${post.memberName}"/></div>
                </div>
                <div class="mb-3">
                    <label for="formFile">첨부 파일</label>
                    <div class="form-control" type="file" id="formFile">
                        <div class="input-group mb-3">
                            <input type="file" class="form-control modify-file" id="file" multiple>
                            <label class="input-group-text" for="file">Upload</label>
                        </div>
                           <div class="file-plus-wrap">
                               <c:forEach items="${files}" var="file">
                                <div id="${file.fileId}" class="modify-file-wrap">
                                    <div class="file-wrap">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" style="cursor:default"
                                             class="bi bi-file-earmark-arrow-down" viewBox="0 0 16 16">
                                            <path d="M8.5 6.5a.5.5 0 0 0-1 0v3.793L6.354 9.146a.5.5 0 1 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0-.708-.708L8.5 10.293V6.5z"/>
                                            <path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5v2z"/>
                                        </svg>
                                        <a class="p-1 rounded" style="color:#0d6efd; font-size: 14px; cursor: default"
                                           href="">${file.fileName}
                                        </a><span style="font-size: 12px">(<fmt:formatNumber value="${file.fileSize/1024}" pattern=".00"/>KB)</span>
                                    </div>
                                    <button type="button" class="btn btn-primary file-delete-btn">삭제</button>
                                </div>
                               </c:forEach>
                           </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="post-content">내용</label>
                    <textarea class="form-control content-area" id="post-content" name="postContent"
                              placeholder="내용을 입력해주세요"><c:out value="${post.postContent}"/></textarea>
                </div>
                <div class="post-btn-container">
                    <button type="button" class="btn btn-primary register-btn">게시글 수정</button>
                </div>
            </div>
        </form>
    </section>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/js/write.js"></script>
</html>
