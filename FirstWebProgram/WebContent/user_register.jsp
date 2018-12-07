<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript" src="js/user_register.js"></script>
</head>
<body>
	<h2>用户注册</h2>
	<hr color="gray">
	<form action="UserRegisterServlet" method="post" onsubmit="return checkData();" enctype="multipart/form-data">
		<table>
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="usernumber" id="usernumber" onblur="checkUsernumber()" />
					<span id="usernumberResult"></span>
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="userpassword" id="userpassword" onblur="checkUserpassword()" />
					<span id="userpasswordResult"></span>	
				</td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td>
					<input type="password" name="userpassword2" id="userpassword2" onblur="checkUserpassword2()" />
					<span id="userpassword2Result"></span>
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
					<input type="text" name="username" id="username" onblur="checkUsername()" />
					<span id="usernameResult"></span>	
				</td>
			</tr>
			<tr>
				<td>卡号</td>
				<td>
					<input type="text" name="usercardnumber" id="usercardnumber" onblur="checkUsercardnumber()" />
					<span id="usercardnumberResult"></span>	
				</td>
			</tr>
			<tr>
				<td>卡密</td>
				<td>
					<input type="password" name="usercardpassword" id="usercardpassword" onblur="checkUsercardpassword()" />
					<span id="usercardpasswordResult"></span>		
				</td>
			</tr>
			<tr>
				<td>地址</td>
				<td>
					<input type="text" name="useraddress" id="useraddress" onblur="checkUseraddress()" />
					<span id="useraddressResult"></span>	
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