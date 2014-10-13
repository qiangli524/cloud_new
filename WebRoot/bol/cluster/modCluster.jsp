<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
  	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
    api.button({
		id:'OkAnd',
		name: '确定',
		callback:modHost,
		focus: true
	},
	{
		id:'cancle',
		name: '取消'
	});
    function modHost(){
    	var name =$("#name").val();   //名称
		var descrip =$("#descrip").val();  //描述
		var status = $("#status").val(); //状态
		if(name==null || name==''){
			alert('请填写集群名称');
			return false;
		}
		if(descrip==null || descrip ==''){
			alert('请填写集群描述');
			return false;
		}
		var id = $("#id").val();
		if (id == null || id == '' || id == 0){
			$.ajax({
				type:"POST",
	            url:"bolcluster_save.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}else{
			$.ajax({
				type:"POST",
	            url:"bolcluster_update.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}
		w.searchRequest();
	}
	</script>
	<title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<input type="hidden" id="id" name="theForm.id" value="<s:property value='clusterId'/>"/>
			</tr>
			<tr>
				<td class="til" align="left">
					集群名称<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;" maxlength="128"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					状态<span style="color:red">*</span>
				</td>
				<td>
				<s:select name="theForm.status" list="#{'1':'正常','0':'异常'}"  id="status" style="width:150px;   height:20px;"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					描述<span style="color:red">*</span>
				</td>
				<td>
					<s:textarea name="theForm.descrip" id="descrip" style="width:350px;   height:80px;" maxlength="256"></s:textarea>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
  </body>
</html>
