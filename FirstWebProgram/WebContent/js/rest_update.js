/**
 * 
 */


	//表单级校验---提交时对每个字段检查
	function checkData() {

		var restnumber = document.getElementById("restnumber");

		var restpassword = document.getElementById("restpassword");
		
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
