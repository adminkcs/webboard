<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기페이지</title>
</head>
<body>
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<form action="doWrite" method="post">
<table>
	<tr>
		<td><input type="text" name = "title" value="제목"></td>
	</tr>
	
	<tr>
		<td><textarea rows="20" cols="40" name="content"></textarea></td>
	</tr>
	<tr>
		<td><input type="submit" value="저장"></td>
	</tr>
</table>
</form>
</body>
</html>