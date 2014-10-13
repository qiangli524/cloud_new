<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/sxcloud/common/taglib.jsp"%>
<html:html locale="true">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</head>
<body>
<s:form action="function_saveFunctions" method="post"  id="theForm">
  <tr>
    <td align="left" valign="top" class="panel-zy" >
       <table width="540" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="32"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-cpu.gif" width="26" height="21" /></td>
            <td width="50">CPU：</td>
            <td width="80" class="orange-20">50颗</td>
            <td width="32"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-nc.gif" width="26" height="21" /></td>
            <td width="50">内存：</td>
            <td width="80" class="orange-20"><s:property value="theForm.mem_all"/>M</td>
            <td width="32" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-yp.gif" width="26" height="21" /></td>
            <td width="50">存储：</td>
            <td width="80" class="orange-20"><s:property value="theForm.store_all"/>G</td>
          </tr>
        </table>
       </td>
       </tr>
</s:form>
</body>
</html:html>
