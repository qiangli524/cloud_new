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
<s:form action="virtual_saveMemInfo" method="post" id="theForm">
	<s:hidden name="theForm.flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						虚拟机编号
					</td>
					<td>
					    <s:textfield name="theForm.VH_ID_IBM" id="VH_ID_IBM" onfocus="this.blur()" cssClass="txt" cssStyle="background-color:#DCDCDC"></s:textfield>             
					</td>
					<td class="til">
						虚拟机名称
					</td>
					<td>
					    <s:textfield name="theForm.VH_NAME" id="VH_NAME" onfocus="this.blur()" cssClass="txt" cssStyle="background-color:#DCDCDC"></s:textfield>               
					</td>
				</tr>
				<tr>
					<td class="til">
						存储卷名称<font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.VH_STORAGE_NAME" id="VH_STORAGE_NAME" cssClass="txt"></s:textfield>               
					</td>
					<td class="til">
						存储器大小
					</td>
					<td>
						<s:textfield name="theForm.VH_STORAGE_VALUE" id="VH_STORAGE_VALUE" cssClass="txt"></s:textfield>   
					(MB)              
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
