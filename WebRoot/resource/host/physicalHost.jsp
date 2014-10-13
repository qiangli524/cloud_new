<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<script type="text/javascript">
	    //进入主机管理界面
		function hostManagement(){
			$.dialog({
	 			id:'management',
	 			title:'管理',
	 			width: '800px',
	 			height: '450px',
	 			lock:true,
	 			content: 'url:phyHost_hostManagement.do'
	 	 	});		
		}
		$(function(){
			$('[name="startup"]').click(function(){
				var id = $(this).attr('id');
				$.getJSON('phyHost_startUpHost.do?id='+id,{'time':new Date().toString()},function(data){
					alert(data);
				});
			});
			$('[name="shutdown"]').click(function(){
				var id = $(this).attr('id');
				$.getJSON('phyHost_shutDownHost.do?id='+id,{'time':new Date().toString()},function(data){
					alert(data);
				});
			});
		});
	</script>
</head>
<body>
	<s:form action="ipmi_listIPMIInfo" method="post" cssClass="theForm" id="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" /></li>
							<li><input type="button" class="thickbox btn-style02" value="管理" onclick="hostManagement();"/> </li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th>主机</th>
									<th>IPMI用户</th>
									<th>用户密码</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="top" id="theBean">
									<tr>
										<td>
											<input type="checkbox" value="#theBean.ip"/>
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<s:property value="#theBean.user"/>
										</td>
										<td>
											<s:property value="#theBean.pwd"/>
										</td>
										<td>
											<a href="javascript:;"  style="text-decoration:underline;color:#CC3300;" name="startup" id="<s:property value="#theBean.id"/>">上电</a>
											&nbsp;&nbsp;
											<a href="javascript:;"  style="text-decoration:underline;color:#CC3300;" name="shutdown" id="<s:property value="#theBean.id"/>"> 下电</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
