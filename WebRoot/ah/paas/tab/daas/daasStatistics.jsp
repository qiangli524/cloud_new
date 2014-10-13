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
		var id = $("#id").val();
		var node_type = $("#node_type").val();
		$("[name='showDB']").click(function(){
	     	$td=$(this).parent();
	        var entity_id=$td.attr("entity_id");
	        $.dialog({
	       		id:'show',
	       		title:'信息',
	       		height:'550px',
	       		width:'900px',
	       		lock:true,
	       		content:'url:paasTree_tabs.do?node_type=24&server_type=31'+'&entity_id='+entity_id
	       	});
		});
		
		 $("#title_oracle").click(function(){
 			if($("#item_oracle").is(":visible")){
 				$("#item_oracle").slideUp("slow");
 			}else{
 				$("#item_oracle").slideDown("slow");
 			}
 			
 			if($("#item_oracle1").is(":visible")){
 				$("#item_oracle1").slideUp("slow");
 			}else{
 				$("#item_oracle1").slideDown("slow");
 			}
 			
 			if($("#item_oracle2").is(":visible")){
 				$("#item_oracle2").slideUp("slow");
 			}else{
 				$("#item_oracle2").slideDown("slow");
 			}
 		});  
		
		 $("#title_mysql").click(function(){
	 			if($("#item_mysql").is(":visible")){
	 				$("#item_mysql").slideUp("slow");
	 			}else{
	 				$("#item_mysql").slideDown("slow");
	 			}
	 			
	 			if($("#item_mysql1").is(":visible")){
	 				$("#item_mysql1").slideUp("slow");
	 			}else{
	 				$("#item_mysql1").slideDown("slow");
	 			}
	 			
	 			if($("#item_mysql2").is(":visible")){
	 				$("#item_mysql2").slideUp("slow");
	 			}else{
	 				$("#item_mysql2").slideDown("slow");
	 			}
	 		});  
		//严重告警
    	$("[name='serious']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'严重告警',
    			width: '850px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=0&id='+id+'&node_type='+node_type
    			});
              });
    		//主要告警
            $("[name='serious1']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'主要告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=1&id='+id+'&node_type='+node_type
        			});
                  });
          //次要告警
            $("[name='serious2']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'次要告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=2&id='+id+'&node_type='+node_type
        			});
                  });
          //不确定告警
            $("[name='serious3']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'不确定告警',
        			width: '850px',
    				height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:paasAlarm_listPaasAlarm.do?obj.event_level=3&id='+id+'&node_type='+node_type
        			});
                  });
	});
	
