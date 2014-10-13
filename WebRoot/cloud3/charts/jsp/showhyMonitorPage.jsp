<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-datacenter" height="100%;">
       <h2 class="vmtitle"></h2>
        <div class="clr"></div>
            <div class="box on">
              <dl class="single">
              	<dt>
              			近一小时的CPU使用率
              	</dt>
              	<dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div style="float:left;" class="cpu"></div></td>
                        <td>
                        	<s:if test="cpuUsedRate == 0">
                        		<div class="percentage"><b style='width:<s:property value="0"/>%'></b></div>
                        	</s:if>
                        	<s:else>
	                        	<s:if test="@java.lang.Math@round(cpuUsedRate) >= 75">
		                        	<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(cpuUsedRate)"/>%'></b></div>
	                        	</s:if>
	                        	<s:elseif test="@java.lang.Math@round(cpuUsedRate) >= 50">
		                        	<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(cpuUsedRate)"/>%'></b></div>
	                        	</s:elseif>
	                        	<s:else>
		                        	<div class="percentage"><b style='width:<s:property value="cpuUsedRate"/>%'></b></div>
	                        	</s:else>
                        	</s:else>
                        </td>
                        <td>
                        	<s:if test="cpuUsedRate == 0">
                        		0.0%
                        	</s:if>
                        	<s:else>
                        		<s:property value="cpuUsedRate"/>%
                        	</s:else>
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		近一小时的内存使用率
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div style="float:left;" class="mem"></div></td>
                        <td>
                        	<s:if test="@java.lang.Math@round(menUsedRate) >= 75">
                        		<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(menUsedRate)"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(menUsedRate) >= 50">
                        		<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(menUsedRate)"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
                        		<div class="percentage"><b style='width:<s:property value="menUsedRate"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="menUsedRate"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		近一小时的磁盘读速度(kb/s)
              	</dt>  
              	<dd>
              		<table width="230" border="0" cellspacing="0" cellpadding="0">
              			<tr>
              				<td>平均值</td>
              				<td>最大值</td>
              			</tr>
              			<td>
              				${monitorObj.diskReadAvg}
              			</td>
              			<td>
              				${monitorObj.diskReadMax}
              			</td>
              		</table>
              	</dd>              
              </dl>    
               <dl class="single">
              	<dt>
              		近一小时的磁盘写速度(kb/s)
              	</dt>  
              	<dd>
              		<table width="230" border="0" cellspacing="0" cellpadding="0">
              			<tr>
              				<td>平均值</td>
              				<td>最大值</td>
              			</tr>
              			<td>
              				${monitorObj.diskWriteAvg}
              			</td>
              			<td>
              				${monitorObj.diskWriteMax}
              			</td>
              		</table>
              	</dd>              
              </dl>      
			  <dl class="single">
              	<dt>
              		近一小时的网络接收速率(kbps)
              	</dt>  
              	<dd>
              		<table width="230" border="0" cellspacing="0" cellpadding="0">
              			<tr>
              				<td>平均值</td>
              				<td>最大值</td>
              			</tr>
              			<td>
              				${monitorObj.netReadAvg}
              			</td>
              			<td>
              				${monitorObj.netReadMax}
              			</td>
              		</table>
              	</dd>              
              </dl>    
               <dl class="single">
              	<dt>
              		近一小时的网络发送速率(kbps)
              	</dt>  
              	<dd>
              		<table width="230" border="0" cellspacing="0" cellpadding="0">
              			<tr>
              				<td>平均值</td>
              				<td>最大值</td>
              			</tr>
              			<td>
              				${monitorObj.netWriteAvg}
              			</td>
              			<td>
              				${monitorObj.netWriteMax}
              			</td>
              		</table>
              	</dd>              
              </dl>                                            
            </div>
    </td>
  </tr>
  
</table>
</div>
</body>