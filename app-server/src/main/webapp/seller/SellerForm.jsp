<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <h1>회원가입(판매자)</h1>     
    <a href='../buyer/form' class="btn btn-outline-primary btn-sm">구매자로 회원가입하기</a>
    <h5>* 필수 입력</h5>
    <form action='add' method='post' enctype="multipart/form-data">
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
          <input id='f-photo' type='file' name='photo' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">* 전화</label>
        <div class="col-sm-10">
          <input id='f-phoneNumber' type='tel' name='phoneNumber' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessName' class="col-sm-2 col-form-label">* 가게명</label>
        <div class="col-sm-6">
          <input id='f-businessName' type='text' name='businessName' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessNumber' class="col-sm-2 col-form-label">* 사업자번호</label>
        <div class="col-sm-10">
          <input id='f-businessNumber' type='text' name='businessNumber' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessAddress' class="col-sm-2 col-form-label">* 사업장주소</label>
        <div class="col-sm-10">
          <input id='f-businessAddress' type='text' name='businessAddress' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessPlaceNumber' class="col-sm-2 col-form-label">* 사업장번호</label>
        <div class="col-sm-10">
          <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessOpeningTime' class="col-sm-2 col-form-label">* 오픈시간</label>
        <div class="col-sm-6">
          <input id='f-businessOpeningTime' type='text' name='businessOpeningTime' class="form-control" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessClosingTime' class="col-sm-2 col-form-label">* 마감시간</label>
        <div class="col-sm-6">
          <input id='f-businessClosingTime' type='text' name='businessClosingTime' class="form-control" required><br>
        </div>
      </div>
      <button class="btn btn-primary btn-sm">등록</button><br>
    </form>
