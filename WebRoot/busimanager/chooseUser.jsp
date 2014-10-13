<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %> 
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

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
	
	function resetForm(userObj){
		userObj.ACCOUNT.value = '';
		userObj.NAME.value = '';
	}

    function searchRequest() { 
		userObj.submit();
 	}
 	
 	function selectUser() { 
       	var count = 0;
	    var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					count++;
				}
			}
		}
		if(count<1){
			alert("请选择用户！");
			return false;
		}else if(count>1){
			alert("一次只能选择一个用户！");
			return false;
		} 
		var flag = '<s:property value="flag"/>';
		var id =  $("[name='checkboxid']:checked").attr("value");//id
 		var username = $("[name='checkboxid']:checked").attr("username");//用户名
 		var phone = $("[name='checkboxid']:checked").attr("phone");//用户电话
 		var email = $("[name='checkboxid']:checked").attr("email");//邮箱
 		var account = $("[name='checkboxid']:checked").attr("account");//用户账号
 		if(flag == 'add'){
 			api.get("addbizsys").getUser(id,username);
 		}else{
 			api.get("updatebizsys").getUser(id,username);
 		}
 		/* if (id == null || id =='') {
 			api.get("add").getUser(id,name);
 		} else {
 			api.get("updatebizsys").getUser(id,name);
 		} */
 		
 	}
</script>
</head>
<body onLoad="self.focus();document.userObj.ACCOUNT.focus()" class="pop-body scrollbody">
<s:form action="bmanager_queryUserInfoList.do" method="post" cssStyle="theForm" id="userObj">
<s:hidden name="flag" id="flag"></s:hidden>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">选择用户</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                 <label class="vm">账号：</label>
                    	<s:textfield name="account" id="ACCOUNT" cssStyle="txt"></s:textfield>
                     <label class="vm">姓名：</label>
                  	  <s:textfield name="name" id="NAME" cssStyle="txt"></s:textfield>
                     <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			
        </div>
	<br>
	<br/>
	
		
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
							<td><br /></td>
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
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	

	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>

</s:form>
</body>
</html:html>
