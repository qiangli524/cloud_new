<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">

   $("input").click(function(){
     alert("您的操作暂不支持...");
	   });

</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
	<s:hidden name="#theBean.store_uuid" id="store_uuid"/>
	<div class="blue-wrap noborder" style="overflow: auto;width: 800px;">
			<div class="table-head">
		 	 <ul class="btns" style="height: 30px;" >
				<li><input type="button" class="thickbox btn-style02" value="创建绑定" name="createBond"/></li>
                <li><input type="button" class="thickbox btn-style02" value="删除绑定" name="deleteBond"/></li>
                <li><input type="button" class="thickbox btn-style02" value="重新扫描" name="rescan"/></li>
			</ul>
			</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">NIC</th>
						<th onclick="sort(theTable,1,'string')">MAC</th>
						<th onclick="sort(theTable,2,'string')">链接状态</th>
						<th onclick="sort(theTable,3,'string')">速度</th>
						<th onclick="sort(theTable,4,'string')">双工</th>
						<th onclick="sort(theTable,5,'string')">供应商</th>
						<th onclick="sort(theTable,6,'string')">设备</th>
						<th onclick="sort(theTable,7,'string')">PCI总线路径</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td>
							<s:property value="#theBean.nicName"/>
							</td>
							<td>
							<s:property value="#theBean.mac"/>
                            </td>
							<td>
							<s:if test="#theBean.connState==true">
							  已链接
							</s:if>
							<s:else>
							 已断开链接
							</s:else>
                            </td>
							<td >
							<s:if test="#theBean.duplex==true">
							 <s:property value="#theBean.speed"/> Mbit/s
							</s:if>
							<s:else>
							 -
							</s:else>
                            </td>
							<td>
							<s:if test="#theBean.duplex==true">
							  全
							</s:if>
							<s:else>
							 -
							</s:else>
							</td>
							<td>
							<s:property value="#theBean.vendorName"/>
							</td>
							<td>
							<s:property value="#theBean.deviceName"/>
							</td>
							<td>
							<s:property value="#theBean.pciBusPath"/>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>
</s:form>
</body>
