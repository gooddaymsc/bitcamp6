<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
  header {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
  }
  .logo {
    position: relative;
    float: left;
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
    float: left;
    position: relative;
  }
  .nav-item .nav-link {
    padding-left: 30px;
  }
  .navbar {
    display: inline-block;
    font-size: bold;
    text-align: center;
  }
<<<<<<< HEAD
  /* 버튼 중앙 정렬 */
=======
  /* 햄버거 버튼 중앙 정렬 */
>>>>>>> e8a7721b2279855918e1cb971342dd1f8065c970
  .navbar-toggler {
    margin-top: 10px;
  }
  /* 버튼 중앙 정렬 */
  .collapse {
    padding-top: 10px;
  }
  .navbar-nav li {
    padding-left: 10px;
    font-weight: bold;
    color:blue;
  }
  /* 네비바 마우스 오버시 밑줄 */
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
    margin-top: 0;
  }
 /* 드롭다운 메뉴 속성 */
  .dropdown-menu a {
    color: #000;
    text-decoration: none;
    font-weight: normal;
  }
<<<<<<< HEAD
/*검색바 아래 키워드 */
=======
  /* 검색창 */
.xans-layout-searchheader #f-search {
    text-indent: 5px;
    padding: 0;
    line-height: 32px;
    border: 2px solid #530030;
    border: 2px solid #3b1358;
    font-size: 12px;
    color: #7c8389;
    background: #fff;
    width: 298px;
    height: 35px;
    float: right;
}
.search-button {
    width: 55px;
    height: 35px;
    margin-left: -2px;
    background-color: #3b1358;
    color: #fff;
    float: right;
}
/*검색창 아래 키워드 */
>>>>>>> e8a7721b2279855918e1cb971342dd1f8065c970
  .top_popular a {
    color: #ababab;
    padding: 0 5px;
    font-size: 13px;
    font-weight: 300;
  }
  .top_popular a:before {
    content: "#";
    display: inline;
}
  .top_popular a:hover {
    color: #000;
    display: inline;
}
  .top_popular a:hover::before {
    color: #000;
    display: inline;
}
.topArea .log {
  float: right;
}
 .topArea2 {
  float: right;
 }
</style>

<header>
<div class="wrap">
  <div class="topArea">
  <div class="log">
<a href='./loginMenu'>로그인</a>
<a href='../buyer/form'>회원가입</a>
  </div>
  </div>
    <div class="topArea2">
      <div class="xans-element- xans-layout xans-layout-searchheader header-form">
        <fieldset class="search-bar-fieldset">
        <form action='search' method='post'>
          <button  class="search-button">검색</button>
          <input id="f-search" type="text" name="search" class="form-control" placeholder="검색어를 입력해주세요">
         </form>
        </fieldset>
        <div class="xans-element- xans-search xans-search-hotkeyword top_popular -hover">
          <p>
            <a href="" class="xans-record-">발렌타인</a>
            <a href="" class="xans-record-">조니워커</a>
            <a href="" class="xans-record-">앱솔루트</a>
            <a href="" class="xans-record-">예거마이스터</a>
          </p>
        </div>
      </div>
    </div>
<<<<<<< HEAD
    -->
<!--   </nav>
 -->  <nav class="navbar navbar-expand-lg navbar-light">
=======
    <!--  
 <form action='search' method='post'>  
<div class="mb-3 row">
  <label for='f-search' class="col-sm-1 col-form-label">검색</label>
    <div class="col-sm-2">
    <input id='f-search' type='text' name='search' class="form-control">
  </div>
</div>
</form>
-->
    
  <nav class="navbar navbar-expand-lg navbar-light">
>>>>>>> e8a7721b2279855918e1cb971342dd1f8065c970
    <div class="container-header">
      <div class="logo">
        <a class="navbar-brand" href="../main/menu"><img src="../image/logo.jpeg"></a>
      </div>
      <div id="menu">
 `         <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02"
          aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="../product/listWine">와인</a>
              <ul class="dropdown-menu">
                <li><a href="">레드</a></li>
                <li><a href="">화이트</a></li>
                <li><a href="">로제</a></li>
                <li><a href="">스위트</a></li>
                <li><a href="">스파클링</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="../product/listWhiskey">위스키</a>
              <ul class="dropdown-menu">
                <li><a href=>아메리칸</a></li>
                <li><a href=>스카치(몰트)</a></li>
                <li><a href=>아이리쉬</a></li>
                <li><a href=>캐나다 위스키</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="../product/listBrandy">브랜디 · 꼬냑</a>
              <ul class="dropdown-menu">
                <li><a href=>브랜디</a></li>
                <li><a href=>꼬냑</a></li>
                <li><a href=>알마냑</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="../product/listVodka">리큐르 · 보드카</a>
              <ul class="dropdown-menu">
                <li><a href=>리큐르</a></li>
                <li><a href=>진</a></li>
                <li><a href=>럼</a></li>
                <li><a href=>보드카</a></li>
                <li><a href=>데낄라</a></li>
                <li><a href=>음료 · 시럽</a></li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="../product/listTrad">전통주</a>
              <ul class="dropdown-menu">
                <li><a href=>한국</a></li>
                <li><a href=>중국</a></li>
                <li><a href=>일본</a></li>
                <li><a href=>기타</a></li>
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
      </div>
    </div>
  </nav>
</div>
</header>