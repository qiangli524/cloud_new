<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    
    <title>逻辑分区摘要</title>
    
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
	  				<td class="til" width="20%" align="left">虚拟机名称</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="lparObj.lpar_name"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" width="20%" align="left">所属主机名称</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="lparObj.sysname"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" width="20%" align="left">分区类型</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:property value="lparObj.lpar_env"/></td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">版本</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:if test='lparObj.os_version!="Unknown"'>
						<s:property value="lparObj.os_version"/>
					</s:if><s:else>
						无
					</s:else>
					</td>
	  			</tr>
	  			<tr >
					<td class="til" align="left">CPU类型</td>
					<td align="left">&nbsp;&nbsp;
					<s:if test='lparObj.curr_proc_mode!="null"'>
						<s:property value="lparObj.curr_proc_mode" />
					</s:if><s:else>
						无
					</s:else>
					</td>
				</tr> 
	  			<tr >
					<td class="til" align="left">物理CPU数</td>
					<td align="left">&nbsp;&nbsp;
					<s:if test='lparObj.desired_proc_units!="null"'>
						<s:property value="lparObj.desired_proc_units" /> 个
					</s:if><s:else>
						0  个
					</s:else>
					</td>
				</tr> 
	  			<tr >
					<td class="til" align="left">虚拟CPU数</td>
					<td align="left">&nbsp;&nbsp;
					<s:if test='lparObj.desired_procs!="null"'>
						<s:property value="lparObj.desired_procs" /> 个
					</s:if><s:else>
						0  个
					</s:else>
					</td>
				</tr> 
	  			<tr>
	  				<td class="til" align="left">内存</td>
	  				<td align="left">&nbsp;&nbsp;
	  				<s:if test='lparObj.memory_size!="null"'>
						<s:property value="lparObj.memory_size"/> MB
					</s:if><s:else>
						0  MB
					</s:else>
					</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">网卡个数</td>
	  				<td align="left">&nbsp;&nbsp;
						<s:property value="ethCount"/> 个</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">光纤卡个数</td>
	  				<td align="left">&nbsp;&nbsp;
						<s:property value="fcCount"/> 个</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">物理卡个数</td>
	  				<td align="left">&nbsp;&nbsp;
						<s:property value="pcardCount"/> 个</td>
	  			</tr>
	  			<tr>
	  				<td class="til" align="left">scsi个数</td>
	  				<td align="left">&nbsp;&nbsp;
						<s:property value="scsiCount"/> 个</td>
	  			</tr>
			</table>
		</div>
  </body>
</html>
