<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow.TaskInfoObj" %>
<%@ page import="com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant" %>
<%@ page import="com.sitech.basd.sxcloud.workflow.web.appworkflow.form.AppDeployWorkFlowForm" %>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
	<head>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			

 	function agreeCommit(){   
		var theForm = document.getElementById('theForm');
		var remark = theForm.INPUT_REMARK.value; 
		var app = theForm.APP_IDVALUE.value;
	//	var defendflag = theForm.DEFEND_FLAG;
		var defendflag = document.getElementById("DEFEND_FLAG0");
		var defend = theForm.DEFEND_DIR.value;
	//	for(var i=0;i<defendflag.length;i++){
			if(defendflag.checked){
				if(defend ==null || "" ==defend){
					alert("����ӷ��۸�");
					return;
				}
			}
	//	}
		if(app ==null || "" ==app){
			alert("��ѡ��������");
			return ;
		}
		if(remark == null || "" == remark){
			alert('�������������������');
			return;
		}
		theForm.action = 'appworkflow_agreeWaitDealOrder.do';  
		theForm.submit();
	}
	function fightbackCommit(){   
		var theForm = document.getElementById('theForm');
		var remark = theForm.INPUT_REMARK.value; 
		if(remark == null || "" == remark){
			alert('�������������������');
			return;
		}
		theForm.action = 'appworkflow_fightbackWaitDealApp.do';  
		theForm.submit();
	}
	var win;
	function openNewWindows(NEED_NUMBERS){
		var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
		win = window.open("appworkflow_editAppOrder.do?NEED_NUMBERS="+NEED_NUMBERS,"editAppOrder",'width=800, height=600, resizable=yes');
 	}
 	
 	//���Ӵ��ڵõ�IP���õ�IP�����ִ��
 	function get(){
 			$("#div").append("<table id='table' width='100%' border='0' cellspacing='0'  </table>");
			$("#table").attr("class","blue-table sorttable");
			$("#table").empty();//����ʾ��IPɾ��
 		if(theForm.APP_IDVALUE.value !=null && theForm.APP_IDVALUE.value!=''){
 			$("#table").append("<tr id='appid'> </tr>");	
 			$("#appid").append("<td id='app1'> </td>");
 			$("#app1").append("Ӧ�ñ��") ;
 			$("#appid").append("<td id='app2'> </td>");
 			$("#app2").append(theForm.APP_IDVALUE.value) ;
 			$("#app1").attr("class","til"); 
			$("#app2").attr("class","til");
		}
		if(theForm.APP_PORTVALUE.value !=null && theForm.APP_PORTVALUE.value!=''){
			$("#table").append("<tr id='port'> </tr>");	
 			$("#port").append("<td id='port1'> </td>");
 			$("#port1").append("Ӧ�ö˿�") ;
 			$("#port").append("<td id='port2'> </td>");
 			$("#port2").append(theForm.APP_PORTVALUE.value) ;
 			$("#port1").attr("class","til"); 
			$("#port2").attr("class","til");
		}
		
		if(theForm.COUNTIP.value != null && theForm.COUNTIP.value !=''){
			var getip = theForm.COUNTIP.value;					
			var str = getip.split(",");
			
			$("#table").append("<tr id='tr'> </tr>");
			$("#tr").append("<td id='td1'> </td>");
			 $("#td1").append("��ѡ������   ") ;
			 $("#tr").append("<td id='td2'> </td>");
			for(var i=0;i<(str.length-1);i++){
          		 $("#td2").append( "<input type=checkbox  id='"+str[i]+"' disabled checked>" + str[i] + ' ' ) ;
			}
			$("#td1").attr("class","til"); 
			$("#td2").attr("class","til");
         }
         if(theForm.COUNTVLAN.value != null && theForm.COUNTVLAN.value !=''){
			var getvlan = theForm.COUNTVLAN.value;	
			var vlan = getvlan.split(",");
			$("#table").append("<tr id='vlan'> </tr>");
			$("#vlan").append("<td id='vlan1'> </td>");
			 $("#vlan1").append("��ѡ�����IP   ") ;
			 $("#vlan").append("<td id='vlan2'> </td>");
			for(var i=0;i<(vlan.length-1);i++){
          		 $("#vlan2").append( "<input type=checkbox  id='"+vlan[i]+"' disabled checked>" + vlan[i] + ' ' ) ;
			}
			$("#vlan1").attr("class","til"); 
			$("#vlan2").attr("class","til");
         }
 	}
