<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
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

	function resetForm(theForm){
		theForm.PROJECT_ID.value = '';
		theForm.NAME.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'project_addProject.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.PROJECT_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改的信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息！");
 	    return false ;
 	    }
 	    theForm.action = 'project_modProject.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.PROJECT_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'project_delProject.do'  
		theForm.submit();
 	}
 	function openNewWindows(PROJECT_ID,NAME,DESCRIPTION){
		window.open("project_listVuserInfoWindow.do?PROJECT_ID="+PROJECT_ID+"&NAME="+NAME+"&DESCRIPTION="+DESCRIPTION,"listVuserInfoWindow",'width=800, height=600, resizable=yes');
 	}
</script>
</head>
<body>
	<s:form action="project_listProject" method="post" id="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								项目编号:
							</td>
							<td>
								<s:textfield name="theForm.PROJECT_ID" id="PROJECT_ID" cssClass="txt"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->
				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="增加"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										项目编号
									</th>
									<th onclick="sort(theTable,2,'string')">
										项目名称
									</th>
									<th onclick="sort(theTable,3,'string')">
										项目描述
									</th>
									<th>
										编辑用户
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<s:property value='#theBean.PROJECT_ID'/>" />
											</td>
											<td>
												<s:property value="#theBean.PROJECT_ID"/>
											</td>
											<td>
												<s:property value="#theBean.NAME"/>
											</td>
											<td>
												<s:property value="#theBean.DESCRIPTION"/>
											</td>
											<td>
												<s:if test="#theBean.PROJECT_ID==51">
										    		所有云用户
										    	</s:if>
												<s:else>
													<input type="button" class="thickbox btn-style02-100"
														value="编辑用户"
														onclick="javascript:openNewWindows('<s:property value='#theBean.PROJECT_ID'/>','<s:property value='#theBean.NAME'/>','<s:property value='#theBean.DESCRIPTION'/>');" />
												</s:else>
											</td>
											<!--   <td>
							  <logic:equal name="theBean" property="ISDEFAULT" value="0">否</logic:equal>
							  <logic:equal name="theBean" property="ISDEFAULT" value="1">是</logic:equal>
							</td>
							<td>
							  <logic:equal name="theBean" property="ISSTAGING" value="0">否</logic:equal>
							  <logic:equal name="theBean" property="ISSTAGING" value="1">是</logic:equal>
							</td>
							<td>
							  <logic:equal name="theBean" property="ISPUBLIC" value="0">否</logic:equal>
							  <logic:equal name="theBean" property="ISPUBLIC" value="1">是</logic:equal>
							</td>-->
										</tr>
								</s:iterator>	
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
