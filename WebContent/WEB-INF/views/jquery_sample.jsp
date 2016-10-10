<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <style type="text/css">
       body{
        font-size: 9pt;
        font-family: "굴림";
       }
       div, p, ul, li{
       border:1px #eeeeee solid;
       margin:10px;
       }
       ul{
        padding:10px;
       }
       li.select{
        background-color: #ccc;
       }
    </style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
<script>
   $(document).ready(function(){
	   
	   //id header 태그에 css적용
	   //$("#header").css("border","4px solid #f00");
	   //아래같이 변수 선언 후 담아서 사용가능
	   //var $header = $("#header");
	   //$header.css("border", "4px solid #f00");
	   
	   //p태그 전체에 css적용
	  // $("p").css("border","4px solid #f00");
	   
	   //test1 클래스 css적용 (크롬에서 마지막 태그에만 적용되는것으로 보임)
	   //$(".test1").css("border","4px solid #f00");
       
	   //ul.menu 노드에서 select클래스가 적용된 메뉴 li를 찾아 css를 적용
	   //$("ul.menu li.select").css("border","4px solid #f00");
       
	   //class가 적용된 모든 노드를 찾아 css적용
	   //$("[class]").css("border","4px solid #f00");
       
	   //문서에서 클래스 이름에 test가 포함된 모든 노드를 찾아 css적용
	   //$("[class*=test]").css("border","4px solid #f00");
       
   })
</script>
</head>
<body>
    <div id="samplepage" class="page">
    샘플 페이지 (div, id=samplePage, class=page)
    <div id="header">
        헤더영역 (div, id=header)
    </div>
    <div id = "content" class="smple-content">
        노드 찾기(div, id=content, class=sample-content)
        <ul class="menu">
            일반 노드 찾기(ul, class=menu)
            <li>id로 찾기(li)</li>
            <li class="select">tag로 찾기(li, class = select)</li>
            <li>class로찾기(li)</li>
            <li class=test1">속성으로 찾기(li, class=test1)</li> 
        </ul>
        <div class="content-data">
            자식 노드 찾기(div, class=content-data)
            <p class="test1"> 1. 모든 자식 노드 찾기(p, class=test1)</p>
            <p>2. 특정 자식 노드만 찾기(p) </p>
            <p class="test2">3. 마지막 자식 노드 찾기(p, class=test2)</p>
        </div>
    </div>
    <div id="footer">
        푸터 영역(div, id=footer)
    </div>
    </div>
</body>
</html>