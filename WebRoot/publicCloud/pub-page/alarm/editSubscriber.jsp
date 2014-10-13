<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
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

    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/rules.js"></script>
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
                        callback: addSubscriber,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });

            //手机验证规则
            $.validator.addMethod("mobile", function (value, element, params) {
                var length = value.length;
                var mobile = /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请填入正确的手机号码！");
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {required: true},
                    'obj.phone': {mobile: true},
                    'obj.email': {email: true}
                },
                messages: {
                    'obj.name': "请输入通知人ID",
                    'obj.phone': {required: "请输入手机号码"},
                    'obj.email': {required: "请输入Email地址", email: "请输入正确的email地址"}
                }
            });

            //添加告警
            function addSubscriber() {
                var check = v.form();
                if (!check)return false;
                var params = $("#frm").serialize();
                var url = "subscriber_saveSubscriber.do";
                w.saveSubscriber(url, params);
            }
        });
        function refresh() {
            parent.location.reload();
        }
    </script>
</head>
<body>
<!-- 添加start -->
<div class="add-sg-rules" style="">
    <div class="modal"
         style="left: 0px; top: 203.5px; width: 600px; height: auto; margin-top: -200px; z-index: 1000;">
        <div class="modal-content" id="">
            <form class="form form-horizontal" id="frm">
                <s:hidden name="obj.id"></s:hidden>
                <%--<p class="alert alert-info">提示：同一个主机的每个监控项只能添加一份。</p>
                --%>
                <fieldset>
                    <legend>添加告警通知人</legend>
                    <div class="item">
                        <div class="control-label"><font color="red">*</font>姓名</div>
                        <div class="controls">
                            <input type="text" name="obj.name" value="${obj.name}">
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">手机号码</div>
                        <div class="controls">
                            <input type="text" name="obj.phone" value="${obj.phone }">
                            <span class="help inline">出现报警以后系统将短信发放给该手机</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">邮箱地址</div>
                        <div class="controls">
                            <input type="text" name="obj.email" value="${obj.email}">
                            <span class="help inline">出现报警以后系统将信息发送到该邮箱</span>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer"></div>
    </div>

</div>
<!-- 添加end -->
</body>
</html>
