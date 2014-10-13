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

	function resetForm(theForm){
		theForm.USER_ID.value = '';
		theForm.USER_NAME.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'vuser_addVuserManage.do' ;
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.USER_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("�빴ѡ��Ҫ�޸���Ϣ��");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ�ܴ�������Ϣ");
 	    return false ;
 	    }
 	    theForm.action = 'vuser_modVuserManage.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.USER_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("�빴ѡ��Ҫɾ��������Ϣ��");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ��ɾ������������Ϣ");
 	    return false ;
 	    }
 	    theForm.action = 'vuser_delVuserManage.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="vuser_listVuserManage.do" method="post" id="theForm">
 <s:hidden name="theForm.flag" id="flag" />
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">�û����:</td>
                    <td>
						<s:textfield name="theForm.USER_ID" id="USER_ID" />
                    </td>
                    <td class="til">�û���:</td>
                    <td>
						<s:textfield name="theForm.USER_NAME" id="USER_NAME" />
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "��ѯ" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "����" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="����" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="�޸�" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="ɾ��" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>ѡ��</th>
					<th onclick="sort(theTable,1,'string')">�û����</th>
					<th onclick="sort(theTable,2,'string')">�û���</th>
					<th onclick="sort(theTable,3,'string')">����</th>
					<th onclick="sort(theTable,4,'string')">����</th>
					<th onclick="sort(theTable,5,'string')">�ʼ�֪ͨ</th>               
					<th onclick="sort(theTable,6,'string')">����</th>
					<th onclick="sort(theTable,7,'string')">�Ƿ������</th>
					<th onclick="sort(theTable,8,'string')">�Ƿ����Ա</th>
					<th onclick="sort(theTable,9,'string')">��ɫ</th>
					<th onclick="sort(theTable,10,'date')">���ʱ��</th>
				</tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean"  value="theForm.resultList">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.USER_ID" />"/></td>
							<td><s:property value="#theBean.USER_ID" /></td>
							<td><s:property value="#theBean.USER_NAME" /></td>
							<td><s:property value="#theBean.PASSWORD" /></td>
							<td><s:property value="#theBean.NAME" /></td>
							<td>
							  <s:if test="#theBean.EMAILNOTIFICATIONS==1">��</s:if>
							  <s:else>��</s:else>
							</td>
							<td><s:property value="#theBean.EMAIL" /></td>
							<td>
								<s:if test="#theBean.ISAPPROVER==1">��</s:if>
							  	<s:else>��</s:else>
							</td>
							<td>
								<s:if test="#theBean.ISADMIN==1">��</s:if>
							  	<s:else>��</s:else>
							</td>
							<td><s:property value="#theBean.ROLE" /></td>
							<td><s:property value="#theBean.INS_DATE" /></td>
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
