<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	var win;
	function mount(uuid){
		win = window.open('tree_mountPage.do?vm_code='+uuid,'newwindow','height=200,width=400,top=250,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') ;
	}
	function receiveVallue(){
		win.close();
	}
</script>
</head>
<body>
	<s:form action="tree_getVMList.do" method="post" cssStyle="theForm"
		id="theForm">
		<div class="blue-wrap noborder">
			<div class="table-head">
				<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theForm" />
				<div class="table-ct">
					<table width="100%" class="blue-table sorttable" border="0" id="theTable"
						cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">
									虚拟机名称
								</th>
								<th onclick="sort(theTable,1,'string')">
									内存(MB)
								</th>
								<th onclick="sort(theTable,2,'string')">
									CPU
								</th>
								<th onclick="sort(theTable,3,'string')">
									存储(MB)
								</th>
								<th onclick="sort(theTable,4,'string')">
									操作系统
								</th>
								<th onclick="sort(theTable,5,'string')">
									IP地址
								</th>
								<th>
									挂载镜像
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td>
										<s:if test="#theBean.VH_NAME==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_NAME" />
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.VH_MEM==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_MEM" />
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.VH_CPU==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_CPU" />
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.VH_STORAGE==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_STORAGE" />
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.VH_SYSTEM==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_SYSTEM" />
										</s:else>
									</td>
									<td>
										<s:if test="#theBean.VH_IP==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_IP" />
										</s:else>
									</td>
									<td>
										<input type = "button" class="thickbox btn-style02" value = "挂载" onclick = "javascript:mount('<s:text name="#theBean.VH_UUID" />')" />
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
