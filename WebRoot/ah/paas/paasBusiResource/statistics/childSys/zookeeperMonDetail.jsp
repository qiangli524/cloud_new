<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
</style>
<style type="text/css">
		
</style>
<script type="text/javaScript">
  	mask('正在查询,请稍后....','0.5','0px');
	 function chang(){
	 	var va=$("#sel").val();
	 	if(va == 0){
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 	}else if(va == 1){//只显示异常
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 		$("[name='state']").each(function(){
	    	if($(this).find("font").html() == '正常'){
	    			$(this).parent().hide();//隐藏行
	    		}
	    	});
	 	}else{//只显示正常
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 		$("[name='state']").each(function(){
	    	if($(this).find("font").html() == '异常'){
	    			$(this).parent().hide();
	    		}
	    	});
	 	}
	 }
	$(function(){
		//默认显示异常的
		$("[name='state']").each(function(){
	    	if($(this).find("font").html() == '正常'){
	    			$(this).parent().hide();
	    		}
	    	});
	    removeMask();
	});	 
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="zookeeperMonitor_monitorTree.do" method="post" cssClass="obj" id="obj" name="obj">
<div style="padding: 10px;">
	<div style="border-bottom: 1px dashed #b5d4f3;padding-bottom: 10px;">
		<font size="20px">请选择需要显示的数据:&nbsp;&nbsp;&nbsp;</font><select id="sel" onchange="chang();" style="width: 100px;">
			<option value="0">全部</option>
			<option value="1" selected="selected">异常</option>
			<option value="2">正常</option>
		</select>
	</div>
	<div class="table-ct">
	<table  width="100%" border="0" cellspacing="0" class="blue-table sorttable">
		 <s:iterator value="mapResult">	
				<tr>
					<td colspan="10" align="left" name="total">
						父路径: <s:property value="key"/>
					</td>
				</tr>
				 <s:iterator value="value" id="st">	
				 <tr>
				 	<td>节点名称：</td>
				 	<td><s:property value="#st.nodeName"/></td>
				 	<td>状态:</td>
				 	<td name="state">
				 		<s:if test="#st.flag == false">
				 			<font color="red">异常</font>
				 		</s:if>
				 		<s:else><font color="blue">正常</font></s:else>
				 	</td>	
				 </tr>
				 </s:iterator>	
		 </s:iterator>
	</table>
	</div>
</div>
</s:form>
</html:html>	