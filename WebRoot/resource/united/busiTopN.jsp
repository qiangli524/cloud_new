<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
<script type="text/javascript">
	$(function(){
		var resourceType = $("#resourceType").val();
		initPie(resourceType);
	});
	
	function initPie(resourceType){
		if (resourceType == 2) {//存储
			showTopN(2);
		} else {//默认虚拟机
			showTopN(1);
		}
	}
	
	function showTopN(num){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'resourceOutline_showBusiTopN.do?resourceType='+num+'&dd='+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Pie2D.swf","ChartId1","100%","270","0","0");
				chart1.setJSONData(msg);
				chart1.render("topchart");
			}
		});
	}
	
	$(function(){
		$("#more").click(function(){
			var resourceType = $("#resourceType").val();
			$.dialog({
				id:'detail',
				title:'详细',
				width:'1000px',
				height:'550px',
				content:'url:resourceOutline_goToBusiDetailTopN.do?resourceType='+resourceType
			});
		});
	});
</script>
</head>

<body class="pop-body scrollbody" >
	<s:form action="" method="post" id="theForm" cssStyle="theForm"> 
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
		<div id="topchart">
		</div>
<%--		<div style="float: right;">--%>
<%--			<a href="javascript:;" id="more" style="color: blue;font-size: 12px">--%>
<%--				更多..--%>
<%--			</a>--%>
<%--		</div>--%>
	</s:form>
</body>