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
		var _templ_name = $("#templ_name").val();
		if(_templ_name == null || _templ_name == ''){
			alert("模版名称是必填项")
			$("#templ_name").focus();
			return false;
		}  
		
		var _os_version = $("#os_version").val();
		if(_os_version == null || _os_version == ''){
			alert("操作系统是必选项");
			$("#os_version").focus();
			return false;
		}	  
		thisForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">安装模版配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osTemplate_modifyOsTemplate.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								模版名称 <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.templ_name" id="templ_name" maxlength="30" cssClass="inpt-2"/>            
								<font color="grey">（模版名称不能重复）</font> 
							</td>
							<td class="til">
								系统镜像<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.os_version" id="os_version" cssClass="select-1"
									listValue="dictname " listKey="dictcode " list="theForm.sdList" headerKey="" headerValue="---请选择---"/>
							</td>
						</tr>
						<tr>
							<td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.templ_desc" id="templ_desc" maxlength="30" cssClass="textarea-1"/> 
							</td>
							<td class="til">
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td colspan="6" class="btnCenter">
								<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
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
