<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
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
                        callback: addLoadBalanceListener,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
            var v = $("#frm").validate({
                rules: {
                    'obj.name': {required: true},
                    'obj.load_port': {required: true, digits: true}
                },
                messages: {
                    'obj.name': "请输入负载监听器名称",
                    'obj.load_port': {required: "请输入端口号", digits: "请输入正确端口号"}
                }
            });

            function addLoadBalanceListener() {
                var check = v.form();
                if (!check)    return false;

                var params = $("#frm").serialize();
                var url = "loadBalanceListener_saveLoadBalanceListener.do";
                w.addLoadBalanceListener(url, params);
            }
        });


        function refresh() {
            parent.location.reload();
        }
    </script>
</head>
<body>
<!-- 添加start -->
<div class="modal" style="width: 600px;height: auto;margin-left: -250px;margin-top: -110px;top: 50%;">
    <div class="modal-content" id="">
        <form id="frm" class="form form-horizontal">
            <s:hidden name="obj.load_id" id="load_id"></s:hidden>
            <fieldset>
                <legend>添加负载监听器信息</legend>
                <div class="item">
                    <div class="control-label">
                        名称
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.name" autofocus="autofocus" maxlength="20"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">
                        负载端口
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.load_port" maxlength="20" id="load_port"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">均衡方式</div>
                    <div class="controls">
                        <div class="select-con">
                            <select class="dropdown-select" name="grpObj.loadbalance_way">
                                <option value="0" selected>轮询</option>
                                <option value="1">最小连接数</option>
                            </select>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<!-- 添加end -->
</body>
</html>
