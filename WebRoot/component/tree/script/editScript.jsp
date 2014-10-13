<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/spin/js/jquery-spin.js"></script>
    <script type="text/javascript">
 	var example_id = '<%=request.getAttribute("example_id")%>';
    var hostIP = '<%=request.getAttribute("hostIP")%>';
    var node_type = '<%=request.getAttribute("node_type")%>';
    var id = '<%=request.getAttribute("id")%>';
   	var selectUsers = '';
   	var userIps = '';
   	var usernames = '';
   	var example_ids = '';
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
    			content: 'url:deployuser_list.do?userids='+selectUsers+'&example_id='+example_id+"&type="+node_type+'&hostIP='+hostIP+'&example_ids='+$("#example_ids").val()
	    		});
		 });
		 
		
		
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createScript,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
	});
		
	function createScript(){
	if(id==null || id=="" ||id=="null"){
		if(selectUsers==null ||selectUsers.length==0){
				alert('请先选择用户');
				return false;
		}
	}
	
		var name =$("#name").val(); 
		var path = $("#path").val();
		var desc = $("#description").val();
		var types = $("#type").val();
		var grade = $("#grade").val();
		var interval = $("#interval").val();
		if (id==null || id=="" ||id=="null") {
			if (interval.length == 0) {
				alert('请填写时间间隔');
				return false;
			}
		}
		
		if(name==null || name==''){
			alert('请填写脚本名称');
				return false;
		}
		/*
			//进行同类别级别验证
    		var validate_url = 'deployscript_filterGrade.do?type='+types+'&example_id='+example_id+'&node_type='+node_type+'&grade='+grade;
			var flag ; 
			$.ajax({
			  type:"GET",
              url:validate_url,
              dataType:"json",
              async: false,
              cache: false,
	          success: function(msg){
                if (msg == null) {
					flag = true;
				}
				else{
					 if(msg.length>0){
					 	flag = false;
					 }else{
					 	flag = true;
					 }
				}
             }
		});
		if(flag==false){
			$("#grade_span").html('当前类型已经存在同级别脚本,请更改');
			return false;
		}
		*/
		if(path==null || path ==''){
			alert('请填写脚本路径');
				return false;
		}
		var url = "deployscript_save.do?selectUsers=" + selectUsers+"&"+$("#theForm").serialize()+
		"&example_id="+example_id+"&type="+node_type+"&id="+id+"&example_ids="+example_ids;
		 $.ajax({
			  type:"POST",
              url:url,
              data:"text",
              dataType:"json",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==null){
                }else{
               		 if(id==null || id=="" ||id=="null"){
               		 	w.updatePage();
                		//w.dynamicAdd(userIps,usernames,msg,types,name,path,grade);
                	}else{
                		w.updatePage();
                	}
                }
              }
		});
    }
	 function addUser(ids,ips,names,example_id){
    	selectUsers=ids;
    	userIps=ips;
    	usernames = names;
    	example_ids = example_id;
    	example_ids = example_id;
    	$("#example_ids").val(example_ids);
        var strIds=ids.split(",");
        var strIps=ips.split(",");
        var temp="";
        /*
        for(var i=0;i<strIps.length;i++){
        	temp+="<label name=\"ip\" key="+strIds[i]+">"+strIps[i]+"</label>"+"&nbsp;&nbsp";
        }
        */
        var lenth = strIds.length-1;
        $("[name='ips']").empty();
        $("[name='ips']").append("<a href=\"javascript:;\" name=\"selectHost\">"+"已选择"+lenth+"个用户"+"</a>");
    }
    
    $(function(){
    	if(id==null || id=="" ||id=="null"){
    		$("#user").show();
    		$("#inter").show();
    	}else{
    		$("#user").hide();
    		$("#inter").hide();
    	}
    })
    
    $(function(){
    	$.spin.imageBasePath = '<%=request.getContextPath()%>/js/spin/img/spin1/';
    	$('#grade').spin();
    })
    
    //根据所选类型来判断级别，即如果选择与之前相同的类型，那么级别不可以相同
    /*
    function filterGrade(){
    	alert(22222);
    	var type = $("#type").val();
    	var grade = $("#grade").val();
    	var url = 'deployscript_filterGrade.do?type='+type+'&example_id='+example_id+'&node_type='+node_type+'&grade='+grade;
    	var lenth=0;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if (data == null) {
					return true;
				}
				else{
					 lenth = data.length;
					 if(lenth>0){
					 	$("#grade_span").html('当前类型已经存在同级别脚本,请更改');
					 	return false;
					 }else{
					 	return true;
					 }
					 
				}
			});
    }*/
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
     <s:hidden name="theForm.script_id" id="script_id"></s:hidden>
      <s:hidden value="example_ids" id="example_ids"></s:hidden>
     <s:hidden name="theForm.flag" id="flag"></s:hidden>
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left" id="user">
				     <td class="til">
				       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">关联用户:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    	已选择:0个
				    </td>
				</tr>
				<tr align="left">
					<td class="til">脚本名称<font color="red">*</font></td>
					<td>
						<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
						<s:select  headerValue="请选择" list="#{'0':'通用','1':'启动','2':'停止','3':'备份','4':'其他'}" name="theForm.type" id="type" style="width:150px" ></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">级别</td>
					<td>
						<s:textfield id="grade"  style="width:30px;height: 16px;" name="theForm.grade"></s:textfield>
							<span id="grade_span" style="color: RED"></span>
						<!-- 
					<s:select list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" id="grade" style="width:50px"></s:select>
					 	-->
					</td>
				</tr>
				<tr align="left" id="inter">
					<td class="til">执行时间间隔<font color="red">*</font></td>
					<td>
					<s:textfield name="theForm.interval" id="interval" style="width:30px;height: 20px;"></s:textfield>
					<s:select list="#{'1':'秒','2':'分','3':'时'}" id="unit" style="width:40px;height: 25px;" name="theForm.unit"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">脚本路径<font color="red">*</font></td>
					<td>
					<s:textfield name="theForm.path" id="path" style="width:330px;   height:20px;"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">
						参数
					</td>
					<td>
						<s:textfield name="theForm.params" id="params" style="width:330px;   height:20px;"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.description" id="description" cols="40" rows="4"></s:textarea>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>