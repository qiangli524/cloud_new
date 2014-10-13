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
  <style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
  </style>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	  	$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		})
	  
		$(function(){
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
				$("#groupName").val(null);
				$("#serviceType").val("-1");
				$("#clusterId").val("-1");
				$("#groupStatus").val("-1");
			});
			
			$("#addForm").click(function(){
				$.dialog({
					id:'addusergroup',
					title:'增加用户组',
					width: '500px',
					height: '320px',
	    		    lock:true,
					content: 'url:hadoopUserGroup_add.do?oper=add',
				});
			});
			
			$("#editForm").unbind().live("click",function(){
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行修改");
					return false;
				}
				var groupId = "";
				var serviceType = "";
				var clusterId = "";
				$(":checkbox:checked").each(function(){
					groupId+=$(this).attr("groupId");
					serviceType += $(this).attr("serviceType");
					clusterId += $(this).attr("clusterId");
				});
				$.dialog({
	        		id:'editusergroup',
	        		title:'编辑用户组',
	        		width: '520px',
	    			height: '300px',
	    		    lock:true,
	    		    content:'url:hadoopUserGroup_edit.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='
	    		    		+serviceType+'&hadoopUserGroup.cluster_id='+clusterId+'&oper=edit'
	        	});
			});
			
			$("#deleteForm").unbind().live("click",function(){
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行删除");
					return false;
				}
				var groupId = "";
				var serviceType = "";
				var clusterId = "";
				var status = 0;
				$(":checkbox:checked").each(function(){
					groupId+=$(this).attr("groupId");
					serviceType += $(this).attr("serviceType");
					clusterId += $(this).attr("clusterId");
					status = $(this).attr("status");
				});
				if (status == 0) {
					alert("该用户组正在进行操作中，无法回收，请等待");
					return false;
				}
				if(confirm("你确定要进行删除操作吗?")){
					mask('正在检测用户组是否满足回收条件，请稍后','0.7','0px');
					var urlc = "hadoopUserGroup_checkIfRecoverable.do?hadoopUserGroup.id="+groupId+
							"&hadoopUserGroup.cluster_id="+clusterId+"&hadoopUserGroup.service_type="+serviceType;
					var flag = false;
					$.ajax({
						type:'post',
						url:urlc,
						dataType:'text',
						async:false,
						success:function(msg){
							removeMask();
							if (msg == -1) {
								alert("该用户组正在关联或解除关联用户，不能回收");
							} else {
								flag = true;
							}
						}
					});
					
					if (flag) {
						var url = "hadoopUserGroup_delete.do?hadoopUserGroup.id="+groupId+"&hadoopUserGroup.service_type="+serviceType;
						$.ajax({
							type:'post',
							url:url,
							dataType:'text',
							success:function(msg){
								if (msg == -1) {
									alert(msg);
								} else {
									$("#theForm").submit();
								}
							}
						});
					}
				}
			});
			
			$("[name='assousers']").unbind().live("click",function(){
				var $td = $(this).parent();
				var groupId = $td.attr("groupId");
				var serviceType = $td.attr("serviceType");
				var clusterId = $td.attr("clusterId");
				$.dialog({
	        		id:'listUserForGroup',
	        		title:'管理组用户',
	        		width: '800px',
	    			height: '600px',
	    		    lock:true,
	    		    content:'url:hadoopUserGroup_listUserForGroup.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='
	    		    		+serviceType+'&hadoopUserGroup.cluster_id='+clusterId
	        	});
			});
			
			$("[name='addStra']").unbind().live("click",function(){
				var $td = $(this).parent();
				var groupId = $td.attr("groupId");
				var serviceType = $td.attr("serviceType");
				var clusterId = $td.attr("clusterId");
				$.dialog({
	        		id:'addStra',
	        		title:'添加权限',
	        		width: '500px',
	    			height: '300px',
	    			lock:true,
	    		    content:'url:hadoopUserGroup_addAuthority.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='
	    		    		+serviceType+'&hadoopUserGroup.cluster_id='+clusterId
	        	});
			});
			
			$("[name='viewStra']").unbind().live("click",function(){
				var $td = $(this).parent();
				var groupId = $td.attr("groupId");
				var serviceType = $td.attr("serviceType");
				var clusterId = $td.attr("clusterId");
				$.dialog({
	        		id:'viewStra',
	        		title:'查看权限',
	        		width: '800px',
	    			height: '600px',
	    		    lock:true,
	    		    content:'url:hadoopUserGroup_viewAuthority.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='
	    		    		+serviceType+'&hadoopUserGroup.cluster_id='+clusterId
	        	});
			});
			
		});
	  	
	  	function saveUserGroup(theForm){
	  		$.ajax({
	  			type:'post',
	  			url:'hadoopUserGroup_save.do?'+theForm,
	  			dataType:'json',
	  			success:function(data){
	  				var aa = data.msg;
	  				if (aa.length > 0) {
						alert("保存失败，失败原因： " + aa);
					} else {
						$("#theForm").submit();
					}
	  			}
	  		});
	     }
	  	
	  	function savePath(authPath,groupId,serviceType,clusterId,autho){
	  		$.ajax({
	  			type:'post',
	  			url:'hadoopUserGroup_savePath.do?hadoopUserGroup.id='+groupId+'&hadoopUserGroup.service_type='+serviceType
	  					+'&hadoopUserGroup.cluster_id='+clusterId+'&authPath='+authPath+'&autho='+autho
	  		});
	  	}
	</script>
