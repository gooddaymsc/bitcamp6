<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	  body {
	    height: 100%
	    margin: 0; 
	    font-family: Arial, Helvetica, sans-serif; 
	    display: grid; justify-items: center; 
	    align-items: center; 
	    min-width:80%; 
	  }  
	  
	  html, body {
	    height: 100%; 
	    min-width:80%; 
	  } 
	  
	  #main-holder {
	   width: 80%;
	   height: 90%; 
	   display: grid; 
	   background-color: #f7f7fc;
	   border: 2px solid lightgray; 
	   min-width:700px; 
	 } 
	 
	 #top-title{
	  font-size: 19px;
	  text-align: center;
	 }
	 
	 #title-label{
	  margin-top:16px;
	  margin-bottom: -7px;
	  text-align: center;
	 }
	 
	 #board-title{
	  margin-left:47px;
	  margin-top:15px; 
	 }
	 
	 #f-title{
	  margin-left: 18px;
	  margin-top: -16px;
	  width: 95%;
	  font-size: 36px;
	 }

  #board-header{
    font-size: 14px;
    margin-left: 20px;
   }
  

  #f-writer{
    font-size: 16px;
    font-weight: bold; 
  }
  
  #f-content{
    margin-left: 20px;
    width:90%;
  }
  
.comment-set {
  position : relative;
  left : 60px;
  width : 800px;
  xborder : 2px solid green;
}
.comment-container {
  position: relative;
  xborder : 2px solid red;
  width: 80%;
  padding:50px 0;
} 
.cmt-box {
  position : relative;
  width : 90%;
  xborder : 2px solid blue; 
}
.comment-add {
  position : absolute;
  xleft : 60px;
  xborder : 1px solid grey;
  border-radius: 7px; 
  width : 90%;
  height: 150px;
  }
  
.col-sm-10 {
position: absolute;
  xborder : 2px solid red;
  border-radius:50%; 
  width : 50px;
  height: 50px;
}
.col-sm-10 img {
    position: absolute;
    border-radius:50%; 
    top: 0;
    left: 0;
    width: 50px;
    height: 50px;
    object-fit: fill;
}
.comment-img #comment-img-chat {
  width : 20px;
  height: 20px;
}
.comment-cng {
  position:absolute;
  xtop : 0;
  width: 150px;
  left : 900px;
  margin-right:0;
  xfloat : right;
  xborder : 2px solid black;
}
#f-content-comment {
position : absolute;
left : 60px;
xborder : 2px solid yellow;
width:80%;
}
#comment-add-button {
float : right;
left : 1000px;
}
#f-photo-id {
  position: relative;
  top : 60px;
}
 /*    :root {
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
    } */
  
}

body {
  text-align: center;
}
a {
  text-decoration: none;
  color: black;
}
a:visited {
  color: black;
}
a:hover {
  color: black;
    cursor: pointer;
}
.board-menu {
position: relative;
  left : 900px;
xfloat: right;
}
  </style>
  
<div class="html-board">
<br>
<h2>게시판</h2>
<h6>게시글보기</h6>
</div>
<div class="board-menu">
  <c:choose> 
  <c:when test="${not empty loginUser}">
  <a href='#' onclick="btn_detail('${loginUser.id}')" class="btn btn-outline-secondary btn-sm">수정</a>
  <a href='#' onclick="btn_delete('${loginUser.id}')" class="btn btn-outline-secondary btn-sm">삭제</a>
</c:when>
</c:choose> 
    <a href='list' class="btn btn-outline-secondary btn-sm">목록</a>
</div>
<main id= "main-holder">  
 <section class="section-1 con-min-width">
  <div class="con">  
<div class="article-list">
<div class="mb-3 row" style= 'display:none;'>
  <label for='f-number' class="col-sm-2 col-form-label">번호</label> 
  <div class="col-sm-6">
    <input id='f-number' type='text' name='boardNumber' value='${board.boardNumber}' readOnly ><br>
  </div>
</div>

<div id="title-label">
<div class="mb-3 row"  id="title-container">
  <label for='f-title' class="col-sm-2 col-form-label"></label>
  <div class="col-sm-6">
    <label for='f-title' id='f-title' class="col-sm-2 col-form-label">${board.title}</label>
  </div>
</div>
</div>

