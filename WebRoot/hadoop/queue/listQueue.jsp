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
	$(function(){
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
			$("#tactics").val("-1");
		});
		
		$("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		})
		
		$("#addForm").click(function(){
			$.dialog({
				id:'addqueue',
				title:'添加队列',
				width:'600px',
				height:'400px',
				lock:true,
				content:'url:hadoopQueue_add.do?oper=add'
			});
		});
		
		$("#deleteForm").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("请至少选择一项进行删除");
				return false;
			}
			var queueid = "";
			$(":checkbox:checked").each(function(){
				queueid += $(this).attr("queueid")+",";
			});
			if (confirm("该操作将删除该队列及其子队列，你确定进行该操作吗？")) {
				$.ajax({
					type:'post',
					url:'hadoopQueue_delete.do?idstr='+queueid,
					success:function(msg){
						if (msg == -1) {
							alert("删除失败");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});
		
		$("[name='edit']").unbind().live("click",function(){
			var $td = $(this).parent();
			var id = $td.attr("queueid");
			$.dialog({
				id:'editqueue',
				title:'修改队列',
				width:'400px',
				height:'300px',
				lock:true,
				content:'url:hadoopQueue_update.do?idstr='+id//修改队列时，只允许修改用户和队列资源大小，不支持修改其他属性，修改用户放在放在用户管理中进行
			});
		});
		
		$("[name='viewUser']").unbind().live("click",function(){
			var queueId = $(this).parent().attr("queueid");
			$.dialog({
				id:'manageUser',
				title:'管理用户',
				width:'800px',
				height:'500px',
				lock:true,
				content:'url:hadoopQueue_managerUsers.do?idstr='+queueId+'&flag=show'
			});
		});
		
	});
	
	function saveQueue(theForm){
		var url = 'hadoopQueue_save.do';
		$.ajax({
            type: "POST",
            url: url,
            data: theForm,
            async:false,
            cache:false,
            success : function(data){
	              $("#theForm").submit();
              }
    	});
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
							<td class="til">类型：</td>
							<td>
								<s:select list="#{'-1':'--请选择--','1':'FIFO','2':'Container','3':'Fair' }" name="hadoopQueueObj.tactics" id="tactics"></s:select>
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
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" id="addForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th><input type="checkbox" id="checkAll"/>选择</th> 
									<th onclick="sort(theTable,1,'string')">队列名称</th>
									<th onclick="sort(theTable,2,'string')">所属策略</th>
									<th onclick="sort(theTable,3,'string')">配置文件</th>
									<th onclick="sort(theTable,4,'string')">cpu最大值</th>
									<th onclick="sort(theTable,5,'string')">cpu最小值</th>
									<th onclick="sort(theTable,6,'string')">内存最大值</th>
									<th onclick="sort(theTable,7,'string')">内存最小值</th>
									<th onclick="sort(theTable,8,'string')">管理用户</th>
									<th onclick="sort(theTable,9,'string')">操作</th>
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
											<s:property value="#theBean.config_name"/>
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
										<td queueid='<s:property value="#theBean.id"/>' >
											<a href="javascript:;" name="viewUser">
												管理用户
											</a>
										</td>
										<td queueid='<s:property value="#theBean.id"/>'>
											<a href="javascript:;" name="edit">
												编辑
											</a>
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