<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"><!--
	
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
		theForm.action="file_FileManagerList.do";
		theForm.submit();
	}


	function submitForm(theForm,command){
		str = "";
		j=0;
		var length =  document.getElementsByName("theForm.TEMP_TYPE_ID").length;
		
			if (length == 0 ){
		  		alert("当前页中没有记录，不能进行修改，删除")
	          		return;
	    	}
			if(document.theForm.TEMP_TYPE_ID.length>0){
				var aa = document.getElementsByName("theForm.TEMP_TYPE_ID");
				for(i=0;i<aa.length;i++){
		 			if(aa[i].checked){
		 			    if(j==0){
		 				str=aa[i].value;
		 				theForm.TYPE_ID.value=str;
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
						xmlHttp.open("GET", "file_isFileTypeDel.do?TYPE_ID="+str, false);
						xmlHttp.setRequestHeader("If-Modified-Since","0");        
						xmlHttp.send(null);
					    if (xmlHttp.readyState == 4) {
					        var flag = xmlHttp.responseText+"";
					       if(flag != ""){
					       		alert(flag);
					    		return;
					    	}
			   		 	}
					
					
						document.theForm.action="file_fileManagerDel.do";
						document.theForm.submit();
						return true;
					}
				}
				else{
					if(j > 1){
						alert("一次只能修改一条记录!");
					}else{
						theForm.FLAG.value=command;
						theForm.action="file_showFileManagerUpdate.do";
						theForm.submit();
					}
				}
			}
	}

	function addSub(theForm,val)
	{
		 theForm.action = "file_showFileManagerAdd.do";
		 theForm.FLAG.value=val;
		 theForm.submit();
	}
	
	function checkAll(e, itemName)
	{
	  var aa = document.getElementsByName(itemName);
	  for (var i=0; i<aa.length; i++){
	   aa[i].checked = e.checked;
	  } 
	}
	
	function resetForm(theForm){
	    theForm.TYPE_NAME.value= '';
	}
 	--></script>
</head>
<body>
<s:form action="file_FileManagerList.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.FLAG" id="FLAG"></s:hidden>
<s:hidden name="theForm.TYPE_ID" id="TYPE_ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">文件类型:</td>
				    <td>
				        <s:textfield name="theForm.TYPE_NAME" id="TYPE_NAME" cssClass="txt" size="20" ></s:textfield>
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
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addSub(document.getElementById('theForm'),'add');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "submitForm(document.getElementById('theForm'),'update');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "submitForm(document.getElementById('theForm'),'delete');return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">文件类型</th>
				   <th onclick="sort(theTable,2,'string')">是否有效</th>
				   <th onclick="sort(theTable,3,'string')">插入者</th>
				   <th onclick="sort(theTable,4,'date')">插入时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input type="checkbox" value="<s:property value="TYPE_ID"/>" name="theForm.TEMP_TYPE_ID" id="TEMP_TYPE_ID"/></td>
			  		<td> <s:property value="#theBean.TYPE_NAME"/> </td>
			  		<td><s:if test="#theBean.ENABLE==1">有效</s:if>
			  			<s:elseif test="#theBean.ENABLE==0">无效</s:elseif>
			  		</td>
			  		<td> <s:property value="#theBean.INSERT_USR"/> </td>
			  		<td> <s:property value="#theBean.INSERT_DATE"/> </td>
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
