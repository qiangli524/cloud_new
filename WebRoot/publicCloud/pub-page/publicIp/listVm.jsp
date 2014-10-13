<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: selectVm,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
        });

        function selectVm() {
            var id = '<s:property value="ipObj.id" />';
            var vmId = "";
            var connectCode = "";
            var count = 0;
            $(":checkbox:checked").each(function () {
                vmId = $(this).attr("vm_uuid");
                connectCode = $(this).attr("connectId");
                count++;
            });
            if (count > 1) {
                alert("一个公网ip只能绑定到一台主机上");
                return false;
            }
            var ipaddress = $("#ipaddress").val();
            var url = "publicip_bind.do?ipObj.entity_id=" + vmId + "&ipObj.connect_id=" + connectCode + "&ipObj.ipaddress=" + ipaddress + "&ipObj.id=" + id;
            w.selectVM(url);
        }

        $(function () {
            $("#searchForm").click(function () {
                theForm.action = 'publicip_listVMForUser.do';
                $("#theForm").submit();
            });
        })
    </script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="ipObj.ipaddress" id="ipaddress"></s:hidden>
    <div class="box on">
        <div class="blue-wrap noborder">
            <ul class="operate-list">
                <li class="search">
                    <input type="text" class="text-1" placeholder="云主机名称" size="22" name="ipObj.entity_name"/>
                    <input type="button" class="search-btn" id="searchForm"/>
                </li>
            </ul>
            <div class="table-ct">
                <table border="0" width="100%" class="table-f4 js_table_f4">
                    <thead id="table">
                    <tr>
                        <th>选择</th>
                        <th>云主机名称</th>
                        <th>IP地址</th>
                        <th>云主机配置</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="vmList" id="theBean">
                        <tr>
                            <td>
                                <input type="checkbox" name="bb" class="code"
                                       vm_uuid='<s:property value="#theBean.VH_UUID"/>'
                                       connectId='<s:property value="#theBean.CONNECT_ID"/>'/>
                            </td>
                            <td>
                                <s:property value="#theBean.VH_NAME"/>
                            </td>
                            <td>
                                <s:property value="#theBean.VH_IP"/>
                            </td>
                            <td>
                                <s:property value="#theBean.VH_CPU"/>核|
                                <s:property value="#theBean.VH_MEM"/>M|
                                <s:property value="@java.lang.Math@round(#theBean.VH_STORAGE/1024)"/>G
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <!-- 分页start -->
                <div class="page mgt34-b25">
                    <jsp:include page="../../inc/fenye.jsp?formId=theForm"/>
                </div>
                <!-- 分页end -->
            </div>
        </div>
    </div>
</s:form>
</body>