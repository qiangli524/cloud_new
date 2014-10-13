<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
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
<%--<link type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css" rel="stylesheet">--%>
<%--  <script type="text/javascript" src="<%=request.getContextPath()%>/temp1/pub-ui/js/plugin/table.js"></script>--%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/tabs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/common.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp1/pub-ui/css/skin1/colorbox.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/temp1/pub-ui/js/plugin/all-min.js">
</script>
<script type="text/javascript">
	$(function(){
		initData();
		var resourceType = $("#resourceType").val();
//		if (resourceType == 3) {
	//		document.getElementById("choice").style.display="none";
//		}
		$("#cpu").click(function(){
			$("#resourceType").val("1");
			initData();
		});
		$("#mem").click(function(){
			$("#resourceType").val("2");
			initData();
		});
		$("#store").click(function(){
			$("#resourceType").val("3");
			initData();
		});
		$("#more").click(function(){
			resourceType = $("#resourceType").val();
			$.dialog({
				id:'detail',
				title:'虚拟机列表',
				width:'1000px',
				height:'550px',
				zIndex:1975,
				content:'url:showvm_ah_listvm.do'
			});
		});
	});
	
	function initData(){
		$("#tab tr:not(:first)").remove();
		var resourceType = $("#resourceType").val();
		$.ajax({
			type:'post',
			url:'resourceOutline_acquireHostTopNData.do?resourceType='+resourceType,
			dataType:'json',
			success:function(msg){
				var resultList = msg.globalList;
				if (resultList != null) {
					for ( var i = 0; i < resultList.length; i++) {
						$(
							"<tr><td>"+resultList[i].businame+"</td><td align=\"left\"><div class=\"percentage\">"+
							"<b style='width: "+resultList[i].name+"%'></b></div>"+resultList[i].name+"%</td><td align=\"left\"><div class=\"percentage\">"+
							"<b style='width: "+resultList[i].cpu_usage+"%'></b></div>"+resultList[i].cpu_usage+"%</td><td align=\"left\"><div class=\"percentage\">"+
							"<b style='width: "+resultList[i].mem_usage+"%'></b></div>"+resultList[i].mem_usage+"%</td></tr>"
						).insertAfter($("#tab tr:eq("+i+")"));
					}
				}
			}
		});		
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="resourceType" id="resourceType"></s:hidden>
		<div class="ued-tab tab-style" data-role="ued-tabs" data-rel="trigger:'click',activeIndex:0" id="choice">
         <div class="title-bg clearfix">
             <ul class="nav-tabs">
                 <li class="">
                     <a href="javascript:;" id="cpu">
                         CPU维度
                     </a>
                 </li>
                 <li class="">
                     <a href="javascript:;" id="mem">
                                                                          内存维度
                     </a>
                 </li>
                 <!-- <li class="">
                     <a href="javascript:;" id="store">
                                                                          存储维度
                     </a>
                 </li> -->
             </ul>
         </div>
     </div>
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder">
					<div class="table-ct">
						<table width="100%" class="ued-table" border="0" cellspacing="0" id="tab" >
							<thead>
								<tr>
									<th>业务名称</th>
									<th>虚拟机名称</th>
									<th>cpu利用率</th>
									<th>内存利用率</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="globalList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.name"/>
										</td>
										<td>
											<div class="percentage">
												<b style='width: <s:property value="#theBean.cpu_usage"/>%'>
												</b>
											</div>
											<s:property value="#theBean.cpu_usage"/>%
										</td>
										<td>
											<div class="percentage">
												<b style='width: <s:property value="#theBean.mem_usage"/>%'>
												</b>
											</div>
											<s:property value="#theBean.mem_usage"/>%
										</td>
										<td>
											<div class="percentage">
												<b style='width: <s:property value="#theBean.store_usage"/>%'>
												</b>
											</div>
											<s:property value="#theBean.store_usage"/>%
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div style="float: right">
			<a href="javascript:;" id="more" style="color: blue;font-size: 12px">更多..</a>
		</div>
	</s:form>
</body>
