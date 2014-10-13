<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant" %>
<%@ page import="com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm" %>
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
		  /*	if(theForm.FLOW_TYPE.value="FLOW_TYPE_ALREADYDO"){
		  		theForm.action = 'alreadyDealApp.do';
		  		theForm.submit();
		  	}
		  	
		  	if(theForm.FLOW_TYPE.value="FLOW_TYPE_PLAYEDBACK"){
		  		theForm.action = 'rejectAppDeploy.do';
		  		theForm.submit();
		  	}*/
		  	var flowType = theForm.FLOW_TYPE.value;
			   theForm.submit();
			
 		}
 		function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
			theForm.NEED_SPONSOR.value = '';
		}
 	//显示详细信息
		   function showDetials(needNum,taskId,processNode){ 
				var theForm = document.getElementById('theForm');
		   		theForm.action = 'appworkflow_dealAppDeploy.do?needNum=' +needNum+'&taskId='+taskId+'&processNode='+processNode; 
		   		theForm.submit();
		   }
//显示被打回工单的详细信息 
		   function showRejectDetials(needNum,needStatus,taskId){  
		   		var rejectOrderFlag = "RejectOrder"
				var theForm = document.getElementById('theForm');
			//	alert(taskId);
		   		theForm.action = 'appworkflow_alterAppOrder.do?NEED_NUMBERS=' +needNum+'&needStatus='+needStatus+'&rejectOrderFlag='+rejectOrderFlag+'&taskId='+taskId;
		   		theForm.submit();
		   }
		   //删除被打回的工单
		 function delRequest() {
 			var couterNum = 0;
 	 	  	var checkboxids = document.getElementsByName("checkboxid");
 	  	  	if(checkboxids!=null&&checkboxids.length>0){
 	    	for(var i=0;i<checkboxids.length;i++){
 	 	    	if(checkboxids[i].checked){
 	      			couterNum = couterNum + 1 ;
 	      			theForm.NEED_NUMBERS.value = checkboxids[i].value;
 	      		}
 	    	}
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要更改的应用部署申请！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能更改单条应用部署申请");
 	    return false ;
 	    }
 	    theForm.action = 'deleteAppOrder.do'  
		theForm.submit();
 	}
		</script>
	</head>
	<body>
	<s:form action="appworkflow_listDealOrder.do" method="post" id="theForm" cssClass="theForm">
	<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
	<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
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
						<s:textfield name="theForm.NEED_NUMBERS" cssClass="txt" id="NEED_NUMBERS"></s:textfield>
                    </td>
                   <td class="til">工单发起人:</td>
                    <td>
						<s:textfield name="theForm.NEED_SPONSOR" cssClass="txt" id="NEED_SPONSOR"></s:textfield>  
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
			<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
			<img src="sxcloud/cjs/ui2/nresources/common/images/workflowy2.png" height="30" />
					</s:if>
			<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO">
				<img src="sxcloud/cjs/ui2/nresources/common/images/workflowy4.png"  height="30"/>
			</s:if>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
	</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">工单编号</th>
				   <th onclick="sort(theTable,1,'string')">工单发起人</th>
				   <th onclick="sort(theTable,2,'date')">发起时间</th>
				   <th onclick="sort(theTable,3,'date')">要求结束时间</th>
				   <th onclick="sort(theTable,4,'string')">工单状态</th>
				   <th>详细信息</th>
             </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td> <s:property value="#theBean.NEED_NUMBERS"/> </td>
			  		<td> <s:property value="#theBean.NEED_SPONSOR"/> </td>
			  		<td> <s:property value="#theBean.START_DATE"/> </td>
			  		<td> <s:property value="#theBean.END_DATE"/> </td>
			  		<td>
			  			<s:if test="#theBean.NEED_STATUS==1">正在处理</s:if>
			  			<s:else>处理完成</s:else>
			  		</td>
			  		<td>
						<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_PLAYEDBACK ">
							<a href="javascript:showRejectDetials('<s:property value="#theBean.NEED_NUMBERS"/>','<s:property value="#theBean.NEED_STATUS"/>','<s:property value="#theBean.taskId"/>')">	详细信息	</a>
						</s:if>
						<s:else>
							<a href="javascript:showDetials('<s:property value="#theBean.NEED_NUMBERS"/>','<s:property value="#theBean.taskId"/>','<s:property value="#theBean.processNode"/>')">	详细信息	</a>
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
