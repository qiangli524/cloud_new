<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    
     	var api = frameElement.api;
		var w = api.opener;
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectFile,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });

       function selectFile(){
       		var versionids ="";
       		var path="";//路径
       		var count = 0;
       		
       		$(":checkbox:checked").each(function(){
       			count += 1;
       			versionids +=$(this).attr("value"); 
       			path+=$(this).parent().next().next().next().next().next().text();
        	 });
        	
       		if (count >1) {
				alert("只能关联一个版本");
				return false;
			}
			var userType=$("#userType").val();
			if(userType=="quickaddtask"){
			    api.get("quickcreateorder").addFile(versionids,path);
			}else{
	       		api.get("editorder").addFile(versionids,path);
			}
       }
	   
       /*
       $(function(){
	   		$check = $(":checkbox");
	   		$check.live("click",function(){
	   			$check.not(this).attr("checked",false);
	   		});
   	   })
   	   */
    </script>
</head>
<body>
<s:form action="" id="theForm" method="post" cssClass="theForm">
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.STRIDS" id="STRIDS"></s:hidden>
	<s:hidden name="userType" id="userType"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">版本包</th>
				   <th onclick="sort(theTable,1,'string')">所属应用</th>
				   <th onclick="sort(theTable,1,'string')">版本号</th>                
                   <th onclick="sort(theTable,1,'string')">版本状态</th>
                   <th onclick="sort(theTable,1,'string')">版本包存放位置</th>
                   <th onclick="sort(theTable,1,'string')">版本描述</th>
                   <th onclick="sort(theTable,1,'date')">创建时间</th>
                   <th onclick="sort(theTable,1,'string')">创建人</th>
                   <th onclick="sort(theTable,1,'date')">使用时间</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="theForm.fileVersionList" id="theBean">
				<tr>
					<td><input name="checked" type="checkbox" value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.NAME" /></td>
					<td><s:property value="#theBean.APPNAME" /></td>
					<td><s:property value="#theBean.NO" /></td>
					<td>
			  			<s:if test="#theBean.STATUS==0">
			  				未使用
			  			</s:if>
			  			<s:elseif test="#theBean.STATUS==1">
			  				已使用
			  			</s:elseif>
			  			<s:elseif test="#theBean.STATUS==2">
			  				版本错误
			  			</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.LOCATION"/></td>
			  		<td><s:property value="#theBean.DESCRIBTION"/></td>
					<td><s:property value="#theBean.CREATED_TIME" /></td>
					<td><s:property value="#theBean.CREATED_USER" /></td> 
					<td><s:property value="#theBean.USED_TIME" /></td>
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
