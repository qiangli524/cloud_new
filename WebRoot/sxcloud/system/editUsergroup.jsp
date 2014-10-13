<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
	
	function submitRequest(theForm){   
	    var groupname = $("#GROUPNAME").val();
	    var id = $("#ID").val();
		if(groupname == ""){
			 $("#GROUPNAME").attr("class","required");
			 $("#GROUPNAME").focus();
			return false;
		} 
		var url = "ugroup_validateFormName.do?groupname=" + groupname+"&id="+id;
		 $.getJSON(url,{"time":new Date().toString()},function(data){
		 	if("NO" == data ){
		 		$("#SPAN_GROUPNAME").html(groupname+"已经存在，请更改名称!");
		 	}if("YES" == data){
		 		theForm.action='ugroup_saveUsergroup.do';
				theForm.submit();
		 	}
		 });
	}
	 function validateForm(){
			//异步方式判断 需求编码是否唯一
			 var id = $("#ID").val();
			var groupname = $("#GROUPNAME").val();
			if(groupname!=""){
				var url = "ugroup_validateFormName.do?groupname=" + encodeURI(encodeURI(groupname))+"&id="+id;
				 $.getJSON(url,{"time":new Date().toString()},function(data){
				 	if("NO" == data ){
				 		$("#SPAN_GROUPNAME").html(groupname+"已经存在，请更改名称!");
				 	}
				 });
			}
	}
<% 
     String msg = request.getAttribute("msg")==null ? "": (String)request.getAttribute("msg");
%>
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">用户组管理</h2>
         <div class="bord-1 pd-10">
			<s:form action="" method="post" cssStyle="theForm" id="theForm">
			<s:hidden name="theForm.ID" id="ID"></s:hidden>
			<input type="hidden" name="theForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr>
					    <td class="til">
							用户组名称 <font color="red">*</font>
						</td>
						<td>
							<s:textfield name="theForm.GROUPNAME" id="GROUPNAME" onblur="validateForm()" maxlength="30" cssClass="required inpt-2"></s:textfield>
							<span id="SPAN_GROUPNAME" style="color: red"></span>
						</td>
						<td class="til">
							状态：
						</td>
						<td>
							<s:select list="#{'1':'有效','0':'无效'}" name="theForm.STATUS" id="STATUS" cssClass="select-1"></s:select>
				<%--		    <s:if test="1==theForm.STATUS" >
						              <span>有效</span>  
						    </s:if>
						    <s:else>
						                无效 
						    </s:else> --%>
						</td>
						<td class="til">
						</td>
						<td>
						</td>
					</tr>
					<tr>
					    <td class="til">
							备注
						</td>
						<td colspan="2">
							<s:textarea name="theForm.REMARK" cssClass="textarea-1"></s:textarea>
						</td>
						<!-- 
						<td class="til">
							领导视图权限
						</td>
						<td>
						<s:select list="#{'1':'有效','0':'无效'}" name="theForm.LEADER" id="LEADER"></s:select>
						</td>
						 -->
					</tr>
					<tr>
						<td colspan="6" class="btnCenter">
							<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
							<span class="ubtn-orange mgl-20"><input type="button"  value="重置"/ onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>、
							<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
						</td>
					</tr>
				</table>
			</s:form>
		  </div>
        </div>
    </div>
</body>

</html:html>
