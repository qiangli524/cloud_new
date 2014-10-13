<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/link.jsp"%>
<%@ include file="/common/view.jsp"%>
<html:html locale="true">
	<head>
		<script type="text/javascript">
			function goImageList() {
				theForm.submit();
			}
		</script>
		<title></title>
	</head>
	<body>
		<html:form action="listImageInfo" method="post" styleId="theForm"> 
			<bean:define id="theForm" name="imageForm" />
			<div class="scrollbody"> 
				<div class="box on">
					<div class="query-form">
						<table width="100%" class="querytable" border="0">
						   <tr> 
								<td align='center'>
									<font color="red">部署虚拟机出现错误，详细信息请查看告警信息！</font><a href="/listMonitorAlarm.do">查看告警</a>
								</td>  
							</tr>
							<tr>
								<td colspan="2" class="btns">
									<div>
										<input type="button" class="thickbox btn-style02" value="返回" onclick="javascript:goImageList();" />
									</div>
								</td>
							</tr>
						</table>
					</div>   
				</div>
			</div>
		</html:form> 
	</body>
</html:html>
