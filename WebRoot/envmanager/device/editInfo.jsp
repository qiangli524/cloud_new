<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>

<body class="pop-body scrollbody">
		<s:hidden name="theForm.eq_id" id="eq_id" />
		
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td colspan="4" class="tit-zzi">
				主机信息
					</td>
					</tr>
				<!-- 
				<tr>
		
					<td class="til" width="20%">
						服务器名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.eq_name"  id="eq_name"/>
					</td>
					<td class="til">
						服务器类型 <font color="red">*</font>
					</td>
					<td>
	                   <s:select name="theForm.eq_type" id="eq_type" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片'}">
								</s:select>
					</td>
				</tr>
 				-->
				<tr>
				   <td class="til">
						服务器类型 <font color="red">*</font>
					</td>
					<td>
	                   <s:select name="theForm.eq_type" id="eq_type" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片'}">
								</s:select>
					</td>
				
					<td class="til">
							所属机柜<font color="red">*</font>
						</td>
						<td>
						 <s:select list="theForm.cabinetList" headerKey="0" headerValue="请选择" listKey="c_id" listValue="c_addr" name="theForm.cq_id" id="cq_id">
		                   </s:select>
						</td>
				</tr>
				
				<tr>
					<td class="til">
						服务器主机名称<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.eq_hostname" id="eq_hostname"/>
					</td>
				    <td class="til">
						是否支持虚拟化<font color="red">*</font>
					</td>
					
						<td >
							<s:select name="theForm.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" list="#{'0':'不支持','1':'支持'}">
							</s:select>
						</td>
						
				</tr>
				<!--
				<tr>
					<td class="til">云平台能否管控<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.control" headerKey="" headerValue="请选择" list="#{'0':'不能管控','1':'能管控'}">
						</s:select>
					</td>
					<td class="til">
					</td>
					<td>
					
					</td>
				</tr>-->
				
				<!-- 研发设备整合部分 -->
				<tr>
					<td colspan="4" class="tit-zzi">
					设备状态信息
						</td>
					</tr>
				
				<tr>
		
					<td class="til">
						机房 
					</td>
					<td>
						<s:textfield name="theForm.MECH_ROOM"  id="MECH_ROOM"/>
					</td>
					<td class="til">
						主机类型
					</td>
					<td>
                        <s:textfield name="theForm.CAPITAL_TYPE" id="CAPITAL_TYPE"/>&nbsp;&nbsp;
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						设备ID
					</td>
					<td >
						<s:textfield name="theForm.MECH_ID"  id="MECH_ID"/>	
					</td>
					<td class="til">
						资产编号
					</td>
					<td >
						<s:textfield name="theForm.CAPITAL_ID" id="CAPITAL_ID" />
					</td>
				</tr>
				<tr>
				   <td class="til">
						主机名
					</td>
					<td >
						<s:textfield name="theForm.SYS_HOSTNAME" id="SYS_HOSTNAME" />&nbsp;&nbsp;
					</td>
					<td class="til">
						操作系统
					</td>
					<td>
                        <s:textfield name="theForm.SYS_SYSTEM" id="SYS_SYSTEM" />
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
					</td>
					<td>
					</td>
					
				</tr>
				<tr>
				  
					 <td class="til">
						配置
					</td>
					<td >
					<s:textarea name="theForm.MECH_CONF" id="MECH_CONF" cols="40" rows="8"></s:textarea>
					<!--	<s:textfield name="theForm.MECH_CONF" id="MECH_CONF" />&nbsp;&nbsp;--> 
					</td>
					<td class="til">
						型号
					</td>
					<td>
					  
					  <s:textarea name="theForm.MECH_TYPE" id="MECH_TYPE" cols="40" rows="8"></s:textarea>
                       <!-- <s:textfield name="theForm.MECH_TYPE" id="MECH_TYPE"/> --> 
					</td>
					
				</tr>
				
				
				
				<tr>
					<td colspan="4" class="tit-zzi">
					IP地址
					</td>
				</tr>
				<tr>
				 	<td class="til">
						物理IP地址
					</td>
					<td >
						<s:textfield name="theForm.IP_PHYSICS" id="IP_PHYSICS"/>&nbsp;&nbsp;
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
						MAC地址<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.MAC" id="mac"/>
					</td>
					<td class="til">
						
					</td>
					<td>
						
					</td>
				
				</tr>
				<!-- 
				<tr>
					<td class="til">
						虚拟机IP地址
					</td>
					<td class="til">
						使用部门
					</td>
					<td colspan="2" class="til">
					
					</td>
					
				</tr>
			
				<tr>
					<td >
						<s:textfield name="theForm.IP_VIRTUAL" id="IP_VIRTUAL" />
						
					</td>
					<td >
						<s:textfield name="theForm.USE_DEPART" id="USE_DEPART" />&nbsp;&nbsp;
					</td>
					<td colspan="2">
					</td>
				</tr>
				 -->
				<tr>
					<td colspan="4" class="tit-zzi">
					系统信息
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
					</td>
					<td>
					</td>
					
				</tr>
				<tr>
					<td colspan="4" class="tit-zzi">
					用途信息
					</td>
				</tr>
				<tr>
				 <td class="til">
						归属域
					</td>
					<td >
						<s:textfield name="theForm.USE_DOMAN" id="USE_DOMAN"/>&nbsp;&nbsp;
					</td>
				  
					<td class="til">
						资源池
					</td>
					<td>
                        <s:textfield name="theForm.USE_RES" id="USE_RES"/>
					</td>
					
				</tr>
				<tr>
				   <!-- <td class="til">
						使用部门
					</td>
					<td >
						<s:textfield name="theForm.USE_DEPART" id="USE_DEPART" />&nbsp;&nbsp;
					</td>
					 -->
					<td class="til">
						用户说明
					</td>
					<td>
                        <s:textfield name="theForm.USE_DESCRIP" id="USE_DESCRIP" />
					</td>
					 <td class="til">
						
					</td>
					<td >
						
					</td>
					
				</tr>
				<tr>
					<td colspan="4" class="tit-zzi">
					管理信息
					</td>
				</tr>
				<tr>
				   <td class="til">
						责任人
					</td>
					<td >
						<s:textfield name="theForm.MANAG_PERSON" id="MANAG_PERSON"/>&nbsp;&nbsp;
					</td>
					<td class="til">
						借用记录
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_RECORD" id="MANAG_RECORD"/>
					</td>
					
				</tr>
				<tr>
				   <td class="til">
						维修记录
					</td>
					<td >
						<s:textfield name="theForm.MANAG_REPAIR" id="MANAG_REPAIR"/>&nbsp;&nbsp;
					</td>
					<td class="til">
						是否在保
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_USE" id="MANAG_USE" />
					</td>
					
				</tr>
				<tr>
				   <td class="til">
						是否需要报废
					</td>
					<td >
						<s:textfield name="theForm.MANAG_USABLE" id="MANAG_USABLE" />&nbsp;&nbsp;
					</td>
					<td class="til">
						管理说明
					</td>
					<td>
                        <s:textfield name="theForm.MANAG_DESCRIP" id="MANAG_DESCRIP" />
					</td>
					
					
				</tr>
				
			</table>
</body>