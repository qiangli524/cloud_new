<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>

    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: saveInfo,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
        });

        function saveInfo() {
            w.save($("#theForm").serialize());
        }
    </script>
</head>
<body style="overflow-y:auto;">
<s:form action="" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="ipObj.ipaddress" id="ipaddr"></s:hidden>
    <div>
        <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
            <tr>
                <td class="til">
                    描述：
                </td>
                <td>
                    <s:textarea cols="30" rows="4" name="ipObj.description" id="descr"></s:textarea><br/>
                </td>
            </tr>
        </table>
    </div>
</s:form>
</body>
</html:html>