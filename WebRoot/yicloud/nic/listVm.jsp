<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
</head>
<body>
<s:form action="#" method="post" id="theForm" cssClass="theForm">

<div class="tit-zzi">
		<div id="zi"><s:property value="#request.vssName"/>链接的虚拟机</div>
</div>
		<div class="scrollbody" style="overflow: auto; height: 450px">
			<div class="table-ct">
				<table width="100%" class="blue-table sorttable" border="0" id="theTable"
					cellspacing="0">
					<thead>
						<tr>
							<th onclick="sort(theTable,0,'string')">
								虚拟机名称
							</th>
							<th onclick="sort(theTable,1,'string')">
								虚拟化类型
							</th>
							<th onclick="sort(theTable,2,'string')">
								操作系统
							</th>
							<th onclick="sort(theTable,3,'string')">
								CPU
							</th>
							<th onclick="sort(theTable,4,'string')">
								内存
							</th>
							<th onclick="sort(theTable,5,'string')">
								存储
							</th>
							<th onclick="sort(theTable,6,'string')">
								IP
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="theForm.resultList" id="theBean">
							<tr>
								<td>
									<s:property value="#theBean.VH_NAME" />
								</td>
								<td>
									<s:property value="#theBean.VH_TYPE" />
								</td>
								<td>
									<s:property value="#theBean.VH_SYSTEM" />
								</td>
								<td>
									<s:property value="#theBean.VH_CPU" />
									核
								</td>
								<td>
									<s:property value="#theBean.VH_MEM" />
									M
								</td>
								<td>
									<s:property value="#theBean.VH_STORAGE" />
									G
								</td>
								<td>
									<s:property value="#theBean.VH_IP" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			
			<div>
			<table width="100%" border="0">
				<tr>
					<td colspan="8" class="btns" align="center">
						<input type="button" class="thickbox btn-style02" value="返回" name="goback" onclick="window.history.back()" />
					</td>
				</tr>
			</table>
		</div>
		</div>
		
	</s:form>
</body>
