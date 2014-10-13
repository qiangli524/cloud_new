<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<style>
	div.hidden{
		width:150px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
		}
</style>
<script type="text/javascript">
	<% int i=1;%>
	//查询
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
	//插入折线图
	function initData(){
	var extEqId = $("#extEqId").val();
	var kpiCfgValue = $("#kpiCfgValue").val();
	var START_TIME = $("#START_TIME").val();
	var END_TIME = $("#END_TIME").val();
	var num = $("#num").val();
			$.ajax({
					type : 'post',
					url : 'bossBusiAction_queryChartJSONForDirDesc.do?cfgObj.extEqId='+extEqId+'&cfgObj.kpiCfgValue='+kpiCfgValue+'&obj.START_TIME='+START_TIME+'&obj.END_TIME='+END_TIME+'&obj.num='+num,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId0", "100%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
					}
			});
	}
	//页面生成后的初始化
	$(function(){
		initData();
	});
	//去空
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
</script>
</head>
<body style="overflow-y: auto;" class="pop-body scrollbody">
<div class="mainbody" style="overflow-y: auto;">
<s:form action="bossBusiAction_queryListForDirDesc.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="cfgObj.extEqId" id="extEqId"></s:hidden>
<s:hidden name="cfgObj.kpiCfgValue" id="kpiCfgValue"></s:hidden>
	<div class="pd-20 bgcolor-1">
		 <h2 class="utt-1">目录文件积压监控</h2>
		<div class="bord-1 pd-20">	
			<div class="clearfix mgt-10">
			<label class="mgl-10 vm">展示点数：</label>
					<s:select cssStyle="width:80px;" cssClass="select-1 vm" list="#{'':'请选择',100:100,200:200,300:300}" name="obj.num"  id="num" ></s:select>
			<label class="mgl-10 vm">时间:</label> 
				<s:textfield id="START_TIME" name="obj.START_TIME" cssClass="Wdate"
							onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				- <s:textfield id="END_TIME" name="obj.END_TIME" cssClass="Wdate"
							onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest();" value="查询" /></span>
			</div>
			<div id="chartarea" align="center"></div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>主机IP</th> 
				   <th>目录名</th> 
                   <th>积压量</th>
                   <th>是否有积压</th>
                   <th>积压描述</th>
                   <th>采集时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="busiObjList" id="theBean">
						<tr>
							<td><s:property value="#theBean.HOST_IP"/></td>
							<td><s:property value="#theBean.dirName"/></td>
							<td><s:property value="#theBean.IN_COUNT"/></td>
							<td>
								<s:if test="#theBean.alarm_flag==0">无</s:if>
								<s:elseif test="#theBean.alarm_flag==3">无法判定</s:elseif>
								<s:else><font color="red">有</font></s:else>
							</td>
							<td align="center">
								<s:if test="#theBean.alarm_desc==null">-</s:if>
								<s:else>
									<div style="padding:auto;" class="hidden" title='<s:property value="alarm_desc"/>' style="width: 15px;">
										<s:property value="#theBean.alarm_desc"/>
									</div>
								</s:else>
							</td>
							<td><s:property value="#theBean.COLL_TMIE"/></td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
	</div>
</s:form>
</div>
</body>
</html:html>
