<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

    <h1>개인정보 변경(판매자)</h1>
      <a href='passwordForm' class="btn btn-outline-secondary btn-sm">비밀번호변경</a>
    <h5>* 필수 입력</h5>
    <form action='update' method='post' enctype="multipart/form-data">
      <div class="mb-3 row">
<!--         <label for='f-no' class="col-sm-2 col-form-label">번호</label>
 -->        <div class="col-sm-6">
          <input type='hidden' id='f-no' type='text' name='member.number' class="form-control" value='${seller.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-6">
          <input id='f-id' type='text' name='member.id' class="form-control" value='${seller.member.id}' readonly><br>
        </div>
      </div>
      
      
<%--       <div class="mb-3 row">
        <label for='f-password' class="col-sm-2 col-form-label">암호</label>
        <div class="col-sm-6">
          <input id='f-password' type='password' name='password' class="form-control" value='${buyer.seller.password}' readonly><br>
        </div>
      </div> --%>
      
      <div class="mb-3 row">
        <label for='f-name' class="col-sm-2 col-form-label">이름</label>
        <div class="col-sm-6">
          <input id='f-name' type='text' name='member.name' class="form-control" value='${seller.member.name}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-nickname' class="col-sm-2 col-form-label">* 닉네임</label>
        <div class="col-sm-6">
          <input id='f-nickname' type='text' name='member.nickname' class="form-control" value='${seller.member.nickname}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-level' class="col-sm-2 col-form-label">레벨</label>
        <div class="col-sm-6">
          <input id='f-level' type='text' name='member.level' class="form-control" value='${seller.member.level}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
        <div class="col-sm-6">
          <input id='f-email' type='email' name='member.email' class="form-control" value='${seller.member.email}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-birthday' class="col-sm-2 col-form-label">생일</label>
        <div class="col-sm-6">
          <input id='f-birthday' type='date' name='member.birthday' class="form-control" value='${seller.member.birthday}' readonly><br>
        </div>
      </div>
<%--       <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <input id='f-photo' type='text' name='photo' class="form-control" value='${seller.member.photo}'><br>
        </div>
      </div> --%>
      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <a href="${contextPath}/upload/member/${seller.member.photo}" >
              <img id="f-photo-image" src="${contextPath}/upload/member/${seller.member.photo}_100x100.jpg" onError="this.src='${contextPath}/upload/member/profile.png'"
     style="width:10rem; height:200px; padding:3px">
          </a>
          <input id='f-photo' type='file' name='photoFile' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">* 전화</label>
        <div class="col-sm-6">
          <input id='f-phoneNumber' type='tel' name='member.phoneNumber' class="form-control"
            value='${seller.member.phoneNumber}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessName' class="col-sm-2 col-form-label">* 가게명</label>
        <div class="col-sm-6">
          <input id='f-businessName' type='text' name='businessName' class="form-control"
            value='${seller.businessName}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessNumber' class="col-sm-2 col-form-label">* 사업자번호</label>
        <div class="col-sm-6">
          <input id='f-businessNumber' type='text' name='businessNumber' class="form-control"
            value='${seller.businessNumber}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessAddress' class="col-sm-2 col-form-label">* 사업장주소</label>
        <div class="col-sm-6">
          <input id='f-businessAddress' type='text' name='businessAddress' class="form-control"
            value='${seller.businessAddress}'><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessPlaceNumber' class="col-sm-2 col-form-label">* 사업장번호</label>
        <div class="col-sm-6">
          <input id='f-businessPlaceNumber' type='text' name='businessPlaceNumber' class="form-control"
            value='${seller.businessPlaceNumber}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessOpeningTime' class="col-sm-2 col-form-label">* 오픈시간</label>
        <div class="col-sm-6">
          <input id='f-businessOpeningTime' type='time' name='businessOpeningTime' class="form-control"
            value='${seller.businessOpeningTime}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-businessClosingTime' class="col-sm-2 col-form-label">* 마감시간</label>
        <div class="col-sm-6">
          <input id='f-businessClosingTime' type='time' name='businessClosingTime' class="form-control"
            value='${seller.businessClosingTime}' required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-authority' class="col-sm-2 col-form-label">권한</label>
        <div class="col-sm-6">
          <input id='f-authority' type='text' name='member.authority' class="form-control" value='${seller.member.authority}'
            readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-registeredDate' class="col-sm-2 col-form-label">등록일</label>
        <div class="col-sm-6">
          <input id='f-registeredDate' type="text"  class="form-control-plaintext"
            value="${seller.member.registeredDate}" readonly>
        </div>
      </div>
      <button class="btn btn-outline-secondary btn-sm">변경</button>
      <a href='../main/myPage' class="btn btn-outline-secondary btn-sm">이전</a>
      <%-- <a href='passwordDetail?id=${seller.member.id}' class="btn btn-outline-success">암호변경</a> --%>
      <a href='delete?id=${seller.member.id}' onclick="return confirm('정말 탈퇴하시겠습니까?');" class="btn btn-outline-secondary btn-sm">탈퇴하기</a>
    </form>