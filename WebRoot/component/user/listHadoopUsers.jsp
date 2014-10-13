<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    var api = frameElement.api;
	var w = api.wer;
	 api.button({
	     id:'OkAnd',
	     name: '确定',
	     callback:selectUsers,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	//点击添加用户时候，把用户id和对应ip形成已逗号分隔的字符串返回，注：id ip一一对应。
     function selectUsers(){
     		var userids ="";//选择用户的id
     		var userips="";//选择用户的ip
     		var usernames = "";//用户的用户名
     		$(":checkbox:checked").each(function(){
     			userids +=$(this).attr("value")+","; 
     			userips+=$(this).parent().next().text()+",";
     			usernames +=$(this).parent().next().next().text()+",";
      	 });
     	api.get("add").addHadoopUser(userids,userips,usernames);//需要调用窗口的id，大家都一样，都叫add
     }
	$(function(){
		 $("#searchForm").click(function(){
				$("#theForm").submit();
			});
			
		$("#resetForm").click(function(){
			$("#ip").val("");
			$("#username").val("");
		});
	});
    </script>
</head>
</head>
<body class="pop-body scrollbody">
    <s:form action="usermanage_listHadoopUsers.do" method="post" id="theForm" cssStyle="theForm">
	<div class="scrollbody">
		<div class="pd-20 bgcolor-1">
        	<div class="bord-1 pd-10">
				<table width="100%"  border="0">
					<tr>
						<td class="til">IP地址：</td>
						<td>
							<s:textfield name="theForm.ip" id="ip" size="15"></s:textfield>
						</td>
						<td class="til">用户名：</td>
						<td>
							<s:textfield name="theForm.username" id="username" size="15"></s:textfield>
						</td>
						<td colspan="10" class="pd-10">
							<div align="center">
								<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" /></span>
								<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" /></span>
							</div>
						</td>
					</tr>
				</table>
	           <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
	           	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">IP地址</th>
						<th onclick="sort(theTable,2,'string')">用户名</th>
						<th onclick="sort(theTable,3,'string')">密码</th>
						<th onclick="sort(theTable,4,'string')">用户类型</th>
					</tr>
				</thead>
	               <tbody>
	               	<s:iterator value="theForm.resultList" id="theBean">
	               		<tr >
	               			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
	               			<td><s:property value="#theBean.ip"/></td>
	               			<td><s:property value="#theBean.username"/></td>
	               			<td><s:property value="#theBean.password"/></td>
	               			<td>
	               				<s:if test="#theBean.type==0">
	               					普通用户
	               				</s:if>
	               				<s:elseif test="#theBean.type==1">
	               					管理员用户
	               				</s:elseif>
	               				<s:elseif test="#theBean.type==2">
	               					信息关系用户
	               				</s:elseif>
	               				<s:elseif test="#theBean.type==3">
	               					oracle安装用户
	               				</s:elseif>
	               				<s:elseif test="#theBean.type==4">
	               					监控用户
	               				</s:elseif>
	               			</td>
	               		</tr>
	               	</s:iterator>
	               </tbody>
	           </table>
           		<div class="pages mgb-10"><!-- 分页 -->
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
			</div>
		</div>
	</div>
    </s:form>
</body>