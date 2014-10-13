<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>
<title>无标题文档</title>
<link
	href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</head>
<body>
	<s:form action="" method="post" id="theForm">
		<div>
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td>
							<ul class="dc-list">
								<li><i class="dccq"></i><span><font
										style="font-weight:bold;font-size:16px;color:#444444">数据中心
								</span><span class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.dc_all_count" /></font>
								</span></li>

								<li><i class="clustercq"></i><span><font
										style="font-weight:bold;font-size:16px;color:#444444"> 集群
								</span><span class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.cluster_all_count" /></font>
								</span></li>

								<li><i class="hostcq"></i><span><font
										style="font-weight:bold;font-size:16px;color:#444444">已分配主机
								</span><span class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.host_allocated_count" /></font>
								</span></li>
								<li><i class="vmcq"></i><span><font
										style="font-weight:bold;font-size:16px;color:#444444">虚拟机
								</span><span class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_all_count" /></font>
								</span></li>
							</ul>
							<ul class="dc-list">
								<li><i class="vmware"></i><span class="txt">vmware</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_vmware_count"/></font>
								</span></li>
								<li><i class="running"></i><span class="txt">正常</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_vmware_run_count"/></font>
								</span></li>
								<li><i class="stop"></i><span class="txt">停止</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_vmware_stop_count"/></font>
								</span></li>
								<li><i class="vmware-unusual"></i><span class="txt">异常
										</span> <span class="num red-num"> <font
										style="font-weight:bold;color:#0066ff"><s:property value="map.vmware_serious_count"/></font> </span></li>
								</br>
								<li><i class="xen"></i><span class="txt">xen</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_xen_count"/></font>
								</span></li>
								<li><i class="running"></i><span class="txt">正常</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff"><s:property value="map.vm_xen_run_count"/></font>
								</span></li>
								<li><i class="stop"></i><span class="txt">停止</span><span
									class="num red-num"><font style="font-weight:bold;color:#0066ff;"><s:property value="map.vm_xen_stop_count"/></font>
								</span></li>
								<li><i class="xen-unusual"></i><span class="txt">异常
										</span> <span class="num red-num"> <font
										style="font-weight:bold;color:#0066ff"><s:property	value="map.xen_serious_count" /></font> </span></li>
								</br>
							</ul>
					</tr>
				</tbody>
			</table>
		</div>
	</s:form>
</body>