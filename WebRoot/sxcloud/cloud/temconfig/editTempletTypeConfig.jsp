<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	var xmlHttp; 
	var check;
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
		if(thisForm.TYPE_NAME.value.length==0){
			alert("请输入服务名称！");
			return false;
		}
		if(thisForm.TYPE.value=='' || thisForm.TYPE.value==null){
			alert("请输入服务类型！");
			return false;
		}  
	    thisForm.submit();
	}
	
	var flag= true;
	function validateForm(){
				document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
				//异步方式判断 需求编码是否唯一
				var TYPE = document.getElementById("theForm").TYPE.value;
				if(TYPE == null || "" == TYPE){
					document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "服务类型不能为空";
					flag=false;
					return false;
				}else{
					flag =true;
				}
				var url = "templetConfig_uniqueJudgement.do?TYPE=" + TYPE+"&Date"+(new Date());
				 $.getJSON(url,function(data){
				 	if("NO" == data ){
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = TYPE+"已经存在，请更改服务类型!";
				 		flag=false;
				 	}else{
				 		flag=true;
				 	}
				 })
			}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="templetConfig_saveTempletType.do" method="post"
		cssClass="theForm" id="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					服务名称
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.TYPE_NAME" cssClass="txt" id="TYPE_NAME"></s:textfield>
				</td>
			</tr>

			<tr>
				<td class="til">
					服务类型
					<font color="red"></font>
				</td>
				<td>
					<s:if test="theForm.flag==1">
						<s:textfield name="theForm.TYPE" cssClass="txt" id="TYPE"
							readonly='true'></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="theForm.TYPE" cssClass="txt" id="TYPE"
							onblur="validateForm()"></s:textfield>
					</s:else>
					<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>
				</td>
			</tr>
			
			<tr>
				<td class="til">
					服务描述
					<font color="red"></font>
				</td>
				<td>
					<s:textarea name="theForm.TYPE_DESC" cols="77" rows="3"
						id="TYPE_DESC"></s:textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.go(-1)" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
