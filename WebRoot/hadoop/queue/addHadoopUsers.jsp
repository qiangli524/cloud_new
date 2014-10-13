<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
	<%!
		public String getImageTag(HttpServletRequest request,String path){
			return ImageUtil.getImageTag(request,path);
		}
	%>
<head>
	<script type="text/javascript">
	    var api = frameElement.api;
		var w = api.opener;
	    $(function(){
	    	api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:selectUser,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
	    	$("#assoUser").click(function(){
	    		theForm.action='hadoopQueue_connectUser.do?flag=show';
	    		theForm.submit();
	    		document.getElementById("usertable").style.display="";
	    		document.getElementById("grouptable").style.display="none";
	    		$("input[name='ids']").each(function(){
	    			   $(this).attr("checked",false);
	    		}); 
	    	});
	    	$("#assoGroup").click(function(){
	    		theForm.action='hadoopQueue_connectUserGroup.do?flag=hide';
	    		theForm.submit();
	    		document.getElementById("usertable").style.display="none";
	    		document.getElementById("grouptable").style.display="";
	    		$("input[name='checkboxid']").each(function(){
		    		   $(this).attr("checked",false);
		    		});  
	    	});
	    });
	    
	    $(function(){
	    	var flag = $("#flag").val();
	    	if(flag=="show"){
	    		$("#usertable").show();
	    		$("#grouptable").hide();
	    	}else{
	    		$("#usertable").hide();
	    		$("#grouptable").show();
	    	}
	    });
	    
	    function selectUser(){
				var serviceId = "";
				var queue_id = $("#idstr").val();
				if(document.getElementById("grouptable").style.display=="none"){
					$("input[name=checkboxid]:checked").each(function(){
						serviceId += $(this).attr("serviceId")+",";
					});
				}else{
					$("input[name='ids']").each(function(){
						serviceId += $(this).attr("serviceId")+",";
		    		});  
				}
				if(serviceId==""|| serviceId ==null){
		    		alert("请勾选一条信息");
		    		return false;
		    	}
				api.get("manageUser").saveConnectUser(serviceId,queue_id);
	    }
	</script>
</head>
<body>
	<s:hidden name="flag" id="flag"></s:hidden>
	<s:form action="hadoopQueue_selectUsers.do" method="post" id="theForm">
		<s:hidden name="hadoopUserGroup.service_type" id="serviceType"></s:hidden>
		<s:hidden name="hadoopUserGroup.cluster_id" id="clusterId"></s:hidden>
		<s:hidden name="hadoopUserGroup.id" id="groupId"></s:hidden>
		<s:hidden name="idstr" id="idstr"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="用户" id="assoUser" />
									<input type="button" class="thickbox btn-style02" value="用户组" id="assoGroup" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct" id="usertable">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">选择</th>
									<th onclick="sort(theTable,1,'string')">用户名</th>
									<th onclick="sort(theTable,2,'string')">所属集群</th>
									<th onclick="sort(theTable,3,'int')">所属服务</th>
									<th onclick="sort(theTable,4,'int')">用户状态</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userList" id="theBean">
					  				<tr>
					  					<td>
					  						<input type="checkbox" name="checkboxid" userId='<s:property value="#theBean.id"/>' serviceId='<s:property value="#theBean.service_id"/>' />
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
					  				</tr>
				  				</s:iterator>
							</tbody>
						</table>
					</div>
					
					<div class="table-ct" id="grouptable">
						<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th>名称</th>
									<th>描述</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userGroupList" id="theBean">
									<tr>
										<td>
											<input type="checkbox" name="ids" value='<s:property value="#theBean.id"/>' serviceId='<s:property value="#theBean.service_id"/>' />
										</td>
										<td>
											<s:property value="#theBean.name"/>
										</td>
										<td>
											<s:property value="#theBean.description"/>
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