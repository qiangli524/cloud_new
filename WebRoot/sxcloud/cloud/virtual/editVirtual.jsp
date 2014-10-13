<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(theForm){    
	    theForm.submit();
	}
	
	
	/* 检测字符串是否为数字或者字母 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }	

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="virtual_saveVirtualInfo.do" method="post" id="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.VH_ID_IBM" id="VH_ID_IBM"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				    <s:hidden name="theForm.VH_ID_IBM" id="VH_ID_IBM"></s:hidden>   
			<%--
				<tr>
					
				    <td class="til">
						虚拟机编号
					</td>
					<td>
					    <bean:write name="theForm" property="VH_ID"/>  
					    <html:hidden name="theForm" property="VH_ID" />              
					</td>
					<td class="til">
						虚拟机名称
					</td>
					<td>
					    <bean:write name="theForm" property="VH_NAME"/>                  
					</td>
				</tr>
				<tr>
					<td class="til">
						内存
					</td>
					<td>
					    <bean:write name="theForm" property="VH_MEM"/>                  
					</td>
					<td class="til">
						CPU
					</td>
					<td>
					    <bean:write name="theForm" property="VH_CPU"/>                  
					</td>
				</tr>
				<tr>
					<td class="til">
						存储
					</td>
					<td>
					    <bean:write name="theForm" property="VH_STORAGE"/>                  
					</td>
					<td class="til">
						IP地址
					</td>
					<td>
					    <bean:write name="theForm" property="VH_IP"/>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						网络
					</td>
					<td>
					    <bean:write name="theForm" property="VH_NETWORK"/>                   
					</td>
					<td class="til">
						操作系统
					</td>
					<td>
					    <bean:write name="theForm" property="VH_SYSTEM"/>                 
					</td>
				</tr>
				
				<tr>
				<td class="til">
						用户名
					</td>
					<td>
					    <html:text property="VH_USER" styleClass="txt" readonly = "true"/>                 
					</td>
					<td class="til">
						密码
					</td>
					<td>
					    <html:text property="VH_PASS" styleClass="txt" readonly = "true"/>                 
					</td>
				</tr>
				
				<tr>
					<td class="til">
						所属项目编号
					</td>
					<td>
					     <bean:write name="theForm" property="PROJECT_ID"/>                 
					</td>
						<td class="til">
						物理主机编号
					</td>
					<td>
					    <bean:write name="theForm" property="EQ_ID"/>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						cpu最小值
					</td>
					<td>
					     <bean:write name="theForm" property="VH_MIN_CPU"/>                     
					</td>
						<td class="til">
						cpu最大值
					</td>
					<td> 
					    <bean:write name="theForm" property="VH_MAX_CPU"/>           
					</td>
				</tr>
				<tr>
					<td class="til">
						内存最小值
					</td>
					<td>    
					    <bean:write name="theForm" property="VH_MIN_MEM"/>               
					</td>
						<td class="til">
						内存最大值
					</td>
					<td>
					    <bean:write name="theForm" property="VH_MAX_MEM"/>                  
					</td>
				</tr>
				<tr>	
					<td class="til">
						日志
					</td>
					<td> 
					    <td class="til"><html:textarea property="VH_LOG" cols="40" rows="3" />            
					</td>
					--%>
					<tr>
						<td class="til">虚拟机名称:</td>
                    	<td>
							<s:textfield name="theForm.VH_NAME" id="VH_NAME" cssClass="txt"></s:textfield>
                    	</td>
                    </tr>
                    <tr>
						<td class="til">
							虚拟机描述
						</td>
						<td class="til">
							<s:textarea name="theForm.VH_DESC" id="VH_DESC" cols="70" rows="4"></s:textarea>
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
