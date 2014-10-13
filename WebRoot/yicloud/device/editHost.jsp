<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
		<s:hidden name="theForm.eq_id" id="eq_id" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
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

				<tr>
				    <td class="til">
						服务器IP地址 <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.eq_ip" id="eq_ip"/>
					</td>
					<td class="til">
						服务器主机名称<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.eq_hostname" id="eq_hostname"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						是否支持虚拟化<font color="red">*</font>
					</td>
					
						<td >
							<s:select name="theForm.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" list="#{'0':'不支持','1':'支持'}">
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
					<td class="til">监控方式<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.PROTOCOL" headerKey="" headerValue="请选择" list="#{'ssh':'ssh','telnet':'telnet'}">
						</s:select>
					</td>
					<td class="til">云平台能否管控<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.control" headerKey="" headerValue="请选择" list="#{'0':'不能管控','1':'能管控'}">
						</s:select>
					</td>
				</tr>
			</table>
