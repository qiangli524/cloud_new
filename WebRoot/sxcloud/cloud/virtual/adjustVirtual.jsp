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
	function submitRequest(thisForm){    
	
	    var bool1 = isnumber(thisForm.VH_CPU.value);
	    var bool2 = isnumber(thisForm.VH_MEM.value);
	    var bool3 = isnumber(thisForm.VH_PROCESS_UNIT.value);
	   	theForm.action = 'virtual_saveAdjustVirtualInfo.do'
	    thisForm.submit();
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
	<s:form action="virtual_adjustVirtualInfo" method="post" id="theForm">
		<s:hidden name="theForm.VH_ID_IBM" id="VH_ID_IBM"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				
				    <!--  <td class="til">
						虚拟机编号 <font color="red"></font>
					</td>
					<td>
					    <html:text property="VH_ID" styleClass="txt"  onfocus="this.blur()" style="background-color:#DCDCDC"/>               
					</td>
					<td class="til">
						虚拟机名称 <font color="red"></font>
					</td>
					<td>
					    <html:text property="VH_NAME" styleClass="txt" onfocus="this.blur()" style="background-color:#DCDCDC"/>                 
					</td>
				</tr>-->
				
					<!--  <td class="til">
						处理器设置 <font color="red"></font>
					</td>
					<td>
					    <html:text property="VH_CPU" styleClass="txt" onfocus="this.blur()" style="background-color:#DCDCDC"/>                 
					</td>-->
					<!--  <td class="til">
						内存  <font color="red"></font>
					</td>
					<td>
					    <html:text property="VH_MEM" styleClass="txt" onfocus="this.blur()" style="background-color:#DCDCDC"/>                 
					</td>
				</tr>-->
				<tr>
					<td><font size="3"><b>处理器设置</b></font>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="til">
						虚拟或专用处理器数<font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.VH_CPU" id="VH_CPU" cssClass="txt"></s:textfield>
						<font color="red">分配给当前活动虚拟服务器</font>          
					</td>
				</tr>
				<tr>
					<td class="til">
						 处理单元数<font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.VH_PROCESS_UNIT" id="VH_PROCESS_UNIT" cssClass="txt"></s:textfield>
					      <font color="red">分配给当前活动虚拟服务器</font>           
					</td>
					 
					<!--  <td class="til">
						内存最大值 <font color="red"></font>
					</td>
					<td>
					    <html:text property="VH_MAX_MEM" styleClass="txt" />	（MB）               
					</td>-->
				</tr>
				<tr>
					<td><font  size="3"><b>内存设置(MB)</b></font>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="til">
						内存设置(MB) <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.VH_MEM" id="VH_MEM" cssClass="txt"></s:textfield>	
					    （MB）                 
					</td>
				</tr>
		
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>
