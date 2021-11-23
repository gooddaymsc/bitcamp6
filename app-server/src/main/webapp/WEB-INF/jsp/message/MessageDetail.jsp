<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<style>
#chat-room {
    height:600px;
    width:400px;
    padding: 6px 10px 7px;
    border-radius: 30px;
    xbackground: rgba(0, 0, 0, .3);
    background-color:#c7d4e0;
    margin: 8px 0;
    position: relative;
    text-shadow: 0 1px 1px rgba(0, 0, 0, .2);
}
#chat-title {
  position: relative;
  z-index: 2;
  background: rgba(0, 0, 0, 0.2);
  color: #fff;
  text-transform: uppercase;
  text-align: left;
  padding: 10px 10px 10px 50px;
}
  #chat-title h1 {
    font-weight: normal;
    font-size: 10px;
    margin: 0;
    padding: 0;
  }  
#chat-title .avatar {
    position: absolute;
    z-index: 1;
    top: 8px;
    left: 9px;
    border-radius: 30px;
    width: 30px;
    height: 30px;
    overflow: hidden;
    margin: 0;
    padding: 0;
    border: 2px solid rgba(255, 255, 255, 0.24);
    }
    img {
      width: 100%;
      height: auto;
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
    text-align:left;
    padding:0.3rem 0;
    color:white;
    font-size:0.5rem;
}

#chat-room .chat-message {
    margin-left:3rem;
    position:relative;
    padding:10px;
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
    font-size: 14px;
    box-shadow: 1px 1px 0 rgba(0,0,0,0.3);
}

#chat-room .chat-message.mine > div {
    background-color:#FDF01B;
    xborder:2px #FDF01B;
    text-align:right;
    float:right;
    box-shadow: -1px 1px 0 rgba(0,0,0,0.3);
}

#chat-room .chat-message.other > div {
    border:1px blue;
    background-color:white;
    padding: 10px;
    text-align:left;
    float:left;
    margin-right:100px;
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
    font-size:15px;
    line-height:30px;
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
   position: absolute;
    z-index: 1;
    top: 9px;
    right: 10px;
    color: #fff;
    border: none;
    background: #248A52;
    font-size: 13px;
    text-transform: uppercase;
    line-height: 1;
    padding: 5px 10px; 
    border-radius: 10px;
    outline: none!important;
    transition: background .2s ease;
}


</style>

<form action='update' method='post'>
   <a href='list' class="btn btn-outline-secondary btn-sm">이전</a><br><br>
<div id="chat-room">
    <div class="message-box" id="message-box">
 <div class="chat-title" id="chat-title">
    <h1>${theOtherId}</h1>
     <figure class="avatar">
      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/156381/profile/profile-80.jpg" /></figure>
  </div>
<c:set var="id" value="${loginUser.id}"/>
<c:forEach items="${messages}" var="message">
<c:choose>
<c:when test="${message.theOtherId eq loginUser.id}">
<div class="chat-message mine">
<div class="message-group" data-date-str="${message.registrationDate}">
    ${message.content}
</div></div>
</c:when>
    <c:otherwise>  
    <div class="chat-message other">
    <span><i class="fa fa-user"></i>${message.theOtherId}</span> 
    <div class="message-group" data-date-str="${message.registrationDate}"> ${message.content}
    </div></div>
   </c:otherwise>
</c:choose>
</c:forEach>

</div>
        <input type='hidden' id='text-input' type='text' name='roomNumber' value="${roomNumber}"><br><br>
        <input type='hidden' id='text-input' type='text' name='theOtherId' value="${theOtherId}"><br><br>
    <div class="input-box">
        <input type="text" id="text-input" name='content' placeholder="메세지 입력...">
            <button class="btn-submit">전송</button>
    </div>
</div>
</form>


<script>
var element = document.getElementById("message-box");
element.scrollTop = element.scrollHeight;
</script>

<script>
$('.follower').click(function(){
    $('#followModal').modal();   //id가 "followModal"인 모달창을 열어준다. 
    $('.modal-title').text("팔로우");    //modal 의 header 부분에 "팔로우"라는 값을 넣어준다. 
});
</script>
