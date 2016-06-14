<%@page import="github.kcs.board.vo.PostVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxpath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
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
<%-- 		 <p>글쓴사람의 ID : ${p.getWriter().getId() }</p>
		 <p>지금 로그인한 사람의 ID : ${loginUser.getId() }</p> --%>
		 
<script type="text/javascript">
function doDel(){
	document.board.method = "POST";
	document.board.action = "delete";
	document.board.submit();
}
</script>		 
 <form name="board">
 	<input type="hidden" name="pnum" value="${p.seq }">
 	<table border=1>
		 <c:if test="${ not empty p }">
			 <tr>
	 			<td>제목</td>
	 			<td>${p.title}</td>
	 			<td>카테고리</td>
	 			<td>${p.category}</td>
	 		 </tr>
			 <tr>
	 			<td>내용</td>
	 			<td colspan="3"><textarea rows="20" cols="40" disabled="disabled">${p.content}</textarea></td>
	 		 </tr>
		 </c:if>
		 <c:if test="${ empty p }">
		 <h3>글 없음</h3>
		 </c:if>
 	</table>
		<c:if test="${ p.writer.id eq loginUser.id  }"><li><a href="edit?pnum=${p.seq}">편집하기</a></c:if><!-- 지금 보고 있는 글을 쓴 사람과 로그인한 사람이 같을때에만 -->
		<c:if test="${ p.writer eq loginUser  }"><li>
		<a href="JavaScript:doDel()">삭제하기</a>
		</c:if><!-- 지금 보고 있는 글을 쓴 사람과 로그인한 사람이 같을때에만 -->
		<li><a href="${ctxpath}/list">목록으로</a>
</form>
</body>
</html>