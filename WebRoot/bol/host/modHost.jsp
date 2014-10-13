<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
  	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<title></title>
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
		var ipaddress =$("#ipaddress").val();  //ip
		var descrip =$("#descrip").val();  //描述
		var status = $("#status").val(); //状态
		if(ipaddress==null || ipaddress==''){
			alert('请填写主机IP地址！');
			return false;
		}
		if(!isIp(ipaddress.toString())){
			alert('ip地址有误，请重新输入');
			return false;
		}
		if(name==null || name==''){
			alert('请填写主机名称');
			return false;
		}
		if(descrip==null || descrip ==''){
			alert('请填写主机描述');
			return false;
		}
		var id = $("#id").val();
		if (id == null || id == '' ||　id == 0){
			$.ajax({
				type:"POST",
	            url:"bolhost_save.do",
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
	            url:"bolhost_update.do",
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
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<input type="hidden" id="id" name="theForm.id" value="<s:property value='hostId'/>"/>
			<tr>
				<td class="til" align="left">
					主机名称<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;" maxlength="128"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" align="left">
					主机IP<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.ipaddress" id="ipaddress" style="width:150px;   height:20px;" maxlength="128"></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td class="til" align="left">
					主机类型<span style="color:red">*</span>
				</td>
				<td>
					<s:select name="theForm.hostType" list="#{'1':'BOL Master','2':'BOL Slave'}"  id="hostType" style="width:150px;   height:20px;"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					主机能力<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.capability" id="capability" style="width:150px;   height:20px;" maxlength="128"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					激活状态<span style="color:red">*</span>
				</td>
				<td>  
					<s:select name="theForm.isActive" list="#{'0':'非激活','1':'已激活 '}"  id="isActive" style="width:150px;   height:20px;"></s:select>
				</td>
			</tr>
			
			<tr>
				<td class="til" align="left">
					状态<span style="color:red">*</span>
				</td>
				<td>
				<s:select name="theForm.status" list="#{'2':'正常','0':'异常','1':'空闲','3':'繁忙 '}"  id="status" style="width:150px;   height:20px;"></s:select>
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
