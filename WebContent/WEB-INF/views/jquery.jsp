<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jquery 연습</title>
    <style type="text/css">
        body {
            font-size: 9pt;
            font-family: "굴림";
            }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
    <script>
    //방법1
    jQuery(document).ready(function(){
    	alert("안녕하세요. jQuery에 온 걸 환영합니다.");
    });
    //방법1을 간소화
    jQuery(function(){
    	alert("안녕하세요. jQuery에 온 걸 환영합니다.");
    });
    
    //방법1에서 jQuery 함수 대신 $ 함수로 변경
    $(document).ready(function(){
    	alert("안녕하세요. jQuery에 온 걸 환영합니다.");
    });
    
    $(function(){
    	alert(" 안녕하세요. jQuery에 온 걸 환영합니다.")
    })
    </script>
</head>
<body>
    <div>
        jQuery를 사용할 준비가 되었습니다.
    </div>
</body>
</html>