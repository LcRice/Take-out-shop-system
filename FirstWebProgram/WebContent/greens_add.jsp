<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上架菜</title>
<script type="text/javascript" src="js/greens_add.js"></script>
</head>
<body>
	<h2>上架菜</h2>
	<hr color="gray" />
	<form action="GreensAddServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
		<table>
			<tr>
				<td>菜名</td>
				<td>
					<input type="text" name="greensname" id="greensname" onblur="checkGreensname()" />
					<span id="greensnameResult"></span>
				</td>
			</tr>
			<tr>
				<td>菜价格</td>
				<td>
					<input type="text" name="greensprice" id="greensprice" onblur="checkGreensprice()" />
					<span id="greenspriceResult"></span>	
				</td>
			</tr>
			
			<tr>
				<td align="right">菜照片：</td>
				<td>
					<input type="file" name="file" id="file">
				</td>
			</tr>
			<tr>
				<td>库存</td>
				<td>
					<input type="text" name="greensnumber" id="greensnumber" onblur="checkGreensnumber()" />
					<span id="greensnumberResult"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input style="margin-right: 50px;" type="submit" value="上架" />
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>