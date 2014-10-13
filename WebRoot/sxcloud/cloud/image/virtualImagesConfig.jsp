<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
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

	function saveRequest(){
		theForm.action = 'image_saveImageInfo.do'
		theForm.submit();
	}
	function resetRequest(){
		theForm.submit();
	}

	function searchRequest() { 
		theForm.submit();
 	}
	function cpuModelSelect(){
		var str = document.theForm.SETTINGS.value;
		if(str=='0'){
			document.getElementById('dedicatedModel').style.display="";
			document.getElementById('sharedModel1').style.display="none";
			document.getElementById('sharedModel2').style.display="none";
			document.getElementById('sharedModel3').style.display="none";
		}else
		if(str=='1'){
			document.getElementById('dedicatedModel').style.display="none";
			document.getElementById('sharedModel1').style.display="";
			document.getElementById('sharedModel2').style.display="";
			document.getElementById('sharedModel3').style.display="";
		}
	}
</script>
</head>
<body onload=cpuModelSelect()>
<s:form action="image_virtualImagesConfig" method="post" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
<%--							<input type = "button" class="thickbox btn-style02" value = "全部选中" onclick = "javascript:searchRequest()" />--%>
<%--							<input type = "button" class="btn-style02" value = "全部取消" onclick = "javascript:resetForm(document.getElementById('theForm'))" />--%>
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
        <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			 <tr>
       		 	<td class="til">
						映像目标 <font color="red">基于此设备的工作负载将转至所选目标</font>
					</td>
				<td>
					<s:select list="theForm.TARGET" listKey="name" listValue="name" name="theForm.TARGETID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
       		 <tr>
       		 	<td class="til">
						处理器设置 
					</td>
				<td>
					<s:select list="#{'0':'专用设置','1':'共享设置'}" name="theForm.IM_CPU_MODE" onclick="cpuModelSelect();return false;" id="IM_CPU_MODE"></s:select>
				</td>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
			</tr>	
				<tr id=dedicatedModel style="display:">
					<td class="til">
						专用处理器设置 
					</td>
					<td>
				    	<s:textfield name="theForm.IM_MIN_CPU" id="IM_MIN_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_CPU" id="IM_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_MAX_CPU" id="IM_MAX_CPU" cssClass="txt"></s:textfield>
					</td>
<%--					<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
				</tr>
				<tr id=sharedModel1 style="display:">
					<td class="til">
						共享虚拟处理器 
					</td>
					<td>
				    	<s:textfield name="theForm.IM_MIN_CPU" id="IM_MIN_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_CPU" id="IM_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_MAX_CPU" id="IM_MAX_CPU" cssClass="txt"></s:textfield>             
					</td>
<%--					<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
				</tr>
				<tr id=sharedModel2 style="display:">
				    <td class="til">
						共享处理单元 
					</td>
					<td>
				    	<s:textfield name="theForm.IM_MIN_PROCESS_UNIT" id="IM_MIN_PROCESS_UNIT" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_PROCESS_UNIT" id="IM_PROCESS_UNIT" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.IM_MAX_PROCESS_UNIT" id="IM_MAX_PROCESS_UNIT" cssClass="txt"></s:textfield>               
					</td>
<%--					<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
				</tr>
				<tr id=sharedModel3 style="display:">
					<td class="til">
						虚拟服务器共享处理单元模式
					</td>
					<td>
						<s:select list="#{'1':'打开','2':'关闭'}" name="theForm.IM_PROCESS_UNIT_MODE" id="IM_PROCESS_UNIT_MODE"></s:select>
					</td>
<%--					<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
				</tr>
			<tr>
				<td class="til">
					内存设置 
				</td>
				<td>
				    <s:textfield name="theForm.IM_MIN_MEM" id="IM_MIN_MEM" cssClass="txt"></s:textfield>&le;
				    <s:textfield name="theForm.IM_MEM" id="IM_MEM" cssClass="txt"></s:textfield>&le;
				    <s:textfield name="theForm.IM_MAX_MEM" id="IM_MAX_MEM" cssClass="txt"></s:textfield>                 
				</td>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
			</tr>
			<tr>
				<td class="til">
					网络配置
				</td>
				<td class="til">
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					系统主机名 --%>
<%--				</td>	--%>
<%--				<td>--%>
<%--				    <html:text property="HOSTNAMESYS" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					系统主机域名 --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				    <html:text property="DOMAINSYS" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
			<tr>
				<td class="til">
					<font size="2"><strong>适配器1 </strong></font>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="til">
					网络配置 
				</td>
				<td class="til">
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					静态IP地址：--%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="FREECOUNT" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					主机名：--%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="HOSTNAME" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					默认网关： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="GATEWAY1" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					子网掩码： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="SUBNET" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					主DNS： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DNS1" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					辅助DNS： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DNS2" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					域名： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DOMAIN" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
			<tr>
				<td class="til">
					<font size="2"><strong>适配器2 </strong></font>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="til">
					网络配置 
				</td>
				<td class="til">
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					静态IP地址： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="FREECOUNT" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					主机名： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="HOSTNAME" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					默认网关： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="GATEWAY1" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					子网掩码：--%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="SUBNET" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>	--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					主DNS： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DNS1" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="til">--%>
<%--					辅助DNS： --%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DNS2" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
<%--			</tr>--%>
<%--			<tr>	--%>
<%--				<td class="til">--%>
<%--					域名：--%>
<%--				</td>--%>
<%--				<td>--%>
<%--				   	<html:text property="DOMAIN" styleClass="txt" />                --%>
<%--				</td>--%>
<%--				<td><input name="checkboxid" type="checkbox" value=""/>在"基本部署"中显示</td>--%>
			<tr>
				<td class="til">
					用户名： <font color="red">*</font>
				</td>
				<td>
				   	root           
				</td>
			</tr>
			<tr>	
				<td class="til">
					密码： <font color="red">*</font>
				</td>
				<td>
				   	<s:password name ="theForm.PASSWORD" id="PASSWORD" cssClass="txt"></s:password>
				</td>
			</tr>
				<tr>
					<td colspan="6" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:saveRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="取消"
							onclick="window.history.back()" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>			
		</table>
		</div>
	</div><!--blue-wrap end -->
	</div>
</s:form>
</body>
