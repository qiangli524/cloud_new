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
		
			var _part_name = $("#part_name").val();
			if (_part_name == null|| _part_name == '') {
				alert('名称是必填项');
				$("#part_name").focus();
				return false;
			}
			var _part_ondisk = $("#part_ondisk").val();
			if (_part_ondisk == null|| _part_ondisk == '') {
				alert('ONDISK是必填项');
				$("#part_ondisk").focus();
				return false;
			}
			var _part_size = $("#part_size").val();
			if (_part_size == null|| _part_size == '') {
				alert('大小是必填项');
				$("#part_size").focus();
				return false;
			}
			/*	  
		 	$.ajax({
				type : 'post',
				url : 'osTemplComp_validOsTemplateGroup.do?' + $("#theForm").serialize(),
				success : function(msg) {
					if (msg == -1) {
						alert("用户组" + _group_name + "已存在");
					} else {
						thisForm.submit();
					}
				}
			});
			*/
			thisForm.submit();
		}
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">Part配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osTemplComp_modifyOsTemplatePart.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								名称 <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.part_name" id="part_name" maxlength="30" cssClass="inpt-2"/>            
								<font color="grey">（名称不能重复）</font> 
							</td>
							<td class="til">
								类型
							</td>
							<td>
								<s:select name="theForm.part_fstype" id="part_fstype" cssClass="select-1"
									list="theForm.sdList" listKey="dictcode" listValue="dictname" headerKey="" headerValue="--请选择--"/>
							</td>
						</tr>
						<tr>
							<td class="til">
								DISK<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.part_ondisk" id="part_ondisk" maxlength="30" cssClass="inpt-2"/>
							</td>
						    <td class="til">
								自增长 
							</td>
							<td>
								<s:select name="theForm.part_grow" id="part_grow" cssClass="select-1"
									list="#{'1':'是','0':'否'}"/>
							</td>
						</tr>						
						<tr>
							<td class="til">
								大小<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.part_size" id="part_size" maxlength="30" cssClass="inpt-2"/>
								<font color="grey">（单位待定）</font>
							</td>						
							<td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.part_desc" id="part_desc" maxlength="30" cssClass="textarea-1"/> 
							</td>
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