</head>
<body style="overflow-y:auto">
	<s:form action="hadoopUserGroup_list.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">用户组名称：</td>
		                  		<td>
		                  			<s:textfield name="hadoopUserGroup.name" id="groupName"></s:textfield> 
		                  		</td>
		                  		<td class="til">所属集群：</td>
		                  		<td>
		                  			<s:select list="clusterList" listKey="cluster_id" listValue="cluster_name" name="hadoopUserGroup.cluster_id" 
		                  				id="clusterId" headerKey="-1" headerValue="--请选择--"></s:select>
		                  		</td>
		                  		<td class="til">所属服务：</td>
		                  		<td>
		                  			<s:select list="#{'-1':'--请选择--','1':'nameNode','2':'dataNode','3':'journalNode','4':'nodeManager',
		                  				'5':'resourceManager','6':'hmaster','7':'regionServer','8':'hbase_thirftServer','9':'znode',
		                  				'10':'hive_thirftServer','11':'impalaxx','12':'DFSzkFailoverController'}" 
		                  				name="hadoopUserGroup.service_type" id="serviceType"></s:select>
		                  		</td>
		                  		<td class="til">用户组状态：</td>
		                  		<td>
		                  			<s:select list="#{'-1':'--请选择--','0':'待创建','1':'创建中','2':'创建成功','3':'创建失败','4':'待回收','5':'回收中','6':'回收失败' }"
		                  			 name="hadoopUserGroup.state" id="groupStatus"></s:select>
		                  		</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
				</div>
				
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" id="addForm" /></li>
							<%--<li><input type="button" class="thickbox btn-style02" value="修改" id="editForm" /></li>
							--%><li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">选择</th>
									<th onclick="sort(theTable,1,'string')">用户组名称</th>
									<th onclick="sort(theTable,2,'string')">所属集群</th>
									<th onclick="sort(theTable,3,'int')">所属服务</th>
									<th onclick="sort(theTable,4,'int')">用户组状态</th>
									<th>管理组用户</th>
									<th onclick="sort(theTable,6,'string')">描述</th>
									<th>权限管理</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
					  					<td>
					  						<input type="checkbox" name="checkboxid" groupId='<s:property value="#theBean.id"/>'
					  							clusterId='<s:property value="#theBean.cluster_id"/>'
					  							serviceType='<s:property value="#theBean.service_type"/>'
					  							status='<s:property value="#theBean.status"/>'/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.name"/>
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
										<td groupId='<s:property value="#theBean.id"/>'
											clusterId='<s:property value="#theBean.cluster_id"/>'
					  						serviceType='<s:property value="#theBean.service_type"/>'
										>
											<s:if test="#theBean.deal_type == 0">
												<s:if test="#theBean.status == 2">
													<a href="javascript:;" name="assousers">
														管理组用户
													</a>
												</s:if>
												<s:else>
													不可操作组用户
												</s:else>
											</s:if>
											<s:else>
												不可操作组用户
											</s:else>
										</td>
										<td align="center">
											<span style="color: black;" class="font-more" title='<s:property value="#theBean.description"/>'>
												<s:property value="#theBean.description" default="无"/>
											</span>
										</td>
										<td groupId='<s:property value="#theBean.id"/>'
											clusterId='<s:property value="#theBean.cluster_id"/>'
					  						serviceType='<s:property value="#theBean.service_type"/>'
										>
											<s:if test="#theBean.deal_type == 0 && #theBean.status == 2">
												<a href="javascript:;" name="addStra">
													授权
												</a>
												&nbsp;&nbsp;
												<a href="javascript:;" name="viewStra">
													查看
												</a>
											</s:if>
											<s:else>
												--&nbsp;&nbsp;--
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
