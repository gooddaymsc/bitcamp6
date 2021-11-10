<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

    <h1>개인정보 변경(구매자)</h1>
    <h5>* 필수 입력</h5>
    <form action='update' method='post' enctype="multipart/form-data">
      <div class="mb-3 row">
<!--         <label  for='f-no' class="col-sm-2 col-form-label">번호</label>
 -->        <div class="col-sm-6">
          <input type='hidden' id='f-no' type='text' name='number' class="form-control" value='${buyer.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-6">
          <input id='f-id' type='text' name='id' class="form-control" value='${buyer.member.id}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-password' class="col-sm-2 col-form-label">암호</label>
        <div class="col-sm-6">
          <input id='f-password' type='password' name='password' class="form-control" value='${buyer.member.password}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-name' class="col-sm-2 col-form-label">이름</label>
        <div class="col-sm-6">
          <input id='f-name' type='text' name='name' class="form-control" value='${buyer.member.name}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-nickname' class="col-sm-2 col-form-label">* 닉네임</label>
        <div class="col-sm-6">
          <input id='f-nickname' type='text' name='nickname' class="form-control" value='${buyer.member.nickname}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-level' class="col-sm-2 col-form-label">레벨</label>
        <div class="col-sm-6">
          <input id='f-level' type='text' name='level' class="form-control" value='${buyer.member.level}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
        <div class="col-sm-6">
          <input id='f-email' type='email' name='email' class="form-control" value='${buyer.member.email}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-birthday' class="col-sm-2 col-form-label">생일</label>
        <div class="col-sm-6">
          <input id='f-birthday' type='date' name='birthday' class="form-control" value='${buyer.member.birthday}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <a href="../upload/buyer/${buyer.member.photo}" >
            <img id="f-photo-image" src="../upload/buyer/${buyer.member.photo}_100x100.jpg">
          </a>
          <input id='f-photo' type='file' name='photo' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">* 전화</label>
        <div class="col-sm-6">
          <input id='f-phoneNumber' type='tel' name='phoneNumber' class="form-control"
            value='${buyer.member.phoneNumber}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-zipcode' class="col-sm-2 col-form-label">우편번호</label>
        <div class="col-sm-6">
          <input id='f-zipcode' type='text' name='zipcode' class="form-control" value='${buyer.zipcode}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-address' class="col-sm-2 col-form-label">주소</label>
        <div class="col-sm-6">
          <input id='f-address' type='text' name='address' class="form-control" value='${buyer.address}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-detailAddress' class="col-sm-2 col-form-label">상세주소</label>
        <div class="col-sm-6">
          <input id='f-detailAddress' type='text' name='detailAddress' class="form-control"
            value='${buyer.detailAddress}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-authority' class="col-sm-2 col-form-label">권한</label>
        <div class="col-sm-6">
          <input id='f-authority' type='text' name='authority' class="form-control" value='${buyer.member.authority}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-registeredDate' class="col-sm-2 col-form-label">등록일</label>
        <div class="col-sm-6">
          <input id='f-registeredDate' type="text" readonly class="form-control-plaintext"
            value="${buyer.member.registeredDate}">
        </div>
      </div>
      <button class="btn btn-primary">변경</button>
      <a href='delete?id=${buyer.member.id}' onclick="return confirm('정말 탈퇴하시겠습니까?');" class="btn btn-primary">탈퇴하기</a>
    </form>