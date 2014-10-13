<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:add,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	 
	 function add(){
		 $.ajax({
			type:"POST",
            url:"vncport_savevncport.do",
            data:$("#tbVncPortVO").serialize(),
            async: false,
            cache: false,
	        success: function(){
           		w.searchRequest();
           	}
		});
	 }
	</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">添加VncPort</h2>
		<div class="bord-1 pd-10">
			<s:form action="vncport_savevncport" method="post"
				cssStyle="tbVncPortVO" id="tbVncPortVO">
				<s:hidden name="theForm.ID" id="ID"></s:hidden>
				<input type="hidden" name="FUNCSID"
					value="<%=(String)request.getParameter("FUNCSID") %>">
					<table width="100%" border="0" cellspacing="0"
						class="pop-table nosize">
						<tr>
							<td class="til">主机<font color="red">*</font></td>
							<td>
								<s:select list="hostList" listKey="eq_id" listValue="eq_ip" headerKey="-1" headerValue="--请选择--" 
								name="tbVncPortVO.host_code"
								id="host_code" cssClass="select-1"></s:select>
							</td>
						</tr>
						<tr>
							<td class="til">开始端口 <font color="red">*</font></td>
<%--							<td><s:textfield name="tbVncPortVO.startport" id="startport"--%>
<%--									maxlength="30" cssClass="required inpt-2" /></td>--%>
								<td><s:select list="portList" headerKey="-1" headerValue="--请选择--" 
								name="tbVncPortVO.startport"
								id="startport" cssClass="select-1"></s:select></td>
						</tr>
						<tr>
							<td class="til">结束端口 <font color="red">*</font></td>
							<td><s:select list="portList" headerKey="-1" headerValue="--请选择--" 
								name="tbVncPortVO.endport"
								id="endport" cssClass="select-1"></s:select></td>
						</tr>
					</table>
			</s:form>
		</div>
	</div>
	</div>
</body>

</html:html>
