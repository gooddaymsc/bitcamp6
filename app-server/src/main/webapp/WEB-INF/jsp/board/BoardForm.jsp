<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
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
   border: 2px solid light gray; 
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
 }
 
 #f-tag{
   margin-left: 18px;
   margin-top: -5px;
   margin-bottom: -25px;
	 width:100px;
	 }
 
 #f-content{
  margin-left: 20px;
  width: 95%;
  height:300px; 
 }
 
 #board-btn{
  float: right;  
 }
 
</style>

<div class="html-board">
<br>
<h2>게시판</h2>
<h7>게시글쓰기</h7>
</div>

<main id= "main-holder">
<form action='add' method='post'>
<div id="title-label">
<h6><label for="exampleFormControlInput1" class="form-label">게시글 쓰기</label></h6>
</div>
<hr>
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label"></label>
  <input type="text" class="form-control" id="f-title" name="title" placeholder="게시글 제목">
</div>
<hr>
<div class="mb-3">
 <!--  <label for='exampleFormControlInput1' class="form-label">태그</label>  -->
  <input id='f-tag' class="form-control" type='text' name='tag' placeholder="#태그"><br>
  </div>
<hr>  
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label"></label>
  <textarea class="form-control" id="f-content" name="content" placeholder="게시글 내용 입력..." rows="3"></textarea>
</div>

<button id="board-btn" class="btn btn-outline-secondary btn-sm">등록</button><br>
</form>
</main>







