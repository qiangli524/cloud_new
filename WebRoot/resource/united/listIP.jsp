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
	   var length=0;
	   $(":checkbox:checked").each(function(){
		   ipaddress += $(this).attr("ipaddress");
		   length +=1;
	   });
	   if(length==0){
		  alert("请先选择IP地址，再点击确定按钮");
		  return false;
	   }else{
		   var name = '<s:property value="name" />';
		   var vm_num = '<s:property value="vm_num" />';
		   if(name=="addVM"){
			   api.get("addVM").selectip(ipaddress,vm_num);
		   }else if(name=="cloneVm-nm"){
			   w.selectip(ipaddress);
		   } else if(name=="addResource"){
			   api.get("addResource").selectip(ipaddress,vm_num);
		   }else{
			   api.get("cloneVM").selectip(ipaddress,vm_num);
		   }
		   
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
    <s:form action="united_selectIpByVlan.do" method="post" id="theForm" >
    <s:hidden name="uuid" id="uuid"></s:hidden>
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
                	<s:iterator value="resultList" id="theBean">
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

    </s:form>
</body>