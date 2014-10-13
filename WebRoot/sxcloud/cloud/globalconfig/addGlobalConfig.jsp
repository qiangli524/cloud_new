<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/entitytree/js/listServerTree.js"></script>
<script type="text/javascript">
 	var api = frameElement.api;
    var w = api.opener;

    api.button({
		id:'OkAnd',
		name: '确定',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '返回'
	}); 
    function Trim(str){
	   return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(){   
		var flag = false;
		var key = $("#KEY").val();
		var value = $("#VALUE").val(); 
		var id = $("#ID").val();
	    if(key==""){
	    	aler("配置关键字必须填写！");
		     $("#KEY").focus();
		     return false;
	    }
	     if(value==""){
	     aler("配置值必须填写！");
	     $("#VALUE").focus();
	     return false ;
	    }
	    var url = 'config_validateForm.do?key='+key+'&id='+id;
		 $.ajax({
			  type:"post",
              url:url,
			  dataType : "html",
              async: false,
              cache: false,
	          success: function(data){
	          	  if(data==""){
						//$("#KEY").attr("disabled",false);
						 mask('正在添加,请稍后....');
						 $.ajax({
				            type: "POST",
				            url: 'config_saveGlobalConfig.do?key='+key+'&'+$('#theForm').serialize(),
				            dataType: "json",
							async: false,
				            cache: false,
				            success : function(){
				            }
				         });
						 removeMask();
						 w.list();
					     flag = true;
				}else{
					$("#span_key").html(data);
				}
              }
		});
		return flag;
	}
	function validateForm(){
		var key = $("#KEY").val();
		var url = 'config_validateForm.do?key='+key;
   		 $.ajax({
			type : "get",
			url : url,
			dataType : "html",
			cache:false,
     	    processData: false,
			success : function(data){
				$("#span_key").html(data);
			}
		});	
	}
</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.KEY.focus()">
<s:form action="" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						配置关键字 <font color="red">*</font>
					</td>
					<td>
					  <s:if test="theForm.flag==0">
					  	 <s:textfield name="theForm.KEY" cssClass="required" id="KEY" maxlength="30" onblur="validateForm()"></s:textfield>
					     <span style="color:RED" id="span_key"/>  
					  </s:if>
					  <s:else>
					      <s:textfield name="theForm.KEY" cssClass="required" id="KEY" disabled="true"></s:textfield>
					   </s:else>         
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置值 <font color="red">*</font>
					</td>
					<td>
					    <s:textarea name="theForm.VALUE" cols="66" rows="3" id="VALUE" cssClass="required" maxlength="66"></s:textarea>                 
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						配置描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.CFG_DESC" cols="66" rows="5" id="CFG_DESC" maxlength="200"></s:textarea>
					</td>		
				</tr>
			</table>
</s:form>
</body>