</script>
	</head>
	<body>
	<s:form action="appworkflow_dealAppDeploy.do" method="post" id="theForm" cssClass="theForm">
	<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
	<s:hidden  name="theForm.taskId" id="taskId"></s:hidden>
	<s:hidden name="theForm.processNode" id="processNode"></s:hidden>
	<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE"></s:hidden>
	<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"></s:hidden>
	<s:hidden name="theForm.COUNTIP" id="COUNTIP"></s:hidden>
	<s:hidden name="theForm.APP_IDVALUE" id="APP_IDVALUE"></s:hidden>
	<s:hidden name="theForm.APP_PORTVALUE" id="APP_PORTVALUE"></s:hidden>
	<s:hidden name="theForm.COUNTVLAN" id="COUNTVLAN"></s:hidden>
	<div class="scrollbody">
     <div>
		<div class="tit-zzi" ><span class="icon"></span>
			<div id="zi">���뵥������Ϣ</div>
			<div id="zi"></div>							
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						�������
					</td>
					<td>
						<s:text name="theForm.NEED_NUMBERS"></s:text>
					</td>
					<td class="til" style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">
						��������
					</td>
					<td id="detail">
						<s:text name="theForm.NEED_CONT"></s:text>
					</td>
				</tr>
				<tr>
					<td class="til">
						Ӧ�÷���ʱ��
					</td>
					<td>
						<s:text name="theForm.START_DATE"></s:text>
					</td>
					<td class="til">
						Ҫ�����ʱ��
					</td>
					<td>
						<s:text name="theForm.END_DATE"></s:text>
					</td>
				</tr>	
				<tr>
					<td class="til">
						Ӧ�ø���
					</td>
					<td>
						<s:text name="theForm.APP_SIZE"></s:text>
					</td>
					<td class="til">
						Ӧ������
					</td>
					<td>
						<s:text name="theForm.APPNAME"></s:text>
					</td>
				</tr>
				</table>
				
				
			</div>
			<% Boolean b = (Boolean)request.getAttribute("isSponsor");
				if(!b){
			%>
		<div class="tit-zzi">
			<div id="zi">���������Ϣ
				 <s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
				 	<input type="button" class="thickbox btn-style02-75"
						value="ѡ������"	onclick="javascript:openNewWindows();" />  
				 </s:if> 
			</div>
		</div>
			<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
		<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr id="isdefend" style="display:">
					<td class="til">
						�Ƿ���ӷ��۸ģ�
					</td>
					<td>
						<s:radio list="#{'0':'��','1':'��'}" name="theForm.DEFEND_FLAG" id="DEFEND_FLAG"></s:radio>
					</td>
				</tr>
				<tr>
					<td class="til">
						���۸�Ŀ¼��
					</td>
					<td>
						<s:textfield name="theForm.DEFEND_DIR" cssClass="txt" id="DEFEND_DIR"></s:textfield>
					</td>
				</tr>
		</table>
		</s:if>
		<s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			 <tr>
			 	<td class="til">
						�Ƿ���ӷ��۸�
				</td>
				<td>
					<s:if test="theForm.DEFEND_FLAG==0">��</s:if>
					<s:else>��</s:else>
				</td>
				<td class="til">
						���۸�Ŀ¼
				</td>
					<td>
					<s:property value="theForm.DEFEND_DIR"/>
					</td>
				</tr>
				<tr>
			  	<td class="til">
			  		Ӧ�ñ��
			  	</td>
			  	<td>
			  		<s:text name="theForm.APP_IDVALUE"></s:text>
			  	</td>
			  	<td class="til">
			  		Ӧ�ö˿�
			  	</td>
			  	<td>
			  		<s:text name="theForm.APP_PORTVALUE"></s:text>
			  	</td>
			  	</tr>
			  	<tr>
			  	<td class="til">
			  		��ѡ����
			  	</td>
			  	<td>
			  	<% 
			  	String str =	(String)request.getAttribute("getip");
			  	if(str !=null){
			  	String ip[] = str.split(",");
			  	for(int i=0;i<ip.length;i++){
			  		%>
			  	<input type="checkbox" disabled="disabled" checked="checked"/>
			  	<% 
			  		out.println(ip[i]);
			  	}
			  	}		
			  	%>
			  	</td>
			  	<td class="til">
			  		����IP
			  	</td>
			  	<td>
			  	<%
			  		String getvlan = (String)request.getAttribute("getvlan");
			  	if(getvlan !=null){
			  		String vlan[] = getvlan.split(",");
			  		for(int j=0;j<vlan.length;j++){
			  	%>
			  	<input type="checkbox" disabled="disabled" checked="checked"/>
			  	<%
			  		out.println(vlan[j]);
			  		}
			  	}
			  	%>
			  	</td>
			  	</tr>
			</table>
				</s:if>	
		<%} %>
		<div>	
			<div id="div">
			</div>
		</div>
	<div>
		<div class="tit-zzi">
			<div id="zi">���̻�����Ϣ</div>
			<div id="zi"></div>							
		</div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>��ˮ��</th>
					<th>������</th>
					<th>����ʱ��</th>
					<th>����ʱ��</th> 
					<th>�������</th>
				</tr>
			  </thead>
			  <tbody>
			  <%int  m = 0; %>
			  <s:iterator value="theForm.taskInfoResultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.TASK_ID"/></td>
			  		<td><s:property value="#theBean.DISPOSE_MAN"/>
			  			<%if(m == 0){
								out.print("(������)");
									} %>
			  		</td>
			  		<td><s:property value="#theBean.RECEIVE_TIME"/></td>
			  		<td><s:property value="#theBean.DISPOSE_TIME"/></td>
						<s:if test="#theBean.REMARK !=null">
							<td>	
										<s:property value="#theBean.REMARK"/>
							</td>
						</s:if>
						<s:else>
							<td>
										<s:if test="#theBean.REMARK==null">��</s:if>
										
							</td>
						</s:else>
			  	</tr>
			  	<%m++; %>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
			<s:if test="theForm.FLOW_TYPE!=@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_ALREADYDO && 
			theForm.FLOW_TYPE !=@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_HUNGLIST">
							<div class="tit-zzi"> 
								<div id="zi">
									<s:text name="theForm.NEED_REMARK"></s:text>
								</div> 
								<div id="zi"></div>							
							</div>
							<div class="pop-table">
								 <table width="100%" border="0" cellpadding="0" cellspacing="0">
						                <tr > 
						                   <th class="til"><s:text name="theForm.NEED_REMARK"></s:text>:	</th>
											<td colspan="3">
												<s:textarea name="theForm.INPUT_REMARK" cols="77" rows="5" id="INPUT_REMARK"></s:textarea>
											</td>	
										</tr> 
								 </table>
							</div>
					</s:if>
			 		
					
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
								 	<tr> 
									 	<td colspan="4" class="btnCenter">
						   			        <s:if test="theForm.FLOW_TYPE==@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_TYPE_TODOLIST">
						   			        	<s:if test="theForm.TASK_OPERATE.contains(@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_OPERATE_APPROVAL)">
						   			        		<input  type="button" class="thickbox btn-style02" value="ͬ��" onclick="agreeCommit();"/>	
						   			        	</s:if>
						   			        	<s:if test="theForm.TASK_OPERATE.contains(@com.sitech.basd.sxcloud.workflow.domain.engine.FlowConstant@FLOW_OPERATE_PLAYEDBACK)">
						   			        		<input  type="button" class="thickbox btn-style02" value="���" onclick="fightbackCommit();"/>
						   			        	</s:if>
						   			        </s:if>
											 <input  type="button" class="thickbox btn-style02" value="����" onclick="window.history.back()"/>
										</td>
									</tr>
							</table>   
      			  </div>
      		  </div>
		</s:form>
	</body>
