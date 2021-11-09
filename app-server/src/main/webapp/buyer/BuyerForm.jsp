<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>

<head>
  <title>새회원</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

  <style>
    .container {
      xborder: 1px solid red;
      width: 640px;
  </style>
</head>

<body>
  <div class="container">
    <h1>회원가입(구매자)</h1>     
    <a href='../seller/form' class="btn btn-outline-primary btn-sm">판매자로 회원가입하기</a>
    <a href="../main/Menu.jsp" class="btn btn-outline-primary btn-sm">메인</a><br><br>
    <h5>* 필수 입력</h5>
    <form action='add' method='post'>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">* 아이디</label>
        <div class="col-sm-6">
          <input id='f-id' type='text' name='id' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-name' class="col-sm-2 col-form-label">* 이름</label>
        <div class="col-sm-6">
          <input id='f-name' type='text' name='name' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-nickname' class="col-sm-2 col-form-label">* 닉네임</label>
        <div class="col-sm-6">
          <input id='f-nickname' type='text' name='nickname' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-email' class="col-sm-2 col-form-label">* 이메일</label>
        <div class="col-sm-10">
          <input id='f-email' type='email' name='email' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-birthday' class="col-sm-2 col-form-label">생일</label>
        <div class="col-sm-6">
          <input id='f-birthday' type='date' name='birthday' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-password' class="col-sm-2 col-form-label">* 암호</label>
        <div class="col-sm-6">
          <input id='f-password' type='password' name='password' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-10">
          <input id='f-photo' type='text' name='photo' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">* 전화</label>
        <div class="col-sm-10">
          <input id='f-phoneNumber' type='tel' name='phoneNumber' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-zipcode' class="col-sm-2 col-form-label">우편번호</label>
        <div class="col-sm-6">
          <input id='f-zipcode' type='text' name='zipcode' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-address' class="col-sm-2 col-form-label">주소</label>
        <div class="col-sm-10">
          <input id='f-address' type='text' name='address' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-detailAddress' class="col-sm-2 col-form-label">상세주소</label>
        <div class="col-sm-10">
          <input id='f-detailAddress' type='text' name='detailAddress' class="form-control"><br>
        </div>
      </div>
      <button class="btn btn-primary btn-sm">등록</button><br>
    </form>
  </div>
</body>

</html>