<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
		<s:hidden name="theForm.eq_id" id="eq_id" />
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
			</table>
