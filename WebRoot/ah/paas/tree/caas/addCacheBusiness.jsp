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
		     callback:addCacheBusiness,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 
		 
		 $("[name='selectBmt']").click(function(){
	        	currentEdit=$(this);
	    		w.$.dialog({
	    			id:'selectVM',
	    			title:'选择业务',
	    			width: '950px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:paasTree_getBusniessList.do?jspName=addCacheBusiness'
	    			});
	          });		 
	});
	
	function listBmt(id,name){
		$("#busi_id").attr("value",id);
		$("#name").attr("value",name);
	}
	
	function addCacheBusiness(){
		var parentId = $("#parent_id").val();
		var name = $("#name").val();
		if(name == null ||name===""){
			alert("业务名称不能为空！");
			return false;
		}
		w.saveCacheBusiness(parentId,$("#treeObj").serialize());
		return true;
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="treeObj" cssStyle="theForm">
	<s:hidden name="treeObj.parent_id" id="parent_id"></s:hidden>
	<s:hidden name="treeObj.server_type" id="server_type"></s:hidden>
	<s:hidden name="treeObj.busi_id" id="busi_id"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					业务名称：
				</td>
				<td><s:textfield name="treeObj.name" id="name" readonly="readonly" cssClass="inpt-2"></s:textfield>
					 <input type="button" value="选择" name="selectBmt" class="ubtn-3 vm mgl-3"/>
				</td>
			</tr>
		</table>
		</div>
	</s:form>
</body>