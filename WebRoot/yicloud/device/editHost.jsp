<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
		<s:hidden name="theForm.eq_id" id="eq_id" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til" width="20%">
						���������� <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.eq_name"  id="eq_name"/>
					</td>
					<td class="til">
						���������� <font color="red">*</font>
					</td>
					<td>
	                   <s:select name="theForm.eq_type" id="eq_type" headerKey="" headerValue="��ѡ��" list="#{'1':'IBMС��','2':'IBM��Ƭ','3':'IBM��ͨ��Ƭ','4':'HPx86��Ƭ'}">
								</s:select>
					</td>
				</tr>

				<tr>
				    <td class="til">
						������IP��ַ <font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.eq_ip" id="eq_ip"/>
					</td>
					<td class="til">
						��������������<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.eq_hostname" id="eq_hostname"/>
					</td>
				</tr>
				<tr>
				    <td class="til">
						�Ƿ�֧�����⻯<font color="red">*</font>
					</td>
					
						<td >
							<s:select name="theForm.hasvertual" id="hasvertual" headerKey="" headerValue="��ѡ��" list="#{'0':'��֧��','1':'֧��'}">
							</s:select>
						</td>
						<td class="til">
							��������<font color="red">*</font>
						</td>
						<td>
						 <s:select list="theForm.cabinetList" headerKey="0" headerValue="��ѡ��" listKey="c_id" listValue="c_addr" name="theForm.cq_id" id="cq_id">
		                   </s:select>
						</td>
				</tr>
				<tr>
					<td class="til">��ط�ʽ<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.PROTOCOL" headerKey="" headerValue="��ѡ��" list="#{'ssh':'ssh','telnet':'telnet'}">
						</s:select>
					</td>
					<td class="til">��ƽ̨�ܷ�ܿ�<font color="red">*</font>
					</td>
					<td>
						<s:select name="theForm.control" headerKey="" headerValue="��ѡ��" list="#{'0':'���ܹܿ�','1':'�ܹܿ�'}">
						</s:select>
					</td>
				</tr>
			</table>
