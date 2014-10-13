<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page
	import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.TaskInfoObj"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant"%>
<%@page
	import="com.sitech.basd.sxcloud.workflow.web.resworkflow.form.ResourceWorkflowForm"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			var count  = 3 ;
			
			//提交
			function submitForm(status){
				//对页面表单进行数据校验
				if(validateForm()){
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
 	    theForm.action = 'modVirtualServer.do' 
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
 	function agreeCommit(){ 
		var theForm = document.getElementById('theForm');
	var remark = theForm.INPUT_REMARK.value; 
	if(remark == null || "" == remark){
			alert('请输入您的审批意见！');
			return;
		}
	var processNode = theForm.processNode.value;
		theForm.action = 'resworkflow_agreeWaitDealResourceOrder.do?processNode='+processNode;  
		theForm.submit();
	}
	function fightbackCommit(){ 
	
		var theForm = document.getElementById('theForm');
		var remark = theForm.INPUT_REMARK.value; 
		if(remark == null || "" == remark){
			alert('请输入您的审批意见！');
			return;
		}
		var processNode = theForm.processNode.value;
		theForm.action = 'resworkflow_fightbackWaitDealResourceOrder.do?processNode='+processNode; 
		theForm.submit();
	}
	function openNewWindows(NEED_NUMBERS){
		
		var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
		var processNode = theForm.processNode.value;
		var taskId = theForm.taskId.value; 
		window.open("resworkflow_editResourceOrder.do?NEED_NUMBERS="+NEED_NUMBERS+"&&processNode="+processNode+"&&taskId="+taskId,"editResourceOrder",'width=800, height=600, resizable=yes');
 	}
 	function get(){
 			$("#div").append("<table id='table' width='100%' border='0' cellspacing='0'  </table>");
			$("#table").attr("class","pop-table nosize");
			$("#table").empty();
		$("#table").append("<tr id='jjj'> <td class='til' colspan='2' >模板信息</td></tr>");
 		if(theForm.VH_CPUVALUE.value !=null && theForm.VH_CPUVALUE.value!=''){
 			$("#table").append("<tr id='cpuid'> </tr>");	
 			$("#cpuid").append("<td id='cpu1'> </td>");
 			$("#cpu1").append("CPU期望值(个)") ;
 			$("#cpuid").append("<td id='cpu2'> </td>");
 			$("#cpu2").append(theForm.VH_CPUVALUE.value) ;
 			$("#cpu1").attr("class","til"); 
			$("#cpu2").attr("class","til");
		}
		if(theForm.VH_MIN_CPUVALUE.value !=null && theForm.VH_MIN_CPUVALUE.value!=''){
		
			$("#table").append("<tr id='cpu_min'> </tr>");	
 			$("#cpu_min").append("<td id='cpu_min1'> </td>");
 			$("#cpu_min1").append("CPU最小值(个)") ;
 			$("#cpu_min").append("<td id='cpu_min2'> </td>");
 			$("#cpu_min2").append(theForm.VH_MIN_CPUVALUE.value) ;
 			$("#cpu_min1").attr("class","til"); 
			$("#cpu_min2").attr("class","til");
		}
		if(theForm.VH_MAX_CPUVALUE.value != null && theForm.VH_MAX_CPUVALUE.value !=''){
			$("#table").append("<tr id='cpu_max'> </tr>");	
 			$("#cpu_max").append("<td id='cpu_max1'> </td>");
 			$("#cpu_max1").append("CPU最大值(个)") ;
 			$("#cpu_max").append("<td id='cpu_max2'> </td>");
 			$("#cpu_max2").append(theForm.VH_MAX_CPUVALUE.value) ;
 			$("#cpu_max1").attr("class","til"); 
			$("#cpu_max2").attr("class","til");
         }
         if(theForm.VH_MEMVALUE.value != null && theForm.VH_MEMVALUE.value !=''){
			$("#table").append("<tr id='mem'> </tr>");	
 			$("#mem").append("<td id='mem1'> </td>");
 			$("#mem1").append("内存期望值(MB)") ;
 			$("#mem").append("<td id='mem2'> </td>");
 			$("#mem2").append(theForm.VH_MEMVALUE.value) ;
 			$("#mem1").attr("class","til"); 
			$("#mem2").attr("class","til");
         }
         if(theForm.VH_MIN_MEMVALUE.value != null && theForm.VH_MIN_MEMVALUE.value !=''){
			$("#table").append("<tr id='mem_min'> </tr>");	
 			$("#mem_min").append("<td id='mem_min1'> </td>");
 			$("#mem_min1").append("内存最小值(MB)") ;
 			$("#mem_min").append("<td id='mem_min2'> </td>");
 			$("#mem_min2").append(theForm.VH_MIN_MEMVALUE.value) ;
 			$("#mem_min1").attr("class","til"); 
			$("#mem_min2").attr("class","til");
         }
         if(theForm.VH_MAX_MEMVALUE.value != null && theForm.VH_MAX_MEMVALUE.value !=''){
			$("#table").append("<tr id='mem_max'> </tr>");	
 			$("#mem_max").append("<td id='mem_max1'> </td>");
 			$("#mem_max1").append("内存最大值(MB)") ;
 			$("#mem_max").append("<td id='mem_max2'> </td>");
 			$("#mem_max2").append(theForm.VH_MAX_MEMVALUE.value) ;
 			$("#mem_max1").attr("class","til"); 
			$("#mem_max2").attr("class","til");
         }
         /*if(theForm.IMAGE_IDVALUE.value != null && theForm.IMAGE_IDVALUE.value !=''){
			$("#table").append("<tr id='image'> </tr>");	
 			$("#image").append("<td id='image1'> </td>");
 			$("#image1").append("虚拟映像ID") ;
 			$("#image").append("<td id='image2'> </td>");
 			$("#image2").append(theForm.IMAGE_IDVALUE.value) ;
 			$("#image").attr("class","til"); 
			$("#image2").attr("class","til");
         }*/
         if(theForm.VH_PROCESS_UNITVALUE.value != null && theForm.VH_PROCESS_UNITVALUE.value !=''){
			$("#table").append("<tr id='process'> </tr>");	
 			$("#process").append("<td id='process1'> </td>");
 			$("#process1").append("CPU处理单元(0.1单位)") ;
 			$("#process").append("<td id='process2'> </td>");
 			$("#process2").append(theForm.VH_PROCESS_UNITVALUE.value) ;
 			$("#process1").attr("class","til"); 
			$("#process2").attr("class","til");
         }
         if(theForm.VH_MIN_PROCESS_UNITVALUE.value != null && theForm.VH_MIN_PROCESS_UNITVALUE.value !=''){
			$("#table").append("<tr id='process_min'> </tr>");	
 			$("#process_min").append("<td id='process_min1'> </td>");
 			$("#process_min1").append("CPU处理单元最小值(0.1单位)") ;
 			$("#process_min").append("<td id='process_min2'> </td>");
 			$("#process_min2").append(theForm.VH_MIN_PROCESS_UNITVALUE.value) ;
 			$("#process_min1").attr("class","til"); 
			$("#process_min2").attr("class","til");
         }
         if(theForm.VH_MAX_PROCESS_UNITVALUE.value != null && theForm.VH_MAX_PROCESS_UNITVALUE.value !=''){
			$("#table").append("<tr id='process_max'> </tr>");	
 			$("#process_max").append("<td id='process_max1'> </td>");
 			$("#process_max1").append("CPU处理单元最大值(0.1单位)") ;
 			$("#process_max").append("<td id='process_max2'> </td>");
 			$("#process_max2").append(theForm.VH_MAX_PROCESS_UNITVALUE.value) ;
 			$("#process_max1").attr("class","til"); 
			$("#process_max2").attr("class","til");
         }
        if(theForm.VH_STORAGEVALUE.value != null && theForm.VH_STORAGEVALUE.value !=''){
			$("#table").append("<tr id='storage'> </tr>");	
 			$("#storage").append("<td id='storage1'> </td>");
 			$("#storage1").append("存储(MB)") ;
 			$("#storage").append("<td id='storage2'> </td>");
 			$("#storage2").append(theForm.VH_STORAGEVALUE.value) ;
 			$("#storage").attr("class","til"); 
			$("#storage2").attr("class","til");
         }
         
 	}
		</script>
</head>
<body>
	<s:form action="reworkflow_dealResourceDeploy.do" method="post" id="theForm">
		<s:hidden name="theForm.COMMAND"  id="COMMAND"/>
		<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"/>
		<s:hidden name="theForm.taskId" id="taskId"/>
		<s:hidden name="theForm.processNode" id="processNode" />
		<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"/>
		<s:hidden name="theForm.TYPE"  id="TYPE"/>
		<s:hidden name="theForm.VH_CPUVALUE" id="VH_CPUVALUE"/>
		<s:hidden name="theForm.VH_MIN_CPUVALUE" id="VH_MIN_CPUVALUE"/>
		<s:hidden name="theForm.VH_MAX_CPUVALUE" id="VH_MAX_CPUVALUE"/>
		<s:hidden name="theForm.VH_MEMVALUE" id="VH_MEMVALUE"/>
		<s:hidden name="theForm.VH_MAX_MEMVALUE" id="VH_MAX_MEMVALUE"/>
		<s:hidden name="theForm.VH_MIN_MEMVALUE" id="VH_MIN_MEMVALUE"/>
		<s:hidden name="theForm.VH_PROCESS_UNITVALUE" id="VH_PROCESS_UNITVALUE" />
		<s:hidden name="theForm.VH_MAX_PROCESS_UNITVALUE" id="VH_MAX_PROCESS_UNITVALUE"/>
		<s:hidden name="theForm.VH_MIN_PROCESS_UNITVALUE" id="VH_MIN_PROCESS_UNITVALUE"/>
		<s:hidden name="theForm.VH_STORAGEVALUE" id="VH_STORAGEVALUE"/>
		<div class="scrollbody">
			<div>
					<div  class="tit-zzi">
						<div id="zi">
							工单基本信息
						</div>
						<div id="zi"></div>
					</div>
					<div>
						<table width="100%" border="0" cellspacing="0"
							class="pop-table nosize">
							<tr>
								<td class="til">
									工单编号
								</td>
								<td>
									<s:text name="theForm.NEED_NUMBERS" />
								</td>
								<td class="til">
									所选资源类别名称
								</td>
								<td>
									<s:text name="theForm.TYPE_NAME"  />
								</td>
							</tr>
							<tr>
								<td class="til"
									style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
									具体内容
								</td>
								<td id="detail">
									<s:text name="theForm.content"  />
								</td>

								<td class="til">
									要求完成时间
								</td>
								<td>
									<s:text name="theForm.end_time"  />
								</td>
							</tr>
						</table>
					</div>
					
					<div>
					<div class="tit-zzi">
						<div id="zi">资源基本信息
						<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
						<input type="button" class="thickbox btn-style02-75" value="选择模板"
							onclick="javascript:openNewWindows('<s:property value="theForm.NEED_NUMBERS" />');" />
						</s:if>
						</div>
						<div id="zi"></div>
					</div>
						<s:elseif test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO">
						<div>
							<table width="100%" class="blue-table sorttable" border="0"
								cellspacing="0">
								
								<tbody>
								<s:if test="theForm.VH_CPUVALUE!=null ">
									<tr>
										<td>
										<s:text name="theForm.VH_CPU"></s:text>
										</td>
										<td>
										<s:text name="theForm.VH_CPUVALUE"></s:text>
										</td>
									</tr>
								</s:if>
									<s:if test="theForm.VH_MEMVALUE!=null" >
										<tr>
											<td>
												<s:text name="theForm.VH_MEM"  />
											</td>
											<td>
												<s:text name="theForm.VH_MEMVALUE"  />
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MAX_CPUVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.VH_MAX_CPU" />
											</td>
											<td>
												<s:text name="theForm.VH_MAX_CPUVALUE"  />
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.IMAGE_IDVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.IMAGE_ID" />
											</td>
											<td>
												<s:text name="theForm.IMAGE_IDVALUE" />
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MIN_CPUVALUE!=null" >
										<tr>
											<td>
												<s:text name="theForm.VH_MIN_CPU"  />
											</td>
											<td>
												<s:text name="theForm.VH_MIN_CPUVALUE"/>
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MAX_MEMVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.VH_MAX_MEM" />
											</td>
											<td>
												<s:text name="theForm.VH_MAX_MEMVALUE"/>
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MIN_MEMVALUE!=null" >
										<tr>
											<td>
												<s:text name="theForm.VH_MIN_MEM" />
											</td>
											<td>
												<s:text  name="theForm.VH_MIN_MEMVALUE" />
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_PROCESS_UNITVALUE!=null" >
										<tr>
											<td>
												<s:text name="theForm.VH_PROCESS_UNIT" />
											</td>
											<td>
												<s:text  name="theForm.VH_PROCESS_UNITVALUE" />
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MAX_PROCESS_UNITVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.VH_MAX_PROCESS_UNIT" />
											</td>
											<td>
												<s:text name="theForm.VH_MAX_PROCESS_UNITVALUE"/>
											</td>
										</tr>
									</s:if>
									<s:if test="theForm.VH_MIN_PROCESS_UNITVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.VH_MIN_PROCESS_UNIT" />
											</td>
											<td>
												<s:text name="theForm.VH_MIN_PROCESS_UNITVALUE"/>
											</td>
										</tr>
									</s:if>
									
									<s:if test="theForm.VH_STORAGEVALUE!=null">
										<tr>
											<td>
												<s:text name="theForm.VH_STORAGE" />
											</td>
											<td>
												<s:text name="theForm.VH_STORAGEVALUE"/>
											</td>
										</tr>
									</s:if>
									
								</tbody>
							</table>
						
						</div>
						</s:elseif>
					<div>	
			<div id="div">
			</div>
		</div>
					<div>
						<div class="tit-zzi">
							<div id="zi">
								流程基本信息</div>
							<div id="zi"></div>
						</div>
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										流水号
									</th>
									<th>
										工单处理人
									</th>
									<th>
										接受时间
									</th>
									<th>
										处理时间
									</th>
								    <th>
										审批意见
									</th>
									
									
								</tr>
							</thead>
							<tbody>
								<%
									int m = 0;
								%>
									<s:iterator id="theBean" value="theForm.taskInfoResultList">
										<tr>
											<td>
												<s:text name="#theBean.TASK_ID" />
											</td>
											<td>
												<s:text name="#theBean.DISPOSE_MAN" />
												<%
													if (m == 0) {
															out.print("(发起人)");
														}
												%>
											</td>
											<td>
												<s:text name="#theBean.RECEIVE_TIME" />
											</td>
											<td>
												<s:text name="#theBean.DISPOSE_TIME" />
											</td>
										
											<s:if test="#theBean.REMARK!=null">
												<td>
												<s:text name="#theBean.REMARK"  />
												</td>
											</s:if>
								
									<s:else>
									<td>
										无
									</td>
									</s:else>
										</tr>
										<%
											m++;
										%>
									</s:iterator>
							</tbody>
						</table>
						
					<s:if test="theForm.FLOW_TYPE!=@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO && 
			theForm.FLOW_TYPE !=@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_HUNGLIST">
						
							<div class="tit-zzi">
								<div><s:text name="theForm.NEED_REMARK"/></div>
								<div id="zi"></div>
							</div>
							<div class="pop-table">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<th class="til">
											<s:text name="theForm.NEED_REMARK"/>：
										</th>
										<td colspan="3">
											<s:textarea name="theForm" name="theForm.INPUT_REMARK"
												cols="77" rows="5" id="INPUT_REMARK"/>
										</td>
									</tr>
								</table>
							</div>
							<!-- itemContent end-->
						</div>
						<!-- itemContent end-->
					</s:if>


						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="4" class="btnCenter">
									<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
									<s:if test="theForm.TASK_OPERATE.contains(@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_OPERATE_APPROVAL)" >
									<input type="button" class="thickbox btn-style02" value="同意"
										onclick="agreeCommit();" />
									</s:if>
						   			 <s:if test="theForm.TASK_OPERATE.contains(@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_OPERATE_PLAYEDBACK)">
									<input type="button" class="thickbox btn-style02" value="打回"
										onclick="fightbackCommit();" />
										</s:if>
						   			 </s:if>
									
									<input type="button" class="thickbox btn-style02" value="返回"
										onclick="history.go(-1)" />
										
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
	</s:form>
</body>
</html:html>