function isNotNULL(str){
    if(null!=str&&''!=str){
       return true;
    }return false;
}


function getTime_new(){
          var dateTime=new Date();
          
          var yy=dateTime.getFullYear();
		  var MM=dateTime.getMonth()+1;  
		  if(MM<10){
		    MM='0'+MM
		  }
		  var dd=dateTime.getDate();
		  if(dd<10){
		    dd='0'+dd;
		  }
          var hh=dateTime.getHours();
          if(hh<10){
            hh='0'+hh;
          }
          var mm=dateTime.getMinutes();
          if(mm<10){
            mm='0'+mm;
          }
		  var ss=dateTime.getSeconds();
		  if(ss<10) {
		    ss='0'+ss;
		  }
		  return yy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss;
}

    function cutString(text, canvasWidth) {
	 	var suffix = "...";
	    var tempText = text.substring(0, canvasWidth);
	    if(canvasWidth >= text.length) return text;
	    return tempText+suffix
	}
    
    function isMASIDRegister(masid,button_name,div_id,function_name,toload_id){
    	$.ajax({
    	type: "POST",
		cache: "false",
		async: true,
		dataType: "text",
		url: "./getENABLE_new.do?MASID="+masid,
		success: function(msg) {
			if('0'==msg||''==msg){
				//$("#toload").hide();
				$("#"+toload_id).hide();
				$("#process-1").attr("class","process-1-error pl15 mr10 color690 line30 f12 fr");
	            $("#process-1").text('MASID未注册');
	            $("#process-1").show();
	            $("[name='"+button_name+"']").each(function (){
	            	$(this).attr("disabled","disabled");
	            });
	            $("#"+div_id).html('');
			}else{
				 eval(function_name+"()");
            }
		}
	});
}
    
    function deviceGrade(opt_id){
    	$.ajax({
    	type: "POST",
		cache: "false",
		async: true,
		dataType: "json",
		url: "/getDeviceGrade_New.do?",
		success: function(data) {
				$("#"+opt_id).html('');
			    var table="<option value=''>----</option>";
				$.each(data, function(i) {
					  var EQUIPLEVEL = data[i].EQUIPLEVEL;
					  var EQUIPMENT_ID = data[i].EQUIPMENT_ID;
	 				  table=table+"<option value='"+EQUIPMENT_ID+"'>"+EQUIPLEVEL+"</option>";
			    });
			     $("#"+opt_id).append(table);
		}
	});
}
    
function errorMsg(XMLHttpRequest, textStatus, errorThrown){
    if(null!=XMLHttpRequest.responseText&&''!=XMLHttpRequest.responseText){
    	UID_dailog_2('系统错误', '系统错误,请重新登录', 'error');
	}else{
		location='/';
	}
   }
//全选
function checkAll(e, itemName)
{
  var obj = document.getElementsByName(itemName);
  for (var i=0; i<obj.length; i++){
   obj[i].checked = e.checked;
  }
}
function addMemberBar_all(self){
	if($(self).is(":checked")){
	  $("#addMember li.memberbox-1").addClass("memberbox-1_on");
	  $("#groupMember b").text( $("#addMember li.memberbox-1").length);
	}
	else{
	  $("#addMember li.memberbox-1").removeClass("memberbox-1_on");
	  $("#groupMember b").text( 0 );
	}
} 
function editGroup(group_name_temp,group_desc){
    group_name_temp=encodeURI(group_name_temp);
    group_name_temp=encodeURI(group_name_temp);
    group_desc=encodeURI(group_desc);
    group_desc=encodeURI(group_desc);
    $.ajax({
    	type: "POST",
		cache: "false",
		async: true,
		dataType: "text",
		url: "./editGroup.do?group_name_temp="+group_name_temp+"&group_desc="+group_desc,
		success: function(msg) { 
		}
	});
}

function Udialog1(url,title,ok){
    var okHandler = ok || true,
    kDialog = $.dialog({
        lock: true,
        content: 'url:'+url,
        title: title,
        width: 800,
        height: 500,
		okVal: '\u786E\u5B9A',
		ok: okHandler
    });
}
function Udialog2(url,title,ok,cancel){
    var okHandler = ok || true,
    cancelHandler = cancel || true,
    kDialog = $.dialog({
        lock: true,
        content: 'url:'+url,
        title: title,
        width: 800,
        height: 500,
		okVal: '\u786E\u5B9A',
		ok: okHandler,
		cancelVal: '\u53D6\u6D88',
		cancel: cancelHandler
    });
}
function Udialog3(url,title,width,height,ok,cancel){
    var okHandler = ok || true,
    cancelHandler = cancel || true,
    kDialog = $.dialog({
        lock: true,
        title: title,
        width: width,
        height: height,
		okVal: '\u786E\u5B9A',
		ok: okHandler,
		cancelVal: '\u53D6\u6D88',
		cancel: cancelHandler
    });
    $.ajax({
    	url: url,
		datatype: "text/html",
		type: "post",
	    success:function(data){
           
	        kDialog.content(data);
	    },
	    cache:false
	});
}

function Udialog_common(url,title,width,height,ok,cancel){
    kDialog = $.dialog({
        lock: true,
        content: 'url:'+url,
        title: title,
        width: width,
        height: height,
		okVal: '\u786E\u5B9A',
		ok: function (){
		   var _window=this.content;
		   var _document=$(this.content.document);
		   var dlg=this;
		    return ok(dlg,_window,_document);
		},
		cancelVal: '\u53D6\u6D88',
		cancel:function (){
		   var _window=this.content;
		   var _document=$(this.content.document);
		   var dlg=this;
		   return cancel(dlg,_window,_document);
		}
    });
}

function cutString(text, canvasWidth) 
{
	var suffix = "...";
   var tempText = text.substring(0, canvasWidth);
   if(canvasWidth >= text.length) return text;
   return tempText+suffix
}