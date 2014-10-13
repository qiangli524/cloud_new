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
	function getSelect() {
    	createXmlHttp();
    	var APP_TYPE=document.getElementById("APP_TYPE").value;
    	xmlHttp.open("GET", "ajax_getAppIp.do?APP_TYPE="+APP_TYPE, false);     
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
    	if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.getElementById("VH_ID");
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
		if(thisForm.KEY.value=='' || thisForm.KEY.value==null){
			alert("请输入配置项！");
			return false;
		}
		if(thisForm.VALUE.value=='' || thisForm.VALUE.value==null){
			alert("请输入配置值！");
			return false;
		}  
	    thisForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="templetConfig_saveTempletConfig.do" method="post" cssClass="theForm" id="theConForm">
<s:hidden name="theConForm.flag" id="flag"></s:hidden>
<s:hidden name="theConForm.TYPE" id="TYPE"></s:hidden>
<s:hidden name="theConForm.ID" id="ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						配置项
					</td>
					<td>
					    <s:textfield name="theConForm.KEY" cssClass="txt" id="KEY"></s:textfield>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						配置值
					</td>
					<td>
					    <s:textfield name="theConForm.VALUE" cssClass="txt" id="VALUE"></s:textfield>           
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theConForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.go(-1)"/>
					</td>
				</tr>
			</table>
</s:form>
</body>
