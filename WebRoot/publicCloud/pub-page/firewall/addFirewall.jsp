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
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: addFirewall,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {
                        required: true
                    }
                },
                messages: {
                    'obj.name': "请输入防火墙名称"
                }
            });

            function addFirewall() {
                var check = v.form();
                if (!check)    return false;

                var params = $("#frm").serialize();
                var url = "firewall_saveFirewall.do";
                w.addFirewall(url, params);
            }
        });


        function refresh() {
            parent.location.reload();
        }
    </script>
</head>
<body>
<!-- 添加start -->
<div class="modal" style="width: 600px;height: auto;margin-left: -280px;margin-top: -70px;top: 50%;">
    <div class="modal-content" id="">
        <form id="frm" class="form form-horizontal">
            <fieldset>
                <legend>添加防火墙信息</legend>
                <div class="item">
                    <div class="control-label">
                        名称
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.name" autofocus="autofocus" maxlength="20"> </input>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<!-- 添加end -->
</body>
</html>
