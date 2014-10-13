<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>



<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
<title></title>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
	
	$(function(){
		var api = frameElement.api;
		var w = api.opener;
		api.button({
			id:'OkAnd',
		     name: '添加',
		     callback:addScriptForProcess,
		     focus: true
		},{
			id:'cancle',
		     name: '取消'
		});
		
		function addScriptForProcess(){
			if($(":checkbox:checked").length!=1){
	      		 alert("只能选择一项.");
	      		 return false;
	      	}
			var params = $(":checkbox:checked").attr("path")+" "+$(":checkbox:checked").attr("params");
			var script = $("#script").val();
			api.get("add").addScript(params,script);
		}
	});
	
	
	
	$(function(){
		$("#searchForm").click(function(){
			$("#theForm").submit();
		});
		$("#resetForm").click(function(){
			$("#type").attr("value","-1");
			$("#name").val(null);
			$("#ip").attr("value","");
			$("#description").attr("value","");
		});
	});
     
</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
<s:form action="script_list.do" method="post" id="theForm" cssStyle="theForm">
<s:hidden name="oper" id="oper"></s:hidden>
<s:hidden name="script" id="script"></s:hidden>
<div class="scrollbody">
	<div class="pd-20 bgcolor-1">
       	<div class="bord-1 pd-10">
			<table width="100%"  border="0">
				<tr>
					<td class="til">脚本名称:</td>
					<td>
						<s:textfield name="theForm.name" id="name" size="15"></s:textfield>
					</td>
					<td class="til">主机:</td>
					<td>
						<s:textfield name="theForm.ip" id="ip" size="15"></s:textfield>
					</td>
					<td class="til">描述:</td>
					<td>
						<s:textfield name="theForm.description" id="description" size="15"></s:textfield>
					</td>
					<td class="til">类型:</td>
					<td>
						<s:select list="#{'0':'通用','1':'启用','2':'停止','3':'备份','4':'其他'}" name="theForm.type" id="type" headerKey="-1" headerValue="--请选择--" cssClass="select-1 vm"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="10" class="pd-10">
						<div align="center">
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" /></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" /></span>
						</div>
					</td>
				</tr>
			</table>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  		<tr>
			  			<th>选择</th>
			  			<th onclick="sort(theTable,1,'string')">脚本名称</th>
			  			<th onclick="sort(theTable,2,'string')">主机/用户</th>
			  			<th onclick="sort(theTable,3,'string')">类型</th>
			  			<th onclick="sort(theTable,4,'string')">脚本路径</th>
			  			<th onclick="sort(theTable,5,'string')">参数</th>
			  			<th onclick="sort(theTable,7,'string')">描述</th>
			  		</tr>
			  </thead>
			  <tbody>
			  		<s:iterator value="theForm.resultList" id="theBean">
			  			<tr>
			  				<td> <input type="checkbox"   value="<s:property value='#theBean.id'/>" name="theForm.id" path='<s:property value="#theBean.path"/>'
			  					params='<s:property value="#theBean.params"/>'/> </td>
			  				<td> <s:property value="#theBean.name"/></td>
			  				<td> <s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
			  				<td>
			  					<s:if test="#theBean.type==0">通用</s:if>
			  					<s:elseif test="#theBean.type==1">启动</s:elseif>
			  					<s:elseif test="#theBean.type==2">停止</s:elseif>
			  					<s:elseif test="#theBean.type==3">备份</s:elseif>
			  					<s:else>其他</s:else>
			  				</td>
			  				<td> <s:property value="#theBean.path"/> </td>
			  				<td> <s:property value="#theBean.params"/></td>
			  				<td> <s:property value="#theBean.description"/> </td>
			  			</tr>
			  		</s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
