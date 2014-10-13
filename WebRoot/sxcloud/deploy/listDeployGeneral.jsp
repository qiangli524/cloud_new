<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
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
		theForm.start_time.value = '';
		theForm.end_time.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'addDeployGeneral.do' 
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
 	    alert("�빴ѡ��Ҫ�޸ĸ��Ի����ã�");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ�ܴ��������Ի�������Ϣ��");
 	    return false ;
 	    }
 	    theForm.action = 'modDeployGeneral.do'
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
 	    alert("�빴ѡ��Ҫɾ���Ĳ�����Ի�������Ϣ��");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ��ɾ������������Ի�������Ϣ");
 	    return false ;
 	    }
 	    theForm.action = 'delDeployGeneral.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<html:form action="listDeployGeneral" method="post" styleId="theForm">
 <bean:define id="theForm" name="DeployGeneralForm"/>
 <html:hidden name="theForm" property="ID" />
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
               <table width="100%" class="querytable" border="0">
					<tr>
						<td class="til">
							�쳣��ֹʱ��:��
						</td>
						<td>
							<html:text name="theForm" property="start_time" size="10"
								readonly="true" styleClass="txt" styleId="datepicker1" />
						</td>
						<td class="til">
							��
						</td>
						<td>
							<html:text name="theForm" property="end_time" size="10"
								readonly="true" styleClass="txt" styleId="datepicker2" />
						</td>
					</tr>
					<tr>
						<td colspan="8" class="btns">
							<div>
								<input type="button" class="thickbox btn-style02" value="��ѯ"
									onclick="javascript:searchRequest()" />
								<input type="button" class="btn-style02" value="����"
									onclick="javascript:resetForm(document.getElementById('theForm'))" />
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
			<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
				   <th>���:</th>
				   <th onclick="sort(theTable,1,'string')">�������:</th>                
                   <th onclick="sort(theTable,2,'string')">Ӧ�ñ��:</th>
                   <th onclick="sort(theTable,3,'string')">Ŀ���ַ���:</th>
                   <th onclick="sort(theTable,4,'string')">�滻�ַ���:</th>
                   <th onclick="sort(theTable,5,'date')">����ʱ��</th>
			  </tr>
			  </thead>
			  <tbody>
			   <logic:present name="theForm" property="resultList">
			      <logic:iterate id="theBean" name="theForm" property="resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<bean:write name="theBean" property="ID"/>"/></td>
							<td><bean:write name="theBean" property="HOSTID"/></td>
							<td><bean:write name="theBean" property="APPID"/></td>
							<td><bean:write name="theBean" property="TARGET"/></td>
							<td><bean:write name="theBean" property="REPLACEMENT"/></td>
							<td><bean:write name="theBean" property="UPDATETIME"/></td>
						</tr>
				</logic:iterate>
				</logic:present>		  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</html:form>
</body>
</html:html>
