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
    var example_id = '<s:property value="example_id"/>';
    var hostIP = '<s:property value="hostIP"/>';
    var nodeType = '<s:property value="type"/>';
   	var selectUsers = '';
   	var userIps = '';
   	var usernames = '';
    //创建配置文件
    $(function(){
    	var api = frameElement.api;
		w = api.opener;
		
		var aa = $("#isReferTactics").val();
		if (aa == 0) {
			$("#refer").hide();
		} else {
			$("#refer").show();
		}
		
		$("#isReferTactics").change(function(){
			var aa = $("#isReferTactics").val();
			if (aa == 0) {
				$("#refer").hide();
			} else {
				$("#refer").show();
			}
		});
		
		
      $("[name='selectHost']").unbind().live("click",function(){
	  		w.$.dialog({
    			id:'addHostIp',
    			title:'选择用户',
    			width: '750px',
    			height: '470px',
    		    lock:true,
    			content: 'url:usermanage_listHadoopUsers.do?theForm.ip='+hostIP
	    		});
		 });
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
	});
		
	function createConfig(){
		var name =$("#name").val(); 
		var path = $("#path").val();
		var desc = $("#description").val();
		var types = $("#type").val();
		var category = $("#category").val();
		if(selectUsers==null ||selectUsers.length==0){
				alert('请先选择用户');
				return false;
		}
		if(name==null || name==''){
			alert('请填写配置文件名称');
				return false;
		}
		if(path==null || path ==''){
			alert('请填写配置文件路径');
				return false;
		}
		
		var flag = false;
		var aa = $("#isReferTactics").val();
		if (aa == 1) {
			var tactics = $("#tactics").val();
			$.ajax({
				type:'post',
				url:'deployconfig_checkTactics.do?theForm.tactics='+tactics,
				async:false,
				dataType:'text',
				success:function(msg){
					if (msg == -1) {
						alert("该策略已经存在配置文件，且只能拥有一个配置文件");
					} else {
						flag = true;
					}
				}
			});
		}else{
			flag = true;
		}
		if (flag) {
			var url = "deployconfig_saveHadoopConfig.do?selectUsers=" + selectUsers+"&"+$("#theForm").serialize()+"&example_id="+example_id+"&type="+nodeType;
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
	                	w.updatePage();
	                }
	              }
			});
		}
    }
	 function addHadoopUser(ids,ips,names){
    	selectUsers=ids;
    	userIps=ips;
    	usernames = names;
        var strIds=ids.split(",");
        var strIps=ips.split(",");
        var temp="";
        var lenth = strIds.length-1;
        $("[name='ips']").empty();
        $("[name='ips']").append("<a href=\"javascript:;\" name=\"selectHost\">"+"已选择"+lenth+"个用户"+"</a>");
    }
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
				    <td class="til">
				       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">关联用户:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    	已选择:0个
				    </td>
				</tr>
				<tr align="left">
					<td class="til">名称</td>
					<td>
						<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
						<s:select list="#{'0':'xml','1':'properties','2':'其他'}" name="theForm.type" id="type" ></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">路径</td>
					<td>
					<s:textfield name="theForm.path" id="path" style="width:330px;height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.description" id="description" cols="40" rows="5"></s:textarea>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">
						是否指定策略：
					</td>
					<td>
						<s:select list="#{'0':'否','1':'是' }" name="isReferTactics" id="isReferTactics"></s:select>
					</td>
				</tr>
				<tr id="refer" align="left">
					<td class="til">
						策略：
					</td>
					<td>
						<s:select list="#{'1':'FIFO','2':'Container','3':'Fair' }" name="theForm.tactics" id="tactics"></s:select>
					</td>
				</tr>
			</table>
		</div>
    </s:form>
</body>