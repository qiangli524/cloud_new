<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.TYPE_NAME.value = '';
		theForm.TYPE.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'templetConfig_addTempletTypeConfig.do' 
		theForm.submit();
	}
	function modRequest() { 
	    var type='';
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      type = checkboxids[i].value;
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
 	    theForm.action = 'templetConfig_modTempletTypeConfig.do?TYPE='+type;
		theForm.submit();
 	}
 	function delRequest() {
 	    var type='';
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      type = checkboxids[i].value;
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
 	    if(confirm("确定要删除该服务吗?")==true){
 	    	theForm.action = 'templetConfig_delTempletTypeConfig.do?TYPE='+type;
			theForm.submit();
 	    }
 	}
 	
</script>
</head>
<body>
	<s:form action="templetConfig_listTempletTypeConfig.do" method="post"
		cssClass="theForm" id="theForm">
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
								服务名称:
							</td>
							<td>
								<s:textfield name="theForm.TYPE_NAME" id="TYPE_NAME"
									cssClass="txt"></s:textfield>
							</td>
							<td class="til">
								服务类型:
							</td>
							<td>
								<s:textfield name="theForm.TYPE" id="TYPE" cssClass="txt"></s:textfield>
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
						<table id="theTable" width="100%" class="blue-table sorttable"
							border="0" cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										服务名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										描述
									</th>
									<th onclick="sort(theTable,3,'string')">
										服务类型
									</th>
									<th>
										类型配置
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value="#theBean.TYPE"/>" />
										</td>
										<td>
											<s:property value="#theBean.TYPE_NAME" />
										</td>
										<td>
											<s:property value="#theBean.TYPE_DESC" />
										</td>
										<td>
											<s:property value="#theBean.TYPE" />
										</td>
										<td>
											<a
												href="templetConfig_listTempletConfig.do?TYPE=<s:property value="#theBean.TYPE"/>">
												类型配置 </a>
										</td>
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
