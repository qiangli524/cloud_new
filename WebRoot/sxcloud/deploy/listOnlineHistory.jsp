<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<style type="text/css">
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
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
<s:form action="onlinehis_listOnlineHistory" method="post" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>

<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
<%--        <div class="query-form">--%>
<%--                <table width="100%" class="querytable" border="0">--%>
<%--                  <tr>--%>
<%--                    <td class="til">所属应用:</td>--%>
<%--				    <td>--%>
<%--				        <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">--%>
<%--						</s:select> --%>
<%--				    </td>--%>
<%--                    <td class="til">主机IP:</td>--%>
<%--                    <td>--%>
<%--                        <s:textfield name="theForm.IP" id="IP" cssClass="txt"></s:textfield>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                  <tr>--%>
<%--                    <td colspan="8" class="btns">--%>
<%--                        <div>--%>
<%--							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />--%>
<%--							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                  </tr>--%>
<%--                </table>--%>
<%--        </div><!--query-form end -->--%>
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
<%--				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="部署" onclick = "deployRequestAll();return false;" /></li>--%>
<%--				<li><input type="button" class="thickbox btn-style02" value="卸载" onclick = "uninstallRequestAll();return false;" /></li>--%>
			</ul>
			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">IP</th>
<%--				   <th>服务IP</th>--%>
				   <th onclick="sort(theTable,1,'string')">应用</th>
				   <th onclick="sort(theTable,2,'string')">上线路径</th>
				   <th>操作</th>
                   <th onclick="sort(theTable,3,'date')">部署时间</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.onlineHisList" id="theBean">
						<tr>
							<td><s:property value="#theBean.ip"  /></td>
							<td><s:property value="#theBean.appname"  /></td>
							<td style="width: 150px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.onlinePath"  />'><s:property value="#theBean.onlinePath"  /></a></td>
							<td>
								<s:if test="#theBean.isRollback==0">上线</s:if>
								<s:elseif test="#theBean.isRollback==1">回滚</s:elseif>						
							</td>
						<td><s:property value="#theBean.insertTime"  />
						</td>
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
