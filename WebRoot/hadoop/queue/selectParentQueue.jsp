<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/treeTable/stylesheets/jquery.treetable.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/treeTable/stylesheets/jquery.treetable.theme.default.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/treeTable/javascripts/src/jquery.treetable.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		api.button({
			id:'OKSelect',
			name:'确定',
			callback:selectPQ,
			focus:true
		},
		{
			id:'cancle',
			name :'取消'
		});
		
		$("#theTable").treetable({expandable:true});
		 
		$(".query").click(function(){
			if($(".query-form").is(":visible")){
				$(".query-form").slideUp("slow");
			}else{
				$(".query-form").slideDown("slow");
			}
		});
		
		$("#searchForm").click(function(){
			$("#theForm").submit();
		});
		
		$("#resetForm").click(function(){
			$("#queue_name").val(null);
		});
	});
	
		
	function selectPQ(){
		var queueId = '';
		var queueName = '';
		$("input['name=checkboxid']:checked").each(function(){
			queueId += $(this).attr("queueid");
			queueName += $(this).attr("queueName");
		});
		if(queueId==null || queueId ==""){
			alert("请勾选一条信息");
			return false;
		}
		
		api.get("addqueue").selectParentQueue(queueId,queueName);
	}
</script>
</head>
<body>
	<s:form action="hadoopQueue_list.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">队列名称：</td>
							<td>
								<s:textfield name="hadoopQueueObj.queue_name" id="queue_name"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="10" class="btns">
								<div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="thickbox btn-style02" value = "重置" id="resetForm" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th> 
									<th onclick="sort(theTable,1,'string')">队列名称</th>
									<th onclick="sort(theTable,2,'string')">所属策略</th>
									<th onclick="sort(theTable,3,'string')">配置文件</th>
									<th onclick="sort(theTable,4,'string')">cpu最大值</th>
									<th onclick="sort(theTable,5,'string')">cpu最小值</th>
									<th onclick="sort(theTable,6,'string')">内存最大值</th>
									<th onclick="sort(theTable,7,'string')">内存最小值</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<s:if test="#theBean.parent_id==0">
										<tr class="expanded" align="center" data-tt-id='<s:property value="#theBean.id" />'>
									</s:if>
									<s:else>
										<tr class="expanded" align="center" data-tt-id='<s:property value="#theBean.id"/>'
										data-tt-parent-id='<s:property value="#theBean.parent_id"/>'>
									</s:else>
										<td>
											<input type="checkbox" name="checkboxid" queueid='<s:property value="#theBean.id"/>' queueName='<s:property value="#theBean.queue_name"/>'/>
										</td>
										<td>
											<s:property value="#theBean.queue_name"/>
										</td>
										<td>
											<s:if test="#theBean.tactics == 1">
												FIFO
											</s:if>
											<s:elseif test="#theBean.tactics == 2">
												Container
											</s:elseif>
											<s:elseif test="#theBean.tactics == 3">
												Fair
											</s:elseif>
											<s:else>
												--
											</s:else>
										</td>
										<td>
											aaa.xml
										</td>
										<td>
											<s:property value="#theBean.cpu_max"/>GHz
										</td>
										<td>
											<s:property value="#theBean.cpu_min"/>GHz
										</td>
										<td>
											<s:property value="#theBean.mem_max"/>GB
										</td>
										<td>
											<s:property value="#theBean.mem_min"/>GB
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>