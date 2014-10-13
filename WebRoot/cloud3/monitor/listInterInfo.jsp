<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
	<title></title>
	<script type="text/javascript">
	
	function getDetail(inter_id){
		$.dialog({
					id:inter_id+"",
					title:'接口监控详情  '+inter_id,
					width: '970px',
					height: '515px',
					content: 'url:inter_getDetail.do?inter_id='+inter_id});
	}
</script>
</head>
<body>
	<s:form  method="post" cssStyle="theForm"
		id="theForm">
			<div class="table-ct" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<thead>
						<tr>
									<th onclick="sort(theTable,0,'string')">
										IP
									</th>
									<th onclick="sort(theTable,1,'string')">
										端口
									</th>
									<th onclick="sort(theTable,2,'string')">
										类型
									</th>
									<th onclick="sort(theTable,3,'string')">
										链接
									</th>
									<th onclick="sort(theTable,4,'string')">
										接口状态
									</th>
									<th>查看详情</th>
										</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.inter_ip" />
										</td>
										<td>
												<s:property value="#theBean.inter_port" />
										</td>
										
										<td>
										<s:if test="#theBean.inter_type==1">Vmvare</s:if>
										<s:elseif test="#theBean.inter_type==2">Cloud OS</s:elseif>
										<s:elseif test="#theBean.inter_type==3">IBM</s:elseif>
										<s:elseif test="#theBean.inter_type==4">防篡改</s:elseif>
										<s:else>Array</s:else>
										<td>
										<s:property value="#theBean.inter_url"/>
										</td>
										<td width="20px">
											<s:if test="#theBean.inter_state==2"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/dot_.gif" /></s:if>
											<s:elseif test="#theBean.inter_state==1"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/dot_2.gif" /></s:elseif>
											<s:else><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/dot_red.gif" /></s:else>
										</td>
										<td>
										<a href="javascript:getDetail('<s:property value="#theBean.id"/>')">详情</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
	</s:form>
</body>
</html:html>
