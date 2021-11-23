<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <h1>등급 변경(판매자)</h1>
    <h5>* 필수 입력</h5>
    <form action='update' method='post'>
      <div class="mb-3 row">
<!--         <label for='f-no' class="col-sm-2 col-form-label">번호</label>
 -->        <div class="col-sm-6">
          <input type='hidden' id='f-no' type='text' name='number' class="form-control" value='${seller.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디 : ${seller.member.id}</label>
        <div class="col-sm-6">
          <input type='hidden' id='f-id' type='text' name='member.id' class="form-control" value='${seller.member.id}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-level' class="col-sm-2 col-form-label">레벨</label>
        <div class="col-sm-6">
          <input id='f-level' type='text' name='member.level' class="form-control" value='${seller.member.level}'><br>
        </div>
      </div>
      <button class="btn btn-outline-secondary btn-sm">변경</button>
      <a href='delete?id=${seller.member.id}' onclick="return confirm('정말 탈퇴시키겠습니까?');" class="btn btn-outline-secondary btn-sm">탈퇴시키기</a>
    
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-password' type='password' name='password' class="form-control" value='${buyer.seller.password}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-name' type='text' name='name' class="form-control" value='${seller.member.name}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-nickname' type='text' name='nickname' class="form-control" value='${seller.member.nickname}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-email' type='email' name='email' class="form-control" value='${seller.member.email}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-birthday' type='date' name='birthday' class="form-control" value='${seller.member.birthday}' readonly><br>
        </div>
      </div>
<%--       <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <input id='f-photo' type='text' name='photo' class="form-control" value='${seller.member.photo}' readonly><br>
        </div>
      </div> --%>
      <div class="mb-3 row">
        <div class="col-sm-6">
           <input type='hidden' id='f-photo' type='file' name='photoFile' class="form-control" readonly><br> 
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-phoneNumber' type='tel' name='phoneNumber' class="form-control"
            value='${seller.member.phoneNumber}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessName' type='text' name='businessName' class="form-control"
            value='${seller.businessName}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessNumber' type='text' name='businessNumber' class="form-control"
            value='${seller.businessNumber}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessAddress' type='text' name='businessAddress' class="form-control"
            value='${seller.businessAddress}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' class="form-control"
            value='${seller.businessPlaceNumber}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessOpeningTime' type='time' name='businessOpeningTime' class="form-control"
            value='${seller.businessOpeningTime}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-businessClosingTime' type='time' name='businessClosingTime' class="form-control"
            value='${seller.businessClosingTime}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-authority' type='text' name='authority' class="form-control" valueㄸ='${seller.member.authority}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-registeredDate' type="text"  class="form-control-plaintext"
            value="${seller.member.registeredDate}" readonly>
        </div>
      </div>
</form>
