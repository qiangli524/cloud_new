<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
var api = frameElement.api;
var w = api.opener;
//虚拟机控制台
function console() {
	var theForm = document.getElementById("theForm");
	var pool_uuid = theForm.POOL_UUID.value;
	var host_uuid = theForm.HOST_UUID.value;
	var entityId = theForm.ENTITY_ID.value;
	window.open("xen_vmConsole.do?pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&entityId="+entityId+"&state="+0,"控制台");
}
//修改虚拟机状态，启动、停止、删除等
function put_virtual_state(state) {
	var theForm = document.getElementById("theForm");
	var pool_uuid = theForm.POOL_UUID.value;
	var host_uuid = theForm.HOST_UUID.value;
	//var entityId = theForm.ENTITY_ID.value;
	var id = theForm.TREENODE_ID.value;
	var vh_uuid = theForm.VH_UUID.value;
	if (state == "powerOn") {
		stat = "\u542f\u52a8";
	} else if (state == "powerOff") {
		stat = "\u5173\u95ed";
	}
	if (confirm("\u786e\u5b9a\u8981" + stat + "\u8be5\u865a\u62df\u673a\u5417\uff1f")) {
		if(state == "powerOn"){
			bar(vh_uuid,"虚拟机启动中...");
		}else if(state == "powerOff"){
			bar(vh_uuid,"虚拟机关机中...");
		}
		<%--
		//原接口的调用方式
		$.getJSON("xen_putVMState.do?entity_id=" + entityId + "&state=" + state+"&id="+id+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid,{"time:":new Date().toString()},function(data){
			var result = data.responseCode;
			if(result !=null && result ==1){
				barEnd(vh_uuid,stat + "成功");
				if(state == "create"){
					$("#state").text("正在运行");
					$("#operate").html('<input type="button" class="thickbox btn-style02" value="停止" onclick="javascript:put_virtual_state(\'shutdown\')"/>');
					changeParentPage("正在运行");
				}else if(state == "shutdown"){
					$("#state").text("已关闭");
					$("#operate").html('<input type="button" class="thickbox btn-style02" value="启动" onclick="javascript:put_virtual_state(\'create\')"/>');
					$("#operate").append('<input type="button" class="thickbox btn-style02" value="删除" onclick="javascript:put_virtual_state(\'destroy\')"/>');
					changeParentPage("已关闭");
				}else if(state == "destroy"){
					w.closeDialog();
				}
			}else{
				barEnd(vh_uuid,stat + "失败");
			}
		});
		--%>
		//统一接口的调用方式
		$.getJSON("united_putVMPowerStateOnList.do?vh_uuid=" + vh_uuid + "&state=" + state+"&id="+id+"&host_uuid="+host_uuid+"&pool_uuid="+pool_uuid+"&vtype="+"2",{"time:":new Date().toString()},function(data){
			var result = data.responseCode;
			if(data == "success"){
				barEnd(vh_uuid,stat + "成功");
				if(state == "powerOn"){
					$("#state").text("正在运行");
					$("#operate").html('<input type="button" class="thickbox btn-style02" value="停止" onclick="javascript:put_virtual_state(\'powerOff\')"/>');
					changeParentPage("正在运行");
				}else if(state == "powerOff"){
					$("#state").text("已关闭");
					$("#operate").html('<input type="button" class="thickbox btn-style02" value="启动" onclick="javascript:put_virtual_state(\'powerOn\')"/>');
					<%-- 
					$("#operate").append('<input type="button" class="thickbox btn-style02" value="删除" onclick="javascript:put_virtual_state(\'destroy\')"/>');
					--%>
					changeParentPage("已关闭");
				}
			}else{
				barEnd(vh_uuid,stat + "失败");
			}
		});
	}
}
	
function bar(idstr,contents){
$.dialog({
		id:idstr,
	    title: '公告',
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

function changeParentPage(state){
	var theForm = document.getElementById("theForm");
	var vh_uuid = theForm.VH_UUID.value;
	w.changePageText(state,vh_uuid);
}
</script>
</head>
<body>
<s:form action="yvm_vmInfo" method="post" id="theForm">
	<s:hidden id="VH_UUID" name="theForm.VH_UUID"></s:hidden>
	<s:hidden id="TREENODE_ID" name="theForm.TREENODE_ID"></s:hidden>
	<s:hidden id="POOL_UUID" name="theForm.POOL_UUID"></s:hidden>
	<s:hidden id="HOST_UUID" name="theForm.HOST_UUID"></s:hidden>
	<s:hidden id="ENTITY_ID" name="theForm.ENTITY_ID"></s:hidden>
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%">
						虚拟机名称
					</td>
					<td align="left">
						<font color="black"><s:property value="theForm.VH_NAME"/></font>
					</td>
				</tr> 
				<tr>
					<td class="til" style="font:red">
						虚拟机操作系统
					</td>
					<td align="left">
						<s:property value="theForm.VH_SYSTEM"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						虚拟化类型
					</td>
					<td align="left">
						<s:property value="theForm.VH_TYPE"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属主机
					</td>
					<td align="left">
						<s:property value="theForm.EQ_NAME"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						虚拟机IP
					</td>
					<td align="left">
						<s:property value="theForm.VH_IP"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU(核)
					</td>
					<td align="left">
						<s:property value="theForm.VH_CPU"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						内存(MB)
					</td>
					<td align="left">
						<s:property value="theForm.VH_MEM"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						存储(G)
					</td>
					<td align="left">
						<s:property value="theForm.VH_STORAGE"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						网卡(个)
					</td>
					<td align="left">
						<s:property value="theForm.VH_NETWORK"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						应用
					</td>
					<td align="left">
						<s:property value="theForm.APP_NAME"/>
					</td>
				</tr> 
				<tr>
					<td class="til">
						状态
					</td>
					<td align="left" id="state">
						<s:if test="theForm.VH_STAT==1">
							正在运行
						</s:if>
						<s:if test="theForm.VH_STAT==0">
							已关闭
						</s:if>
						<s:if test="theForm.VH_STAT==2">
							挂起
						</s:if>
					</td>
				</tr> 
				<tr>
					<td class="til">
						控制台
					</td>
					<td align="left">
						<a href="javascript:console();" style="color: blue;outline: blue">控制台</a>
					</td>
				</tr> 
				<tr>
					<td class="til">
						操作
					</td>
					<td align="left" id="operate">
						<s:if test="theForm.VH_STAT==1">
							<input type="button" class="thickbox btn-style02" value="停止" onclick="javascript:put_virtual_state('powerOff')"/>
						</s:if>
						<s:if test="theForm.VH_STAT==0">
							<input type="button" class="thickbox btn-style02" value="启动" onclick="javascript:put_virtual_state('powerOn')"/>
							<%--
							<input type="button" class="thickbox btn-style02" value="删除" onclick="javascript:put_virtual_state('destroy')"/>
							 --%>
						</s:if>
						<s:if test="theForm.VH_STAT==2">
							<input type="button" class="thickbox btn-style02" value="启动" onclick="javascript:put_virtual_state('powerOn')"/>
						</s:if>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
