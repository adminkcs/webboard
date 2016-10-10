<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// UserVO user = new UserVO ( 100, "jane", "1111", "2016-05-33");
seq = [1, 2, 3 ]; // window.seq = [ 1, 3];
// 객체 리터럴
var user = {
	seq : 100 ,
	id  : 'jane' ,
	password : '1111' ,
	joinDate  : '2016-03-03' ,
	
	getSeq : function() {
		return this.seq ;
	} ,
	setSeq : function( newSeq ) {
		this.seq = newSeq ;
	}
	
	
}; // 인스턴스

var animal = {
	seq : 'sequence 100 ',
	type : 'dog'
};
function getter() {
	return this.seq ;
}

function setter (newSeq ) {
	this.seq = newSeq ;
}

// user.getSeq = getter ;
animal.getSeq = user.getSeq ;

// user.seq = [ 1, 2, 3, 4 ]; // error or ok

</script>
</head>
<body>

</body>
</html>