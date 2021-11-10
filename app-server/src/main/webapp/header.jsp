<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
header{
position: fixed;
top:0;
 left:0;
right:0;

  background-color: rgb(88, 1, 88);
}
 .logo  {
 position: relative;
 float:left;
  width: 100px;
  height: 60px;
  overflow: hidden;
 }
 
 .logo img {
 position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
 }
 
 #menu {
 float:right;
 position: relative;
 }

 .nav-item .nav-link {
 padding-left :30px;
 }
 
 .navbar{
  display: inline-block;
  font-size:bold;
  text-align: center;
}
 .container{
position:relative;
 }
 nav {
  margin-left: auto;
}
  .nav-item a::after {content:""; display: block; border-bottom: 3px solid #000; transition: width 250ms ease-out; left: auto; right: 0; width: 0;}
  .nav-item a:hover::after {width: 100%; left: 0; right: auto;}
</style>

<header>
<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container">
    <div class="logo">
         <a class="navbar-brand" href="../main/Menu.jsp"><img src="../image/logo.jpeg"></a>
    </div>
    <div id="menu">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">와인</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">위스키</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">브랜디 · 꼬냑</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">리큐르 · 보드카</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/list">민속주</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../product/ranking">Ranking</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="../board/list">Board</a>
        </li>
      </ul>
      <!-- <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="상품검색" aria-label="Search">
       <span><button class="btn btn-outline-success" type="submit">Search</button></span>
      </form> -->
    </div>
    </div>
      </div>
  </nav>    
</header>
