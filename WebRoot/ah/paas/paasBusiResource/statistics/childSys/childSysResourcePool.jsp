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
	 		id:'zook_motion',
	 		title:'Zookeeper监控',
	 		width: '800px',
	 		height: '500px',
	 		lock:true,
	 		content: 'url:paasBusiStatistics_zookeeperMonDetail.do'			
	 	 });
    });
});
	
</script>
<body class="pop-body scrollbody">
		<s:hidden name="obj.uuid"></s:hidden>
		<s:hidden name="obj.node_type"></s:hidden>
		<div class="mainboxBus" style="width: 100%">
			<div class="title" id='title' style="margin-top: 18px;"><font color="blue" size="4" style="margin-top: 18px;">关联主机信息</font></div>
			<div class="item" style="width: 47.1%;height: 108px;">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">主机</font></div>
	  			</div>
	  			<div style="padding-top: 2px;text-align: center;">
		  			<table style="text-align: center;">
	  					<tr>
	  						<td><div class="leftt hostimg" style="margin-top: 7px;"></div></td>
	  						<td>物理主机</td>&nbsp;
	  						<td width="30%"><span class="value"><s:property value="hostsNum" />&nbsp;</span>个</td>
	  						<td><div class="leftt vmimg" style="margin-top: 7px;"></td>
	  						<td>虚拟机</td>&nbsp;
	  						<td width="30%"><span class="value"><s:property value="vmhostsNum"/>&nbsp;</span>个</td>
	  					</tr>
	  				</table>
	  			</div>
	  		</div>	
	  		<div class="item" style="width: 47.1%">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">告警</font></div>
	  			</div>
	  			<div>
		  			<div class="right" style="padding: 10px 10px;">
		  				<table>
						<tr style="height: 30px;">
							<td width="100px;">
								<div>
									<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm1.png" width="16" height="16" state="1" style="vertical-align: middle;"/>
									&nbsp;严重告警:
								</div>
							</td>
							<td width="50px;">
								<%-- <s:if test="paasEntityObj.seriousAlarmCount_all == 0"> --%>
									<span class="value">0</span>
								<%-- </s:if>
								<s:else>
									<div><a href="javascript:;" name="serious"><span class="value"><s:property value="paasEntityObj.seriousAlarmCount_all"/></span></a></div>
								</s:else> --%>
							</td>
							<td width="110px;">
								<div>
									<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm2.png" width="16" height="16" state="1" style="vertical-align: middle;"/>
					            	&nbsp;主要告警:
					            </div>
							</td>
							<td>
								<%-- <s:if test="paasEntityObj.mainAlarmCount_all == 0"> --%>
									<span class="value">0</span>
								<%-- </s:if>
								<s:else>
									<div><a href="javascript:;" name="serious1"><span class="value"><s:property value="paasEntityObj.mainAlarmCount_all"/></span></a></div>
								</s:else> --%>
							</td>
						</tr>
						<tr style="height: 30px;">
							<td>
								<div>
									<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm3.png" width="16" height="16" state="1" style="vertical-align: middle;" />
           							 &nbsp;次要告警:
								</div>
							</td>
							<td>
								<%-- <s:if test="paasEntityObj.minorAlarmCount_all == 0"> --%>
									<span class="value">0</span>
								<%-- </s:if>
								<s:else>
									<div><a href="javascript:;" name="serious2"><span class="value"><s:property value="paasEntityObj.minorAlarmCount_all"/></span></a></div>
								</s:else> --%>
							</td>
							<td>
								<div>
									<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm4.png" width="16" height="16" state="1" style="vertical-align: middle;"/>
            						&nbsp;不确定告警: 
								</div>
							</td>
							<td>
								<%-- <s:if test="paasEntityObj.otherAlarmCount_all == 0"> --%>
									<span class="value">0</span>
								<%-- </s:if>
								<s:else>
									<div><a href="javascript:;" name="serious3"><span class="value"><s:property value="paasEntityObj.otherAlarmCount_all"/></span></a></div>
								</s:else> --%>
							</td>
						</tr>
					</table>
		  			</div>
	  			</div>
	  		</div>
		</div>
		<div class="mainboxBus" style="width: 100%;float: left;">
			<div class="title"  style="margin-top: 18px;"><font color="blue" size="4" style="margin-top: 18px;">Zookeeper</font></div>
			<div class="item" style="width: 97.3%">
	  			<div class="top">
	  				<div class="ml5"><font color="orange" size="3">zookeeper</font></div>
	  			</div>
	  			<div>
		  			<div class="right" style="padding: 10px 10px;">
		  				<img src="<%=request.getContextPath()%>/hadoop/statistics/css/run.png" style="vertical-align: middle;"/>&nbsp;正常：<font class="value"><s:property value="zookNormalNum"/></font>个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  				<img src="<%=request.getContextPath()%>/hadoop/statistics/css/stop.png" style="vertical-align: middle;"/>&nbsp;异常：
		  				<s:if test="zookAbNormalNum == 0">
		  					<span class="value">0</span>个
		  				</s:if>
		  				<s:else>
		  					<a href="#" name="zookDetail"><font color="red"><s:property value="zookAbNormalNum"/></font></a>个
		  				</s:else>
		  				
		  			</div>
	  			</div>
	  		</div>	
	  	</div>
</body>