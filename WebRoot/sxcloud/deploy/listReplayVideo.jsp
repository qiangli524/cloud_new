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
		theForm.HOSTIP.value = '';
		theForm.HOSTUSERNAME.value = '';
	}

   function searchRequest() {
   		var hostip=document.theForm.HOSTIP.value;
   		var hostusername=document.theForm.HOSTUSERNAME.value;
   		if(!isnumber(hostip)){
   			alert("����IP���벻�Ϸ�,ֻ��Ϊ.������!");
   			return false;
   		}
   		if(checkusername(hostusername)){
   			alert("�����û������벻�Ϸ���ֻ��Ϊ�ַ�");
   			document.theForm.HOSTUSERNAME.value='';
	     	document.theForm.HOSTUSERNAME.focus;
   			return false;
   		}
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'addVideoExample.do'; 
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
 	    alert("�빴ѡ��Ҫ�޸ĵĲ���ط���Ϣ��");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ�ܴ���������ط���Ϣ��");
 	    return false ;
 	    }
 	    theForm.action = 'modVideoExample.do';
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
 	    alert("�빴ѡ��Ҫɾ���Ĳ���ط���Ϣ��");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ��ɾ����������ط���Ϣ");
 	    return false ;
 	    }
 	    theForm.action = 'delVideoExample.do';  
		theForm.submit();
 	}
 	/* ���������ַ����Ƿ����Ҫ�� */
 	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	        {
	            if (number_chars.indexOf(str.charAt(i))==-1){
	            	
	            	document.theForm.HOSTIP.value='';
	            	document.theForm.HOSTIP.focus;
	            	return false;
	            }
	        }
	        return true;
	 }
	 
	  function checkusername(username){
  		var pattern=/^([a-zA-Z0-9]|[\uFE30-\uFFA0|V:])*$/gi;
    	if(pattern.test(username)) {
	     return false;
    	}
    	 return true;
 	  }
 	  
	 
</script>
</head>
<body>
<html:form action="listReplayVideo" method="post" styleId="theForm">
<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
 <bean:define id="theForm" name="VideoExampleForm"/>
 <html:hidden name="theForm" property="ID" />
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">����IP:</td>
                    <td>
                        <html:text name="theForm" property="HOSTIP"  styleClass="txt" />
                    </td>
                    <td class="til">�����û���:</td>
                    <td>
                        <html:text name="theForm" property="HOSTUSERNAME"  styleClass="txt"/>
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
			<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
		</div>
		<input type="hidden" id="result" value="<bean:write name="theForm" property="result"/>"/>	
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>���</th>
				   <th onclick="sort(theTable,1,'string')">¼������</th>
				   <th onclick="sort(theTable,2,'string')">¼����</th>
				   <th onclick="sort(theTable,3,'date')">����¼��ʱ��</th>
                   <th onclick="sort(theTable,4,'string')">����IP</th>
                   <th onclick="sort(theTable,5,'string')">�����û���</th>
                   <th>����</th>
			  </tr>
			  </thead>
			  <tbody>
			   <logic:present name="theForm" property="resultList">
			      <logic:iterate id="theBean" name="theForm" property="resultList" indexId="status">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<bean:write name="theBean" property="ID"/>"/></td>
							<td><bean:write name="theBean" property="VIDEONAME"/></td>
							<td><bean:write name="theBean" property="CREATEUSER"/></td>
							<td><bean:write name="theBean" property="CREATETIME"/></td>
							<td><bean:write name="theBean" property="HOSTIP"/></td>
							<td><bean:write name="theBean" property="HOSTUSERNAME"/></td>
							<td><input type="button" class="thickbox btn-style02"  value="�ط�" onclick ="location='reploy_process.do?videoid=<bean:write name="theBean" property="ID"/>'" /></td>
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
