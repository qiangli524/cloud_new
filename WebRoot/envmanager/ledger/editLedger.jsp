<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function submitRequest(obj){ 
	var room = document.getElementById("MECH_ROOM").value;
	var type = document.getElementById("CAPITAL_TYPE").value;
	var mech_id = document.getElementById("MECH_ID").value;
	var capital = document.getElementById("CAPITAL_ID").value;
	if(room.length==0){
		alert("机房不能为空");
		return false;
	}
	if(type.length==0){
		alert("主机型号不能为空");
		return false;
	}
	if(mech_id.length==0){
		alert("设备ID不能为空");
		return false;
	}
	if(capital.length==0){
		alert("资产编号不能为空");
		return false;
	}
	var id = $("#LD_ID").val();
	obj.action = "ledger_saveLedgerObj.do?id="+id;
	theForm.submit();
	}
	
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="ledger_saveLedgerObj.do" method="post" id="theForm" >
		<s:hidden name="theForm.LD_ID" id="LD_ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						机房 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.MECH_ROOM"  id="MECH_ROOM"/>
					</td>
					<td class="til">
						主机型号<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.CAPITAL_TYPE" id="CAPITAL_TYPE"/>&nbsp;&nbsp;
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						设备ID<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.MECH_ID"  id="MECH_ID"/>	
					</td>
					<td class="til">
						资产编号<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.CAPITAL_ID" id="CAPITAL_ID" />
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						型号
					</td>
					<td>
                        <s:textfield name="theForm.MECH_TYPE" id="MECH_TYPE"/>
					</td>
					 <td class="til">
						配置
					</td>
					<td >
						<s:textfield name="theForm.MECH_CONF" id="MECH_CONF" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						操作系统
					</td>
					<td>
                        <s:textfield name="theForm.SYS_SYSTEM" id="SYS_SYSTEM" />
					</td>
					 <td class="til">
						主机名
					</td>
					<td >
						<s:textfield name="theForm.SYS_HOSTNAME" id="SYS_HOSTNAME" />&nbsp;&nbsp;
					</td>
				</tr>
				
				
				<tr>
				  
					<td class="til">
						VM分区
					</td>
					<td>
                        <s:textfield name="theForm.SYS_VM" id="SYS_VM" />
					</td>
					 <td class="til">
						物理IP地址
					</td>
					<td >
						<s:textfield name="theForm.IP_PHYSICS" id="IP_PHYSICS"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						虚拟机IP地址
					</td>
					<td>
                        <s:textfield name="theForm.IP_VIRTUAL" id="IP_VIRTUAL" />
					</td>
					 <td class="til">
						管理地址
					</td>
					<td >
						<s:textfield name="theForm.IP_ILO" id="IP_ILO" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						系统口令
					</td>
					<td>
                        <s:textfield name="theForm.PWD_SYSTEM" id="PWD_SYSTEM" />
					</td>
					 <td class="til">
						console用户/口令
					</td>
					<td >
						<s:textfield name="theForm.PWD_CONSOLE" id="PWD_CONSOLE"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						对应存储
					</td>
					<td>
                        <s:textfield name="theForm.STORE" id="STORE" />
					</td>
					 <td class="til">
						归属域
					</td>
					<td >
						<s:textfield name="theForm.USE_DOMAN" id="USE_DOMAN"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						资源池
					</td>
					<td>
                        <s:textfield name="theForm.USE_RES" id="USE_RES"/>
					</td>
					 <td class="til">
						使用部门
					</td>
					<td >
						<s:textfield name="theForm.USE_DEPART" id="USE_DEPART" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						用户说明
					</td>
					<td>
                        <s:textfield name="theForm.USE_DESCRIP" id="USE_DESCRIP" />
					</td>
					 <td class="til">
						责任人
					</td>
					<td >
						<s:textfield name="theForm.MANAG_PERSON" id="MANAG_PERSON"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						借用记录
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_RECORD" id="MANAG_RECORD"/>
					</td>
					 <td class="til">
						维修记录
					</td>
					<td >
						<s:textfield name="theForm.MANAG_REPAIR" id="MANAG_REPAIR"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						是否在保
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_USE" id="MANAG_USE" />
					</td>
					 <td class="til">
						是否需要报废
					</td>
					<td >
						<s:textfield name="theForm.MANAG_USABLE" id="MANAG_USABLE" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						管理说明
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_DESCRIP" id="MANAG_DESCRIP" />
					</td>
					<td class="til">
						
					</td>
					<td>
                        
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
</html:html>
