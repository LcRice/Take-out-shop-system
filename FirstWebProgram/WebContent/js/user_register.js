function checkUserpassword() { // 检测密码
	var userpassword = document.getElementById("userpassword");
	var userpasswordResult = document.getElementById("userpasswordResult");
	if (userpassword.value.length < 6 || userpassword.value.length > 16) {
		userpasswordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>密码需在6-16字符之间</font>";
	} else {
		userpasswordResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}

}

function checkUserpassword2() { // 检测确认密码
	var userpassword = document.getElementById("userpassword");
	var userpassword2 = document.getElementById("userpassword2");
	var userpassword2Result = document.getElementById("userpassword2Result");
	if (userpassword.value != userpassword2.value) {
		userpassword2Result.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>两次密码输入不一致</font>";
	} else {
		userpassword2Result.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkUsername() { // 检测用户昵称
	var username = document.getElementById("username");
	var usernameResult = document.getElementById("usernameResult");
	if (username.value.length < 3) {
		usernameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户昵称不能为空</font>";
	} else {
		usernameResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkUsercardnumber() { // 检测用户卡号
	var usercardnumber = document.getElementById("usercardnumber");
	var usercardnumberResult = document.getElementById("usercardnumberResult");
	if (usercardnumber.value.length < 1) {
		usercardnumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户卡号不能为空</font>";
	} else {
		usercardnumberResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}
function checkUsercardpassword() { // 检测用户卡密
	var usercardpassword = document.getElementById("usercardpassword");
	var usercardpasswordResult = document
			.getElementById("usercardpasswordResult");
	if (usercardpassword.value.length < 1) {
		usercardpasswordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户卡密不能为空</font>";
	} else {
		usercardpasswordResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkUseraddress() { // 检测用户地址
	var useraddress = document.getElementById("useraddress");
	var useraddressResult = document.getElementById("useraddressResult");
	if (useraddress.value.length == 0) {
		useraddressResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户地址不能为空</font>";
	} else {
		useraddressResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}
// 表单级校验---提交时对每个字段检查
function checkData() {

	var usernumber = document.getElementById("usernumber");

	var userpassword = document.getElementById("userpassword");
	var userpassword2 = document.getElementById("userpassword2");

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

var xhr1 = new XMLHttpRequest();

function checkValCode() {

	var valCode = document.getElementById("valCode");

	xhr1.open("get", "CheckValCodeServlet?valCode=" + encodeURI(valCode.value),
			true);

	xhr1.send(null);

	xhr1.onreadystatechange = checkValCodeResult;

}

function checkValCodeResult() {
	var valCodeResult = document.getElementById("valCodeResult");
	if (xhr1.readyState == 4) {

		if (xhr1.status == 200) {
			if (xhr1.responseText == "success") {
				valCodeResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>验证码正确</font>";
			} else {
				valCodeResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>验证码输入错误，请重新输入</font>";
			}
		} else {
			valCodeResult.innerText = "调用失败";
		}
	}
}

function photoUpdate() {
	var photoUpdate = document.getElementById("photoUpdate");

	var photo = document.getElementsByName("photo");

	var flag = null;
	for (var i = 0; i < photo.length; i++) {
		if (photo[i].checked) {
			flag = photo[i].value;
		}
	}
	if (flag == "-1.gif") {
		photoUpdate.style.visibility = "visible";
	} else {
		photoUpdate.style.visibility = "hidden";
	}
}