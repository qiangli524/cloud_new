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
	//当前页面是编辑还是添加
	var userids="";
	var userips="";
	$(function(){
		var oper=$("#oper").val();
		var api = frameElement.api;
		var w = api.opener;
		if("edit"==oper){	
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
	
		if("edit"==oper){
			$("[name='processdisplay']").hide();
		}
		 
	    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
		}

		 function addprocess(){
			var level=$("#level option:selected").val();
			if("add"==oper){
				if("0"==level){
					var ipUsers=$("[name='ip']").text();
					if(ipUsers<=0){
						alert("至少选择一个关联用户!");
						return false;
					}
				}else{
					var ipUsers=$("[name='ip']").text();
					if(ipUsers<=0){
						alert("至少选择一个父进程!");
						return false;
					}
				}
			}
			var process=$("#process").val();
		    if(process==null||Trim(process)==""){
			       alert("进程名称不能为空！");
			       return false;
			}
		    var process_key=$("#process_key").val();
		    if(process_key==null||Trim(process_key)==""){
			       alert("进程标识不能为空！");
			       return false;
			}
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
			if("0"==level){
     		    w.saveProcess($("#theForm").serialize(),userids,userips,oper);
	    	}else{
	    	    $("#parentProcessIds").val($("[name='ip']").attr("key"));
	    	    $("#parentProcessUserIds").val($("[name='ip']").attr("key1"));
	    		w.saveParentProcess($("#theForm").serialize(),oper);
	    	}
		 }
		 
		 $("[name='selectHost']").unbind().live("click",function(){
	  		w.$.dialog({
    			id:'addHostIp',
    			title:'添加主机',
    			width: '750px',
    			height: '470px',
    		    lock:true,
    			content: 'url:usermanage_list.do?userids='+userids
	    		});
		 });
		 
		 $("[name='selectParentProcess']").unbind().live("click",function(){
			    var tempParentProcessIds=$("[name='ip']").attr("key");
		  		w.$.dialog({
	    			id:'parentprocess',
	    			title:'关联父进程',
	    			width: '950px',
	    			height: '470px',
	    		    lock:true,
	    		    button: [
	    		             {
	    		                 name: '添加',
	    		                 callback:function(){
	    		                	var t=w.$.dialog.list["parentprocess"].content.addParentProcess();
	    		                	var array=t.split("~");
	    		             		var parentProcessIds=array[0];
	    		             		var userid=array[1];
	    		             		if(parentProcessIds!=null&&parentProcessIds!=""){
		    		                    var tParentProcessIds=parentProcessIds.split(",");
		    		                    var temp="<a href=\"javascript:;\" name=\"selectParentProcess\" style=\"color: blue;\">已选择:<label name=\"ip\" key="+parentProcessIds+" key1="+userid+">"+(tParentProcessIds.length-1)+"</label>个</a>";
		    		                    $("[name='ips']").empty();
		    		                    $("[name='ips']").append(temp);        		
	    		             		}else{
	    		             			var temp="<a href=\"javascript:;\" name=\"selectParentProcess\" style=\"color: blue;\">已选择:<label name=\"ip\" key='' key1=''>0</label>个</a>";
		    		                    $("[name='ips']").empty();
		    		                    $("[name='ips']").append(temp);     
	    		             		}
	    		                 },
	    		                 focus: true
	    		             },
	    		             {
	    		                 name: '取消'
	    		             }
	        	      ],
	    			content: 'url:process_listAllProcess.do?userType=processParent&parentProcessIds='+tempParentProcessIds
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
	  //  	var temp=$("#relationscript option:checked").val();
	  //  	if(temp!=1){//只有在选中关联脚本的时候才可以点击
	   // 		alert("请在在管理脚本中选择关联脚本，再点击进行关联.");
	   /// 		return ;
	   // 	}
	    	var id=$(this).attr("id");
	  		w.$.dialog({
     			id:'add1',
     			title:'关联脚本',
     			width: '750px',
     			height: '470px',
     		    lock:true,
     			content: 'url:script_list.do?oper=process&script='+id
 	    		});
	     });
	     
	     $("#level").change(function(){
	    	 var temp=$(this).val();
	    	 if("1"==temp){//子进程
	    		 $("#tr_type").hide();
	    		 $("#relation-process-parent").empty();
	    		 $("#relation-process-parent").append("<a href=\"javascript:;\" name=\"selectParentProcess\" style=\"color: blue;text-decoration: underline;\">关联父进程:</a><font color=\"red\">*</font>");
	    	 }else{
	    		 $("#tr_type").show();
	    		 $("#relation-process-parent").empty();
	    		 $("#relation-process-parent").append("<a href=\"javascript:;\" name=\"selectHost\" style=\"color: blue;text-decoration: underline;\">关联用户:</a><font color=\"red\">*</font>");
	    	 }
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
	
    
    function addUser(ids,ips){
    	userids=ids;
    	userips=ips;
        var strIds=ids.split(",");
        var strIps=ips.split(",");
        var temp="<a href=\"javascript:;\" name=\"selectHost\" style=\"color: blue;\">已选择:<label name=\"ip\" key="+userids+" >"+(strIps.length-1)+"</label>个</a>";
        $("[name='ips']").empty();
        $("[name='ips']").append(temp);
    }

    $(function(){
    	userids=$("#ipidvalue").val();
    	userips=$("#ipvalue").val();
    	if(userips!=null&&userips!=""){
	    	var temp=userips.indexOf(",");
	    	if(temp==-1){
	    		userips+=",";
	        }
        }
    	var strIds=userids.split(",");
        var strIps=userips.split(",");
        var temp="<a href=\"javascript:;\" name=\"selectHost\" style=\"color: blue;\">已选择:<label name=\"ip\" key="+userids+">"+(strIps.length-1)+"</label>个</a>";
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
	    <s:hidden name="parentProcessIds" id="parentProcessIds"></s:hidden>
	    <s:hidden name="parentProcessUserIds" id="parentProcessUserIds"></s:hidden>
	    <s:hidden name="oper" id="oper"></s:hidden>
		<bean:define id="theForm" name="BusiAppScriptForm" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr name="processdisplay">
				    <td class="til">
				                    进程级别
				    </td>
				    <td colspan="5">
				       <s:select list="#{'0':'父进程','1':'子进程'}" name="processObj.LEVEL" id="level" ></s:select>
				    </td>
				</tr>	
				<tr name="processdisplay">
				    <td class="til" id="relation-process-parent">
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
				       进程唯一标识:<font color="red">*</font>
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
				<tr align="left" id="tr_type">
					<td class="til">类型</td>
					<td>
						<%-- <s:select list="#{'0':'通用','1':'部署管理','2':'其他'}" name="processObj.TYPE" id="type" ></s:select> --%>
						<s:select list="#{'0':'通用'}" name="processObj.TYPE" id="type" ></s:select>
					</td>
				</tr> 
<%--				<tr align="left">--%>
<%--					<td class="til">脚本管理</td>--%>
<%--					<td>--%>
<%--						<s:select list="#{'0':'手动输入','1':'关联脚本','2':'托管模式'}" name="" id="relationscript" ></s:select>--%>
<%--					</td>--%>
<%--				</tr> 				--%>
				<tr>
				    <td class="til" id="start_script_link">
				       <a href="javascript:;" type="script_a" id="start" style="color: blue;text-decoration: underline;">启动脚本:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" >
				    <s:textfield name="processObj.START_SCRIPT" cssClass="path" id="start_script"></s:textfield>
				    </td>
				</tr>
				<tr>
				  <td class="til" id="stop_script_link">
					<a href="javascript:;" type="script_a" id="stop" style="color: blue;text-decoration: underline;">停止脚本:</a><font color="red">*</font>
					</td>
					<td colspan="5">
					 <s:textfield name="processObj.STOP_SCRIPT" cssClass="path" id="stop_script" ></s:textfield>
					</td>
				</tr>
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
