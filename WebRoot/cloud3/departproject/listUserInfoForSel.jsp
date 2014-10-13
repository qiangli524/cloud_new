<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript">
    var api = frameElement.api;
    var w = api.opener; 
     	
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
	
	
	$(function() {

		$("#searchForm").click(function() {
			searchRequest();
		});
		$("#resetForm").click(function() {
			resetForm(userObj);
		});

	});

	function resetForm(userObj) {
		userObj.ACCOUNT.value = '';
		userObj.NAME.value = '';
	}

	function searchRequest() {
		userObj.submit();
	}

	function selectUser() {
		var count = 0;
		var account = '';
		var username = '';
		var phone = '';
		var email = '';
		var checkboxids = document.getElementsByName("checkboxid");
		if (checkboxids != null && checkboxids.length > 0) {
			for ( var i = 0; i < checkboxids.length; i++) {
				if (checkboxids[i].checked) {
					account += checkboxids[i].getAttribute("account") + '_';
					username += checkboxids[i].getAttribute("username") + '_';
					phone += checkboxids[i].getAttribute("phone") + '_';
					email += checkboxids[i].getAttribute("email") + '_';
					count++;
				}
			}
		}
		if (count < 1) {
			alert("请选择用户！");
			return false;
		}
		/* else if(count>1){
			alert("一次只能选择一个用户！");
			return false;
		} */
		var id = '<s:property value="obj.ID"/>';
		var flag = '<s:property value="flag"/>';
		/*  		var username = $("[name='checkboxid']:checked").attr("username");//用户名*/
		/* var phone = $("[name='checkboxid']:checked").attr("phone");//用户电话 */
		/* var email = $("[name='checkboxid']:checked").attr("email");//邮箱 */
		/*  		var account = $("[name='checkboxid']:checked").attr("account");//用户账号*/
		api.get(flag).getUserInfo(username, phone, email, account);
	}
</script>
</head>
<body onLoad="self.focus();document.userObj.ACCOUNT.focus()" class="pop-body scrollbody">
<s:form action="departproject_queryUserInfoList.do" method="post" cssStyle="userObj" id="userObj">
<s:hidden name="userObj.ID" id="ID"></s:hidden>
<s:hidden name="flag" id="flag"></s:hidden>

			<div class="bord-1 pd-10">			
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
        			<label class="vm">账号:</label>
                    	<s:textfield name="userObj.ACCOUNT" id="ACCOUNT" cssStyle="txt"></s:textfield>
				</div>
				<div class="filtrate-field">
        			<label class="vm">姓名:</label>
                  	  <s:textfield name="userObj.NAME" id="NAME" cssStyle="txt"></s:textfield>
				</div>
				<div class="filtrate-field">
        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"   value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" id="resetForm"  value="重置" /></span>
				</div>
			</div>
		<div class="utt-2 mgt-20">
		</div>

	
	<div class="blue-wrap noborder"  style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">编号</th>
				   <th onclick="sort(theTable,1,'string')">账号</th>                
                   <th onclick="sort(theTable,2,'string')">密码</th>
                   <th onclick="sort(theTable,3,'string')">姓名</th>
                   <th onclick="sort(theTable,4,'string')">手机号</th>
                   <th onclick="sort(theTable,5,'string')">邮箱</th>
                   <th onclick="sort(theTable,6,'string')">状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"
							username='<s:property value="#theBean.NAME"/>' phone='<s:property value="#theBean.MOBILE"/>' 
							email='<s:property value="#theBean.EMAIL"/>' account='<s:property value="#theBean.ACCOUNT"/>' /></td>
							<td><s:property value="#theBean.ACCOUNT"/></td>
							<td>
							<s:property value="#theBean.PASSWORD"/></td>
							<td><s:property value="#theBean.NAME"/></td>
							<td><s:property value="#theBean.MOBILE"/></td>
							<td><s:property value="#theBean.EMAIL"/></td>
							<td>
							<s:if test="#theBean.STATUS==0">
							冻结
							</s:if>
							<s:elseif test="#theBean.STATUS==1">
							活跃
							</s:elseif>
							<s:elseif test="#theBean.STATUS==2">
							锁定
							</s:elseif>
							</td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			 <div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=userObj" />
			</div>	
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>
