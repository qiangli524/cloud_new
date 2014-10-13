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
		theForm.realAddress.value='';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'realserver_addRealServer.do' 
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
 	    theForm.action = 'realserver_modRealServer.do' 
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
 	    if(confirm("确定要删除该服务器信息吗?")==true){
	    	theForm.action = 'realserver_delRealServer.do'  
			theForm.submit();
 	    }
 	}
</script>
</head>
<body>
<s:form action="realserver_realServerList.do" method="post" id="theForm" cssClass="theForm">
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
								真实服务器名称:
							</td>
							<td>
								<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>
							</td>
							<td class="til">
								真实服务器地址:
							</td>
							<td>
								<s:textfield name="theForm.realAddress" id="realAddress" cssClass="txt"></s:textfield>
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
										服务器名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										地址
									</th>
									<th onclick="sort(theTable,3,'string')">
										虚拟服务器编号
									</th>
									<th onclick="sort(theTable,4,'string')">
										权重
									</th>
									<th onclick="sort(theTable,5,'string')">
										健康检查请求内容
									</th>
									<th onclick="sort(theTable,6,'string')">
										健康检查响应内容
									</th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
												<input name="checkboxid" type="checkbox"
													value="<s:property value="#theBean.INFO"/>" />
										</td>
										<td><s:property value="#theBean.name"/></td>
										<td><s:property value="#theBean.realAddress"/></td>
										<td><s:property value="#theBean.virtual_name"/></td>
										<td><s:property value="#theBean.weight"/></td>
										<td><s:property value="#theBean.request"/></td>
										<td><s:property value="#theBean.receive"/></td>
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
