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
<input type="hidden" name="seq" value=${loginUser.seq }>
<table>
    <tr>
        <td>제목</td>
        <td><input type="text" name = "title"></td>
        <td>카테고리</td>
        <td><input type="text" name = "category"></td>
    </tr>
    <tr>
        <td>내용</td>
        <td colspan="3"><textarea rows="20" cols="100%" name="content"></textarea></td>
    </tr>
    <tr>
        <td colspan="4" align="right"><input type="submit" value="저장"></td>
    </tr>
</table>
</form>
</body>
</html>