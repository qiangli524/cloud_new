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

	function resetForm(theForm){
		theForm.id.value = '';
		theForm.serverName.value = '';
	}

   function searchRequest() {
   if(theForm.id.value.length > 0 && theForm.id.value < 1){
   alert("Server Id 必须为大于0的数");
   return false;
   } 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'farmserver_addServerfarms.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改账户！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条账户信息");
 	    return false ;
 	    }
 	    theForm.action = 'farmserver_modfarmServer.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除账户！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条账户信息");
 	    return false ;
 	    }
 	    theForm.action = 'delfarmServer.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="farmserver_farmServerList.do" method="post" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">FARMServer ID:</td>
                    <td> 
						<s:textfield name="theForm.id" id="id"/> 
                    </td>
                    <td class="til">FARMServer名称:</td>
                    <td>
						<s:textfield name="theForm.serverName" id="serverName"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  	   <th>Select<br /></th>
				   <th onclick="sort(theTable,1,'string')">Server<br /> Name</th>
				   <th onclick="sort(theTable,2,'string')">Farm<br /> Name</th>                
                   <th onclick="sort(theTable,3,'string')">Server<br /> Address </th>
                   <th onclick="sort(theTable,4,'string')">Server<br /> Port </th>
                   
                   <th onclick="sort(theTable,5,'string')">Operational<br /> Status  </th>
                   <th onclick="sort(theTable,6,'string')">Operation<br /> Mode  </th>
                   
                   <th onclick="sort(theTable,7,'string')">Admin<br /> Status</th>
                   <th onclick="sort(theTable,8,'string')">Redirect<br /> To </th>
                   <th onclick="sort(theTable,9,'string')">Client <br />NAT </th>
                   
                   <th onclick="sort(theTable,10,'string')">Backup Server<br /> Address </th>
                   <th onclick="sort(theTable,11,'string')">Backup <br />Preemption</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id" />"/></td>
							<td><s:text name="#theBean.serverName"/></td>
							<td><s:text name="#theBean.farmName"/></td>
							<td><s:text name="#theBean.serverAddress" /></td>
							<td><s:text name="#theBean.serverPort" /></td>
							<td><s:text name="#theBean.operationalStatus" /></td>
							<td><s:text name="#theBean.operationMode"/></td>
							<td><s:text name="#theBean.adminStatus" /></td>
							<td><s:text name="#theBean.redirectTo"/></td>
							<td><s:text name="#theBean.clientNAT"/></td>
							<td><s:text name="#theBean.backupServerAddress" /></td>
							<td><s:text name="#theBean.ADServerBackupPreemption" /></td>
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
