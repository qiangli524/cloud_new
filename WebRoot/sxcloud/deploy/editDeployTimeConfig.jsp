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
	
	function reset(theForm){
		theForm.EXPRESSION.value='';
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){    
	 
	    if(thisForm.EXPRESSION.value.length ==0){
	     alert("配置时间不能为空！");
	     thisForm.EXPRESSION.focus;
	     return false  ;
	    }
	    var expression=thisForm.EXPRESSION.value;
	   	if(!checknumber(expression)){
   			alert("配置时间输入不合法，只能为数字和字符字符！");
   			document.theForm.EXPRESSION.value='';
	     	document.theForm.EXPRESSION.focus;
   			return false;
   			}
<%--   		if(thisForm.EXPRESSION.value!=thisForm.EXPRESSION_BF.value&&thisForm.STATUS.value==0){--%>
<%--   			alert("失效状态下无法修改时间配置！");--%>
<%--   			return false;--%>
<%--   		}--%>
   		var flag1=false;
   		var flag2=false;
   		var type=0;
   		if(thisForm.EXPRESSION.value!=thisForm.EXPRESSION_BF.value){
   			flag1=true;
   		}
   		if(thisForm.STATUS.value!=thisForm.STATUS_BF.value){
   			flag2=true;
   		}
   		if(flag1==true&&flag2==false){
   			type=1;
   		}
   		if(flag1==false&&flag2==true){
   			type=2;
   		}
   		if(flag1==true&&flag2==true){
   			type=3;
   		}
   		thisForm.action='time_saveDeployTimeConfig.do?type='+type;
	    thisForm.submit();
	}
	
function checknumber(expression){
  		var pattern=/^([a-zA-Z0-9]|[\uFE30-\uFFA0|V:])*$/gi;
  		
    	if(pattern.test(expression)) {
	     return false;
    	}
    	 return true;
 	  }


</script>
</head>
<body class="pop-body scrollbody">
<s:form action="time_saveDeployTimeConfig.do" method="post" id="theForm">
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.EXPRESSION_BF" id="EXPRESSION_BF"></s:hidden>
<s:hidden name="theForm.STATUS_BF" id="STATUS_BF"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						部署时间配置名称<font color="red">*</font>
					</td>
					<td>
					<s:if test="theForm.ID==0">
                        <s:textfield name="theForm.NAME" id="NAME" cssClass="txt"></s:textfield>
                    </s:if>
                    <s:if test="theForm.ID>0">
                        <s:property value="theForm.NAME"/>
                    </s:if>
					</td>
					<td class="til">
						状态<font color="red">*</font>
					</td>
					<td >
                         <s:select list="#{'0':'停止','1':'启动'}" name="theForm.STATUS" id="STATUS"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						应用名称
					</td>
				    <td>
				            <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">
							</s:select>
				    </td>
				    <td class="til">
						配置时间 <font color="red">*</font>
					</td>
					<td >
						  <s:textfield name="theForm.EXPRESSION" id="EXPRESSION" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:reset(document.getElementById('theForm'));return false;" />
					</td>
				</tr>

			</table>
</s:form>
</body>
