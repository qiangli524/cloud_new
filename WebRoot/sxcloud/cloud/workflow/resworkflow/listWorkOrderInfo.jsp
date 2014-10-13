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
			theForm.action="resworkflow_listWorkOrderInfo.do";
			   theForm.submit();
			}
			function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
			theForm.datepicker1.value = '';
		}
		
		   function showDetials(needNum,taskId,processNode){ 
				var theForm = document.getElementById('theForm');
		   		theForm.action = 'resworkflow_dealWorkOrderResourceDeploy.do?needNum=' +needNum+'&taskId='+taskId+'&processNode='+processNode; 
		   		theForm.submit();
		   }
		</script>
</head>
<body>
	<s:form action="resworkflow_listDealResourceWorkOrder.do" method="post" cssClass="theForm"
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
								申请类型:
								<select id="applyType" name="theForm.APPLY_Type">
								  <option value="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
								  <option value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新增&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
								  <option value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
								  <option value="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="submit" class="thickbox btn-style02" value="查询"/>
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">
										工单编号
									</th>
									<th onclick="sort(theTable,1,'string')">
										申请类型
									</th>
									<th onclick="sort(theTable,2,'string')">
										详细描述
									</th>
									<th onclick="sort(theTable,3,'date')">
									           申请时间
									</th>
									<th onclick="sort(theTable,4,'string')">
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
											<s:property value="#theBean.NEED_NUMBERS" default=""/>
										</td>
										<td>
										    <s:if test="#theBean.APPLY_TYPE==0">
										                                    新增
										    </s:if>
										    <s:elseif test="#theBean.APPLY_TYPE==1">
										                                    修改
										    </s:elseif>
										    <s:elseif test="#theBean.APPLY_TYPE==2">
										                                      删除
										    </s:elseif>
										    <s:else>
										                                      无
										    </s:else>
										</td>
										<td>
											<s:property value="#theBean.content" default="无"/>
										</td>
										<td>
										    <s:property value="#theBean.NEED_START" default="未知"/>
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