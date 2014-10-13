<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/bomc/Contents/JS/ClientFunc.js"></script>
	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	function reset(pfbForm){
		pfbForm.GROUP_ID.value = '';
		pfbForm.SENTTO_EMPLOYE.value = '';
		pfbForm.TYPE_ID.value = '';
		pfbForm.TITLE.value = '';
		pfbForm.TITLE.value = '';
		
	}
	String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(pfbForm){
		if( confirm("确定要保存吗？")  && checkValid(pfbForm) ){
			  pfbForm.action = "feedback_saveFeedBackInfo.do";
		 	  pfbForm.submit();
		 
	      return true;
		}
		return false;
	}
	function BtnSelect_OnSelectAll(obj){
		if(obj != null && obj.length > 0 ){
           for(var i=0;i<obj.length;i++){
               obj[i].selected="true";
           }
        }
	}
	function BtnNew_OnClick(pfbForm){
		
		pfbForm.action="feedback_saveFeedBackInfo.do";
		pfbForm.submit();
	}

	function checkValid(obj){

		if (!checkEmpty(obj.SUBMIT_TIME,'提交时间')) return false;
		if (!checkEmpty(obj.TYPE_ID,'类型ID')) return false;
		if (!checkEmpty(obj.TITLE,'反馈信息名称')) return false;
		if (!checkEmpty(obj.DF_INFO,'反馈信息')) return false;
		if (!checkEmpty(obj.SENTTO_EMPLOYE,'反馈信息接收人')) return false;
		if (!checkEmpty(obj.LOGIN_ID,'反馈信息提交人')) return false;
		if (!checkEmpty(obj.IF_AFFIRM,'生效')) return false;
	
		return true;
	}
	 function isdigit(s)
            {
            var r,re;
            re = /\d*/i; 
            r = s.match(re);
            return (r==s)?1:0;
            }
	
	
	
	
	
	
   function back(){
     	document.theForm.SI_ID.value = '';
     	document.theForm.TYPE_ID.value = '';
     	document.theForm.action="showHelpFileList.do";
		document.theForm.submit();
     }
     
     function addSub(thisForm){
     	var command = thisForm.COMMAND.value;
     	if(checkValid(thisForm) && confirm("确定要保存吗？")){
	     	thisForm.submit();
     	}
     }
     
    var xmlHttp; 
	var check="<%=String.valueOf(request.getAttribute("SENTTO_EMPLOYE"))%>";
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
    function getSelect(ID) {
	    createXmlHttp();
	    var GROUP_ID = pfbForm.GROUP_ID.value;
	    xmlHttp.open("GET", "feedback_getUserListByGroupId.do?GROUP_ID="+GROUP_ID, false);
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
 
		if (obj.GROUP_ID==null||obj.GROUP_ID==""){
			alert('反馈接收人部门不能为空');
			return false;
		} 
		if (obj.SENTTO_EMPLOYE==null||obj.SENTTO_EMPLOYE==""){
			alert('反馈信息接收人不能为空');
			return false;
		} 
		if (obj.TITLE==null||obj.TITLE==""){
			alert('反馈信息标题不能为空');
			return false;
		} 
		return true;
	}
	
	function init(){
		getSelect('SENTTO_EMPLOYE');
	}
	
</script>
</head>
<body class="pop-body scrollbody" onload="init()">
	<s:form action="feedback_saveFeedBackInfo.do" method="post" id="pfbForm">
  		<s:hidden name="pfbForm.action_type" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				     	反馈接收人部门:<font color="red">*</font>
				    </td>
				    <td>
				    	<s:select list="pfbForm.GROUP_LIST" headerKey="" headerValue="请选择"  listKey="GROUP_ID" listValue="GROUP_NAME" name="pfbForm.GROUP_ID" id="GROUP_ID" onchange="getSelect('SENTTO_EMPLOYE')">
				    	</s:select>
				    </td>
				    <td class="til">
				    	反馈信息接收人：<font color="#FF0000">*</font></td>
    				<td>
    					<s:select list="#{'','--请选择--'}" headerKey="" headerValue="--请选择--"  name="pfbForm.SENTTO_EMPLOYE" id="SENTTO_EMPLOYE"></s:select>
    				</td>
    			</tr>
    			<tr>					
				    <td class="til">反馈信息类型:<font color="#FF0000">*</font></td>
    				<td colspan="3">
    					<s:select list="pfbForm.TYPE_LIST" listKey="TYPE_ID" listValue="TYPE_NAME" name="pfbForm.TYPE_ID" headerKey="" headerValue="请选择"></s:select>
    				</td>
    			</tr>
    			<tr>					
				    <td class="til">反馈信息标题:<font color="#FF0000">*</font></td>
    				<td colspan="3">
						<s:textfield name="pfbForm.TITLE" id="TITLE" />
    				</td>
    			</tr>
    			<tr>
    				<td class="til">反馈信息内容:</td>
    				<td colspan="3"><s:textarea name="pfbForm.DF_INFO" cols="40" rows="3"  /></td>
  				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="BtnSave_OnClick(document.pfbForm);return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="reset(pfbForm);return false;" />
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>