<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  <link href="/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css">
		<form id="theForm" name="theForm" action="" method="post">
            <div class="mgl-20 mgr-20">
            <table border="0" cellspacing="0" cellpadding="0" class="utable-1">
              <tbody><tr>
                <th>虚拟机名称：</th>
                <td><s:property value="vmObj.VH_NAME"/></td>
                 <th>虚拟化类型：</th>
                <td>
                <s:if test="vmObj.VH_TYPE==1">&nbsp;&nbsp;vmware</s:if>
	  					<s:else>&nbsp;&nbsp;xen</s:else>
	  			</td>
              </tr>
              <tr>
              	<th>虚拟机操作系统：</th>
                <td><s:property value="vmObj.VH_SYSTEM"/></td>
                <th>vCPU：</th>
                <td><s:property value="vmObj.VH_CPU" />个</td>
              </tr>
              <tr>
              	<th>内存：</th>
                <td><fmt:formatNumber value="${(vmObj.VH_MEM/1024)}" pattern="#,###.##" type="number"/>GB</td>
                <th>存储：</th>
                <td><fmt:formatNumber value="${(vmObj.VH_STORAGE/1024)}" pattern="#,###.##" type="number"/>GB</td>
              </tr>
              <tr>
              	<th>IP地址：</th>
                <td><s:property value="vmObj.VH_IP"/></td>
                <th>DNS名称：</th>
                <td><s:property value="vmObj.DNS"/></td>
              </tr>
              <tr>
              	<th>业务系统：</th>
                <td><s:if test="vmObj.centerid==null">
	  				  ---
	  				</s:if>
	  				<s:else>
	  				<s:property value="vmObj.centerid"/>
	  				</s:else>
	  				</td>
                <th>负责人：</th>
                <td><s:if test="vmObj.USER_ID==null">
	  				  ---
	  				</s:if>
	  				<s:else>
	  				<s:property value="vmObj.USER_ID"/>
	  				</s:else></td>
              </tr>
              <tr>
              	<th>业务状态：</th>
                <td>
	  				<s:if test="vmObj.BUSI_STATUS==1">
	  				  上线
	  				</s:if>
	  				<s:else>
	  				 已分配
	  				</s:else></td>
                <th>&nbsp;</th>
                <td>&nbsp;</td>
              </tr>
            </tbody></table>
            </div>
    	</form>
  </body>
</html>
