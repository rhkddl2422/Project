<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title></title>

</head>
<body>
<h1> ${member.userid } 회원님 로그아웃되셨습니다.</h1>
<% session.removeAttribute("member"); 
//session.invalidate();
%>
</body>
</html>