<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/ah/paas/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
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
		
		$("[name='showDB']").click(function(){
	     	$td=$(this).parent();
	        var entity_id=$td.attr("entity_id");
	        $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'800px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=31'+'&entity_id='+entity_id
	       	});
		});
		
		/* $("#title").click(function(){
    			if($(".item").is(":visible")){
    				$(".item").slideUp("slow");
    			}else{
    				$(".item").slideDown("slow");
    			}
    		}); */
	});
	
</script>
</head>
<body>
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="node_type" id="node_type"></s:hidden>
	<s:hidden name="busiId" id="busiId"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;">
		<div class="title" style="margin-top: 18px;/* cursor: pointer; */" id='title'><h2 class="datacenter dc-tt mgt-15"><span class="txt">CAAS</span></h2></div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;">1<s:property value="paasEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;">1<s:property value="paasEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;">0<s:property value="paasEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
		</div>
	</div>
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;">
		<div class="title" style="margin-top: 18px;/* cursor: pointer; */" id='title'><h2 class="datacenter dc-tt mgt-15"><span class="txt">DAAS</span></h2></div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据库:</span><span class="num blue-num"><font style="font-weight:bold;">1<s:property value="paasEntityObj.dbCount_all"/></font></span>
	            		</li>		
	     			</ul>  
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据库实例:</span><span class="num blue-num"><font style="font-weight:bold;">1<s:property value="paasEntityObj.instanceCount_all"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;">1<s:property value="paasEntityObj.normalCount_all"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;">0<s:property value="paasEntityObj.unusualCount_all"/></font></span>
	         			</li>
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据库服务:</span><span class="num blue-num"><font style="font-weight:bold;">10<s:property value="paasEntityObj.serverCount_all"/></font></span>
	            		</li>		
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;">2<s:property value="paasEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;">2<s:property value="paasEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;">0<s:property value="paasEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width: 48%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
		</div>
	</div>
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;">
		<div class="title" style="margin-top: 18px;" id='title'>
			<h2 class="datacenter dc-tt mgt-15"><span class="txt">Maas</span></h2>
		</div>
		<div class="item" style="width: 46.5%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">数据源:</span><span class="num blue-num"><font style="font-weight:bold;">2<s:property value="paasEntityObj.dsCount_all"/></font></span>
	            		</li>		
	     			</ul> 
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">实例:</span><span class="num blue-num"><font style="font-weight:bold;">5<s:property value="paasEntityObj.instanceCount_all"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;">5<s:property value="paasEntityObj.normalCount_all"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;">0<s:property value="paasEntityObj.unusualCount_all"/></font></span>
	         			</li>
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;">2<s:property value="paasEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;">2<s:property value="paasEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;">0<s:property value="paasEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>
				</div>
			</div>
		</div>
		<div class="item" style="width: 46.5%;">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span><span class="num blue-num"><font style="font-weight:bold;">0</font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
			<div style="height: 98px;"></div>
		</div>
	</div>
	</s:form>
</body>