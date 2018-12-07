/**
 * 
 */
//全选
function selectAll() {
	var all = document.getElementById("all");
	var greensids = document.getElementsByName("userorderids");
	
	for(var i = 0;i<greensids.length;i++){
		greensids[i].checked = all.checked; //checked 选中为true  没选中为false;
	}
}

//反选
function selectReverse(){
	var greensids = document.getElementsByName("userorderids");
	
	for(var i = 0;i<greensids.length;i++){
		greensids[i].checked = !greensids[i].checked;
	}
	
	checkSelect();
}

//全选框判断
function checkSelect() {
	var all = document.getElementById("all");
	var greensids = document.getElementsByName("userorderids");
	
	var flag = true;
	for(var i = 0;i<greensids.length;i++){
		if(!greensids[i].checked){
			flag = false;
			break;
		}
	}
	
	all.checked = flag;
}

//至少选一个用户
function checkCount(){
	var greensids = document.getElementsByName("userorderids");
	
	var flag = false;
	for(var i = 0;i<greensids.length;i++){
		if(greensids[i].checked){
			flag = true;
			break;
		}
	}
	
	if(!flag){
		alert("至少选择一个订单");
		return false;
	}
	
	if(flag){
		return confirm("是否确认？");
	}
	
}