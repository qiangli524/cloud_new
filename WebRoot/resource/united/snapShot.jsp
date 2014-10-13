<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<style type="text/css">
		.validateError {border:2px solid #FF7256;}
		.onfocus {border:1px solid #FFD700;}
	</style>
	<script type="text/javascript">
		var api = frameElement.api;
	 var w = api.opener;
	
	 api.button({
	     id:'Ok',
	     name: '创建',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
		/*
		function addSnapshotInSonPage(){
			var vtype = $("#vtype").val();
			if($("#snapshotName").val().length==0){
				$("#snapshotName").attr("class","validateError");
				var $_snapshotname = $("#snapshotName");
				$("#snapshotName").parent().empty().append($_snapshotname ).
				append('<br /><font color="red">名称不能为空，请输入名称!</font>');
				return false;
			}else{
				$("#snapshotName").attr("class","txt");
				var $_snapshotname = $("#snapshotName");
				$("#snapshotName").parent().empty().append($_snapshotname);
			}
			if($("#description").val().length==0){
				$("#description").attr("class","validateError");
				var $_description = $("#description");
				$("#description").parent().empty().append($_description)
				.append('<br /><font color="red">描述不能为空，请输入描述!</font>');
				return false;
			}else{
				$("#description").removeClass("validateError");
				var $_description = $("#description");
				$("#description").parent().empty().append($_description);
			}
			var uuid = '<s:property value="uuid" />';
			var connect_id = '<s:property value="connect_id" />';
			var vtype = '<s:property value="vtype" />';
			w.addSnapshot(uuid,connect_id,vtype,$("#snapshotVO").serialize());
		}
		*/
		function add(){
			var uuid = '<s:property value="uuid" />';
			var connect_id = '<s:property value="connect_id" />';
			var vtype = '<s:property value="vtype" />';
			var mem = $("#snapshotMem").is(':checked');
			var state = $("#quiescedState").is(':checked');
			var snapshotName = $("#snapshotName").val();
			var description = $("#description").val();
			if(mem){
				$("#snapshotMem").attr("value",true);
			}else{
				$("#snapshotMem").attr("value",false);
			}
			if(state){
				$("#quiescedState").attr("value",true);
			}else{
				$("#quiescedState").attr("value",false);
			}
			if(snapshotName==""){
				alert("名字不能为空");
				return false;
			}
			if(description==""){
				alert("描述不能为空");
				return false;
			}
			w.addSnapshot(uuid,connect_id,vtype,$("#snapshotVO").serialize());
		}
	</script>
</head>
<body>
<s:form action="" method="post" id="snapshotVO">
	<div class="table-ct">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td class="til" width="20%" align="left">
					名称<font color="red">*</font>
				</td>
				 <td align="left">
					<s:textfield name="snapshotVO.snapshotName"  id="snapshotName" cssClass="inpt-2"></s:textfield>
				</td>          
			</tr>
			<tr>
				<td class="til" width="20%" align="left">
					描述<font color="red">*</font>
				</td>
				 <td align="left">
					<s:textarea name="snapshotVO.description" id="description" cols="50" rows="6" ></s:textarea>
				</td>          
			</tr>
	<!-- VMWARE -->
		<s:if test="vtype==1">
			<tr>
				<td class="til" width="20%" align="left">
					选项
				</td>
				<td align="left">
					<input type="checkbox" name="snapshotVO.snapshotMem" id="snapshotMem"/>生成虚拟机内存快照</br>
					<input type="checkbox" name="snapshotVO.quiescedState" id="quiescedState"/>使客户机文件系统处于默认状态(需要安装有VMware Tools)
				</td>
			</tr>
		</s:if>
		</table>
	</div>
</s:form>
</body>
