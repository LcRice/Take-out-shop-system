/**
 * 
 */

// 表单级校验---提交时对每个字段检查
function checkData() {

	var usernumber = document.getElementById("usernumber");

	var userpassword = document.getElementById("userpassword");

	var username = document.getElementById("username");
	var usercardnumber = document.getElementById("usercardnumber");
	var usercardpassword = document.getElementById("usercardpassword");
	var useraddress = document.getElementById("useraddress");

	if (usernumber.value.length < 5 || usernumber.value.length > 10) {
		alert("用户名需在5-10字符之间");
		return false;
	} else if (userpassword.value.length == 0) {
		alert("密码不能为空");
		return false;
	} else if (userpassword.value != userpassword2.value) {
		alert("两次密码输入不一致");
		return false;
	} else if (usename.value.length == 0) {
		alert("用户昵称不能为空");
		return false;
	} else if (usercardnumber.value.length == 0) {
		alert("用户卡号不能为空");
		return false;
	} else if (usercardpassword.value.length == 0) {
		alert("用户卡密不能为空");
		return false;
	} else if (useraddress.value.length == 0) {
		alert("用户地址不能为空");
		return false;
	} else {
		var photo = document.getElementsByName("photo");

		var file = document.getElementById("file").value;
		var flag = null;
		for (var i = 0; i < photo.length; i++) {
			if (photo[i].checked) {
				flag = photo[i].value;
			}
		}
		if (flag == "-1.gif") {
			if (file.length == 0) {
				alert("头像不能为空！");
				return false;
			} else {
				return true;
			}
		}
	}
}

var xhr = new XMLHttpRequest();

function checkUsernumber() {

	var usernumber = document.getElementById("usernumber");

	var usernumberResult = document.getElementById("usernumberResult");
	if (usernumber.value.length < 5 || usernumber.value.length > 10) {
		usernumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户名需在5-10字符之间</font>";
	} else {
		xhr.open("get", "CheckUsernumberServlet?usernumber="
				+ encodeURI(usernumber.value), true);

		xhr.send(null);

		xhr.onreadystatechange = checkUsernumberResult;
	}
}

function checkUsernumberResult() {
	var usernumberResult = document.getElementById("usernumberResult");
	if (xhr.readyState == 4) {

		if (xhr.status == 200) {
			if (xhr.responseText == "success") {
				usernumberResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>用户名可用</font>";
			} else {
				usernumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户已存在</font>";
			}
		} else {
			usernumberResult.innerText = "调用失败";
		}
	}
}
