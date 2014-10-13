<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>



<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>	
</head>
<body>
<s:form >
<div class="scrollbody">
	<div class="box on">
		<div class="blue-wrap noborder">
			<div class="table-ct">
				<!-- vmware的网络情况 -->
				<s:if test="vtype == 1"> 
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
					  <tr>
					  	<th onclick="sort(theTable,0,'string')">端口组名称</th>
						<th onclick="sort(theTable,1,'string')">VLan</th>
						<th onclick="sort(theTable,2,'string')">虚拟交换机名称</th>
					  </tr>
				  </thead>
				  <tbody>
				  		<s:iterator value="resultList" id="theBean">
				  			<tr>
				  				<td align="center">
										<s:property value="#theBean.name" default="-"/>
								</td>
								<td align="center">
										<s:property value="#theBean.vlanId" default="-"/>
								</td>
								<td align="center">
										<s:property value="#theBean.vSwitchName" default="-"/>
								</td>
				  			</tr>
				  		</s:iterator>
				  </tbody>
				</table>
				</s:if>
				<s:elseif test="vtype == 2">
				<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">说明</th>
						<th onclick="sort(theTable,2,'string')">NIC</th>
						<th onclick="sort(theTable,3,'string')">VLAN</th>
						<th onclick="sort(theTable,4,'string')">自动</th>
						<th onclick="sort(theTable,5,'string')">链接状态</th>
						<th onclick="sort(theTable,6,'string')">MAC</th>
					</tr>
				</thead>
				<tbody>
						<s:iterator value="resultList" id="theBean">
							<tr class="<s:property value="#theBean.networkUuid"/>">
								<td><s:property value="#theBean.networkName"/></td>
								<td><s:property value="#theBean.networkDesc"/></td>
	                            <td><s:if test="theBean.nicName=='' ||　theBean.nicName==undefined"></s:if><s:else><s:property value="#theBean.nicName"/></s:else></td>
								<td ><s:if test="#theBean.vlanId==-1 || #theBean.vlanId== 0">-</s:if><s:else><s:property value="#theBean.vlanId"/></s:else></td>
								<td><s:if test="#theBean.automatic==true">是</s:if><s:else>否</s:else></td>
								<td>
								<s:if test="#theBean.connState==true">
								  已链接
								</s:if>
								<s:else>
								 已断开链接
								</s:else>
	                            </td>
								<td>
								<s:property value="#theBean.mac"/>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>	
				</s:elseif>			
			</div>
		</div>
    </div>
</div>
</s:form>
</body>
