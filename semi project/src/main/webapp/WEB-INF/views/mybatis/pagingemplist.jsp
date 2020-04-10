<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title></title>

</head>
<body>
<h3>
<% 
int totalPage = (int)request.getAttribute("totalPage"); 
for(int i = 1; i <= totalPage; i++){
	out.println("<a href='/tabi/mybatis/pagingemplist2?pagenum=" + i +"'>"   + i + "</a>&nbsp;" );
}
%>
</h3>
</body>
</html>

