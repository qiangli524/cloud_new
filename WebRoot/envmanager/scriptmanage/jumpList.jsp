<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
  <head>
   
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest() { 
		theForm.submit();
 	}
 	
 	function resetForm(){
			theForm.EQ_IP.value = "";
		}
	var api = frameElement.api, W = api.opener; 
	function actionJump(EQ_IP,HOSTUSERNAME,HOSRPWD){
		W.document.getElementById('SCRIPT_IP').value = EQ_IP; 
		W.document.getElementById('SCRIPT_USERNAME').value = HOSTUSERNAME; 
		W.document.getElementById('SCRIPT_PASSWORD').value = HOSRPWD; 
	}
	
	</script>

  </head>
  
  <body>
  
   <s:form action="scriptmanage_jumpScriptmanageObj.do" id="theForm" method="post"
		>
		<s:hidden name="theForm.id" id="id"></s:hidden>
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
								服务器IP:
							</td>
							<td>
								<s:textfield name="theForm.EQ_IP" cssClass="txt"
									id="EQ_IP"></s:textfield>
							</td>
						
						
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick = "javascript:searchRequest()"/>
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
							
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									
									<th>
										服务器IP
									</th>
									<th>
										用户
									</th>
									<th>
										密码
									</th>
									<th>操作</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.hostipList" id="theBean">
									<tr >
										
										<td>
											<s:property value="#theBean.EQ_IP" />
										</td>
									
										<td>
											<s:property value="#theBean.HOSTUSERNAME" />
										</td>
										<td>
											<s:property value="#theBean.HOSRPWD" />
											
										</td>
										<td>
										<a href="#"><input type="button" class="thickbox btn-style02" value="选择" onclick="actionJump('<s:property value="#theBean.EQ_IP" />','<s:property value="#theBean.HOSTUSERNAME" />','<s:property value="#theBean.HOSRPWD" />');" /></a>
										</td>

									</tr>
								</s:iterator>
							</tbody>


						</table>
							<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
							</div>
						</div>
					</div>
				</div>
		

	</s:form>
   
 </body>


