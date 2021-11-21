<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  
  .search-img {
  
    width: 20px;
  }
  
    .wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
    .wrap * {padding: 0;margin: 0;}
    .wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
    .wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
    .info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
    .info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
    .info .close:hover {cursor: pointer;}
    .info .body {position: relative;overflow: hidden;}
    .info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
    .desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
    .desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
    .info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
    .info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
    .info .link {color: #5085BB;}
  

</style>

<h1>상품 목록</h1>
<form action='search' method='post'>  
<div class="mb-3 row"><!-- 
 <button  class="search-button"><img class="search-img" src="../../image/search.png"></button> -->
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
              <div class="row">
                <div class="col-sm">
                  <p class="top">주종</p>
                  <hr>
                   <p class="p-bottom">${product.productType.type}</p>
                </div>
                <div class="col-sm">
                  <p class="top">도수</p>
                  <hr>
                  <p class="p-bottom">${product.alcoholLevel}%</p>
                </div>
                <div class="col-sm">
                  <p class="top">용량</p>
                  <hr> 
                  <p class="p-bottom"  >${product.volume}ml</p>
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
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    window.location.href = e.currentTarget.querySelector("a").href;
  };
});

</script>

<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e95526ee94a73ad7b5fda1653496933"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 마커를 표시합니다 
var marker = new kakao.maps.Marker({
    map: map, 
    position: new kakao.maps.LatLng(33.450701, 126.570667)
});

// 커스텀 오버레이에 표시할 컨텐츠 입니다
// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
// 별도의 이벤트 메소드를 제공하지 않습니다 
var content = '<div class="wrap">' + 
            '    <div class="info">' + 
            '        <div class="title">' + 
            '            카카오 스페이스닷원' + 
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
            '        </div>' + 
            '        <div class="body">' + 
            '            <div class="img">' +
            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
            '           </div>' + 
            '            <div class="desc">' + 
            '                <div class="ellipsis">제주특별자치도 제주시 첨단로 242</div>' + 
            '                <div class="jibun ellipsis">(우) 63309 (지번) 영평동 2181</div>' + 
            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';

// 마커 위에 커스텀오버레이를 표시합니다
// 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
var overlay = new kakao.maps.CustomOverlay({
    content: content,
    map: map,
    position: marker.getPosition()       
});

// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
kakao.maps.event.addListener(marker, 'click', function() {
    overlay.setMap(map);
});

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
    overlay.setMap(null);     
}

</script>








