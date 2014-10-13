<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<head>
<title></title>
<style type="text/css">
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	function resetForm(theForm){
	    theForm.APPID.value= '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';		
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function selectAll()
	{
		var n=document.getElementsByName("checkboxid").length;
		var select=document.getElementsByName("checkboxid");
		if(select.length)
		{
			for(var i=0;i<n;i++)
			{
				if(select[i].value!="-1")
				{
					if(select[i].checked==true){
						select[i].checked=false;
					} else {
						select[i].checked=true;
					}    
			    } 
			}
		}
	}
	
	function hisVersion(app_id){
    	var dialog = $.dialog({
			id:'version',
			title:'历史版本',
			lock:true,
			content:'url:version_historyVersion.do?appId=' + app_id
		});
    }
	
 	</script>
</head>
<body>
<s:form action="version_listVersionResume" method="post" cssStyle="theForm" id="theForm">
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
                    <td class="til">状态:</td>
                    <td>
                     <s:select list="#{'':'-请选择-','0':'-未捕获-','1':'-正在捕获-','2':'-已捕获-'}" 
                     name="theForm.CATCH_STATUS" id="CATCH_STATUS"></s:select>
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
				<li><input type="button" class="thickbox btn-style02" value="恢复" onclick = "addRequest();return false;" /></li>
			</ul>
<%--			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />--%>
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input name="allcheckbox" type="checkbox" onclick="selectAll()" /></th>
				   <th onclick="sort(theTable,1,'string')">应用名称</th>
				   <th onclick="sort(theTable,2,'string')">部署策略</th>
				   <th onclick="sort(theTable,3,'string')">应用状态</th>
				   <th onclick="sort(theTable,4,'string')">部署主机用户</th>
				   <th onclick="sort(theTable,5,'string')">应用英文标识</th>
				   <th onclick="sort(theTable,6,'string')">应用编号</th>
				   <th onclick="sort(theTable,7,'string')">业务名称</th>
				   <th onclick="sort(theTable,8,'string')">最新上线路径</th>
				   <th>选择恢复版本</th>
				   <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			     <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						<td><input  name="checkboxid" type="checkbox" value="<bean:write name="theBean" property="ID"/></td>
						    <td><s:property value="#theBean.APPNAME"/></td>
							<td>
							<s:if test="#theBean.STRATEGYTYPE==1">
							录像部署</s:if>
							<s:elseif test="#theBean.STRATEGYTYPE==2">
							基准部署</s:elseif></td>
							<td>
							  <s:if test="#theBean.STATUS==1">
						      已注册</s:if>
							   <s:elseif test="#theBean.STATUS==2">正在部署</s:elseif>
							  <s:elseif test="#theBean.STATUS==3">已部署</s:elseif>
							  <s:elseif test="#theBean.STATUS==4">正在注销</s:elseif>
							  <s:elseif test="#theBean.STATUS==5">已注销</s:elseif>
							  <s:elseif test="#theBean.STATUS==6">正在升级</s:elseif>
							  <s:elseif test="#theBean.STATUS==7">已升级</s:elseif></td>
							<td><s:property value="#theBean.HOSEUSERNAME"/></td>
							<td><s:property value="#theBean.APP_IDENTIFY"/></td>
							<td><s:property value="#theBean.ID"/></td>
							<td><s:property value="#theBean.OPERATIONNAME"/></td>
							<td style="width: 160px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>'><s:property value="#theBean.DEPLOYPATH"/></a></td>
							<td>
								<a href="javascript:;" onclick="hisVersion('<s:property value="#theBean.ID"/>');">选择恢复版本</a>
							</td>
							<td>
									<input type="button" class="thickbox btn-style02"
										value="恢复"
										onclick="CatchImage('<s:property value="#theBean.ID"/>');return false;" />
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
