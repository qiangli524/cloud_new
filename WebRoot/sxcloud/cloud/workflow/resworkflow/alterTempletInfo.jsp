<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page
	import="com.sitech.basd.workflow.domain.resourceorder.WorkFlowConstant"%>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/link.jsp"%>
<%@ include file="/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript">
$(function() {
	$("#datepicker1").datepicker( {
		showOn : 'button',
		buttonImage : '../cresources/default/images/date-icon.gif',
		buttonImageOnly : true
	});
	$("#datepicker2").datepicker( {
		showOn : 'button',
		buttonImage : '../cresources/default/images/date-icon.gif',
		buttonImageOnly : true
	});
})
var count = 3;
function validateForm() {
	document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
	//�첽��ʽ�ж� ��������Ƿ�Ψһ
	var NEED_NUMBERS = document.getElementById("theForm").NEED_NUMBERS.value;
	if (NEED_NUMBERS == null || "" == NEED_NUMBERS) {
		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "�����Ų���Ϊ��";
		return false;
	}
	var url = "OrderUniqueJudgement.do?NEED_NUMBERS=" + NEED_NUMBERS
			+ "&Date" + (new Date());
	$
			.getJSON(
					url,
					function(data) {
						if ("NO" == data) {
							document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS
									+ "�Ѿ����ڣ������������!";
							return false;
						}
					})
	//�Ա��������ݽ����ж�

}

