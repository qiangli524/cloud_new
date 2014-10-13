<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet"  type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
    <title></title>

    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: createdhcp,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
        });

        function createdhcp() {
            var re = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
            var ipaddr = $("#ipaddr").val();
            if (ipaddr == null || ipaddr == "") {
                alert("IP地址不能为空");
                return false;
            } else if (!re.test(ipaddr)) {
                alert("ip地址不合法");
                return false;
            }
            w.saveDhcp($("#theForm").serialize());
        }
    </script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="theForm">
    <s:hidden name="ipObj.id"></s:hidden>
    <s:hidden name="oper" id="oper"></s:hidden>
    <table width="100%" border="0" cellspacing="0"
           class="pop-table nosize">
        <tr>
            <td class="til">公网IP地址 <font color="red">*</font>
            </td>
            <td>
                <s:textfield name="ipObj.ipaddress" id="ipaddr"></s:textfield>
            </td>
        </tr>
        <tr>
            <td class="til">端口<font color="red">*</font>
            </td>
            <td><s:textfield name="ipObj.addr_group" id="group"/>
            </td>
        </tr>
    </table>
</s:form>
</body>
</html:html>
