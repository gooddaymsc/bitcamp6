<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>장바구니목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
</head>
<body>
<form name="form">
<div class="container">
<h1>장바구니 목록</h1>
<a href='form' class="btn btn-outline-primary btn-sm">새장바구니</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>선택</th>
    <th>번호</th>
    <th>구매자</th>
    <th>가게명</th>
    <th>판매자</th>
    <th>상품명</th>
    <th>수량</th>
    <th>개당금액</th>
    <th>등록일</th>
    <th>총액</th>
  </tr>
</thead>
<tbody>
<%-- <c:forEach items="${cartList}" var="cart" items="${sellerList}" var="sellerList"> --%>
  <c:forEach items="${cartList}" var="cart" varStatus="status">
    <tr>
      <td> 
      <input name='chkbox' type="checkbox"  value="${cart.cartStocks*cart.stock.price}" onClick="itemSum(this.form);">
  </td> 
      <td> <a href='detail?cartNumber=${cart.cartNumber}&id=${cart.id}'>${cart.cartNumber}</a></td>
      <td>${cart.id}</td> 
      <td>${sellerList.businessName}</td>  
      <td>${cart.stock.seller.member.id}</td> 
      <td>${cart.stock.product.productName}</td> 
      <td>${cart.cartStocks}</td> 
      <td>${cart.stock.price}</td> 
      <td>${cart.registrationDate}</td> 
      <td>${cart.cartStocks*cart.stock.price}</td>
</tr>
</c:forEach>
</tbody>
</table>
<table class="table table-bordered" id="tbl-total">
            <thead>
                <tr>
                    <th>선택 품목</th>
                    <th>합계</th>
                </tr>
            </thead>
            <tbody>
                <td><input type="number"  id="total-qty" value="0" readonly></td>
                <td><input name="total_sum"  type="text" size="20" readonly></td>
            </tbody>            
        </table>
</div><!-- .container -->
<footer>
<a href='../main/logout' class="btn btn-primary">로그아웃</a>
</footer>
<script>
function itemSum(frm)
{
   var sum = 0;
   var count = frm.chkbox.length;
   for(var i=0; i < count; i++ ){
       if( frm.chkbox[i].checked == true ){
      sum += parseInt(frm.chkbox[i].value);
       }
   }
   frm.total_sum.value = sum;
}
</script>
</form>
</body>
</html>
