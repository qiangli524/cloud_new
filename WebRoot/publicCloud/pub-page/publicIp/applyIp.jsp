<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
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

            var v = $("#ipObj").validate({
                rules: {
                    'ipObj.ipNums': {required: true, digits: true}
                },
                messages: {
                    'ipObj.ipNums': "请输入IP数量,且必须为正整数"
                }
            });

            function saveInfo() {
                var check = v.form();
                if (!check)return false;
                var reg = new RegExp("^([1-9])$");
                var ipnums = $("#nums").val();
                var descr = $("#descr").val();
                w.apply(descr, ipnums);
            }
        });
    </script>
</head>
<body style="overflow-y:auto;">
<div class="modal" style="width: 600px;height: auto;margin-left: -340px;margin-top: -120px;top: 50%;">
    <div class="modal-content" id="">
        <form id="ipObj" class="form form-horizontal">
            <fieldset>
                <legend>修改防火墙信息</legend>
                <div class="item">
                    <div class="control-label">
                        数量
                    </div>
                    <div class="controls">
                        <input type="text" name="ipObj.ipNums" autofocus="autofocus" value="${ipObj.ipNums}"
                               maxlength="20" id="nums"></input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">描述</div>
                    <div class="controls">
                        <textarea name="ipObj.description"
                                  style="margin: 0px; width: 400px; height: 112px;" id="descr"></textarea>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html:html>