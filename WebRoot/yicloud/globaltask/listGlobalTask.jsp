<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>


<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(theForm){
 		theForm.taskName.value='';
 		theForm.taskType.value='';
 		theForm.taskStatus.value='';
 	}
</script>
</head>
 <style type="text/css">
		div.hidden{
		width:400px;
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
<div class="mainbody">
<s:form action="globaltask_listGlobalTask.do" method="post" id="theForm">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">任务管理</h2>
		<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">任务名称：</label>
				<s:textfield name="taskName" id="taskName" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">任务类型：</label>
				<s:select cssClass="select-1 vm" list="#{'':'--请选择--','1':'应用部署','2':'创建虚拟机'}" name="taskType" id="taskType">
				</s:select>
				<label class="mgl-20 vm">任务状态:</label>
				<s:select cssClass="select-1 vm" list="#{'':'全部','1':'执行中','2':'已完成'}" name="taskStatus" id="taskStatus">
				</s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="mgt-20">
			</div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">任务名称</th>
				   <th onclick="sort(theTable,1,'string')">任务类型</th>
				   <th onclick="sort(theTable,2,'string')">任务状态</th>
				   <th onclick="sort(theTable,3,'string')">进度</th>
				   <th onclick="sort(theTable,4,'string')">任务信息</th>
                   <th onclick="sort(theTable,5,'string')">创建人</th>
                   <th onclick="sort(theTable,6,'date')">创建时间</th>
                   <th onclick="sort(theTable,7,'date')">完成时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td> <s:property value="#theBean.name"/></td>
			  		<td>
			  			<s:if test="#theBean.type==1">
			  				应用部署
			  			</s:if>
			  			<s:elseif test="#theBean.type==2">
			  				创建虚拟机
			  			</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.status==1">
							进行中			  				
			  			</s:if>
			  			<s:else>
			  				已完成
			  			</s:else>
			  		</td>
			  		<td> <s:property value="#theBean.progress"/>%</td>
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.content"/>'>
			  				<s:property value="#theBean.content"/></a>
			  			</div> 
			  		</td>
			  		<td> <s:property value="#theBean.createrName"/></td>
			  		<td> <s:property value="#theBean.createTime"/></td>
			  		<td>
			  			<s:if test="#theBean.endTime==null">
			  				-
			  			</s:if>
			  			<s:else><s:property value="#theBean.endTime"/></s:else>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
	</div>
</s:form>
</div>
</body>
