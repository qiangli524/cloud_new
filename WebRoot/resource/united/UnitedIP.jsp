<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
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
  <link type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css" rel="stylesheet">
  <script type="text/javascript" src="<%=request.getContextPath()%>/temp1/pub-ui/js/plugin/table.js"></script>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	$(function(){
		$("#more").click(function(){
			$.dialog({
				id:'net',
				title:'更多',
				width:'1200px',
				height:'800px',
				lock:true,
				zIndex:1975,
				content:'url:net_listNetInfo.do'
			});
		});
	});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
		<div class="scrollbody">
			<div class="tab-content">
				<div class="tab-pane">
					<div class="pdt-10">
						<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab">
							<thead>
								<tr>
									<th>网络域</th>
									<th>IP总量(个)</th>
									<th>已分配(个)</th>
									<th>分配率</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="netList" id="theBean">
									<tr style="height: 20px;">
										<td align="center">
											<s:property value="#theBean.netName"/>
										</td>
										<td align="center">
												<s:property value="#theBean.allCount"/>
										</td>
										<td align="center">
											<s:property value="#theBean.usedCount"/>
										</td>
										<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<div class="percentage">
												<b style='width: <s:property value="@java.lang.Math@round((#theBean.usedCount*100*100/#theBean.allCount))/100.0"/>%'>
												</b>
											</div>
											<fmt:formatNumber value="${((theBean.usedCount )* 100 * 100 /(theBean.allCount) ) / 100.0}" pattern="#,###.##" type="number"/>%
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div style="float: right">
			<a href="javascript:;" id="more">更多网络资源</a>
		</div>
	</s:form>
</body>
