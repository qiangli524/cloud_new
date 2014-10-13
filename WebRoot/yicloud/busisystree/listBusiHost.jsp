<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
 	<script type="text/javascript">
		$(function(){
			$("#searchForm").click(function(){
				$("#theForm").submit();
			});
			
			$("#resetForm").click(function(){
				$("#hostname").val("");
				$("#vlanip").val("");
			});
			
		});
	</script>
</head>
<body>
<s:form action="busiareainfo_listBusiHost.do" method="post" id="theForm" cssStyle="theForm" >
<s:hidden name="busiHostObj.WORKSTATUS"></s:hidden>
	<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		 <td class="til">主机名称:</td>
		                  		 <td>
		                  		 	<s:textfield name="busiHostObj.HOSTNAME" id="hostname" cssClass="txt"></s:textfield>
		                  		 </td>
		                  		 <td class="til">服务IP:</td>
		                  		 <td>
		                  		 	<s:textfield name="busiHostObj.VLANIP" id="vlanip" cssClass="txt"></s:textfield>
		                  		 </td>
		                  </tr>
		                  <tr>
		                    <td colspan="8" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
				</div>
				
				<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
							<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							  <thead>
							  		<tr>
										<th onclick="sort(theTable,0,'string')">主机名</th>
										<th onclick="sort(theTable,1,'string')">管理IP</th>
										<th onclick="sort(theTable,2,'string')">内存</th>
										<th onclick="sort(theTable,3,'string')">服务IP</th>
										<th onclick="sort(theTable,4,'string')">运行状态</th>
									</tr>
				  			</thead>
				  			<tbody>
						  		<s:iterator value="resultList" id="theBean">
						  			<tr>
						  				<td><s:property value="#theBean.HOSTNAME" />
													</td>
						  				<td><s:property value="#theBean.IP" />
													</td>
						  				<td><s:property value="#theBean.MEMORY" />
													</td>
										<td><s:property value="#theBean.VLANIP" />
													</td>
										<td><s:if test="#theBean.WORKSTATUS==1">正在运行</s:if> <s:else>已停止</s:else>
													</td>
						  			</tr>
						  		</s:iterator>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
