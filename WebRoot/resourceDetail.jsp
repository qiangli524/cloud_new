<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/sxcloud/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<s:form action="function_saveFunctions" method="post"  id="theForm">

<div style="width: 880px;" align="center">
<dl class="single2">
	<dt class="zj">
		<s:if test="theForm.host_all_num!=null && ''!=theForm.host_all_num">
	            <s:property value="theForm.host_all_num"/>
            </s:if><s:else>
            	0
            </s:else>
	</dt>
    <dd>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="name">XCP系统:</td>
            <td class="yellow">
            <s:if test="theForm.host_x_num!=null && ''!=theForm.host_x_num ">
	            <s:property value="theForm.host_x_num"/>
            </s:if><s:else>
            	0
            </s:else>
            </td>
          </tr>
          <tr>
            <td class="name">ESXI系统:</td>
            <td class="yellow">
            <s:if test="theForm.host_v_num!=null && ''!=theForm.host_v_num">
	          <s:property value="theForm.host_v_num"/>
            </s:if><s:else>
            	0
            </s:else>
            </td>
          </tr>
          <tr>
            <td class="name">小型机:</td>
            <td class="yellow">0</td>
          </tr>
          <tr>
            <td class="name">LINUX系统:</td>
            <td class="yellow">0</td>
          </tr>
          <tr>
            <td class="name">其他:</td>
            <td class="yellow">0</td>
          </tr>
        </table>
    </dd>
</dl>
<dl class="single2">
	<dt class="xnj">
		<s:if test="theForm.vm_all_num!=null && ''!=theForm.vm_all_num">
         	 <s:property value="theForm.vm_all_num"/>
         </s:if><s:else>
         	0
         </s:else>
	</dt>
    <dd>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="name">XEN个数:</td>
            <td class="yellow">
            <s:if test="theForm.vm_x_num!=null && ''!=theForm.vm_x_num">
	         	 <fmt:formatNumber value="${(theForm.vm_x_num)}" pattern="#,###.##" type="number"/>
            </s:if><s:else>
            	0
            </s:else>
            </td>
          </tr>
          <tr>
            <td class="name">VMWARE个数:</td>
            <td class="yellow">
             <s:if test="theForm.vm_v_num!=null && ''!=theForm.vm_v_num">
	         	 <s:property value="theForm.vm_v_num"/>
            </s:if><s:else>
            	0
            </s:else>
            </td>
          </tr>
          <tr>
            <td class="name">POWER VM个数:</td>
            <td class="yellow">0</td>
          </tr>
          <tr>
            <td class="name">KVM个数:</td>
            <td class="yellow">0</td>
          </tr>
          <tr>
            <td class="name">其他个数:</td>
            <td class="yellow">
             <s:if test="theForm.vm_other!=null && ''!=theForm.vm_other ">
	           <s:property value="theForm.vm_other"/>
            </s:if><s:else>
            	0
            </s:else>
            </td>
          </tr>
        </table>
    </dd>
</dl>
<dl class="single2">
	<dt class="cc">
		<s:if test="theForm.store_all!=null && ''!=theForm.store_all">
         	<fmt:formatNumber value="${(theForm.store_all)/1024}" pattern="#,###.##" type="number"/>T
         </s:if><s:else>
         	0T
         </s:else>
	</dt>
	
    <dd>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="name">XEN总量:</td>
            <td class="yellow">
            	<s:if test="theForm.x_store!=null && ''!=theForm.x_store ">
	           		<fmt:formatNumber value="${(theForm.x_store)/1024}" pattern="#,###.##" type="number"/>T
            	</s:if><s:else>
            		0G
           		</s:else>
            </td>
<%--            <td class="gray">--%>
<%--            	<s:if test="theForm.x_store_free!=null && ''!=theForm.x_store_free ">--%>
<%--	           		<fmt:formatNumber value="${(theForm.x_store_free)/1024}" pattern="#,###.##" type="number"/>T--%>
<%--            	</s:if><s:else>--%>
<%--            		0T--%>
<%--           		</s:else>--%>
<%--            </td>--%>
          </tr>
          <tr>
            <td class="name">VMWARE总量:</td>
            <td class="yellow">
            <s:if test="theForm.v_store!=null && ''!=theForm.v_store ">
	           		<fmt:formatNumber value="${(theForm.v_store)/1024}" pattern="#,###.##" type="number"/>T
            	</s:if><s:else>
            		0T
           		</s:else></td>
<%--            <td class="gray">--%>
<%--            	 <s:if test="theForm.v_store_free!=null && ''!=theForm.v_store_free">--%>
<%--	           		<fmt:formatNumber value="${(theForm.v_store_free)/1024}" pattern="#,###.##" type="number"/>T--%>
<%--            	</s:if><s:else>--%>
<%--            		0T--%>
<%--           		</s:else></td>--%>
          </tr>
          <tr>
            <td class="name">POWER VM总量:</td>
            <td class="yellow">0T</td>
<%--            <td class="gray">0T</td>--%>
          </tr>
          <tr>
            <td class="name">KVM总量:</td>
            <td class="yellow">0T</td>
<%--            <td class="gray">0T</td>--%>
          </tr>
          <tr>
            <td class="name">其他总量:</td>
            <td class="yellow">0T</td>
<%--            <td class="gray">0T</td>--%>
          </tr>
        </table>
    </dd>
</dl>
<dl class="single2">
	<dt class="cpu">
		<s:if test="theForm.cpu_all!=null && ''!=theForm.cpu_all">
         	<fmt:formatNumber value="${(theForm.cpu_all)}" pattern="#,###.##" type="number"/>
         </s:if><s:else>
         	0
         </s:else>
	</dt>
    <dd>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="name">XEN个数:</td>
            <td class="yellow">
            	 <s:if test="theForm.x_cpu!=null && ''!=theForm.x_cpu">
	           		<fmt:formatNumber value="${(theForm.x_cpu)}" pattern="#,###.##" type="number"/>
            	</s:if><s:else>
            		0
           		</s:else>
            </td>
