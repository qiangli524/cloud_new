<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	
    var oper = '<%=request.getAttribute("oper")%>';
    var eid = '<%=request.getAttribute("eid")%>';
   	var api = frameElement.api;
	var w = api.opener;

	$(function(){
		 
		 if("add"==oper){
			 api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addexample,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }else{
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:addexample,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }
		 
		 function Trim(str){
				return str.replace(/^\s+|\s+$/g,"");
			}
	
		 function addexample(){
			var example_name=$("#example_name").val();
		    if(example_name==null||Trim(example_name)==""){
			       alert("实例名不能为空");
			       return false  ;
			}
			var example_ip=$("#example_ip").val();
		    if(example_ip==null||Trim(example_ip)==""){
			       alert("ip地址不能为空");
			       return false  ;
			}
			var data_file_path=$("#data_file_path").val();
		    if(data_file_path==null||Trim(data_file_path)==""){
			       alert("数据库文件路径不能为空");
			       return false  ;
			}
			var sys_pass=$("#sys_pass").val();
		    if(sys_pass==null||Trim(sys_pass)==""){
			       alert("sys口令不能为空");
			       return false  ;
			}
		    var userid = $("#userid").val();
		    $.ajax({
		    	type:'post',
		    	dataType:'json',
		    	async:false,
		    	url:'dbexample_checkPath.do?ip='+example_ip+'&data_file_path='+data_file_path+'&userid='+userid,
		    	success:function(data){
		    		if (!data.result) {
			    		alert("路径不存在！请重新填写");
			    		return false;
					} 
		    	}
		    });
     		w.saveExample($("#theForm").serialize(),oper);
		 }
		 
		 
		 $("#example_ip").click(function(){
			 w.$.dialog({
      			id:'selectip',
      			title:'选择ip',
      			width: '750px',
      			height: '470px',
      		    lock:true,
      			content: 'url:usermanage_queryUsersByType.do?type=3'
  	    	});
		 });
	});
	
    function addIP(ip,userid){
    	$("#example_ip").val(ip);
    	$("#userid").val(userid);
    }
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	<s:hidden name="dbExampleObj.id" id="eid"></s:hidden>
	<s:hidden name="dbExampleObj.userid" id="userid"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr align="left">
				<td class="til">实例名称<font color="red">*</font></td>
				<td>
					<s:textfield name="dbExampleObj.example_name" id="example_name" style="border: 1px solid #9DBCD9;width:335px;height:20px;"></s:textfield>
				</td>
			</tr>
			<tr align="left">
				<td class="til">IP地址<font color="red">*</font></td>
				<td>
					<s:textfield name="dbExampleObj.example_ip" id="example_ip" style="border: 1px solid #9DBCD9;width:335px;height:20px;" readonly="true"></s:textfield>
				</td>
			</tr>
			<tr align="left">
				<td class="til">数据库文件路径<font color="red">*</font></td>
				<td>
					<s:textfield name="dbExampleObj.data_file_path" id="data_file_path" style="border: 1px solid #9DBCD9;width:335px;height:20px;" ></s:textfield>
				</td>
			</tr>
			<tr align="left">
				<td class="til">sys口令<font color="red">*</font></td>
				<td>
					<s:textfield name="dbExampleObj.sys_pass" id="sys_pass" style="border: 1px solid #9DBCD9;width:335px;height:20px;" ></s:textfield>
				</td>
			</tr>
		</table>
	</s:form>
</body>

</html:html>
