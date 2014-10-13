<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript">
	
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	
		          
		$("[name='manager']").click(function(){
		        	currentEdit=$(this);
		        	var DEVICE_ID=$(this).attr("DEVICE_ID");
		    		$.dialog({
		    			id:'vdi',
		    			title:'用户信息',
		    			width: '890px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    			content: 'url:pwdobj_queryPwdObj.do?DEVICE_ID='+DEVICE_ID
		    			});
		              });   
		})
	
		function resetForm(theForm){
			theForm.DEVICE_NAME.value = '';
			theForm.IP_PHYSICS.value = '';
			theForm.USE_DOMAN.value = '';
		}
	
		function searchRequest() { 
			theForm.submit();
	 	}
	 	
	</script>
	<script type="text/javascript">

	function canel(){
	document.getElementById("mdiv").style.display="none";
	}

	function listDetails(device_id){ 
		var device_id = device_id;
		window.open("condevice_getDeviceInfo.do?device_id="+device_id,"listDetails",'height=250,width=550,top=500,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
        </script>
</head>
<body>
	<s:form action="condevice_queryManage.do" id="theForm" method="post"
		cssClass="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								设备名称:
							</td>
							<td>
								<s:textfield name="theForm.DEVICE_NAME" cssClass="txt"
									id="DEVICE_NAME"></s:textfield>
							</td>
							<td class="til">
								物理IP:
							</td>
							<td>
								<s:textfield name="theForm.IP_PHYSICS" cssClass="txt"
									id="IP_PHYSICS"></s:textfield>
							</td>
							<td class="til">
								归属域:
							</td>
							<td>
								<s:textfield name="theForm.USE_DOMAN" cssClass="txt"
									id="USE_DOMAN"></s:textfield>
							</td>
							
							<td class="til">
								资源池:
							</td>
							<td>
								<s:textfield name="theForm.USE_RES" cssClass="txt"
									id="USE_RES"></s:textfield>
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
					
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										设备名称
									</th>
									
									<th>
										设备类型
									</th>
									<th>
										设备状态
									</th>
									<th>
										物理IP
									</th>
									<th>
										归属域
									</th>
									<th>
										资源池
									</th>
									<th>
										用户信息
									</th>
								
									
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr id="<s:property value='#theBean.DEVICE_ID'/>">
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.DEVICE_ID'/>"
												 />
										</td>
										<td>
											<s:property value="#theBean.DEVICE_NAME" />
										</td>
										
										<td>
											<s:property value="#theBean.TYPE_NAME" />
										</td>
										<td>
											<s:if test="#theBean.STATUSE==1">正常</s:if>
											<s:elseif test="#theBean.STATUSE==2">不正常</s:elseif>
										</td>
										
										<td>
											<s:property value="#theBean.IP_PHYSICS"/>
										</td>
										<td>
											<s:property value="#theBean.USE_DOMAN"/>
										</td>
										<td>
											<s:property value="#theBean.USE_RES"/>
										</td>
										<td>
											<a href="javascript:;" DEVICE_ID='<s:property value="#theBean.DEVICE_ID" />' name="manager">修改用户密码</a>
										</td>
										
										
									
									</tr>
								</s:iterator>
							</tbody>


						</table>
							</div>
						</div>
					</div>
				</div>
		</div>

	</s:form>

</body>

