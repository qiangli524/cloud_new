<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	var id =<%=request.getAttribute("id")%>;
	var vm_uuid ='<%=request.getAttribute("vm_uuid")%>';
	var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
	var host_uuid = '<%=request.getAttribute("host_uuid")%>';
	var flag = '<%=request.getAttribute("flag")%>';
	var nodeId = '<%=request.getAttribute("nodeId")%>';

	 var api = frameElement.api;
	 var w = api.opener;
	 if(flag=='true'){
			 api.button({
			     id:'cancle1',
			     name: '取消'
			 });
		}else{
			 api.button({
			     id:'Ok1',
			     name: '启动',
			     callback:ok,
			     focus: true
			 },
			 {
			     id:'cancle1',
			     name: '取消'
			 });
		}

	 function ok(){
		var newHostEntityID=$(":selected").val();
		if(newHostEntityID==0){
			alert("请选择虚拟机要在其上启动的服务器！")
			return false;
		}
		w.moveVm(vm_uuid,pool_uuid,host_uuid,newHostEntityID,nodeId);
	}
	 $(function(){
        if(flag=='true'){
            $("#text").empty().append("虚拟机所在存储不是共享存储，无法在其他服务器上启动！").css({"color":"red"});
            $("[name='select']").attr("disabled","true");
        }
	    });
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="vmw_cloneVirtualMac.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
		<s:hidden name="theForm.NAME_ZH" id="NAME_ZH"></s:hidden>
			<tr>
				<td align="left" colspan="2">
					<span id="text">当虚拟机关闭时，会恢复到自己启动前所在的服务器。</span>
				</td>
			</tr>
			<tr id="migHost1" style="display:">
				<td class="til">
					在服务器上启动：
				</td>
				<td>
					<s:select headerKey="0" headerValue="-请选择-" list="theForm.resultList" disabled="false" name="select" listKey="entityId" listValue="name" id="HOST_ID"  style="width: 200px;"></s:select>
				</td>
			</tr>
		
			<tr>
			</tr>
		</table>
	</s:form>
</body>
