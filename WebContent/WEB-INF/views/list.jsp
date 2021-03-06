<%@page import="github.kcs.board.vo.PostVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
<title>게시판 - ${category }</title>

<style type="text/css">
.menu {
    background-color: #ff0;
}

#page-area {
    margin : 10px;
}
#page-area .pnum {
    padding : 4px;
    background-color: yellow;
}
.padding1 {
    padding-left:14px;
}
.padding2 {
    padding-left:28px;
}
.padding3 {
    padding-left:42px;
}
</style>

<script type="text/javascript">
function go ( pnum ) {
    var input = document.getElementById('pnum') ;
    input.value = pnum;
    var frm = document.getElementById('frm') ;
    frm.submit();
}

$(document).ready(function(){
	// mouseover, mouseout
	$('#posts tr.each-post').on('mouseover', function(e){
		  var td = e.target; // mouseover 이벤트를 발생시킨 dom element
		  //var tr = $( td ).parent(); // td의 부모를 찾아줌. jquery 기능
		  var tr = $( td ).closest('.each-post'); // td의 부모를 찾아줌. jquery 기능
		  tr.css('background-color', 'yellow');
	});
	
	$('#posts tr.each-post').on('mouseout', function(e){
		  var td = e.target; // mouseover 이벤트를 발생시킨 dom element
		  //var tr = $( td ).parent(); // td의 부모를 찾아줌. jquery 기능
		  var tr = $( td ).closest('.each-post'); // td의 부모를 찾아줌. jquery 기능
		  tr.css('background-color', 'white');
	});
	
	
});
</script>
</head>
<body>
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<form action="<%=request.getContextPath() %>/read" method="post" id="frm">
<input type="hidden" name="pnum" id="pnum" value="1005">
</form>
<h3>${prePage }게시판 리스트 출력[2]</h3>
<b> ${curPage} of ${totalPage } page</b>
<table border="1" id="posts" >
    <tr>
        <td>글번호</td>
        <td>제목</td>
        <td>조회수</td>
        <td>작성일</td>
        <td>작성자</td>
        <td>카테고리</td>
    </tr>
<c:forEach var="k" items="${allPosts }">
    <tr class="each-post">
        <td>${k.seq}</td>
        <!-- http://localhost:8080/board/read?pid=1005 -->
        <td><span class="padding${ k.indentation }"></span><a href="#" onclick="go(${k.seq}); return false;">${k.title }</a></td>
        <td>${k.viewCount }</td>
        <td>${k.time }</td> <!-- k.getCreationTime() -->
        <td>${k.writer.id }</td> <!-- k.getWriter().getId() -->
        <td><a href="<%=request.getContextPath()%>/list?c=${k.category.cdDvsId}">${k.category.cdNm }</a></td> <!-- k.getWriter().getId() -->
    </tr>    
</c:forEach>
</table>
<div id="page-area">
<c:forEach var="l" items="${pageNums }">
    <span class="pnum">
        <c:if test="${l != curPage}"> <a href="/webboard/list?pnum=${l }">${l }</a></c:if>
        <c:if test="${l == curPage}"><b>${l }</b></c:if>
    </span> 
</c:forEach>
</div>
<jsp:include page="/WEB-INF/views/form-search.jsp"></jsp:include>
<%-- 기존 리스트 뽑아내는 것
<%
    List<PostVO> posts = (List<PostVO>) request.getAttribute("allPosts");
    for ( PostVO p : posts ) {
        out.println("<li>" +  p.toString() ) ;
    }
%> --%>
<button><a href="write">글쓰기</a></button>
</body>
</html>