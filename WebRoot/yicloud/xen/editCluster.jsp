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
