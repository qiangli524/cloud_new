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
		theForm.l4PolicyName.value = ''; 
		theForm.virtualIP.value = '';
		theForm.l4Port.value = '';
		theForm.l4Protocol.value = '';
		theForm.segmentName.value = '';
		theForm.sourceIPFrom.value = '';
		theForm.farmName.value = '';
		theForm.httpPolicy.value = '';
		theForm.SSLPolicy.value = '';
		theForm.cachingPolicy.value = '';
		theForm.l7PolicyName.value = '';
		theForm.clientAuthenticationPolicy.value = '';
		theForm.DCtable.value = '';
		theForm.redundancyStatus.value = '';
	}
	function submitRequest(thisForm){ 
			 if(thisForm.l4PolicyName.value.length ==0){
	     		alert("l4Policy名称不能为空！");
	    		thisForm.l4PolicyName.focus;
	   	 		return false  ;
	  	 	 }
			 if(thisForm.virtualIP.value.length ==0){
	     		alert("virtualIP不能为空！");
	    		thisForm.virtualIP.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.virtualIP.value)){
	  	 	 	alert("virtualIP格式必须为ip格式！");
	  	 	 	thisForm.virtualIP.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.sourceIPTo.value)){
	  	 	 	alert("sourceIPTo格式必须为ip格式！");
	  	 	 	thisForm.sourceIPTo.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isIP(thisForm.sourceIPFrom.value)){
	  	 	 	alert("sourceIPFrom格式必须为ip格式！");
	  	 	 	thisForm.sourceIPFrom.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(!isPort(thisForm.l4Port.value)){
	  	 	 	alert("l4Port格式必须为port格式！");
	  	 	 	thisForm.l4Port.focus;
	   	 		return false  ;
	  	 	 }
			theForm.submit();  
		}
 	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="L4Policy_sureModl4Policy.do" method="post" id="theForm">
 <s:hidden name="theForm.id" id="id"/>
<div class="scrollbody">
	<div>
		<div class="tit-zzi">
			<div id="zi">Policy Classifiers </div>
			<div id="zi"></div>	
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						L4 Policy 名称 <font style="color:#FF3401">*</font>
					</td>
					<td> 
						<s:textfield name="theForm.l4PolicyName" id="l4PolicyName" styleClass="input disable" readonly="true"/>
 						
					</td>
					<td class="til">
						Virtual IP:  <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.virtualIP" id="virtualIP" styleClass="input disable" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						L4 Port:     <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.l4Port" id="l4Port" styleClass="input disable" readonly="true"/>
					</td>
					<td class="til">
						Application: <font style="color:#FF3401"></font>
					</td>
					<td colspan="3">
						<s:select name="theForm.application"  list="#{'1':'Any','2':'FTP Control','3':'HTTP',
						'4':'HTTPS','5':'PING','6':'REXEC','7':'RSH','8':'RTSP','9':'SIP','10':'SIPS','11':'TS COOKIE',
						'12':'RADIUS','13':'TCP','14':'TFTP','15':'UDP','16':'Virtual IP Interface','17':'SCTP',
						'18':'MH-SCTP','19':'Generic-SSL'}" id="application">
					 	</s:select> 
					</td>
				</tr>
				<tr>
					<td class="til">
						L4 Protocol: <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.l4Protocol" id="l4Protocol" styleClass="input disable" readonly="true"/>
					</td>
					<td class="til">
						Segment Name:  <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.segmentName" list="#{}" id="segmentName">
					 	</s:select>     
					</td>
				</tr>
				<tr>
					<td class="til">
						Source IP From: <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.sourceIPFrom" id="sourceIPFrom" styleClass="input disable" readonly="true"/>
					</td>
					<td class="til">
						Source IP To:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.sourceIPTo"  id="sourceIPTo"/>             
					</td>
				</tr>
			</table>
		</div>
	</div>		
		
		<div class="tit-zzi">
			<div id="zi">Policy Actions &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Farm Name:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.farmName" list="theForm.farmList" listKey="farmName" listValue="farmName" id="farmName">
					 	</s:select>
					</td>
					<td class="til">
						HTTP Policy:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.httpPolicy" list="#{'Default':'Default'}" id="httpPolicy">
					 	</s:select>           
					</td>
				</tr>	
				<tr>
					<td class="til">
						SSL Policy:   <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.SSLPolicy" list="#{'None':'None'}" id="SSLPolicy">
					 	</s:select>          
					</td>
					<td class="til">
						Caching Policy: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.cachingPolicy" list="#{'None':'None'}" id="cachingPolicy">
					 	</s:select>          
					</td>
				</tr>	
				<tr>
					<td class="til">
						L7 Policy Name:     <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.l7PolicyName" list="#{'None':'None'}" id="l7PolicyName">
					 	</s:select>         
					</td>
					<td class="til">
						Client Authentication Policy: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.clientAuthenticationPolicy" list="#{'None':'None'}" id="clientAuthenticationPolicy">
					 	</s:select>          
					</td>
				</tr>	
				<tr>
					<td class="til">
						Compression Policy:    <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select name="theForm.compressionPolicy" list="#{'None':'None'}" id="compressionPolicy">
					 	</s:select>           
					</td>
					<td class="til"> 
					</td>
					<td>           
					</td>
				</tr>
			</table>
		</div>
		
		<div class="tit-zzi">
			<div id="zi">Policy Settings   &nbsp;&nbsp;&nbsp;</div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="pop-table nosize" border="0" cellspacing="0" id="DCtable">
				<tr>
					<td class="til">
						Redundancy Status: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:select  name="theForm.redundancyStatus" list="#{'1':'Primary','2':'Backup'}" id="redundancyStatus">
					 	</s:select> 
					</td>
					<td class="til">
						  Policy DefinedBy: <font style="color:#FF3401"></font>
					</td>
					<td>
						<s:textfield name="theForm.policyDefinedBy" id="policyDefinedBy" styleClass="input disable" readonly="true"/>
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
