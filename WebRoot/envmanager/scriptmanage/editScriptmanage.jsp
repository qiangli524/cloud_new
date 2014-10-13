<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
	<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	function submitRequest(){ 
	var SCRIPT_IP = document.getElementById("SCRIPT_IP").value;
	var SCRIPT_USERNAME = document.getElementById("SCRIPT_USERNAME").value;
	var SCRIPT_PASSWORD = document.getElementById("SCRIPT_PASSWORD").value;
	var SCRIPT_CONTENT = document.getElementById("SCRIPT_CONTENT").value;
		if(SCRIPT_IP.length==0){
			alert("IP不能为这空");
			return false;
		}
		if(SCRIPT_USERNAME.length==0){
			alert("用户不能为这空");
			return false;
		}
		if(SCRIPT_PASSWORD.length==0){
			alert("密码不能为这空");
			return false;
		}
		if(SCRIPT_CONTENT.length==0){
			alert("脚本路径不能为这空");
			return false;
		}
//		var count=0;
//   	url = "scriptmanage_checkManager.do?SCRIPT_IP="+SCRIPT_IP+"&SCRIPT_USERNAME="+SCRIPT_USERNAME;
//		$.getJSON(url,{"time":new Date().toString()},function(data){
//			if(data.result==1){
//				alert("此服器IP存在相同的用户!");
//				count=1;
//				return false;
//			}
//		});
//		alert(count);
//    if(count==1){
//    	return false;
//    }
	theForm.submit();
	}
	var xmlHttp; 
	var check;
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
   //  window.onload=getCheck;

	function getCheck() {
		var EQ_IP = document.getElementById("EQ_IP").value;
		if(EQ_IP.length==0){
			alert("IP不能为这空");
			return false;
		}
    	url = "scriptmanage_getCheckIP.do?EQ_IP="+EQ_IP;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.result==1){
				alert("服器IP已存在!");
				theForm.type.value = 0;
			}else{
				theForm.type.value = 1;
			}
		});
    
	}
	function getHostName() {
		var HOSTUSERNAME = document.getElementById("HOSTUSERNAME").value;
		if(HOSTUSERNAME.length==0){
			alert("用户不能为这空");
			return false;
		}
    	url = "scriptmanage_getHostUserName.do?HOSTUSERNAME="+HOSTUSERNAME;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.pwd!=null){
				alert("用户已存在!");
				theForm.HOSRPWD.value = data.pwd;
				theForm.option.value=0;
			}else{
				theForm.option.value=1;
			}
		});
    
	}
	
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(<%=request.getAttribute("htId")%> == value){
    		opt.selected=true;
  		}
  		return opt;
	}
	function submitBack() {
 	    theForm.action = 'scriptmanage_queryScriptmanageList.do';
	  	theForm.submit();
 	}
 	
 	function actionJump(){
			$.dialog({ 
			title: '服务器IP列表', 
		    width: '600px', 
		    height: '450px', 
		    content: 'url:scriptmanage_jumpScriptmanageObj.do' ,
		     button: [ 
        	{ name:'确定'
		    }]
		});
 	}

</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="scriptmanage_saveScriptmanageObj.do" method="post" id="theForm" >
		<s:hidden name="theForm.s_id" id="s_id"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						服务器IP <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.SCRIPT_IP" id="SCRIPT_IP"  ></s:textfield>
						<s:if test="theForm.flag==1">
						</s:if>
						<s:else>
						<input type="button" class="thickbox btn-style02" value="选择" onclick="return actionJump();" />
						</s:else>
						
					</td>
					<td class="til">
						
					</td>
					<td >
						
					</td>
				</tr>
				<tr >
				
					<td class="til">
						用户 <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="theForm.SCRIPT_USERNAME" id="SCRIPT_USERNAME" ></s:textfield>
					</td>
					
					<td class="til">
						密码 <font color="red">*</font>
					</td>
					<td >
						 <s:textfield name="theForm.SCRIPT_PASSWORD" id="SCRIPT_PASSWORD"></s:textfield>
					</td>
				
				</tr>

				<tr>
					<td class="til">
						脚本路径<font color="red">*</font>
					</td>
					<td>
                        <s:textarea name="theForm.SCRIPT_CONTENT" id="SCRIPT_CONTENT"  cols="40" rows="8"></s:textarea>
					</td>
					<td class="til">
						描述
					</td>
					<td >
						<s:textarea name="theForm.SCRIPT_USE" id="SCRIPT_USE"  cols="40" rows="8"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">

						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="javascript:submitBack(document.getElementById('theForm'));return false;"/>
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>

