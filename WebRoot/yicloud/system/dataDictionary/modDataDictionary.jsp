<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		ttheForm.CODE.value = '';
		theForm.NAME.value ='';
		theForm.GROUP_NAME.value ='';
		theForm.PARENT_ID.value = '';
	}
	function submitRequest(thisForm){  
	    thisForm.submit();
	}
</script>
</head>
<body>
<s:form action="dic_saveData.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
	
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
             <tr>
             <td class="til">编码</td>
					 <td>
						<s:textfield name="theForm.CODE" cssClass="txt" id="CODE"></s:textfield>
					</td>
             </tr>
             <tr>
             <td class="til">名称</td>
					 <td>
						<s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
					</td>
             </tr>
             <tr>
             <td class="til">分组</td>
					 <td>
						<s:textfield name="theForm.GROUP_NAME" cssClass="txt" id="GROUP_NAME"></s:textfield>
					</td>
             </tr>
             <tr>
             <td class="til">上级节点</td>
					 <td>
						<s:textfield name="theForm.PARENT_ID" cssClass="txt" id="PARENT_ID"></s:textfield>
					</td>
             </tr>
              <tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
				 			onclick="window.history.back()" />
							
					</td>
				</tr>
			</table>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
