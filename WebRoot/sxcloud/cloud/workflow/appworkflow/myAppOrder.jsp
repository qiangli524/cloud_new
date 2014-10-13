<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
	
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
 	 function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
		}
		
	function showMyOrder(needNum,taskId,processNode){
		var theForm = document.getElementById('theForm');
		theForm.action = 'dealAppDeploy.do?needNum=' +needNum+'&taskId='+taskId+'&processNode='+processNode; 
		theForm.submit();
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
	<s:form action="appworkflow_myAppOrder" method="post" id="theForm">
	<s:hidden  name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
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
						<s:textfield name="theForm.NEED_NUMBERS" id="NEED_NUMBERS" cssClass="txt"></s:textfield>
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
						<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
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
				    <th onclick="sort(theTable,5,'string')">时间</th> 
				    <th>操作</th> 
             </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td>
			  			<s:if test="#theBean.DEPLOY_FLAG==1">
			  				<s:property value="#theBean.NEED_NUMBER"/>
			  			</s:if>
			  			<s:else>
			  			<a href="javascript:showMyOrder('<s:property value="#theBean.NEED_NUMBER"/>',<s:property value="#theBean.taskId"/>,<s:property value="#theBean.processNode"/>)">	<s:property value="#theBean.NEED_NUMBER"/>	</a>
			  			</s:else>
			  		</td>
			  		<td>
			  			<s:property value="#theBean.VLANIP"/>
			  		</td>
			  		<td>
			  			<s:property value="#theBean.APPNAME"/>
			  		</td>
			  		<td>
			  			<span id="div<s:property value="#theBean.ID"/>">
			  				<s:if test="#theBean.DEPLOY_FLAG==0">未部署</s:if>
			  				<s:elseif test="#theBean.DEPLOY_FLAG==1"><img src="./images/ajax-loader.gif" width="15" height="18"/>正在部署</s:elseif>
			  				<s:elseif test="#theBean.DEPLOY_FLAG==2">已部署</s:elseif>
			  				<s:elseif test="#theBean.DEPLOY_FLAG==3"><img src="./images/ajax-loader.gif" width="15" height="18"/>正在卸载</s:elseif>
			  				<s:elseif test="#theBean.DEPLOY_FLAG==4">已卸载</s:elseif>
			  			</span>
			  		</td>
			  		<td>
			  			<span id="div<s:property value="#theBean.ID"/>">
			  				<s:if test="#theBean.START_STOP_FLAG==0"><img src="./images/ajax-loader.gif" width="15" height="18"/>正在停止</s:if>
			  				<s:elseif test="#theBean.START_STOP_FLAG==1">已停止</s:elseif>
			  				<s:elseif test="#theBean.START_STOP_FLAG==2"><img src="./images/ajax-loader.gif" width="15" height="18"/>正在启动</s:elseif>
			  				<s:elseif test="#theBean.START_STOP_FLAG==3">已启动</s:elseif>
			  			</span>
			  		</td>
			  		<td><s:property value="#theBean.DEPLOYETIME"/></td>
			  		<td>
			  			<div id="div_an<s:property value="#theBean.ID"/>">
			  			<s:if test="#theBean.START_STOP_FLAG==1">
			  				<input type="button" class="thickbox btn-style02"
										value="启动"
										onclick="StartAndStopDeployExample('<s:property value="#theBean.ID"/>','2');return false;" />
			  			</s:if>
			  			<s:elseif test="#theBean.START_STOP_FLAG==3">
			  				<input type="button" class="thickbox btn-style02"
										value="停止"
										onclick="StartAndStopDeployExample('<s:property value="#theBean.ID"/>','0');return false;" />
			  			</s:elseif>
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
