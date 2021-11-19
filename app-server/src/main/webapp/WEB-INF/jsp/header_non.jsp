<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<style>
  header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
  }
  .logo {
    xborder: 1px solid #5f6;
    position: absolute;
    float: left;
    xpadding: 20px;
    left:100px;
    top:30px;
    width: 120px;
    height: 80px;
    xoverflow: hidden;
    z-index: 10;
    
  }
    .logo img {
    position: absolute;
    top: 0;
    left: 0;
    width: 120px;
    height: 80px;
    object-fit: fill;
    z-index: 10;
  }
  .wrap {
  position: absolute;
    xborder: 2px solid grey;
      width: 950px;
    height: 60px;
    float: left;
    left: 270px;
    top: 50px;
    xpadding-right : 150px;
  }

.search-class { 
  position: relative;
  width: 400px;
  top: 10px;
  right: -950px;
  margin-right: 10px;
  xborder: 1px solid;
  height: 35px;
  
}
.search-class a {
    text-decoration: none;
    color: black;
    cursor: pointer;
    vertical-align : middle;
    font-weight:bolder;
    margin-left:5px;
    font-size : small;
    
}

.f-text:hover {
    color: black;
} 
.search-bar-fieldset #f-search {
    position:absolute;
    top: 0px;
    right: -2px;
    height: 35px;
    text-indent: 5px;
    padding: 10px;
    line-height: 32px;
    border: 2px solid #530030;
    border: 2px solid #3b1358;
    font-size: 12px;
    color: #7c8389;
    background: #fff;
    width: 200px;
    margin-right: 65px;
    
}
.search-button {
    position:absolute;
    top: 0px;
    right : 10px;
    width: 55px;
    height: 35px;
    xmargin-right: 10px;
    xmargin-left: -2px;
    background-color: #3b1358;
    color: #fff;
}
 .nav a {
    position:relative;
    bottom: -20px;
    text-decoration: none;
    color: black;
    cursor: pointer;
    padding-left: 30px;
    padding-right: 30px;
    font-weight:bolder;
    margin-left:5px;
}
.nav a:hover {
    color: black;
    }
/* 네비바 마우스 오버시 밑줄 */
.dropdown-menu a::after {
}
.nav-item a::after {
  content: "";
  display: block;
  border-bottom: 3px solid #000;
  transition: width 250ms ease-out;
  left: auto;
  right: 0;
  width: 0;
}
  /* 네비바 마우스 오버시 밑줄 */
  .nav-item a:hover::after {
    width: 100%;
    left: 0;
    right: auto;
  }
/* 메뉴 마우스 오버시 드롭다운 */
  .nav-item:hover .dropdown-menu {
    display: block;
    margin-top: 20px;
  }
 /* 드롭다운 메뉴 속성 */
  .dropdown-menu a {
    position:relative;
    top :5px;
    color: #000;
    text-decoration: none;
    font-weight: normal;
    text-align: center;
  }
   .nav-link active {
  width: 1250px;
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
<div class="logo">
  <a class="navbar-brand" href="../main/menu"><img src="../image/logo.jpeg"></a>
</div>
<div class="search-class">
  <div class="search-class-menu">
    <a class='f-text' href='../main/loginMenu'>로그인</a>
    <a class='f-text' href='../buyer/form'>회원가입</a>
  </div>
  <fieldset class="search-bar-fieldset">
    <form action='search' method='post'>
      <button  class="search-button">검색</button>
      <input id="f-search" type="text" name="search" class="form-control" placeholder="검색어를 입력해주세요">
    </form>
  </fieldset>
</div>
<div class='wrap'>
<ul class="nav">
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="${contextPath}/app/product/listType?type=와인">와인</a>
  <ul class="dropdown-menu">
    <li><a href="${contextPath}/app/product/listSubType?no=1">레드</a></li>
    <li><a href="../product/listSubType?no=2">화이트</a></li>
    <li><a href="../product/listSubType?no=3">로제</a></li>
    <li><a href="../product/listSubType?no=4">스위트</a></li>
    <li><a href="../product/listSubType?no=5">스파클링</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=위스키">위스키</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=6">아메리칸</a></li>
    <li><a href="../product/listSubType?no=7">스카치(몰트)</a></li>
    <li><a href="../product/listSubType?no=8">아이리쉬</a></li>
    <li><a href="../product/listSubType?no=9">캐나다 위스키</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=브랜디/꼬냑">브랜디 · 꼬냑</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=10">브랜디</a></li>
    <li><a href="../product/listSubType?no=11">꼬냑</a></li>
    <li><a href="../product/listSubType?no=12">알마냑</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=리큐르/보드카">리큐르 · 보드카</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=13">리큐르</a></li>
    <li><a href="../product/listSubType?no=14">진</a></li>
    <li><a href="../product/listSubType?no=15">럼</a></li>
    <li><a href="../product/listSubType?no=16">보드카</a></li>
    <li><a href="../product/listSubType?no=17">데낄라</a></li>
    <li><a href="../product/listSubType?no=18">음료 · 시럽</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=전통주">전통주</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=19">한국</a></li>
    <li><a href="../product/listSubType?no=20">중국</a></li>
    <li><a href="../product/listSubType?no=21">일본</a></li>
    <li><a href="../product/listSubType?no=22">기타</a></li>
  </ul>
</li>
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="../product/ranking">Ranking</a>
  </li>
  <li class="nav-item">
    <a class="nav-link active" aria-current="page" href="../board/list">Board</a>
  </li> 
</ul>
</div>
</header>
</body>
</html>