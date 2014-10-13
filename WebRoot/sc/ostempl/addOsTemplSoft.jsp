<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		function submitRequest(thisForm){ 
			var _soft_name = $("#soft_name").val();
			if (_soft_name == null|| _soft_name == '') {
				alert('名称是必填项');
				$("#soft_name").focus();
				return false;
			}
			
			var _soft_type = $("#soft_type").val();
			if (_soft_type == null|| _soft_type == '') {
				alert('类型是必填项');
				$("#soft_type").focus();
				return false;
			}
		 	$.ajax({
				type : 'post',
				url : 'osTemplComp_validOsTemplateSoft.do?' + $("#theForm").serialize(),
				success : function(msg) {
					if (msg == -1) {
						alert("软件" + _soft_name + "已存在");
					} else {
						thisForm.submit();
					}
				}
			});
		}
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">软件配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osTemplComp_saveOsTemplateSoft.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								软件名称<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.soft_name" id="soft_name" maxlength="30" cssClass="inpt-2"/>            
								<font color="grey">（名称不能重复）</font>
							</td>
							<td class="til">
								类别<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.soft_type" id="soft_type" cssClass="select-1"
									listValue="dictname " listKey="dictcode " list="theForm.sdList" headerKey="" headerValue="---请选择---"/>
							</td>							
						</tr>
						<tr>
							 <td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.soft_desc" id="soft_desc" maxlength="30" cssClass="textarea-1"/> 
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="4" class="btnCenter">
								<span class="ubtn-orange"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-blue mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
								<span class="ubtn-green mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
