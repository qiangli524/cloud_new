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

	function resetForm(theForm){
		theForm.virtualIP.value = '';
		theForm.l4PolicyName.value = '';
	}

   function searchRequest(theForm) { 	
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'L4Policy_addl4Policy.do' 
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
 	    theForm.action = 'L4Policy_modl4Policy.do' 
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
 	    theForm.action = 'L4Policy_dell4Policy.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="L4Policy_l4PolicyList.do" method="post" id="theForm">
 <s:hidden name="theForm.id" id="id"></s:hidden> 
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">l4Policy virtualIP:</td>
                    <td> 
						<s:textfield name="theForm.virtualIP" id="virtualIP"/> 
                    </td>
                    <td class="til">l4Policy名称:</td>
                    <td>
						<s:textfield name="theForm.l4PolicyName" id="l4PolicyName"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest(theForm)" />
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
				   <th onclick="sort(theTable,1,'string')">Virtual IP<br /> 	</th>
				   <th onclick="sort(theTable,2,'string')">L4 Policy  <br /> Name</th>                
                   <th onclick="sort(theTable,3,'string')">L4 <br /> Protocol </th>
                   <th onclick="sort(theTable,4,'string')">L4  <br /> Port </th>
                   <th onclick="sort(theTable,5,'string')">Source IP  <br /> From  </th>
                   <th onclick="sort(theTable,6,'string')">Source IP <br />  To  </th>
                   <th onclick="sort(theTable,7,'string')">Farm   <br /> Name</th>
                   <th onclick="sort(theTable,8,'string')">HTTP  <br /> Policy </th>
                   <th onclick="sort(theTable,9,'string')">SSL  <br />Policy </th>
                   <th onclick="sort(theTable,10,'string')">Compression  <br /> Policy </th>  
                   <th onclick="sort(theTable,11,'string')">Caching   <br />Policy</th>
                   <th onclick="sort(theTable,12,'string')">Client   <br />Authentication <br />Policy</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/></td>
							<td><s:text name="#theBean.virtualIP"/></td>
							<td><s:text name="#theBean.l4PolicyName" /></td>
							<td><s:text name="#theBean.l4Protocol" /></td>
							<td><s:text name="#theBean.l4Port" /></td>
							<td><s:text name="#theBean.sourceIPFrom"/></td>
							<td><s:text name="#theBean.sourceIPTo"/></td>
							<td><s:text name="#theBean.farmName" /></td>
							<td><s:text name="#theBean.httpPolicy"/></td>
							<td><s:text name="#theBean.SSLPolicy"/></td>
							<td><s:text name="#theBean.compressionPolicy"/></td>
							<td><s:text name="#theBean.cachingPolicy" /></td>
							<td><s:text name="#theBean.clientAuthenticationPolicy"/></td>
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
