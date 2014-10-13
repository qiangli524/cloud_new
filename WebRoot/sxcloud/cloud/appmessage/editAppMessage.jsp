<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	var vhidss = '<%=request.getAttribute("vhId")%>';
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	var xmlHttp; 
	var check;
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	window.onload=getSelect;
	function getSelect() {
    	createXmlHttp();
    	var APP_TYPE=document.getElementById("APP_TYPE").value;
    	xmlHttp.open("GET", "ajax_getAppIp.do?APP_TYPE="+APP_TYPE, false);     
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
    	if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.getElementById("VH_ID");
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(vhidss == value){
    		opt.selected=true;
  		}
  		return opt;
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
		var vhid = theForm.VH_ID.value;
		if(thisForm.APP_NAME.value.length==0){
			alert("请输入应用名称！");
			return false;
		}
		if(thisForm.SYS_ID.value=='' || thisForm.SYS_ID.value==null){
			alert("请选择所属系统名称！");
			return false;
		}
		if(vhid==null || vhid==""){
			alert("请选择IP地址");
			return false;
		}   
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="appmess_saveAppMessage.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.APP_ID" id="APP_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						应用名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.APP_NAME" cssClass="txt" id="APP_NAME"></s:textfield>                 
					</td>
					<td class="til">
						所属系统名称<font color="red">*</font>
					</td>
					<td>
							<s:select list="theForm.sysList" name="theForm.SYS_ID" id="SYS_ID" listKey="SYS_ID" listValue="SYS_NAME" headerKey="" headerValue="-请选择-"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						应用类型<font color="red"></font>
					</td>
					<td>
						  <s:select list="#{'':'-请选择-','0':'物理主机','1':'虚拟主机'}" name="theForm.APP_TYPE" id="APP_TYPE" onclick="getSelect()"></s:select>     
					</td>
					 <td class="til">
						IP地址<font color="red">*</font>
					</td>
					<td>
						 <s:select list="#{'':'请选择'}" name="theForm.VH_ID" id="VH_ID"></s:select>
					  (先选择应用类型)
					</td>
				</tr>
				<tr>
					<td class="til">
						使用协议
					</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'ssh','1':'telnet'}" name="theForm.PROTOCOL" id="PROTOCOL"></s:select>
					</td>
					<td class="til">
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="til">
						进程名1<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.PROCESS" id="PROCESS" cols="127" rows="2"></s:textarea>
						<p>
					<font color="red">	注：通过ps -ef 命令查看到的进程名，确认进程唯一性</font>
						</p>
					</td>  
				</tr>
				<tr>             
					<td class="til">
						进程名2<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.PROCESS_AUX" id="PROCESS_AUX" cols="127" rows="2"></s:textarea>
						<p>
					<font color="red">	注：通过ps aux 查看到的进程名，确认进程唯一性</font>
						</p>
					</td> 
				</tr>
				<tr>
					<td class="til">
						描述<font color="red"></font>
					</td>
					 <td colspan="3">
						<s:textarea name="theForm.APP_DESC" id="APP_DESC" cols="127" rows="4"></s:textarea>
					</td>          
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
</s:form>
</body>
