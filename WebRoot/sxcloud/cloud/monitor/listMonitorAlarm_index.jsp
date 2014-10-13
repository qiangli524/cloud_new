<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<style type="text/css">
td { border:1px dashed #87CEEB }
.txt1{height:20px;line-height:20px;border:none; border:1px solid #9dbcd9}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	function searchRequest(){
		theForm.submit();
	}
	
	function resetForm(theForm){
		theForm.EVENT_TYPE.value = '';
		theForm.EVENT_LEVEL.value = '';
		theForm.EVENT_STAT.value = '';
		theForm.datepicker1.value ='';
		theForm.EVENT_LOCATION.value ='';
//		theForm.INS_DATE.value ='';
		theForm.TITILE.value = '';
		theForm.CONTENT.value = '';
	}
	function dealAlarmRequest(){
		var couterNum = 0;
		var flag = 0 ; //0表示未处理1表示已处理
		var flag1 = 0;
   		var checkboxids = $("input[name='checkboxid']");
  		var subNum = '';
   		if(checkboxids!=null&&checkboxids.length>0){
     		for(var i=0;i<checkboxids.length;i++){
       			if(checkboxids[i].checked){
       				couterNum = couterNum + 1 ;
		   			subNum += checkboxids[i].value + ",";
		   			if(checkboxids[i].stu == 1){
		   				flag = 1;
		   				if(flag1 == 0){
			   				if(confirm("存在已处理告警,是否取消选择?")){
			   					flag1 = 1;
				   				checkboxids[i].checked = false;
			   				}else{
			   					flag1 = 2;//点击取消时执行
			   					checkboxids[i].checked = false;
			   				}
		   				}else{
		   					checkboxids[i].checked = false;
		   					if(flag1 == 1){
		   						alert("已取消已处理告警");
		   					}
		   				}
     			  }
    		}
		}
		theForm.EVENT_ID.value = subNum;
		if(couterNum==0){
			alert("请选择要处理的告警信息");
			return false;
		}
		if(flag == 0){
			if(confirm("请确定是否处理该告警?")){
				theForm.action = 'alarm_dealMonitorAlarm_index.do';
				theForm.submit();
			}else{
				for(var i=0;i<checkboxids.length;i++){
					checkboxids[i].checked = false;
				}
			}
		}
	}}
	function hisAlarmRequest(){
		var couterNum = 0;
   		var checkboxids = $("input[name='checkboxid']");
  		var subNum = '';
   		if(checkboxids!=null&&checkboxids.length>0){
     		for(var i=0;i<checkboxids.length;i++){
       			if(checkboxids[i].checked){
       				couterNum = couterNum + 1 ;
		   			subNum += checkboxids[i].value + ",";
     			}
    		}
		}
		theForm.EVENT_ID.value = subNum;
		if(couterNum==0){
			alert("请选择要放入历史的告警信息");
			return false;
		}
		if(confirm("请确定是否消除该告警?")){
			theForm.action = 'alarm_hisMonitorAlarm_index.do';
		    theForm.submit();
		}else{
			for(var i=0;i<checkboxids.length;i++){
				checkboxids[i].checked = false;
			}
		}
	}
	
	$(function(){
		$("a[name='location']").click(function(){
			var entityId=$(this).attr("entity_id");
			var entityType=$(this).attr("entity_type");
			if(1==entityType){
	    		$.dialog({
	    			id:'vmList',
	    			title:'虚拟机定位信息',
	    			width: '600px',
					height: '300px',
	    		    zIndex:'1975',
	    		    lock : true,
//	    			content: 'url:showvm_listvm.do?entityId='+entityId+'&entityType=alarm'
	    			content: 'url:alarm_getLocationInfo.do?entityId='+entityId+'&entityType='+entityType
	    			});
			}else if(2==entityType){
				$.dialog({
	    			id:'hostList',
	    			title:'主机定位信息',
	    			width: '600px',
					height: '200px',
	    		    zIndex:'1975',
	    		    lock : true,
//	    		    content: 'url:resource_allHostList.do?entityId='+entityId+'&entityType=alarm'
	    		    content: 'url:alarm_getLocationInfo.do?entityId='+entityId+'&entityType='+entityType
	    			});
			}else if(-1==entityType){
				$.dialog({
	    			id:'hostList',
	    			title:'存储定位信息',
	    			width: '600px',
					height: '150px',
	    		    zIndex:'1975',
	    		    lock : true,
//	    		    content: 'url:resource_allHostList.do?entityId='+entityId+'&entityType=alarm'
	    		    content: 'url:alarm_getLocationInfo.do?entityId='+entityId+'&entityType='+entityType
	    			});
			}else{
				alert("抱歉,暂不支持此实体的定位,请联系管理员.");
			}
			
			
		});
	});
</script>
</head>
<body>
<s:form action="alarm_listMonitorAlarm.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.EVENT_ID" id="EVENT_ID"></s:hidden>
	<div class="utt-2 mgt-20">
		<a class="icon-add" href="javascript:void(0)" onclick="javascript:dealAlarmRequest();return false;">告警处理</a>
		<a class="icon-modify" href="javascript:void(0)" onclick="javascript:hisAlarmRequest();return false;" >告警消除</a>
	</div>
	<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	  <thead>
	  <tr>	
	  		<th>选择</th>
			<th>级别</th>
			<th onclick="sort(theTable,1,'string')">告警编号</th>
			<th onclick="sort(theTable,2,'string')">告警标题</th> 
			<th onclick="sort(theTable,3,'string')">告警类型</th> 
			<th onclick="sort(theTable,4,'string')">告警内容</th>
			<th onclick="sort(theTable,5,'string')">告警级别</th>
			<th onclick="sort(theTable,6,'string')">当前状态</th>
			<th onclick="sort(theTable,7,'int')">告警次数</th>
			<th onclick="sort(theTable,8,'string')">告警位置</th>
			<th onclick="sort(theTable,9,'date')">告警产生时间</th>
	  </tr>
	  </thead>
	  <tbody>
	  <s:iterator value="theForm.resultList" id="theBean">
	  	<tr>
	  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.EVENT_ID"/>" stu="<s:property value='#theBean.EVENT_STAT' default='0'/>"/></td>
	  		<td>
	  		    <s:if test="#theBean.EVENT_LEVEL==0">
	  		       <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm1.png" width="16" height="16" state="1" />
	  		    </s:if>
	  			<s:elseif test="#theBean.EVENT_LEVEL==1">
	                        <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm2.png" width="16" height="16" state="1" />
	                     </s:elseif>
	  			<s:elseif test="#theBean.EVENT_LEVEL==2">
	  			   <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm3.png" width="16" height="16" state="1" />
	  			</s:elseif>
	  			<s:elseif test="#theBean.EVENT_LEVEL==3">
	  			   <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm4.png" width="16" height="16" state="1" />
	  			</s:elseif>
	                 </td>
	        <td><s:property value="#theBean.EVENT_NUM"/></td>
	  		<td>
	  			<span class="js_ByEllipsis" title="<s:property value='#theBean.TITILE'/>"><s:property value="#theBean.TITILE"/></span>
	  		</td>
	  		<td>
	  			<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
	  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==4">进程告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==5">BOMC工单告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==6">HADOOP告警</s:elseif>
	  			<s:elseif test="#theBean.EVENT_TYPE==7">资源池告警</s:elseif>
	  		</td>
	  		<td>
	  			<span class="js_ByEllipsis" title="<s:property value='#theBean.CONTENT'/>"><s:property value="#theBean.CONTENT"/></span>
	  		</td>
		  		<td>
			  			<s:if test="#theBean.EVENT_LEVEL==0"><span style="color:red;">严重告警<span/></s:if>
			  			<s:elseif test="#theBean.EVENT_LEVEL==1"><span style="color:blue;">重要告警<span/></s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==2"><span style="color:green;">一般告警<span/></s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.EVENT_STAT==0">未处理</s:if>
			  			<s:elseif test="#theBean.EVENT_STAT==1">已处理</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.EVENT_COUNT" default="1"/></td>
			  		<td>
			  		<a href="javascript:;" entity_id='<s:property value="#theBean.ENTITY_ID"/>' entity_type='<s:property value="#theBean.EVENT_TYPE"/>' style="text-decoration:underline" name="location" ><s:property value="#theBean.EVENT_LOCATION"/></a>
			  		</td>
			  		<td><s:property value="#theBean.ALARM_TIME" default="无"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
</s:form>
</body>
