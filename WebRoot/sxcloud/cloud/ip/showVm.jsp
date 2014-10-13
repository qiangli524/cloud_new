<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp"%>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
<script type="text/javascript">
	var dialogType = "";
	var api = frameElement.api;
	var w = api.opener;
	function aa(VH_NAME,EQ_NAME,VH_TYPE,EQ_ID,VH_SYSTEM,VH_CPU,VH_MEM,VH_STORAGE,NICNUM,VH_STAT,VH_UUID,VH_IP){
		if(VH_TYPE=="VMWARE"){
			dialogType = "VMWARE";
			w.$.dialog({
			id:'showVmwareDetail',
			title:'Vmware虚拟机详细信息',
			resize:false,
			width: '600px',
			height: '410px',
			lock:true,
			content: 'url:showvm_showVmwareDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
			+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
			+'&NICNUM='+NICNUM+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP});
		}else if(VH_TYPE=="XEN"){ 
			dialogType = "XEN";
			w.$.dialog({
			id:'showXenDetail',
			title:'XEN虚拟机详细信息',
			resize:false,
			width: '600px',
			height: '410px',
			lock:true,
			content: 'url:showvm_showXenDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
			+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
			+'&NICNUM='+NICNUM+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP});
		}
	}
	function closeDialog(){
		var vmForm = document.getElementById("vmForm");
		var eq_id = vmForm.EQ_ID.value;
		if(dialogType=="VMWARE"){
			w.$.dialog.list['showVmwareDetail'].close();
			var vmForm = document.getElementById("vmForm");
			vmForm.action='showvm_listvm.do?eq_id'+eq_id;
			vmForm.submit();
		}else if(dialogType=="XEN"){
			w.$.dialog.list['showXenDetail'].close();
			var vmForm = document.getElementById("vmForm");
			vmForm.action='showvm_listvm.do?eq_id='+eq_id;
			vmForm.submit();
		}
	}
	
	function changePageText(state,vh_uuid){
		$("#"+vh_uuid+" :eq(9)").text(state);
	}

	$(function(){
		$("a[name='vm_motion']").click(function(){
			var vh_id=$(this).parent().parent().attr("vhid");
			w.$.dialog({
				id:vh_id,
				title:'性能监控',
				width: '1000px',
				height: '500px',
				lock:true,
				content: 'url:hyMonitor_highChartsTabs.do?hyId='+vh_id
				});
		});
	});
	
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}
</script>
</head>
<body>
<s:form action="showvm_listvm.do" method="post" cssClass="vmForm" id="vmForm">
<s:hidden id="EQ_ID" name="vmForm.EQ_ID"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'string')">IP</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<%--<th onclick="sort(theTable,3,'string')">应用个数</th>--%>
					<th onclick="sort(theTable,4,'string')">操作系统</th>
					<th onclick="sort(theTable,5,'string')">CPU</th>
					<th onclick="sort(theTable,6,'string')">内存</th>
					<th>性能</th>
					<th onclick="sort(theTable,7,'string')">状态</th>
					<th>明细</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="vmForm.resultList" id="theBean">
			  	<tr id="<s:property value="#theBean.VH_UUID"/>" vhid='<s:property value="#theBean.VH_UUID"/>'>
			  		<td><s:property value="#theBean.VH_NAME"/></td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:property value="#theBean.VH_TYPE"/></td>
			  		<%--<td>
			  			<s:if test="#theBean.APPNUM !=0">
			  				<a href="dep_listDeployExample.do?HOST_ID=<s:property value="#theBean.HOST_ID"/>">
			  					<s:property value="#theBean.APPNUM"/>个
			  				</a>
			  			</s:if>
			  			<s:else>
			  				<s:property value="#theBean.APPNUM"/>个
			  			</s:else>
			  		</td>--%>
			  		<td><s:property value="#theBean.VH_SYSTEM"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/>核</td>
			  		<td><s:property value="#theBean.VH_MEM"/>M</td>
			  		<td><a href='javascript:;' name='vm_motion'>性能</a></td>
			  		<td id="stateText">
			  			<s:if test="#theBean.VH_STAT==1">
							正在运行
						</s:if>
						<s:if test="#theBean.VH_STAT==0">
							已关闭
						</s:if>
						<s:if test="#theBean.VH_STAT==2">
							挂起
						</s:if>
			  		</td>
			  		<td ><a href="javascript:aa('<s:property value="#theBean.VH_NAME"/>','<s:property value="#theBean.EQ_NAME"/>','<s:property value="#theBean.VH_TYPE"/>',
			  		'<s:property value="#theBean.EQ_ID"/>','<s:property value="#theBean.VH_SYSTEM"/>','<s:property value="#theBean.VH_CPU"/>','<s:property value="#theBean.VH_MEM"/>',
			  		'<s:property value="#theBean.VH_STORAGE"/>','<s:property value="#theBean.NICNUM"/>','<s:property value="#theBean.VH_STAT"/>','<s:property value="#theBean.VH_UUID"/>','<s:property value="#theBean.VH_IP"/>')">查看</a></td>
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
