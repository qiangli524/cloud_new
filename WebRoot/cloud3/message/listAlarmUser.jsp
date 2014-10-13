<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.name.value = '';
		theForm.email.value = '';
<%--		theForm.telephone.value = '';--%>
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'message_addAlarmUser.do' 
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
 	    theForm.action = 'message_modAlarmUser.do' 
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
 	    theForm.action = 'message_delAlarmUser.do'  
		theForm.submit();
 	}
</script>
</head>
<body onLoad="self.focus();document.theForm.name.focus()">
<s:form action="message_listAlarmUser" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">姓名:</td>
                    <td>
                    	<s:textfield name="theForm.name" id="name" cssStyle="txt"></s:textfield>
                    </td>
                    <td class="til">Email:</td>
                    <td>
                  	  <s:textfield name="theForm.email" id="email" cssStyle="txt"></s:textfield>
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
	
	<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">编号</th>
				   <th onclick="sort(theTable,1,'string')">姓名</th>                
                   <th onclick="sort(theTable,2,'string')">Email</th>
                   <th onclick="sort(theTable,3,'string')">手机号</th>
                   <th onclick="sort(theTable,4,'string')">用户关联主机/虚机</th>
                   <th onclick="sort(theTable,5,'string')">用户关联业务</th>
                   <th onclick="sort(theTable,6,'string')">用户订阅对象</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:property value="#theBean.resultList"/>
			  <s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/></td>
							<td><s:property value="#theBean.name"/></td>
							<td>
							<s:property value="#theBean.email"/></td>
							<td><s:property value="#theBean.telephone"/></td>
							<td>
							<a target="" href="message_editUserWarning.do?USERID=<s:text name="#theBean.id"/>">
							用户告警订阅配置
							</a>
							</td>
							<td>
							<a target="" href="message_editBusiUserWarning.do?USERID=<s:text name="#theBean.id"/>">
							用户告警订阅配置
							</a>
							</td>
							<td>
							<a target="" href="message_listUserSubscribe.do?USERID=<s:text name="#theBean.id"/>">
							查看
							</a>
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
