function isIp(ip) {
	var s;
	var flag = true;
	var re = /^[\d]+\.[\d]+\.[\d]+\.[\d]+$/;
	if (re.test(ip)) {
	  s = ip.split(".");
	  for(var i=0;i<s.length;i++){
		  if(s[i]>255){
			  return false;
		  }
	  }
	}else{
		return false;
	}
	return true;
}
