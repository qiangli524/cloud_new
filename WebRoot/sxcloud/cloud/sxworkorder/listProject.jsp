<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
     <style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
    </style>
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
	     callback:selectPro,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });

   function selectPro(){
	   var proid = "";
	   var proleader = "";
	   var projectname = "";
	   var projectusername = "";
	   $(":checkbox:checked").each(function(){
		   proid += $(this).attr("value");
		   proleader += $(this).attr("proleader");
		   projectname += $(this).attr("projectname");
		   projectusername += $(this).attr("projectusername");
	   });
	   api.get("addWorkOrder").selectPro(proid,proleader,projectname,projectusername);
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#project_name").val("");
		   $("#project_no").val("");
	   });
   });
   
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="workorder_selectProject.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">项目名称:</td>
		                  		<td>
		                  			<s:textfield name="projectObj.PROJECT_NAME" id="project_name"></s:textfield> 
		                  		</td>
		                  		<td class="til">项目编号:</td>
		                  		<td>
		                  			<s:textfield name="projectObj.PROJECT_NO" id="project_no"></s:textfield> 
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
      <div class="blue-wrap noborder">
      				<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">项目编号</th>
						<th onclick="sort(theTable,2,'string')">项目名称</th>
						<th onclick="sort(theTable,3,'string')">项目负责人</th>
						<th onclick="sort(theTable,4,'string')">预分配cpu个数</th>
						<th onclick="sort(theTable,5,'string')">预分配内存大小</th>
						<th onclick="sort(theTable,6,'string')">预分配存储大小</th>
						<th onclick="sort(theTable,7,'string')">预分配ip个数</th>
						<th onclick="sort(theTable,8,'string')">网络域名称</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="projectList" id="theBean">
                		<tr >
                			<td><input type="checkbox" value="<s:property value="#theBean.ID"/>" 
                				proleader="<s:property value='#theBean.PROJECT_LEADER'/>" 
                				projectusername="<s:property value='#theBean.PROJECT_LEADERNAME' />"
                				projectname="<s:property value='#theBean.PROJECT_NAME'/>"/></td>
                			<td><s:property value="#theBean.PROJECT_NO"/></td>
                			<td>
                				<span style="color:black;" class="font-more" title='<s:property value="#theBean.PROJECT_NAME"/>'>
	                				<s:property value="#theBean.PROJECT_NAME"/>
                				</span>
                			</td>
                			<td><s:property value="#theBean.PROJECT_LEADERNAME"/></td>
                			<td><s:property value="#theBean.CPU_COUNT"/>个</td>
                			<td><s:property value="#theBean.MEMORY_SIZE"/>M</td>
                			<td><s:property value="#theBean.STORAGE_SIZE"/>M</td>
                			<td><s:property value="#theBean.IP_COUNT"/></td>
                			<td><s:property value="#theBean.NETNAME"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>