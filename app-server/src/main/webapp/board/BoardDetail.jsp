<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>게시글 상세</h1>
<form action='update'>
<div class="mb-3 row">
  <label for='f-number' class="col-sm-2 col-form-label">번호</label> 
  <div class="col-sm-6">
    <input id='f-number' type='text' name='boardNumber' value='${board.boardNumber}' readOnly ><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-title' class="col-sm-2 col-form-label">제목</label>
  <div class="col-sm-6">
    <input id='f-title' type='text' name='title' value='${board.title}' ><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
    <input id='f-content' type='text' name='content' value='${board.content}'><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-writer' class="col-sm-2 col-form-label">작성자</label>
  <div class="col-sm-6">
    <input id='f-writer' type='text' name='writer' value='${board.writer.id}'readOnly><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-registrationDate' class="col-sm-2 col-form-label">등록일</label> 
  <div class="col-sm-6">
    <input id='f-registrationDate' type='date' name='registrationDate' value='${board.registrationDate}'readOnly><br>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-views' class="col-sm-2 col-form-label">조회수</label> 
  <div class="col-sm-6">
    <input id='f-views' type='text' name='views' value='${board.views}'readOnly><br>    
  </div>
</div>
<div class="mb-3 row">
	<label for='f-like' class="col-sm-2 col-form-label">좋아요 수</label> 
  <div class="col-sm-6">
		<input id='f-like' type='text' name='like' value='${board.likes}'readOnly><br>
  </div>
</div>
<div class="mb-3 row">
	<label for='f-tag' class="col-sm-2 col-form-label">태그</label> 
  <div class="col-sm-6">
		<input id='f-tag' type='text' name='tag' value='${board.tag}'><br>
  </div>
</div>
		
<c:choose> 
  <c:when test="${loginUser.authority eq 2 || loginUser.authority eq 4|| loginUser.authority eq 8}">
    <button class="btn btn-primary">변경</button>
    <a href='delete?no=${board.boardNumber}' class="btn btn-primary">삭제</a><br>
</c:when>
<%--   
    <c:when test="${loginUser.authority eq 2}">
    <button class="btn btn-primary">변경</button>
    <a href='delete?no=${board.boardNumber}' class="btn btn-primary">삭제</a><br>
  </c:when>
    <c:when test="${loginUser.authority eq 4}">
    <button class="btn btn-primary">변경</button>
    <a href='delete?no=${board.boardNumber}' class="btn btn-primary">삭제</a><br>
  </c:when> --%>
</c:choose>		
    <a href='list' class="btn btn-primary">목록</a><br>
<hr />
</form>
</div><!-- .container -->

<div class="container">
<h4>댓글</h4>
<a href='comment/form?no=${board.boardNumber}' class="btn btn-primary">새댓글</a><br>
<c:forEach items= "${commentList}" var="comment">
<fieldset>
<legend>작성자 : ${comment.writer.id}</legend>
     <p>내용 : <a href='comment/detail?no=${comment.commentNumber}'>${comment.content}</a></p>
     <p>등록일 : ${comment.registrationDate}</p>
</fieldset>
</c:forEach>
</div><!-- .container -->
</body>
</html>
