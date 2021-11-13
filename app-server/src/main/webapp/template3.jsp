<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.css">
  
  <script src="https://kit.fontawesome.com/26add2f61b.js" crossorigin="anonymous"></script>
  <script src="${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextRoot}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>

<style>
body {
    height: 100%
    margin: 0; 
    font-family: Arial, Helvetica, sans-serif; 
    display: grid; justify-items: center; 
    align-items: center; 
    background-color: #3a3a3a;
    min-width:80%; 
  }  
  
  html, body {
    height: 100%; 
    min-width:80%; 
  } 
  
   #main-holder {
   width: 40%;
   height: 80%; 
   display: grid; 
   justify-items: center; 
   align-items: center; 
   background-color: white; 
   border-radius: 7px; 
   box-shadow: 0px 0px 5px 2px black; 
   min-width:500px; 
 }   
   
  #login-error-msg-holder { 
  width: 100%; 
  height: 100%;
  display: grid; 
  justify-items: center; 
  align-items: center; 
  min-width:60%; 
  } 
  
  #login-error-msg { 
  width: 43%; 
  text-align: center; 
  margin: 0; 
  padding: 5px; 
  font-size: 14px; 
  font-weight: bold; 
  color: #8a0000; 
  background-color: #e58f8f; 
  opacity: 80% 
  }
   
  #error-msg-second-line { 
   display: block;
  }
  
 
  #login-form { 
    align-self: flex-start; 
    display: grid; 
    justify-items: center; 
    align-items: center; 
  }

  .login-form-field::placeholder { 
   color: #3a3a3a; 
  }

  .login-form-field { 
    width: 100%; 
    border: none; 
    border-bottom: 1px solid #3a3a3a; 
    margin-bottom: 10px; 
    border-radius: 3px; 
    outline: none; 
    padding: 0px 0px 5px 5px
    outline: none; 
  }
 
  #login-form-submit { 
    width: 30%; 
    padding: 7px; 
    border: none; 
    border-radius: 5px; 
    color: white; 
    font-weight: bold;
    background-color: #3a3a3a; 
    cursor: pointer; 
    outline: none; 
    xmin-width:60%; 
  }  
  
  
</style>

<body>
 <jsp:include page="${contentUrl}"/>

</body>
</html>