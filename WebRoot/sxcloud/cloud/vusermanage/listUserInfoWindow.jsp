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
	
	function BtnSave_OnClick1(value)
	{ 
	
    	var obj = document.getElementsByName("IDS");
    	
    	for(var i=0;i<obj.length;i++){
	         if( obj[i].checked){
				window.opener.document.getElementById("theForm").USER_ID.value=document.getElementsByName("HIDID")[i-1].value;
				window.opener.document.getElementById("theForm").USER_NAME.value=document.getElementsByName("HIDACCOUNT")[i-1].value;
				window.opener.document.getElementById("theForm").NAME.value=document.getElementsByName("HIDNAME")[i-1].value;
				window.opener.document.getElementById("theForm").EMAIL.value=document.getElementsByName("HIDEMAIL")[i-1].value;
				//window.opener.document.getElementById("theForm").MOBILE.value=document.getElementsByName("HIDMOBILE")[i].value;
				window.opener.document.getElementById("theForm").PASSWORD.value=document.getElementsByName("HIDPASSWORD")[i-1].value;
				//window.opener.document.getElementById("theForm").CREATETIME.value=document.getElementsByName("HIDCREATETIME")[i].value;
			 
	            }
	    }
		window.close();
	}
	
	//检索按钮按下
	function search() {
		   document.getElementById("theForm").action="vuser_listUserInfoWindow.do";
		   document.getElementById("theForm").submit();
	   }
	function  manualreset(theForm){
    	theForm.ID.value = 0;
    }
    
</script>
</head>

<body>
	<s:form action="listUserInfoWindow" method="post" id="theForm">
	<input type="hidden" name="IDS"></input>
		<div class="scrollbody">
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								编号:
							</td>
							<td>
								<s:text name="theForm.ID" id="ID" />
							</td>
						</tr>
						<tr>
							 <td colspan="8" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
									<input type = "button" class="btn-style02" value = "重置" onclick = "manualreset(document.getElementById('theForm'))" />
									<input type="button" class="btn-style02" value="关闭"
											onclick="window.close();" />
		                        </div>
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
									<th>选择</th>
									<th onclick="sort(theTable,1,'string')">用户编号</th>
									<th onclick="sort(theTable,2,'string')">用户名</th>
									<th onclick="sort(theTable,3,'string')">姓名</th>
									<th onclick="sort(theTable,4,'string')">邮箱</th>
									<th onclick="sort(theTable,5,'string')">手机号</th>
									<th onclick="sort(theTable,6,'string')">密码</th>
									<th onclick="sort(theTable,7,'date')">创建时间</th>
								</tr>
							</thead>
							<tbody>
									<s:iterator id="theBean" value="theForm.resultList">
										<tr>
											<td>
												<input type="radio" name="IDS"
													onclick="BtnSave_OnClick1('<s:property value="#theBean.ID" />')" />
											</td>
											<td>
												
												<s:property value="#theBean.ID" />
												<input type="hidden" name="HIDID" value="<s:property value="#theBean.ID" />"/>
											</td>
											<td>
												<s:property value="#theBean.ACCOUNT" />
												<input type="hidden" name="HIDACCOUNT" value="<s:property value="#theBean.ACCOUNT"/>"/>
											</td>
											<td>
												<s:property value="#theBean.NAME"  />
												<input type="hidden" name="HIDNAME" value="<s:property value="#theBean.NAME" />"/>
											</td>
											<td>
												
												<s:property value="#theBean.EMAIL"  />
												<input type="hidden" name="HIDEMAIL" value="<s:property value="#theBean.EMAIL" />"/>
											</td>
											<td>
												<s:property value="#theBean.MOBILE" />
												<input type="hidden" name="HIDMOBILE" value="<s:property value="#theBean.MOBILE" />"/>
											</td>
											<td>
												<s:property value="#theBean.PASSWORD"  />
												<input type="hidden" name="HIDPASSWORD" value="<s:property value="#theBean.PASSWORD" />"/>
											</td>
											<td>
												<s:property value="#theBean.CREATETIME" />
												<input type="hidden" name="HIDCREATETIME" value="<s:property value="#theBean.CREATETIME" />"/>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html:html>