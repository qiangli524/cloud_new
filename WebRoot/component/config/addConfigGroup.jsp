<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    
    <script type="text/javascript">
    var api = frameElement.api;
	var w = api.opener;
	$(function(){
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createConfigGroup,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
    //创建配置文件
    function createConfigGroup(){
    	var url = "configgroup_save.do?"+$("#theForm").serialize();
    	if($("#name").val() == ''){
    		alert("请输入组名!");
    		return false;
    	}
    	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	w.list();
                }
              }
		});
    }
    
    </script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">组名称<span style="color:red">*</span></td>
					<td>
						<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.description" id="description" cols="40" rows="4"></s:textarea>
					</td>
				</tr> 
				<!-- 
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="创建"
							onclick="javascript:createConfigGroup()" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
				 -->
			</table>
		</div>
    </s:form>
</body>