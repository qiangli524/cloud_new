<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
<script type="text/javascript">
	 
		var dialogType = "";
		
		function showVMDetail(VH_NAME,EQ_NAME,VH_TYPE,EQ_ID,VH_SYSTEM,VH_CPU,VH_MEM,VH_STORAGE,VH_NETWORK,VH_STAT,VH_UUID,VH_IP,connectId){
			var flag = theForm.flag.value;
			var busiId = $("#busiId").val();
			if (flag == 'host' || busiId.length > 0) {
				var api = frameElement.api;
		    	var w = api.opener;
				if(VH_TYPE=="VMWARE"){
					dialogType = "VMWARE";
					w.$.dialog({
					id:'showVmwareDetail',
					title:'Vmware虚拟机详细信息',
					lock:true,
					resize:false,
					width: '600px',
					height: '500px',
					content: 'url:showvm_showVmwareDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
					+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}else if(VH_TYPE=="XEN"){ 
					dialogType = "XEN";
					w.$.dialog({
					id:'showXenDetail',
					title:'XEN虚拟机详细信息',
					lock:true,
					resize:false,
					width: '600px',
					height: '500px',
					content: 'url:showvm_showXenDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
					+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}
			} else {
				if(VH_TYPE=="VMWARE"){
					dialogType = "VMWARE";
					$.dialog({
					id:'showVmwareDetail',
					title:'Vmware虚拟机详细信息',
					lock:true,
					resize:false,
					width: '600px',
					height: '500px',
					content: 'url:showvm_showVmwareDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
					+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}else if(VH_TYPE=="XEN"){ 
					dialogType = "XEN";
					$.dialog({
					id:'showXenDetail',
					title:'XEN虚拟机详细信息',
					lock:true,
					resize:false,
					width: '600px',
					height: '500px',
					content: 'url:showvm_showXenDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
					+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}
			}
		}
		$(function(){
		 	$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
			});
		
			$("a[name='vm_motion']").click(function(){				
				var vh_id=$(this).parent().parent().attr("vhid");
				var conn_id = $(this).attr("connect_id");
				if(conn_id!=null && conn_id!=""){
					vh_id = conn_id + "_" + vh_id;
				}else{
					vh_id = vh_id;//山西虚拟机表中没有存connect_id @yanggl
				}
				var flag = theForm.flag.value; 
				var busiId = $("#busiId").val();
				if (flag == 'host' || busiId.length > 0) {
					var api = frameElement.api;
		    		var w = api.opener;
					w.$.dialog({
						id:vh_id,
						title:'性能监控',
						width: '800px',
						height: '500px',
						lock:true,
						content: 'url:hyMonitor_highChartsTabs.do?hyId='+vh_id
					});
				} else {
					$.dialog({
						id:vh_id,
						title:'性能监控',
						width: '800px',
						height: '500px',
						lock:true,
						content: 'url:hyMonitor_highChartsTabs.do?hyId='+vh_id
					});
				}
			});
			
			//查询获取主机的IP
			$("[name='queryHostIp']").unbind().live("click",function(){
		    	var type = $("#queryType option:selected").val(); 
		    	var flag = theForm.flag.value;
		    	var busiId = $("#busiId").val();
				if (flag == 'host' || busiId.length > 0) {
					var api = frameElement.api;
		    		var w = api.opener;
			    	w.$.dialog({
			    			id:'queryHostIp',
			    			title:'选择主机',
			    			width: '800px',
			    			height: '400px',
			    			max: true,
			    		    min: true,
			    		    lock:true,
			    			content: 'url:showvm_queryHostIp.do?type='+type + '&flag=' + flag
			    	}); 
			    } else {
			   		$.dialog({
			    			id:'queryHostIp',
			    			title:'选择主机',
			    			width: '800px',
			    			height: '400px',
			    			max: true,
			    		    min: true,
			    		    lock:true,
			    			content: 'url:showvm_queryHostIp.do?type='+type + '&flag=' + flag
			    	});
			    }
	        });
	        
	        $("a[name='app_list']").click(function(){
	        	var $td = $(this).parent();
		        var hostid = $td.attr("hostid"); 
		        var flag = theForm.flag.value;
		        var busiId = $("#busiId").val();
				if (flag == 'host' || busiId.length > 0) {
					var api = frameElement.api;
		    		var w = api.opener;
					w.$.dialog({
						id:'app_list',
						title:'应用信息',
						width: '1000px',
						height: '500px',
						lock:true,
						content: 'url:dep_listDeployExample.do?HOST_ID='+hostid
					});
				} else {
					$.dialog({
						id:'app_list',
						title:'应用信息',
						width: '1000px',
						height: '500px',
						lock:true,
						content: 'url:dep_listDeployExample.do?HOST_ID='+hostid
					});
				}
			});
			
			
			$("#searchForm").click(function(){
				searchRequest();
			})
			$("#resetForm").click(function(){
				resetForm();
			})
			$("#exportForm1").click(function(){
				listExp();
			})
			
			
		});
		function closeDialog(){
			var theForm = document.getElementById("theForm");
			var eq_id = theForm.EQ_ID.value;
			if(dialogType=="VMWARE"){
				$.dialog.list['showVmwareDetail'].close();
				var theForm = document.getElementById("theForm");
				theForm.action='showvm_listvm.do?eq_id'+eq_id;
				theForm.submit();
			}else if(dialogType=="XEN"){
				$.dialog.list['showXenDetail'].close();
				var theForm = document.getElementById("theForm");
				theForm.action='showvm_listvm.do?eq_id='+eq_id;
				theForm.submit();
			}
		} 
	function resetForm(theForm){
		$("#center_uuidd").attr("value","");
		$("#queryName").attr("value","");
		$("#hostip").attr("value","");
		$("#queryType").attr("value",'0');
		$("#queryState").attr("value",'');
		$("#queryVHIP").attr("value","");
	}
	function searchRequest(){
		mask('正在查询,请稍后....','0.5','0px');
		theForm.submit();
	}
	function changePageText(state,vh_uuid){
		$("#"+vh_uuid+" td:eq(8)").text(state);
		var arr = $("#"+vh_uuid+" td:last-child a").attr("href").split(",");
		if(state=="正在运行"){
			arr[9]="1";
		}else if(state=="已关闭"){
			arr[9]="0";
		}
		$("#"+vh_uuid+" td:last-child a").attr("href",arr.toString());
	}
	
	//获取到主机IP，赋值查询框
	function getHostip(hostip) {
		theForm.hostip.value = hostip;
	}
	
	function changeHostIp(){
		var type = $("#queryType option:selected").val();
		if(type == 0){
			$("#queryHostIp option").remove();
			$("#queryHostIp").append(createSelect("0","--请选择--"));
		}else{
			var url = "showvm_queryHostIp.do?type="+type;
			$.getJSON(url,function(data){
				$("#queryHostIp option").remove();
				$("#queryHostIp").append(createSelect("0","--请选择--"));
	     		if(data != null){
	     			$.each(data,function(key,value){
						$("#queryHostIp").append(createSelect(key,value));
					});
	     		}
			});
		}
	}
	
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		return opt;
	}

	function listExp(){
		var name = theForm.queryName.value;
		var hostip = theForm.hostip.value;
		var type = theForm.queryType.value;
		var state = theForm.queryState.value; 
		var center_uuid = theForm.center_uuidd.value; 
		var url="<%=excel_export_jsp%>?key=ah_vmhostinfo&name=" + name + "&hostip=" + hostip;
		if(center_uuid !=''){
			url = url + "&center_uuid=" + center_uuid;
		}
		if (type > 0) {
		    url = url + "&type=" + type;
		}
		if (state != "") {
		    url = url + "&state=" + state;
		} 
		exportForm.action =url;
		exportForm.submit();
	}
</script>
</head>
  <style type="text/css">
		div.hidden{
		width:200px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<body  class="pop-body scrollbody">
<s:form action="showvm_ah_listvm.do" method="post" cssClass="theForm" id="theForm">
<s:hidden id="EQ_ID" name="theForm.EQ_ID"></s:hidden>
<s:hidden id="flag" name="theForm.flag"></s:hidden>
<s:hidden id="busiId" name="busiId"></s:hidden>
	<div class="scrollbody">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">虚拟机管理</h2>
	       	<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
        			<label class="vm">数据中心:</label>
					<s:select list="theForm.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
							name="theForm.center_uuid" value="theForm.center_uuid" cssClass="select-1 vm"></s:select> 
        			<label class="vm">虚拟机名称:</label>
					<s:textfield name="theForm.queryName" id="queryName" cssClass="inpt-1 vm"></s:textfield>
        			
        			<label class="vm">IP:</label>
					<s:textfield name="theForm.queryVHIP" id="queryVHIP" cssClass="inpt-1 vm"></s:textfield>
        			
        			<label class="vm">虚拟化类型:</label>
					<s:select id="queryType" name="theForm.queryType" list="#{'0':'--请选择--','1':'VMWARE','3':'XEN','9':'其他'}" onchange="changeHostIp();" cssClass="select-1 vm"></s:select>
        			<div>
        			<label class="vm">所属主机:</label>
					<s:textfield name="theForm.queryHostIp" cssClass="inpt-1 vm" id="hostip"></s:textfield>
					<span class="ubtn-1 mgl-20"><input type="button"  value="选择"  class="btn-style02" name="queryHostIp"/></span>	
        			<label class="vm">状态:</label>
					<s:select id="queryState" name="theForm.queryState" list="#{'':'--请选择--','0':'已关闭','1':'正在运行','2':'挂起'}" cssClass="select-1 vm"></s:select>
        			
        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"  value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" id="resetForm"  value="重置" /></span>
					</div>
			</div>
			<div class="utt-2 mgt-20">
					<a class="icon-export" href="javascript:void(0)" id="exportForm1" >导出</a>
			</div>
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'ip')">IP</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<!-- <th onclick="sort(theTable,3,'string')">应用个数</th> -->
					<th onclick="sort(theTable,11,'string')">虚拟机状态</th>
					<th onclick="sort(theTable,12,'string')">上线状态</th>
					<th onclick="sort(theTable,4,'string')">项目名称</th>
					<th onclick="sort(theTable,5,'string')">业务</th>
					<th onclick="sort(theTable,6,'string')">网络域</th>
					<th onclick="sort(theTable,7,'int')">CPU(核)</th>
					<th onclick="sort(theTable,8,'int')">内存(G)</th>
					<th onclick="sort(theTable,9,'int')">存储(G)</th>
						<th>性能</th>
						<th>明细</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr id="<s:property value="#theBean.VH_UUID"/>" vhid='<s:property value="#theBean.VH_UUID"/>' connID='<s:property value="#theBean.connectId"/>'>
			  		<td width="200"> 
			  			<div class="hidden" title='<s:property value="#theBean.VH_NAME"/>'>
			  				<s:property value="#theBean.VH_NAME"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:property value="#theBean.VH_TYPE"/></td>
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
			  		<td>
			  			<s:if test="#theBean.BUSI_STATUS==1">
		  				  上线
		  				</s:if>
		  				<s:else>
		  				 未上线
		  				</s:else>
			  		</td>
			  		<%--<td hostid='<s:property value="#theBean.H_ID" />'>
			  			<s:if test="#theBean.APPNUM !=0">
			  				<a href='javascript:;' name='app_list'>
			  					<s:property value="#theBean.APPNUM"/>个
			  				</a>
			  			</s:if>
			  			<s:else>
			  				<s:property value="#theBean.APPNUM"/>个
			  			</s:else>
			  		</td>--%>
					<td>
		  				<s:property value="#theBean.PROJECT_NAME" default="-"/></a>
			  		</td>
			  		<td> 
		  				<s:property value="#theBean.name" default="-"/>
			  		</td>
			  		<td><s:property value="#theBean.NET_NAME" default="-"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/></td>
			  		<td>
			  			<s:if test="%{VH_MEM%1024==0}">
			  				<fmt:formatNumber type="currency" pattern="#0" value="${VH_MEM/1024}"></fmt:formatNumber>
			  			</s:if>
			  			<s:else>
			  				<fmt:formatNumber type="currency" pattern="#0" value="${VH_MEM/1024}"></fmt:formatNumber>
						</s:else>
			  		</td>
			  		<td><s:property value="#theBean.VH_STORAGE"/></td>
				  		<td>
				  			<a href='javascript:;' name='vm_motion' connect_id='<s:property value="#theBean.connectId"/>'>性能</a>
				  		</td>
				  		<td ><a href="javascript:showVMDetail('<s:property value="#theBean.VH_NAME"/>','<s:property value="#theBean.EQ_NAME"/>','<s:property value="#theBean.VH_TYPE"/>',
				  		'<s:property value="#theBean.EQ_ID"/>','<s:property value="#theBean.VH_SYSTEM"/>','<s:property value="#theBean.VH_CPU"/>','<s:property value="#theBean.VH_MEM"/>',
				  		'<s:property value="#theBean.VH_STORAGE"/>','<s:property value="#theBean.VH_NETWORK"/>','<s:property value="#theBean.VH_STAT"/>','<s:property value="#theBean.VH_UUID"/>',
				  		'<s:property value="#theBean.VH_IP"/>','<s:property value="#theBean.connectId"/>')">查看</a></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
			<div style="height:10px;"></div>
			<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</s:form>
<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
