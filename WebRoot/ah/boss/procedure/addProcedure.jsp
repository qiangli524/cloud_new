<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<title></title>
<script type="text/javascript">

$(function(){
  	 var oper =$("#oper").val();
	 var api = frameElement.api;
	 var w = api.opener;
		if("add"==oper){
			api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:saveProcedure,
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
			     callback:saveProcedure,
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
	//保存操作
	function saveProcedure(){
		var program_name = $("#program_name").val();
		var operator_id = $("#operator_id").val();
		var install_status = $("#install_status").val();
		var ip = $("#install_host_id").val();
		var user_id = $("#hostUser").val();
		var cluster_id = $("#cluster_id").val(); 
		var app_pool = $("#app_pool").val();
		var start_script = $("#startScript").val(); 
		var stop_script = $("#stopScript").val();
		if(Trim(program_name)=='' || Trim(operator_id)=='' || Trim(install_status)=='' || ip == ''){
			alert("请填写完整");
			return false;
		}
		if(Trim(user_id)=='' || Trim(cluster_id)=='' || Trim(app_pool)=='' || Trim(start_script)=='' || Trim(stop_script)==''){
			alert("请填写完整");
			return false;
		}
		w.saveProcedure($("#theForm").serialize());
	}
	//用于给用户选项赋值
	var oper = '<%=request.getAttribute("oper")%>';
	var user_id = "<s:property value='obj.user_uid'/>";
	var user_name = '<%=request.getAttribute("userName")%>';
	if(user_id == null){
		user_id="";
		user_name="";
	}
	if(oper == "edit"){
		var option = "<option value='"+user_id+"' selected='selected'>"+user_name+"</option>";
		$("#hostUser").append(option);
	}
});
	//选择主机后查询主机关联用户
	function queryUsersByHostId(){
		  var form=document.getElementById('theForm');
		  var html="";
		  var id=$("#install_host_id option:selected").val();
	      $.getJSON("usermanage_getUserListByHostId.do?hostId="+id,{'time':new Date().toString()},function(data){
			for(var i=0;i<data.length;i++)
			{
				html=html+"<option value='"+data[i].id+"'>"+data[i].username+"</option>";		
			}
			//$("#hostUser").html("");可以不删
			$("#hostUser").html(html);
		   });
	}
	//检查主机是否已选
	function checkHost(){
		if($("#install_host_id").val().length == 0){
			alert("请先选择部署主机！");
			$("#install_host_id").focus();
			return false;
		}
	}
	//检查集群和进程名称是否联合唯一
	function checkIsExist(){
		var program_name = $("#program_name").val();
		var cluster_id = $("#cluster_id").val();
		//var ipN = $("#install_host_id option:selected").text();
		//都不为空时做判断
		if(program_name != '' && cluster_id != ''){
			$.ajax({
				 type: "post",
				 url: "bossProcedure_checkIsExist.do?obj.program_name="+program_name+"&obj.cluster_id="+cluster_id,
				 dataType: "json",
				 async:true,
				 cache:false,
				 success : function(msg){
				 			$("#program_name").parent().find("font").remove();
							$("#program_name").parent().append("<font color='red' >"+msg+"</font>");
					}
		 	 });
		}
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="obj.uid"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr align="left">
				<td class="til">进程名称<font color="red">*</font></td>
				<td colspan="4"><s:textfield onblur="javascript:checkIsExist();" name="obj.program_name" cssStyle="width:80%;" cssClass="txt"
						id="program_name"></s:textfield></td>
			</tr>
			<tr>
				<td class="til">启停标识<font color="red">*</font></td>
				<td>
					<s:select list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"
						name="obj.operator_id" id="operator_id"></s:select>
				</td>
				<td class="til">安装状态<font color="red">*</font></td>
				<td><s:select list="#{'':'--请选择--','1':'完成','2':'已卸载'}"
						name="obj.install_status" id="install_status"></s:select>
				</td>
			</tr>
			<tr>
                <td class="til">部署主机<font color="red">*</font></td>
                <td>
	                <s:select onchange="queryUsersByHostId();"  list="obj.hostList" id="install_host_id" headerKey="" headerValue="---请选择---" 
						listKey="ID" listValue="IP" name="obj.host_id" >
					</s:select>
				</td>
				<td class="til">主机用户<font color="red">*</font></td>
				<td>
                	<select id="hostUser" onfocus="checkHost();" name="obj.user_uid">
                		<option value="" >--请选择--</option>
                	</select>
                </td>
            </tr>
			<tr>
				<td class="til">集群名称<font color="red">*</font></td>
				<td>
					<s:select list="obj.clusterList" onblur="javascript:checkIsExist();" id="cluster_id" headerKey="" headerValue="---请选择---" 
						listKey="nodeId" listValue="nodeName" name="obj.cluster_id" >
					</s:select>
				</td>
				<td class="til">程序池<font color="red">*</font></td>
				<td>
					<s:select list="obj.poolList" id="app_pool" headerKey="" headerValue="---请选择---" 
						listKey="nodeId" listValue="nodeName" name="obj.app_pool" >
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="til" id="start_script_link">启动脚本<font color="red">*</font></td>
				<td colspan="8" ><s:textfield name="obj.startScript"  cssStyle="width:80%;"
						cssClass="path txt" id="startScript" ></s:textfield></td>
			</tr>
			<tr>
				<td class="til" id="stop_script_link">停止脚本<font color="red">*</font></td>
				<td colspan="8"><s:textfield name="obj.stopScript" cssStyle="width:80%;"
						cssClass="path txt" id="stopScript" ></s:textfield></td>
			</tr>
			<tr>
				<td class="til">备注</td>
				<td colspan="10"><s:textarea name="obj.note"
						cssClass="txt" id="note" cols="35" rows="5"></s:textarea></td>
			</tr>
		</table>
	</s:form>
</body>
</html>		