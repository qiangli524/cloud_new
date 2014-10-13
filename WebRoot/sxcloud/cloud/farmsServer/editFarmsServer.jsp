<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%> 
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript">
<%--	空值校验--%>
	function isNull( str ){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
}
<%--	ip校验--%>
	function isIP(strIP) {  
		if (isNull(strIP)) return false; 

		var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g 
		
		if(re.test(strIP)) 

		{ 
			if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true; 

		} 

		return false; 

	}
<%--	正整数校验--%>
	function isNumber( s ){
		var regu = "^[0-9]+$";
		var re = new RegExp(regu);
		if (s.search(re) != -1) {
			return true;
		} else {
			return false;
		}
	}
<%--	port校验--%>
	function isPort( str ){
		return (isNumber(str) && str<65536);
	}
<%--	数字校验--%>
	function isInteger( str ){
		var regu = /^[-]{0,1}[0-9]{1,}$/;
		return regu.test(str);
	}
	function resetForm(theForm){ 
		theForm.adminStatus.value = '1'; 
		theForm.bandwidthLimit.value = '';
		theForm.weight.value = '1';
		theForm.operationMode.value = '1';
		theForm.type.value = '1';
		theForm.connectionLimit.value = '0';
		theForm.redirectTo.value = '';
		theForm.clientNAT.value = 'enable';
		theForm.serverDescription.value = '';
		theForm.responseThreshold.value = '0';
		theForm.backupServerAddress.value = '0.0.0.0';
		theForm.ADServerBackupPreemption.value = '1';
		theForm.clientNATAddressRange.value = '0.0.0.0';
		theForm.farmNameForLocalFarm.value = '';
	}
	function submitRequest(thisForm){ 
			 if(thisForm.serverName.value.length ==0){
	     		alert("FarmServer 名称不能为空！");
	    		thisForm.serverName.focus;
	   	 		return false  ;
	  	 	 }
			 if(thisForm.farmName.value.length ==0){
	     		alert("Farm名称不能为空！");
	    		thisForm.farmName.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(thisForm.serverAddress.value ==0){
	     		alert("Server Address不能为空！");
	    		thisForm.serverAddress.focus;
	   	 		return false  ;
	  	 	 }  
	  	 	 if(!isInteger(thisForm.weight.value)){
	  	 	 	alert("weight必须数字！");
	  	 	 	thisForm.weight.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.responseThreshold.value)){
	  	 	 	alert("responseThreshold必须数字！");
	  	 	 	thisForm.responseThreshold.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.bandwidthLimit.value) && thisForm.bandwidthLimit.value.length != 0){
	  	 	 	alert("bandwidthLimit必须数字！");
	  	 	 	thisForm.bandwidthLimit.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isInteger(thisForm.connectionLimit.value)){
	  	 	 	alert("connectionLimit必须数字！");
	  	 	 	thisForm.connectionLimit.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.serverAddress.value)){
	  	 	 	alert("Server Address格式必须为ip格式！");
	  	 	 	thisForm.serverAddress.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.backupServerAddress.value)){
	  	 	 	alert("backupServerAddress格式必须为ip格式！");
	  	 	 	thisForm.serverAddress.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.clientNATAddressRange.value)){
	  	 	 	alert("clientNATAddressRange格式必须为ip格式！");
	  	 	 	thisForm.clientNATAddressRange.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isPort(thisForm.serverPort.value)){
	  	 	 	alert("serverPort格式必须为port格式！");
	  	 	 	thisForm.serverPort.focus;
	   	 		return false  ;
	  	 	 }
			theForm.submit();  
		}
 	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="farmserver_sureModfarmServer.do" method="post" id="theForm">
 <s:hidden name="theForm.id" />
<div class="scrollbody">
	<div>
		<div class="tit-zzi">
			<div id="zi">FarmServer基本信息</div>
			<div id="zi"></div>	
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						FarmServer 名称 <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield  name="theForm.serverName" id="serverName"/> 
					</td>
					<td class="til">
						Farm 名称  <font style="color:#FF3401">*</font>
					</td>
					<td>   
					 	<s:textfield name="theForm.farmName" styleClass="input disable" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="til">
						Server Address:    <font style="color:#FF3401">*</font>
					</td>
					<td>
					 	<s:textfield name="theForm.serverAddress"  styleClass="input disable" readonly="true"/>
					</td>
					<td class="til">
						Server Port: <font style="color:#FF3401">*</font>
					</td>
					<td colspan="3">
					 	<s:textfield name="theForm.serverPort" styleClass="input disable" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						Server Description: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.serverDescription" styleClass="input disable" readonly="true" /> 
					</td>
					<td class="til">
						Admin Status:  <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.adminStatus" list="#{'1':'Enable','2':'Disable','3':'Shutdown'}" id="adminStatus">
						</s:select>          
					</td>
				</tr>
			</table>
		</div>
	</div>		
		
		<div class="tit-zzi">
			<div id="zi">Forwarding Parameters &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Type: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select list="#{'1':'Regular','2':'Distributed AppDirector','3':'Remote Server',
							'4':'Local Triangulation','5':'Local Farm','6':'Local AppDirector'}" name="theForm.type" id="type"></s:select>
					</td>
					<td class="til">
						Farm Name for Local Farm :   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.farmNameForLocalFarm" list="theForm.farmList" listKey="farmName" listValue="farmName" id="farmNameForLocalFarm">
							</s:select>          
					</td>
				</tr>	
				<tr>
					<td class="til">
						Redirect To:  <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.redirectTo"  id="redirectTo"/>             
					</td>
					<td class="til">
						
					</td>
					<td>
						             
					</td>
					
				</tr>	
			</table>
		</div>
		
		<div class="tit-zzi">
			<div id="zi">Load Balancing Parameters  &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Weight: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.weight" id="weight"/> 
					</td>
					<td class="til">
						Response Threshold [ms]:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.responseThreshold"  id="responseThreshold"/>           
					</td>
				</tr>	
				<tr>
					<td class="til">
						Bandwidth Limit: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theform.bandwidthLimit" id="bandwidthLimit"/> 
					</td>
					<td class="til">
						Connection Limit:    <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.connectionLimit" id="connectionLimit"/>      
					</td>
					
				</tr>	
			</table>
		</div>
		
		<div class="tit-zzi">
			<div id="zi">Redundancy Parameters &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Operation Mode:  <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.operationMode" list="#{'1':'Regular','2':'Backup'}" id="operationMode">
							</s:select> 
					</td>
					<td class="til">
						 Backup Preemption:    <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.ADServerBackupPreemption" list="#{'1':'Enabled','2':'Disabled'}" id="ADServerBackupPreemption">
						</s:select>         
					</td>
				</tr>	
				<tr>
					<td class="til">
						Backup Server Address:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.backupServerAddress" id="backupServerAddress"/>
					</td>
					<td class="til">
						
					</td>
					<td>
						             
					</td>
					
				</tr>	
			</table>
		</div>
		
		<div class="tit-zzi">
			<div id="zi">Client NAT  &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Client NAT:  <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.clientNAT" list="#{'1':'Enabled','2':'Disabled'}" id="clientNAT">
						</s:select>
					</td>
					<td class="til">
						Client NAT Address Range:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.clientNATAddressRange" id="clientNATAddressRange"/>         
					</td>
				</tr>	 	
			</table>
		</div>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:resetForm(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back()" />
				</td>
			</tr>
		</table>
</div>
</s:form>
</body>
</html:html>
