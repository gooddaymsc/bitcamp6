<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>메인메뉴</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="../css/common.css">
  <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
  <link rel="icon" href="/favicon.ico" type="image/x-icon">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  
  <style>
   .container {
      border: 1px solid red;
      width:100%;
    }
    
    tr a {
        text-decoration: none;
        color: black;
    }
    tr a:visited {
        color: black;
    }
    tr:hover {
        cursor: pointer;
    }
    .d-flex{
      width: 440px;
      border: 1px solid green;
    }
    
    .intro_bg{
      background-image:url("../image/alcohol5.jpg");
      width:100%;
      height:618px;
      background-repeat: no-repeat;
      background-position: center;
      background-size:contain;
    }
    
    .header{
      margin:auto;
      border: 1px solid red;
    }
    
  </style>
</head>

<body>
  <div class="intro_bg"><div></div>

 <div id="content">
  <c:when  test="${loginUser eq null}">
  <a href='./loginMenu' class="btn btn-primary">로그인</a>
  <a href='../buyer/form' class="btn btn-primary">회원가입</a><br>
  <a href='../board/list' class="btn btn-primary">게시판</a>
  <a href='../product/ranking' class="btn btn-primary">오늘의 술</a>
  <a href='../product/list' class="btn btn-primary">상품</a>
  </c:when>
  <c:otherwise>
  <p>아이디 : ${loginUser.id}</p>
  <br>
  <a href='logout' class="btn btn-primary">로그아웃</a>
  </c:otherwise> 
</div> <!-- content -->
<jsp:include page="${contentUrl}"/>
<jsp:include page="/footer.jsp"/>

</div><!-- .container -->
</body>
</html>