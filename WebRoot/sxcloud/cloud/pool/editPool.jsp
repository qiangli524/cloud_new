<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(){ 
	var name=document.getElementById('POOL_NAME').value;
	    if(name.length ==0){
	     alert("主机名称不能为空！");
	     // theForm.NAME.focus;
	     return false  ;
	    }
	    theForm.submit();
	}
	

</script>
</head>
<body class="pop-body scrollbody" >
	<s:form action="resourcepool_saveHostPool.do" method="post" id="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						主机池名称 <font color="red">*</font>
					</td>
					<td>
					<s:textfield name="theForm.NAME" id="POOL_NAME" cssClass="txt" ></s:textfield>
					          
					</td>
					
				</tr>
				<tr>
				    <td class="til">
						主机池描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.POOL_DESC" id="DESC" cssClass="txt" style="width:300px;height:100px"></s:textarea>
					</td>
					
					
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest();return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>