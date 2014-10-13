<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
	<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<div class="tit-zzi">
				<div id="zi">
					设备状态信息
				</div>
				<div id="zi"></div>
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
				
	</table>