<div class="board-header" id="board-header">
  <label for='f-writer' class="col-sm-2 col-form-label"></label>
    <div class="board-header-second">
    <input type='hidden' id='btn-writer' type='text' name='writer' value='${board.writer.id}'readOnly><br>
     <label for="f-writer" id='f-writer' class="col-sm-2 col-form-label">${board.writer.id}</label>
     <br><label for='f-registrationDate' class="col-sm-2 col-form-label">${board.registrationDate}</label>
     <label for='f-views' class="col-sm-2 col-form-label">조회 ${board.views}</label>    
  </div>
</div>

<hr>
<br>
<div id="title block">
<div class="board-content" id="board-content">
   <label for='f-content' id="f-content">${board.content}</label>
  </div>
</div>
<br>
<br>
<br>
<div class="mb-3 row">
	<label for='f-tag' class="col-sm-2 col-form-label"># 태그</label> 
  <div class="col-sm-6">
  <button id='f-tag' class="btn">${board.tag}</button>
  </div>
</div>
</div>
</div>
</section>
</main>
<!--        <button class="btn btn-outline-secondary btn-sm">변경</button>
       <button class="btn btn-outline-secondary btn-sm">삭제</button> -->
<div class="comment-container">
  <div class='comment-img' >
    <img id="comment-img-like" src="../../image/icons/icon_hot.gif"><a href='like?no=${board.boardNumber}'> 좋아요</a> ${board.likes}  &nbsp;  <img id="comment-img-chat" src="../../image/icons/001-chat.png"> 댓글 ${fn:length(commentList)}
  </div>
  <hr>
	<h4>댓글</h4><br>
	<div class="cmt-box">
	<c:forEach items= "${commentList}" var="comment">
	<fieldset>
    <div class="col-sm-10">
        <img id="f-photo-image" onerror="${contextPath}/upload/member/${comment.writer.photo}_100x100.jpg" src="${contextPath}/image/profile.png">
    </div>
	   <div class="comment-cng">
	   <c:choose> 
			 <c:when test="${loginUser.id eq comment.writer.id}">
			  <a href='#' class="btn btn-outline-secondary btn-sm">수정</a>
			  <a href='#' onclick="btn_delete_comment(${comment.commentNumber})" class="btn btn-outline-secondary btn-sm">삭제</a>
			</c:when>
		</c:choose>
		</div>
	<div class="comment-set">
	   <input type='hidden' id="f-comment-no" name='commentNumber' class="form-control" value='${comment.commentNumber}' readOnly >
	   <p style="font-weight: bold;">${comment.writer.id}</p>
	   <p>${comment.content}</p>
	   <p style="font-size: small; color: grey;">${comment.registrationDate}</p>
  </div>
    <hr>
	</fieldset>
	</c:forEach>
	</div>
	<div class="comment-add">
	 <div class="col-sm-10">
     <img id="f-photo-image" onerror="${contextPath}/upload/member/${loginUser.photo}_100x100.jpg" src="${contextPath}/image/profile.png">
    <p id="f-photo-id">${loginUser.id}</p>
   </div>
	<form action='./comment/add' method='post'>
  <input type='hidden' id='f-number' type='text' name='boardNumber' value='${board.boardNumber}' readOnly><br>
  <div class="col-sm-4">
    <input id='f-content-comment' type='text' name='content' class="form-control" value='댓글 입력...'>
  </div>
  <button id='comment-add-button' class="btn btn-outline-secondary btn-sm">등록</button><br><br>
  </form>
  </div>
</div><!-- .container -->

<script>
function btn_detail(id) {
 var no = document.getElementById('f-number').value;
 const board_id = document.getElementById('btn-writer').value;
   if (id==board_id) {
      location.href="${contextRoot}/drinker/app/board/detail?no="+no;
   } else {
     alert("작성자가 아니므로 수정할 수 없습니다.");
     return false;
   }
 }
</script>
<script>
function btn_delete(id) {
	var no = document.getElementById('f-number').value;
	const board_id = document.getElementById('btn-writer').value;
    if ((id==board_id)||(id=="admin")) {
      if (confirm("정말 삭제하시겠습니까?")==true) {
    	   location.href="${contextRoot}/drinker/app/board/delete?no="+no;
      } else {
    	  return false;
      }
    } else {
    	alert("작성자가 아니므로 삭제할 수 없습니다.");
    	return false;
    }
  }
</script>
<script>
function btn_delete_comment(no) {
    if (confirm("정말 삭제하시겠습니까?")==true) {
       location.href="${contextRoot}/drinker/app/board/comment/delete?no="+no;
    } else {
      return false;
    }
  }
</script> 
