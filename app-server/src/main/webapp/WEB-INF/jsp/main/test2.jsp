<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<style>
  header {
    background-color: #983246;
    position: fixed;
    height:150px;
    top: 0;
    left: 0;
    right: 0;
  }
  .logo {
    xborder: 1px solid #5f6;
    position: absolute;
    float: left;
    xpadding: 20px;
    margin: 40 0 0 100;
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
    top: 55px;
    xpadding-right : 150px;
  }

.search-class { 
  position: relative;
  background-color:#700F33;
  width: 100%;
  top: 0px;
  xright: -950px;
  xmargin-right: 10px;
  xborder: 1px solid;
  height: 35px;
  
}
.search-class a {
  position: relative;
  left: 1080px;
    text-decoration: none;
    color: white;
    cursor: pointer;
    vertical-align : middle;
    font-weight:bolder;
    margin-left:5px;
    font-size : small;
    
}

.f-text:hover {
    color: black;
} 
  
/* .search-button{
    xposition: absolute;
    xrigth: -900px;
    xmargin-rigth: 100px;
    xtop: 0;
    xleft: -1100px;
    width: 20x;
    height: 20px;
    xobject-fit: fill;
    xz-index: 10;
}  */
.search-button img{
  position: absolute;
  top:5px;
  right:120px;
      width: 20x;
    height: 20px;
    object-fit: fill;
} 
button 
  {border:0; padding:0; background:transparent; cursor:pointer; *overflow:visible; }

 .search-bar-fieldset #f-search {
    position:absolute;
    top: 0px;
    xmargin-right: 40px;
    right: 50px;
    height: 30px;
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

 .nav a {
    position:relative;
    bottom: -20px;
    text-decoration: none;
    color: white;
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
.nav-item {
  xborder: 2px solid #000;
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
  .dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
}
@media(max-width:1000px){
  .search-class-menu {
  display:none;}
}
.dropdown:hover .dropdown-content {
  display: block;
}
 /* /* 드롭다운 메뉴 속성 */
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
 } */
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
<!--   <div class="search-class-menu">
    <a class='f-text' href='../main/loginMenu'>로그인</a>
    <a class='f-text' href='../buyer/form'>회원가입</a>
  </div> -->
  <fieldset class="search-bar-fieldset">
    <div class="search-class-menu">
      <a class='f-text' href='../main/loginMenu'>로그인</a>
      <a class='f-text' href='../buyer/form'>회원가입</a>
      <form action='search' method='post'>
        <input id="f-search" type="text" name="search" class="form-control" placeholder="검색어를 입력해주세요">
        <button class="search-button"><img class="search-img" src="../image/search.png"></button>
      </form>
    </div>
  </fieldset>
</div>
<div class='wrap'>
<ul class="nav">
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=와인">와인</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=1">레드</a></li>
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