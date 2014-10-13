<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
<style type="text/css">
/*恢复版本*/
.stepStatusList { color:#333; text-align:center; height:40px; line-height:37px; padding:10px 0; border-bottom:1px solid #f1f1f1;}
.stepStatusList li { float:left; width:155px; height:37px; margin-left:-12px; background:url(<%=request.getContextPath() %>/sxcloud/images/version/process.gif) no-repeat 0 -76px;}
.stepStatusList .frist { color:#fff; margin-left:0; width:140px; background-position:0 0;}
.stepStatusList .pass { color:#fff; background-position:0 -36px;}
.stepList { padding:0 5px;}
.stepList h3 { text-indent:1em; line-height:3em; font-size:12px; font-weight:bold;}
.stepList .radio, .stepList .checkbox { vertical-align:middle;}
.stepList .step { display:;}
.stepList .step1 .stepRightBlock { background:url(<%=request.getContextPath() %>/sxcloud/images/application/hostBG.jpg) no-repeat 0 0;}
.stepList .step1 .stepRightTitle { border:none; color:#fff;}
.stepList .step1 .stepLeftList {}
.stepList .step3 .stepRightBlock { height:300px;}
.stepList .step4 .stepLeftList { width:auto; height:auto; border-bottom:0; margin:0;}
.stepList .btnLine { margin-bottom:20px;}
.stepRightBlock { float:right; width:310px; height:340px; padding:0 20px; background-color:#f5f5f5;}
.stepRightTitle { color:#000; margin-bottom:1em; font-family:Tahoma; line-height:2em; border-bottom:1px solid #e1e1e1;}
.stepRightTitle li { display:none;}
.stepRightForm {}
.stepRightForm li { display:none;}
.stepRightForm td { padding-top:3px;}
.stepRightForm select { width:100px; font-size:12px;}
.stepRightForm textarea { width:160px;}
.stepLeftList { width:380px; height:300px; line-height:36px; margin-bottom:10px; overflow:hidden; overflow-y:auto; border:1px solid #f1f1f1; border-width:1px 0;}
.stepLeftList dt { cursor:pointer; border-bottom:1px solid #e6e6e6; padding-left:30px; background:url(../images/icon/nodes_add.gif) no-repeat 10px 50%;}
.stepLeftList dt.extend { background-image:url(../images/icon/nodes_sub.gif);}
.stepLeftList dd { border-bottom:1px dotted #e6e6e6; padding-left:60px; cursor:pointer;}
.stepLeftList dd:hover, .stepLeftList dd.current { background-color:#fef0a5;}
.stepHostInf dt { font-weight:bold;}
.stepHostInf dd { line-height:2em; cursor:default;}
</style>
<script type="text/javascript">
	function resetForm(theForm){
	    theForm.APPID.value= '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';		
	}

   function searchRequest() { 
		var count = 0;
		var versionids = document.getElementsByName("radioid");
		if (versionids != null) {
			var i;
			for (i = 0; i < versionids.length; i++) {
				if (versionids[i].checked) {
					count = count + 1;
					theForm.APPID.value = versionids[i].value;
				}
			}
		}
		if (count < 1) {
			alert("请勾选需要恢复的版本！");
			return false;
		}
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
<body class="scrollbody">
<s:form action="rollback_getSecondVersion" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
<div class="scrollbody" >


    <ul class="stepStatusList">
    <li class="frist">选择业务应用</li>
    <li>选择恢复版本</li>
    <li>选择恢复文件</li>
	<li>选择上线主机</li>
	<li>完成</li>
    </ul>
	<%--	<div class="query">--%>
	<%--		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>--%>
	<%--	</div>--%>
	<div class="box on">
<%--        <div class="query-form">--%>
<%--                <table width="100%" class="querytable" border="0">--%>
<%--                  <tr>--%>
<%--                    <td class="til">所属应用:</td>--%>
<%--                    <td>--%>
<%--				             <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">--%>
<%--						</s:select> --%>
<%--                    </td>--%>
<%--                    --%>
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
		    
<%--			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />--%>
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择应用</th>
				   <th>应用名称</th>
<%--				   <th>部署策略</th>--%>
<%--				   <th>应用状态</th>--%>
<%--				   <th>部署主机用户</th>--%>
<%--				   <th>应用英文标识</th>--%>
<%--				   <th>应用编号</th>--%>
<%--				   <th>业务名称</th>--%>
<%--				   <th>最新上线路径</th>--%>
			  </tr>
			  </thead>
			  <tbody>
			     <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						<td><input id="radioid" class="radio" name="radioid"
											type="radio"
											value='<s:property value="#theBean.ID"/>'>
										</td>
						    <td><s:property value="#theBean.APPNAME"/></td>
<%--							<td>--%>
<%--							<s:if test="#theBean.STRATEGYTYPE==1">--%>
<%--							录像部署</s:if>--%>
<%--							<s:elseif test="#theBean.STRATEGYTYPE==2">--%>
<%--							基准部署</s:elseif></td>--%>
<%--							<td>--%>
<%--							  <s:if test="#theBean.STATUS==1">--%>
<%--						      已注册</s:if>--%>
<%--							   <s:elseif test="#theBean.STATUS==2">正在部署</s:elseif>--%>
<%--							  <s:elseif test="#theBean.STATUS==3">已部署</s:elseif>--%>
<%--							  <s:elseif test="#theBean.STATUS==4">正在注销</s:elseif>--%>
<%--							  <s:elseif test="#theBean.STATUS==5">已注销</s:elseif>--%>
<%--							  <s:elseif test="#theBean.STATUS==6">正在升级</s:elseif>--%>
<%--							  <s:elseif test="#theBean.STATUS==7">已升级</s:elseif></td>--%>
<%--							<td><s:property value="#theBean.HOSEUSERNAME"/></td>--%>
<%--							<td><s:property value="#theBean.APP_IDENTIFY"/></td>--%>
<%--							<td><s:property value="#theBean.ID"/></td>--%>
<%--							<td><s:property value="#theBean.OPERATIONNAME"/></td>--%>
<%--							<td style="width: 160px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>'><s:property value="#theBean.DEPLOYPATH"/></a></td>--%>
<%--							--%>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
			
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="btn-style02" value="下一步"
							onclick="javascript:searchRequest();" />
					</td>
				</tr>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>


</body>
