<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
	    if(thisForm.VALUE.value.length ==0){
	     alert("配置值不能为空！");
	     thisForm.VALUE.focus;
	     return false  ;
	    }
	    if($("#asd").val() == ''){
	    	alert("");
	    }
	    thisForm.submit();
	}

</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.VALUE.focus()">
<s:form action="config_saveGlobalConfig.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.KEY" id="KEY"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						配置关键字 <font color="red">*</font>
					</td>
					<td>
					    <s:property value="theForm.KEY" maxlength="30" id="KEY"></s:property>                
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置值 <font color="red">*</font>
					</td>
					<td>
					   <!-- <s:textfield name="theForm.VALUE" cssClass="txt" id="VALUE"></s:textfield>  --> 
					    <s:textarea name="theForm.VALUE" cols="80" rows="3" id="asd" maxlength="30"></s:textarea>                   
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						配置描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.CFG_DESC" cols="66" rows="5" id="CFG_DESC" maxlength="300"></s:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
				<!-- 	<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" /> -->
					<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()" />
					</td>
				</tr>
			</table>
</s:form>
</body>
