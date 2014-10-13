<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
	<script type="text/javascript">
	    var api = frameElement.api;
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:save,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });
		function save(){
			var groupId = $("#groupId").val();
			var userIds='';
			$('input[name="ids"]').each(function(){
				if(this.checked){
					userIds +=$(this).val()+',';
				}
			});
			var flag = '<s:property value="flag"/>';
			if(flag ==0){
				if(userIds){
					api.opener.saveGroupUsers(groupId,userIds);
				}else{
					alert('请先选择要添加的用户!');
					return false;
				}
			}else{
				api.opener.saveGroupUsers(groupId,userIds);
			}
		}
		
		function searchRequest(){
			var groupId  = $("#groupId").val();
			var flag = '<s:property value="flag"/>';
			theForm.action= 'hadoopUsers_editGroupUsers.do?groupId='+groupId+"&flag="+flag;
			theForm.submit();
		}
		function resetForm(){
			theForm.ip.value = '';
			theForm.username.value = '';
		}
		$(function(){
			$('#all').click(function(){
				if($(this).attr("checked")){
					$('input[name="ids"]').attr("checked",true);
				}else{
					$('input[name="ids"]').attr("checked",false);
				}
			})
		})
	</script>
</head>
<body class="scrollbody">
	<s:form action="hadoopUsers_editGroupUsers" method="post" id="theForm" >
	<s:hidden id="groupId" value="%{groupId}"></s:hidden>
		<div>
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
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										<s:if test="flag==0">
											<input type="checkbox" id='all'/> 
										</s:if>
										<s:elseif test="flag==1">
											<input type="checkbox" id='all' checked="checked"/> 
										</s:elseif>
									</th>
									<th>IP地址</th>
									<th>用户名</th>
									<th>密码</th>
									<th>类型</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td>
											<s:if test="flag==0">
												<input name="ids" value='<s:property value="#theBean.id"/>'  type="checkbox"/>
											</s:if>
											<s:elseif test="flag==1">
												<input name="ids" value='<s:property value="#theBean.id"/>'  type="checkbox" checked="checked"/>
											</s:elseif>
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
