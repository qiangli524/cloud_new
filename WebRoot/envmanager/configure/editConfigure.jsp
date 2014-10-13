<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>

<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	//是否是ip地址
	function ipFormat(str)	{
		var re=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	  	if (str.match(re)== null){
      		return false;
    	}else{
    		var stn = str.split(".");
    		var re1=/^0\d|0\d\d|00\d$/;
			for(var i=0;i<4;i++){
				if(stn[i].match(re1)!= null){
				//alert("is 0");
				return false;
				break;
				}
			}
		}
    	return true;
    }
	function submitRequest(obj){ 
		
		var  DOMAIN = document.getElementById("CF_DOMAIN").value;
		var  SOURCE = document.getElementById("CF_SOURCE").value;
		var  CLASS = document.getElementById("CF_CLASS").value;
		var  HOSTTYPE = document.getElementById("CF_HOSTTYPE").value;
		
		var  TABLESPACE = document.getElementById("CF_TABLESPACE").value;
		var  FILEAPPNUM = document.getElementById("CF_FILEAPPNUM").value;
		var  MEM = document.getElementById("CF_MEM").value;
		var  FILEUSERD = document.getElementById("CF_FILEUSERD").value;
		var  TABSPAUSED = document.getElementById("CF_TABSPAUSED").value;
		var  IP = document.getElementById("CF_IP").value;
		
		//if($(":input#DOMAIN").find("option:selected").val()==''){
		//		alert("请选择系统域！");
		//		return false;
		//}
		if(DOMAIN.length==0){
		alert("系统域不能为空");
			return false;
		}
		if(SOURCE.length==0){
		alert("资源域不能为空");
			return false;
		}
		if(CLASS.length==0){
			alert("分类不能为空");
			return false;
		}
		if(HOSTTYPE.length==0){
			alert("主机类型不能为空");
			return false;
		}
		if(IP.length==0){
			alert("IP地址不能为空");
			return false;
		}
		if (!ipFormat(IP)) {
				alert("IP输入不合法,只能为.和数字!");
				return false;
			}
		if(isNaN(TABLESPACE)){
			alert("表空间值只能是数字");
			return false;
			}
		if(isNaN(FILEAPPNUM)){
			alert("文件系统大小只能是数字");
			return false;
			}
		if(isNaN(MEM)){
			alert("内存值只能是数字");
			return false;
			}
		if(isNaN(FILEUSERD)){
			alert("表空间只能是数字");
			return false;
			}
		if(isNaN(TABSPAUSED)){
			alert("内存值只能是数字");
			return false;
			}
	
	theForm.submit();
}	
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="configure_saveConfigureObj.do" method="post" id="theForm" >
		<s:hidden name="theForm.cf_env" id="cf_env"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"/>
			<table width="100%" border="1" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						系统域 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.CF_DOMAIN"  id="CF_DOMAIN"/>
							<!--<s:select name="theForm.DOMAIN" headerKey="-1" headerValue="请选择" list="#{'研发环境':'研发环境','测试环境':'测试环境','演示环境':'演示环境'}" id="DOMAIN">
						</s:select>-->
					</td>
					<td class="til">
						资源域<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.CF_SOURCE" id="CF_SOURCE"/>&nbsp;&nbsp;
					</td>
					
				</tr>

				<tr>
				    <td class="til">
						分类<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.CF_CLASS"  id="CF_CLASS"/>	
					</td>
					<td class="til">
						主机类型<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.CF_HOSTTYPE" id="CF_HOSTTYPE" />
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						操作系统
					</td>
					<td>
                        <s:textfield name="theForm.CF_SYSTEM" id="CF_SYSTEM" />
					</td>
					 <td class="til">
						主机名
					</td>
					<td >
						<s:textfield name="theForm.CF_HOSTNAME" id="CF_HOSTNAME" />&nbsp;&nbsp;
					</td>
				</tr>
				
				
				<tr>
				  
					<td class="til">
						IP地址
					</td>
					<td>
                        <s:textfield name="theForm.CF_IP" id="CF_IP" />
					</td>
					 <td class="til">
						使用产品线
					</td>
					<td >
						<s:textfield name="theForm.CF_PRODUCT" id="CF_PRODUCT"/>&nbsp;&nbsp;
					</td>
				</tr>
				
				<tr>
				  
					<td class="til">
						研发产品
					</td>
					<td>
                        <s:textfield name="theForm.CF_DEVEPROD" id="CF_DEVEPROD" />
					</td>
					<td class="til">
						
					</td>
					<td>
                       
					</td>
					
				</tr>
				<tr>
				  
					<td class="til">
						型号
					</td>
					<td>
						<s:textarea name="theForm.CF_HOSTNUM" id="CF_HOSTNUM"  cols="40" rows="6" ></s:textarea>
                       <!-- <s:textfield name="theForm.CF_HOSTNUM" id="CF_HOSTNUM"/> -->
					</td>
					 <td class="til">
						配置
					</td>
					<td >
					<s:textarea name="theForm.CF_DESCRIPTION" id="CF_DESCRIPTION"  cols="40" rows="6" ></s:textarea>
						<!-- <s:textfield name="theForm.CF_DESCRIPTION" id="CF_DESCRIPTION" />&nbsp;&nbsp; -->
					</td>
				</tr>

