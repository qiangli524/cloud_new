<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
<title></title>
<script type="text/javascript">
	function resetForm(theForm){
		theForm.name.value='';
		theForm.hostIP.value='';
	}
	function searchRequest(){
		theForm.submit();
	}
	function addRequest(){
		$.dialog({
					id:"add",
					title:'增加日志信息',
					width: '500px',
					height: '300px',
					lock:true,
					content: 'url:hostLog_addLog.do',
				});
	}
	function modRequest(){
		var id = $(":checkbox[checked='checked']").val();
		if(id == null || id == ''){
			alert("请选择要修改的日志对象!");
			return false;
		}
		$.dialog({
			id:"updateLog",
			title:'修改日志信息',
			width: '500px',
			height: '300px',
			lock:true,
			content: 'url:hostLog_updateLog.do?id='+id,
		});
	}
	function delRequest(){
		var id = $(":checkbox[checked='checked']").val();
		if(id == null || id == ''){
			alert("请选择要删除的日志对象!");
			return false;
		}
		theForm.logId.value = id;
		theForm.action = "hostLog_deleteLog.do";
		$.dialog.confirm('你确定要删除该日志对象吗？', function(){
   			 	$.dialog.tips('执行删除操作');
   			 	theForm.submit();
			}, function(){
    			//$.dialog.tips('执行取消操作');
		});
	}
	$(function(){
		$check = $(":checkbox");
		$check.click(function(){
			$check.not(this).attr("checked",false);
		});
	})
	function formSubmit(api,theForm){
		theForm.action='hostLog_saveLogInfo.do';
		theForm.submit();
		api.close();
	}
	$(function(){
		$("a[name='path']").click(function(data){
			if(confirm("连接主机查看日志?")){
				id = $(this).attr("logid");	
				type = $(this).attr("logtype");
				if(type === "1"){//文件
					$.dialog({
						title:'查看日志',
						width: '810px',
						height: '450px',
						cache:false,
						lock:true,
						content: 'url:hostLog_getLogInfo.do?id='+id,
					});
				}else if(type === "2"){//文件夹
					$.dialog({
						title:'查看日志',
						width: '810px',
						height: '450px',
						cache:false,
						lock:true,
						content: 'url:hostLog_getLogMenu.do?id='+id,
					});
				}
			}
		});
	})
	function test(){alert(111);}
</script>
  <style type="text/css">
		div.hidden{
		width:170px;
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
</head>
<body onLoad="self.focus();document.theForm.name.focus()">
<s:form action="hostLog_listLog" method="post" id="theForm">
<s:hidden id="logId" name="theForm.id"></s:hidden>
<div class="mainbody">
		<s:form action="ip_listIpInfo.do" method="post" cssStyle="theForm"
			id="theForm">
			<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
			<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
			<s:hidden name="theForm.IPADDRESS" id="IPADDRESS"></s:hidden>

			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">主机日志管理</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="vm">日志名称：</label>
							<s:textfield name="theForm.name" cssClass="txt" id="name"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">主机：</label>
							<s:textfield name="theForm.hostIP" cssClass="txt" id="hostIP"></s:textfield>
						</div>
						
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button"
								onclick="javascript:searchRequest()" value="查询" />
							</span> <span class="ubtn-2 mgl-20"><input type="button"
								onclick="javascript:resetForm(document.getElementById('theForm'))"
								value="重置" />
							</span>
						</div>
					</div>
				</div>
				<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)"
							onclick="addRequest();return false;">添加</a>
						<a class="icon-modify" href="javascript:void(0)"
							onclick="modRequest();return false;">修改</a> 
						<a class="icon-del" href="javascript:void(0)" 
							onclick="delRequest();return false;">删除</a>
				</div>
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th>选择</th>
								<th onclick="sort(theTable,1,'string')">日志文件名称</th>
								<th onclick="sort(theTable,2,'string')">所在主机</th>
								<th onclick="sort(theTable,3,'string')">文件/文件夹</th>
								<th onclick="sort(theTable,4,'string')">路径</th>
								<th onclick="sort(theTable,5,'string')">描述</th>
								<th onclick="sort(theTable,6,'date')">修改时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.resultList" id="theBean">
								<tr>

									<td> <input type="checkbox" value="<s:property value='#theBean.id'/>"/> </td>
									<td> <s:property value="#theBean.name"/> </td>
									<td> <s:property value="#theBean.hostIP"/> </td>
									<td>
										<s:if test="#theBean.type==1">文件</s:if>
										<s:elseif test="#theBean.type==2">文件夹</s:elseif>
									</td>
									<td width="170"> 
										<div class="hidden" title='<s:property value="#theBean.path"/>'>
											<a href="javascript:;" name="path" logid="<s:property value='#theBean.id'/>" logtype="<s:property value='#theBean.type'/>"><s:property value="#theBean.path"/></a>
										</div> 
									</td>
									<td> <s:property value="#theBean.description"/> </td>
									<td> <s:property value="#theBean.insDate"/> </td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
				</div>
		</s:form>
	</div>
</s:form>
</body>
