<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
  	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<title></title>

	<script type="text/javascript">
		<%-- var id= '<%=request.getAttribute("id")%>'; --%>
   		var api = frameElement.api;
		w = api.opener;
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:submitRequest,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });
		 
	//插入数据库
	function submitRequest(){ 
		var name = $("#name").val();
		var clusterId = $("#clusterId").val();
		var hostId = $("#hostId").val();
		if(name=="" || name==null){
			alert("请填写云节点名称");
			return false;
		}
		if(clusterId==""){
			alert("请选择集群名称");
			return false;
		}
		if(hostId==""){
			alert("请选择主机IP");
			return false;
		}
		var url = "bolnode_save.do";
		var data = $("#bolNodeVO").serialize();
		w.saveBolNode(url,data);  
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssClass="bolNodeVO" id="bolNodeVO">
<s:hidden name="bolNodeVO.id" id="id"></s:hidden> 

			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						云节点名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="bolNodeVO.name" id="name" maxlength="50"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						集群名称<font color="red">*</font>
					</td>
					<td>
						<s:if test="clusterList!=null">
							<s:select list="clusterList" name="bolNodeVO.clusterId" id="clusterId" listKey="id" listValue="name" headerKey="" headerValue="-请选择-" >
					   		</s:select>
						</s:if>
						<s:else>
							<select><option value=''>-请选择-</option></select>
						</s:else>
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机IP<font color="red">*</font>
					</td>
					<td>
					   <s:if test="hostIpList!=null">
							<s:select list="hostIpList" name="bolNodeVO.hostId" id="hostId" listKey="id" listValue="ipaddress" headerKey="" headerValue="-请选择-" >
					   		</s:select>
						</s:if>
						<s:else>
							<select><option value=''>-请选择-</option></select>
						</s:else>
					</td>
				</tr>
				<tr>
				    <td class="til">
						云节点状态<font color="red">*</font>
					</td>
					<td>
					   <s:select list="#{'1':'正常','0':'异常'}" name="bolNodeVO.status" id="status"></s:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						云节点描述
					</td>
					<td>
						<s:textarea name="bolNodeVO.descrip" id="descrip" rows="6" cols="40" maxlength="256"></s:textarea>
					</td>		
				</tr>
			</table>
</s:form>
</body>