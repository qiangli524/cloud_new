<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
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
	function search(){
		document.theForm.submit();
	}
	function submitForm(command){
	
		str = "";
		j=0;
		var length =  document.getElementsByName("theForm.TEMP_TYPE_ID").length;
		
			if (length == 0 ){
		  		alert("当前页中没有记录，不能进行修改，删除")
	          		return;
	    		}
			if(document.theForm.TEMP_TYPE_ID.length>0){
			var aa = document.getElementsByName('theForm.TEMP_TYPE_ID');
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
					j++;
					str+=document.theForm.TEMP_TYPE_ID.value;
				}
			}
			
			
			if(j==0){
				alert("请选择您需要删除或者修改的条目!");
			}
			else{
				if(command=="delete"){
					if(confirm('确定要删除吗？')){
					createXmlHttp();
					xmlHttp.open("GET", "file_whetherDirIsUsed.do?LIST_ID="+str, false);
					xmlHttp.setRequestHeader("If-Modified-Since","0");        
					xmlHttp.send(null);
				    if (xmlHttp.readyState == 4) {
				        var flag = xmlHttp.responseText+"";
				        if(flag != ""){
				       		alert(flag);
				    		return;
				    	}
		   		 	}
					document.theForm.action = "file_dirManagerDel.do";
					document.theForm.submit();
					return true;}
					return false;
				}
				else{
					if((j==1) && (command=="update")){
							createXmlHttp();
							xmlHttp.open("GET", "file_whetherDirIsUsed.do?LIST_ID="+str, false);
							xmlHttp.setRequestHeader("If-Modified-Since","0");        
							xmlHttp.send(null);
						    if (xmlHttp.readyState == 4) {
						        var flag = xmlHttp.responseText+"";
						        if(flag != ""){
						       		alert(flag);
						    		return;
						    	}
				   		 	}
						
						document.theForm.action="file_dirManagerUpdate.do";
						document.theForm.submit();
						return true;
					} 
					else{
						alert("一次只能修改一条记录!");
					}
				}
			}
	}

	function addSub(thisForm)
	{
		 thisForm.action = "file_dirManagerAdd.do";
	     thisForm.submit();
	}
	function checkAll(e, itemName)
	{
	  var aa = document.getElementsByName(itemName);
	  for (var i=0; i<aa.length; i++)
	   aa[i].checked = e.checked;
	}
	function resetForm(theForm){
	    theForm.LIST_NAME.value= '';
	    theForm.SI_ID.value= '';
	}
 	</script>
</head>
<body>
<s:form action="file_showDirectoryList.do" method="post" cssClass="theForm" id="theForm">

<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">目录名称:</td>
				    <td>
				    	<s:textfield name="theForm.LIST_NAME" cssClass="txt" id="LIST_NAME" size="20"></s:textfield>
				    </td>
				    <td class="til">文件类型选择:</td>
				    <td>
						<s:select list="theForm.SI_LIST" listKey="SI_ID" listValue="SI_NAME" id="SI_ID" name="theForm.SI_ID" headerKey="" headerValue="-请选择-"></s:select>
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
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addSub(document.getElementById('theForm'));return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "submitForm('update');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "submitForm('delete');return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">目录名称</th>
				   <th onclick="sort(theTable,2,'string')">所属文件类型</th>
				   <th onclick="sort(theTable,3,'string')">是否有效</th>
				   <th onclick="sort(theTable,4,'string')">创建者</th>
				   <th onclick="sort(theTable,5,'date')">创建时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
			  		<tr>
			  			<td><input type="checkbox" value="<s:property value="#theBean.LIST_ID"/>" id="TEMP_TYPE_ID" name="theForm.TEMP_TYPE_ID"/></td>
			  			<td><s:property value="#theBean.LIST_NAME"/></td>
			  			<td><s:property value="#theBean.SI_NAME"/></td>
			  			<td>	
			  				<s:if test="#theBean.ENABLE==1">有效</s:if>
			  				<s:elseif test="#theBean.ENABLE==0">无效</s:elseif>
			  			</td>
			  			<td><s:property value="#theBean.INSERT_USR"/></td>
			  			<td><s:property value="#theBean.INSERT_DATE"/></td>
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
