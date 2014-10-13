<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
    var example_id = '<%=request.getAttribute("example_id")%>';
    var hostIP = '<%=request.getAttribute("hostIP")%>';
    var type = '<%=request.getAttribute("type")%>';
    var id='<%=request.getAttribute("id")%>';
    var task_id = '<%=request.getAttribute("task_id")%>';
    //创建配置文件
    var api = frameElement.api;
	w = api.opener;
    $(function(){
      $("[name='selectHost']").unbind().live("click",function(){
	  		w.$.dialog({
    			id:'addHostIp',
    			title:'选择用户',
    			width: '750px',
    			height: '470px',
    		    lock:true,
    			content: 'url:treetask_selectTask.do?task_ids='+taskIds
	    		});
		 });
		
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createrule,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
	});
		
	function createrule(){
		/*
		if(id==null || id=="" ||id=="null"){
		if(taskIds==null ||taskIds.length==0){
				alert('请先选择任务');
				return false;
			}
		}
		*/
		var name =$("#name").val(); 
		var path = $("#report_path").val();
		var desc = $("#description").val();
		var types = $("#type").val();
		var content = $("#content").val();
		if(name==null || name==''){
			alert('请填写规则名称');
				return false;
		}
		if(path==null || path ==''){
			alert('请填写验证报告路径');
				return false;
		}
		 var url = "rule_save.do?task_id=" + task_id+"&"+$("#theForm").serialize()+"&id="+id;
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              dataType:"json",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==null){
                }else{
                	if(id==null || id=="" || id=="null"){
                		api.get("rule").dynamicAdd(name,msg,types);
                	}else{
                		api.get("rule").dynamicUpdate(name,types,content,path,desc);
                	}
                }
              }
		});
    }
    function addTask(task_ids){
    	taskIds = task_ids;
    	var task_id= taskIds.split(",");
    	var lenth = task_id.length-1;
        $("[name='ips']").empty();
        $("[name='ips']").append("<a href=\"javascript:;\" name=\"selectHost\">"+"已选择"+lenth+"个用户"+"</a>");
    }
    
    $(function(){
    	if(id!=null && id!="" && id!="null"){
    		$("#edit").hide();
    	}else{
    		$("#edit").show();
    	}
    })
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<!--  
				<tr align="left" id="edit">
				    <td class="til">
				       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">关联任务:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    	已选择:0个
				    </td>
				</tr>
			-->
				<tr align="left">
					<td class="til">名称</td>
					<td>
						<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
						<s:select list="#{'0':'正则表达式','1':'关键字模糊匹配','2':'接口探测','3':'其他'}" name="theForm.type" id="type"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">内容</td>
					<td>
						<s:textfield name="theForm.content" id="content" style="width:330px;height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">报告路径</td>
					<td>
					<s:textfield name="theForm.report_path" id="report_path" style="width:330px;height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.description" id="description" cols="40" rows="5"></s:textarea>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>