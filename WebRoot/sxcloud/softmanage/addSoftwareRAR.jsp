<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/portal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui.datepicker.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_wx.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_check.js"></script>
 
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.min.js"></script>
	<!-- <link href="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify.css" rel="stylesheet" type="text/css"/> -->
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '确定',
	     callback:submitRequest,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	$(function() {
		$("#ATTACHMENT").uploadify({
			height:20,
			width:100,
            buttonImage   :'<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadbutton.png', 
            fileTypeExts  :'*.*',
			swf           : '<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify.swf',
			uploader      : 'software_saveSoftwareRAR.do',
			fileObjName: 'tbBusiSoftwareInfoObj.ATTACHMENT',
            displayData    : 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
            auto          : false, 
            cancel : 'uploadify-cancel.png',
            fileTypeExts : '*.zip;*.tar;*.gz',
            fileSizeLimit :'5024000k',
            multi : false,
            queueSizeLimit : 1,
            formData:{'appid':''},
            onUploadStart : function(file) {
	        	//校验   
            	var appid = $("#APPID").val();
    	    	if(appid==0){
    	    		alert("所属应用不能为空！");
    			    return false  ;
    	    	} 
    	    	var softname = $("#NAME").val();
    	    	if (softname == null || softname == "") {
					alert("软件名称不能为空");
					return false;
				}
	        	$("#ATTACHMENT").uploadify("settings", "formData", {'sysappid':appid,'softname':softname});
	        },
            onQueueFull : function() {
            	alert("一次只能上传单个文件");
            },
            onUploadSuccess : function(file) {
            	var bizid = $("#bizid").val();
            	if (bizid == null || bizid == "") {
            		//直接上传的结果
            		$("#theForm").attr("action","software_saveSoftwareRAR.do?b=" +true + "&name="+encodeURI(encodeURI(file.name)))
            		$("#theForm").submit();
            		w.listForm();
	            	api.close();
				} else {
					//在tab页上传的结果
			//		var api = frameElement.api;
			//		var w = api.opener;
					var form = $("#theForm").serialize()
	            	$.ajax({
	            		type:'post',
	            		url:'software_saveSoftwareRAR.do?'+ form + '&b=' + true + '&name='+encodeURI(encodeURI(file.name)) ,
	            		success:function(msg){
	            			$("#fileempty").empty();
	            			api.close();
	            		    w.listForm();
	            		}
	            	});
			//	return true;
				}
            }
		});
	})
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
	function submitRequest(){ 
		var name = $("#NAME").val();
		var manufacturers = $("#MANUFACTURERS").val();
		var system_request = $("#SYSTEM_REQUEST").val();
		var providers = $("#PROVIDERS").val();
		var introduce = $("#INTRODUCE").val();
	    if(name.length ==0){
	     alert("软件名称不能为空！");
	     return false  ;
	    }
	    if(manufacturers.length ==0){
	     alert("软件制作厂家不能为空！");
	     return false  ;
	    }
	    if(system_request.length ==0){
	     alert("软件平台要求不能为空！");
	     return false  ;
	    }
	    if(providers.length ==0){
	     alert("软件提供者不能为空！");
	     return false  ;
	    }
	    if(introduce.length ==0){
	     alert("软件功能介绍不能为空！");
	     return false  ;
	    }
	    var APPID = $("#APPID").val();
	    if(APPID==0){
	    	alert("所属应用不能为空！");
		    return false  ;
	    }
	   $('#ATTACHMENT').uploadify('upload','*');
	   return false;
	}
	
</script>
</head>
<body class="pop-body scrollbody" >
	<s:form action="software_saveSoftwareRAR.do" method="post" cssClass="theForm" id="theForm" enctype="multipart/form-data">
	<s:hidden name="bizid" id="bizid"></s:hidden>	
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						请选择上传的软件 <font color="red">*</font>
					</td>
					<td id="fileempty">
					    <s:file name="tbBusiSoftwareInfoObj.ATTACHMENT" id="ATTACHMENT"></s:file>
					</td>
					<td class="til">
						软件名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="tbBusiSoftwareInfoObj.NAME" cssClass="txt" id="NAME"></s:textfield>              
					</td>
				</tr>
				<tr>
				    <td class="til">
						软件制作厂家 <font color="red">*</font>
					</td>
					<td>
                      <s:textfield name="tbBusiSoftwareInfoObj.MANUFACTURERS" cssClass="txt" id="MANUFACTURERS"></s:textfield>
					</td>
				    <td class="til">
						软件平台要求 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="tbBusiSoftwareInfoObj.SYSTEM_REQUEST" id="SYSTEM_REQUEST" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						所属应用 <font color="red">*</font>
					</td>
					<td >
						 <s:select list="sysappList" listKey="ID" listValue="APPNAME" id="APPID" name="tbBusiSoftwareInfoObj.APPID" 
						 	headerKey="0" headerValue="-请选择-" ></s:select>
					</td>
					<td class="til">
						软件提供者<font color="red">*</font>
					</td>
					<td>
                      <s:textfield name="tbBusiSoftwareInfoObj.PROVIDERS" id="PROVIDERS" cssClass="txt"></s:textfield>
					</td>
				</tr>
				
				<tr>
					<td class="til">
						软件功能介绍<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:textarea name="tbBusiSoftwareInfoObj.INTRODUCE" id="INTRODUCE" cols="97" rows="5"></s:textarea>
					</td>
				</tr>
				<tr>
				    <td class="til">
						备注
					</td>
					<td colspan="3">
						<s:textarea name="tbBusiSoftwareInfoObj.REMARK" id="REMARK" cols="97" rows="5"></s:textarea>
					</td>		
				</tr>
<%--				<tr>--%>
<%--					<td colspan="4" class="btnCenter">--%>
<%--						<input type="button" class="thickbox btn-style02" value="确定"--%>
<%--							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />--%>
<%--						<input type="button" class="thickbox btn-style02" value="重置"--%>
<%--							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />--%>
<%--					</td>--%>
<%--				</tr>--%>
			</table>
</s:form>
</body>
