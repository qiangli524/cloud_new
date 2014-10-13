<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	});	
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.ACCOUNT.value = '';
		theForm.NAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'user_addUserInfo.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能修改一条信息");
 	    return false ;
 	    }
 	    theForm.action = 'user_modUserInfo.do' 
		theForm.submit();
 	}
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除一条信息");
 	    return false ;
 	    }
 	   if(confirm("确定要删除?")){
 	    	theForm.action = 'user_delUserInfo.do'  
			theForm.submit();
		}
 	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="user_listUserInfo.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">用户管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">账号：</label>
				<s:textfield name="theForm.ACCOUNT" id="ACCOUNT" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">姓名：</label>
				<s:textfield name="theForm.NAME" id="NAME" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>编号</th>
				   <th onclick="sort(theTable,0,'string')">账号</th>  
				   <%--           
                   <th onclick="sort(theTable,2,'string')">密码</th>
                    --%>    
                   <th onclick="sort(theTable,1,'string')">姓名</th>
                   <th onclick="sort(theTable,2,'string')">手机号</th>
                   <th onclick="sort(theTable,3,'string')">所属部门</th>
                   <th onclick="sort(theTable,4,'string')">所属用户组</th>
                   <!-- 
                   <th onclick="sort(theTable,5,'string')">邮箱</th>
                    -->
                   <th onclick="sort(theTable,5,'string')">状态</th>
                   <!-- 
                   <th>用户应用关联设置</th>
                   <th>用户权限</th>
                    -->
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/></td>
							<td><s:property value="#theBean.ACCOUNT"/></td>
							<!-- 
							<td>
							<s:property value="#theBean.PASSWORD"/></td>
							 -->
							<td><s:property value="#theBean.NAME"/></td>
							<td>
								<s:if test="#theBean.MOBILE!=null && ''!=#theBean.MOBILE">
									<s:property value="#theBean.MOBILE"/>
								</s:if>
								<s:else>
									暂无
								</s:else>
							</td>
							<!--  
							<td>
								<s:if test="#theBean.EMAIL!=null && ''!=#theBean.EMAIL">
									<s:property value="#theBean.EMAIL"/>
								</s:if>
								<s:else>
									暂无
								</s:else>
							</td>
							-->
							
							<!-- 
							<td>
							<a href="user_editUserDataAuthority.do?USERID=<s:text name="#theBean.ID"/>&FUNCSID=<%=(String)request.getParameter("FUNCSID") %>&ACCOUNT=<s:text name="#theBean.ACCOUNT"/>">
							用户应用关联
							</a>
							</td>
							<td>
							<a href="user_findUserAuthority.do?USERID=<s:text name="#theBean.ID"/>&FUNCSID=<%=(String)request.getParameter("FUNCSID") %>&ACCOUNT=<s:text name="#theBean.ACCOUNT"/>">
							权限查看
							</a>
							</td>
							 -->
							 <td><s:property value="#theBean.DATAAUTHORITY"/></td>
							 <td><s:property value="#theBean.GROUP_NAME"/></td>
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
			<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
