<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
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
	    if(thisForm.FUNCID.value.length ==0){
	     alert("排序号不能为空！");
	     thisForm.FUNCID.focus;
	     return false  ;
	    }
	    var bool1 = isnumber(thisForm.FUNCID.value);
	    if(!bool1)
		{
			alert("排序号不符合要求");
			return false;
		}
	    if(thisForm.FUNNAME.value.length ==0){
	     alert("功能名称不能为空！");
	     thisForm.FUNNAME.focus;
	     return false  ;
	    }
	    if(thisForm.FUNCREQUEST.value.length ==0){
	     alert("连接地址不能为空！");
	     thisForm.FUNCREQUEST.focus;
	     return false  ;
	    }
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
	<html:form action="saveImageInfo" method="post" styleId="theForm">
		<bean:define id="theForm" name="imageForm" />
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<html:hidden name="theForm" property="IM_ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						映像名称 <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNCID" styleClass="txt" /><font color="red" size="1"> 注:只能输入数字</font>                 
					</td>
					<td class="til">
						映像描述 <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNNAME" styleClass="txt" />                 
					</td>
				</tr>
				<tr>
				    <td class="til">
						状态<font color="red">*</font>
					</td>
					<td>
                      <html:select property="STATUS" >
                          <html:option value="0">失效</html:option>
                          <html:option value="1">生效</html:option>
					    </html:select>
					</td>
				    <td class="til">
						类型 <font color="red">*</font>
					</td>
					<td >
						<html:select property="TYPE" >
					      <html:option value="1">菜单类型</html:option>
					      <html:option value="2">按钮类型</html:option>
					    </html:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						连接地址 <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNCREQUEST" styleClass="txt" />                 
					</td>
					<td class="til">
						加载是否刷新 <font color="red">*</font>
					</td>
					<td>
					    <html:select property="ISREFRESH" >
					      <html:option value="1">刷新</html:option>
					      <html:option value="0">不刷新</html:option>
					    </html:select>                 
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						备注
					</td>
					<td colspan="3">
						<html:textarea property="REMARK" cols="77" rows="5"></html:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
			</table>
	</html:form>
</body>

</html:html>
