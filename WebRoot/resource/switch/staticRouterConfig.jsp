<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:updateConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    function updateConfig(){
    	var url ="staticRouterAction_save.do?"+$("#obj").serialize();
    	api.get("staticRouter").staticRouterConfig(url);
	}

	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="obj" id="obj" >
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<s:hidden type="hidden" id="switch_id" name="switchId"/> 
		  	<s:hidden type="hidden" id="id" name="obj.id"/> 
			<tr>
				<td class="til" width="20%" align="left">
					目的地址<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="obj.source_ip" id="source_ip" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					子网掩码<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="obj.subnet_mask" id="subnet_mask" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					网关地址<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="obj.ip" id="ip" ></s:textfield>
				</td>
			</tr>
			
		</table>
	</div>
	</s:form>
  </body>
</html>
