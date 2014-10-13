<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	  	var api = frameElement.api;
		var w = api.opener;
		$(function(){
			$("#addUser").click(function(){
				var groupId = $("#groupId").val();
				var serviceType = $("#serviceType").val();
				var clusterId = $("#clusterId").val();
				w.$.dialog({
					id:'adduser',
					title:'关联用户',
					width: '800px',
					height: '600px',
	    		    lock:true,
					content: 'url:hadoopUserGroup_assoUsersForGroup.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='+serviceType
							+'&hadoopUserGroup.cluster_id='+clusterId
				});
			});
			
			$("#deleteUser").click(function(){
				if ($(":checkbox:checked").length==0) {
					alert("请至少选择一项进行操作");
					return false;
				}
				var userIds = "";
				$(":checkbox:checked").each(function(){
					userIds += $(this).attr("memberId")+",";
				});
				var serviceType = $("#serviceType").val();
				$.ajax({
					type:'post',
					url:'hadoopUserGroup_deleteMembers.do?hadoopUserGroup.userIds='+userIds+'&hadoopUserGroup.service_type='+serviceType,
					success:function(msg){
						$("#theForm").submit();
					}
				});
			});
		});
	  	
		function saveMember(userIds){
			var groupId = $("#groupId").val();
			var serviceType = $("#serviceType").val();
			$.ajax({
				type:'post',
				dataType:'text',
				url:'hadoopUserGroup_saveMembers.do?hadoopUserGroup.userIds='+userIds+'&hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='+serviceType,
				success:function(msg){
					$("#theForm").submit();
				}
			});
		}
	</script>
</head>
<body style="overflow-y:auto">
	<s:form action="hadoopUserGroup_listUserForGroup.do" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="hadoopUserGroup.service_type" id="serviceType"></s:hidden>
		<s:hidden name="hadoopUserGroup.cluster_id" id="clusterId"></s:hidden>
		<s:hidden name="hadoopUserGroup.id" id="groupId"></s:hidden>
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="新增" id="addUser" /></li>
							<li><input type="button" class="thickbox btn-style02" value="解除" id="deleteUser" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">选择</th>
									<th onclick="sort(theTable,1,'string')">用户名</th>
									<th onclick="sort(theTable,2,'string')">所属集群</th>
									<th onclick="sort(theTable,3,'int')">所属服务</th>
									<th onclick="sort(theTable,4,'int')">用户状态</th>
									<th onclick="sort(theTable,5,'int')">关联状态</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userList" id="theBean">
					  				<tr>
					  					<td>
					  						<s:if test="#theBean.memberType == 0 && #theBean.memberStatus==2">
					  							<input type="checkbox" name="checkboxid" memberId='<s:property value="#theBean.memberId"/>'/>
					  						</s:if>
					  						<s:else>
					  							--
					  						</s:else>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.username"/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.cluster_name"/>
					  					</td>
						  				<td>
						  					<s:if test="#theBean.service_type == 1">nameNode</s:if>
						  					<s:elseif test="#theBean.service_type == 2">dataNode</s:elseif>
						  					<s:elseif test="#theBean.service_type == 3">journalNode</s:elseif>
						  					<s:elseif test="#theBean.service_type == 4">nodeManager</s:elseif>
						  					<s:elseif test="#theBean.service_type == 5">resourceManager</s:elseif>
						  					<s:elseif test="#theBean.service_type == 6">hmaster</s:elseif>
						  					<s:elseif test="#theBean.service_type == 7">regionServer</s:elseif>
						  					<s:elseif test="#theBean.service_type == 8">hbase_thirftServer</s:elseif>
						  					<s:elseif test="#theBean.service_type == 9">znode</s:elseif>
						  					<s:elseif test="#theBean.service_type == 10">hive_thirftServer</s:elseif>
						  					<s:elseif test="#theBean.service_type == 11">impalaxx</s:elseif>
						  					<s:elseif test="#theBean.service_type == 12">DFSzkFailoverController</s:elseif>
						  					<s:else>--</s:else>
										</td>
										<td>
											<s:if test="#theBean.deal_type==0">
												<s:if test="#theBean.status==0">待创建</s:if>
												<s:elseif test="#theBean.status==1">创建中</s:elseif>
												<s:elseif test="#theBean.status==2">创建成功</s:elseif>
												<s:else>创建失败</s:else>
											</s:if>
											<s:elseif test="#theBean.deal_type==1">
												<s:if test="#theBean.status==0">待回收</s:if>
												<s:elseif test="#theBean.status==1">回收中</s:elseif>
												<s:elseif test="#theBean.status==2">回收成功</s:elseif>
												<s:else>回收失败</s:else>
											</s:elseif>
										</td>
										<td>
											<s:if test="#theBean.memberType==0">
												<s:if test="#theBean.memberStatus==0">待关联</s:if>
												<s:elseif test="#theBean.memberStatus==1">关联中</s:elseif>
												<s:elseif test="#theBean.memberStatus==2">关联成功</s:elseif>
												<s:else>关联失败</s:else>
											</s:if>
											<s:else>
												<s:if test="#theBean.memberStatus==0">待解除</s:if>
												<s:elseif test="#theBean.memberStatus==1">解除中</s:elseif>
												<s:elseif test="#theBean.memberStatus==2">解除成功</s:elseif>
												<s:else>解除失败</s:else>
											</s:else>
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
