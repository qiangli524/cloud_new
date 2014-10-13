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
		var id = $("#ID").val();
		var key = $("#KEY").val();
		var type = $("#TYPE").val();
		var value = $("#VALUE").val();
	    if(key==""){
	      $("#KEY").attr("class","required");
	      $("#KEY").focus();
	      return false;
	    }
	    if(value==""){
	      $("#VALUE").attr("class","required");
	      $("#VALUE").focus();
	      return false  ;
	    }
	    var url = 'exportconfig_validateForm.do?key='+key+'&type='+type+'&id='+id;
		 $.ajax({
			  type:"post",
              url:url,
			  dataType : "html",
              async: false,
              cache: false,
	          success: function(data){
	          	  if(data==""){
						var urls= "exportconfig_saveExportConfig.do?"+"&"+$("#theForm").serialize();
		    			w.saveConfig(urls);
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
		var type = $("#TYPE").val();
		var url = 'exportconfig_validateForm.do?key='+key+'&type=' + type;
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
<body onLoad="self.focus();document.theForm.KEY.focus()">
<s:form action="" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">导出配置</h2>
         <div class="bord-1 pd-10">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						导出关键字 <font color="red">*</font>
					</td>
					<td> 
						<s:if test="theForm.flag==0">
					    	<s:textfield name="theForm.KEY" cssClass="required inpt-2" id="KEY" maxlength="30" onblur="validateForm()"></s:textfield>
					    	<span style="color:RED" id="span_key"/>    
					    </s:if>
					    <s:else>
					    	<s:textfield name="theForm.KEY" cssClass="required inpt-2" id="KEY" readonly="true" disabled="true"></s:textfield>
					    </s:else>  
					</td>
				</tr>
				<tr>
					<td class="til">语句类型<font color="red">*</font></td>
					<td>
					   <s:if test="theForm.flag==0">
						  <s:select list="#{'1':'mysql','2':'oracle'}" name="theForm.TYPE" id="TYPE" cssClass="select-1"></s:select>
					   </s:if>
					   <s:else>
					      <s:select list="#{'1':'mysql','2':'oracle'}" name="theForm.TYPE" id="TYPE" readonly="true" disabled="true" cssClass="select-1"></s:select>
					    </s:else>  
					</td>
				</tr>
				<tr>
				    <td class="til">
						导出语句 <font color="red">*</font>
					</td>
					<td colspan="3">
<%-- 					    <s:textarea name="theForm.VALUE" cols="66" rows="12"  id="VALUE" maxlength="100" cssClass="required"></s:textarea>                 
 --%>				
 					<s:textarea name="theForm.VALUE" cols="66" cssStyle="" rows="12"  id="VALUE"></s:textarea> 
 					</td>
				</tr>
				
				<tr>
				    <td class="til">
						说明
					</td>
					<td colspan="3">
						<s:textarea name="theForm.CFG_DESC" cols="66" rows="4" id="CFG_DESC"  maxlength="300" ></s:textarea>
					</td>		
				</tr>
				</table>
			</div>
        </div>
    </div>
</s:form>
</body>
