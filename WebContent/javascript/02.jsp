<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.nums {
    background-color: yellow;
}
.even {
    background-color: aqua;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
function ArrayList () {
	var elementData = [] ; // array
	
	return {
		add : function ( e ) {
			elementData.push ( e );
		},
		get : function ( idx ) {
			return elementData [ idx ];
		},
		size : function () {
			return elementData.length ;
		},
		toString : function () {
			console.log ( elementData );
		},
		clear : function () {
			elementData = [];
		}
	}
}

var nums = ArrayList();
nums.add(100);
nums.add(200);
nums.add(300); // number, string, array, object

function updateView () {
	// update view
    var cnt = $('#list-size');
    var str = '<h3>' + nums.size() + '개</h3>';
    cnt.html ( str ); // jquery .html : 화면 html 을 동적으로 구성할 때 사용함.
    
    var content = '';
    for(var i = 0; i < nums.size() ; i ++ ) {
        var elem = nums.get(i);
        if(i%2 == 0) {
            content += '<div class="nums odd">' + elem + '</div>';
        	
        } else {
            content += '<div class="nums even">' + elem + '</div>';
        	
        }
        
    }
    $('#contents').html(content);
    /*
    $.each ( [1, 2, 3, ], function ( idx, value){
        
    });
    */
}
$(document).ready ( function () {
	/*
	console.log ( $('#list-view') ); // arra
	var nums = $( '.num'  );
	console.log ( nums );
	console.log ( $('.num').text() ); // [ ]
	*/
	updateView();
	
	$('#btn_add').on ( 'click', function(e){
		var e = $('#elem').val() ;
		nums.add ( e );
		$('#elem').val('');
		
		updateView();
	});
	
	$( '#btn_reset' ).click ( function(e){
		nums.clear();
		updateView();
	});
	
	$( '#elem' ).on ( 'keyup', function(e) {
		if ( e.keyCode == 13 ){
	        nums.add ( $('#elem').val() );
	        $('#elem').val('');
	        updateView();			
		}
	});
});

 ( function ( p ) { alert( '이게 뜰거 같음.'); })('yes');
</script>

</head>
<body>
<div>
    <div><input type="text" id="elem"> <input type="button" id="btn_add" value="추가"> <input type="button" id="btn_reset" value="RESET"></div>
</div>
<div id="list-view">
    <div id="list-size">3개</div>
    <div id="contents">
    <!-- 
        <div class="num odd" >100</div>
        <div class="num even">200</div>
        <div class="num odd">300</div>
     -->
    </div>
</div>
</body>

</html>