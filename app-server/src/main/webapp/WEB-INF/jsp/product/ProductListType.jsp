<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
  .responsive {
    width: 250px;
    height: 500px;    
    padding: 10px 6px;
    margin-left: 30px;
    float: left;
    position: relative;
    z-index: 2;
  }
  
  div.gallery {
    border-radius: 5px;
    overflow: hidden;
  }

  div.gallery:hover {
    cursor: pointer;
  }

  div.gallery #thumbnail {
    width: 100%;
    height: auto;
  }

  #thumbnail {
    transform: scale(1);
    -webkit-transform: scale(1);
    -moz-transform: scale(1);
    -ms-transform: scale(1);
    -o-transform: scale(1);
    transition: all 0.3s ease-in-out;
  }

  #thumbnail:hover {
    transform: scale(1.2);
    -webkit-transform: scale(1.2);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
  }

  .desc {
    padding: 0px;
    text-align: center;
  }
  .box {
    padding:15px 15px 0 15px;
    background-color: #fff;
  }
  .desc a {
    color: black;
    text-decoration: none;
  }

  .desc h4 {
    font-weight: bold;
    margin-bottom: -5px;
  }

  .list-group-item {
    border: 0px;
    background-color: rgb(245, 245, 245);
  }

  .desc p {
    text-align: center;
  } 
  
  .p-top {
  margin: 0;
  }

  .p-bottom {
  height: 48px;
  }
  
  hr {
  margin:5px;
  }

  * {
    box-sizing: border-box;
  }


  .clearfix:after {
    content: "";
    display: table;
    clear: both;
  }

  #cart img {
    width: 50px;
    height: auto;
  }

  #review img {
    width: 20px;
    height: auto;
  }

</style>
<h1> ${type} </h1>
<form action='search' method='post'>
  <div class="mb-3 row">
    <label for='f-search' class="col-sm-1 col-form-label">검색</label>
    <div class="col-sm-2">
      <input id='f-search' type='text' name='search' class="form-control">
    </div>
  </div>
</form>

<c:set var="i" value="0" />
<c:set var="j" value="4" />

<!--  갤러리   -->
<c:choose>
  <c:when test="${productList ne null}">
    <c:forEach items="${productList}" var="product">
      <c:if test="{i%j == 0}">
      </c:if>
      <div class="responsive">
        <div class="gallery">
          <img id="thumbnail" src="../../upload/product/${product.photo}_300x300.jpg" name="photo" align="middle"
            width="600" height="400">
          <div class="desc">
            <h4>
              <a href="show?no=${product.productNumber}" class="list-group-item">${product.productName}</a>
            </h4>
            <div class="box">
              <div class="row top-row">
                <div class="col-sm">
                  <p class="p-top">주종</p>
                   <hr> 
                   <p class="p-bottom" style="font-size:small;">${product.productType.type}</p>
                </div>
                <div class="col-sm">
                  <p class="p-top">도수</p>
                  <hr>
                  <p class="p-bottom">${product.alcoholLevel}%</p>
                </div>
                <div class="col-sm">
                  <p class="p-top">용량</p>
                  <hr>
                  <p class="p-bottom">${product.volume}ml</p>
                </div>
              </div>
              <div class="row">
                <div class="cart col-sm">
                  <p>
                    <a id="cart" href="../stock/sellerList?no=${product.productNumber}">
                      <img
                        src="https://media.istockphoto.com/vectors/add-to-cart-icon-shopping-cart-icon-vector-illustration-vector-id1179275901?k=20&m=1179275901&s=170667a&w=0&h=YN3VNlg_HuhSrcscbxZAdXIaYDF5Mr1CjOFAix7VLaU="
                        alt="장바구니" height="30" width="30">
                    </a>
                  </p>
                </div>
                <div class="col-sm">
                </div>
                <div class="rate col-sm">
                  <p>
                    <a id="review" href="../product/show?no=${product.productNumber}">
                      <img src="../../image/favourite.png"> <br> ${product.rate}
                    </a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <c:if test="${i%j == j-1}">
      </c:if>
      <c:set var="i" value="${i+1}" />
    </c:forEach>
  </c:when>
  <c:otherwise>
    <div>존재하지 않습니다.</div>
  </c:otherwise>
</c:choose>

<script>
  document.querySelectorAll(".list-group-item").forEach((aTag) => {
    aTag.onclick = () => false;
  });
  var trList = document.querySelectorAll(".responsive");
  trList.forEach(function (trTag) {
    trTag.onclick = (e) => {
      //console.log(e.currentTarget.querySelector("a").href);
      //e.currentTarget.querySelector("a").click();
      window.location.href = e.currentTarget.querySelector("a").href;
      //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
    };
  });
</script>