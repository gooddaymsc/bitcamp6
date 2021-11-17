<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<style>
tr a {
    text-decoration: none;
    color: black;
}
tr a:visited {
    color: black;
}
tr:hover {
    cursor: pointer;
}
</style>

<h1>장바구니 목록</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th><input type='checkbox' /></th>
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
<c:forEach items="${cartList}" var="cart" varStatus="status">
    <tr>
    <td><input type="checkbox" /></td>
<%--       <input name='chkbox' type="checkbox"  value="${cart.cartStocks*cart.stock.price}" onClick="itemSum(this.form);">
 --%>      <td class='chk'> <a href='detail?cartNumber=${cart.cartNumber}&id=${cart.id}'>${cart.cartNumber}</a></td>
      <td class='chk'>${cart.id}</td> 
      <td class='chk'>${sellerList.businessName}</td>  
      <td class='chk'>${cart.stock.seller.member.id}</td> 
      <td class='chk'>${cart.stock.product.productName}</td> 
      <td class='chk'>${cart.cartStocks}</td> 
      <td class='chk'>${cart.stock.price}</td> 
      <td class='chk'>${cart.registrationDate}</td> 
      <td class='chk'>${cart.cartStocks*cart.stock.price}</td>
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
<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody .chk"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>
