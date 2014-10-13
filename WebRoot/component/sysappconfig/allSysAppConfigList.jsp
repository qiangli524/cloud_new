<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	$(function(){
		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
		$("#searchForm").click(function(){
			$("#theForm").submit();
		});
		
		$("#resetForm").click(function(){
			$("#appid").val("-1");
		});
		
		$("#addButton").click(function(){
			$.dialog({
				id:'addForm',
				title:'添加基准应用配置信息',
				width:'700px',
				height:'500px',
				max:false,
				min:false,
				content:'url:sysappconfig_insertSysAppConfig.do?oper=add'
			});
		});
		
		$("#editButton").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("您好：请至少选择一项来修改");
				return false;
			}
			var sysappconfigid = "";
			$(":checkbox:checked").each(function(){
				sysappconfigid += $(this).attr("sysappconfigid");
			});
			$.dialog({
        		id:'editdhcp',
        		title:'修改基准应用配置信息',
        		width: '700px',
    			height: '500px',
    			lock:true,
    		    content:'url:sysappconfig_updateSysAppConfig.do?sysappconfigid='+sysappconfigid+'&oper=edit'
        	});
		});
		
		$("#deleteButton").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("您好：请至少选择一项来删除！");
				return false;
			}
			var sysappconfigid = "";
			$(":checkbox:checked").each(function(){
				sysappconfigid += $(this).attr("sysappconfigid");
			});
			$.ajax({
				type:'post',
				url:'sysappconfig_deleteSysAppConfig.do?sysappconfigid='+sysappconfigid,
				success:function(msg){
					if (msg == -1) {
						alert("删除失败");
					} else {
						$("#theForm").submit();
					}
				}
			});
		});
	});

	$(function(){
		$check = $(":checkbox");
		$check.click(function(){
			$check.not(this).attr("checked",false);
		});
	})
	
	function saveTheForm(theForm){
		$.ajax({
			type:'post',
			url:'sysappconfig_saveSysAppConfig.do?'+theForm,
			success:function(msg){
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
</script>
</head>
<body>
	<s:form action="sysappconfig_listMappings.do" method="post" id="theForm" cssStyle="theForm">
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">基准应用环境监测管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
					<label class="mgl-20 vm">基准应用名称:</label>
								<s:select cssClass="select-1 vm" list="appList" listKey="id" listValue="appname" name="sysAppConfigObj.CONFIG_ID" id="appid" headerKey="-1" headerValue="请选择"></s:select>
							<span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input id="resetForm" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
				
			<div class="utt-2 mgt-20">
				<a id="addButton" class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a id="editButton" class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a id="deleteButton" class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th onclick="sort(theTable,1,'string')">基准应用名称</th>
									<th onclick="sort(theTable,2,'string')">操作系统要求</th>
									<th onclick="sort(theTable,3,'string')">JDK要求</th>
									<th onclick="sort(theTable,4,'string')">用户要求</th>
									<th onclick="sort(theTable,5,'string')">中间件要求</th>
									<th onclick="sort(theTable,6,'string')">CPU利用率要求</th>
									<th onclick="sort(theTable,7,'string')">内存利用率要求</th>
									<th onclick="sort(theTable,8,'string')">存储利用率要求</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
											<td>
												<input type="checkbox" name="checkboxid" sysappconfigid='<s:property value="#theBean.CONFIG_ID"/>'/>
											</td>
											<td>
												<s:property value="#theBean.APPNAME"/>
											</td>
											<td><s:property value="#theBean.SYS_ORDER" />
											</td>
											<td><s:property value="#theBean.JDK_ORDER"/>
											</td>
											<td><s:property value="#theBean.USER_ORDER" />
											</td>
											<td><s:property value="#theBean.MIDDLEWARE_ORDER" />
											</td>
											<td><s:property value="#theBean.CPUUSE_ORDER" />
											</td>
											<td><s:property value="#theBean.MEMORYUSE_ORDER" />
											</td>
											<td><s:property value="#theBean.HEAPUSE_ORDER" />
											</td>
									</tr>
				  				</s:iterator>
							</tbody>
						</table>
						<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>