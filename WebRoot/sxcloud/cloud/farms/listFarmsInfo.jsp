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
		theForm.id.value = '';
		theForm.farmName.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'farm_addfarms.do' 
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
 	    theForm.action = 'farm_modfarms.do' 
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
 	    $.get("farm_queryAjx_del.do?id="+theForm.id.value,{'time':new Date().toString()}, function(data, textStatus){
					if(data=='ok'){
 	    theForm.action = 'farm_delfarms.do'  
		theForm.submit();
			   		}else{
			    	  theForm.id.value = '';
			    	  alert('Error: invalid value, farm in Layer 4 Policies. Remove Layer 4 policy first');
			      	  return false;
			   		 }		      
			      });
 	}
</script>
</head>
<body>
<s:form action="farm_farmsList.do" method="post" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">FARM ID:</td>
                    <td> 
						<s:textfield name="theForm.id" id="id"/> 
                    </td>
                    <td class="til">FARM名称:</td>
                    <td>
						<s:textfield name="theForm.farmName" id="farmName"/>
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
			  	   <th onclick="sort(theTable,0,'string')">Farm ID</th>
				   <th onclick="sort(theTable,1,'string')">Farm Name</th>
				   <th onclick="sort(theTable,2,'string')">Admin Status </th>                
                   <th onclick="sort(theTable,3,'date')">Aging Time </th>
                   <th onclick="sort(theTable,4,'string')">Dispatch Method </th>
                   <th onclick="sort(theTable,5,'string')">Connectivity Check Method </th>
                   <th onclick="sort(theTable,6,'string')">Sessions Mode </th>
                   <th onclick="sort(theTable,7,'string')">Operational Status </th> 
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id" />"/></td>
							<td><s:text name="#theBean.farmName"/></td>
							<td><s:text name="#theBean.adminStatus"/></td>
							<td><s:text name="#theBean.agingTime" /></td>
							<td><s:text name="#theBean.dispatchMethod" /></td>
							<td><s:text name="#theBean.connectivityCheckMethod" /></td>
							<td><s:text name="#theBean.sessionsMode"/></td>
							<td><s:text name="#theBean.operationalStatus"/></td> 
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
