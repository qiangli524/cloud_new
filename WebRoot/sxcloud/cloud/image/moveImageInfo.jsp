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
		theForm.action = 'image_saveMvImageInfo.do'  
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
	<s:form action="image_moveImageInfo" method="post" id="theForm">
	<s:hidden name="theForm.IM_ID" id="IM_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						所属项目名称<font color="red">*</font>
					</td>
					<td>
					  <s:select list="theForm.projectList" listKey="PROJECT_ID" listValue="NAME" name="theForm.PROJECT_ID"></s:select>
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
