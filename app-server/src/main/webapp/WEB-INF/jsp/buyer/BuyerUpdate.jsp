<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <h1>등급 변경(구매자)</h1>
    <h5>* 필수 입력</h5>
    <form action='update' method='post'>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-no' type='text' name='number' class="form-control" value='${buyer.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디 : ${buyer.member.id}</label>
        <div class="col-sm-6">
          <input type='hidden' id='f-id' type='text' name='member.id' class="form-control" value='${buyer.member.id}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-level' class="col-sm-2 col-form-label">레벨</label>
        <div class="col-sm-6">
          <input id='f-level' type='text' name='level' class="form-control" value='${buyer.member.level}'><br>
        </div>
      </div>
      <button class="btn btn-outline-secondary btn-sm">변경</button>
      <a href='delete?id=${buyer.member.id}' onclick="return confirm('정말 탈퇴시키겠습니까?');" class="btn btn-outline-secondary btn-sm">탈퇴시키기</a>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-password' type='password' name='password' class="form-control" value='${buyer.member.password}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-name' type='text' name='name' class="form-control" value='${buyer.member.name}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-nickname' type='text' name='member.nickname' class="form-control" value='${buyer.member.nickname}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-level' type='text' name='level' class="form-control" value='${buyer.member.level}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-email' type='email' name='email' class="form-control" value='${buyer.member.email}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-birthday' type='date' name='birthday' class="form-control" value='${buyer.member.birthday}' readonly><br>
        </div>
      </div>
 <%--      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <input id='f-photo' type='text' name='photo' class="form-control" value='${buyer.member.photo}' readonly><br>
        </div>
      </div> --%>
      <div class="mb-3 row">
        <div class="col-sm-6">
        <!-- <input id='f-photo' type='file' name='photoFile' class="form-control" readonly><br>   -->
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-phoneNumber' type='tel' name='member.phoneNumber' class="form-control"
            value='${buyer.member.phoneNumber}' required readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-zipcode' type='text' name='zipcode' class="form-control" value='${buyer.zipcode}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-address' type='text' name='address' class="form-control" value='${buyer.address}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-detailAddress' type='text' name='detailAddress' class="form-control"
            value='${buyer.detailAddress}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-authority' type='text' name='authority' class="form-control" value='${buyer.member.authority}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <div class="col-sm-6">
          <input type='hidden' id='f-registeredDate' type="text" readonly class="form-control-plaintext"
            value="${buyer.member.registeredDate}">
        </div>
      </div>
</form>
