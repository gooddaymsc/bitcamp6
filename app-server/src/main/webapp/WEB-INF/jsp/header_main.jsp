<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}" />

<html>

<style>

footer { 
  position:absolute;
  top:1600px;
}

  .logo1 {
    position: absolute;
    xborder: 3px solid red;
    xleft: 41%;
    top:50px;
    left:50%;
    transform:translate(-50%,0);
    width: 300px;
    height: 120px;
    xoverflow: hidden;
    }

    .logo1 img {
    position: absolute;
    top: 0;
    left: 0;
    width: 300px;
    height: 120px;
    object-fit: fill;
    z-index: 4;
  }
@media(max-width:800px){
  .logo1 {
  display:none;
  }
  .nav a{
    display:none;
  }
}
@media(min-width:800px){
  .logo2 {
  display:none;
  }
} 


  .logo2 {
    xborder: 1px solid #5f6;
    position: absolute;
    xfloat: left;
    xpadding: 20px;
    left:50px;
    top:20px;
    width: 120px;
    height: 80px;
    xoverflow: hidden;
    z-index: 10;
    
  }
    .logo2 img {
    position: absolute;
    top: 0;
    left: 0;
    width: 120px;
    height: 80px;
    object-fit: fill;
    z-index: 10;
  }
.search-class { 
  xborder : 2px solid black;
  position: relative;
  width: 620px;
  top: 0;
  left:650px;
  xmargin-right: 150px;
  height: 35px;
  z-index: 1;
}

.f-text:hover {
    color: black;
} 
.search-bar-fieldset #f-search {
  xborder : 2px solid green;
    position:absolute;
    top: 0;
    left:375px;
    height: 30px;
    text-indent: 5px;
    padding: 10px;
    line-height: 32px;
    border: 2px solid #45483c;
    font-size: 12px;
    color: #7c8389;
    background: #fff;
    width: 180px;
    xmargin-right: 0;
    xz-index: 10;
}
.search-class-menu {
  position:absolute;
  right:250px;
  xmargin-right: 200px;
  xright : 110px;
  xborder: 2px solid yellow;
  height: 35px;
}
.search-class-menu a {
  position: relative;
    top: 4px;
    font-weight:bold;
    font-size : 10px;
    text-decoration: none;
    color: #c5c6c2;
    cursor: pointer;
    xvertical-align : bottom;
    xmargin-top:10px;
    padding:5px;
}
.wrap {
  position: absolute;
  top:0;
  right:0;
  left:0;
  xborder: 2px solid white;
  text-align:center;
      wegiht: 100%;
      height: 400px;
      z-index:4;
  } 
 .nav {
  position: absolute;
    xborder: 2px solid grey;
      width: 880px;
    height: 60px;
    top: 230px;
    left:50%;
    transform:translate(-50%,0);
      text-align: center;
    display:inline-block;
    
  } 
 .nav a {
    position:relative;
    bottom: -20px;
    text-decoration: none;
    color: white;
    cursor: pointer;
    padding-left: 30px;
    padding-right: 30px;
    xfont-weight:bolder;
    xmargin-left:10px;
    font-size : x-large;
    font-family: fantasy;
    
}
.nav a:hover {
    color: black;
    }
.dropdown-menu a::after {
  xcontent: "";
  display: block;
  xfont-weight: 700;
  xborder-bottom: 3px solid #000;
  transition: width 250ms ease-out;
  left: 0;
  right: 0;
  width: 0;
}
/* 네비바 마우스 오버시 밑줄 */
.nav-item > a::after {
  content: "";
  display: block;
  border-bottom: 3px solid #000;
  transition: width 250ms ease-out;
  left: auto;
  right: 0;
  width: 0;
}

  .nav-item a:hover::after {
    width: 100%;
    left: 0;
    right: auto;
  }
/* 메뉴 마우스 오버시 드롭다운 */
  .nav-item:hover .dropdown-menu {
    xborder: 2px solid blue;
    display: block;
    margin-top: 20px;
    
  }
  .dropdown-menu {
  position:relative;
  xborder : 2px solid green;
  }
  
 /* 드롭다운 메뉴 속성 */
  .dropdown-menu a {
    position:relative;
    top :5px;
    color: #000;
    xborder: 2px solid black;
    text-decoration: none;
    font-weight: normal;
    text-align: center;
    font-size: small;
  }
