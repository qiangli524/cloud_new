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
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function resetForm(theForm){
		theForm.eq_id.value = "";
		theForm.eq_type.value = "";
		theForm.hasvertual.value = "";
		theForm.start_date.value = "";
		theForm.end_date.value = "";
	}
   function searchRequest() { 
		theForm.submit();
 	}
</script>
</head>
<body>
<html:form action="listHostHis" method="post" styleId="theForm">
 <bean:define id="theForm" name="hostManageForm"/>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">�������:</td>
                    <td>
						<html:text name="theForm" property="eq_id"  styleClass="txt" />
                    </td>
                    <td class="til">
						��������:
					</td>
					<td>
	                   <html:select property="eq_type">
	                   <html:option value="">��ѡ��</html:option>
	                   <html:option value="2">�ǿ���</html:option>
	                   <html:option value="1">����</html:option>
	                   </html:select>
					</td>
					<td class="til">
						�Ƿ�֧�����⻯:
					</td>
					<td>
	                   <html:select property="hasvertual">
		                   <html:option value="">��ѡ��</html:option>
		                   <html:option value="0">��֧��</html:option>
		                   <html:option value="1">֧��</html:option>
	                   </html:select>
					</td>
					<td class="til">����ʱ�俪ʼ:</td>
                    <td>
                    	<html:text name="theForm" property="start_date" size="10"
									readonly="true" styleClass="txt" styleId="datepicker1" />
					</td>
                    <td class="til">����ʱ���ֹ:</td>
                    <td>
                    	<html:text name="theForm" property="end_date" size="10"
									readonly="true" styleClass="txt" styleId="datepicker2" />
					</td>
                  </tr>
                  <tr>
                    <td colspan="10" class="btns">
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
			<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			       <th>���</th>
				   <th onclick="sort(theTable,1,'string')">���������</th>
				   <th onclick="sort(theTable,2,'string')">����������</th>   
				   <th onclick="sort(theTable,3,'string')">����������</th>              
                   <th onclick="sort(theTable,4,'string')">����������IP</th>
                   <th onclick="sort(theTable,5,'string')">������������</th>
                   <th onclick="sort(theTable,6,'string')">�������¶�</th>
                   <th onclick="sort(theTable,7,'string')">�Ƿ�֧�����⻯</th>
                   <th onclick="sort(theTable,8,'string')">���ڻ���</th>
                   <th onclick="sort(theTable,9,'date')">����ʱ��</th>
			  </tr>
			  </thead>
			  <tbody>
			   <logic:present name="theForm" property="resultList">
			      <logic:iterate id="theBean" name="theForm" property="resultList" indexId="idx">
						<tr>
							<td><%=++idx %></td>
							<td><bean:write name="theBean" property="eq_id"/></td>
							<td><bean:write name="theBean" property="eq_name"/></td>
							<td><bean:write name="theBean" property="eq_type"/></td>
							<td><bean:write name="theBean" property="eq_ip"/></td>
							<td><bean:write name="theBean" property="eq_hostname"/></td>
							<td><bean:write name="theBean" property="eq_temperature"/> ��</td>
							<td>
							<logic:equal name="theBean" property="hasvertual" value="0">��֧��</logic:equal>
							<logic:equal name="theBean" property="hasvertual" value="1">֧��</logic:equal>
							</td>
							<td>[<bean:write name="theBean" property="cq_id"/> - <bean:write name="theBean" property="cq_name"/>]</td>
							<td><bean:write name="theBean" property="ins_date"/></td>
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
