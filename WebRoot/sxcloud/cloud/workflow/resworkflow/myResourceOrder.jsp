<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
	
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
 	 function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
		}
	function StartAndStopDeployExample(ID,PARAM) {
 	        if(PARAM=='2'){
 	          if(confirm("确定要启动该主机吗！")==true)
		      {
		       $.getJSON("StartAndStopDeployExample.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		//         setStatus();
		       }); 
		      }
 	        }else{
 	          if(confirm("确定要停止该主机吗！")==true)
		      {
		       $.getJSON("StartAndStopDeployExample.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		  //       setStatus();
		       });
		      }
 	        }
 	}
	</script>
	</head>
	<body>
		<s:form action="resworkflow_myResourceOrder.do" method="post" id="theForm"> 
	<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
			 <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">工单编号:</td>
                    <td>
						<s:textfield name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"/>
                    </td>
                    <%--
                    <td class="til">要求发起时间:</td>
                    <td>
						<html:text name="theForm" property="START_DATE" size="10"
						readonly="true" styleClass="txt" styleId="datepicker2" />
                    </td>
                    --%>
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
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
	</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			    <%--
			  		<th>选择</th>
			  		--%>
				  	<th onclick="sort(theTable,0,'string')">工单编号</th>
				    <th onclick="sort(theTable,1,'string')">服务IP</th>
				    <th onclick="sort(theTable,2,'string')">应用</th>
				    <th onclick="sort(theTable,3,'string')">部署状态</th>
				    <th onclick="sort(theTable,4,'string')">服务器状态</th>
				    <th onclick="sort(theTable,5,'date')">时间</th> 
				    <th>操作</th> 
             </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList"  >
						<tr>
						<%--<td><input name="checkboxid" type="checkbox" value="<bean:write name="theBean" property="NEED_NUMBERS"/>"/></td>
						--%>
							<td><s:text name="#theBean.NEED_NUMBERS" /></td>
							<td><s:text name="#theBean.VLANIP" /></td>
							<td><s:text name="#theBean.APPNAME" /></td>
							<td>
								<span id="div<s:property value="#theBean.ID" />">
								<s:if test="#theBean.DEPLOY_FLAG==0">未部署</s:if>
								<s:if test="#theBean.DEPLOY_FLAG==1"><img src="/sxcloud/cloud/images/ajax-loader.gif" width="15" height="18"/>正在部署</s:if>
								<s:if test="#theBean.DEPLOY_FLAG==2">已部署</s:if>
								<s:if test="#theBean.DEPLOY_FLAG==3"><img src="/sxcloud/cloud/images/ajax-loader.gif" width="15" height="18"/>正在卸载</s:if>
								<s:if test="#theBean.DEPLOY_FLAG==4">已卸载</s:if>
							  	 </span>
							</td>
							<td>
								<div id="divstartstop<s:property value="#theBean.ID" />">
								<s:if test="#theBean.START_STOP_FLAG==0"><img src="/sxcloud/cloud/images/ajax-loader.gif" width="15" height="18"/>正在停止</s:if>
								<s:if test="#theBean.START_STOP_FLAG==1">已停止</s:if>
								<s:if test="#theBean.START_STOP_FLAG==2"><img src="/sxcloud/cloud/images/ajax-loader.gif" width="15" height="18"/>正在启动</s:if>
								<s:if test="#theBean.START_STOP_FLAG==3">已启动</s:if>
								  </div>
							</td>
							<td><s:text name="#theBean.DEPLOYETIME" /></td>
							<td>
							<div id="div_an<bean:write name="theBean" property="ID"/>">
								<s:if test="#theBean.START_STOP_FLAG==1">
								<input type="button" class="thickbox btn-style02"
										value="启动"
										onclick="StartAndStopDeployExample('<s:property value="#theBean.ID" />','2');return false;" />
								</s:if>
								<s:if test="#theBean.START_STOP_FLAG==3">
								<input type="button" class="thickbox btn-style02"
										value="停止"
										onclick="StartAndStopDeployExample('<s:property value="#theBean.ID" />','0');return false;" />
								</s:if>
								</div>
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