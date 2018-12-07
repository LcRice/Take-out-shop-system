<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
<script type="text/javascript">
function showTime() {
	var time = document.getElementById("time");
	
	if(time.innerText > 1){
		time.innerText--;
	}else{
		location.href="courier_index.jsp";
	}
}
</script>
</head>
<body onload="setInterval('showTime()', 1000)">
<h3 align="center"><font color="red" size="5">恭喜您注冊成功</font></h3>
<h4 align="center"><font color="red" size="5"><span id="time">3</span></font>秒后返回主頁面，若沒返回，請點擊<a href="courier_index.jsp">這裏</a></h4>
</body>
</html>