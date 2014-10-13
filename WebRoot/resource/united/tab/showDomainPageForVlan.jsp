<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
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
                        	<div class="percentage">
	                        	<s:if test="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0 >= 75">
		                        	<b class="red" style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b>
	                        	</s:if>
	                        	<s:elseif test="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0 >= 50">
		                        	<b class="ora" style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b>
	                        	</s:elseif>
	                        	<s:else>
		                        	<b style='width:<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%'></b>
	                        	</s:else>
                        	</div>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(ipusedcount*100*100/ipcount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
<%--              <dl class="single">--%>
<%--                <dd>--%>
<%--                	<table width="230" border="0" cellspacing="0" cellpadding="0">--%>
<%--                      <tr>--%>
<%--                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/net.gif" width="16" height="16" /></td>--%>
<%--                        <td>--%>
<%--                        	子域：总量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="orange" size="5"><s:property value="subdomaincount"/></font> 个--%>
<%--                        </td>--%>
<%--                      </tr>--%>
<%--                    </table>--%>
<%--                </dd>--%>
<%--              </dl>--%>
              <dl class="single">
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="10%"><div style="float:left;" class="net"></div></td>
                        <td>VLAN：总量：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="orange" size="5"><s:property value="vlancount"/></font> 个
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
    </td>
  </tr>
</table>

	<div class="clr" style="height: 10px;"></div>
	<h2 class="ip"></h2>
		<ul class="panel-ip">
  			<s:iterator id="theBean" value="netList">
  				<li title="总量:<s:property value="#theBean.allCount"/> 已分配:<s:property value="#theBean.usedCount"/> 未分配:<s:property value="#theBean.freeCount"/>">
		    		<p><s:property value="@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount ) / 100.0"/> %</p>
		    		<p class="percentage2"><b style='height:<s:property value="100-(@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount )/100.0)"/>%'></b></p>
		    		<p><s:property value="#theBean.netName"/></p>
   				</li>
   			</s:iterator>
		</ul>
</div>
</s:form>
</body>