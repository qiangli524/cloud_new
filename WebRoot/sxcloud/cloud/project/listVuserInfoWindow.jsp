<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	//检索按钮按下
	function search() {
		   document.getElementById("theForm").action="ibmuser_listUserInfoWindow.do";
		   document.getElementById("theForm").submit();
	   }
	function searchRequest() { 
		theForm.submit();
 	}
	
    function submitRequest(){
    	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    var subNum = '';
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      subNum += checkboxids[i].value + ":" + $('#'+checkboxids[i].value+' option:selected').val() + ",";
 	     }
 	    }
 	      theForm.USER_STAT_ID.value = subNum;
 	    }
 	     document.getElementById("theForm").action="ibmuser_saveProVuser.do";
		 document.getElementById("theForm").submit();
		window.location.href=window.location.href;
		 alert("保存成功！");
		 check();
    }
 	
    function check(){
    	var checkboxids = document.getElementsByName("checkboxid");
    	$.getJSON("ibmuser_getVuserRole.do?projectId="+theForm.PROJECT_ID.value,{'time':new Date().toString()},function(data){
    		for(j=0;j<data.length;j++){
    			if (data[j].USER_ROLE=='OWNER') {
    				$('#'+data[j].USER_ID).attr('value','1');
    				checkboxids[j].checked="true";
    			} else if (data[j].USER_ROLE=='VIEWER') {
    				$('#'+data[j].USER_ID).attr('value','0');
    				checkboxids[j].checked="true";
    			}  else if (data[j].USER_ROLE=='USER') {
    				$('#'+data[j].USER_ID).attr('value','2');
    				checkboxids[j].checked="true";
    			}
    		}
    	});
    }
</script>
</head>
<body onload=check()>
	<s:form action="ibmuser_listVuserInfoWindow" method="post" id="theForm">
	<s:hidden name="theForm.PROJECT_ID" id="PROJECT_ID"></s:hidden>
	<s:hidden name="theForm.USER_STATUS" id="USER_STATUS"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.NAME" id="NAME"></s:hidden>
	<s:hidden name="theForm.DESCRIPTION" id="DESCRIPTION"></s:hidden>
	<s:hidden name="theForm.USER_STAT_ID" id="USER_STAT_ID"></s:hidden>
		<div class="scrollbody">
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								用户编号:
							</td>
							<td>
								<s:textfield name="theForm.USER_ID" id="USER_ID" cssClass="txt"></s:textfield>
							</td>
							<td class="til">
								用户名:
							</td>
							<td>
								<s:textfield name="theForm.USER_NAME" id="USER_NAME" cssClass="txt"></s:textfield>
							</td>
							<td>
								<input type="button" class="thickbox btn-style02" value="查询"
									onclick="javascript:searchRequest()" />
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder">
					<div class="table-head">
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
										用户编号
									</th>
									<th onclick="sort(theTable,2,'string')">
										用户名
									</th>
									<th onclick="sort(theTable,3,'date')">
										创建时间
									</th>
									<th onclick="sort(theTable,4,'string')">
										项目角色
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<s:property value='#theBean.USER_ID'/>" />
												<input type="hidden" name="USER_STATUS1"
													value="<s:property value='#theBean.USER_STATUS'/>" />
												<input type="hidden" name="PROJECT_ID1"
													value="<s:property value='#theBean.PROJECT_ID'/>" />
											</td>
											<td>
												<s:property value="#theBean.USER_ID"/>
											</td>
											<td>
												<s:property value="#theBean.USER_NAME"/>
											</td>
											<td>
												<s:property value="#theBean.INS_DATE"/>
											</td>
											<s:if test="#theBean.ISADMIN==0">
												<td>
													<select
														id="<s:property value='#theBean.USER_ID'/>"
														name="huojl">
														<option value="1">
															所有者
														</option>
														<option value="0">
															查看者
														</option>
														<option value="2">
															用户
														</option>
													</select>
												</td>
											</s:if>
											<s:elseif test="#theBean.ISADMIN==1">
												<td>
													<s:if test="#theBean.ISADMIN==1">
													所有者
													</s:if>
												</td>
											</s:elseif>
										</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<table width="100%" border="0" cellspacing="0"
						class="pop-table nosize">
						<tr>
							<td colspan="4" class="btnCenter">
								<input type="button" class="thickbox btn-style02" value="确定"
									onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
								<input type="button" class="btn-style02" value="返回"
									onclick="window.close();" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
