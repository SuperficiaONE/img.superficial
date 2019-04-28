		 var array=['username','password'];
	    function canSubmit(){
		    if(checkNull('password')||checkNull('username')){
			    return false;
		    }
		    return true;
	    }
	    function checkNull(name){
		    if(isNull(getInputValueByName(name))){
			    return true;
		    }
		    return false;
	    }
	    