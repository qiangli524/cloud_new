<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
<%@ include file="../../../common/link.jsp"%>
<%@ page
	import="com.sitech.basd.sxcloud.workflow.domain.resourceorder.WorkFlowConstant"%>
<head>
	<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			var flag= true;
			function validateForm(){
				document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
				//�첽��ʽ�ж� ��������Ƿ�Ψһ
				var NEED_NUMBERS = document.getElementById("theForm").NEED_NUMBERS.value;
				if(NEED_NUMBERS == null || "" == NEED_NUMBERS){
					document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "������Ų���Ϊ��";
					flag=false;
					return false;
				}
				var url = "OrderUniqueJudgement.do?NEED_NUMBERS="+NEED_NUMBERS+"&Date"+(new Date());
				 $.getJSON(url,function(data){
				 	if("NO" == data ){
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = NEED_NUMBERS+"�Ѿ����ڣ�����Ĺ������!";
				 		flag=false;
				 	}
				 })
			}
			
			//�ύ
			function submitForm(status){
				if(flag){
				document.getElementById("theForm").COMMAND.value = status;
				theForm.action = 'saveOrderInfo.do';
				theForm.submit();
				}else{
				  alert("��Ŵ�������������ţ�");
				}
			}
	function selectTemplet() {
		var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
		if(theForm.SELECT.value==0){
			return false;
		}else{
		
			theForm.action = 'resworkflow_selectTemplet.do?SELECT='+theForm.SELECT.value;
			theForm.submit();
		}
	}
	function saveSubPageMess(){
		var type = theForm.TYPE.value;
		if (type == '6' || type == '2') {
			window.opener.theForm.VH_CPUVALUE.value = theForm.VH_CPUVALUE.value;
			window.opener.theForm.VH_MIN_CPUVALUE.value = theForm.VH_MIN_CPUVALUE.value;
			window.opener.theForm.VH_MAX_CPUVALUE.value = theForm.VH_MAX_CPUVALUE.value;
			window.opener.theForm.VH_MEMVALUE.value = theForm.VH_MEMVALUE.value;
			window.opener.theForm.VH_MIN_MEMVALUE.value = theForm.VH_MIN_MEMVALUE.value;
			window.opener.theForm.VH_MAX_MEMVALUE.value = theForm.VH_MAX_MEMVALUE.value;
			window.opener.theForm.VH_PROCESS_UNITVALUE.value = theForm.VH_PROCESS_UNITVALUE.value;
			window.opener.theForm.VH_MAX_PROCESS_UNITVALUE.value = theForm.VH_MAX_PROCESS_UNITVALUE.value;
			window.opener.theForm.VH_MIN_PROCESS_UNITVALUE.value = theForm.VH_MIN_PROCESS_UNITVALUE.value;
		} else if (type == '7' || type == '1' || type == '3' || type == '8') {
			window.opener.theForm.VH_CPUVALUE.value = theForm.VH_CPUVALUE.value;
			window.opener.theForm.VH_MEMVALUE.value = theForm.VH_MEMVALUE.value;
		} else if(type == '4' ) {
			window.opener.theForm.VH_STORAGEVALUE.value = theForm.VH_STORAGEVALUE.value;
		}
		theForm.action = 'resworkflow_saveSubPageMess.do';
		theForm.submit();
		this.close();
	}
		</script>
</head>
<body class="pop-body scrollbody" onunload="opener.get()">
	<s:form action="resworkflow_editResourceOrder.do" method="post"
		id="theForm">
		<s:hidden name="theForm.COMMAND" id="COMMAND" />
		<s:hidden name="theForm.FLOW_TYPE" id="FLOW_TYPE" />
		<s:hidden name="theForm.NEED_NUMBERS" id="NEED_NUMBERS" />
		<s:hidden name="theForm.TYPE" id="TYPE" />
		<s:hidden name="theForm.TEM_ID" id="TEM_ID"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						ѡ��ģ��
					</td>
					<td>
						<s:select list="theForm.templetList" name="theForm.SELECT"
							headerKey="0" headerValue="-��ѡ��ģ��-" listKey="TEM_ID"
							listValue="TEM_NAME" onchange="selectTemplet()" id="SELECT">
						</s:select>
					</td>
				</tr>
			</table>
		</div>
		<div class="tit-zzi">
			<div id="zi">
				ģ�������Ϣ
			</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						ģ����
					</td>

					<td>
						<s:if test="theForm.TEM_ID !=null">
							<s:text name="theForm.TEM_ID" />
						</s:if>
					</td>


					<td class="til">
						ģ������
					</td>
					<td>
						<s:if test="theForm.TEM_NAME !=null">
							<s:text name="theForm.TEM_NAME" />
						</s:if>
					</td>

				</tr>
				<tr>
					<td class="til">
						ģ������
					</td>

					<td >
						<s:if test="theForm.TYPE_NAME!=null">
							<s:text name="theForm.TYPE_NAME" />
						</s:if>
					</td>
					<td class="til">
						ģ������
					</td>
					<td>
						<s:if test="theForm.TEM_DESC!=null">
							<s:text name="theForm.TEM_DESC" />
						</s:if>
					</td>
				</tr>

			</table>
		</div>
		<div class="tit-zzi">
			<div id="zi">
				ģ��������Ϣ&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0"
				cellspacing="0">
				<thead>
					<tr>
						<th>
							������
						</th>
						<th>
							����ֵ
						</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="theForm.VH_CPUVALUE!=null ">
						<tr>
							<td>
								<s:text name="theForm.VH_CPU"></s:text>
							</td>
							<td>
								<s:textfield name="theForm.VH_CPUVALUE" id="VH_CPUVALUE"></s:textfield>
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MEMVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MEM" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MEMVALUE" id="VH_MEMVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MAX_CPUVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MAX_CPU" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MAX_CPUVALUE" id="VH_MAX_CPUVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.IMAGE_IDVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.IMAGE_ID" />
							</td>
							<td>
								<s:textfield name="theForm.IMAGE_IDVALUE" id="IMAGE_IDVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MIN_CPUVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MIN_CPU" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MIN_CPUVALUE" id="VH_MIN_CPUVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MAX_MEMVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MAX_MEM" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MAX_MEMVALUE" id="VH_MAX_MEMVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MIN_MEMVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MIN_MEM" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MIN_MEMVALUE" id="VH_MIN_MEMVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_PROCESS_UNITVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_PROCESS_UNIT" />
							</td>
							<td>
								<s:textfield name="theForm.VH_PROCESS_UNITVALUE"
									id="VH_PROCESS_UNITVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MAX_PROCESS_UNITVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MAX_PROCESS_UNIT" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MAX_PROCESS_UNITVALUE"
									id="VH_MAX_PROCESS_UNITVALUE" />
							</td>
						</tr>
					</s:if>
					<s:if test="theForm.VH_MIN_PROCESS_UNITVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_MIN_PROCESS_UNIT" />
							</td>
							<td>
								<s:textfield name="theForm.VH_MIN_PROCESS_UNITVALUE"
									id="VH_MIN_PROCESS_UNITVALUE" />
							</td>
						</tr>
					</s:if>

					<s:if test="theForm.VH_STORAGEVALUE!=null">
						<tr>
							<td>
								<s:text name="theForm.VH_STORAGE" />
							</td>
							<td>
								<s:textfield name="theForm.VH_STORAGEVALUE" id="VH_STORAGEVALUE" />
							</td>
						</tr>
					</s:if>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="btn-style02" value="ȷ��"
							onclick="saveSubPageMess()" />
						<input type="button" class="btn-style02" value="����"
							onclick="window.close();" />
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>