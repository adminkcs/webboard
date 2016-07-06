<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글 달기</title>
</head>
<body>
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<form action="doReply" method="post" enctype="multipart/form-data">
<input type="hidden" name="parent" value=${parent.seq}>

<div>
<h3>원글</h3>
<div>${parent.content }</div>
</div>
<table>
    <tr>
        <td>제목</td>
        <td><input type="text" name = "title" value="title"></td>
        <td>카테고리</td>
        <td>
        <!-- 
        <select id="ddd" name = "category">
            <c:forEach items="${codes }" var="cd">
            <option value="${cd.cdDvsId }">${cd.cdNm }</option>
            </c:forEach>
        </select>
         -->
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td colspan="3"><textarea rows="20" cols="100%" name="content">content</textarea></td>
    </tr>
    <tr>
        <td>파일</td>
        <td colspan="3"><input type="file" name="f"></td>
    </tr>
    <tr>
        <td colspan="4" align="right"><input type="submit" value="저장"></td>
    </tr>
</table>
</form>
</body>
</html>