</script>
</head>
<body>
	<s:hidden name="id" id="id"></s:hidden>
	<s:hidden name="node_type" id="node_type"></s:hidden>
	<s:hidden name="busiId" id="busiId"></s:hidden>
	<s:form action="" method="post"  id="theForm">
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;" >
		<div class="title" style="margin-top: 18px;cursor: pointer;" id='title_oracle'><h2 class="datacenter dc-tt mgt-15"><span class="txt"><s:property value="paasEntityObj.entity_name"/></span></h2></div>
		<div class="item" style="width: 97%;" id="item_oracle">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">数据库:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.dbCount_all"/></font></span>
	            		</li>		
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">数据库实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.instanceCount_all"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.normalCount_all"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.unusualCount_all"/></font></span>
	         			</li>
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">数据库服务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.serverCount_all"/></font></span>
	            		</li>		
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="paasEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width: 97%;" id="item_oracle1">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious"><s:property value="paasEntityObj.seriousAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious1"><s:property value="paasEntityObj.mainAlarmCount_all"/></a></font></span>
	            		</li>	
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious2"><s:property value="paasEntityObj.minorAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious3"><s:property value="paasEntityObj.otherAlarmCount_all"/></a></font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
		</div>
		<s:if test="paasEntityObj.entityIdList.size() != 0">
			<div class="item" style="width: 98%;" id="item_oracle2">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">数据库信息</font></div>
				</div>
				<div>
						<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
							<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
								<thead>
									<tr>
										<th width="25%;">数据库名称</th>
										<th>存储使用率</th>
										<th>版本</th>
										<th>所有者</th>
										<th>描述</th>
										<th>实例个数</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="paasEntityObj.entityIdList" id="theBean">
										<tr>
											<td style="padding-left: 60px;" entity_id="<s:property value='#theBean.entity_id'/>">
												<a href="#" name="showDB"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
											</td>
											<td align="center">
												<s:property value="" default="0"/>
											</td>
									  		<td align="center">
												<s:property value="#theBean.oracle_db_version" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.oracle_db_owner" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.oracle_db_desc" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.instanceCount" default="0"/>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					<div class="table-head">
					  <%-- <jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=theForm" /> --%>
					</div>
				</div>
			</div>
		</s:if>
	</div>
	<div class="mainboxBus" style="overflow-y: auto;width: 100%;" >
		<div class="title" style="margin-top: 18px;cursor: pointer;" id='title_mysql'><h2 class="datacenter dc-tt mgt-15"><span class="txt"><s:property value="mysqlEntityObj.entity_name"/></span></h2></div>
		<div class="item" style="width: 48%;" id="item_mysql">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">汇总数据</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">数据库实例:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.dbCount_all"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.normalCount_all"/></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.unusualCount_all"/></font></span>
	         			</li>
	     			</ul>  
	     			<ul class="dc-list">
						<li style="width: 150px;">
	            			<i class="zhuji"></i><span class="txt">业务:</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.busiCount"/></font></span>
	            		</li>		
	        			<li style="width: 100px;">
	            			<i class="running"></i><span class="txt">正常</span><span class="num blue-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.busi_normalCount"/></span></font></span>
	        			</li>
	        			<li style="width: 100px;">
	           				<i class="alert"></i><span class="txt">异常</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="mysqlEntityObj.busi_unusualCount"/></font></span>
	         			</li>
	     			</ul>  
				</div>
			</div>
		</div>
		<div class="item" style="width: 48%;" id="item_mysql1">
			<div class="top">
				<div class="ml5"><font color="orange" size="3">告警</font></div>
			</div>
			<div>
				<div class="right">
					<ul class="dc-list">
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">严重告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious"><s:property value="mysqlEntityObj.seriousAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">主要告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious1"><s:property value="mysqlEntityObj.mainAlarmCount_all"/></a></font></span>
	            		</li>	
						<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">次要告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious2"><s:property value="mysqlEntityObj.minorAlarmCount_all"/></a></font></span>
	            		</li>		
	            		<li style="width: 135px;">
	            			<i class="zhuji"></i><span class="txt">不确定告警:</span>
	            			<span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="serious3"><s:property value="mysqlEntityObj.otherAlarmCount_all"/></a></font></span>
	            		</li>	
	     			</ul>  
				</div>
			</div>
		</div>
		<s:if test="mysqlEntityObj.resultList.size() != 0">
			<div class="item" style="width: 98%;" id="item_mysql2">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">数据库信息</font></div>
				</div>
				<div>
						<div class="right"  style="width: 100%;margin-bottom: 5px;margin-top: 10px;">
							<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
								<thead>
									<tr>
										<th width="25%;">数据库名称</th>
										<th>存储使用率</th>
										<th>版本</th>
										<th>所有者</th>
										<th>描述</th>
										<th>实例个数</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="mysqlEntityObj.resultList" id="theBean">
										<tr>
											<td style="padding-left: 60px;" entity_id="<s:property value='#theBean.entity_id'/>">
												<a href="#" name="showDB"><span class="value"><s:property value="#theBean.entity_name"/></span></a>
											</td>
											<td align="center">
												<s:property value="" default="0"/>
											</td>
									  		<td align="center">
												<s:property value="#theBean.oracle_db_version" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.oracle_db_owner" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.oracle_db_desc" default="-"/>
											</td>
											<td align="center">
												<s:property value="#theBean.instanceCount" default="0"/>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					<div class="table-head">
					  <%-- <jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=theForm" /> --%>
					</div>
				</div>
			</div>
		</s:if>
	</div>
	<div class="mainboxservice" style="width: 100%;border: solid 0px #FFFFFF;">
		<div class="title" style="border: solid 0px #FFFFFF;" id='title'>
			<h2 class="datacenter dc-tt mgt-15"><span class="txt">GP</span></h2>
		</div>
	</div>
	</s:form>
</body>