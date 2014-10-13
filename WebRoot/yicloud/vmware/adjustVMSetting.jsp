<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
		function submitForm(theForm) {
				var hostCode = '<%=request.getAttribute("hostCode") %>';
				var NAME_EN = theForm.NAME_EN.value;
				var cpu = theForm.CPU.value;
				var memory = theForm.MEMORY.value;
				var dataSize = theForm.dataSize.value;
				var ID = theForm.ID.value;
				var code = theForm.CODE.value;
				//var nic = $("#nic").find("option:selected").text();
				var nicNum = new Array();
				<%
					String[] nicnum = (String[])request.getAttribute("nicnum");
					for(int i=0;i<nicnum.length;i++){
						try{
				%>
							nicNum.push('<%= URLDecoder.decode(nicnum[i],"utf-8") %>');
				<%
						}catch(Exception e){
							e.printStackTrace();
						}						
					}
				%>
				<%--
				var nic1 ='<%=nicnum[0]%>';
				var nic1value = document.getElementById("<%=nicnum[0]%>").value;
				$("#")
				var nic2 = "";
				var nic2 ='<%=nicnum[1]%>';
				var nic2value = "";	
				--%>
				<%--	var nic2value = document.getElementById("<%=nicnum[1]%>").value; --%>
				var nic1 = '';
				var nic2 = '';
				var nic1value = '';
				var nic2value = '';
				for(var i = 0;i<nicNum.length;i++){
					if(i==0){
						var nic1 = nicNum[i];
						var nic1value = document.getElementById(nic1).value;
					}else if(i==1){
						var nic2 = nicNum[i];
						var nic2value = document.getElementById(nic2).value;
					}
				}
			if (confirm('确认调整虚拟机吗?')) {
				bar(ID,"正在重新设置虚拟机"+NAME_EN);
				 $.getJSON("vmw_adjustVmwVirtualInfo.do?cpu="+cpu+"&memory="+memory+"&dataSize="+dataSize+"&NAME_EN="+code+"&ID="+ID+"&nic1="+encodeURI(encodeURI(nic1))+"&nic2="+encodeURI(encodeURI(nic2))+"&nic1value="+nic1value+"&nic2value="+nic2value+"&CODE="+code+"&hostCode="+hostCode,{'time':new Date().toString()},function(data){
				 	if(data==1) {
				 		barEnd(ID,"已成功设置虚拟机"+NAME_EN);
				 	} else {
				 		barEnd(ID,"重新设置虚拟机"+NAME_EN+"失败");
				 	}
				 	//window.location.reload();
				 });
			 }
		}
	</script>
	<script type="text/javascript">
	$.dialog.setting.zIndex = 100000;
		function bar(idstr,contents){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
	</script>
</head>
<body style="width: 840px;height: 450px;margin: 10px;">
	<s:form action="vmw_adjustVmwVirtualInfo.do" method="post" id="theForm">
		<s:hidden name="theForm.NAME_EN" id="NAME_EN"></s:hidden>
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.CODE" id="CODE"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="20%">
						所属主机IP
					</td>
					<td>
						<s:property value="theForm.hostIP" />
					</td>
				</tr>
				<tr>
					<td class="til">
						虚拟机名称
					</td>
					<td>
						<s:property value="theForm.NAME_EN" />
					</td>
				</tr>
				<tr>
					<td class="til">
						IP地址
					</td>
					<td>
						<s:property value="theForm.IP" />
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU(单位：个)
					</td>
					<td>
						<s:textfield name="theForm.CPU" id="CPU"
							cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						内存(单位：MB)
					</td>
					<td>
						<s:textfield name="theForm.MEMORY" id="MEMORY"
							cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						存储(单位：GB)
						</br>
						(只能添加存储，不能减小)
					</td>
					<td>
						<s:textfield name="theForm.dataSize" id="dataSize"
							cssClass="txt notnull posInt"></s:textfield>
					</td>
				</tr>
				<s:iterator value="%{#request.nicList}" id="nics">
					<s:iterator value="nics">
						<tr>
							<td class="til">
								<s:property value="key"/>
							</td>
							<td>
								<s:select list="theForm.resultList" listValue="name"
									listKey="pguuid" id="%{key}" value='%{value}'></s:select>
							</td>
						</tr>
					</s:iterator>
				</s:iterator>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
							onclick="javascript:submitForm(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
