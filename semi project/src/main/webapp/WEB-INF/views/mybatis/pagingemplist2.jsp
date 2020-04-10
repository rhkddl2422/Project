<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title></title>

</head>
<body>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${paginglist}"   var="vo">
    사번 : ${vo.employee_id } : 성:${vo.last_name } : 이름${vo.first_name }
    :${vo.hire_date }<br>
</c:forEach>
</body>
</html>