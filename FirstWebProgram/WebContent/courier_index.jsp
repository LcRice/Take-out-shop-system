<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配送员主页</title>
</head>
<body>
<img alt="" src="image/photo/${sessionScope.courier.courierphoto }">
${sessionScope.courier.couriername },你好!
<hr color="gray" />
<a href="CourierFinishServlet">完成订单配送</a>
<hr color="gray" />
<a href="CourierLogoutServlet">退出</a>
</body>
</html>