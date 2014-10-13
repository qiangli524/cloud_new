<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.name.value = '';
		//theForm.NAME.value = '';
		theForm.virtualAddress.value='';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'virserver_addVirtualServer.do' 
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
 	    alert("请勾选需要修改的虚拟服务器！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条虚拟服务器信息");
 	    return false ;
 	    }
 	    theForm.action = 'virserver_modVirtualServer.do' 
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
 	    alert("请勾选需要删除的虚拟服务器！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条虚拟服务器信息");
 	    return false ;
 	    }
 	    if(confirm("确定要删除该虚拟服务器吗?")==true){
 	    	theForm.action = 'virserver_delVirtualServer.do'  
			theForm.submit();
 	    }
 	}
</script>
</head>
<body>
<s:form action="virserver_listVirtualServer.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.id" id="id"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								虚拟服务器名称:
							</td>
							<td>
								<s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>
							</td>
							<td class="til">
								虚拟服务器地址:
							</td>
							<td>
								<s:textfield name="theForm.virtualAddress" cssClass="txt" id="virtualAddress"></s:textfield>
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
										虚拟服务器名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										虚拟服务器地址
									</th>
									<th onclick="sort(theTable,3,'string')">
										操作员
									</th>
									<th onclick="sort(theTable,4,'date')">
										更新时间
									</th>

								</tr>
							</thead>
							<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td>
										<input name="checkboxid" type="checkbox"
													value="<s:property value="#theBean.id"/>" />
									</td>
									<td><s:property value="#theBean.name"/></td>
									<td><s:property value="#theBean.virtualAddress"/></td>
									<td><s:property value="#theBean.optr_name"/></td>
									<td><s:property value="#theBean.opt_time"/></td>
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