<!-- -->
				<tr>
				<td colspan="4" class="tit-zzi" >
				申请资源情况
					</td>
					</tr>

<!-- -->
<!-- -->
				<tr>
					<td colspan="2" >
					数据库
					</td>
					<td colspan="2" >
					应用
					</td>
				</tr>

<!-- -->

				<tr>

				 <td class="til">
						实例
					</td>
					<td >
						<s:textfield name="theForm.CF_SID" id="CF_SID" />&nbsp;&nbsp;
					</td>
				  
					
					 <td class="til">
						文件系统
					</td>
					<td >
						<s:textfield name="theForm.CF_FILESYSNAM" id="CF_FILESYSNAM"/>&nbsp;&nbsp;
					</td>
					
				</tr>

			
				<tr>
				
					<td class="til">
						表空间
					</td>
					<td>
                        <s:textfield name="theForm.CF_TABLESPACE" id="CF_TABLESPACE" />
					</td>
				  
					<td class="til">
						文件系统大小
					</td>
					<td>
                        <s:textfield name="theForm.CF_FILEAPPNUM" id="CF_FILEAPPNUM" />
					</td>
					
				</tr>
<!-- -->
				<tr>
					<td colspan="4"  class="tit-zzi" >
					使用情况
					</td>
				</tr>

<!-- -->	

				<tr>
					<td colspan="2" >
					应用
					</td>
					
					<td colspan="2" >
					数据库
					</td>
					
				</tr>

<!-- -->
				<tr>
				 <td class="til">
						CPU
					</td>
					<td >
						<s:textfield name="theForm.CF_CPUUSED" id="CF_CPUUSED"/>&nbsp;&nbsp;
					</td>
				   <td class="til">
						实例
					</td>
					<td >
						<s:textfield name="theForm.CF_SID1" id="CF_SID1"/>&nbsp;&nbsp;
					</td>
					
					
				</tr>
				<tr>
				<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.CF_MEM" id="CF_MEM"/>
					</td>
				<td class="til">
						表空间
					</td>
					<td>
                        <s:textfield name="theForm.CF_TABSPAUSED" id="CF_TABSPAUSED"/>
					</td>
				  
					
					
				</tr>
				<!-- -->
			

<!-- -->
				<tr>
				 <td class="til">
						文件系统
					</td>
					<td >
						<s:textfield name="theForm.CF_FILEUSERD" id="CF_FILEUSERD" />&nbsp;&nbsp;
					</td>
				    <td class="til">
						表空间使率
					</td>
					<td >
						<s:textfield name="theForm.CF_TABSPAUSEPER" id="CF_TABSPAUSEPER"/>&nbsp;&nbsp;
					</td>
					
					
				</tr>
				<tr>
				<td class="til">
						文件系统使用率
					</td>
					<td>
                        <s:textfield name="theForm.CF_FILEUSEPER" id="CF_FILEUSEPER" />
					</td>
				 
					<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.CF_SGA" id="CF_SGA" />
					</td>
					
				</tr>
				<!-- -->
				<tr>
				<td colspan="4" class="tit-zzi">
				剩余资源情况
					</td>
					</tr>

<!-- -->
				<tr>
				   <td class="til">
						CPU
					</td>
					<td >
						<s:textfield name="theForm.CF_CPULEFT" id="CF_CPULEFT" />&nbsp;&nbsp;
					</td>
					<td class="til">
						内存
					</td>
					<td>
                        <s:textfield name="theForm.CF_MEMLEFT" id="CF_MEMLEFT" />
					</td>
					
					
				</tr>
				<tr>
				<td class="til">
						存储
					</td>
					<td>
                        <s:textfield name="theForm.CF_STORAGE" id="CF_STORAGE" />
					</td>
					<td class="til">
						
					</td>
					<td>
                       
					</td>
				</tr>
			
				<tr>
					<td colspan="4" class="btnCenter">

						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>
