<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.css.CssUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.js.JsUtil" %>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/portal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui.datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_wx.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_check.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
<script type="text/javascript">
		var api = frameElement.api;
		w = api.opener;
		$(function(){
			 api.button({
			     id:'andVm',
			     name: '添加',
			     callback:submitRequest,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '返回'
			 });
	});
		//添加设备到任务表
		function submitRequest(){
	 		var couterNum = 0;
			var checkboxids = document.getElementsByName("theForm.VH_UUID");
			if (checkboxids != null && checkboxids.length > 0) {
				for ( var i = 0; i < checkboxids.length; i++) {
					if (checkboxids[i].checked) {
						couterNum = couterNum + 1;
					}
				}
			} 
			if (couterNum == 0) {
				alert("至少勾选一条信息！");
				return false;
			}else{
				var url = "quartzScheduler_add.do?"+$("#theForm").serialize();
				api.get("addTask").addVm(url);
			}
		}
		$(".query").click(function() {
			if ($(".query-form").is(":visible")) {
				$(".query-form").slideUp("slow");
			} else {
				$(".query-form").slideDown("slow");
			}
		});
	function closeDialog() {
		var theForm = document.getElementById("theForm");
		var eq_id = theForm.EQ_ID.value;
		if (dialogType == "VMWARE") {
			$.dialog.list['showVmwareDetail'].close();
			var theForm = document.getElementById("theForm");
			theForm.action = 'showvm_listvm.do?eq_id' + eq_id;
			theForm.submit();
		} else if (dialogType == "XEN") {
			$.dialog.list['showXenDetail'].close();
			var theForm = document.getElementById("theForm");
			theForm.action = 'showvm_listvm.do?eq_id=' + eq_id;
			theForm.submit();
		}
	}
	function resetForm(theForm) {
		$("#queryName").attr("value", "");
		$("#hostip").attr("value", "");
		$("#queryType").attr("value", '0');
		$("#queryState").attr("value", '');
		$("#queryVHIP").attr("value", "");
	}
	function searchRequest() {
		theForm.submit();
	}

	function createSelect(value, text) {
		var opt = document.createElement("option");
		opt.setAttribute("value", value);
		opt.appendChild(document.createTextNode(text));
		return opt;
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
<body>
<s:form action="showvm_listvm2.do" method="post" cssClass="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">虚拟机名称:</td>
                    <td><s:textfield name="theForm.queryName" id="queryName"></s:textfield></td>
                    <td class="til">IP:</td>
                    <td><s:textfield name="theForm.queryVHIP" id="queryVHIP"></s:textfield></td>
                    <td class="til">虚拟化类型:</td>
                    <td><s:select id="queryType" name="theForm.queryType" list="#{'0':'--请选择--','1':'VMWARE','3':'XEN','9':'其他'}" ></s:select></td>
                    <td class="til">状态:</td>
                    <td><s:select id="queryState" name="theForm.queryState" list="#{'':'--请选择--','0':'已关闭','1':'正在运行','2':'挂起'}"></s:select></td>
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
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th onclick="sort(theTable,0,'string')">选择</th>
					<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,2,'string')">IP</th>
					<th onclick="sort(theTable,3,'string')">虚拟化类型</th>
					<th onclick="sort(theTable,4,'string')">业务</th>
					<th onclick="sort(theTable,5,'string')">网络域</th>
					<th onclick="sort(theTable,6,'string')">虚拟机状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	     <tr>
			  	    	<td>
						 	<input  name="theForm.VH_UUID"  type="checkbox"   value="${theBean.VH_UUID}"/>	
						</td>
			  			<td><s:property value="#theBean.VH_NAME"/></a></td>
			  			<td><s:property value="#theBean.VH_IP"/></td>
			  			<td><s:property value="#theBean.VH_TYPE"/></td>
			  			<td> <s:property value="#theBean.name" default="-"/></td>
			  		    <td><s:property value="#theBean.NET_NAME" default="-"/></td>
			  		<td id="stateText">
			  			<s:if test="#theBean.VH_STAT==1">正在运行</s:if>
						<s:if test="#theBean.VH_STAT==0">已关闭</s:if>
						<s:if test="#theBean.VH_STAT==2">挂起</s:if>
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
