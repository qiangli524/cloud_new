<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

		$("[name='detail']").click(function(){
		       	currentEdit=$(this);
		        	var device_id=$(this).attr("DEVICE_ID");
		    		$.dialog({
		    			id:'vdi',
		    			title:'详细信息',
		    			width: '890px',
		   				height: '550px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:deviceinfo_getDeviceInfo.do?device_id='+device_id
		    			});
		              });  

	})

	function resetForm(theForm){
		theForm.DEVICE_NAME.value = '';
		theForm.DEVICE_NAME_EN.value = '';
		theForm.DEVICE_TYPE.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'deviceinfo_addDevice.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEVICE_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'deviceinfo_modDevice.do';
		theForm.submit();
 	}
 	function delRequest() {
 	
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEVICE_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = "deviceinfo_delDevice.do";  
		theForm.submit();
 	}
 	
 	function getDeviceInfo(device_id){
 		var device_id = device_id;
 		/*
 		$.getJSON("deviceinfo_getDeviceInfo.do?device_id="+device_id,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'     余量:'+data.freeSpace+'GB');
			alert(data.capacity);
		});
		*/
 	}
</script>
	<script type="text/javascript">
	function canel(){
		document.getElementById("mdiv").style.display="none";
	}

	function listDetails(device_id){ 
		var device_id = device_id;
		window.open("deviceinfo_getDeviceInfo.do?device_id="+device_id,"listDetails",'height=250,width=550,top=500,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
	
     </script>
</head>
<body>
	<s:form action="deviceinfo_listDevice.do" id="theForm" method="post"
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
								中文名称:
							</td>
							<td>
								<s:textfield name="theForm.DEVICE_NAME" cssClass="txt"
									id="DEVICE_NAME"></s:textfield>
							</td>
							<td class="til">
								英文名:
							</td>
							<td>
								<s:textfield name="theForm.DEVICE_NAME_EN" cssClass="txt"
									id="DEVICE_NAME_EN"></s:textfield>
							</td>
							<td class="til">
								设备类型:
							</td>
							<td>
								<s:select list="#{'1':'主机','2':'交换机','3':'路由器','4':'磁盘阵列'}"
									id="DEVICE_TYPE" name="theForm.DEVICE_TYPE" headerKey=""
									headerValue="--请选择--"></s:select>
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
							<li>
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0" id="theTable"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										设备名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										英文名称
									</th>
									<th onclick="sort(theTable,3,'string')">
										设备编码
									</th>
									<th onclick="sort(theTable,4,'string')">
										设备类型
									</th>
									<th onclick="sort(theTable,5,'string')">
										设备描述
									</th>
									<th onclick="sort(theTable,6,'string')">
										设备状态
									</th>
									<th onclick="sort(theTable,7,'string')">
										设备详细
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
											<s:property value="#theBean.DEVICE_NAME_EN" />
										</td>
										<td>
											<s:property value="#theBean.DEVICE_CODE" />
										</td>
										<td>
											<s:property value="#theBean.TYPE_NAME" />
										</td>
										<td>
											<s:property value="#theBean.DEVICE_DESC" />
										</td>
										<td>
											<s:if test="#theBean.STATUSE==1">正常</s:if>
											<s:elseif test="#theBean.STATUSE==2">不正常</s:elseif>
										</td>
										<td>
												<a href="javascript:;" DEVICE_ID='<s:property value="#theBean.DEVICE_ID" />' name="detail">详细信息</a>
										<!-- 	<a  href="javascript:listDetails('<s:property value="#theBean.DEVICE_ID" />')">详细信息</a> -->
										</td>

									</tr>
								</s:iterator>
							</tbody>


						</table>
							
				
		</div>

	</s:form>

</body>

