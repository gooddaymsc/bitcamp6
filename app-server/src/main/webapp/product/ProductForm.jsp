<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
<div class="container">
<h1>새상품(MVC)</h1>
<form action='add'>
<div class="mb-3 row">
  <label for='f-productName' class="col-sm-2 col-form-label">상품명</label>
  <div class="col-sm-6">
    <input id='f-productName' type='text' name='productName' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-type' class="col-sm-2 col-form-label">주종</label>
  <div class="col-sm-6">
     <select id='f-type' onchange="categoryChange(this)" class="form-select" name='type' aria-label="Default select example">
      <option selected>주종선택</option>
      <option value="와인" >와인</option>
      <option value="위스키">위스키</option>
      <option value="브랜디/꼬냑">브랜디/꼬냑</option>
      <option value="리큐르/보드카">리큐르/보드카</option>
    </select>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-type' class="col-sm-2 col-form-label">상세주종</label>
  <div class="col-sm-6"> 
  <select id="good" name="subType" class="form-select">
  <option>주종상세</option>
  </select>
    <label for='f-variety' class="col-sm-2 col-form-label">품종</label>
    <input id='f-variety' type='text' name='variety' class="inputText" disabled />
  </div>
</div>


<div class="mb-3 row">
  <label for='f-countryOrigin' class="col-sm-2 col-form-label">원산지</label>
  <div class="col-sm-6">
    <input id='f-countryOrigin' type='text' name='countryOrigin' class="form-control">
  </div>
</div>

<div class="mb-3 row">
  <label for='f-volume' class="col-sm-2 col-form-label">용량</label>
  <div class="col-sm-6">
    <input id='f-volume' type='number' name='volume' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-alcoholLevel' class="col-sm-2 col-form-label">도수</label>
  <div class="col-sm-6">
    <input id='f-alcoholLevel' type="number" name='alcoholLevel' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-sugarLevel' class="col-sm-2 col-form-label">당도</label>
  <div class="col-sm-6">
    <select id='f-sugarLevel' class="form-select" name='sugarLevel' aria-label="Default select example">
      <option value="1" selected>1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-acidity' class="col-sm-2 col-form-label">산도</label>
  <div class="col-sm-6">
    <select id='f-acidity' class="form-select" name='acidity' aria-label="Default select example">
      <option value="1" selected>1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-weight' class="col-sm-2 col-form-label">바디감</label>
  <div class="col-sm-6">
    <select id='f-weight' class="form-select" name='weight' aria-label="Default select example">
		  <option value="1" selected>1</option>
		  <option value="2">2</option>
		  <option value="3">3</option>
		  <option value="4">4</option>
		  <option value="5">5</option>
    </select>
  </div>
</div>

<button  class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!-- .container -->

<script>
    function categoryChange(e) {
    var good_a = ["상세주종", "레드와인", "화이트와인", "로제와인", "스위트와인","스파클링"];
    var good_b = ["상세주종", "아메리칸위스키(버번)", "스카치위스키(몰트)", "아이리쉬위스키", "캐나다위스키"];
    var good_c = ["상세주종", "브랜디", "꼬냑", "알마냑"];
    var good_d = ["상세주종", "리큐르", "진", "럼", "보드카", "데낄라", "음료.시럽"];
    var target = document.getElementById("good");
    if(e.value == "와인") var d = good_a;
    else if(e.value == "위스키") var d = good_b;
    else if(e.value == "브랜디/꼬냑") var d = good_c;
    else if(e.value == "리큐르/보드카") var d = good_d;

    target.options.length = 0;
 
    for (x in d) {
        var opt = document.createElement("option");
        opt.value = d[x];
        opt.innerHTML = d[x];
        target.appendChild(opt);
        }
    }
</script>
<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script>
$(function() { 
	  $(document).on("change", "select[name=subType]", function(){
	    var value = $(this).find("option:selected").val();
	    var inputText = $(this).closest('.col-sm-6').find('.inputText');
	    var flag = false;
	    //서브타입이 와인계열이면 품종 입력
	    if ((value == "화이트와인")||(value == "레드와인")||(value == "로제와인")
	    		||(value == "스위트와인")||(value == "스파클링")) {
	    $(inputText).attr("disabled", flag);
	    }
	      flag = true;
	      $(inputText).val('');
	  });
	})
</script>
</body>
</html>









