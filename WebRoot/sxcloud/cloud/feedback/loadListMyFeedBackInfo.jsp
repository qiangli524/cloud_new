<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	var xmlHttp;
	//ajax
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
	function search() {
		pfbForm.action="feedback_loadListMyFeedBackInfo.do";
		pfbForm.submit();
	}


	function submitForm(thisForm,command){
		str = "";
		j=0;
		var length =  document.getElementsByName("TEMP_TYPE_ID").length;
		
			if (length == 0 ){
		  		alert("当前页中没有记录，不能进行修改，删除")
	          		return;
	    	}
	    	
			if(document.theForm.TEMP_TYPE_ID.length>0){
				var aa = document.getElementsByName("TEMP_TYPE_ID");
				for(i=0;i<aa.length;i++){
		 			if(aa[i].checked){
		 			     if(j==0){
		 				str=aa[i].value;
		 				}else{
		 				str+=","+aa[i].value;
		 				}
		 				j++;
		 			}
				}
			}
			else{
				if(document.theForm.TEMP_TYPE_ID.checked){
					str+=document.theForm.TEMP_TYPE_ID.value;
					j++;
				}
			}
			
			if(j==0){
				alert("请选择您需要删除或者修改的条目!");
			}
			else{
				
				if(command=="delete"){
					if(confirm("您确定要删除吗？")){
						createXmlHttp();
						xmlHttp.open("GET", "/isFileTypeDel.do?TYPE_ID="+str, false);
						xmlHttp.setRequestHeader("If-Modified-Since","0");        
						xmlHttp.send(null);
					    if (xmlHttp.readyState == 4) {
					        var flag = xmlHttp.responseText+"";
					       if(flag != ""){
					       		alert(flag);
					    		return;
					    	}
			   		 	}
					
					
						document.theForm.action="fileManagerDel.do";
						document.theForm.submit();
						return true;
					}
				}
				else{
					if(j > 1){
						alert("一次只能修改一条记录!");
					}else{
						thisForm.FLAG.value=command;
						thisForm.action="showFileManagerUpdate.do";
						thisForm.submit();
					}
				}
			}
	}

	function addSub(thisForm,val)
	{
		 thisForm.action = "showFileManagerAdd.do";
		 thisForm.FLAG.value=val;
	     thisForm.submit();
	}
	
	function checkAll(e, itemName)
	{
	  var aa = document.getElementsByName(itemName);
	  for (var i=0; i<aa.length; i++){
	   aa[i].checked = e.checked;
	  } 
	}
	
	function resetForm(pfbForm){
	    theForm.TITLE.value= '';
	    theForm.IF_AFFIRM.value= '';
	}
 	</script>
</head>
<body>
<s:form action="feedback_loadListMyFeedBackInfo.do" method="post" id="pfbForm">
  <s:hidden  name="pfbForm.ID" />
  <s:hidden  name="pfbForm.ISMP_JSP_COMMAND" value="add"/>
  <input type="hidden"  name ="type" value=""/>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">反馈信息标题:</td>
				    <td>
				        <s:textfield name ="pfbForm.TITLE" id="TITLE" />
				    </td>
				    <td class="til">反馈信息状态:</td>
				    <td>
				        <s:select name="pfbForm.IF_AFFIRM" list="#{'0':'待确认','1':'已确认'} " headerKey="" headerValue="--请选择--" id="IF_AFFIRM">
						</s:select>
				    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "search()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('pfbForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=pfbForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">反馈信息标题</th>
				   <th onclick="sort(theTable,1,'date')">提交时间</th>
				   <th onclick="sort(theTable,2,'string')">反馈类型</th>
				   <th onclick="sort(theTable,3,'string')">信息发送人</th>
				   <th onclick="sort(theTable,4,'string')">信息接收人</th>
				   <th onclick="sort(theTable,5,'string')">反馈信息状态</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="pfbForm.resultList"  >
						<tr>
							<td><a href="feedback_examineHisInfo.do?ID=<s:property value="#theBean.ID" />"><s:text name="#theBean.TITLE" /></a></td> 
    						<td><s:text name="#theBean.SUBMIT_TIME" /></td>
    						<td><s:text name="#theBean.TYPE_ID"/></td> 
     						<td><s:text name="#theBean.LOGIN_ID" /></td>   
    						<td><s:text name="#theBean.SENTTO_EMPLOYE" /></td>
							<td>
								<s:if test="#theBean.IF_AFFIRM==1">已确认</s:if>
								<s:if test="#theBean.IF_AFFIRM==0">待确认</s:if>
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>