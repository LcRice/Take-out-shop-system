<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅注册</title>
<script type="text/javascript" src="js/rest_register.js"></script>
</head>
<body>
	<h2>餐厅注册</h2>
	<hr color="gray">
	<form action="RestRegisterServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
		<table>
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="restnumber" id="restnumber" onblur="checkRestnumber()" />
					<span id="restnumberResult"></span>
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="restpassword" id="restpassword" onblur="checkRestpassword()" />
					<span id="restpasswordResult"></span>	
				</td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td>
					<input type="password" name="restpassword2" id="restpassword2" onblur="checkRestpassword2()" />
					<span id="restpassword2Result"></span>
				</td>
			</tr>
			
			<tr>
				<td align="right">头像：</td>
				<td><input type="radio" name="photo" value="1.gif" checked="checked" onclick="photoUpdate()">
						<img alt="" src="image/photo/1.gif">
					<input type="radio" name="photo" value="2.gif" onclick="photoUpdate()">
						<img alt="" src="image/photo/2.gif"> 
					<input type="radio" name="photo" value="3.gif" onclick="photoUpdate()">
						<img alt="" src="image/photo/3.gif"> 
					<input type="radio" name="photo" value="4.gif" onclick="photoUpdate()">
						<img alt="" src="image/photo/4.gif">
					<input type="radio" name="photo" value="5.gif" onclick="photoUpdate()">
						<img alt="" src="image/photo/5.gif">
					<br/>
					
					<input type="radio" name="photo" value ="-1.gif" onclick="photoUpdate()">自定义
					<span id = "photoUpdate" style="visibility: hidden;"><input type="file" name="file" id="file"></span>
				
				</td>
			</tr>
			<tr>
				<td>用户昵称</td>
				<td>
					<input type="text" name="restname" id="restname" onblur="checkRestname()" />
					<span id="restnameResult"></span>	
				</td>
			</tr>
			<tr>
				<td>卡号</td>
				<td>
					<input type="text" name="restcardnumber" id="restcardnumber" onblur="checkRestcardnumber()" />
					<span id="restcardnumberResult"></span>	
				</td>
			</tr>
			<tr>
				<td>卡密</td>
				<td>
					<input type="password" name="restcardpassword" id="restcardpassword" onblur="checkRestcardpassword()" />
					<span id="restcardpasswordResult"></span>		
				</td>
			</tr>
			<tr>
				<td>地址</td>
				<td>
					<input type="text" name="restaddress" id="restaddress" onblur="checkRestaddress()" />
					<span id="restaddressResult"></span>	
				</td>
			</tr>
			<tr>
				<td>验证码</td>
				<td><input type="text" name="valCode" id = "valCode" onblur = "checkValCode()"> 
				<img src="ValCodeServlet" id="imgValCode" onclick="this.src=this.src+'?'" /> 
				<input type="button" value="更新" onclick="document.getElementById('imgValCode').src=document.getElementById('imgValCode').src+'?'" />
					<span id = "valCodeResult"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input style="margin-right: 50px;" type="submit" value="注册" />
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>