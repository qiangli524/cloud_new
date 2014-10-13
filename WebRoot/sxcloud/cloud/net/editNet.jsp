<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
	    if(thisForm.NAME.value.length ==0){
	     alert("网络名称不能为空！");
	     thisForm.NAME.focus;
	     return false  ;
	    }
         if(thisForm.SUBNET.value.length ==0){
		     thisForm.SUBNET.focus;
		     return false  ;
	    }
//	    if(thisForm.DNS1.value.length ==0){
//	     alert("主DNS不能为空！");
//	     thisForm.DNS1.focus;
//	     return false  ;
//	    }
	    if($("#VLAN_ID").val()== null||$("#VLAN_ID").val()==""){
	    	alert("vlan不能为空");
	    	return false;
	    }
	    thisForm.submit();
	}
	
	/* 检测字符串是否为数字或者字母 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }	
 	function backup() {
 	    theForm.action = 'net_listNetInfo.do' 
		theForm.submit();
 	}
 	function ipSelect1(){
 		document.getElementById('manual1').style.display="none";
 		document.getElementById('manual2').style.display="none";
 		document.getElementById('manual3').style.display="none";
 		//document.getElementById('manual4').style.display="none";
 		//document.getElementById('manual5').style.display="none";
	}
	function ipSelect2(){
 		document.getElementById('manual1').style.display="";
 		document.getElementById('manual2').style.display="";
 		document.getElementById('manual3').style.display="";
 		//document.getElementById('manual4').style.display="";
 		//document.getElementById('manual5').style.display="";
	}   
</script>
</head>
<body class="pop-body scrollbody" onload=ipSelect2()>
	<div class="pd-20 bgcolor-1">
	<h2 class="utt-1">网络管理</h2>
	<div class="bord-1 pd-10">
	<s:form action="net_saveNetInfo" method="post" cssStyle="theForm" id="theForm">
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						网络名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.NAME" cssStyle="txt" id="NAME" cssClass="inpt-2"></s:textfield>
					</td>
					<td class="til">
						安全域 <font color="red">*</font>
					</td>
					<td>
						<s:select cssStyle="txt"  cssClass="select-1" list="theForm.domainList" listKey="name" name="theForm.DOMAIN" id="DOMAIN" listValue="name" headerKey="0" headerValue="-请选择-">
						</s:select>  
					</td>
				</tr>
				<!--  
				<tr>
				    <td class="til">
				    	<s:radio list="#{'0':'使用DHCP'}" name="theForm.USEDHCP" onclick="ipSelect1();"></s:radio>
				    	<s:radio list="#{'1':'使用IP地址池'}" name="theForm.USEDHCP" onclick="ipSelect2();"></s:radio>
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						网络类型 
					</td>
					<td>
						<s:select list="#{'':'-请选择-','2':'XEN','1':'Vmware'}"
									name="theForm.NET_TYPE" id="NET_TYPE" cssClass="select-1"></s:select>
					</td>
					<td class="til">
						是否缺省
					</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'缺省网络','1':'非缺省网络'}"
									name="theForm.ISDEFAULT" id="ISDEFAULT" cssClass="select-1"></s:select>
					</td>
				</tr>
				-->
				<tr id="manual1" style="display:">
				    <td class="til">
						子网掩码 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.SUBNET" cssStyle="txt" id="SUBNET" cssClass="inpt-2"></s:textfield>
					</td>
					 <td class="til">
						网关地址<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.GATEWAY1" cssStyle="txt" id="GATEWAY1" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				<tr id="manual2" style="display:">
					<td class="til">
						主DNS<%--<font color="red">*</font>--%>
					</td>
					<td>
						<s:textfield name="theForm.DNS1" cssStyle="txt" id="DNS1" cssClass="inpt-2"></s:textfield>
					</td>
<%--					<td class="til">--%>
<%--						备用网关地址--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<s:textfield name="theForm.GATEWAY2" cssStyle="txt" id="GATEWAY2"></s:textfield>--%>
<%--					</td>--%>
					<td class="til">
						VLAN<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.VLAN_ID" cssStyle="txt" id="VLAN_ID" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
<%--				<tr id="manual3" style="display:">--%>
<%--				    <td class="til">--%>
<%--						主DNS <font color="red">*</font>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<s:textfield name="theForm.DNS1" cssStyle="txt" id="DNS1"></s:textfield>--%>
<%--					</td>--%>
<%--					 <td class="til">--%>
<%--						辅助DNS --%>
<%--					</td>--%>
<%--					<td>--%>
<%--						<s:textfield name="theForm.DNS2" cssStyle="txt" id="DNS2"></s:textfield>--%>
<%--					</td>--%>
<%--				</tr>--%>
				<!--  
				<tr id="manual4" style="display:">
				  <td class="til">
						域名后缀
					</td>
					<td>
						<s:textfield name="theForm.DOMAINSUFFIXES" cssStyle="txt" id="DOMAINSUFFIXES" cssClass="inpt-2"></s:textfield>
					</td>
					<td class="til">
						域名
					</td>
					<td>
						<s:textfield name="theForm.DOMAIN" cssStyle="txt" id="DOMAIN" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机名前缀 
					</td>
					<td>
						<s:textfield name="theForm.HOSTNAMEPREFIX" cssStyle="txt" id="HOSTNAMEPREFIX" cssClass="inpt-2"></s:textfield>
					</td>
					 <td class="til">
						计算机名前缀
					</td>
					<td>
						<s:textfield name="theForm.COMPUTERNAMEPREFIX" cssStyle="txt" id="COMPUTERNAMEPREFIX" cssClass="inpt-2"></s:textfield>
					</td>
					</tr>
				<tr>
				  <td class="til">
						工作组
					</td>
					<td>
						<s:textfield name="theForm.WORKGROUP" cssStyle="txt" id="WORKGROUP" cssClass="inpt-2"></s:textfield>
					</td>					
				</tr>
				<tr id="manual5" style="display:">
					<td class="til">
						主要WINS地址	
					</td>
					<td>
					<s:textfield name="theForm.WINS1" cssStyle="txt" id="WINS1" cssClass="inpt-2"></s:textfield>
					</td>
				    <td class="til">
						辅助WINS地址
					</td>
					<td>
					<s:textfield name="theForm.WINS2" cssStyle="txt" id="WINS2" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				-->
				<tr id="manual4" style="display:">
					<td class="til">
						网络描述 
					</td>
					<td colspan="3">
						<s:textarea name="theForm.DESCRIPTION" cssStyle="txt" id="DESCRIPTION" cols="50" rows="2" cssClass="textarea-1"></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<span class="ubtn-green"><input type="button" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						</span>
						<span class="ubtn-orange mgl-20"><input type="button" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						</span>
						<span class="ubtn-blue mgl-20"><input type="button" value="返回"
							onclick="javascript:backup();return false;" />
						</span>
					</td>
				</tr>
			</table>
	</s:form>
		</div>
	</div>
</body>
</html:html>
