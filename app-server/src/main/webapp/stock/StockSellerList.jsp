<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>재고판매자목록</h1>
<form action='place'>  
<div class="mb-3 row">
  <label for='f-place' class="col-sm-1.5 col-form-label">주소검색</label>
    <div class="col-sm-3">
    <input id='f-place' type='text' name='place' class="form-control">
    <input type="hidden" id='list' type='text' name='list' class="form-control" value="${stockSellerList}" >
  </div>
</div>
</form>
<table class="table table-hover">
<thead>
  <tr>
    <th>가게명</th>
    <th>판매자</th>
    <th>판매주소</th>
    <th>판매장번호</th>
    <th>재고</th>
    <th>금액</th>
    <th>장바구니담기</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${stockSellerList}" var="stock">
<tr>
    <td>${stock.seller.businessName}</td> 
    <td>${stock.seller.member.id}</td> 
    <td>${stock.seller.businessAddress}</td> 
    <td>${stock.seller.businessPlaceNumber}</td> 
    <td>${stock.stocks}</td> 
    <td>${stock.price}</td> 
    <td><a href='../cart/form?no=${stock.stockNumber}'>
    <img src="https://media.istockphoto.com/vectors/add-to-cart-icon-shopping-cart-icon-vector-illustration-vector-id1179275901?k=20&m=1179275901&s=170667a&w=0&h=YN3VNlg_HuhSrcscbxZAdXIaYDF5Mr1CjOFAix7VLaU=" alt="장바구니" height="30" width="30" >
    </a>
    </td>
    
</tr>
</c:forEach>
</tbody>
</table>
  <a href='../product/detail?no=${productNo}' class="btn btn-primary">이전</a><br>
  
<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>








