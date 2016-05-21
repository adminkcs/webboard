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

<script type="text/javascript">
function doRead(){
	document.read.method = "POST";
	document.read.action = "read";
	document.read.submit();
}

function doEdit(pnum){
	document.read.method = "POST";
	document.read.action = "doEdit";
	document.read.submit();
}
</script>

<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
 <form name="read">
 <input type="hidden" name="pnum" value=${p.seq }>
 	<table border=1>
		 <c:if test="${ not empty p }">
			 <tr>
	 			<td>제목</td>
	 			<td><input type="text" name="title" value=${p.title }></td>
	 		 </tr>
			 <tr>
	 			<td>내용</td>
	 			<td><textarea rows="20" cols="40" name="content">${p.content}</textarea></td>
	 		 </tr>
		 </c:if>
		 <c:if test="${ empty p }">
		 <h3>글 없음</h3>
		 </c:if>
 	</table>
		<c:if test="${ p.writer.id eq loginUser.id  }"><li><a href="JavaScript:doEdit()">저장하기</a></c:if><!-- 지금 보고 있는 글을 쓴 사람과 로그인한 사람이 같을때에만 -->
		<c:if test="${ p.writer eq loginUser  }"><li><a href="JavaScript:doRead()">저장취소</a></c:if><!-- 다시 상세보기로 이동 -->
</form>
</body>
</html>