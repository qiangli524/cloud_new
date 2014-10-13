<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
   function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
	function submitRequest(theForm){ 
		var id = "<%=request.getAttribute("id")%>";
		var name = "<%=request.getAttribute("name")%>";
		var dcName = "<%=request.getAttribute("dcName")%>";
		var ha = "<%=request.getAttribute("ha")%>";
		var drs = "<%=request.getAttribute("drs")%>";
		var hastate = "0";
		var drsstate = "0";
		if(document.getElementById("hastate").checked){
			hastate = "1";
		}
		if(document.getElementById("drsstate").checked){
			drsstate = "1";
		}
		if(ha==hastate  && drs==drsstate){
			alert("未做更改!");
			return ;
		}
		var url = "cluster_saveConCluster.do?id=" + id + "&hastate=" + hastate + "&drsstate=" + drsstate + "&name=" + name + "&dcName=" + dcName;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result == 1){
					alert("保存成功!");
					window.location.reload();
				}else{
					alert("保存失败!");
					window.location.reload();
				}
		});
   	//	theForm.action="cluster_saveConCluster.do";
	//    theForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="cluster_saveConCluster.do" id="theForm" method="post"
	cssClass="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
				<tr>
					<td>
					<s:if test="#request.ha==0">
						<input type="checkbox" id="hastate"/>	打开 Vsphere HA					
					</s:if>
					<s:else>
						<input type="checkbox" id="hastate" checked="checked"/>	打开 Vsphere HA
					</s:else>
					</td>
					<td>vSphere HA功能用于检测故障,对集群中运行的虚拟机提供快速恢复功能。核心功能包括主机监控和虚拟机监控功能,用于在检测不到信号时最大程度的缩短停机时间。<br />
						必须打开vSphere HA，才能使用Fault Tolerance。
					</td>
				</tr>
				<tr>
					<td>
						<s:if test="#request.drs==0">
							<input type="checkbox" id="drsstate"/>	打开 Vsphere DRS
						</s:if>
						<s:else>
							<input type="checkbox" id="drsstate" checked="checked"/>	打开 Vsphere DRS
						</s:else>
					</td>
					<td>
						vSphere DRS 使vCenter Server能够将主机作为资源的聚合池进行管理。群集资源可以根据用户、组和虚拟机划分为更小的资源池。<br />
						vSphere DRS还使vCenter Server能够自动管理虚拟机到主机的分配，在虚拟机打开电源时给出放置位置建议，以及为平衡负载和强制执行资源分配策略而对运行的虚拟机进行迁移。<br />
						应当在集群中启用vSphere DRS和VMvare EVC，以允许在负载平衡期间放置和迁移已打开Fault Tolerance的虚拟机。
					</td>
				</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
