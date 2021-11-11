<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

    <h1>비밀번호 변경(판매자)</h1>
    <h5>* 필수 입력</h5>
    <form action='passwordUpdate' method='post'>
      <div class="mb-3 row">
<!--         <label for='f-no' class="col-sm-2 col-form-label">번호</label>
 -->        <div class="col-sm-6">
          <input type='hidden' id='f-no' type='text' name='number' class="form-control" value='${seller.member.number}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
        <div class="col-sm-6">
          <input id='f-id' type='text' name='id' class="form-control" value='${seller.member.id}' readonly><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-password' class="col-sm-2 col-form-label">* 암호</label>
        <div class="col-sm-6">
          <input id='f-password' type='password' name='password' class="form-control" value='${buyer.seller.password}' required><br>
        </div>
      </div>
      <button class="btn btn-primary">변경</button>
    </form>