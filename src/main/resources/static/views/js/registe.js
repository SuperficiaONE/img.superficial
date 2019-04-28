        var names=['userName','nickname','password','password2','email','valicode'];
        var userNameCanUse=false;
        function checkSame(){
		    var password1 = getInputValueByName('password');
		    var password2 = getInputValueByName('password2');
		    if(password1!=password2){
			    setMessageLabelByName('password2','两次密码不一致')
		    }
	    }
	  
	    function checkLength(e){
		    var name = $(e).attr('name');
		    if(name=='password'||name=='password2'||name=='userName'||name=='nickname'){
			    var value = getInputValueByName(name);
			    var labelName = $(e).attr("placeholder");
			    if(value.length<6){
			    	setMessageLabelByName(name,labelName+"太短");
				    return false;
		         }else if(value.length>22){
		        	 setMessageLabelByName(name,labelName+"太长");
		        	 return false;
		         }
		    }
		    return true;
		    
	    }
	    function checkVerfy(){
	    	var valicode = $("input[name='valicode']").val();
	    	
	    	if(getCookie("verifyCode").toLowerCase()==valicode.toLowerCase()){
	    		
	    		return true;
	    	}else{
	    		alert("验证码输入错误")
	    		$("#valicode_img").click();
	    		return false;
	    	}
	    }
	    function checkNull(e1){
		    var value1 = $(e1).val();
		    var name1 = $(e1).attr('name');
		    var labelName1 = $(e1).attr("placeholder");
		    setMessageLabelByName(name1,'')
		    if($(e1).attr('name')=='password2'){
					 checkSame(); 
				 }
		    if(isNull(value1)){
			    setMessageLabelByName(name1,labelName1+"不能为空");
			    return true;
		    }else{
			    return false;
		    }
		    }
		function canSubmit(){
			
			  for(var j=0;j<names.length;j++){
				  var e = getInputByName(names[j]);
				  if(checkNull(e)){
					 return false; 
				  }
				  if(!checkLength(e)){
					  return false;
				  }
			  }
			  if( userNameCanUse==false){
					return false;
				}
			  if(!checkVerfy()){
				  return false;
			  }
			  return true;
		}
		function guid() {
		    return 'xxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
		        return v.toString(16);
		    });
		}
		$("#valicode_img").click(function() {
			$(this).attr("src","/verify/"+guid()+".png")
		}
			
		)
		
		$("input[name='userName']").hover(function(){},function(){
			$.ajax({
				  type: "GET",
				  url: "/service/checkUserName",
				  data:{"userName":$("input[name='userName']").val()},
				 success:function(data){
					if(data['status']==200){
						userNameCanUse=true;
						setMessageLabelByName("userName","该用户名可以使用");
					}else{
						userNameCanUse=false;
						setMessageLabelByName("userName","名称已存在");
					}
				 }
				});
		})
	    $(function(){
	    	$("#valicode_img").attr("src","/verify/"+guid()+".png")
		    for(var i=0;i<names.length;i++){
			    inputBindChangeByName(names[i],function(){
				    var isNull = checkNull(this);
				     if(!isNull){
					     checkLength(this)
				     }
			     }
                );
		    }
	    })