<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <h1>회원가입(구매자)</h1>     
    <a href='../seller/form' class="btn btn-outline-primary btn-sm">판매자로 회원가입하기</a>
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
        <div class="col-sm-6">
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
          <input id='f-password' type='password' name='password' class="form-control" onchange="check_pw()" required><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-password2' class="col-sm-2 col-form-label">* 암호 확인</label>
        <div class="col-sm-6">
          <input id='f-password2' type='password' name='password2' class="form-control" onchange="check_pw()" required><span id="check"></span><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <div class="col-sm-6">
          <input id='f-photo' type='file' name='photo' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-phoneNumber' class="col-sm-2 col-form-label">* 전화</label>
        <div class="col-sm-6">
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
        <div class="col-sm-6">
          <input id='f-address' type='text' name='address' class="form-control"><br>
        </div>
      </div>
      <div class="mb-3 row">
        <label for='f-detailAddress' class="col-sm-2 col-form-label">상세주소</label>
        <div class="col-sm-6">
          <input id='f-detailAddress' type='text' name='detailAddress' class="form-control"><br>
        </div>
      </div>
      <button class="btn btn-primary btn-sm">등록</button><br>
    </form>
  <script>
        function check_pw(){
 
            var pw = document.getElementById('f-password').value;
            var SC = ["!","@","#","$","%"];
            var check_SC = 0;
 
            if(pw.length < 6 || pw.length>16){
                window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
                document.getElementById('f-password').value='';
            }
            for(var i=0;i<SC.length;i++){
                if(pw.indexOf(SC[i]) != -1){
                    check_SC = 1;
                }
            }
            if(check_SC == 0){
                window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
                document.getElementById('f-password').value='';
            }
            if(document.getElementById('f-password').value !='' && document.getElementById('f-password2').value!=''){
                if(document.getElementById('f-password').value==document.getElementById('f-password2').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
    </script>
