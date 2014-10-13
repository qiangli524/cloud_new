<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
     <style type="text/css">
		.font-more{ width:170px;height:20px;line-height:20px;overflow: hidden;
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
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectBusi,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectBusi(){
	   var busisystemid = $("#busisystemid").val();
	   var busiid = "";
	   var businame = "";
	   $(":checkbox:checked").each(function(){
		   busiid += $(this).attr("busiid");
		   businame += $(this).attr("businame");
	   });
	   api.get("addWorkOrder").selectBusi(busiid,businame,busisystemid);
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#businame").val("");
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="workorder_selectBusiSystem.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
    <s:hidden name="busisystemid" id="busisystemid"></s:hidden>
	<div class="box on">
			<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">业务系统名称:</td>
		                  		<td>
		                  			<s:textfield name="busiSystemObj.NAME" id="businame"></s:textfield> 
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
						<th onclick="sort(theTable,1,'string')">名称</th>
						<th onclick="sort(theTable,2,'string')">描述</th>
						<th onclick="sort(theTable,3,'string')">厂商</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="busiList" id="theBean">
                		<tr>
                			<td>
                				<input type="checkbox" busiid="<s:property value="#theBean.ID"/>"  businame='<s:property value="#theBean.NAME"/>'/>
                			</td>
                			<td>
	                			<s:property value="#theBean.NAME"/>
                			</td>
                			<td>
                				<span style="color:black;" class="font-more" title='<s:property value="#theBean.PROJECT_NAME"/>'>
	                				<s:property value="#theBean.DESC"/>
                				</span>
                			</td>
                			<td>
                				<s:property value="#theBean.OEM"/>
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