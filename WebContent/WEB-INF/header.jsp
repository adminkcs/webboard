<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${empty loginUser }">
    <span class="menu"><a href="join">가입하기</a></span>
    <span class="menu"><a href="login">로그인</a></span>
    </c:if>
    <c:if test="${not empty loginUser }">
    <span>${loginUser.id}</span>
    <span class="menu"><a href="logout">로그아웃</a></span>
    </c:if>
</div>
