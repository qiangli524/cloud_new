<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>小型机节点摘要</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/ibm/unitedtree-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<s:hidden name="centerid" id="centerid"></s:hidden>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-datacenter">
       <h2 class="datacenter"></h2>
        <div class="clr"></div>
            <div class="box on">
              <dl class="single">
              	<dt>
              			CPU：总量：<s:property value="ibmManagerTabObj.ibmCpuCount"/> 核
              			已分配: <s:property value="ibmManagerTabObj.ibmCpuUsedCount"/> 核
              			未分配：<s:property value="ibmManagerTabObj.ibmCpuUnUsedCount"/>核
              	</dt>
              	<dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
<%--                        <td><div style="float:left;" class="cpu"></div></td>--%>
                        <td>
                        	<s:if test="ibmManagerTabObj.ibmCpuCount == 0">
                        		<div class="percentage"><b style='width:<s:property value="0"/>%'></b></div>
                        	</s:if>
                        	<s:else>
	                        	<s:if test="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0 >= 75">
		                        	<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0"/>%'></b></div>
	                        	</s:if>
	                        	<s:elseif test="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0 >= 50">
		                        	<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0"/>%'></b></div>
	                        	</s:elseif>
	                        	<s:else>
		                        	<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0"/>%'></b></div>
	                        	</s:else>
                        	</s:else>
                        </td>
                        <td>
                        	<s:if test="ibmManagerTabObj.ibmCpuCount == 0">
                        		0.0%
                        	</s:if>
                        	<s:else>
                        		<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmCpuUsedCount*100*100/ibmManagerTabObj.ibmCpuCount)/100.0"/>%
                        	</s:else>
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		内存：总量：<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemCount/1024) "/> G
              		已分配: <s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount/1024)"/> G
              		未分配：<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUnUsedCount/1024)"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div style="float:left;" class="mem"></div></td>
                        <td>
                        	<s:if test="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0 >= 75">
                        		<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0 >= 50">
                        		<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
                        		<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(ibmManagerTabObj.ibmMemUsedCount*100*100/ibmManagerTabObj.ibmMemCount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
    	<h2 class="cluster"><span><font color="orange" size="5"><s:property value="hmcCount" /></font>&nbsp;个</span></h2>
        <table width="450" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
          <tr>
          		<td>
          			<table>
          				<tr>
          					<td align="left">
	          					<div style="float:left;" class="zhuji"></div>&nbsp;主机总数: 
				             </td>
		      				<td align="right">
				              	<font color="blue" size="4"><s:property value="powerCount"/></font>&nbsp;个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				            </td>
				            <td align="left">
				            	<div style="float:left;" class="xuniji"></div>&nbsp;虚拟机总数: 
				            </td>
		      				<td align="right">
				              	<font color="blue" size="4"><s:property value="lparCount"/></font>&nbsp;个
				             </td>
          				</tr>
          			</table>
          		</td>		
          </tr>
        </table> 
    </td>
  </tr>
</table>
</div>
</s:form>
</body>