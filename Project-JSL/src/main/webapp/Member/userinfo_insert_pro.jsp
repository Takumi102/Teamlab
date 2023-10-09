<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${row==1}">
<script>
	alert("등록성공");
	location.href="/Board/board_list.do";
</script>
</c:if>
<c:if test="${row==0 }">
<script>
	alert("오류");
	history.back();
</script>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>