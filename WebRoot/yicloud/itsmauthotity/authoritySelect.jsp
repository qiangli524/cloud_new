<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var userId = '<%=request.getAttribute("userid")%>';
	var uuid = '<%=request.getAttribute("uuid")%>';
	var type = '<%=request.getAttribute("type")%>';
	var name = '<%=request.getAttribute("name")%>';
	function saveOper(){   
		if (userId!='null') {
			if(confirm("您确定要修改对 " + name +" 的权限吗？")){
				$.ajax({
			         type: "get",
			         url: "vmauth_updateUserOperAuthority.do?userid="+ userId+'&uuid=' +uuid +'&type='+type+'&name='+name+'&'+$("#theForm").serialize(),
			         dataType: "json",
			         success : function(data){
						window.parent.refresh(userId,uuid,type,name);
			        }
				});
				
		 	} 
	 	}
	}
</script>
</head>
<body >
<s:form action="vmauth_saveItsmUserVirAuthority" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td>
						<s:if test="vmAuthorityObj.OPERAUTHORITY == 0">
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="0" checked="checked" id="operate" onclick="saveOper()" />全部权限
				    	</s:if>
				    	<s:else>
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="0" id="operate" onclick="saveOper()" />全部权限
				    	</s:else>
					</td>
				</tr> 
				<tr id="alls" align="left" >
					<td class="til">
						选择此项，则具有操作该资源的全部权限
					</td>
				</tr>
				<tr align="left">
					<td>
						<s:if test="vmAuthorityObj.OPERAUTHORITY == 1">
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="1" checked="checked" id="operate" onclick="saveOper()" />操作权限
				    	</s:if>
				    	<s:else>
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="1" id="operate" onclick="saveOper()" />操作权限
				    	</s:else>
					</td>
				</tr>
				<tr id="operates" align="left" >
					<td class="til">
						选择此项，则可以启动或停止该资源
					</td>
				</tr>
				<tr align="left">
					<td>
						<s:if test="vmAuthorityObj.OPERAUTHORITY == 2">
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="2" checked="checked" id="operate" onclick="saveOper()" />查看权限
				    	</s:if>
				    	<s:else>
				    		<input type="radio" name="vmAuthorityObj.OPERAUTHORITY" value="2" id="operate" onclick="saveOper()" />查看权限
				    	</s:else>
					</td>
				</tr> 
				<tr id="views" align="left">
					<td class="til">
						选择此项，则只能查看该资源
					</td>
				</tr>
				<!-- 
				<tr>
				<td>
					<input type="button" class="btn-style02" value="确定" name="save" onclick="submitRequest()"/>
            	</td>
				</tr>
				 -->
			</table>
		</div>
</s:form>
</body>
