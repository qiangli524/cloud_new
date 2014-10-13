<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控制中心</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" />
<link href="<%=request.getContextPath()%>/publicCloud/pub-ui/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/js/My97DatePicker/skin/WdatePicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
</style>
</head>
<script type="text/javascript">
$(function(){

	 //检索
	  $("#search-btn").click(function(){
	  			$("#theForm").submit();
	  });
	 
})	
</script>
<body>
	<!--container star-->
	<div class="container">
		<!--col-c7 star-->
		<div class="col-c7">
			<!--left star-->
			<jsp:include page="/publicCloud/pub-page/order.jsp" />
			<!--left end-->
			<!--main-c1 star-->
			<div class="main-c1 fr">
				<!--标题 star-->
				<h2 class="title-common6">
					<a href="#" class="home"></a><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif" width="65" height="65" />操作日志
				</h2>
				<!--标题 end-->
				<div class="box on" style="display: block;">
					<p class="title-common8">日志列表</p>
					<div class="border-2 pd-10 clearfix">
						<form id="theForm" name ="theForm" action="<%=request.getContextPath() %>/sysUserLog_logList.do?type=<s:property value="type"/>" method="post">
							<p class="fl mgr-5">
							    &nbsp;&nbsp;&nbsp;
								操作类型：&nbsp;&nbsp;&nbsp;
								      <s:select list="#request.operationTypeMap "  style="height: 25px;"  listKey="key" listValue="value" name="operationType"></s:select>  
							</p>
							<p class="fl mgr-5">&nbsp;
									开始时间： <input type="text" id="search_starttime" name="operationStartDate"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"style="width: 160;" class="Wdate input-1c" value="">
									&nbsp;&nbsp;&nbsp;
									结束时间： <input type="text" id="search_endtime" name="operationEndDate" readonly="readonly"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width: 160;" class="Wdate input-1c" value="">
							</p>
							<p class="fl mgr-5">
								&nbsp;&nbsp;&nbsp;
								操作状态： &nbsp;&nbsp;&nbsp;
								<s:select list="#request.operationResultMap "  style="height: 25px;"  listKey="key" listValue="value" name="operationResult"></s:select>  
							</p>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="search-btn" style="cursor: pointer"  class="btn-w4"><span>查询</span></a>
						</form>

					</div>
				</div>
				<!--tab-content star-->
				<div class="tab-content border-2 pdtb-10">
					<!--box star-->
					<div class="box" style="display: block;">
					<form id="theListForm" action="sysUserLog_logList.do?type=<s:property value="type"/>" method="post">
						<table border="0" width="100%" class="table-f4 js_table_f4">
							<tr>
								<th>操作类型</th>
								<th>操作人</th>
								<th>操作对象</th>
								<th>操作内容</th>
								<th>操作结果</th>
								<th>操作开始时间</th>
								<th>操作结束时间</th>
							</tr>
							<s:iterator value="resultList" id="theBean">
								<tr>
									<td><span class="font-blue"><s:property value="#theBean.operationType" /></span></td>
									<td><span class="font-blue"><s:property value="#theBean.operatorName" /></span></td>
									<td><span class="font-blue"><s:property value="#theBean.operationObject" /></span></td>
									<td><span class="font-blue"><s:property value="#theBean.operationContent" /></span></td>
									<td><span class="font-blue"> <s:if test="#theBean.operationResult==1">成功</s:if> <s:else>失败</s:else>
									</span></td>
									<td><span class="font-blue"><s:date name="#theBean.operationStartDate" format="yyyy-MM-dd hh:mm:ss" /></span></td>
									<td><span class="font-blue"><s:date name="#theBean.operationEndDate" format="yyyy-MM-dd hh:mm:ss" /></span></td>
									</td>
								</tr>
							</s:iterator>
						</table>
						<div class="page mgt34-b25">
							<jsp:include page="../../inc/fenye.jsp?formId=theListForm" />
						</div>
						</form>
					</div>
				</div>
			</div>
			<!--main-c1 end-->
			<div class="clear"></div>
		</div>
		<!--col-c7 end-->
	</div>
	<!--container end-->
	<!--版权 star-->
	<div class="copy">
		<div class="copy-con ac">
			<p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号 增值电信业务经营许可证A2.B1.B2-20090001</p>
		</div>
	</div>
	<!--版权 end-->
</body>
</html>