/*    .nav-link active {
  width: 1250px;
  } */
button 
  {border:0; padding:0; background:transparent; cursor:pointer; *overflow:visible; }

 .search-button {
  xborder: 5px solid black;
    position:absolute;
    top: 1px;
    right: 70px;
    xright : 17px;
    width: 20px;
    height: 20px;
    background-repeat : repeat;
    background-size : cover;
    z-index:2;
    
} 
.search-button img{
  xposition: absolute;
  xtop:5px;
  xright:120px;
      width: 20x;
    height: 20px;
        object-fit: fill;
    
        xz-index: 10;
    
} 
</style>
<head>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="../css/common.css"> 
  <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
  <link rel="icon" href="../favicon.ico" type="image/x-icon">
  
  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>
<body>

<header>
<!-- 로고 -->

<!-- 메인로고 -->
<div class='wrap'>
<!-- 검색 -->
	<div class="logo1">
	  <a class="navbar-brand1" href="../main/menu"><img src="../image/logoW.png"></a>
	</div>
	<div class="logo2">
	  <a class="navbar-brand2" href="../main/menu"><img src="../image/logoW.png"></a>
	</div>
	<div class="search-class">
	  <div class="search-class-menu">
	   <c:choose> 
      <c:when  test="${loginUser eq null}">
	    <a id='visitor' class='f-text' href='../main/loginMenu'>로그인</a>
	    <a id='visitor' class='f-text' href='../buyer/form'>회원가입</a>
	    </c:when>
      <c:when  test="${(loginUser.authority eq 2) ||(loginUser.authority eq 4)}">
	    <a id='loginUser'class='f-text' href='../main/myPage'>MyPage</a>
      </c:when>
      <c:when  test="${loginUser.authority eq 8}">
	    <a id='admin' class='f-text' href='../main/myPage'>관리자페이지</a>
      </c:when>
	    
	   </c:choose>
	  </div>
	  <fieldset class="search-bar-fieldset">
	    <form action='search' method='post'>
	      <button  class="search-button"><img class="search-img" src="../image/search.png"></button>
	      <input id="f-search" type="text" name="search" class="form-control" placeholder="검색어를 입력해주세요">
	    </form>
	  </fieldset>
	</div>
<ul class="nav">
<li class="nav-item">
  <a id='type-menu' class="nav-link active" aria-current="page" href="${contextRoot}/app/product/listType?type=와인">Wine</a>
  <ul class="dropdown-menu">
    <li><a href="${contextRoot}/app/product/listSubType?no=1">레드</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=2">화이트</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=3">로제</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=4">스위트</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=5">스파클링</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="${contextRoot}/app/product/listType?type=위스키">Whiskey</a>
  <ul class="dropdown-menu">
    <li><a href="${contextRoot}/app/product/listSubType?no=6">아메리칸</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=7">스카치(몰트)</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=8">아이리쉬</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=9">캐나다 위스키</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="${contextRoot}/app/product/listType?type=브랜디/꼬냑">Brandy · Cognac</a>
  <ul class="dropdown-menu">
    <li><a href="${contextRoot}/app/product/listSubType?no=10">브랜디</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=11">꼬냑</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=12">알마냑</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="${contextRoot}/app/product/listType?type=리큐르/보드카">liqueur · Vodka</a>
  <ul class="dropdown-menu">
    <li><a href="${contextRoot}/app/product/listSubType?no=13">리큐르</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=14">진</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=15">럼</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=16">보드카</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=17">데낄라</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=18">음료 · 시럽</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="${contextRoot}/app/product/listType?type=전통주">Traditional</a>
  <ul class="dropdown-menu">
    <li><a href="${contextRoot}/app/product/listSubType?no=19">한국</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=20">중국</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=21">일본</a></li>
    <li><a href="${contextRoot}/app/product/listSubType?no=22">기타</a></li>
  </ul>
<!-- </li>
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="../product/ranking">Ranking</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="../board/list">Board</a>
  </li>  -->
</ul>
</div>
</header>
</body>
</html>