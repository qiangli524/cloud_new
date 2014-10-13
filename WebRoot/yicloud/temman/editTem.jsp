<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<html>
<head>

<script type="text/javascript">

  function resetForm(){
  	theForm.name.value = '';
  }
  $(function(){
	
	var api = frameElement.api;
	var w = api.opener;

		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:saveTem,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 
	function saveTem(){
  
  	var url ='temman_saveTem.do?'+$("#obj").serialize();
  	w.updateTem(url);
  }
})
  
  
</script>
</head>
<body>
<s:form action="" method="post" id="obj">
	<s:hidden name="obj.id" id="id"></s:hidden>
		<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">模板名称</td>
			 		<td>
					<s:property value="obj.name"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
					<s:select list="#{'1':'vmware模板','2':'xen模板','3':'OVF模板'}" name="obj.type" id="type" disabled="true"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否公有</td>
					<td>
					<!-- 
					<s:select list="#{'0':'公有','1':'私有'}" name="theForm.isPublic" id="isPublic"></s:select>
					 -->
					 <s:if test="theForm.isPublic==0">
					 	<input type="radio" name="obj.isPublic"  value="0" checked="checked"/><label>公有</label>
					 	<input type="radio" name="obj.isPublic" value="1"/><label>私有</label>
					 </s:if>
					<s:else>
						<input type="radio" name="obj.isPublic"  value="0" /><label>公有</label>
					 	<input type="radio" name="obj.isPublic" value="1" checked="checked"/><label>私有</label>
					</s:else>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">位置</td>
					<td>
					<s:textfield name="obj.position" id="position"  cssStyle="width:220px;   height:20px;"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">备注</td>
					<td>
					<s:textfield name="obj.remark" id="remark"  cssStyle="width:220px;   height:20px;"></s:textfield>
					</td>
				</tr>  
			</table>
		</div>
</s:form>
</body>
</html>
