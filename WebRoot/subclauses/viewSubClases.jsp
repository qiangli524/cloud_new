<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<head>
	<title></title>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
   $(function(){
	 api.button({
	     id:'OkAnd',
	     name: '确定',
	     callback:saveInfo,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
   })
	 
  function saveInfo(){
		 var name=$("#id").val();
		 if(name==null||name==""){
			 alert("编码不能为空.");
			 return false;
		 }
		 var actype = $("#acType").val();
		 var clauseId = $("#id").val();
	 if(actype=="1"){
		 	$.ajax({
				type:"POST",
		     	url:"service_validateSubClauses.do?clauseId="+clauseId,
		    	async: false,
		    	data:"text",
		     	cache: false,
		      	success: function(msg){
		      	if(msg!=null&&msg!=""){
		      		alert(msg);
		      		return false;
		      	}else{
		      	    var url ='service_saveSubClauses.do?'+$("#theForm").serialize();
		      		w.saveInfo(url);
		      	}
		     }
			});
		 }else{
		 	 var url ='service_saveSubClauses.do?'+$("#theForm").serialize();
		     w.saveInfo(url);
		 }
	 }
	function page_init(){
	 if(theForm.acType.value=="2"){
	 	document.getElementById("id").readOnly = true;
	 }
	}
	
	function resourceTypeChange(){
		var type = $("#resource_type").val();
		if(type!=""){
			$.ajax({
				type:"POST",
		     	url:"service_getResourceNameList.do?resourceType="+type,
		    	async: false,
		    	data:"text",
		     	cache: false,
		      	success: function(msg){
		      	if(msg!=null&&msg!=""){
		      	   showResourceNameList(msg);
		      	}else{
		  			$("#resource_id option").remove();
		    		$("#resource_id").append("<option value=''>--请选择--</option>"); 
		      	}
		      }
			});
		}else{
		  	$("#resource_id option").remove();
		    $("#resource_id").append("<option value=''>--请选择--</option>"); 
		}
	}
	
	function showResourceNameList(data){
		  	$("#resource_id option").remove();
		    $("#resource_id").append("<option value=''>--请选择--</option>"); 
		  	var options = data.split("|");
	      	for(var i=0;i<options.length;i++){
	      	    var option = options[i].split(",");
	      	    $("#resource_id").append("<option value='"+option[0]+"'>"+option[1]+"</option>");
	      	} 
		 }
	function resourceIdChanged(){
	  var resourceType = $("#resource_type").val();
	  var resourceId = $("#resource_id").val();
	  $.ajax({
				type:"POST",
		     	url:"service_getResourceInfo.do?resourceType="+resourceType+"&resourceId="+resourceId,
		    	async: false,
		    	data:"text",
		     	cache: false,
		      	success: function(msg){
		      	if(msg!=null&&msg!=""){
		      	   var msgs = msg.split("|");
		      	   $("#declare").attr("value",msgs[1]);
		      	   $("#resource_info").attr("value",msgs[0]);
		      	}else{
		      	   alert("没有找到相应的模板规格，请输入！")
		      	}
		      }
			});
	}
	</script>
</head>
<body class="pop-body scrollbody" onload="page_init();">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="theForm.acType" id="acType" />
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					服务编码：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.id" id="id" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					服务名称：
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					有效时间：<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.indate" id="indate" maxlength="30" cssStyle="txt"  onFocus="WdatePicker({minDate:'1900-01-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd '})"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					服务权限：
				</td>
				<td>
					<s:textfield name="theForm.role" id="role" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					服务说明：
				</td>
				<td>
					<s:textarea name="theForm.declare" id="declare" cssStyle="box" cols="30" rows="4"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">
					模板类别:
				</td>
                <td>
                	<s:select list="#{'0':'--请选择--','1':'虚拟模板','2':'有效'}" name="theForm.resource_type" id="resource_type" onchange="resourceTypeChange();"></s:select>
                </td>
            </tr>
			<tr>
				<td class="til">
					资源模板编码：
				</td>
				<td>
				
				<s:select name="theForm.resource_id" id="resource_id"
                list="ResourceList" listKey="id" listValue="name" 
                onchange="resourceIdChanged()"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					资源规格：
				</td>
				<td>
					<s:textarea name="theForm.resource_info" id="resource_info" cssStyle="box" cols="30" rows="4"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">
					服务类别：
				</td>
				<td>
					<s:textfield name="theForm.type_id" id="type_id" cssClass="txt"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					状态:
				</td>
                <td>
                	<s:select list="#{'1':'有效','0':'有效'}" name="theForm.state" id="state"></s:select>
                </td>
            </tr>
		</table>
	</s:form>
</body>
