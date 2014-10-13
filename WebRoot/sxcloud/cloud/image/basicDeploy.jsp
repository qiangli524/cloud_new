<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
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
	    
 		theForm.action = 'image_deployImage.do'
 		theForm.submit();
	}
 	function saveRequest(thisForm){ 
 		if(thisForm.IM_NAME.value.length ==0){
	    	alert("名称不能为空！");
	    	thisForm.IM_NAME.focus;
	    	return false  ;
	    } 
 		theForm.action = 'saveImageInfo.do'
		theForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="image_basicDeploy.do" method="post" id="theForm">
		<s:hidden id="workloadInfoId" name="theForm.workloadInfoId"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						名称 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.IM_NAME" id="IM_NAME" cssClass="txt"></s:textfield>           
					</td>
					<td class="til">
						项目 
					</td>
					<td>
						<s:select list="theForm.projectList" id="PROJECT_ID" name="theForm.PROJECT_ID" listKey="PROJECT_ID" listValue="NAME" headerKey="0" headerValue="--请选择--">
						</s:select>
					</td>
				</tr>	
				<tr>
					<td class="til">
						CPU
					</td>
					<td>
					    <s:textfield name="theForm.VH_CPU" id="VH_CPU" cssClass="txt"></s:textfield>          
					</td>
					<td class="til">
						内存
					</td>
					<td>
					    <s:textfield name="theForm.VH_MEM" id="VH_MEM" cssClass="VH_MEM"></s:textfield>             
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.IM_DESC" id="IM_DESC" cols="50" rows="3"></s:textarea>
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
	</s:form>
</body>
