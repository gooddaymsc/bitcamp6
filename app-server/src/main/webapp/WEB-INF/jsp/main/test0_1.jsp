<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<style>
  header {
  height: 400px;
  background-image: url('../image/menu.jpg');
      background-repeat : no-repeat;
    background-size : cover;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
  }

  .logo1 {
    xborder: 1px solid #5f6;
    position: absolute;
    float: left;
    xpadding: 20px;
    left:600px;
    top:80px;
    width: 160px;
    height: 120px;
    xoverflow: hidden;
    z-index: 10;
    }

    .logo1 img {
    position: absolute;
    top: 0;
    left: 0;
    width: 300px;
    height: 120px;
    object-fit: fill;
    z-index: 10;
  }
@media(max-width:1000px){
  .logo1 {
  display:none;
  }
  .nav a{
    display:none;
  }
}
@media(min-width:1000px){
  .logo2 {
  display:none;
  }
} 
  .logo2 {
    xborder: 1px solid #5f6;
    position: absolute;
    float: left;
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
    xvertical-align : bottom;
    margin-top:10px;
    font-weight:bolder;
}

.f-text:hover {
    color: black;
} 
.search-bar-fieldset #f-search {
    position:absolute;
    top: -2px;
    right: 30px;
    height: 35px;
    text-indent: 5px;
    padding: 10px;
    line-height: 32px;
    border: 2px solid #530030;
    border: 2px solid #3b1358;
    font-size: 12px;
    color: #7c8389;
    background: #fff;
    width: 230px;
    xmargin-right: 60px;
    
}

  .nav {
  position: relative;
    float: left;
  xfloat: center;
    xborder: 2px solid grey;
      width: 950px;
    height: 60px;
    left: 300px;
    top: 170px;
    xpadding-right : 150px;
  }
 .nav a {
    position:relative;
    bottom: -20px;
    text-decoration: none;
    color: black;
    cursor: pointer;
    padding-left: 30px;
    padding-right: 30px;
    xfont-weight:bolder;
    margin-left:10px;
    font-size : x-large;
    font-family: fantasy;
    
}
.nav a:hover {
    color: black;
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
  /* ????????? ????????? ????????? ?????? */
  .nav-item a:hover::after {
    width: 100%;
    left: 0;
    right: auto;
  }
/* ?????? ????????? ????????? ???????????? */
  .nav-item:hover .dropdown-menu {
    display: block;
    margin-top: 20px;
  }
 /* ???????????? ?????? ?????? */
  .dropdown-menu a {
    position:relative;
    top :5px;
    color: #000;
    text-decoration: none;
    font-weight: normal;
    text-align: center;
    font-size: small;
  }
   .nav-link active {
  width: 1250px;
  }
button 
  {border:0; padding:0; background:transparent; cursor:pointer; *overflow:visible; }

 .search-button {
  border: 5px black;
  float:right;
    position:absolute;
    top: 0px;
    right : 17px;
    width: 55px;
    height: 35px;
    background-repeat : repeat;
    background-size : cover;
    z-index:6;
    
} 
.search-button img{
  xposition: absolute;
  xtop:5px;
  xright:120px;
      width: 20x;
    height: 20px;
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
<div class="logo1">
  <a class="navbar-brand1" href="../main/menu"><img src="../image/logo.jpeg"></a>
</div>
<div class="logo2">
  <a class="navbar-brand2" href="../main/menu"><img src="../image/logo.jpeg"></a>
</div>
<div class="search-class">
  <div class="search-class-menu">
    <a class='f-text' href='../main/loginMenu'>?????????</a>
    <a class='f-text' href='../buyer/form'>????????????</a>
  </div>
  <fieldset class="search-bar-fieldset">
    <form action='search' method='post'>
      <button  class="search-button"><img class="search-img" src="../image/search.png"></button>
      <input id="f-search" type="text" name="search" class="form-control" placeholder="???????????? ??????????????????">
    </form>
  </fieldset>
</div>
<div class='wrap'>
<ul class="nav">
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=??????">Wine</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=1">??????</a></li>
    <li><a href="../product/listSubType?no=2">?????????</a></li>
    <li><a href="../product/listSubType?no=3">??????</a></li>
    <li><a href="../product/listSubType?no=4">?????????</a></li>
    <li><a href="../product/listSubType?no=5">????????????</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=?????????">Whiskey</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=6">????????????</a></li>
    <li><a href="../product/listSubType?no=7">?????????(??????)</a></li>
    <li><a href="../product/listSubType?no=8">????????????</a></li>
    <li><a href="../product/listSubType?no=9">????????? ?????????</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=?????????/??????">Brandy ?? Cognac</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=10">?????????</a></li>
    <li><a href="../product/listSubType?no=11">??????</a></li>
    <li><a href="../product/listSubType?no=12">?????????</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=?????????/?????????">liqueur ?? Vodka</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=13">?????????</a></li>
    <li><a href="../product/listSubType?no=14">???</a></li>
    <li><a href="../product/listSubType?no=15">???</a></li>
    <li><a href="../product/listSubType?no=16">?????????</a></li>
    <li><a href="../product/listSubType?no=17">?????????</a></li>
    <li><a href="../product/listSubType?no=18">?????? ?? ??????</a></li>
  </ul>
</li>
<li class="nav-item">
  <a class="nav-link active" aria-current="page" href="../product/listType?type=?????????">Traditional</a>
  <ul class="dropdown-menu">
    <li><a href="../product/listSubType?no=19">??????</a></li>
    <li><a href="../product/listSubType?no=20">??????</a></li>
    <li><a href="../product/listSubType?no=21">??????</a></li>
    <li><a href="../product/listSubType?no=22">??????</a></li>
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