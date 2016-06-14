<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><!-- html5로 적용됩니다. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<script type="text/javascript" src="/js/placeholders.min.js"></script> <!-- IE9 이하에서 placeholder태그 문제 해결 -->
<!-- 상단 메뉴 시작  -->
<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
<!-- 상단 메뉴 끝 -->
<form  action="doLogin" method="post">
    <table>
        <tr> 
            <td><input type="text" name="id" value="james" placeholder="아이디입력"></td>
        </tr>
        <tr>
            <td><input type="password" name="password"  value="1111" placeholder="패스워드입력"></td>
        </tr>
        <tr>
            <td><input type="submit" value="로그인"></td>
        </tr>
    </table>
<!--     <button><a href="doLogin">로그인</a></button>  -->
</form>
</body>
</html>