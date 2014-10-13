<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
    $(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
 	var api = frameElement.api;
	var w = api.opener;
	 api.button({
	     id:'OkAnd',
	     name: '确定',
	     callback:selectIP,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });

   function selectIP(){
   		var ip="";
   		var userid="";
   		var count = 0;
   		$(":checkbox:checked").each(function(){
   			count += 1;
   			ip+=$(this).parent().next().text();
   			userid+=$(this).attr("value");
    	 });
    	
   		if (count >1) {
			alert("只能关联一个ip");
			return false;
		}
   		api.get("editexample").addIP(ip,userid);
   }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
	<div class="box on">
      <div class="blue-wrap noborder">
       <div class="table-ct" >
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
                				<s:elseif test="#theBean.type==4">
                					Oracle类型用户
                				</s:elseif>
                				<s:else>
                					其他用户
                				</s:else>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
        </div>
    </s:form>
</body>