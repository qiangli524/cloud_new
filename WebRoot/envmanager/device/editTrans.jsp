<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<body class="pop-body scrollbody">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<td colspan="4" class="tit-zzi">
				交换机/路由器信息
					</td>
					</tr>
				<tr>
			<tr>
		
					<td class="til" width="20%">
						名称
					</td>
					<td>
						<s:textfield name="theForm.NAME"  id="trans_name"/>
					</td>
					<td class="til">
						型号
					</td>
					<td>
	                  	<s:textfield name="theForm.TYPE"  id="trans_type"/>			
					</td>
				</tr>

				<tr>
				    <td class="til">
						物理位置 
					</td>
					<td >
						<s:textfield name="theForm.LOCATION" id="trans_location"/>
					</td>
					<td class="til">
						百兆端口总数
					</td>
					<td>
                        <s:textfield name="theForm.PORT_100M_NUMS" id="trans_PORT_100M_NUMS" nameTemp='百兆端口总数' nameId='meterNumber'/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						百兆端口可用数
					</td>
					
						<td >
							 <s:textfield name="theForm.PORT_100M_USABLE" id="trans_PORT_100M_USABLE" nameTemp='百兆端口可用数' nameId='meterNumber'/>
						</td>
						<td class="til">
							千兆端口总数
						</td>
						<td>
						 <s:textfield name="theForm.PORT_RJ45_NUMS" id="trans_PORT_RJ45_NUMS" nameTemp='千兆端口总数' nameId='meterNumber'/>
						</td>
				</tr>
				<tr>
					<td class="til">千兆端口可用数
					</td>
					<td>
						<s:textfield name="theForm.PORT_RJ45_USABLE" id="trans_PORT_RJ45_USABLE" nameTemp='千兆端口可用数' nameId='meterNumber'/>
					</td>
					<td class="til">光纤以太端口总数
					</td>
					<td>
						<s:textfield name="theForm.PORT_FIBER_NUMS" id="trans_PORT_FIBER_NUMS" nameTemp='光纤以太端口总数' nameId='meterNumber'/>
					</td>
				</tr>
				<tr>
					<td class="til">光纤端口可用数
					</td>
					<td>
						<s:textfield name="theForm.PORT_FIBER_USABLE" id="trans_PORT_FIBER_USABLE" nameTemp='光纤端口可用数' nameId='meterNumber'/>
					</td>
					<td class="til">ATM端口总数
					</td>
					<td>
						<s:textfield name="theForm.PORT_ATM_NUMS" id="trans_PORT_ATM_NUMS" nameTemp='ATM端口总数' nameId='meterNumber'/>
					</td>
				</tr>
				<tr>
					<td class="til">ATM端口可用数
					</td>
					<td>
						<s:textfield name="theForm.PORT_ATM_USABLE" id="trans_PORT_ATM_USABLE" nameTemp='ATM端口可用数' nameId='meterNumber'/>
					</td>
					<td class="til">EI端口总数
					</td>
					<td>
						<s:textfield name="theForm.PORT_EI_NUMS" id="trans_PORT_EI_NUMS" nameTemp='EI端口总数' nameId='meterNumber'/>
					</td>
				</tr>
				<tr>
					<td class="til">EI端口可用数
					</td>
					<td>
						<s:textfield name="theForm.PORT_EI_USABLE" id="trans_PORT_EI_USABLE" nameTemp='EI端口可用数' nameId='meterNumber'/>
					</td>
					<td class="til">管理IP地址 
					</td>
					<td>
						<s:textfield name="theForm.IP_ADDR" id="trans_IP_ADDR" nameTemp='管理IP地址' nameId='ip'/>
					</td>
				</tr>
				
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
						主机型号
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
					</td>
					<td>
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
						虚拟机IP地址
					</td>
					<td>
                        <s:textfield name="theForm.IP_VIRTUAL" id="IP_VIRTUAL" />
					</td>
					
				</tr>
				<tr>
				
					 <td class="til">
						管理地址
					</td>
					<td >
						<s:textfield name="theForm.IP_ILO" id="IP_ILO" />&nbsp;&nbsp;
					</td>
					<td class="til">
					</td>
					<td>
					</td>
				</tr>
				
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
				   <td class="til">
						使用部门
					</td>
					<td >
						<s:textfield name="theForm.USE_DEPART" id="USE_DEPART" />&nbsp;&nbsp;
					</td>
					<td class="til">
						用户说明
					</td>
					<td>
                        <s:textfield name="theForm.USE_DESCRIP" id="USE_DESCRIP" />
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