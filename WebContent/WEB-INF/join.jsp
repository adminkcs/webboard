<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
<script type="text/javascript" src="/js/placeholders.min.js"></script> <!-- IE9 이하에서 placeholder태그 문제 해결 -->
<!-- 상단 메뉴 시작  -->
<script type="text/javascript">
	function doJoin()
	{
		if(document.join.password.value!=document.join.password1.value)
		{
		alert("비밀번호 불일치");
		document.join.password.focus();
		return;
		}
		document.join.action = "doJoin";
		document.join.submit();
		
	}
</script>
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<h3>회원가입</h3>
<form name="join" method="post">
	<table>
		<tr>
			<td><input type="text" name="id" value="" placeholder="아이디입력"></td>
		</tr>
		<tr>
			<td><input type="password" name="password"  value="" placeholder="패스워드입력"></td>
		</tr>
		<tr>
			<td><input type="password" name="password1"  value="" placeholder="패스워드확인"></td>
		</tr>
		<tr>
			<td><input type="button" value="회원가입" onclick="JavaScript:doJoin()"></td>
		</tr>
	</table>
<!-- 	<button><a href="doLogin">로그인</a></button>  -->
</form>
</body>
</html>