<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtreenet-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-datacenter">
       <%--<h2 class="datacenter"></h2>
        <div class="clr"></div>--%>
            <div class="box on">
            <dl class="single">
              	<dt>
              		IP：总量：<s:property value="ipcount"/> 个
              		已分配: <s:property value="ipusedcount"/> 个
              		未分配：<s:property value="ipcount-ipusedcount"/> 个
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="10%"><div style="float:left;" class="ippng"></div></td>
                        <td>
                        	<s:if test="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0 >= 75">
	                        	<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0 >= 50">
	                        	<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
	                        	<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            <div class="clr" style=" height: 40px;"></div>
              <dl class="single">
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                         <td width="10%"><div style="float:left;" class="domain"></div></td>
                        <td>
                        	网络域：总量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="orange" size="5"><s:property value="domaincount"/></font> 个
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <div class="clr" style=" height: 40px;"></div>
              <dl class="single">
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="10%"><div style="float:left;" class="subdomain"></div></td>
                        <td>
                        	子域：总量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="orange" size="5"><s:property value="subdomaincount"/></font> 个
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <div class="clr" style=" height: 40px;"></div>
              <dl class="single">
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="10%"><div style="float:left;" class="network"></div></td>
                        <td>
                        	VLAN：总量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="orange" size="5"><s:property value="vlancount"/></font> 个
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
    </td>
  </tr>
</table>
</div>
</s:form>
</body>