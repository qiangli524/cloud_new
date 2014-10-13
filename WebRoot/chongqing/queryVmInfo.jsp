<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<!-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
 -->
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
<script type="text/javascript">
		var api = frameElement.api;
		var w = api.opener;
		
		api.button({
			id:'OkAnd',
			name: '确定',
			callback:commitScript,
			focus: true
		},
		{
			id:'cancle',
			name: '取消'
		}); 
		var dialogType = "";
		
		function showVMDetail(VH_NAME,EQ_NAME,VH_TYPE,EQ_ID,VH_SYSTEM,VH_CPU,VH_MEM,VH_STORAGE,VH_NETWORK,VH_STAT,VH_UUID,VH_IP,connectId){
			var flag = showVmForm.flag.value;
			if (flag == 'host') {
				var api = frameElement.api;
		    	var w = api.opener;
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
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
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
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}
			} else {
				if(VH_TYPE=="VMWARE"){
					dialogType = "VMWARE";
					$.dialog({
					id:'showVmwareDetail',
					title:'Vmware虚拟机详细信息',
					resize:false,
					width: '600px',
					height: '410px',
					lock:true,
					content: 'url:showvm_showVmwareDetail.do?VH_NAME='+encodeURI(encodeURI(VH_NAME))+'&EQ_NAME='+encodeURI(encodeURI(EQ_NAME))+'&VH_TYPE='+VH_TYPE
					+'&EQ_ID='+EQ_ID+'&VH_SYSTEM='+encodeURI(encodeURI(VH_SYSTEM))+'&VH_CPU='+VH_CPU+'&VH_MEM='+VH_MEM+'&VH_STORAGE='+VH_STORAGE
					+'&VH_NETWORK='+VH_NETWORK+'&VH_STAT='+VH_STAT+'&VH_UUID='+VH_UUID+'&VH_IP='+VH_IP+'&connectId='+connectId});
				}else if(VH_TYPE=="XEN"){ 
					dialogType = "XEN";
					$.dialog({
					id:'showXenDetail',
					title:'XEN虚拟机详细信息',
					resize:false,
					width: '600px',
					height: '410px',
					lock:true,
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
				var flag = showVmForm.flag.value;
				if (flag == 'host') {
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
		    	var flag = showVmForm.flag.value;
				if (flag == 'host') {
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
		        var flag = showVmForm.flag.value;
				if (flag == 'host') {
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
		});
		function closeDialog(){
			var showVmForm = document.getElementById("showVmForm");
			var eq_id = showVmForm.EQ_ID.value;
			if(dialogType=="VMWARE"){
				$.dialog.list['showVmwareDetail'].close();
				var showVmForm = document.getElementById("showVmForm");
				showVmForm.action='convert_queryVmIpAddr.do?eq_id'+eq_id;
				showVmForm.submit();
			}else if(dialogType=="XEN"){
				$.dialog.list['showXenDetail'].close();
				var showVmForm = document.getElementById("showVmForm");
				showVmForm.action='convert_queryVmIpAddr.do?eq_id='+eq_id;
				showVmForm.submit();
			}
		} 
	function resetForm(showVmForm){
		$("#queryName").attr("value","");
		$("#hostip").attr("value","");
		$("#queryType").attr("value",'0');
		$("#queryState").attr("value",'');
		$("#queryVHIP").attr("value","");
	}
	function searchRequest(){
		showVmForm.submit();
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
		showVmForm.hostip.value = hostip;
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
	
	//点击选择一个主机时，把主机的IP返回赋值到父页面
     function commitScript(){
     	var vhId='';
     	var vhName = '';
     	var vhIp = '';
     	var vhCpu = '';
     	var vhMem = '';
     	var vhStorage = '';
     	var vhSystem = '';
  		var lenth=0;
			$('[name=checkboxid]:checkbox:checked').each(function(){
				vhId +=$(this).val();
				vhName +=$(this).attr("vhName");
				vhIp +=$(this).attr("vhIp")+",";
				vhCpu +=$(this).attr("vhCpu");
				vhMem +=$(this).attr("vhMem");
				vhStorage +=$(this).attr("vhStorage");
				vhSystem +=$(this).attr("vhSystem");
     			lenth +=1;
     	 	});
     		if(vhId==null || vhId ==''){
				alert('请先选择一项');
				return false;
			}
     		/*
     		else if(lenth>1){
				alert('只能选择一项');
				return false;
			}
     		*/
     	api.get("convertPage").getVmIpAddr(vhId,vhName,vhIp,vhCpu,vhMem,vhStorage,vhSystem);
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
<body class="scrollbody">
<s:form action="convert_queryVmIpAddr.do" method="post" cssClass="showVmForm" id="showVmForm">
<s:hidden id="EQ_ID" name="showVmForm.EQ_ID"></s:hidden>
<s:hidden id="flag" name="showVmForm.flag"></s:hidden>
<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">虚拟机名称:</td>
                    <td>
						<s:textfield name="showVmForm.queryName" id="queryName"></s:textfield>
                    </td>
                     <td class="til">IP:</td>
                    <td>
						<s:textfield name="showVmForm.queryVHIP" id="queryVHIP"></s:textfield>
                    </td>
                    <td class="til">虚拟化类型:</td>
                    <td>
						<s:select id="queryType" name="showVmForm.queryType" list="#{'0':'--请选择--','1':'VMWARE','3':'XEN','9':'其他'}" onchange="changeHostIp();"></s:select>
                    </td>
                    <td class="til">状态:</td>
                    <td>
						<s:select id="queryState" name="showVmForm.queryState" list="#{'':'--请选择--','0':'已关闭','1':'正在运行','2':'挂起'}"></s:select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="10" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm()" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=showVmForm" />
		</div>
		<div class="table-ct">
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'ip')">IP</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<!-- <th onclick="sort(theTable,3,'string')">应用个数</th> -->
					<th onclick="sort(theTable,4,'string')">网络域</th>
					<th onclick="sort(theTable,5,'int')">CPU(核)</th>
					<th onclick="sort(theTable,6,'int')">内存(G)</th>
					<th onclick="sort(theTable,7,'int')">存储(G)</th>
					<th onclick="sort(theTable,9,'string')">虚拟机状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="showVmForm.resultList" id="theBean">
			  	<tr id="<s:property value="#theBean.VH_UUID"/>" vhid='<s:property value="#theBean.VH_UUID"/>' connID='<s:property value="#theBean.connectId"/>'>
			  		<td>
						<input name="checkboxid" type="checkbox" value="<s:property value="#theBean.VH_ID"/>" 
							vhName='<s:property value = "#theBean.VH_NAME"/>' vhIp='<s:property value="#theBean.VH_IP"/>' 
							vhCpu='<s:property value="#theBean.VH_CPU"/>' vhStorage='<s:property value="#theBean.VH_STORAGE"/>' 
							vhMem='<s:property value="@java.lang.Math@round(#theBean.VH_MEM/1024*100) / 100.0"/>' 
							vhSystem='<s:property value="#theBean.VH_SYSTEM"/>'/>
					</td>
			  		<td width="200"> 
			  			<div class="hidden" title='<s:property value="#theBean.VH_NAME"/>'>
			  				<s:property value="#theBean.VH_NAME"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:property value="#theBean.VH_TYPE"/></td>
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
			  		<td><s:property value="#theBean.NET_NAME" default="-"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/></td>
			  		<td>
			  			<s:if test="%{VH_MEM%1024==0}">
			  				<fmt:formatNumber type="currency" pattern="#0" value="${VH_MEM/1024}"></fmt:formatNumber>
			  			</s:if>
			  			<s:else>
			  				<fmt:formatNumber type="currency" pattern="#0.00" value="${VH_MEM/1024}"></fmt:formatNumber>
						</s:else>
			  		</td>
			  		<td><s:property value="#theBean.VH_STORAGE"/></td>
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
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div style="height:10px;"></div>
			<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
