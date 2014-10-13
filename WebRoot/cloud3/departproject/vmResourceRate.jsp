<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	//展示全部虚拟机信息
	function showVMAll(){
		 $.dialog({
			id:'showVMAll',
			title:'虚拟机信息',
			height:'500px',
			width:'1000px',
			lock:true,
			content:'url:unitedOutline_showVMAll.do'
		});
	}
</script>
</head>



<body class="pop-body scrollbody">

<s:form action="function_saveFunctions" method="post"  id="theForm">
<div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
  	<td width="25"></td>
    <td align="left" valign="top" class="panel-datacenter">
       <h2 class="zy"></h2>
            <div class="box on">
                <dl class="single5">
              	<dt>
              			总量:<fmt:formatNumber value="${(userAllObj.CPU_ALL_COUNT) }" pattern="#,###.##" type="number"/> 核 &nbsp;
              			已用:<s:if test="userUsedObj.CPU_USED_COUNT ==null or userUsedObj.CPU_USED_COUNT ==''">
              					0 核
              				</s:if><s:else>
              					<fmt:formatNumber value="${(userUsedObj.CPU_USED_COUNT) }" pattern="#,###.##" type="number"/>核 
              				</s:else>
              			可用:<fmt:formatNumber value="${(userAllObj.CPU_ALL_COUNT-userUsedObj.CPU_USED_COUNT)}" pattern="#,###.##" type="number"/> 核
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/cpu.gif" width="40" height="23" /></td>
                        <td width="30"><span>CPU</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.CPU_USED_COUNT*100*100/userAllObj.CPU_ALL_COUNT)/100.0"/>%'></b></div></td>
                        <td align="left"><!-- 60%  -->
                        		<%-- <s:property value="@java.lang.Math@round(userUsedObj.CPU_USED_COUNT * 100 * 100 / userAllObj.CPU_ALL_COUNT ) / 100.0"/> --%>
                        		<s:if test="userUsedObj.CPU_USED_COUNT==null or userUsedObj.CPU_USED_COUNT==0">
                        			0%
                        		</s:if>
                        		<s:else>
		                        	<fmt:formatNumber value="${((userUsedObj.CPU_USED_COUNT) * 100 * 100 / (userAllObj.CPU_ALL_COUNT) ) / 100.0}" pattern="#,###.##" type="number"/> %
                        			
                        		</s:else>
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			<%-- <s:property value='@java.lang.Math@round(userAllObj.MEM_ALL_MB /1024*100) / 100.0'/> --%>
              			总量:<fmt:formatNumber value="${(userAllObj.MEM_ALL_MB)/1024}" pattern="#,###.##" type="number"/>G &nbsp;
              			已用:<fmt:formatNumber value="${(userUsedObj.MEM_USED_MB)/1024}" pattern="#,###.##" type="number"/>G &nbsp;
              			可用:<fmt:formatNumber value="${(userAllObj.MEM_ALL_MB-userUsedObj.MEM_USED_MB)/1024}" pattern="#,###.##" type="number"/>G
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/mem.gif" width="40" height="23" /></td>
                        <td width="30"><span>内存</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.MEM_USED_MB*100*100/userAllObj.MEM_ALL_MB)/100.0"/>%'></b></div></td>
                        <td align="left"><!-- 60%  -->
                        	<s:if test="userUsedObj.MEM_USED_MB==null or userUsedObj.MEM_USED_MB ==0">
                        		0%
                        	</s:if>
                        	<s:else>
	                        	<fmt:formatNumber value="${((userUsedObj.MEM_USED_MB )* 100 * 100 /(userAllObj.MEM_ALL_MB) ) / 100.0}" pattern="#,###.##" type="number"/>%
                        	</s:else>
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			总量:<fmt:formatNumber value="${(userAllObj.STORAGE_ALL_MB)/1024}" pattern="#,###.##" type="number"/>G &nbsp;
              			已用:<fmt:formatNumber value="${(userUsedObj.STORAGE_USED_MB)/1024}" pattern="#,###.##" type="number"/>G &nbsp;
              			可用:<fmt:formatNumber value="${(userAllObj.STORAGE_ALL_MB-userUsedObj.STORAGE_USED_MB)/1024}" pattern="#,###.##" type="number"/>G
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="40"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/storage.png" width="25" height="23" /></td>
                         <td width="30"><span>存储</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.STORAGE_USED_MB*100*100/userAllObj.STORAGE_ALL_MB)/100.0"/>%'></b></div></td>
                        <td align="left"><!-- 60%  -->
                        <s:if test="userUsedObj.STORAGE_USED_MB==null or userUsedObj.STORAGE_USED_MB == 0">
                        		0%
                        </s:if>
                        <s:else>
	                        <fmt:formatNumber value="${((userUsedObj.STORAGE_USED_MB) * 100 * 100 / (userAllObj.STORAGE_ALL_MB )) / 100.0}" pattern="#,###.##" type="number"/> %
                        </s:else>
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			总量:<s:property value='userAllObj.IP_ALL_COUNT'/> 
              			已用:<s:property value='userUsedObj.IP_USED_COUNT'/> 
              			可用:<s:property value='userAllObj.IP_ALL_COUNT-userUsedObj.IP_USED_COUNT'/>
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="40"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/ip.png" width="20" height="20" /></td>
                        <td width="30"><span>IP</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.IP_USED_COUNT*100*100/userAllObj.IP_ALL_COUNT)/100.0"/>%'></b></div></td>
                        <td align="left"><!-- 60%  -->
                        <s:if test="userUsedObj.IP_USED_COUNT == null or userUsedObj.IP_USED_COUNT==0">
                        	0%
                        </s:if>
                        <s:else>
                        	<fmt:formatNumber value="${((userUsedObj.IP_USED_COUNT) * 100 * 100 / (userAllObj.IP_ALL_COUNT) ) / 100.0}" pattern="#,###.##" type="number"/> %
                        </s:else>
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
