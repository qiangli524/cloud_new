<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function isdigit(s)
            {
            var r,re;
            re = /\d*/i; //\d表示数字,*表示匹配多个数字
            r = s.match(re);
            return (r==s)?1:0;
            }
            
	function submitRequest(thisForm){    
	    if(thisForm.name.value.length ==0){
	     alert("真实服务器名称不能为空！");
	     thisForm.name.focus;
	     return false  ;
	    }
	    if(thisForm.realAddress.value.length ==0){
	     alert("服务器IP地址和端口号不能为空！");
	     thisForm.realAddress.focus;
	     return false  ;
	    }
	    if(isdigit(thisForm.weight.value)==0){
	     alert("请输入服务器权重，并且只能为数字!");
	     thisForm.weight.focus;
	     return false  ;
	    }
	    if(thisForm.virtualAddress.value =='all'){
	     alert("请选择真实服务器所属的虚拟服务器！");
	     return false  ;
	    }
	    thisForm.submit();
	}
	



</script>
</head>
<body class="pop-body scrollbody">
<s:form action="realserver_sureAddRealServer.do" method="post" id="theForm" cssClass="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					真实服务器名称
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>
				</td>
				<td class="til">
					服务器IP地址和端口
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.realAddress" id="realAddress" cssClass="txt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					所属虚拟服务器
					<font color="red">*</font>
				</td>
				<td>
					<s:if test="theForm.virtualServerlist !=null">
					<s:select list="theForm.virtualServerlist" listKey="INFO" listValue="name" name="theForm.virtualAddress" id="virtualAddress"
					 headerKey="all" headerValue="-请选择-"></s:select>
					 </s:if>
					<s:else>
						<s:select list="#{'all':'-请选择-'}" name="theForm.virtualAddress" id="virtualAddress"></s:select>
					</s:else>
				</td>
				<td class="til">
					服务器权重
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.weight" cssClass="txt" id="weight"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					真实服务器健康检查请求内容
				</td>
				<td>
					<s:textfield name="theForm.request" id="request" cssClass="txt"></s:textfield>
				</td>
				<td class="til">
					真实服务器健康检查响应内容
				</td>
				<td>
					<s:textfield name="theForm.receive" id="receive" cssClass="txt"></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>	
				</td>
			</tr>

		</table>
	</s:form>
</body>
