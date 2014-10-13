<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:addDBType,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function addDBType(){
		var parentId = $("#parent_id").val();
		var name = $("#entity_name").val();
		if(name == null ||name===""){
			alert("名称不能为空！");
			return false;
		}
		w.saveDBEntity(parentId,$("#treeObj").serialize());
		return true;
	}
	
	$(function(){
		var server_type = $("#server_type").val();
		var parent_id = $("#parent_id").val();
		$("[name='selectDBEntity']").unbind().live("click",function(){
				w.$.dialog({
	    			id:'addDBInstance',
	    			title:'选择实体',
	    			width: '800px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:paasTree_listDBEntity.do?treeObj.server_type='+server_type+'&treeObj.parent_id='+parent_id
		    		});
			 });
		$("[name='selectHost']").unbind().live("click",function(){
				w.$.dialog({
	    			id:'addGPHost',
	    			title:'选择主机',
	    			width: '800px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:paasTree_listGpHost.do?treeObj.server_type='+server_type
		    		});
			 });
	});

	function listDBEntity(entity_id,entity_name){
		$("#entity_id").attr("value",entity_id);
		$("#entity_name").attr("value",entity_name);
		$("#name").attr("value",entity_name);
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="treeObj" cssStyle="theForm">
	<s:hidden name="treeObj.parent_id" id="parent_id"></s:hidden>
	<s:hidden name="treeObj.server_type" id="server_type"></s:hidden>
		<s:if test="treeObj.server_type==6">
			<div>
				<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
					<tr>
						<td class="til">
							主机<font color="red">*</font>
						</td>
						<td>
							<s:hidden name="treeObj.entity_id" id="entity_id"></s:hidden>
							<s:hidden name="treeObj.name" id="name"></s:hidden>
							<s:textarea id="entity_name" disabled="true" cssStyle="height:80px;"></s:textarea>
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "选择" name="selectHost" /></span>
						</td>
					</tr>
				</table>
			</div>
		</s:if>
		<s:else>
			<div>
				<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
					<tr>
						<td class="til">
							实体<font color="red">*</font>
						</td>
						<td>
							<s:hidden name="treeObj.entity_id" id="entity_id"></s:hidden>
							<s:hidden name="treeObj.name" id="name"></s:hidden>
							<s:textarea id="entity_name" disabled="true" cssStyle="height:80px;"></s:textarea>
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "选择" name="selectDBEntity" /></span>
						</td>
					</tr>
				</table>
			</div>
		</s:else>
	</s:form>
</body>