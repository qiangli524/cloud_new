<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    
    <title>虚拟机摘要</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  <div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
	  			<tr>
	  				<td class="til" width="20%" align="left">所属主机名称</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="vmObj.EQ_NAME"/></td>
	  			</tr>
				<tr>
	  				<td class="til" width="20%" align="left">虚拟机名称</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="vmObj.VH_NAME"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">虚拟机操作系统</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="vmObj.VH_SYSTEM"/></td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">CPU(个)</td>
					<td align="left">&nbsp;&nbsp;
					<s:property value="vmObj.VH_CPU" /> 个</td>
				</tr> 
	  			<tr>
	  				<td class="til" align="left">内存</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="vmObj.VH_MEM"/> MB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">存储</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="@java.lang.Math@round(vmObj.VH_STORAGE/1024)"/> GB</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">IP地址</td>
	  				<td align="left">&nbsp;&nbsp;
		  				<s:if test='vmObj.VH_IP!= null && vmObj.VH_IP!="" && vmObj.VH_IP!="null" '>
							<s:property value="vmObj.VH_IP"/>
						</s:if><s:else>
							无
						</s:else>
					</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">DNS名称</td>
	  				<td align="left">&nbsp;&nbsp;
	  					<s:if test='vmObj.DNS!= null && vmObj.DNS!="" && vmObj.DNS!="null" '>
							<s:property value="vmObj.DNS"/>
						</s:if><s:else>
							无
						</s:else>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">业务系统</td>
	  				<td align="left">&nbsp;&nbsp;
	  					<s:if test='vmObj.CONNECT_ID!= null && vmObj.CONNECT_ID!="" && vmObj.CONNECT_ID!="null" '>
							<s:property value="vmObj.CONNECT_ID"/>
						</s:if><s:else>
							无
						</s:else>
	  				</td>
	  			</tr>
	  			<%--<tr>
	  				<td class="til" align="left">负责人</td>
	  				<td align="left">&nbsp;&nbsp;
	  					<s:if test='vmObj.centerid!= null && vmObj.centerid!="" && vmObj.centerid!="null" '>
							<s:property value="vmObj.USER_ID"/>
						</s:if><s:else>
							无
						</s:else>
	  				</td>
	  			</tr>
	  			--%><tr>
	  				<td class="til" align="left">业务状态</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:if test="vmObj.BUSI_STATUS==1">
	  				  预占
	  				</s:if>
	  				<s:elseif test="vmObj.BUSI_STATUS==2">
	  				  生产  
	  				</s:elseif>
	  				<s:elseif test="vmObj.BUSI_STATUS==3">
	  				  测试  
	  				</s:elseif>
	  				<s:else>
	  				  未知  
	  				</s:else>
	  				</td>
	  			</tr>	  			
			</table>
		</div>
  </body>
</html>
