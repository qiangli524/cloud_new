<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
<script type="text/javascript">
	$(function(){
		var resourceType = $("#resourceType").val();
		initPie(resourceType);
	});
	
	function initPie(resourceType){
		if (resourceType == 3) {//存储
			document.getElementById("choice").style.display="none";
			showTopN(3);
		} else {//默认cpu
			showTopN(1);
		}
	}
	
	function showTopN(num){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'resourceOutline_showTopN.do?resourceType='+num+'&dd='+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Column3D.swf","ChartId1","100%","300","0","0");
				chart1.setJSONData(msg);
				chart1.render("topchart");
			}
		});
	}
	
	$(function(){
		$("#more").click(function(){
			$("#theForm").submit();
		});
	});
</script>
</head>

<body class="pop-body scrollbody" >
	<s:form action="entityTopN_topTabs.do" method="post" id="theForm" cssStyle="theForm"> 
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
		<div id="choice">
			<a href="javascript:;" onclick="showTopN(1)" id="cpu">cpu维度</a>
			<a href="javascript:;" onclick="showTopN(2)" id="mem">内存维度</a>
		</div>
		<div id="topchart">
		</div>
		<div style="float: right;">
			<a href="javascript:;" id="more">
				更多：
			</a>
		</div>
	</s:form>
</body>