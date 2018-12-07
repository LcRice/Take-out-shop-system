/**
 * 
 */
// 全选
function selectAll() {
	var all = document.getElementById("all");
	var greensids = document.getElementsByName("greensids");

	for (var i = 0; i < greensids.length; i++) {
		greensids[i].checked = all.checked; // checked 选中为true 没选中为false;
	}
}

// 反选
function selectReverse() {
	var greensids = document.getElementsByName("greensids");

	for (var i = 0; i < greensids.length; i++) {
		greensids[i].checked = !greensids[i].checked;
	}

	checkSelect();
}

// 全选框判断
function checkSelect() {
	var all = document.getElementById("all");
	var greensids = document.getElementsByName("greensids");

	var flag = true;
	for (var i = 0; i < greensids.length; i++) {
		if (!greensids[i].checked) {
			flag = false;
			break;
		}
	}

	all.checked = flag;
}

// 至少选一个用户
function checkCount() {
	var greensids = document.getElementsByName("greensids");

	var flag = false;
	for (var i = 0; i < greensids.length; i++) {
		if (greensids[i].checked) {
			flag = true;
			break;
		}
	}

	if (!flag) {
		alert("至少选择一个菜");
		return false;
	}

	if (flag) {
		return confirm("是否确认？");
	}

}

var xhr = new XMLHttpRequest();
var flag = false;
function checkGreensnumber(greensid) {

	xhr.open("get", "CheckGreensnumberServlet?greensid="
			+ encodeURI(greensid), true);
	
	xhr.send(null);

	xhr.onreadystatechange = checkGreensnumberResult;

	return flag;
}

function checkGreensnumberResult() {
	var Result = document.getElementById("Result");
	if (xhr.readyState == 4) {

		if (xhr.status == 200) {
			if (xhr.responseText == "success") {
				flag = confirm("是否加入购物车");
			} else {
				alert("库存不足！请选择其他菜");
			}
		} else {
			alert("调用失败");
		}
	}
}