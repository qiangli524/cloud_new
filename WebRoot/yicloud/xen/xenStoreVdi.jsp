<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
</head>
<body>
	<s:form action="xen_hostInfo" method="post" id="theForm" cssStyle="theForm">
			<div class="table-ct" style="overflow:auto;height: 380px;width: 800px">
				<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				<thead>
					<tr>
						<th>
							名称
						</th>
						<th>
							虚拟机
						</th>
						<th>
							大小
						</th>
						<th>
							说明
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td style="width: 200px">
							  <s:if test="#theBean.name==''">
							    -
							  </s:if>
							  <s:else>
								<s:property value="#theBean.name" default="-" />
							  </s:else>
							</td>
							<td style="width: 200px">
							  <s:if test="#theBean.vm_name==''">
							    -
							  </s:if>
							  <s:else>
								<s:property value="#theBean.vm_name" default="-" />
							  </s:else>
							</td>
							<td style="width: 30px">
							  <s:if test="#theBean.sr_size==''">
							    -
							  </s:if>
							  <s:else>
								<s:property value="#theBean.sr_size" default="-" />
								G
							  </s:else>
							</td>
							<td style="width: 200px">
							  <s:if test="#theBean.sr_desc==''">
							    -
							  </s:if>
							  <s:else>
								<s:property value="#theBean.sr_desc" default="-" />
							  </s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:form>
</body>
