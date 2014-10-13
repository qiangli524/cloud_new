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
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	$(function(){
		$("#effect_time").change(function(){
			var tm = $(this).val();
			if (tm == 0) {
				var now = new Date(); 
				var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
				document.getElementById("executetime").value = nowStr;
			}
		});
	})
	
	
    var example_id = '<%=request.getAttribute("example_id")%>';
    var hostIP = '<%=request.getAttribute("hostIP")%>';
    var type = '<%=request.getAttribute("type")%>';
    var oper = '<%=request.getAttribute("oper")%>';//当前要做的操作
   	
    //创建配置文件
	$(function(){
		 var api = frameElement.api;
		 var w = api.opener;
		 
		 if("add"==oper){
			 api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addtask,
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
			     callback:addtask,
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

		 function addtask(){
			var example=$("#example").text();
		    if(example==0){
			       alert("至少要选择一个实例！");
			       return false  ;
			     }
		    var report_path=$("#report_path").val();
		    if(report_path==null||Trim(report_path)==""){
			       alert("报告路径不能为空！");
			       return false  ;
			     }
		    var taskid=$("#taskid").val();
		    var flag=-1;
	    	 $.ajax({
	  	            type: "GET",
	  	            async:false,
	  	            url: "treetask_changeTaskTypeCanJionOrder.do?tasktype="+$("#task_type option:selected").val()+"&time"+new Date().toString(),
	  	            dataType: "json",
	  	            success : function(data){
	  		              flag=data.result;
	  	              }
	    	});
	    	 if(flag==2){
	    		 alert("一个订单下不允许添加不同类型的任务,请重新选择!");
	    		 return false;
	    	 }
     		 api.get("addtask11").saveTask($("#theForm").serialize(),oper);
		 }
		 
		 $("[name='selectHost']").unbind().live("click",function(){
			var appid=$("#appid").val();
	  		w.$.dialog({
    			id:'addHostIp',
    			title:'添加实例',
    			width: '750px',
    			height: '470px',
    		    lock:true,
    			content: 'url:treetask_listDeployExample.do?appid='+appid+"&exampleid="+$("#exampleid").val()+"&executelevel="+$("#executelevel").val()
	    		});
		 });

	        $("#file_list_num").change(function(){
	            var temp=$(this).val();
	            if("0"==temp){
	                $("#file_list").attr("disabled",false);
	              }else if("1"==temp){
	                $("#file_list").attr("disabled",true);
	     	  		w.$.dialog({
	         			id:'add1',
	         			title:'从版本中选取',
	         			width: '750px',
	         			height: '470px',
	         		    lock:true,
	         			content: ''
	     	    		});
	              }else if("2"==temp){
	            	$("#file_list").attr("disabled",true);
	     	  		w.$.dialog({
	         			id:'add2',
	         			title:'从软件包选取',
	         			width: '750px',
	         			height: '470px',
	         		    lock:true,
	         			content: ''
	     	    		});
	              }else{
	            	$("#file_list").attr("disabled",true);
	              }
	        });
	});
	
    
    function addExample(exampleid,executelevel){
        $("#exampleid").val(exampleid);//这样可以通过标签把值传回actin
        $("#executelevel").val(executelevel);
        var idarrays=exampleid.split(",");
        var temp="已选择:<label id=\"example\">"+(idarrays.length-1)+"</label>个";
        $("[name='ips']").empty();
        $("[name='ips']").append(temp);
    }

    //页面初始化后的方法
    $(function(){
    	var exampleid=$("#exampleid").val();
    	var executelevel=$("#executelevel").val();
    	var temp="";
    	if(exampleid!=null&&exampleid!='undefine'){
    		var idarrays=exampleid.split(",");
    		temp="已选择:<label id=\"example\">"+(idarrays.length-1)+"</label>个";
    	}else{
            temp="已选择:<label id=\"example\">0</label>个";
    	}
    	$("[name='ips']").empty();
    	$("[name='ips']").append(temp);
    });
	
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	    <s:hidden name="taskObj.ID" id="taskid"></s:hidden>
	    <s:hidden name="appId" id="appid"></s:hidden>
	    <s:hidden name="exampleid" id="exampleid"></s:hidden>
	    <s:hidden name="executelevel" id="executelevel"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">关联实例:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    </td>
				</tr>	
			<tr align="left">
				<td class="til">任务类型
				<td>
<%--	0部署任务，1升级任务，2启动任务，3停止任务，4重启任务，5、恢复任务，6卸载任务，7捕获任务			--%>
					<s:select list="#{'0':'部署任务','1':'升级任务','2':'启动任务','3':'停止任务','4':'重启任务','5':'恢复任务','6':'卸载任务','7':'捕获任务'}" name="taskObj.TASK_TYPE" id="task_type" ></s:select>
				</td>
			</tr>
			<tr>
			  <td class="til">
			       任务报告路径:<font color="red">*</font>
				</td>
				<td>
				<s:textfield name="taskObj.REPORT_PATH" id="report_path" style="width:330px;height:20px;"></s:textfield>
				</td>
			</tr>
		</table>
	</s:form>
</body>

</html:html>
