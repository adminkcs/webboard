<%@page import="github.kcs.board.vo.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxpath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 보기</title>
</head>
<body>
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<%--
<%
	PostVO post = (PostVO) request.getAttribute("p"); // post == null
	out.print("<h3>" + post.getTitle() + "</h3>");
	out.print("<p>" + "아직 본문 내용이 없다" + "</p>");
%>
 --%>
 <c:if test="${ not empty p }">
 <h3>${p.title}</h3> 
 <h3>${p.content}</h3> 
 <p>아직 본문 없음</p>
 </c:if>
 <c:if test="${ empty p }">
 <h3>글 없음</h3>
 </c:if>
 <p>글쓴사람의 ID : ${p.getWriter().getId() }</p>
 <p>지금 로그인한 사람의 ID : ${loginUser.getId() }</p>
<c:if test="${ p.writer.id eq loginUser.id  }"><li><a href="edit">편집하기</a></c:if><!-- 지금 보고 있는 글을 쓴 사람과 로그인한 사람이 같을때에만 -->
<c:if test="${ p.writer eq loginUser  }"><li><a href="delete">삭제하기</a></c:if><!-- 지금 보고 있는 글을 쓴 사람과 로그인한 사람이 같을때에만 -->
<li><a href="${ctxpath}/list">목록으로</a>
</body>
</html>