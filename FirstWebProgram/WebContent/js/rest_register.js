/**
 * 
 */

	function checkRestpassword() { //检测密码
		var restpassword = document.getElementById("restpassword");
		var restpasswordResult = document.getElementById("restpasswordResult");
		if (restpassword.value.length<6||restpassword.value.length>16) {
			restpasswordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>密码需在6-16字符之间</font>";
		} else {
			restpasswordResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	
	}
	
	function checkRestpassword2() {	//检测确认密码
		var restpassword = document.getElementById("restpassword");
		var restpassword2 = document.getElementById("restpassword2");
		var restpassword2Result = document.getElementById("restpassword2Result");
		if (restpassword.value != restpassword2.value) {
			restpassword2Result.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>两次密码输入不一致</font>";
		} else {
			restpassword2Result.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	
	function checkRestname(){ //检测用户昵称
		var restname = document.getElementById("restname");
		var restnameResult = document.getElementById("restnameResult");
		if(restname.value.length<3){
			restnameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户昵称不能为空</font>";
		} else {
			restnameResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	
	function checkRestcardnumber(){ //检测用户卡号
		var restcardnumber = document.getElementById("restcardnumber");
		var restcardnumberResult = document.getElementById("restcardnumberResult");
		if(restcardnumber.value.length<1){
			restcardnumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户卡号不能为空</font>";
		} else {
			restcardnumberResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	function checkRestcardpassword(){ //检测用户卡密
		var restcardpassword = document.getElementById("restcardpassword");
		var restcardpasswordResult = document.getElementById("restcardpasswordResult");
		if(restcardpassword.value.length<1){
			restcardpasswordResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户卡密不能为空</font>";
		} else {
			restcardpasswordResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	
	function checkRestaddress(){ //检测用户地址
		var restaddress = document.getElementById("restaddress");
		var restaddressResult = document.getElementById("restaddressResult");
		if(restaddress.value.length == 0){
			restaddressResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户地址不能为空</font>";
		} else {
			restaddressResult.innerHTML = "<img src = 'image/yes.gif'/>";
		}
	}
	//表单级校验---提交时对每个字段检查
	function checkData() {

		var restnumber = document.getElementById("restnumber");

		var restpassword = document.getElementById("restpassword");
		var restpassword2 = document.getElementById("restpassword2");
		
		var restname = document.getElementById("restname");
		var restcardnumber = document.getElementById("restcardnumber");
		var restcardpassword = document.getElementById("restcardpassword");
		var restaddress = document.getElementById("restaddress");
		
		if (restnumber.value.length<5||restnumber.value.length>10) {
			alert("用户名需在5-10字符之间");
			return false;
		}

		else if (restpassword.value.length == 0) {
			alert("密码不能为空");
			return false;
		}

		else if (restpassword.value != restpassword2.value) {
			alert("两次密码输入不一致");
			return false;
		} 
		else if(restname.value.length == 0){
			alert("用户昵称不能为空");
			return false;
		}
		else if(restcardnumber.value.length == 0){
			alert("用户卡号不能为空");
			return false;
		}
		else if(restcardpassword.value.length == 0){
			alert("用户卡密不能为空");
			return false;
		}
		else if(restaddress.value.length == 0){
			alert("用户地址不能为空");
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

	function checkRestnumber() {

		var restnumber = document.getElementById("restnumber");

		var restnumberResult = document.getElementById("restnumberResult");
		if (restnumber.value.length<5||restnumber.value.length>10) {
			restnumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户名需在5-10字符之间</font>";
		} else {
			xhr.open("get", "CheckRestnumberServlet?restnumber=" + encodeURI(restnumber.value), true);

			xhr.send(null);

			xhr.onreadystatechange = checkRestnumberResult;
		}
	}

	function checkRestnumberResult() {
		var restnumberResult = document.getElementById("restnumberResult");
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					restnumberResult.innerHTML = "<img src = 'image/yes.gif'/><font color='green' size='1px'>用户名可用</font>";
				} else {
					restnumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>用户已存在</font>";
				}
			} else {
				restnumberResult.innerText = "调用失败";
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