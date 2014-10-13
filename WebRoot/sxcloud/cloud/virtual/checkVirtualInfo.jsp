<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
function Trim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}
function cpuModelSelect() {
	var str = document.theForm.VH_CPU_MODE.value;
	if (str == 'DEDICATED') {
		document.getElementById('dedicatedModel').style.display = "";
		document.getElementById('sharedModel1').style.display = "none";
		document.getElementById('sharedModel2').style.display = "none";
		document.getElementById('sharedModel3').style.display = "none";
	} else if (str == 'SHARED') {
		document.getElementById('dedicatedModel').style.display = "none";
		document.getElementById('sharedModel1').style.display = "";
		document.getElementById('sharedModel2').style.display = "";
		document.getElementById('sharedModel3').style.display = "";
	}
	var str= document.theForm.VH_PROCESS_UNIT_MODE.value;
		if(str=='UNCAP'){
			document.getElementById('priority').style.display="";
			
		}else
		if(str=='CAP'){
			document.getElementById('priority').style.display="none";
		}
}
</script>
</head>
<body class="pop-body scrollbody" onload="cpuModelSelect()">
	<s:form action="virtual_checkVirtualInfo.do" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="40%">
						����������
					</td>
					<td>
						<s:select list="#{'DEDICATED':'ר������','SHARED':'��������'}" name="theForm.VH_CPU_MODE" id="VH_CPU_MODE" onfocus="this.blur()" cssStyle="background-color:#F8F8FF" disabled="true"></s:select>
					</td>
				</tr>
				<tr id=dedicatedModel style="display: ">
					<td class="til">
						ר�ô���������
					</td>
					<td>
						<s:property value="theForm.VH_MIN_CPU"/>
						&le;
						<s:property value="theForm.VH_CPU"/>
						&le;
						<s:property value="theForm.VH_MAX_CPU"/>
					</td>
				</tr>
				<tr id=sharedModel1 style="display: ">
					<td class="til">
						�������⴦����
					</td>
					<td>
						<s:property value="theForm.VH_MIN_CPU"/>
						&le;
						<s:property value="theForm.VH_CPU"/>
						&le;
						<s:property value="theForm.VH_MAX_CPU"/>
					</td>
				</tr>
				<tr id=sharedModel2 style="display: ">
					<td class="til">
						������Ԫ
					</td>
					<td>
						<s:property value="theForm.VH_MIN_PROCESS_UNIT"/>
						&le;
						<s:property value="theForm.VH_PROCESS_UNIT"/>
						&le;
						<s:property value="theForm.VH_MAX_PROCESS_UNIT"/>
					</td>
				</tr>
				<tr id=sharedModel3 style="display: ">
					<td class="til">
						���������������Ԫģʽ
					</td>
					<td>
					<s:select list="#{'CAP':'��','UNCAP':'�ر�'}" name="theForm.VH_PROCESS_UNIT_MODE" id="VH_PROCESS_UNIT_MODE" onfocus="this.blur()" cssStyle="background-color:#F8F8FF" disabled="true"></s:select>
					</td>
				</tr>
				<tr id=priority style="display:">
					<td class="til">
						���������ʹ�ù����������еĿ��ô���Ԫ�����ȼ�:
					</td>
					<td>
						<s:property value="theForm.VH_PROCESS_UNIT_Priority"/>
					</td>
				</tr>

				<tr>
					<td class="til">
						�ڴ�����
					</td>
					<td>
						<s:property value="theForm.VH_MIN_MEM"/>
						&le;
						<s:property value="theForm.VH_MEM"/>
						&le;
						<s:property value="theForm.VH_MAX_MEM"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						ϵͳ
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="til">
						������1
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="til">
						��������(IP)
					</td>
					<td>
						<s:property value="theForm.VH_IP"/>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table width="100%" class="blue-table sorttable" border="0"
				cellspacing="0">
				<thead>
					<tr id=dedicatedModel style="display: ">
						<td class="til" width="40%">
							�洢����������
						</td>
						<td class="til">
							�洢����С
						</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.list" id="theBean">
					<tr>
						<td>
							<s:property value="#theBean.VH_STORAGE_NAME"/>
						</td>

						<td>
							<s:property value="#theBean.VH_STORAGE_VALUE"/>
						</td>

					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="����"
						onclick="window.history.back()" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
