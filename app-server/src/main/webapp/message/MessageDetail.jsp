<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<style>
#chat-room {
    height:400px;
    width:1100px;
    background-color:#6884b3;
    /* 임시 */
    outline:2px solid black;
    position:relative;
}

#chat-room .message-box {
    padding:0 0.7rem;
    overflow-y:auto;
    height:calc(100% - 4.4rem);
    padding:10px;
}

#chat-room .message-group::before {
    content:attr(data-date-str);
    display:block;
    background-color:rgba(0,0,0,0.15);
    border-radius:1rem;
    text-align:center;
    padding:0.3rem 0;
    color:white;
    font-size:1.6rem;
}

#chat-room .chat-message {
    margin-left:3rem;
    position:relative;
}

#chat-room .chat-message > section {
    position:absolute;
    top:0;
    left:-3rem;
}

#chat-room .chat-message > span {
    display:block;
}

#chat-room .chat-message > section {
    font-size:3rem;
}

#chat-room .chat-message::after {
    content:"";
    display:block;
    clear:both;
}

#chat-room .chat-message > div {
    background-color:white;
    float:left;
    padding:0.8rem;
    border-radius:1rem;
    clear:both;
    font-weight:bold;
    font-size:1.46rem;
    box-shadow: 1px 1px 0 rgba(0,0,0,0.3);
}

#chat-room .chat-message.mine > div {
    background-color:#FDF01B;
    float:right;
    box-shadow: -1px 1px 0 rgba(0,0,0,0.3);
}

#chat-room .chat-message.other > div {
    background-color:#FDF01B;
    float:left:0px;
    box-shadow: -1px 1px 0 rgba(0,0,0,0.3);
}

#chat-room .chat-message.mine > span {
    display:none;
}

#chat-room .chat-message.mine > section {
    display:none;
}

#chat-room .input-box {
    position:absolute;
    bottom:0;
    left:0;
    width:100%;
}

#chat-room .input-box #text-input {
    width:100%;
    display:block;
    border:0;
    outline:none;
    padding-right:8.5rem;
    padding-left:4.8rem;
    padding-top:0.1rem;
    font-size:1.4rem;
    line-height:4rem;
    font-weight:bold;
    box-sizing:border-box;
}

#chat-room .input-box .btn-plus {
    position:absolute;
    top:0;
    left:0;
    width:4rem;
    height:100%;
    background-color:#D1D1D1;
}

#chat-room .input-box .btn-plus > i {
    position:absolute;
    top:50%;
    left:50%;
    transform:translateX(-50%) translateY(-45%);
    color:white;
    font-size:3rem;
}

#chat-room .input-box .btn-submit {
    position:absolute;
    right:1rem;
    top:50%;
    transform:translateY(-50%);
    padding:10px;
    background-color:#fdf01b;
    padding:10px;
    color:#bfb73d;
    border-radius:3px;
    font-size:1.3rem;
    box-shadow:0 1px 10px rgba(0,0,0,0.2);
    user-select:none;
}

#chat-room .input-box .btn-emo {
    position:absolute;
    right:6rem;
    top:50%;
    transform:translateY(-50%);
    font-size:2rem;
    color:#CBCBCB;
    user-select:none;
}
</style>

<h1>Message</h1>

<form action='update' method='post'>

<div id="chat-room">
    <div class="message-box">

<c:set var="id" value="${loginUser.id}"/>
<c:forEach items="${messages}" var="message">
<c:choose>
<c:when test="${theOtherId eq loginUser.id}">
<div class="chat-message other">
<div class="message-group" data-date-str="${message.registrationDate}">
    <section><i class="fa fa-user"></i></section>
    <span>${loginUser.id}</span> : ${message.content}
</div></div>
</c:when>
    <c:otherwise>  
    <div class="chat-message mine" style = float:right:0px>
    <div class="message-group" data-date-str="${message.registrationDate}">
    <section><i class="fa fa-user"></i></section>
    <span>${message.theOtherId}</span> : ${message.content}
    </div></div>
   </c:otherwise>
</c:choose>
</c:forEach>

</div></div>

    <div class="input-box">
    <div class="btn-plus">
<!-- type='hidden' -->
 <input type='hidden' id='f-roomNo' type='text' name='no' value="${roomNumber}"><br><br>
 <input type='hidden' id='f-other' type='text' name='other' value="${theOtherId}"><br><br>
 <input id='f-content' type='text' name='content'><br><br>
 <!--  <button class="btn btn-primary" >전송</button> -->
  <button class="btn btn-primary ">전송</button> 
  </div></div>
  
   <a href='list' class="btn btn-outline-primary btn-sm">이전</a><br><br>
</form>

<!-- 오른쪽 바 하단 가르키기? -->
<!-- <script type="text/javascript">
$(".message-box").scrollTop($(".message-box")[0].scrollHeight);
</script> -->