//�ύ
function submitForm(status) {
	//��ҳ�����������У��
	validateForm();
	document.getElementById("theForm").COMMAND.value = status;
	document.getElementById("theForm").submit();
}
function resetForm(theForm) {
	theForm.TEM_ID.value = '';
	theForm.TEM_NAME.value = '';
	theForm.TYPE.value = '';
	theForm.CONFIG_NAME.value = '';
	theForm.CONFIG_VALUE.value = '';
}
function submitRequest() {
	theForm.action = 'saveTempletInfo.do'
	theForm.submit();
}
function searchRequest() {
	theForm.submit();
}
function searchTempletRequest() {
	theForm.action = 'queryTempletInfo.do'
	theForm.submit();

}
</script>
</head>
<body>
	<html:form action="saveOrderInfo" method="post"
		styleId="theForm">
		<bean:define id="theForm" name="resourceWorkflowForm" />
		<html:hidden name="theForm" property="COMMAND" />
		<html:hidden property="CPU" name="theForm" />
		<html:hidden property="VH_MAX_CPU" name="theForm"/> 
		<html:hidden property="VH_MIN_CPU" name="theForm"/>
		<html:hidden property="MEM" name="theForm"/>
		<html:hidden property="VH_MIN_MEM" name="theForm"/>
		<html:hidden property="VH_MAX_MEM" name="theForm"/>
		<html:hidden property="IMAGE_ID" name="theForm"/>
		<div class="scrollbody">
			<div class="Operation_Table">
				<div class="itemContent1">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th>
								�����ţ�
							</th>
							<td>
								<html:text name="theForm" property="NEED_NUMBERS"
									onblur="javascript:validateForm()" />
								<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>
							</td>
							<th>
								����״̬��
							</th>
							<td colspan="3">
								<html:select name="theForm" property="NEED_STATUS"
									disabled="true">
									<html:option value="0" disabled="true">�ݸ�</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								������ʱ�䣺
							</th>
							<td>
								<html:text name="theForm" property="start_time" size="10"
									readonly="true" styleClass="txt" styleId="datepicker1" />
							</td>
							<th>
								�������ʱ�䣺
							</th>
							<td>
								<html:text name="theForm" property="end_time" size="10"
									readonly="true" styleClass="txt" styleId="datepicker2" />
							</td>
						</tr>
					</table>
				</div>
				<td>
					&nbsp;
				</td>
				<th>
					��ѡ��ģ��
				</th>
				<td>
					<html:select name="theForm" property="SELECT"
						onchange="searchTempletRequest()">
						<html:option value="0">-��ѡ��ģ��-</html:option>
						<html:optionsCollection name="theForm" property="typeList"
							label="TEM_NAME" value="TEM_ID" />
					</html:select>
				</td>
				<!-- itemContent1 end-->
				<div class="scrollbody">
					<div>
						<div id="zzi" class="query-form">
							<div id="zi">
								ģ�������Ϣ
							</div>
							<div id="zi"></div>
						</div>
						<div>
							<table width="100%" border="0" cellspacing="0"
								class="pop-table nosize">
								<tr>
									<td class="til">
										ģ����
									</td>
									<td>
										<html:text name="theForm" property="TEM_ID" size="10"
											readonly="true" styleClass="txt" styleId="datepicker1" />
										<!--<bean:write name="theForm" property="TEM_ID" />-->
									</td>

									<td class="til">
										ģ������
									</td>
									<td>
										<html:text name="theForm" property="TEM_NAME" size="10"
											readonly="true" styleClass="txt" styleId="datepicker1" />
									</td>
								</tr>
								<tr>
									<td class="til">
										ģ������
									</td>
									<td class="til">
										<html:text name="theForm" property="TYPE_NAME" size="10"
											readonly="true" styleClass="txt" styleId="datepicker1" />
									</td>
									<td class="til">
										ģ������
									</td>
									<td>
										<html:text name="theForm" property="TEM_DESC" size="10"
											onblur="javascript:validateForm()" />
									</td>
								</tr>

							</table>
						</div>
					</div>

					<div id="zzi" class="query-form">
						<div id="zi">
							ģ��������Ϣ&nbsp;&nbsp;&nbsp;
						</div>
					</div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										��Ŀ
									</th>
									<th>
										ֵ
									</th>
								</tr>
							</thead>
							<tbody>
								<logic:notEqual name="theForm" property="CPU" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="CPU" />
										</td>
										<td>
											<html:text name="theForm" property="CPUVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="MEM" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="MEM" />
											
										</td>
										<td>
											<html:text name="theForm" property="MEMVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="VH_MAX_CPU" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="VH_MAX_CPU" />
											
										</td>
										<td>
											<html:text name="theForm" property="VH_MAX_CPUVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="IMAGE_ID" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="IMAGE_ID" />
											
										</td>
										<td>
											<html:text name="theForm" property="IMAGE_IDVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="VH_MIN_CPU" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="VH_MIN_CPU" />
											
										</td>
										<td>
											<html:text name="theForm" property="VH_MIN_CPUVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="VH_MAX_MEM" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="VH_MAX_MEM" />
										</td>
										<td>
											<html:text name="theForm" property="VH_MAX_MEMVALUE" />
										</td>
									</tr>
								</logic:notEqual>
								<logic:notEqual name="theForm" property="VH_MIN_MEM" value="0">
									<tr>
										<td>
											<bean:write name="theForm" property="VH_MIN_MEM" />
											
										</td>
										<td>
											<html:text name="theForm" property="VH_MIN_MEMVALUE" />
										</td>
									</tr>
								</logic:notEqual>
							</tbody>
						</table>
					</div>
					<table width="100%" border="0" cellspacing="0"
						class="pop-table nosize">
						<tr>
							<td colspan="4" class="btnCenter">
								<input type="button" class="thickbox btn-style02" value="����"
									onclick="window.history.back()" />
								<input type="button" class="buttonStyle" value="�ύ"
									onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_PUBLISTH%>);" />
								<input type="button" class="buttonStyle" value="ȷ��"
									onclick="javascript:submitForm(<%=WorkFlowConstant.COMMAND_SAVE%>)" />
							</td>
						</tr>
					</table>
				</div>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<!-- ��Ť���� start -->
					<tr>
						<td class="footer_b">

						</td>
					</tr>
					<!-- ��Ť���� end -->
				</table>
			</div>
			<!-- ������ End -->
		</div>
	</html:form>
</body>
</html:html>