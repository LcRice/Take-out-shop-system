/**
 * 
 */
	function checkWordCount(){
		var content = document.getElementById("content");
		var restWordCount = document.getElementById("restWordCount");
		
		if(content.value.length>20){
			content.value = content.value.substring(0, content.value.length-1);
		}
		
		restWordCount.innerText = 20 - content.value.length;
	}
	
	var stars = [0,0,0,0,0];
	
	function enter(index){
	
		 for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			if(i<=index){
				star.src ="image/star_yellow.gif";
			}else{
				star.src ="image/star_white.gif";
			}
		} 
	}
	
	function leave(index){
		
		for(var i=1; i<=5; i++){
			
			var star = document.getElementById("star" + i);
			
			if(stars[i-1]==0){
				star.src ="image/star_white.gif";
			}
		}	
	}
	
	function select(index){
		
		 for(var i=1; i<=5; i++){
				
			var star = document.getElementById("star" + i);
			
			if(i<=index){
				star.src ="image/star_yellow.gif";
				stars[i-1] = 1;  
			}else{
				star.src ="image/star_white.gif";
				stars[i-1] = 0;
			}
			
			var score = document.getElementById("score");
			
			score.value = index;
			
		} 
	}
	