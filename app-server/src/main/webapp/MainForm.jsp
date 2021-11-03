<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<html>
<head>
	<title>메인 화면</title>
	
	<script type="text/javascript">
		
		// 로그아웃 담당 JSP로 이동
		function logoutPro(){
			location.href="member/pro/LogoutPro.jsp";
		}
	</script>
</head>
<body>
	<b><font size="5" color="skyblue">메인화면입니다.</font></b><br><br>
	<%
		if(session.getAttribute("sessionID") == null) // 로그인이 안되었을 때
		{ 
			// 로그인 화면으로 이동
			response.sendRedirect("member/view/LoginForm.jsp");
		}
		else // 로그인 했을 경우
		{
	%>
	
	<h2>
		<font color="red"><%=session.getAttribute("sessionID") %></font>
		님 로그인되었습니다.
	</h2>
	
	<br><br>
	<input type="button" value="로그아웃" onclick="logoutPro()" />
	
	<%} %>	
</body>
</html>