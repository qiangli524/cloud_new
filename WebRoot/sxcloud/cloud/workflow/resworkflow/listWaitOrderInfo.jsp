<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp" %>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">

<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			var count  = 3 ;
			function validateForm(){
				document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
				//异步方式判断 需求编码是否唯一
				var NEED_NUMBERS = document.getElementById("theForm").NEED_NUMBERS.value;
				if(NEED_NUMBERS == null || "" == NEED_NUMBERS){
					document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "工单编号不能为空";
					return false;
				}
				var url = "OrderUniqueJudgement.do?NEED_NUMBERS="+NEED_NUMBERS+"&Date"+(new Date());
				 $.getJSON(url,function(data){
				 	if("NO" == data ){
				 		//alert(data); 
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS+"已经存在，请更改工单编号!";
				 		return false;
				 	}
				 })
				//对表单其他数据进行判断
				return true;
			}
			function searchRequest(){
			var flowType = theForm.FLOW_TYPE.value;
			theForm.action="resworkflow_listDealResourceOrder.do";
			   theForm.submit();
			}
			function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
			theForm.datepicker1.value = '';
		}
			
			//提交
			function submitForm(status){
				
				//对页面表单进行数据校验
				if(validateForm()){
				
					//alert(status);
					document.getElementById("theForm").COMMAND.value = status;
					
					document.getElementById("theForm").submit();
				}
				
			}
			function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改的虚拟服务器！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条虚拟服务器信息");
 	    return false ;
 	    }
 	    theForm.action = 'resworkflow_modVirtualServer.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除的虚拟服务器！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条虚拟服务器信息");
 	    return false ;
 	    }
 	    theForm.action = 'delVirtualServer.do'  
		theForm.submit();
 	}
 	//显示详细信息
		   function showDetials(needNum,taskId,processNode){ 
				var theForm = document.getElementById('theForm');
		   		theForm.action = 'resworkflow_dealResourceDeploy.do?needNum=' +needNum+'&taskId='+taskId+'&processNode='+processNode; 
		   		theForm.submit();
		   }
//显示被打回工单的详细信息 
		   function showRejectDetials(needNum,needStatus,taskId){  
		   		var rejectOrderFlag = "RejectOrder"
				var theForm = document.getElementById('theForm');
			//	alert(taskId);
		   		theForm.action = 'resworkflow_alterOrderInfo.do?NEED_NUMBERS=' +needNum+'&needStatus='+needStatus+'&rejectOrderFlag='+rejectOrderFlag+'&taskId='+taskId;
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
 	    alert("请勾选需要更改的需求工单！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能更改单条需求工单");
 	    return false ;
 	    }
 	    theForm.action = 'deleteAppOrder.do'  
		theForm.submit();
 	}
		</script>
</head>
<body>
	<s:form action="resworkflow_listDealResourceOrder.do" method="post" cssClass="theForm"
		id="theForm">
		<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE" />
		<s:hidden name="theForm.COMMAND" />
		<s:hidden name="theForm.NEED_REMARK" />

		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								工单编号:
							</td>
							<td>
								<s:textfield name="theForm.NEED_NUMBERS" id="NEED_NUMBERS" />
							</td>
							<td class="til">
								要求完成时间:
								<s:textfield name="theForm.end_time" id="datepicker1" />
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head">
					
					<ul class="btns">
					<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
							<img src="sxcloud/cjs/ui2/nresources/common/images/workflowy2.png" height="30"/>
						</s:if>
						<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO">
							<img src="sxcloud/cjs/ui2/nresources/common/images/workflowy4.png" height="30"/>
						</s:if>
					</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm&FUNCSID=123" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">
										工单编号
									</th>
									<th onclick="sort(theTable,1,'string')">
										工单发起人
									</th>
									<th onclick="sort(theTable,2,'date')">
										要求完成时间
									</th>
									<th onclick="sort(theTable,3,'string')">
										工单状态
									</th>
									<th>
										详细信息
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator id="theBean" value="theForm.needResultList">
									<tr>

										<td>
											<s:text name="#theBean.NEED_NUMBERS" />
										</td>
										<td>
											<s:text name="#theBean.NEED_SPONSOR" />
										</td>
										<td>
											<s:text name="#theBean.NEED_END" />
										</td>
										<td>
											<s:if test="#theBean.NEED_STATUS==1">正在处理</s:if>
											<s:if test="#theBean.NEED_STATUS==2">处理完成</s:if>
										</td>
										<td>
											<s:if
												test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_PLAYEDBACK ">
												<a
													href="javascript:showRejectDetials('<s:property value="#theBean.NEED_NUMBERS"/>',<s:property value="#theBean.NEED_STATUS"/>,<s:property value="#theBean.taskId" />)">
													详细信息 </a>
											</s:if>
											<s:else>
												<a
													href="javascript:showDetials('<s:property value="#theBean.NEED_NUMBERS" />',<s:property value="#theBean.taskId" />,<s:property value="#theBean.processNode" />)">
													详细信息 </a>
											</s:else>
										</td>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>

</body>
</html:html>