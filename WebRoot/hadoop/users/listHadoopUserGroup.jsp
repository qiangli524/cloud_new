<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<script type="text/javascript">
		
		
	    function addRequest(){
	    	$.dialog({
    			id:'add',
    			title:'增加用户组',
    			width: '890px',
    			height: '480px',
    		    lock:true,
    			content: 'url:hadoopUsers_addUserGroup.do'
    			});
	    }
	    
	    function modRequest(){
	    	var selected = $('input[name="ids"]:checked').val();
	    	if(selected){
	    		var url = "hadoopUsers_updateUser.do";
	    		$.dialog({
    				id:'add',
    				title:'修改用户组',
    				width: '890px',
    				height: '480px',
    		    	lock:true,
    				content: 'url:hadoopUsers_editUserGroup.do?id='+selected
    			});
	    	}else{
	    		alert('请先选择要修改的用户组!');
	    	}
	    }
	    
	    function delRequest(){
			var selected = $('input[name="ids"]:checked').val();
			if(selected){
				var url = "hadoopUsers_deleteUserGroup.do";
				var data = {'group.id':selected};
				$.dialog.confirm('你确定要删除吗？', function(){
					$.ajax({
					 	type:"POST",
					 	url:url,
					 	data:data,
					 	cache: false,
					 	success:function(){
					 		$.dialog.tips('删除成功！');
					 		reloadAfter();
					 	},
					 	error:function(){
					 		$.dialog.tips('删除失败！');
					 		reloadAfter();
					 	}
					});
				});
			}else{
				alert('请先选择要删除的用户组!');
			}
		}
		function saveUserGroup(name,description,flag){
			var data = {'group.name':name,'group.description':description,'group.id':flag};
			var url
			if(flag){
				url = "hadoopUsers_updateUserGroup.do";
			}else {
				url = "hadoopUsers_insertUserGroup.do";
			}
			$.ajax({
				type:"POST",
				url:url,
				data:data,
				cache: false,
				success:function(){
					$.dialog.tips('保存成功！');
					reloadAfter();
				},
				error:function(){
					$.dialog.tips('保存失败！');
					reloadAfter();
				}
			});
		}
		function reloadAfter(){
			setTimeout("window.location.reload()",400)
		}
		
		function searchRequest(){
			theForm.submit();
		}
		function resetForm(){
			theForm.name.value = '';
		}
		
		//添加用户界面
		function addUsers2Group(groupId){
			$.dialog({
    			id:'add',
    			title:'增加用户',
    			width: '890px',
    			height: '480px',
    		    lock:true,
    			content: 'url:hadoopUsers_editGroupUsers.do?groupId='+groupId+"&flag=0"
    		});
		}
		function saveGroupUsers(groupId,userIds){
			var data = {'group.id':groupId,'user.id':userIds};
			var url = 'hadoopUsers_saveGroupUsers.do';
			$.ajax({
				type:"POST",
				url:url,
				data:data,
				cache: false,
				success:function(){
					$.dialog.tips('保存成功！');
				},
				error:function(){
					$.dialog.tips('保存失败！');
				}
			});
		}
		//从用户组中编辑用户界面
		function delUsersFromGroup(groupId){
			$.dialog({
    			id:'add',
    			title:'编辑用户',
    			width: '890px',
    			height: '480px',
    		    lock:true,
    			content: 'url:hadoopUsers_editGroupUsers.do?groupId='+groupId+"&flag=1"
    		});
		}
	</script>
</head>
<body>
	<s:form action="hadoopUsers_listUserGroup" method="post" id="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								用户组名称:
							</td>
							<td>
								<s:textfield name="group.name" id="name" cssClass="txt"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th>名称</th>
									<th>描述</th>
									<th>添加/删除用户</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#resultList" id="theBean">
									<tr>
										<td>
											<input type="checkbox" name="ids" value='<s:property value="#theBean.id"/>'/>
										</td>
										<td><s:property value="#theBean.name"/></td>
										<td><s:property value="#theBean.description"/></td>
										<td>
											<a href="javascript:addUsers2Group('<s:property value="#theBean.id"/>');">添加用户</a>&nbsp;&nbsp;
											<a href="javascript:delUsersFromGroup('<s:property value="#theBean.id"/>');">删除用户</a>
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
