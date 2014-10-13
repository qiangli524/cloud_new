<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'createSnapShot.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            //根据 快照个数渲染页面
            renderInfo();
            //校验
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {
                        required: true
                    }
                },
                messages: {
                    'obj.name': "请输入快照名称"
                }
            });

            //保存快照
            function saveSnapshot() {
                var check = v.form();
                if (!check)    return false;

                var params = $("form").serialize();
                var url = "snapshot_createSnapShot.do";
                w.saveSnapshot(url, params);
            }

            function renderInfo() {

                $("#tobuy").hide();
                $.ajax({
                    type: 'post',
                    async: false,
                    url: 'snapshot_getSnapshotCountByVm.do',
                    data: {"obj.vm_uuid": $('#vm_uuid').val()},
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == "success") {
                            $("#info").html("这台机器您已经创建了<b> <font color='red'>" + data.snapshot_count + "</font> </b>个快照。</br>为了您的机器的性能，您最多只能创建<b> " + data.snapshot_max_num + " </b>个快照。");
                            //快照数量
                            if (data.snapshot_count > data.snapshot_max_num) {
                                $("#info").removeClass("alert-info").addClass("alert-error").append("，您创建快照的数量已经达到上限，不能创建新的快照。");
                                api.button({
                                    id: 'cancle',
                                    name: '取消'
                                });
                            } else {
                                var left_num = data.snapshot_max_num - data.snapshot_count;
                                $("#info").removeClass("alert-error").addClass("alert-info").append("，您还可以创建<b> <font color='green'>" + left_num + "</font> </b>个快照。");
                                api.button({
                                            id: 'OkAnd',
                                            name: '确定',
                                            callback: saveSnapshot,
                                            focus: true
                                        },
                                        {
                                            id: 'cancle',
                                            name: '取消'
                                        });

                            }
                        }
                    },
                    error: function (data, textStatus) {
                        console.log('error:' + data);
                    }
                });
            }
        });
    </script>
</head>
<body>
<!-- 添加start -->
<div class="modal" style="width: 550px;height: auto;margin-left: -330px;margin-top: -165px;top: 50%;">
    <p class="alert alert-info" style="width: 550px;height: auto;margin-left: 55px;" id="info"></p>

    <div class="modal-content" id="">
        <form id="frm" class="form form-horizontal">
            <input id="vm_uuid" name="obj.vm_uuid" value="${obj.vm_uuid}" type="hidden"/>
            <fieldset>
                <legend>创建快照</legend>
                <div class="item">
                    <div class="control-label">
                        <span style="color:red">*</span>名称
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.name" autofocus="autofocus" value="${obj.name }"
                               maxlength="20"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">描述</div>
                    <div class="controls">
                        <textarea name="obj.description"
                                  style="margin: 0px; width: 400px; height: 112px;">${obj.description}</textarea>
                    </div>
                </div>
                <div id="tobuy">
                    <!-- <input  type="button" value="立即购买"/> -->
                    <a href="snapshot_listSnapShot.do?type=13">查看快照列表</a>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<!-- 添加end -->
</body>
</html>
