<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createDepart,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 
		 $("[name='selectUser']").click(function(){
	        	currentEdit=$(this);
	        	var flag = $("#flag").attr("value");
	    		w.$.dialog({
	    			id:'addUser',
	    			title:'选择部门负责人',
	    			width: '950px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:departproject_queryUserInfoList.do?flag='+flag
	    			});
	          });
	})
 function createDepart(){
		var teg = /^\d+$/;
		var name = $("#name").val();
		var leader = $("#leader").val();
		var cpu = $("#cpu").val();
		var mem = $("#mem").val();
		var store= $("#store").val();
		var ip = $("#ip_num").val();
		if(name=="" || name==null){
			alert('请填写科室名称');
			return false;
		}
		if(leader=="" || leader==null){
			alert("请选择科室负责人");
			$("#leader").focus();
			return false;
		}
		/* if(!teg.test(cpu)){
			alert("cpu核数只能为大于或等于0的数字");
			return false;
		}
		if(!teg.test(mem)){
			alert("mem只能为大于或等于0的数字");
			return false;
		}
		if(!teg.test(store)){
			alert("store只能为大于或等于0的数字");
			return false;
		}
		if(!teg.test(ip)){
			alert("ip数量只能为大于或等于0的数字");
			return false;
		} */
	
 	var str = $("#obj").serialize()+"&name="+encodeURI(encodeURI(name));
 	
 	var url = "section_saveSection.do?"+str;
 	w.createDepart(url);
 }

	function pageOnLoad(){
		///getVMList();
		//listVMInfo();
	}
	function getUserInfo(username,phone,email,account) {
		var name = username.split("_");
		var count = account.split("_");
		var names = '';
		var accounts = '';
		
		for(var i=0;i<name.length;i++){
			if(name[i]!=null&&name[i]!=""){
				names = names + name[i] + " ";
				accounts = accounts + count[i] +" ";
			}
		}
		$("#leader").attr("value",accounts);
		$("#leaderName").attr("value",names);
	}
</script>
</head>
<body onload="pageOnLoad()">
<s:form action="" method="post" id="obj">
<s:hidden name="obj.id" id="id"></s:hidden>
<s:hidden name="obj.leader" id="leader"></s:hidden>
<s:hidden name="flag" id="flag"></s:hidden>
		<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">部门名称</td>
					<td>
						<s:textfield name="obj.name" id="name"></s:textfield>
					</td>
				</tr> 
				 <tr align="left">
					<td class="til">部门负责人</td>
					<td>
						<s:textfield name="obj.leaderName" id="leaderName" readonly="true"></s:textfield>
						<span class="ubtn-1 mgl-20"><input  type="button" value="选择" name="selectUser"/></span>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
