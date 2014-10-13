<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<head>
<script type="text/javascript">
</script>
</head>
<body style="background-color:#f7f7f7;">
<table  style="position:absolute;top:20px;">
	<tr>
		<td>
			<font style="color:#6495ED;"> 请选择要导入的Excel文件</font>
		</td>
	</tr>
	<tr>
		<td>
					<input type="file" accept="application/vnd.ms-excel" id="browse"
						 style="background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:380px;"/>
		</td>
	</tr>
	<tr>
		<td>
					<a href="hostmanage_downloadxlsTemplate.do">下载模板</a>
		</td>
	</tr>
</table>
</body>
