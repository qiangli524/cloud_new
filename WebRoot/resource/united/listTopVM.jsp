<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
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
	
	.font-more{ width:130px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</script>
<script type="text/javascript">
	$(function(){
		initData();
	//	var resourceType = $("#resourceType").val();
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
		$("#more").click(function(){
			resourceType = $("#resourceType").val();
			$.dialog({
				id:'detail',
				title:'业务系统虚机资源利用率',
				width:'1200px',
				height:'700px',
				lock:true,
				content:'url:resourceOutline_listVmTopNDetail.do?resourceType='+resourceType
			});
		});
	});
	
	function initData(){
		var resourceType = $("#resourceType").val();
		$.ajax({
			type:'post',
			url:'resourceOutline_acquireVmList.do?resourceType='+resourceType,
			dataType:'json',
			async:false,
			success:function(msg){
				$("#tab tr:not(:first)").remove();
				var resultList = msg.globalList;
				if (resultList != null) {
					for ( var i = 0; i < resultList.length; i++) {
						$(
							"<tr><td>"+resultList[i].businame+"</td><td>"+resultList[i].name+
							"</td><td align=\"left\"><div class=\"percentage\">"+
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
		<%--<div class="ued-tab tab-style" data-role="ued-tabs" data-rel="trigger:'click',activeIndex:0" id="choice">
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
             </ul>
         </div>
     </div>
		--%>
		<div ></div>
		<div class="scrollbody">
			<div >
				 <div>
       				<div class="table-ct">
           				 <table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="tab">
							<thead>
								<tr>
									<th>业务名称</th>
									<th>虚机名称</th>
									<th>cpu利用率</th>
									<th>内存利用率</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="globalList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.businame"/>
										</td>
										<td>
											<span style="color: black;" class="font-more" title='<s:property value="#theBean.name"/>'>
												<s:property value="#theBean.name"/>
											</span>
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
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div style="float: right">
			<a href="javascript:;" id="more" style="color: blue;font-size: 12px">more</a>
		</div>
	</s:form>
</body>
