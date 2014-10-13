<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<style type="text/css">
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
 function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(theForm){
	    theForm.APPNAME.value= '';
		theForm.IP.value = '';
	}
</script>
</head>
<body>
<s:form action="dephis_listDeployExampleHis" method="post" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>

<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">����Ӧ��:</td>
				    <td>
				        <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-��ѡ��-">
						</s:select> 
				    </td>
                    <td class="til">����IP:</td>
                    <td>
                        <s:textfield name="theForm.IP" id="IP" cssClass="txt"></s:textfield>
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
<%--				<li><input type="button" class="thickbox btn-style02" value="����" onclick = "addRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="�޸�" onclick = "modRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="ɾ��" onclick = "delRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="����" onclick = "deployRequestAll();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="ж��" onclick = "uninstallRequestAll();return false;" /></li>--%>
			</ul>
			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">����IP</th>
<%--				   <th>����IP</th>--%>
				   <th onclick="sort(theTable,1,'string')">Ӧ��</th>
				   <th onclick="sort(theTable,2,'string')">����·��</th>
				   <th onclick="sort(theTable,3,'string')">����</th>
				   <th onclick="sort(theTable,4,'string')">����</th>
				   <th onclick="sort(theTable,5,'string')">����״̬</th>
                   <th onclick="sort(theTable,6,'string')">������״̬</th>
                   <th onclick="sort(theTable,7,'date')">����ʱ��</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><s:property value="#theBean.IP"  /></td>
<%--							<td><bean:write name="theBean" property="VLANIP"/></td>--%>
							<%--<td><bean:write name="theBean" property="STRATEGYNAME"/></td>--%>
							<td><s:property value="#theBean.APPNAME"  /></td>
							<td style="width: 150px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"  />'><s:property value="#theBean.DEPLOYPATH"  /></a></td>
							<td>
								<s:if test="#theBean.isbackup==0">������</s:if>
								<s:elseif test="#theBean.isbackup==1">����</s:elseif>						</td>
							
							<td>
								<s:if test="#theBean.isrestart==0">������</s:if>
								<s:elseif test="#theBean.isrestart==1">����</s:elseif>								</td>
							<td>
							  <span id='div<bean:write name="theBean" property="ID"/>'>
							 <s:if test="#theBean.DEPLOY_FLAG==0">
								    δ����
								</s:if>
							  <s:elseif test="#theBean.DEPLOY_FLAG==1">
								  ���ڲ���
								</s:elseif>
							  <s:elseif test="#theBean.DEPLOY_FLAG==2">
								  �Ѳ���
								</s:elseif>
							  <s:elseif test="#theBean.DEPLOY_FLAG==3">
								  ����ж��
								</s:elseif>
							  <s:elseif test="#theBean.DEPLOY_FLAG==4">
								  ��ж��
								</s:elseif>
							  </span>
							  
							</td>
							
							<td>
							  <div id='divstartstop<s:property value="#theBean.ID"  />'>
								<s:if test="#theBean.START_STOP_FLAG==0">
								    ����ֹͣ
								</s:if>
								<s:elseif test="#theBean.START_STOP_FLAG==1">
								   ��ֹͣ
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==2">
								  ��������
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==3">
								   ������
								</s:elseif>
							 </div>
						
							</td>
							<td id='deployetime<bean:write name="theBean" property="ID"/>'><s:property value="#theBean.DEPLOYESTARTTIME"  />--<s:property value="#theBean.DEPLOYEENDTIME"  /></td>
							<%--<td><bean:write name="theBean" property="RELEASE_FLAG"/></td>--%>
							
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
