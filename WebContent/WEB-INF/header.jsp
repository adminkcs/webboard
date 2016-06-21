<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function listSeach(url) 
{
  document.location.href= url;
}
</script>
<div>
    <c:if test="${empty loginUser }">
    <span class="menu"><a href="join">가입하기</a></span>
    <span class="menu"><a href="login">로그인</a></span>
    </c:if>
    <c:if test="${not empty loginUser }">
    <span>${loginUser.id}</span>
    <span class="menu"><a href="logout">로그아웃</a></span>
    </c:if>
    <span>
      <select name="headerCdNm" id="headerCdNm" onchange="listSeach(this.value)">
          <option value="">선택</option>
          <option value="<%=request.getContextPath()%>/list">전체보기</option>
	      <c:forEach items="${codes }" var="cd">
	      <option value="<%=request.getContextPath()%>/list?c=${cd.cdDvsId}">${cd.cdNm }</option>
	      </c:forEach>
      </select>
    </span>
</div>