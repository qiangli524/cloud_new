<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/formvalidate/validate.css"  type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	
	 var api = frameElement.api;
	 var w = api.opener;
	
	 api.button({
	     id:'Ok',
	     name: '导入',
	     callback:importovf,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	//导入ovf模板
	 function importovf(){
		var name = $("#name").val();
		var parent_id = '<s:property value="parent_id" />';
	 	var vtype = '<s:property value="vtype" />';
	 	var connect_id = '<s:property value="connect_id" />';
	 	if (name=='') {
	 		alert('虚拟机名称不能为空！');
	 		return false;
	 	}
	 	var params = $("#virtualMachineUnitedVO").serialize();
	 	
		if(validateFile()&&validateName(name,vtype)){
			w.saveOvf(parent_id,params);
		}else{
			return false;
		}
		
	 }
	//校验上传文件的类型
	function validateFile() {
		var filepath=$("#file").val(); 
		//alert(filepath);
        var extStart=filepath.lastIndexOf("."); 
        var ext=filepath.substring(extStart,filepath.length).toUpperCase(); 
        if(ext!=".OVA"&&ext!=".OVF"){ 
        	alert("只允许上传.ovf/.ova格式文件"); 
        	return false; 
		}
		return true;
	}
	 
	//校验虚拟机名字是否存在
	function validateName(name,vtype){
		var param = "united_validateName.do?name="+encodeURI(encodeURI(name))+"&vtype="+vtype;
		var flag = false;
		$.ajax({
			type : "POST",
			url : param,
			dataType : "json",
			cache:false,
			async: false,
			success : function(data){
				var count = data.count;
				if(count==0){
					flag = true;
					$("#span_vmName").empty();
				}else{
					if(vtype==1){
						$("#span_vmName").html('虚拟机名称已经存在，请更改');
					}
					else if(vtype==2){
						$("#span_vmName").html('虚拟机名称已经存在，请更改');
					}
				}
			}
		});	
		return flag;
	}
	
	function bar(idstr,contents){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	
	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}

	</script>
	
<script type="text/javascript">
 //FX获取文件路径方法
 function readFileFirefox(fileBrowser) {
     try {
         netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
     } 
     catch (e) {
        // alert('无法访问本地文件，由于浏览器安全设置。为了克服这一点，请按照下列步骤操作：(1)在地址栏输入"about:config";(2) 右键点击并选择 New->Boolean; (3) 输入"signed.applets.codebase_principal_support" （不含引号）作为一个新的首选项的名称;(4) 点击OK并试着重新加载文件');
         return;
     }
     var fileName=fileBrowser.value; //这一步就能得到客户端完整路径。下面的是否判断的太复杂，还有下面得到ie的也很复杂。
     var file = Components.classes["@mozilla.org/file/local;1"]
         .createInstance(Components.interfaces.nsILocalFile);
     try {
         // Back slashes for windows
         file.initWithPath( fileName.replace(/\//g, "\\\\") );
     }
     catch(e) {
         if (e.result!=Components.results.NS_ERROR_FILE_UNRECOGNIZED_PATH) throw e;
        // alert("File '" + fileName + "' cannot be loaded: relative paths are not allowed. Please provide an absolute path to this file.");
         return;
     }
     if ( file.exists() == false ) {
       //  alert("File '" + fileName + "' not found.");
         return;
     }
 

  return file.path;
 }
 

//根据不同浏览器获取路径
 function getvl(obj){
 //判断浏览器
   var Sys = {}; 
   var ua = navigator.userAgent.toLowerCase(); 
   var s; 
   (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
   (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
   (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
   (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
   (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
   var file_url="";
   if(Sys.ie<="6.0"){
     //ie5.5,ie6.0
     file_url = obj.value;
   }else if(Sys.ie>="7.0"){
     //ie7,ie8
    
     obj.select();
    //ie9
    // obj.blur();
    //如果用了iframe框架，就将obj.blur()替换成下面这句话
     window.parent.document.body.focus();
     file_url = document.selection.createRange().text;
     //alert("file_url:"+file_url);
     //alert(file_url);
   }else if(Sys.firefox){
     //fx
     //file_url = document.getElementById("file").files[0].getAsDataURL();//获取的路径为FF识别的加密字符串
     file_url = readFileFirefox(obj);
   }else if(Sys.chrome){
     file_url = obj.value;
   }
   $("#file").val(file_url);
   var name = getFileName(file_url)
   $("#name").val(name);
   $("#path").val(file_url.substring(0,file_url.lastIndexOf(name, 0)));
   //document.getElementById("text").innerHTML="获取文件域完整路径为："+file_url;
 }
 function getFileName(file_url){
 	var filenameAndExt=file_url.replace(/.*(\/|\\)/,"");
 	 $("#ovffile").val(filenameAndExt);
 	var filename = filenameAndExt.substring(0,filenameAndExt.lastIndexOf("."));
 	//alert(filename);
 	return filename;
 }
 
 </script>
</head>
<body class="mainbody">
	<s:form action="" method="post" id="virtualMachineUnitedVO">
	<s:hidden name="unitedTreeObj.id" ></s:hidden>
	<s:hidden name="virtualMachineUnitedVO.ip" ></s:hidden>
	<s:hidden name="virtualMachineUnitedVO.connectCode" ></s:hidden>
	<s:hidden name="virtualMachineUnitedVO.hostCode" ></s:hidden>
	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				
				<tr>
					<td class="til" >
						虚拟机名称
						<font color="red">*</font>
					</td>
					<td align="left">
						<s:textfield name="virtualMachineUnitedVO.vmName" id="name"  readonly="true" cssClass="inpt-2"></s:textfield>
						<span style="color:RED" id="span_vmName"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						OVF模板位置
						<font color="red">*</font>
					</td>
					<td align="left">
						<!-- like "RabbitMQ.ovf" -->
						<input type="hidden" name="virtualMachineUnitedVO.newVmName" id="ovffile"/>
						<!-- like "C:\\Users\\Administrator\\Desktop" -->
						<input type="hidden" name="virtualMachineUnitedVO.isoPath" id="path"/>
						<input type="file" id="file" onchange="getvl(this)"/>
						<span style="color:RED" id="span_file"/>
					</td>
				</tr>
			</table>
		</div>
</s:form>
</body>
</html>

