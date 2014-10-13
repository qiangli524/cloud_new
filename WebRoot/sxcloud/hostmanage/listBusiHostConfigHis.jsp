<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.UPDATEUSER.value = '';
		theForm.UPDATETYPE.value = -1;
	}

   function searchRequest() {
        theForm.action = "hostconfighis_listBusiHostConfigHis.do";
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
 	    alert("请勾选需要删除主机配置历史信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条配置历史信息");
 	    return false ;
 	    }
 	    theForm.action = 'hostconfighis_delBusiHostConfigHis.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="hostconfighis_listBusiHostConfigHis.do" method="post" id="theForm">
 <s:hidden name="theForm.ID" id="ID"/>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">操作人:</td>
                    <td>
						<s:textfield name="theForm.UPDATEUSER" id="UPDATEUSER"/>
                    </td>
                    <td class="til">操作类型:</td>
                    <td>
                    	<s:select headerKey="" headerValue="请选择"   list="#{'1':'更新','2':'删除 '}" name="theForm.UPDATETYPE" id="UPDATETYPE">
     					</s:select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
                
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			       <th>编号</th>
				   <th onclick="sort(theTable,1,'string')">主机编号</th>
				   <th onclick="sort(theTable,2,'string')">主机用户</th>                
                   <th onclick="sort(theTable,3,'string')">主机密码</th>
                   <th onclick="sort(theTable,4,'string')">操作类型</th>
                   <th onclick="sort(theTable,5,'string')">操作人</th>
                   <th onclick="sort(theTable,6,'date')">操作时间</th>
			  </tr>
			  </thead>
			  <tbody>
			   
			      <s:iterator id="theBean" value="theForm.resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/></td>
							<td><s:property value="#theBean.HOSTID" /></td>
							<td><s:property value="#theBean.HOSTUSERNAME"/></td>
							<td><s:property value="#theBean.HOSRPWD" /></td>
							<td>
								<s:if test="#theBean.UPDATETYPE==1">更新</s:if>
							  	<s:if test="#theBean.UPDATETYPE==2">删除</s:if>
							</td>
							<td><s:property value="#theBean.UPDATEUSER" /></td>
							<td><s:property value="#theBean.INSERTTIME" /></td>
						</tr>
				</s:iterator>  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>
