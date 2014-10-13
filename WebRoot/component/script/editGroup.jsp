<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
var uuid = '<%=request.getAttribute("id")%>';
	function submitRequest(){
		theForm.submit();
	}
	var api = frameElement.api;
	w = api.opener;
	$(function(){
		
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createGroup,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
	
		//创建脚本组
	function createGroup(){
		var name =$("#name").val(); 
		var path = $("#description").val();
		if(name==null || name==''){
			alert('请填写脚本组名称');
				return false;
		}
		 var url = "scriptgroup_save.do?id="+uuid+"&"+$("#theForm").serialize();
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	w.searchRequest();
                }
              }
		});
    }
</script>
<style>
.path {
	width: 335px;
	height: 20px;
	vertical-align: middle;
	overflow-y: hidden;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #999;
}
</style>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">

<s:form action="scriptinfo_saveGroup.do" id="theForm" method="post">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.group_id"></s:hidden>
	<div>
		<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr>
				<td class="til">
					组名称<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>
				</td>
				</tr>
				<tr>
					<td class="til">
						描述
					</td>
					<td>
						<s:textarea cols="40" rows="4" name="theForm.description"></s:textarea>
					</td>
				</tr>
		</table>
	</div>
	</s:form>
</body>
