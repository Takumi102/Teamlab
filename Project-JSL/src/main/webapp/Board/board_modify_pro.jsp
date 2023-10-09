<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${row==1}">
<script>
	alert("등록완료");
	location.href="/Board/board_list.do?page=${page}";
</script>
</c:when>
<c:otherwise>
<script>
	alert("등록실패");
	history.back();
</script>
</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>