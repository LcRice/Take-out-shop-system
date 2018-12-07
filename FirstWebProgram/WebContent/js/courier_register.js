/**
 * 
 */
function checkCourierpassword() { //检测密码
		var courierpassword = document.getElementById("courierpassword");
		var courierpasswordResult = document.getElementById("courierpasswordResult");
		if (courierpassword.value.length<6||courierpassword.value.length>16) {
			courierpasswordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>密码需在6-16字符之间</font>";
		} else {
			courierpasswordResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	
	}
	
	function checkCourierpassword2() {	//检测确认密码
		var courierpassword = document.getElementById("courierpassword");
		var courierpassword2 = document.getElementById("courierpassword2");
		var courierpassword2Result = document.getElementById("courierpassword2Result");
		if (courierpassword.value != courierpassword2.value) {
			courierpassword2Result.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>两次密码输入不一致</font>";
		} else {
			courierpassword2Result.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	
	function checkCouriername(){ //检测用户昵称
		var couriername = document.getElementById("couriername");
		var couriernameResult = document.getElementById("couriernameResult");
		if(couriername.value.length<3){
			couriernameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户昵称不能为空</font>";
		} else {
			couriernameResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	
	function checkData() {

		var couriernumber = document.getElementById("couriernumber");

		var courierpassword = document.getElementById("courierpassword");
		var courierpassword2 = document.getElementById("courierpassword2");

		var couriername = document.getElementById("couriername");
		
		if (couriernumber.value.length<5||couriernumber.value.length>10) {
			alert("用户名需在5-10字符之间");
			return false;
		}

		else if (courierpassword.value.length == 0) {
			alert("密码不能为空");
			return false;
		}

		else if (courierpassword.value != courierpassword2.value) {
			alert("两次密码输入不一致");
			return false;
		} 
		else if(couriername.value.length == 0){
			alert("用户昵称不能为空");
			return false;
		}
		else{
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

	function checkCouriernumber() {

		var couriernumber = document.getElementById("couriernumber");

		var couriernumberResult = document.getElementById("couriernumberResult");
		if (couriernumber.value.length<5||couriernumber.value.length>10) {
			couriernumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户名需在5-10字符之间</font>";
		} else {
			xhr.open("get", "CheckCouriernumberServlet?couriernumber=" + encodeURI(couriernumber.value), true);

			xhr.send(null);

			xhr.onreadystatechange = checkCouriernumberResult;
		}
	}

	function checkCouriernumberResult() {
		var couriernumberResult = document.getElementById("couriernumberResult");
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					couriernumberResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>用户名可用</font>";
				} else {
					couriernumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户已存在</font>";
				}
			} else {
				couriernumberResult.innerText = "调用失败";
			}
		}
	}
	
	var xhr1 = new XMLHttpRequest();
	
	function checkValCode() {

		var valCode = document.getElementById("valCode");

		xhr1.open("get", "CheckValCodeServlet?valCode=" + encodeURI(valCode.value), true);

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
		for(var i = 0;i<photo.length;i++){
			if(photo[i].checked){
				flag = photo[i].value;
			}
		}
		if(flag == "-1.gif"){
			photoUpdate.style.visibility = "visible";
		}else{
			photoUpdate.style.visibility = "hidden";
		}
	}