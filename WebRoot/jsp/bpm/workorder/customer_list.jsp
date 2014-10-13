<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectUser,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
		//重置表单数据
		$("#reset").click(function(){
			$("#ACCOUNT").val("");
			$("#NAME").val("");
			$("#MOBILE").val("");
			$("#EMAIL").val("");
		});
	})

	//回传到父页面设值
	function selectUser(){
	   var check_box = $("[input[name='checkboxid']:checked");
 	   if (check_box.length == 1) {
           $('input[name="checkboxid"]:checked').each(function () {
               w.document.getElementById('account').value = $(this).attr("ID");
	       	   w.document.getElementById('name').value = $(this).attr("UNAME");
	       	   w.document.getElementById('phone').value = $(this).attr("MOBILE");
	       	   w.document.getElementById('email').value = $(this).attr("EMAIL");
           });
       } else {
           if ($('input[name="checkboxid"]:checked').length > 0) {
               alert('请仅选择一个客户！');
           }
       }
	}
	function resetForm(theForm){
		theForm.ACCOUNT.value = '';
		theForm.NAME.value = '';
		theForm.MOBILE.value = '';
		theForm.EMAIL.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'user_addUserInfo.do' 
		theForm.submit();
 	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="workorder_queryCustomerList.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">用户管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10 mgb-10">
				<label class="vm">账号：</label>
				<s:textfield name="customer.ACCOUNT" id="ACCOUNT" cssClass="inpt-1 vm" value="" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">姓名：</label>
				<s:textfield name="customer.NAME" id="NAME" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<label class="mgl-20 vm">手机：</label>
				<s:textfield name="customer.MOBILE" id="MOBILE" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<label class="mgl-20 vm">邮箱：</label>
				<s:textfield name="customer.EMAIL" id="EMAIL" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" id="reset" value="重置" /></span>
			</div>
			<%--<div class="utt-2 mgt-20">
				<a class="icon-export" href="javascript:void(0)">选择</a>
			</div>
			--%><table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				   <th onclick="sort(theTable,0,'string')">账号</th>  
                   <th onclick="sort(theTable,1,'string')">姓名</th>
                   <th onclick="sort(theTable,2,'string')">手机</th>
                   <th onclick="sort(theTable,3,'string')">邮箱</th>
			  </tr>
			  </thead>
			  <tbody>
				  <s:if test="%{customerList!=null}">
					  <s:iterator value="customerList" id="theBean">
								<tr>
									<td><input name="checkboxid" type="checkbox"
										 ID="<s:text name="#theBean.ID"/>" 
										 ACCOUNT="<s:text name="#theBean.ACCOUNT"/>" 
										 UNAME="<s:text name="#theBean.NAME"/>" 
										 MOBILE="<s:text name="#theBean.MOBILE"/>" 
										 EMAIL="<s:text name="#theBean.EMAIL"/>" 							
									/></td>
									<td><s:property value="#theBean.ACCOUNT"/></td>
									<td><s:property value="#theBean.NAME"/></td>
									<td>
										<s:if test="#theBean.MOBILE!=null && ''!=#theBean.MOBILE">
											<s:property value="#theBean.MOBILE"/>
										</s:if>
										<s:else>
											暂无
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.EMAIL!=null && ''!=#theBean.EMAIL">
											<s:property value="#theBean.EMAIL"/>
										</s:if>
										<s:else>
											暂无
										</s:else>
									</td>
								</tr>
						</s:iterator>	
				  </s:if>
				  <s:else>
						<tr><td colspan="5">没有查到任何记录</td></tr>
				  </s:else>
			  </tbody>
			</table>
		</div>
</s:form>
</div>
</body>
</html:html>
