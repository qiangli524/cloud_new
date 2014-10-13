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

	function resetForm(theForm){
		theForm.USER_ID.value = '';
		theForm.USER_NAME.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'vuser_addVuserManage.do' ;
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.USER_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'vuser_modVuserManage.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.USER_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'vuser_delVuserManage.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="vuser_listVuserManage.do" method="post" id="theForm">
 <s:hidden name="theForm.flag" id="flag" />
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">用户编号:</td>
                    <td>
						<s:textfield name="theForm.USER_ID" id="USER_ID" />
                    </td>
                    <td class="til">用户名:</td>
                    <td>
						<s:textfield name="theForm.USER_NAME" id="USER_NAME" />
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
				<li><input type="button" class="thickbox btn-style02" value="创建" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>选择</th>
					<th onclick="sort(theTable,1,'string')">用户编号</th>
					<th onclick="sort(theTable,2,'string')">用户名</th>
					<th onclick="sort(theTable,3,'string')">密码</th>
					<th onclick="sort(theTable,4,'string')">名字</th>
					<th onclick="sort(theTable,5,'string')">邮件通知</th>               
					<th onclick="sort(theTable,6,'string')">邮箱</th>
					<th onclick="sort(theTable,7,'string')">是否审核人</th>
					<th onclick="sort(theTable,8,'string')">是否管理员</th>
					<th onclick="sort(theTable,9,'string')">角色</th>
					<th onclick="sort(theTable,10,'date')">入库时间</th>
				</tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean"  value="theForm.resultList">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.USER_ID" />"/></td>
							<td><s:property value="#theBean.USER_ID" /></td>
							<td><s:property value="#theBean.USER_NAME" /></td>
							<td><s:property value="#theBean.PASSWORD" /></td>
							<td><s:property value="#theBean.NAME" /></td>
							<td>
							  <s:if test="#theBean.EMAILNOTIFICATIONS==1">是</s:if>
							  <s:else>否</s:else>
							</td>
							<td><s:property value="#theBean.EMAIL" /></td>
							<td>
								<s:if test="#theBean.ISAPPROVER==1">是</s:if>
							  	<s:else>否</s:else>
							</td>
							<td>
								<s:if test="#theBean.ISADMIN==1">是</s:if>
							  	<s:else>否</s:else>
							</td>
							<td><s:property value="#theBean.ROLE" /></td>
							<td><s:property value="#theBean.INS_DATE" /></td>
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
