<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
   console.log("before body");
   var div = document.getElementById("content");
   console.log ( div );
   
/*
 * 비동기 방식으로 함수가 호출됩니다.
 */
 /*
var callbacks = []; //
callbacks.push (function() {
    alert ( 'ok?');
});
 
callbacks.push(function() {
	alert ( 'second');
});

window.onload = function() {
	for ( var i = 0 ; i < callbacks.length ; i++ ) {
		callbacks[i]();
	}
}

window.onload = function(){
	;
}
*/


$( document ).ready ( function() {
    alert ( 'first');
});

$( document).ready ( function() {
	alert ( 'second');
});

</script>
</head>
<body>

<div id="content">haha</div>
</body>
<script type="text/javascript">
    console.log("after body");
    var div = document.getElementById("content");
    console.log ( div );
</script>
</html>