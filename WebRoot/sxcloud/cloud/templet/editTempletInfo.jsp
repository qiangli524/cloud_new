<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%> 
<head>
<title></title>
<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})		
	function resetForm(theForm){
		theForm.TEM_ID.value = '';
		theForm.TEM_NAME.value = '';
		theForm.TYPE.value = '';
		theForm.CONFIG_NAME.value = '';
		theForm.CONFIG_VALUE.value = '';
	}
	function submitRequest(){
		theForm.action = 'templet_saveModTempletInfo.do'
		theForm.submit();
	}
   	function searchRequest() { 
		theForm.submit();
 	} 	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="templet_modTempletInfo.do" method="post" cssStyle="theForm" id="theForm">
  <s:hidden name="theForm.flag" id="flag"></s:hidden>
   <s:hidden name="theForm.TYPE" id="TYPE"></s:hidden>
 <s:hidden name="theForm.RELEASE_FLAG" id="RELEASE_FLAG"></s:hidden>
<div class="scrollbody">
	<div>
		<div class="tit-zzi">
			<div id="zi">服务基本信息</div>
			<div id="zi"></div>							
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						服务编号
					</td>
					<td>
						<s:text name="theForm.TEM_ID"></s:text>
						<s:hidden name="theForm.TEM_ID" id="TEM_ID"></s:hidden>
					</td>
					<td class="til">
						服务类型 
					</td>
					<td class="til">
						<s:text name="theForm.TYPE_NAME"></s:text>
					</td>
				</tr>
				<tr>
					<td class="til">
						服务名称 
					</td>
					<td>
						<s:textfield name="theForm.TEM_NAME" cssClass="txt" id="TEM_NAME"></s:textfield>            
					</td>
					<td class="til">
						服务描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.TEM_DESC" cols="77" rows="3" id="TEM_DESC"></s:textarea>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>配置项</th>
				   <th>配置值</th>
             </tr>
			  </thead>
			  <tbody>	
			  <s:if test="theForm.VH_CPUVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_CPU"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_CPUVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>
				<s:if test="theForm.VH_MEMVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MEM"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MEMVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>
				<s:if test="theForm.VH_MAX_CPUVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MAX_CPU"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MAX_CPUVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>
				<s:if test="theForm.IMAGE_IDVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.IMAGE_ID"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.IMAGE_IDVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>
				</s:if>	
				<s:if test="theForm.VH_MIN_CPUVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MIN_CPU"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MIN_CPUVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>	
				<s:if test="theForm.VHMAX_MEMVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VHMAX_MEM"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VHMAX_MEMVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>	
				<s:if test="theForm.VH_MIN_MEMVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MIN_MEM"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MIN_MEMVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.APP_IDVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.APP_ID"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.APP_IDVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.APP_PORTVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.APP_PORT"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.APP_PORTVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.APP_NAMEVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.APP_NAME"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.APP_NAMEVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.VH_PROCESS_UNITVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_PROCESS_UNIT"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_PROCESS_UNITVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.VH_MAX_PROCESS_UNITVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MAX_PROCESS_UNIT"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MAX_PROCESS_UNITVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.VH_MIN_PROCESS_UNITVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MIN_PROCESS_UNIT"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MIN_PROCESS_UNITVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
			</s:if>	
			<s:if test="theForm.VH_STORAGEVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_STORAGE"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_STORAGEVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>	
				<s:if test="theForm.VH_MAX_MEMVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_MAX_MEM"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_MAX_MEMVALUE" cssClass="txt"></s:textfield>
					</td>
				</tr>	
				</s:if>	
				<s:if test="theForm.VH_NUMVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_NUM"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_NUMVALUE" ></s:textfield>
					</td>
				</tr>	
				</s:if>
				<s:if test="theForm.POOL_UUIDVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.POOL_UUID"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.POOL_UUIDVALUE" ></s:textfield>
					</td>
				</tr>	
				</s:if>
				<s:if test="theForm.VH_IPVALUE !=null">
				<tr>
					<td>
						<s:text name="theForm.VH_IP"></s:text>
					</td>
					<td>
						<s:textfield name="theForm.VH_IPVALUE" ></s:textfield>
					</td>
				</tr>	
				</s:if>
			  </tbody>
			</table>
		</div>
	</div>		
		
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest();return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.go(-1)" />
				</td>
			</tr>
		</table>
</div>
</s:form>
</body>
