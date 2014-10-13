<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	<% int i=1;%>
	function resetForm() {
		theForm.CLUSTER_NAME.value = '';
		theForm.POOL_NAME.value = '';
		theForm.START_TIME.value = '';
		theForm.END_TIME.value = '';
	}
	function searchRequest() {
		var START_TIME = $("#START_TIME").val();
		var END_TIME = $("#END_TIME").val();
		if(START_TIME > END_TIME){
			alert("开始时间不得大于结束时间！");
			return false;
		}
		if(START_TIME == "" && END_TIME!=""){
			alert("请填写开始时间！");
			return false;
		}
		theForm.submit();
	}
	//选择集群、程序池后，关联查询出旗下进程
	function queryProcess(){
		  var html="";
		  var cluster_id=$("#CLUSTER_NAME option:selected").val();
		  var pool_name=$("#POOL_NAME option:selected").val();
		  if(cluster_id == "" || pool_name == ""){
		  	return false;
		  }
			$.ajax({
	            type: "post",
	            url: "bossProcedure_getProcessListByCluAndPool.do?cluster_id="+cluster_id+"&pool_name="+pool_name,
	            dataType: "json",
				async:false,//取消异步
				cache:false,
	            success : function(data){
					for(var i=0;i<data.length;i++){
						html=html+"<option value='"+data[i].uid+"'>"+data[i].program_name+"</option>";		
					}
					$("#pname").empty();
					$("#pname").append("<option value='' >--请选择--</option>");
					$("#pname").append(html);
				}
	          });
	}
	//插入折线图
	function initData(){
		var BUSI_KPI ="<s:property value='kpi'/>";
		var CLUSTER_NAME = $("#CLUSTER_NAME").val();
		var POOL_NAME = $("#POOL_NAME").val();
		var timeStart = $("#START_TIME").val();
		var timeEnd = $("#END_TIME").val();
		var uid = $("#pname").val();
		var flag = $("#ONLINE_FLAG").val();
		var num = $("#num").val();
		if(uid == "" || uid == null){
			//显示走势图
			$.ajax({
					type : 'post',
					cache:false,
					url : 'bossBusiAction_queryHosiChartJSONForPool.do?obj.CLUSTER_NAME='+
						CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI+"&obj.START_TIME="+timeStart+"&obj.END_TIME="+timeEnd+"&obj.ONLINE_FLAG="+flag+"&obj.num="+num,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId1", "75%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
					}
			});
			//显示饼图
            $("#ratio").insertFusionCharts({  
            	swfUrl: "FusionCharts/Pie2D.swf", 
        	    dataSource: "bossBusiAction_showHosiNctRatio.do?obj.CLUSTER_NAME="+CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI+"&obj.START_TIME="+timeStart+"&obj.END_TIME="+timeEnd,
        	    dataFormat: "jsonurl", 
        	    width: "300", 
        	    height: "220", 
        	    id: "ratio2"
        	});
		}else{
			$.ajax({
					type : 'post',
					url : 'bossBusiAction_queryHosiChartJSONForPool.do?obj.CLUSTER_NAME='+
						CLUSTER_NAME+"&obj.POOL_NAME="+POOL_NAME+"&obj.BUSI_KPI="+BUSI_KPI+"&obj.START_TIME="+timeStart+"&obj.END_TIME="+timeEnd+"&uid="+uid,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId1", "100%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
					}
		});
		}
	}
	$(function(){
		//默认进入页面查询第一个池子下的进程
		//queryProcess();
		/**这里有一点需要注意，就是上面的方法可能还没有返回，下面就已经执行了，导致页面select不能默认选中之前选中的*/
		//保留选择的进程
		var uid='<%=request.getAttribute("uid")%>';
		if(uid != "" && uid != null){
			$("#pname").find("option").each(function(){
					if($(this).val() == uid){
						$(this).attr("selected","selected");
					}
				});
		}
		initData();
		chan();
	});
	//明细
	function desc(a){
			var busiKpi ="<s:property value='kpi'/>";
			var cluster = $(a).parent().parent().find("td").eq(1).text();
			var timeSeq = $(a).parent().parent().find("td").eq(0).text();
			var online_flag = $(a).parent().parent().find("td").eq(5).text();
			if(online_flag == "在线"){
				online_flag = "1";
			}else {
				online_flag = "0";
			}
			var poolName =  $(a).parent().parent().find("td").eq(4).text();
			$.dialog({
					id:'add',
					title:'话务明细',
					width: '750px',
					height: '450px',
					lock:true,
					content: 'url:bossBusiAction_queryHosiListForPortDesc.do?obj.CLUSTER_NAME='+cluster
					+"&obj.POOL_NAME="+poolName+"&obj.BUSI_KPI="+busiKpi+"&obj.TIME_SEQ="+timeSeq+"&obj.ONLINE_FLAG="+online_flag+"&kpi="+busiKpi
				});	
	}
	//判断所选程序池是否为NCT
	function chan(){
		var poolName = $("#POOL_NAME").val();
		if(poolName == "NCT"){
			$("#sel").show();
		}else{
			$("#sel").hide();
		}
	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="bossBusiAction_queryHosiPortDataList.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="kpi"></s:hidden>
		 <div class="pd-20 bgcolor-1">
		 	<h2 class="utt-1">话务量监控</h2>
			<div class="bord-1 pd-20">			
			<div class="clearfix mgt-10">
				<label class="vm">集群名称：</label>
					<s:select cssStyle="width:110px;" list="obj.clusterList" cssClass="select-1" id="CLUSTER_NAME"  
						listKey="nodeId" listValue="nodeName" name="obj.CLUSTER_NAME" >
					</s:select>
				<label class="mgl-20 vm">程序池：</label>
					<s:select cssStyle="width:110px;" list="obj.poolList"  cssClass="select-1 vm" id="POOL_NAME" 
						listKey="nodeId" listValue="nodeName" name="obj.POOL_NAME" onchange="javascript:chan();">
					</s:select>
				<label id="sel">
					<label class="mgl-20 vm">状态：</label>
						<s:select list="#{'':'请选择','1':'在线','0':'离线'}" cssClass="select-1 vm" name="obj.ONLINE_FLAG" id="ONLINE_FLAG"></s:select>
				</label>
				<!-- <label class="mgl-20 vm">进程名：</label>
					<select style="width:110px;" class="select-1 vm" id="pname"  name="uid"></select> -->
				<label class="mgl-20 vm">展示点数：</label>
					<s:select cssStyle="width:80px;" cssClass="select-1 vm" list="#{'':'请选择',100:100,200:200,300:300}" name="obj.num" id="num" ></s:select>
				<label class="mgl-20 vm">时间:</label> 
				<s:textfield id="START_TIME" name="obj.START_TIME" cssClass="Wdate"
							onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})" />
				- <s:textfield id="END_TIME" name="obj.END_TIME" cssClass="Wdate"
							onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})" />
				<div class="clearfix mgt-10" align="center">	
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest();" value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm();" value="重置" /></span>
				</div>
				<div id="ratio" style="margin-right: 5px;margin-top:15px;position:absolute ;"></div>
			</div>
			<div id="chartarea"  align="right"></div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				   <th>集群</th>  
                   <s:if test="uid != null && uid != ''">
                   	<th>程序池</th>
                   	<th>进程名</th>
                   </s:if>
                   <s:else>
                   	<th>程序池</th>
                   </s:else>
                   <th>状态</th>
                   <th>采集时间</th>
                   <th>入口业务量</th>
                   <th>出口业务量</th>
                   <th>无主量</th>
                   <th>错单量</th>
                   <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="busiObjList" id="theBean">
						<tr>
							<td style="display: none;"><s:property value="#theBean.TIME_SEQ"/></td>
							<td style="display: none;"><s:property value="#theBean.CLUSTER_NAME"/></td>
							<td><%= i++%></td>
							<td><s:property value="#theBean.nodename"/></td>
							<s:if test="uid != null && uid != ''">
								<td><s:property value="#theBean.POOL_NAME"/></td>
								<td><s:property value="#theBean.PROGRESS_NODE"/></td>
							</s:if>
							<s:else>
								<td><s:property value="#theBean.POOL_NAME"/></td>
							</s:else>
							<td>
								<s:if test="#theBean.ONLINE_FLAG == 1">
									在线
								</s:if>
								<s:else>
									离线
								</s:else>
							</td>
							<td><s:property value="#theBean.COLL_TMIE"/></td>
							<td><s:property value="#theBean.IN_COUNT"/></td>
							<td><s:property value="#theBean.OUT_COUNT"/></td>
							<td><s:property value="#theBean.NO_MASTER_COUNT"/></td>
							<td><s:property value="#theBean.ERROR_COUNT"/></td>
							<td><a href="#" onclick="javascript:desc(this);" >明细</a></td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
