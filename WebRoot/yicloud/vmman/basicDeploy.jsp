<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
 	function deployRequest(thisForm){
 		if(thisForm.IM_NAME.value.length ==0){
	    	alert("名称不能为空！");
	    	thisForm.IM_NAME.focus;
	    	return false  ;
	    }
	    if(thisForm.PROJECT_ID.value ==0){
	    	alert("请选择项目！");
	    	thisForm.PROJECT_ID.focus;
	    	return false  ;
	    }
	    if(thisForm.VH_CPU.value <1 ){
	    alert("CPU个数不能小于一个，并且必须为整数值");
	    thisForm.VH_CPU.focus;
	    	return false  ;
	    }
	    if(theForm.VH_CPU.value >128){
	    alert("CPU个数不能大于128，并且必须为整数值");
	    thisForm.VH_CPU.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MEM.value < 256){
	    alert("内存不能小于128，并且必须为整数值");
	    thisForm.VH_MEM.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MEM.value > 123136){
	    alert("内存不能大于123136，并且必须为整数值");
	    thisForm.VH_MEM.focus;
	    	return false  ;
	    }
	    
 		theForm.action = 'saveVMService.do'
 		theForm.submit();
	}
 	function saveRequest(thisForm){ 
 		if(thisForm.IM_NAME.value.length ==0){
	    	alert("名称不能为空！");
	    	thisForm.IM_NAME.focus;
	    	return false  ;
	    } 
 		theForm.action = 'saveVMService.do'
		theForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<html:form action="saveVMService" method="post" styleId="theForm">
		<bean:define id="theForm" name="vmManagerForm" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						名称 <font color="red">*</font>
					</td>
					<td>
					    <html:text property="IM_NAME" styleClass="txt" />              
					</td>
				</tr>	
				<tr>
					<td class="til">
						CPU
					</td>
					<td>
					    <html:text property="VH_CPU" styleClass="txt" />              
					</td>
					<td class="til">
						内存
					</td>
					<td>
					    <html:text property="VH_MEM" styleClass="txt" />              
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置描述
					</td>
					<td colspan="3">
						<html:textarea property="IM_DESC" cols="50" rows="3"></html:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="部署"
							onclick="javascript:deployRequest(document.getElementById('theForm'));return false;" />
<%--						<input type="button" class="thickbox btn-style02" value="确定"--%>
<%--							onclick="javascript:saveRequest(document.getElementById('theForm'));return false;" />--%>
						<input type="button" class="thickbox btn-style02" value="取消"
							onclick="window.history.back()" />
							
					</td>
				</tr>
			</table>
	</html:form>
</body>

</html:html>
