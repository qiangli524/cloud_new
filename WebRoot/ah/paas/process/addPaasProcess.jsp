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
<style>
.path {
	width: 337px;
	height: 20px;
	vertical-align: middle;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #9DBCD9;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
    //创建配置文件
	    var example_id = '<%=request.getAttribute("example_id")%>';
	    var hostIP = '<%=request.getAttribute("hostIP")%>';
	    var type1 = '<%=request.getAttribute("type")%>';
	   	var selectUsers = '';
	   	var userIps = '';
	   	var usernames = '';
	$(function(){
	   	 var oper =$("#oper").val();
		 var api = frameElement.api;
		 var w = api.opener;
			if("edit"==oper){
				$("[name='selectHost']").parents("tr").hide();
				 api.button({
				     id:'OkAnd',
				     name: '修改',
				     callback:addprocess,
				     focus: true
				 },
				 {
				     id:'cancle',
				     name: '取消'
				 });
			}else{
				 api.button({
				     id:'OkAnd',
				     name: '添加',
				     callback:addprocess,
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

		 function addprocess(){
			var ipUsers=$("[name='ip']").text();
			if(ipUsers<=0){
				alert("至少选择一个关联用户!");
				return false;
			}
			var process=$("#process").val();
		    if(process==null||Trim(process)==""){
			       alert("进程名称不能为空！");
			       return false  ;
			     }
		    var process_key=$("#process_key").val();
		    if(process_key==null||Trim(process_key)==""){
			       alert("进程标识不能为空！");
			       return false  ;
			     }
			//2为托管模式，进程的启动和停止脚本可以为null
		    var start_script=$("#start_script").val();
		    if(start_script==null||Trim(start_script)==""){
			       alert("启动脚本不能为空！");
			       return false;
			}
		    var stop_script=$("#stop_script").val();
		    if(stop_script==null||Trim(stop_script)==""){
			       alert("停止脚本不能为空！");
			       return false;
			}
     		w.saveProcess($("#theForm").serialize(),selectUsers,userIps,oper,example_id,type1);
		 }
		 
		 $("[name='selectHost']").unbind().live("click",function(){
			// var example_id = $("#exampleId").val();
			 var hostIP = $("#hostIP").val();
			// var typer = $("#typer").val();
	  		w.$.dialog({
    			id:'addHostIp',
    			title:'添加主机',
    			width: '750px',
    			height: '600px',
    		    lock:true,
    			content: 'url:usermanage_listHadoopUsers.do?theForm.ip='+hostIP
	    		});
		 });

	     $("#relationscript").change(function(){
	            var temp=$(this).val();
	            if("0"==temp){
	                $("#start_script").attr("readonly",false);
	                $("#stop_script").attr("readonly",false);
	              }else if("1"==temp){
	            	  $("#start_script").attr("readonly",true);
	                  $("#stop_script").attr("readonly",true);
	              }else if("2"==temp){
	                  $("#start_script").attr("readonly",false);
	                  $("#stop_script").attr("readonly",false);
	            	  alert("你好：托管模式下进程的权限将交于上级管理.");
	              }else{
	                  $("#start_script").attr("readonly",false);
	                  $("#stop_script").attr("readonly",false);
	              }
		});
	     $("[type='script_a']").unbind().live("click",function(){
		    //	var temp=$("#relationscript option:checked").val();
		    //	if(temp!=1){//只有在选中关联脚本的时候才可以点击
		    //		alert("请在在管理脚本中选择关联脚本，再点击进行关联.");
		    //		return ;
		    //	}
		    	var id=$(this).attr("id");
		  		w.$.dialog({
	     			id:'add1',
	     			title:'关联脚本',
	     			width: '850px',
	     			height: '520px',
	     		    lock:true,
	     			content: 'url:script_list.do?oper=process&script='+id
	 	    		});
		     });	     
	});
	
	function addScript(path,script){
		if("start"==script){
			$("#start_script").val(path);
			 $("#start_script").attr("readonly",true);
		}else if("stop"==script){
			$("#stop_script").val(path);
             $("#stop_script").attr("readonly",true);
		}else{
			alert("脚本类型错误,请检查addProcess.jsp")
		}
	}
    function addHadoopUser(ids,ips,names){
    	selectUsers=ids;
    	userIps=ips;
    	usernames = names;
        var strIds=ids.split(",");
        var strIps=ips.split(",");
        var temp="已选择:<label name=\"ip\" key="+userids+">"+(strIps.length-1)+"</label>个";
        $("[name='ips']").empty();
        $("[name='ips']").append(temp);
    }

    $(function(){
    	userids=$("#ipidvalue").val();
    	userips=$("#ipvalue").val();
    	if(userips!=null&&userips!=""){
    	   hostIP=userips;
        	}
    	selectUsers=userids;
    	if(userips!=null&&userips!=""){
	    	var temp=userips.indexOf(",");
	    	if(temp==-1){
	    		userips+=",";
	        }
        }
    	var strIds=userids.split(",");
        var strIps=userips.split(",");
        var temp="已选择:<label name=\"ip\" key="+userids+">"+(strIps.length-1)+"</label>个";
    	$("[name='ips']").empty();
    	$("[name='ips']").append(temp);
    });
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="processObj.IP" id="ipvalue"></s:hidden>
	    <s:hidden name="processObj.USER_ID" id="ipidvalue"></s:hidden>
	    <s:hidden name="processObj.ID" id="processid"></s:hidden>
	    <s:hidden name="oper" id="oper"></s:hidden>
	    <s:hidden name="example_id" id="exampleId"></s:hidden>
	    <s:hidden name="hostIP" id="hostIP"></s:hidden>
	    <s:hidden name="type" id="typer"></s:hidden>
<%--	    //部署管理设置为1--%>
	    <s:hidden name="processObj.TYPE" value="1"></s:hidden>
		<bean:define id="theForm" name="BusiAppScriptForm" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">关联用户:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    </td>
				</tr>			
				<tr>
				    <td class="til">
				       进程名称:<font color="red">*</font>
				    </td>
				    <td colspan="5">
				   	 <s:textfield name="processObj.PROCESS" cssClass="txt" id="process"></s:textfield>
				    </td>		
				</tr>
				<tr>
				    <td class="til">
				       进程标识:<font color="red">*</font>
				    </td>
				    <td colspan="5">
				   	 <s:textfield name="processObj.PROCESS_KEY" cssClass="txt" id="process_key"></s:textfield>
				    </td>		
				</tr>
				<tr>
				    <td class="til">
				       同标识个数:<font color="red">*</font>
				    </td>
				    <td>
				   	 <s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16'}" name="processObj.PROCESS_COUNT" id="processcount" ></s:select>
				    </td>		
				</tr>
				<tr>
				    <td class="til" id="start_script_link">
				       <a href="javascript:;" type="script_a" id="start" style="color: blue;text-decoration: underline;">启动脚本:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" >
				    <s:textfield name="processObj.START_SCRIPT" cssClass="path" id="start_script" readonly="true"></s:textfield>
				    </td>
				</tr>
				<tr>
				  <td class="til" id="stop_script_link">
					<a href="javascript:;" type="script_a" id="stop" style="color: blue;text-decoration: underline;">停止脚本:</a><font color="red">*</font>
					</td>
					<td colspan="5">
					 <s:textfield name="processObj.STOP_SCRIPT" cssClass="path" id="stop_script" readonly="true"></s:textfield>
					</td>
				</tr>
				<%--<tr>
					 <td class="til">
				     	 自定义监控:<font color="red">*</font>
				    </td>
				    <td>
				   	 <s:select list="#{'1':'默认','2':'jps'}" name="processObj." id="processcount" ></s:select>
				    </td>	
				</tr>
				--%>
				<tr>
					<td class="til">
						描述
					</td>
					<td>
						<s:textarea cols="40" rows="4" name="processObj.PROCESS_DESC" cssStyle="border: 1px solid #9DBCD9;overflow-y:auto;"></s:textarea>
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
