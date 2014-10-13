<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title></title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: rename,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
        });
        //重命名
        function rename() {
            if ("" == $("#image_alias").val() || null == $("#image_alias").val()) {
                alertMsg('请填写映像名称');
                return false;
            }
            var params = $("#imageform").serialize();
            var url = "physicalVlan_updatePhysicalVlan.do";
            w.renameVlan(url, params);
        }
    </script>
</head>
<body>
<div class="thick-content">
    <form id="imageform">
        <br>

        <div>
            <input id="image_id" name="obj.id" value="${obj.id }" type="hidden"/>
            <span class="font-red pdr-5">*</span>新名称: <input type="text" id="image_alias" name="obj.name" maxlength="20"
                                                             class="input-1c"/>
        </div>

    </form>
</div>
</body>
</html>
