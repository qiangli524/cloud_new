<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/bomc/Contents/JS/ClientFunc.js"></script>
	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
   function back(){
     	document.theForm.SI_ID.value = '';
     	document.theForm.LIST_ID.value = '';
     	document.theForm.action="file_showHelpFileList.do";
		document.theForm.submit();
     }
     
     function addSub(thisForm){
     	var command = thisForm.COMMAND.value;
     	if(checkValid(thisForm) && confirm("确定要保存吗？")){
	     	thisForm.submit();
     	}
     }
     
    var xmlHttp; 
	var check="<%=String.valueOf(request.getAttribute("LIST_ID"))%>";
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
    function getSelect(ID) {
	    createXmlHttp();
	    var SI_ID = theForm.SI_ID.value;
	    xmlHttp.open("GET", "file_getHelpFileDir.do?SI_ID="+SI_ID, false);
	    xmlHttp.setRequestHeader("If-Modified-Since","0");      
	    xmlHttp.send(null);
	    if (xmlHttp.readyState == 4) {
	            var pageInfo = eval("("+xmlHttp.responseText+")");       
	            var SelectNode=document.getElementById(ID);
	            SelectNode.length=0;
	            SelectNode.appendChild(createSelect("","--请选择--"));
	            for(var o in pageInfo){
	              SelectNode.appendChild(createSelect(o,pageInfo[o]));
	            }            
	    }
	}
	
	function createSelect(value,text){
	  var i;
	  var opt=document.createElement("option");
	  opt.setAttribute("value",value);
	  opt.appendChild(document.createTextNode(text));
	  if(check == value){
	    opt.selected=true;
	  }
	  i=i+1;
	  return opt;
	}
	
    function checkValid(obj){
 
		if (obj.SI_ID.value==null||obj.SI_ID.value==""){
			alert('文件类型不能为空');
			return false;
		} 
		if (obj.LIST_ID.value==null||obj.LIST_ID.value==""){
			alert('存放目录不能为空');
			return false;
		} 
		if (obj.file.value==null||obj.file.value==""){
			alert('上传文件不能为空');
			return false;
		} 
		return true;
	}
	
	function init(){
		getSelect('LIST_ID');
	}
</script>
</head>
<body class="pop-body scrollbody" >
<s:form action="file_helpFileSave.do" method="post" cssClass="theForm" id="theForm" enctype="multipart/form-data">
<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
<s:hidden name="theForm.FILE_ID" id="FILE_ID"></s:hidden>
<s:hidden name="theForm.OLD_FILE_DIR" id="OLD_FILE_DIR"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	类型选择:<font color="red">*</font>
				    </td>
				    <td>
    					<s:select list="theForm.SI_LIST" name="theForm.SI_ID" id="SI_ID" listKey="SI_ID" listValue="SI_NAME" 
    						headerKey="" headerValue="-请选择-" onchange="getSelect('LIST_ID')"></s:select>
				    </td>
				    <td class="til">存放目录<font color="#FF0000">*</font> ：</td>
    				<td>
    					<s:if test="#request.LIST_ID==null">
    						<s:select list="#{'':'-请选择-'}" name="theForm.LIST_ID" id="LIST_ID"></s:select>
    					</s:if>
    					<s:else>
    						<s:select list="theForm.DIRECTORYLIST" name="theForm.LIST_ID" id="LIST_ID" listKey="LIST_ID" listValue="LIST_NAME"></s:select>
    					</s:else>
    				</td>
    			</tr>
    			<tr>					
				    <td class="til">文件上传<font color="#FF0000">*</font> ：</td>
    				<td colspan="3">
    					<s:file name="theForm.file" id="file" cssStyle="width:70%"></s:file>
    				</td>
    			</tr>
    			<tr>
    				<td class="til">当前版本:</td>
    				<td>
    				<%-- 	<s:if test='theForm.COMMAND=="update"'>
    						<s:property value="theForm.VERSION"/>
    					</s:if>
    					<s:else>1.0&nbsp;</s:else>
    					--%>
    					<s:property value="theForm.VERSION"/>
    					<s:hidden name="theForm.VERSION"></s:hidden>
    				</td>
    			<s:if test='theForm.COMMAND=="update"'>
    				<td class="til">修改模式<font color="#FF0000">*</font> ：</td>
    				<td>
    					<s:select list="#{'YES':'文件更新','NO':'文件升级'}" name="theForm.FLAG" id="FLAG"></s:select>
    				</td>
    			</s:if>
  				</tr>
  				<tr>
    				<td class="til">文件描述<font color="#FF0000">*</font> ：</td>
    				<td colspan="3">
    					<s:textarea name="theForm.FILE_DESC" id="FILE_DESC" cols="120" rows="20" cssClass="input"></s:textarea>
    				</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="addSub(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="javascript:back();return false;" />
					</td>
				</tr>

			</table>
	</s:form>
</body>
