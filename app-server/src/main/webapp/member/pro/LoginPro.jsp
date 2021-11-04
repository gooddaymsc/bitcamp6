<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ page import="com.eomcs.pms.jsp.member.model.MemberDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>로그인 처리 JSP</title>
</head>
<body>
<%
        // 로그인 화면에 입력된 아이디와 비밀번호를 가져온다
        String id= request.getParameter("id");
        String pw = request.getParameter("password");
        
        // DB에서 아이디, 비밀번호 확인
        MemberDAO memberDAO = MemberDAO.getInstance();
        int check = memberDAO.loginCheck(id, pw);

        String msg = "";
        
        if(check  == 1)    // 로그인 성공
        { 
            // 세션에 현재 아이디 세팅
            session.setAttribute("sessionID", id);
            msg = "../../MainForm.jsp";
        }
        else if(check  == 0) // 아이디나 비밀번호가 틀릴경우
        {
            msg = "../view/LoginForm.jsp?msg=0";
        }
        // sendRedirect(String URL) : 해당 URL로 이동
        // URL뒤에 get방식 처럼 데이터를 전달가능
        response.sendRedirect(msg);
%>
</body>
</html>