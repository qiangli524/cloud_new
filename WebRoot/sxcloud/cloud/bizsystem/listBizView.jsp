<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<head>
<title></title>
<script type="text/javascript">
	function resetForm(theForm){
	    theForm.APPID.value= '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';
		theForm.BIZID.value = '';				
	}
   function searchRequest() { 
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="dep_listBizDeployExample.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">所属应用:</td>
				    <td>
				        <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">
						</s:select>    
				    </td>
				     <td class="til">所属业务系统:</td>
                    <td>
                        <s:select list="theForm.bizList" listKey="SYS_ID" name="theForm.BIZID" id="BIZID" listValue="SYS_NAME" headerKey="" headerValue="-请选择-">
						</s:select> 
                    </td>
                    <td class="til">部署状态:</td>
                    <td>
                        <s:select list="#{'':'请选择','0':'-未部署-','1':'-正在部署-','2':'-已部署-'}" name="theForm.DEPLOY_FLAG" id="DEPLOY_FLAG"></s:select>
<%--                        ,'3':'-正在卸载-','4':'-已卸载-'--%>
                    </td>
                    <td class="til">启用状态:</td>
                    <td>
                         <s:select list="#{'':'-请选择-','1':'-已停止-','2':'-正在启动-','3':'-已启动-'}" name="theForm.START_STOP_FLAG" id="START_STOP_FLAG"></s:select>
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
			<jsp:include page="../../inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">部署机IP</th>
				   <th onclick="sort(theTable,1,'string')">业务系统</th>
				   <th onclick="sort(theTable,2,'string')">应用</th>
				   <th onclick="sort(theTable,3,'string')">上线路径</th>
				   <th onclick="sort(theTable,4,'string')">部署状态</th>
                   <th onclick="sort(theTable,5,'string')">服务器状态</th>
                   <th onclick="sort(theTable,6,'date')">部署时间</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						    <td> <s:property value="#theBean.IP"/></td>
						    <td><s:property value="#theBean.SYS_NAME"/></td>
							<td><s:property value="#theBean.APPNAME"/></td>
							<td><s:property value="#theBean.DEPLOYPATH"/></td>
							<td>
							  <span id="div<s:text name="#theBean.ID"/>">
							    <s:if test="#theBean.DEPLOY_FLAG==0">
								    未部署
								</s:if>
								<s:elseif test="#theBean.DEPLOY_FLAG==1">
								  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在部署
								</s:elseif>
								<s:elseif test="#theBean.DEPLOY_FLAG==2">
								   已部署
								</s:elseif>
							  </span>
							</td>
							<td>
							  <div id="divstartstop<s:text name="#theBean.ID"/>">
							     <s:if test="#theBean.START_STOP_FLAG==0">
								    正在停止
								</s:if>
								<s:elseif test="#theBean.START_STOP_FLAG==1">
								   已停止
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==2">
								  正在启动
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==3">
								   已启动
								</s:elseif>
							 </div>
							</td>
							<td >
								<s:if test="#theBean.DEPLOYEENDTIME!=null">
									<s:property value="#theBean.DEPLOYEENDTIME"/>
								</s:if>
								<s:else>
									未部署
								</s:else>
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
