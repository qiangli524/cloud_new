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
 	
 	function delRequest() {
 	var theForm = document.getElementById("theForm");
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.pwd_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'pwdobj_deletePwdObj.do'; 
		theForm.submit();
		
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.pwd_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'pwdobj_updatePwdObj.do';
		theForm.submit();
 	}
 	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'pwdobj_insertPwdObj.do';
	  	theForm.submit();
 	}
 	function forPassId(ID){
 	  theForm.action = '';
	  theForm.submit();
 	}
 	function resetForm(){
			theForm.NAME.value = "";
		}
 	
	</script>

  </head>
  
  <body>
  
   <s:form action="pwdobj_queryPwdObj.do" id="theForm" method="post"
		>
		<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"/>
		<s:hidden name="theForm.pwd_id" id="pwd_id"></s:hidden>
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
								IP地址:
							</td>
							<td>
								<s:textfield name="theForm.IPADDRESS" cssClass="txt"
									id="IPADDRESS"></s:textfield>
							</td>
							<td class="til">
								用户名:
							</td>
							<td>
								<s:textfield name="theForm.PWD_USER" cssClass="txt"
									id="NAME"></s:textfield>
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
							<li>
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
							</li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										IP地址
									</th>
									<th>
										用户名
									</th>
									<th>
										密码
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.pwdList" id="theBean">
									<tr >
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.pwd_id'/>"/>
										</td>
										<td>
											<s:property value="#theBean.IPADDRESS" />
										</td>
										
										<td>
											<s:property value="#theBean.PWD_USER" />
										</td>
										<td>
											<s:property value="#theBean.PWD_NEW" />
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

