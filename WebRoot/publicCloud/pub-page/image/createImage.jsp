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
            //根据 镜像个数渲染页面
            var image_max_num = "${attr.image_max_num}";
            var image_count = "${attr.image_count}";
            $("#tobuy").hide();
            renderInfo(image_max_num, image_count);
            //校验
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.alias': {
                        required: true
                    }
                },
                messages: {
                    'obj.alias': "请输入镜像名称"
                }
            });

            //保存镜像
            function saveImage() {
                var check = v.form();
                if (!check)    return false;

                var params = $("form").serialize();
                var url = "images_createImage.do";
                w.saveImage(url, params);
            }

            //根据镜像个数渲染页面
            function renderInfo(image_max_num, image_count) {
                $("#info").html("您可以免费创建<b> " + image_max_num + " </b>个镜像。</br>您已经创建了<b> <font color='red'>" + image_count + "</font> </b>个镜像");

                //镜像数量限制
                if (image_count > image_max_num) {
                    $("#info").removeClass("alert-info").addClass("alert-error").append("，您创建镜像的数量已经达到上限，不能创建新的镜像。");
                    api.button({
                        id: 'cancle',
                        name: '取消'
                    });
                    $("#tobuy").show();
                } else {
                    var left_num = image_max_num - image_count;
                    $("#info").removeClass("alert-info").addClass("alert-info").append("，您还可以创建<b> <font color='green'>" + left_num + "</font> </>个镜像。");
                    api.button({
                                id: 'OkAnd',
                                name: '确定',
                                callback: saveImage,
                                focus: true
                            },
                            {
                                id: 'cancle',
                                name: '取消'
                            });
                }
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
            <input id="hostCode" name="obj.hostCode" value="${obj.hostCode}" type="hidden"/>
            <input id="connectId" name="obj.connectId" value="${obj.connectId}" type="hidden"/>
            <input id="templateCode" name="obj.templateCode" value="${obj.templateCode}" type="hidden"/>
            <input id="name" name="obj.name" value="${obj.name}" type="hidden"/>
            <fieldset>
                <legend>创建镜像</legend>
                <div class="item">
                    <div class="control-label">
                        <span style="color:red">*</span>名称
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.alias" autofocus="autofocus" value="${obj.alias }"
                               maxlength="20"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">
                    </div>
                    <div class="controls">
                        <a href="images_listImage.do?type=17" id="tobuy" target="blank">查看镜像列表</a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<!-- 添加end -->
</body>
</html>
