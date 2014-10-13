<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
</head>
<script type="text/javascript">
//parent.removeMask();
$(function(){
	//收起标题
	$("#title").click(function(){
    	if($(".item").is(":visible")){
    				$(".item").slideUp("slow");
    			}else{
    				$(".item").slideDown("slow");
    			}
    		});
    //展示zookeeper详细信息		
    $("a[name='zookDetail']").click(function(){
    	$.dialog({
	 		id:'vm_motion',
	 		title:'Zookeeper监控',
	 		width: '800px',
	 		height: '500px',
	 		lock:true,
	 		content: 'url:zookeeperMonitor_monitorTree.do'			
	 	 });
    });
});
	
</script>
<body class="pop-body scrollbody">
		<s:hidden name="obj.uuid"></s:hidden>
		<s:hidden name="obj.node_type"></s:hidden>
		<div class="mainboxBus" style="width: 100%">
			<div class="title" id='title' style="margin-top: 18px;"><font color="blue" size="4" style="margin-top: 18px;">子系统个数:&nbsp;&nbsp;&nbsp;<s:property value="systemNum"/></font></div>
			<s:iterator value="systemTreeObjList" id="theBean">
				<div class="item" style="width: 97%;height: 108px;">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3"><s:property value="#theBean.name"/></font></div>
	  			</div>
	  			<div>
		  			<div class="left testimg"></div>
		  			<div class="right" style="padding-top: 20px;">
		  				业务系统数：&nbsp;<span class="value"><s:property value="#theBean.busiNum"/></span>个
		  			</div>
	  			</div>
	  			</div>
			</s:iterator>
		</div>
</body>