/**
 * 
 */
function checkGreensname() {
	var greensname = document.getElementById("greensname");
	var greensnameResult = document.getElementById("greensnameResult");
	if (greensname.value.length == 0) {
		greensnameResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>菜名不能为空</font>";
	} else {
		greensnameResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkGreensprice() {
	var greensprice = document.getElementById("greensprice");
	var greenspriceResult = document.getElementById("greenspriceResult");
	if (greensprice.value.length == 0) {
		greenspriceResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>菜价格不能为空</font>";
	} else {
		greenspriceResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkGreensnumber() {
	var greensnumber = document.getElementById("greensnumber");
	var greensnumberResult = document.getElementById("greensnumberResult");
	if (greensnumber.value.length == 0) {
		greensnumberResult.innerHTML = "<img src = 'image/no.gif'/><font color='red' size='1px'>菜库存不能为空</font>";
	} else {
		greensnumberResult.innerHTML = "<img src = 'image/yes.gif'/>";
	}
}

function checkData() {
	var greensname = document.getElementById("greensname");
	var greensprice = document.getElementById("greensprice");
	var file = document.getElementById("file").value;
	var greensnumber = document.getElementById("greensnumber");
	
	if(greensname.value.length == 0){
		alert("菜名不能为空！");
		return false;
	}else if(greensprice.value.length == 0){
		alert("菜价格不能为空！");
		return false;
	}else if(greensnumber.value.length == 0){
		alert("菜库存不能为空！");
		return false;
	}else if(file.length == 0){
		alert("菜照片不能为空！");
		return false;
	}else{
		return true;
	}
}