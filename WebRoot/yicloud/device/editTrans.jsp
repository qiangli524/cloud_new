<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
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
			</table>
