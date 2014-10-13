<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<%@ include file="../../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    
    <script type="text/javascript">
    var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:updateConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    function updateConfig(){
    	var id = '<%=request.getAttribute("id")%>';
    	$("#name").attr("disabled",false);
    	$("#type").attr("disabled",false);
    	$("#path").attr("disabled",false);
    	var desc = $("#description").val();
    	 var url = "deployconfig_saveHadoopConfig.do?id=" + id+"&"+$("#theForm").serialize();
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              dataType:"json",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==null){
                }else{
                	w.updatePage();
                }
              }
		});
    }
    
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
				    <td class="til">
				       IP地址:
				    </td>
				    <td name="user" colspan="5" id="user">
				       <s:property value="theForm.ip"/>
				    </td>
				</tr>
				<tr align="left">
				    <td class="til">
				       用户名:
				    </td>
				    <td name="user" colspan="5" id="user">
				       <s:property value="theForm.username"/>
				    </td>
				</tr>
				<tr align="left">
					<td class="til">名称</td>
					<td>
						<s:textfield name="theForm.name" id="name" style="width:150px;   height:20px;" disabled="true"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
						<s:select list="#{'0':'xml','1':'properties','2':'其他'}" name="theForm.type" id="type" disabled="true"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">路径</td>
					<td>
					<s:textfield name="theForm.path" id="path" style="width:300px;   height:20px;" disabled="true"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.description" id="description" cols="40" rows="4"></s:textarea>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>