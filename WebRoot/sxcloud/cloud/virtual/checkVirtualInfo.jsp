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
						处理器设置
					</td>
					<td>
						<s:select list="#{'DEDICATED':'专用设置','SHARED':'共享设置'}" name="theForm.VH_CPU_MODE" id="VH_CPU_MODE" onfocus="this.blur()" cssStyle="background-color:#F8F8FF" disabled="true"></s:select>
					</td>
				</tr>
				<tr id=dedicatedModel style="display: ">
					<td class="til">
						专用处理器设置
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
						共享虚拟处理器
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
						共享处理单元
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
						虚拟服务器共享处理单元模式
					</td>
					<td>
					<s:select list="#{'CAP':'打开','UNCAP':'关闭'}" name="theForm.VH_PROCESS_UNIT_MODE" id="VH_PROCESS_UNIT_MODE" onfocus="this.blur()" cssStyle="background-color:#F8F8FF" disabled="true"></s:select>
					</td>
				</tr>
				<tr id=priority style="display:">
					<td class="til">
						虚拟服务器使用共享处理器池中的可用处理单元的优先级:
					</td>
					<td>
						<s:property value="theForm.VH_PROCESS_UNIT_Priority"/>
					</td>
				</tr>

				<tr>
					<td class="til">
						内存设置
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
						系统
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="til">
						适配器1
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td class="til">
						网络配置(IP)
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
							存储器根卷名称
						</td>
						<td class="til">
							存储器大小
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
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back()" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
