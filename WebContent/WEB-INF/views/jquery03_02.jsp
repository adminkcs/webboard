<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>load event 테스트</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
    <script>
    $(document).ready(function(){
    	$("#target").html("안녕하세요.");
    })
    </script>
</head>
<body>
    <div id="target"> </div>    
</body>
</html>