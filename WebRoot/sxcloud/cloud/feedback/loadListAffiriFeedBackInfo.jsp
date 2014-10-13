<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

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
		document.theForm.action="loadListFeedBackInfo.do";
		document.theForm.submit();
	}


	function confirmFeedBackInfo(){
		var j= 0;
		var str = "" ;
		var len = document.getElementsByName("IDS").length;
		if(len!=null&&len>0){
 		var IDS = document.getElementsByName("IDS") ;
			for(i=0;i<len;i++){
	 			if(IDS[i].checked){
	 				str+=IDS[i].value;
	 				j = j + 1;
	 			}
			}
		}
		if(j>1){
			alert("只能选择一条反馈信息处理");
		}else if(j==0){
			alert("请选中一条反馈信息进行处理");
		}else{
			document.theForm.action="confirmFeedBackInfo.do?ID="+ str;
			document.theForm.submit();
		return true;
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
	
	function resetForm(theForm){
	    theForm.TITLE.value= '';
	}
 	</script>
</head>
<body>
<html:form action="/loadListFeedBackInfo" method="post" styleId="theForm">
  <bean:define id="theForm" name="publishFeedBackForm"/>
<html:hidden  name="theForm" property="ID"/>
<html:hidden  name="theForm" property="ISMP_JSP_COMMAND" value="add"/>
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
				        <html:text styleClass="button" name ="theForm" property="TITLE"  size="20"/>
				    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:search()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input type="checkbox"  onclick="selectAll();"></th>
				   <th onclick="sort(theTable,1,'string')">反馈信息标题</th>
				   <th onclick="sort(theTable,2,'date')">提交时间</th>
				   <th onclick="sort(theTable,3,'string')">反馈类型</th>
				   <th onclick="sort(theTable,4,'string')">信息发送人</th>
				   <th onclick="sort(theTable,5,'string')">信息处理人</th>
				   <th onclick="sort(theTable,6,'date')">处理时间</th>
			  </tr>
			  </thead>
			  <tbody>
			   <logic:present name="theForm" property="resultList">
			      <logic:iterate id="theBean" name="theForm" property="resultList" indexId="status">
						<tr>
							<td><INPUT type=checkbox value=<bean:write name="theBean" property="ID"/> name=IDS></td>
							<td><a href="examineInfo.do?ID=<bean:write name="theBean" property="ID"/>"><bean:write name="theBean" property="TITLE"/></a></td> 
    						<td><bean:write name="theBean" property="SUBMIT_TIME"/></td>
    						<td><bean:write name="theBean" property="TYPE_ID"/></td> 
     						<td><bean:write name="theBean" property="LOGIN_ID"/></td>
     						<td><bean:write name="theBean" property="HF_INFO"/></td>   
     						<td><bean:write name="theBean" property="HF_TIME"/></td>      
							</td>
						</tr>
				</logic:iterate>
				</logic:present>		  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</html:form>
</body>
</html:html>