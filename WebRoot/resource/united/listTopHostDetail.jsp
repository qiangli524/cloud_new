<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<style type="text/css">
	.percentage { 
	vertical-align:middle;  
	display:inline-block; 
	background: url(sxcloud/images/nresources/greyp.gif) no-repeat; 
	width:73px; 
	height:12px; 
	margin-top: 4px;}
	.percentage b{ display:block; height:14px; background:url(sxcloud/images/nresources/greenp.gif) no-repeat; }
	.percentage b.red {background:url(sxcloud/images/nresources/redp.gif) no-repeat;}
</style>
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
			$("#eqName").val(null);
			$("#ipaddress").val(null);
		});
	});
</script>
</head>
<body>
	<s:form action="resourceOutline_listHostTopNDetail.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
		                  <tr>
		                  	<td class="til">主机名称:</td>
		                  	<td>
		                  		<s:textfield name="topTargetObj.name" id="eqName"></s:textfield> 
		                  	</td>
		                  	<td class="til">主机IP:</td>
		                  	<td>
		                  		<s:textfield name="topTargetObj.ip" id="ipaddress"></s:textfield> 
		                  	</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>主机名称</th>
									<th>主机IP</th>
									<th>cpu利用率</th>
									<th>内存利用率</th>
									<th>存储利用率</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="globalList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.name"/>
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<div class="percentage">
												<s:if test="#theBean.cpu_usage > 50">
													<b class="red" style='width: <s:property value="#theBean.cpu_usage"/>%'>
													</b>
												</s:if>
												<s:else>
													<b style='width: <s:property value="#theBean.cpu_usage"/>%'>
													</b>
												</s:else>
											</div>
											<s:property value="#theBean.cpu_usage"/>%
										</td>
										<td>
											<div class="percentage">
												<s:if test="#theBean.mem_usage > 75">
													<b class="red" style='width: <s:property value="#theBean.mem_usage"/>%'>
													</b>
												</s:if>
												<s:else>
													<b style='width: <s:property value="#theBean.mem_usage"/>%'>
													</b>
												</s:else>
											</div>
											<s:property value="#theBean.mem_usage"/>%
										</td>
										<td>
											<div class="percentage">
												<s:if test="#theBean.store_usage > 75">
													<b class="red" style='width: <s:property value="#theBean.store_usage"/>%'>
													</b>
												</s:if>
												<s:else>
													<b style='width: <s:property value="#theBean.store_usage"/>%'>
													</b>
												</s:else>
											</div>
											<s:property value="#theBean.store_usage"/>%
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
</body>