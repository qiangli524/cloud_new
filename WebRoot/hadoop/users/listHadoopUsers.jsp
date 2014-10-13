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
    			title:'增加用户',
    			width: '890px',
    			height: '480px',
    		    lock:true,
    			content: 'url:hadoopUsers_addUser.do'
    			});
	    }
	    
	    function modRequest(){
	    	var selected = $('input[name="ids"]:checked').val();
	    	if(selected){
	    		var url = "hadoopUsers_updateUser.do";
	    		$.dialog({
    				id:'add',
    				title:'修改用户',
    				width: '890px',
    				height: '480px',
    		    	lock:true,
    				content: 'url:hadoopUsers_editUser.do?id='+selected
    			});
	    	}else{
	    		alert('请先选择要修改的用户信息!');
	    	}
	    }
		function delRequest(){
			var selected = $('input[name="ids"]:checked').val();
			if(selected){
				var url = "hadoopUsers_deleteUser.do";
				var data = {'user.id':selected};
				$.dialog.confirm('你确定要删除吗？', function(){
					$.ajax({
					 	type:"POST",
					 	url:url,
					 	data:data,
					 	cache: false,
					 	success:function(){
					 		$.dialog.tips('删除成功！');
					 		window.location.reload();
					 	},
					 	error:function(){
					 		$.dialog.tips('删除失败！');
					 		window.location.reload();
					 	}
					});
				});
			}else{
				alert('请先选择要删除的用户!');
			}
		}
		function saveUser(ip,username,password,type,flag){
			var data = {'user.ip':ip,'user.username':username,'user.password':password,'user.type':type,'user.id':flag};
			var url
			if(flag){
				url = "hadoopUsers_updateUser.do";
			}else {
				url = "hadoopUsers_insertUser.do";
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
			theForm.ip.value = '';
			theForm.username.value = '';
		}
	</script>
</head>
<body>
	<s:form action="hadoopUsers_listUsers" method="post" id="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								IP地址:
							</td>
							<td>
								<s:textfield name="user.ip" id="ip" cssClass="txt"></s:textfield>
							</td>
							<td class="til">
								用户名:
							</td>
							<td>
								<s:textfield name="user.username" id="username" cssClass="txt"></s:textfield>
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
									<th>
										<input type="checkbox"/> 
									</th>
									<th>IP地址</th>
									<th>用户名</th>
									<th>密码</th>
									<th>类型</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#resultList" id="theBean">
									<tr>
										<td>
											<input name="ids" value='<s:property value="#theBean.id"/>'  type="checkbox"/>
										</td>
										<td><s:property value="#theBean.ip"/></td>
										<td><s:property value="#theBean.username"/></td>
										<td>*************</td>
										<td>
											<s:if test="#theBean.type==0">普通用户</s:if>
											<s:elseif test="#theBean.type==1">管理员</s:elseif>
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
