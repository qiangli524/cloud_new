<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
	
	$(function(){
		var caozuo = '<%=request.getAttribute("caozuo")%>';
		if ("addp" == caozuo) {
			var api = frameElement.api;
			var w = api.opener;
			 api.button({
			     id:'OkAnd',
			     name: '确定',
			     callback:selectPower,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });

		   function selectPower(){
		   		var powerid="";
		   		var exampleUserId = $("#euid").val();
		   		$(":checkbox:checked").each(function(){
		   			var $td = $(this).parent();
		   			powerid = $td.attr("powerid");
		    	 });
				if(""==powerid||null==powerid||powerid.length==0){
					alert("请选择一个需要添加的权限!");
					return false;
				}
				api.get("addexampleuser").addPower(powerid,exampleUserId);
		   }
		} else {
			$("#deleteAut").show();
			$("#deleteAut").click(function(){
				var powerid="";
		   		var exampleUserId = $("#euid").val();
		   		$(":checkbox:checked").each(function(){
		   			var $td = $(this).parent();
		   			powerid = $td.attr("powerid");
		    	 });
				if(""==powerid||null==powerid||powerid.length==0){
					alert("请选择一个需要删除的权限!");
					return false;
				}
	        	mask('正在删除数用户权限,请稍后....','0.7','0px');
	        	$.ajax({
	        		type:'post',
	        		dataType:'json',
	        		url:'dbexample_delUserPower.do?exampleUserId='+exampleUserId+'&powerid='+powerid,
	        		success:function(msg){
	        			removeMask();
	        			if (msg.result == -1) {
	        				alert("删除失败!");
			                return false;
						} else {
							alert("删除成功!");
							$td.parent().remove();
						}
	        		}
	        	});
			});
		}
	});
 	
    </script>
</head>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="exampleUserId" id="euid"></s:hidden>
	<div class="box on">
      <div class="blue-wrap noborder">
<%--      	<div class="table-head">--%>
<%--				<ul class="btns">--%>
<%--                    <li colspan="5" class="btnCenter">--%>
<%--                           <input type="button"  value="删除"  class="btn-style02" name="deleteAut" id="deleteAut" style="display: none"/>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--    	</div>--%>
      
       <div class="table-ct" >
            <table id="theTable"  width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th onclick="sort(theTable,0,'string')">选择</th>
						<th onclick="sort(theTable,1,'string')">权限</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="powerList" id="theBean">
                		<tr>
                			<td powerid='<s:property value="#theBean.ID"/>'><input type="checkbox" name="select"/></td>
                			<td><s:property value="#theBean.POWER_NAME"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>