<%--            <td class="gray">--%>
<%--            	<s:if test="theForm.x_cpu_free!=null && ''!=theForm.x_cpu_free">--%>
<%--	           		<fmt:formatNumber value="${(theForm.x_cpu_free)}" pattern="#,###.##" type="number"/>--%>
<%--            	</s:if><s:else>--%>
<%--            		0--%>
<%--           		</s:else>--%>
<%--            </td>--%>
          </tr>
          <tr>
            <td class="name">VMWARE个数:</td>
             <td class="yellow">
            	 <s:if test="theForm.v_cpu!=null && ''!=theForm.v_cpu">
	           		<fmt:formatNumber value="${(theForm.v_cpu)}" pattern="#,###.##" type="number"/>
            	</s:if><s:else>
            		0
           		</s:else>
            </td>
<%--            <td class="gray">--%>
<%--            	<s:if test="theForm.v_cpu_free!=null && ''!=theForm.v_cpu_free">--%>
<%--	           		<fmt:formatNumber value="${(theForm.v_cpu_free)}" pattern="#,###.##" type="number"/>--%>
<%--            	</s:if><s:else>--%>
<%--            		0--%>
<%--           		</s:else>--%>
<%--            </td>--%>
          </tr>
          <tr>
            <td class="name">POWER VM个数:</td>
            <td class="yellow">0</td>
<%--            <td class="gray">0</td>--%>
          </tr>
          <tr>
            <td class="name">KVM个数:</td>
            <td class="yellow">0</td>
<%--            <td class="gray">0</td>--%>
          </tr>
          <tr>
            <td class="name">其他个数:</td>
            <td class="yellow">0</td>
<%--            <td class="gray">0</td>--%>
          </tr>
        </table>
    </dd>
</dl>
<dl class="single2">
	<dt class="nc">
		<s:if test="theForm.mem_all!=null && ''!=theForm.mem_all">
         	<fmt:formatNumber value="${(theForm.mem_all)}" pattern="#,###.##" type="number"/>G
         </s:if><s:else>
         	0G
         </s:else>
	</dt>
    <dd>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="name">XEN总量:</td>
            <td class="yellow">
            	 <s:if test="theForm.x_mem!=null && ''!=theForm.x_mem">
	           		<fmt:formatNumber value="${(theForm.x_mem)}" pattern="#,###.##" type="number"/>G
            	</s:if><s:else>
            		0G
           		</s:else>
            </td>
<%--            <td class="gray">--%>
<%--            	<s:if test="theForm.x_mem_free!=null && ''!=theForm.x_mem_free">--%>
<%--	           		<fmt:formatNumber value="${(theForm.x_mem_free)}" pattern="#,###.##" type="number"/>G--%>
<%--            	</s:if><s:else>--%>
<%--            		0G--%>
<%--           		</s:else>--%>
<%--            </td>--%>
          </tr>
          <tr>
            <td class="name">VMWARE总量:</td>
            <td class="yellow">
            	 <s:if test="theForm.v_mem!=null && ''!=theForm.v_mem">
	           		<fmt:formatNumber value="${(theForm.v_mem)}" pattern="#,###.##" type="number"/>G
            	</s:if><s:else>
            		0G
           		</s:else>
            </td>
<%--            <td class="gray">--%>
<%--            	<s:if test="theForm.v_mem_free!=null && ''!=theForm.v_mem_free">--%>
<%--	           		<fmt:formatNumber value="${(theForm.v_mem_free)}" pattern="#,###.##" type="number"/>G--%>
<%--            	</s:if><s:else>--%>
<%--            		0G--%>
<%--           		</s:else>--%>
<%--            </td>--%>
          </tr>
          <tr>
            <td class="name">POWER VM总量:</td>
            <td class="yellow">0G</td>
<%--            <td class="gray">0G</td>--%>
          </tr>
          <tr>
            <td class="name">KVM 总量:</td>
            <td class="yellow">0G</td>
<%--            <td class="gray">0G</td>--%>
          </tr>
          <tr>
            <td class="name">其他总量:</td>
            <td class="yellow">0G</td>
<%--            <td class="gray">0G</td>--%>
          </tr>
        </table>
    </dd>
</dl>
<%--<dl class="single2">--%>
<%--	<dt class="jhj">96</dt>--%>
<%--    <dd>--%>
<%--    	<table width="100%" border="0" cellspacing="0" cellpadding="0">--%>
<%--          <tr>--%>
<%--            <td class="name">千兆端口个数:</td>--%>
<%--            <td class="yellow">48</td>--%>
<%--            <td class="gray">24</td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td class="name">百兆端口个数:</td>--%>
<%--            <td class="yellow">48</td>--%>
<%--            <td class="gray">24</td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td class="name">ATM端口:</td>--%>
<%--            <td class="yellow">0</td>--%>
<%--            <td class="gray">0</td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td class="name">EI端口个数:</td>--%>
<%--            <td class="yellow">0</td>--%>
<%--            <td class="gray">0</td>--%>
<%--          </tr>--%>
<%--          <tr>--%>
<%--            <td class="name">其他:</td>--%>
<%--            <td class="yellow">0</td>--%>
<%--            <td class="gray">0</td>--%>
<%--          </tr>--%>
<%--        </table>--%>
<%--    </dd>--%>
<%--</dl>--%>
</div>
</s:form>
</body>
</html>
