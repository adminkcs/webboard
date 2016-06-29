<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<form action="<%=request.getContextPath()%>/search" method="GET">
    <select name="type">
        <option value="1">제목   <!-- int tnum = req.getParameter("type"); // 1, 2, 3 -->
        <option value="2">내용
        <option value="3">제목 + 내용
    </select>
    <input type="text" name="sw">
    <input type="submit" value="검색">
</form>
</div>