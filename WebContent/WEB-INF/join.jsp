<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<h3>회원가입</h3>
<form  action="doJoin" method="post">
	<table>
		<tr>
			<td><input type="text" name="id" value="" placeholder="아이디입력"></td>
		</tr>
		<tr>
			<td><input type="password" name="password"  value="1111" placeholder="패스워드입력"></td>
		</tr>
		<tr>
			<td><input type="submit" value="회원가입"></td>
		</tr>
	</table>
<!-- 	<button><a href="doLogin">로그인</a></button>  -->
</form>
</body>
</html>