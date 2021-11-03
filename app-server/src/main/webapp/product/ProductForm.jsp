<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>새상품</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        width: 640px;
    }
  </style>
</head>
<body>
<h1>새상품(MVC)</h1>
<form action='add'>
<label for='f-productName'>상품명</label> <input id='f-productName' type='text' name='productName'><br>

<label for='f-type'>주종</label> <input id='f-type' type='text' name='type'><br>
<label for='f-subType'>상세주종</label> <input id='f-subType' type='text' name='subType'><br>

<label for='f-countryOrigin'>원산지</label> <input id='f-countryOrigin' type='text' name='countryOrigin'><br>
<label for='f-variety'>품종</label> <input id='f-variety' type='text' name='variety'><br>

<label for='f-volume'>용량</label> <input id='f-volume' type='text' name='volume'><br>
<label for='f-alcoholLevel'>도수</label> <input id='f-alcoholLevel' type='text' name='alcoholLevel'><br>

<label for='f-sugarLevel'>당도</label> <input id='f-sugarLevel' type='text' name='sugarLevel'><br>
<label for='f-acidity'>산도</label> <input id='f-acidity' type='text' name='acidity'><br>
<label for='f-weight'>바디감</label> <input id='f-weight' type='text' name='weight'><br>

<button>등록</button><br>
</form>
</body>
</html>









