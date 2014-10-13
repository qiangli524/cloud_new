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
	
	$(function(){
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		showTopN(1);
	})
	
	//初始化ENENT_TYPE下拉框
	$(document).ready(function(){
		var eventSys = $("#EVENT_SYS").val();// 告警系统
		var eventTypeTemp = $("#eventTypeTemp").val();// 告警类型
		if(eventSys != ''){
			var eventTypes=document.getElementById("EVENT_TYPE");
			eventTypes.options.length=0;
			eventTypes.add(new Option("-请选择-",""));
			eventTypes.add(new Option("话费量告警","21"));
			eventTypes.add(new Option("目录文件积压量告警","22"));
			eventTypes.add(new Option("错误日志告警","23"));
			eventTypes.add(new Option("流量查询服务告警","24"));
			eventTypes.add(new Option("端口收发告警","25"));
			eventTypes.add(new Option("提醒服务告警","26"));
			eventTypes.add(new Option("boss业务进程告警","27"));
			$("#EVENT_TYPE").attr("value",eventTypeTemp);}
	});
  		
	function searchRequest(){
		theForm.submit();
	}
	
		function searchRequest0(theForm){
		theForm.EVENT_LEVEL.value = "0";
		theForm.submit();
	}
		function searchRequest1(theForm){
		theForm.EVENT_LEVEL.value = "1";
		theForm.submit();
	}
		function searchRequest2(theForm){
		theForm.EVENT_LEVEL.value = "2";
		theForm.submit();
	}
	
	function showTopN(num){
		var d = new Date();
		$.ajax({
			type:'get',
			dataType:'text',
			url:'alarm_listMonitorAlarmChart.do?'+$("#theForm").serialize()+'&dd='+d,
			success:function(msg){
				var chart1 = new FusionCharts("FusionCharts/Column2D.swf","ChartId1","100%","270","0","0");
				chart1.setJSONData(msg);
				chart1.render("topchart");
			}
		});
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
				theForm.action = 'alarm_dealMonitorAlarm.do';
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
			theForm.action = 'alarm_hisMonitorAlarm.do';
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
	//根据告警系统修改告警类型 add by lizqd 20140904
	function changeEventType(eventSys){
		if(eventSys == ''){
			var eventType=document.getElementById("EVENT_TYPE");
			eventType.options.length=0;
			eventType.add(new Option("-请选择-",""));
			eventType.add(new Option("应用告警","0"));
			eventType.add(new Option("虚拟机告警","1"));
			eventType.add(new Option("物理主机告警","2"));
			eventType.add(new Option("机房告警","3"));
			eventType.add(new Option("进程告警","4"));
			eventType.add(new Option("BOMC工单告警","5"));
			eventType.add(new Option("HADOOP告警","6"));
			return;
			}
		if(eventSys == '1_BOSS'){
			var eventType=document.getElementById("EVENT_TYPE");
			eventType.options.length=0;
			eventType.add(new Option("-请选择-",""));
			eventType.add(new Option("话费量告警","21"));
			eventType.add(new Option("目录文件积压量告警","22"));
			eventType.add(new Option("错误日志告警","23"));
			eventType.add(new Option("流量查询服务告警","24"));
			eventType.add(new Option("端口收发告警","25"));
			eventType.add(new Option("提醒服务告警","26"));
			eventType.add(new Option("boss业务进程告警","27"));
			}
		}
</script>
</head>
<body >
<div class="mainbody">
<s:form action="alarm_listMonitorAlarm.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.EVENT_ID" id="EVENT_ID"></s:hidden>
<s:hidden name="eventTypeTemp" id="eventTypeTemp"></s:hidden>
	<div class="pd-20 bgcolor-1">
		<div class="box on">
			<h2 class="utt-1">告警管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
			<div id='topchart' style="float:left;width: 40%">
			</div>
			<div style="float:right;width:50%">
			<table width="100%" class="querytable" border="0" style="margin-top: 20px;" >
		    <tbody>
		    <tr>
               	<td>告警系统</td>
               	<td>
					<s:select style="width: 70%" list="#{'':'-请选择-','1_BOSS':'BOSS系统'}" name="theForm.EVENT_SYS" id="EVENT_SYS" onchange="changeEventType(this.value)" ></s:select> 
				</td>
			  </tr> 
               <tr>
               	<td>告警类型</td>
               	<td>
					<s:select style="width: 70%" list="#{'':'-请选择-','0':'应用告警','1':'虚拟机告警','2':'物理主机告警','3':'机房告警','4':'进程告警','5':'BOMC工单告警','6':'HADOOP告警'}" name="theForm.EVENT_TYPE" id="EVENT_TYPE"></s:select>
				</td>
			  </tr>
			  <tr>	
				<td>告警级别</td>
         		<td>
                    <s:select style="width: 70%;" list="#{'':'-请选择-','0':'严重告警','1':'主要告警','2':'一般告警'}" name="theForm.EVENT_LEVEL" id="EVENT_LEVEL"></s:select>
				</td>
			   </tr>
			   <tr>
				<td>告警状态</td>
				<td>
	                 <s:select style="width: 70%;" list="#{'':'-请选择-','0':'当前告警（未处理）','1':'当前告警（已处理）'}" name="theForm.EVENT_STAT" id="EVENT_STAT"></s:select> 
				</td>
			   </tr>
				<tr>
					<td>告警位置</td>
					<td>
						<s:textfield style="width: 70%;" name="theForm.EVENT_LOCATION" cssClass="txt" id="EVENT_LOCATION"></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>告警标题</td>
					<td>
						<s:textfield style="width: 70%;" name="theForm.TITILE" cssClass="txt" id="TITILE" ></s:textfield>
					</td>
				</tr>
				<tr>	
					<td>告警内容</td>
					<td>
						<s:textfield style="width: 70%;"  name="theForm.CONTENT" cssClass="txt" id="CONTENT" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td>告警时间</td>
					<td>
						<input style="width: 70%;" readonly="true" type="text" name="theForm.ALARM_TIME"  class="Wdate" id="datepicker1"
			   		onFocus="WdatePicker({minDate:'1900-01-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			   		value="${theForm.ALARM_TIME}"/> 
					</td>
				</tr>
				<tr  height="50px">
                    <td colspan="8" class="btns" align="center">
                        <div>
                        	<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
							<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
                        </div>
                    </td>
                 </tr>
                 </tbody>
           	 </table>
            </div>
            </div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="javascript:dealAlarmRequest();return false;">告警处理</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="javascript:hisAlarmRequest();return false;" >告警消除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>选择</th>
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
				  			<s:elseif test="#theBean.EVENT_TYPE==21">话务量告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==22">目录文件积压告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==23">错误日志告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==24">流量查询服务告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==25">端口收发告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==26">提醒服务告警</s:elseif>
				  			<s:elseif test="#theBean.EVENT_TYPE==27">boss业务进程告警</s:elseif>
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
			<div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
		</div>
	</div>
</s:form>
</body>
