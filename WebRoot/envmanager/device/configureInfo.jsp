<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
	<script type="text/javascript">
		function submitRequest(theForm) {
		var DEVICE_ID = document.getElementById('DEVICE_ID').value;
	//	var  DOMAIN = document.getElementById("DOMAIN").value;
	//	var  SOURCE = document.getElementById("SOURCE").value;
	//	var  CLASS = document.getElementById("CLASS").value;
	//	var  HOSTTYPE = document.getElementById("HOSTTYPE").value;
		
	//	var  TABLESPACE = document.getElementById("TABLESPACE").value;
	//	var  FILEAPPNUM = document.getElementById("FILEAPPNUM").value;
	//	var  MEM = document.getElementById("MEM").value;
	//	var  FILEUSERD = document.getElementById("FILEUSERD").value;
	//	var  TABSPAUSED = document.getElementById("TABSPAUSED").value;
		
	//	if(DOMAIN.length==0){
	//		alert("系统域不能为空");
	//		return false;
	//	}
	//	if(SOURCE.length==0){
	//		alert("资源域不能为空");
	//		return false;
	//	}
	//	if(CLASS.length==0){
	//		alert("分类不能为空");
	//		return false;
	//	}
	//	if(HOSTTYPE.length==0){
	//		alert("主机类型不能为空");
	//		return false;
	//	}
	//	if(isNaN(TABLESPACE)){
	//		alert("表空间值只能是数字");
	//		return false;
	//		}
	//	if(isNaN(FILEAPPNUM)){
	//		alert("文件系统大小只能是数字");
	//		return false;
	//		}
	//	if(isNaN(MEM)){
	//		alert("内存值只能是数字");
	//		return false;
	//		}
	//	if(isNaN(FILEUSERD)){
	//		alert("表空间只能是数字");
	//		return false;
	//		}
	//	if(isNaN(TABSPAUSED)){
	//		alert("内存值只能是数字");
	//		return false;
	//		}
				theForm.flag.value = 0;
				theForm.action = "configure_saveConfigureObj.do?DEVICE_ID"+DEVICE_ID;
				theForm.submit();
		}
		function modRequest(theForm){
		var DEVICE_ID = document.getElementById('DEVICE_ID').value;
				theForm.flag.value = 1;
				theForm.action = "configure_saveConfigureObj.do?DEVICE_ID"+DEVICE_ID;
				theForm.submit();
		}
		
		
	</script>
</head>

<body class="pop-body scrollbody" >
<s:form action="configure_saveConfigureObj.do" id="theForm" method="post"
	cssClass="theForm">
<s:textfield name="theForm.DEVICE_ID" id="DEVICE_ID" />
<s:hidden name="theForm.flag" id="flag"/>
<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						系统域 <font color="red">*</font>
					</td>
					<td>
					<!-- 	<s:textfield name="theForm.DOMAIN"  id="DOMAIN"/> -->
						<s:select name="theForm.DOMAIN" headerKey="-1" headerValue="请选择" list="#{'研发环境':'研发环境','测试环境':'测试环境','演示环境':'演示环境'}" id="DOMAIN">
						</s:select>
					</td>
					<td class="til">
						资源域<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.SOURCE" id="SOURCE"/>&nbsp;&nbsp;
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						分类<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.CLASS"  id="CLASS"/>	
					</td>
					<td class="til">
						主机类型<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.HOSTTYPE" id="HOSTTYPE" />
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						型号
					</td>
					<td>
                        <s:textfield name="theForm.HOSTNUM" id="HOSTNUM"/>
					</td>
					 <td class="til">
						配置
					</td>
					<td >
						<s:textfield name="theForm.DESCRIPTION" id="DESCRIPTION" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						操作系统
					</td>
					<td>
                        <s:textfield name="theForm.SYSTEM" id="SYSTEM" />
					</td>
					 <td class="til">
						主机名
					</td>
					<td >
						<s:textfield name="theForm.HOSTNAME" id="HOSTNAME" />&nbsp;&nbsp;
					</td>
				</tr>
				
				
				<tr>
				  
					<td class="til">
						IP地址
					</td>
					<td>
                        <s:textfield name="theForm.IP" id="IP" />
					</td>
					 <td class="til">
						使用产品线
					</td>
					<td >
						<s:textfield name="theForm.PRODUCT" id="PRODUCT"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						研发产品
					</td>
					<td>
                        <s:textfield name="theForm.DEVEPROD" id="DEVEPROD" />
					</td>
					 <td class="til">
						SID
					</td>
					<td >
						<s:textfield name="theForm.SID" id="SID" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						表空间
					</td>
					<td>
                        <s:textfield name="theForm.TABLESPACE" id="TABLESPACE" />
					</td>
					 <td class="til">
						文件系统
					</td>
					<td >
						<s:textfield name="theForm.FILESYSNAM" id="FILESYSNAM"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						文件系统大小
					</td>
					<td>
                        <s:textfield name="theForm.FILEAPPNUM" id="FILEAPPNUM" />
					</td>
					 <td class="til">
						CPU
					</td>
					<td >
						<s:textfield name="theForm.CPUUSED" id="CPUUSED"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.MEM" id="MEM"/>
					</td>
					 <td class="til">
						文件系统
					</td>
					<td >
						<s:textfield name="theForm.FILEUSERD" id="FILEUSERD" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						文件系统使用率
					</td>
					<td>
                        <s:textfield name="theForm.FILEUSEPER" id="FILEUSEPER" />
					</td>
					 <td class="til">
						实例
					</td>
					<td >
						<s:textfield name="theForm.SID1" id="SID1"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						表空间
					</td>
					<td>
                        <s:textfield name="theForm.TABSPAUSED" id="TABSPAUSED"/>
					</td>
					 <td class="til">
						表空间使率
					</td>
					<td >
						<s:textfield name="theForm.TABSPAUSEPER" id="TABSPAUSEPER"/>&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.SGA" id="SGA" />
					</td>
					 <td class="til">
						CPU
					</td>
					<td >
						<s:textfield name="theForm.CPULEFT" id="CPULEFT" />&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.MEMLEFT" id="MEMLEFT" />
					</td>
					<td class="til">
						存储
					</td>
					<td>
                        <s:textfield name="theForm.STORAGE" id="STORAGE" />
					</td>
					
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
					
						<input type="button" class="thickbox btn-style02" value="添加"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="修改"
							onclick="javascript:modRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
</table>
</s:form>
</body>
