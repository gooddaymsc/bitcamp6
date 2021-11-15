<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    :root {
      --article-list__cell-id__width:100px;
      --article-list__cell-reg-date__width:250px;
      --article-list__cell-writer__width:150px;
      --article-list__cell-title__width:calc( 100% - var(--article-list__cell-id__width) - var(--article-list__cell-reg-date__width) - var(--article-list__cell-writer__width));
    }
    
    .article-list > * > div{
      display:flex;
    }
    
    .article-list > * > div::after{
      content:"";
      display:block;
      clear:both;
    }
    .article-list > header > div > div{
      position:relative;
    }
    
    .article-list > header > div > div:not(:last-child)::after{
      content:"";
      top:50%;
      transform:translatey(-50%);
      right:0;
      position:absolute;
      width:1px;
      height:10px;
      background-color:black;
    }
    
    .article-list > * > div > div{
      box-sizing:border-box;
      text-align:center;
      padding-top:10px;
      padding-bottom:10px;
    }
    
    .article-list__cell-id{
      width:var(--article-list__cell-id__width);
    }
    .article-list__cell-reg-date{
      width:var(--article-list__cell-reg-date__width);
    }
    .article-list__cell-writer{
      width:var(--article-list__cell-writer__width);
    }
    .article-list__cell-title{
      width:var(--article-list__cell-title__width);
    }
    
    .article-list > main .article-list__cell-title {
      text-align:left;
      padding-left:10px;
    }
    
    .article-list > main > div {
      border-top:2px solid black;
    }
  </style>
 <section class="section-1 con-min-width">
  <div class="con">
<div class="article-list">
<form action='update' method='post'>
<div class="mb-3 row" style= 'display:none;'>
  <label for='f-number' class="col-sm-2 col-form-label">번호</label> 
  <div class="col-sm-6">
    <input id='f-number' type='text' name='boardNumber' value='${board.boardNumber}' readOnly ><br>
  </div>
</div>

<div class="mb-3 row">
  <label for='f-title' class="col-sm-2 col-form-label">제목</label>
  <div class="col-sm-6">
    <label for='f-title' class="col-sm-2 col-form-label">${board.title}</label>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-content' class="col-sm-2 col-form-label">내용</label> 
  <div class="col-sm-6">
   <label for='f-content' class="col-sm-2 col-form-label">${board.content}</label>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-writer' class="col-sm-2 col-form-label">작성자</label>
    <div class="col-sm-6">
  <label for="f-content" class="col-sm-2 col-form-label">${board.writer.id}</label>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-registrationDate' class="col-sm-2 col-form-label">등록일</label> 
  <div class="col-sm-6">
     <label for='f-registrationDate' class="col-sm-2 col-form-label">${board.registrationDate}</label>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-views' class="col-sm-2 col-form-label">조회수</label> 
  <div class="col-sm-6">
  <label for='f-views' class="col-sm-2 col-form-label">${board.views}</label>    
  </div>
</div>
<div class="mb-3 row">
	<label for='f-like' class="col-sm-2 col-form-label">좋아요 수</label> 
  <div class="col-sm-6">
  <label for='f-views' class="col-sm-2 col-form-label">${board.views}</label>
  </div>
</div>
<div class="mb-3 row">
	<label for='f-tag' class="col-sm-2 col-form-label">태그</label> 
  <div class="col-sm-6">
  <label for='f-tag' class="col-sm-2 col-form-label">${board.tag}</label>
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
    <a href='list' class="btn btn-outline-secondary btn-sm">목록</a>
    <a href='like?no=${board.boardNumber}' class="btn btn-outline-secondary btn-sm">좋아요</a><br> 
<hr />
</form>
</div>
</div>
</section>w

<div class="container">
<h4>댓글 <a class="btn btn-outline-secondary btn-sm">새댓글</a><br>
</h4>
<form action='./comment/add'>
<input type='hidden' id='f-number' type='text' name='boardNumber' value='${board.boardNumber}' readOnly><br>
내용 <input id='f-content' type='text' name='content'>
<input type='hidden' id='f-writer' type='text' name='writer' value='${loginUser.id}' readonly>
<button class="btn btn-outline-secondary btn-sm">등록</button><br><br>
</form>
<c:forEach items= "${commentList}" var="comment">
<fieldset>
<legend>작성자 : ${comment.writer.id}</legend>
     <p>내용 : <a href='comment/detail?no=${comment.commentNumber}'>${comment.content}</a></p>
     <p>등록일 : ${comment.registrationDate}</p>
</fieldset>
</c:forEach>
</div><!-- .container -->

<!-- <script>
document.querySelector("#member-form").onsubmit = () => {
  if (document.querySelector("#f-name").value == "" ||
      document.querySelector("#f-email").value == "" ||
      document.querySelector("#f-password").value == "") {
    //window.alert("필수 입력 항목이 비어 있습니다.")
    Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};
</script> -->
