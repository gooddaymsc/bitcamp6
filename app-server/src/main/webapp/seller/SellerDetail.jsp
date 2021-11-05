<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>

<head>
  <title>회원상세(판매자)</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
    .container {
      xborder: 1px solid red;
      width: 640px;
    }
  </style>
</head>

<body>
  <div class="container">
    <h1>회원 상세(판매자)</h1>
    <form action='update'>
      <div class="mb-3 row">
        <label for='f-no' class="col-sm-2 col-form-label">번호</label>
        <div class="col-sm-6">
          <input id='f-no' type='text' name='number' class="form-control" value='${seller.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-6">
          <input id='f-id' type='text' name='id' class="form-control" value='${seller.member.id}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-password' class="col-sm-2 col-form-label">암호</label>
        <div class="col-sm-6">
          <input id='f-password' type='password' name='password' class="form-control"
            ><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-name' class="col-sm-2 col-form-label">이름</label>
        <div class="col-sm-6">
          <input id='f-name' type='text' name='name' class="form-control" value='${seller.member.name}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
        <div class="col-sm-6">
          <input id='f-nickname' type='text' name='nickname' class="form-control" value='${seller.member.nickname}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-level' class="col-sm-2 col-form-label">레벨</label>
        <div class="col-sm-6">
          <input id='f-level' type='text' name='level' class="form-control" value='${seller.member.level}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
        <div class="col-sm-10">
          <input id='f-email' type='email' name='email' class="form-control" value='${seller.member.email}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-birthday' class="col-sm-2 col-form-label">생일</label>
        <div class="col-sm-6">
          <input id='f-birthday' type='date' name='birthday' class="form-control" value='${seller.member.birthday}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-10">
          <input id='f-photo' type='text' name='photo' class="form-control" value='${seller.member.photo}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">전화</label>
        <div class="col-sm-10">
          <input id='f-phoneNumber' type='tel' name='phoneNumber' class="form-control"
            value='${seller.member.phoneNumber}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessName' class="col-sm-2 col-form-label">가게명</label>
        <div class="col-sm-6">
          <input id='f-businessName' type='text' name='businessName' class="form-control"
            value='${seller.businessName}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessNumber' class="col-sm-2 col-form-label">사업자번호</label>
        <div class="col-sm-10">
          <input id='f-businessNumber' type='text' name='businessNumber' class="form-control"
            value='${seller.businessNumber}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessAddress' class="col-sm-2 col-form-label">사업장주소</label>
        <div class="col-sm-10">
          <input id='f-businessAddress' type='text' name='businessAddress' class="form-control"
            value='${seller.businessAddress}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessPlaceNumber' class="col-sm-2 col-form-label">사업장번호</label>
        <div class="col-sm-10">
          <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' class="form-control"
            value='${seller.businessPlaceNumber}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessOpeningTime' class="col-sm-2 col-form-label">오픈시간</label>
        <div class="col-sm-6">
          <input id='f-businessOpeningTime' type='text' name='businessOpeningTime' class="form-control"
            value='${seller.businessOpeningTime}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessClosingTime' class="col-sm-2 col-form-label">마감시간</label>
        <div class="col-sm-6">
          <input id='f-businessClosingTime' type='text' name='businessClosingTime' class="form-control"
            value='${seller.businessClosingTime}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-authority' class="col-sm-2 col-form-label">권한</label>
        <div class="col-sm-6">
          <input id='f-authority' type='text' name='authority' class="form-control" value='${seller.member.authority}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-registeredDate' class="col-sm-2 col-form-label">등록일</label>
        <div class="col-sm-6">
          <input id='f-registeredDate' type="text" readonly class="form-control-plaintext"
            value="${seller.member.registeredDate}">
        </div>
      </div>
      <button class="btn btn-primary">변경</button>
      <a href='delete?id=${seller.member.id}' class="btn btn-primary">삭제</a>
      <a href='list' class="btn btn-primary">목록</a><br>
    </form>
  </div>
</body>

</html>