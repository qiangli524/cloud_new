function alertMsg(msg){
	$.dialog(
	{
		title:'友情提示',
		height:100,
	   	width:300,
	   	max:false,
	   	min:false,
	   	lock:true,
		content: msg
	});
}

/**
 * @description 控制台右上角信息提示框
 * @param msg 需要显示的信息
 * @param method error,info,success
 * @param timeout 5000 = 5 seconds
 * @author wanlei_bj@si-tech.com.cn
 */
function mask(msg,method,timeout){
		if(msg==''||msg==null){
	    	msg="数据加载中..."
		}
		var divclass = '';
		switch(method){
			case 'error':{
				divclass = "overall-prompt bg-red";
				break;
			}
			case 'success':{
				divclass = "overall-prompt bg-green";
				break;
			}
			default:{
				divclass = "overall-prompt bg-blue";
				break;
			}
		}
		
	    var alertHmtl=$("<div class='"+divclass+"' style='display:none'><a href='javascript:;' class='js_p_close'></a><span id='msg'>"+trim(msg)+"</span></div>");
	    $("body").append(alertHmtl);
	    $(".overall-prompt").fadeIn("slow");
	    if(timeout != null && timeout != ""){
	    	setTimeout("removeMask()",timeout);
	    }
	}
	//移除mask
	function removeMask() {
		$(".overall-prompt").fadeOut("slow");
		//$(".overall-prompt").remove();
	}
	
	// 去掉两段空格
	function trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}

	// 检测输入的字符串是否符合要求
	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}
	
	
	
	 /**
	 * @description 用于验证虚拟机下是否存在快照 存在返回true 不存在返回false
	 * @param vm_uuid 虚拟机Id
	 * @param connect_id 链接VMwareID
	 * @returns "1" 存在，"0" 不存在，"error" 错误。
	 * @author wanlei_bj@si-tech.com.cn
	 */
	function checkExistSnapshot(vm_uuid,connect_id){
		  var ret = "0";
		  var params = {
				'obj.vm_uuid':vm_uuid,
				'obj.connect_id':connect_id
		  };
		  $.ajax({
			 url:'snapshot_getSnapshotCountByVm.do',
			 async:false,
			 type:"POST",
			 data:params,
			 dataType:"json",
			 success:function(data){
				 if(data.snapshot_count > 0){
					 ret = "1";
				 }else{
					 ret = "0";
				 }
			 },
			 error:function(){
				 ret = "error";
			 }
		  });
		  return ret;
	  }