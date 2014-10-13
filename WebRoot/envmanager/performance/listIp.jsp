<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
	document.getElementById("IP_OLD").value="";
	document.getElementById("MAC_OLD").value="";
	document.getElementById("IP_NEW").value="";
	document.getElementById("MAC_NEW").value="";
	}
</script>
</head>
<body>
<s:form action="performance_queryAddress.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.LD_ID" id="LD_ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>

 <div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%"  border="0">
                  <tr>
                 
					<td class="til">旧IP地址</td>
					<td>
						<s:textfield name="theForm.IP_OLD" cssClass="txt" id="IP_OLD"></s:textfield>
					</td>
					<td class="til">旧MAC地址</td>
					<td>
						<s:textfield name="theForm.MAC_OLD" cssClass="txt" id="MAC_OLD"></s:textfield>
					</td>
					<td class="til">新IP地址</td>
					<td>
						<s:textfield name="theForm.IP_NEW" cssClass="txt" id="IP_NEW"></s:textfield>
					</td>
					<td class="til">新MAC地址</td>
					<td>
						<s:textfield name="theForm.MAC_NEW" cssClass="txt" id="MAC_NEW"></s:textfield>
					</td>
				</tr>
			
				<tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                 </tr>
                </table>
        </div>
  <div class="blue-wrap noborder">
		<div class="table-head">
		  
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
		 <tr>
			<th colspan="4" >地址变动</td>
			<th rowspan="2" >主机类型</th>
			<th rowspan="2"  >设备ID</th>
			<th rowspan="2"  >资产编号</th>
			<th rowspan="2"  >型号</th>
			<th rowspan="2"  >配置</th>
			<th rowspan="2" >操作系统</th>
			<th rowspan="2"  >主机名</th>
			<th rowspan="2"  >VM分区</th>
			<th colspan="3" >IP地址</td>
			<th rowspan="2"  >系统口令</th>
			<th rowspan="2"  >console用户/口令</th>
			<th rowspan="2"  >对应存储</th>
			<th colspan="4" >用途</th>
			<th rowspan="2" >责任人</th>
		</tr>
		<tr>
			<th style="background:#4682B4">旧IP地址</th>
			<th style="background:#4682B4">旧MAC地址</th>
			<th style="background:#4682B4">新IP地址</th>
			<th style="background:#4682B4">新MAC地址</th>
			<th style="background:#4682B4">物理IP地址</th>
			<th style="background:#4682B4">虚拟机IP地址</th>
			<th style="background:#4682B4">管理地址</th>
			<th style="background:#4682B4">归属域</th>
			<th style="background:#4682B4">资源池</th>
			<th style="background:#4682B4">使用部门</th>
			<th style="background:#4682B4">用户说明</th>
		</tr>
		 	  
			 <!--<tr>
			<th >旧IP地址</th>
			<th >旧MAC地址</th>
			<th >新IP地址</th>
			<th >新MAC地址</th>
			</tr> -->
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.addressList" id="theBean">
			  	<!--<tr>
			  		<td><s:property value="#theBean.IP_OLD"/></td>
			  		<td><s:property value="#theBean.MAC_OLD"/></td>
			  		<td><s:property value="#theBean.IP_NEW"/></td>
			  		<td><s:property value="#theBean.MAC_NEW"/></td>
			  	-->
			  	
			  	 <tr>
			  		<td><s:property value="#theBean.IP_OLD"/></td>
			  		<td><s:property value="#theBean.MAC_OLD"/></td>
			  		<td><s:property value="#theBean.IP_NEW"/></td>
			  		<td><s:property value="#theBean.MAC_NEW"/></td>
			  		
			  		<td><s:property value="#theBean.CAPITAL_TYPE"/></td>
			  		
			  		<td><s:property value="#theBean.MECH_ID"/></td>
			  		<td><s:property value="#theBean.CAPITAL_ID"/></td>
			  		
			  		<td><s:property value="#theBean.MECH_TYPE"/></td>
			  		<td><s:property value="#theBean.MECH_CONF"/></td>
			  		<td><s:property value="#theBean.SYS_SYSTEM"/></td>
			  		<td><s:property value="#theBean.SYS_HOSTNAME"/></td>
			  		<td><s:property value="#theBean.SYS_VM"/></td>
			  		<td><s:property value="#theBean.IP_PHYSICS"/></td>
			  		<td><s:property value="#theBean.IP_VIRTUAL"/></td>
			  		<td><s:property value="#theBean.IP_ILO"/></td>
			  		<td><s:property value="#theBean.PWD_SYSTEM"/></td>
			  		<td><s:property value="#theBean.PWD_CONSOLE"/></td>
			  		<td><s:property value="#theBean.STORE"/></td>
			  		<td><s:property value="#theBean.USE_DOMAN"/></td>
			  		
			  		<td><s:property value="#theBean.USE_RES"/></td>
			  		<td><s:property value="#theBean.USE_DEPART"/></td>
			  		<td><s:property value="#theBean.USE_DESCRIP"/></td>
			  		<td><s:property value="#theBean.MANAG_PERSON"/></td>
			  		<!-- 
			  		<td><s:property value="#theBean.MANAG_RECORD"/></td>
			  		<td><s:property value="#theBean.MANAG_REPAIR"/></td>
			  		<td><s:property value="#theBean.MANAG_USE"/></td>
			  		<td><s:property value="#theBean.MANAG_USABLE"/></td>
			  		<td><s:property value="#theBean.MANAG_DESCRIP"/></td>
			  		 -->
			  		
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
		</div>
		</div>
</s:form>
</body>
