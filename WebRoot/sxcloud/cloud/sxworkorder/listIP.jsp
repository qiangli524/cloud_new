<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
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
	$(function(){
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
	});

   function selectIP(){
	   var ipaddress = "";
	   $(":checkbox:checked").each(function(){
		   ipaddress += $(this).attr("ipaddress");
	   });
	   var oper = $("#oper").val();
	   if (oper == "add") {
		   api.get("addResource").selectip(ipaddress);
		} else {
		   api.get("editResource").selectip(ipaddress);
		}
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#ipaddress").val("");
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="workorder_selectIp.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
    <s:hidden name="net_id" id="net_id"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">IP地址:</td>
		                  		<td>
		                  			<s:textfield name="ipInfoObj.IPADDRESS" id="ipaddress"></s:textfield> 
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
						<th onclick="sort(theTable,1,'string')">IP地址</th>
						<th onclick="sort(theTable,2,'string')">是否使用</th>
						<th onclick="sort(theTable,3,'string')">是否被阻塞</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="ipList" id="theBean">
                		<tr >
                			<td>
                				<input type="checkbox" ipaddress='<s:property value="#theBean.IPADDRESS" />'/>
                			</td>
                			<td>
                				<s:property value="#theBean.IPADDRESS"></s:property>
                			</td>
                			<td>
                				<s:if test="#theBean.ISUSED==1">
									是
								</s:if>
								<s:elseif test="#theBean.ISUSED==0">
									否
								</s:elseif>
                			</td>
                			<td>
								<s:if test="#theBean.ISBLOCKED==1">
									是
								</s:if>
								<s:elseif test="#theBean.ISBLOCKED==0">
									否
								</s:elseif>
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