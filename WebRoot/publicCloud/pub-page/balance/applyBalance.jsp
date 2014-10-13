<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>申请负载均衡</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                id: 'OkAnd',
                name: '确定',
                callback: apply_balance,
                focus: true
            }, {
                id: 'cancle',
                name: '取消'
            });

            var v = $("#obj").validate({
                rules: {
                    'obj.name': {required: true}/*,
                    'obj.load_port': {required: true, digits: true},
                    'obj.outer_ip': {required: true}*/
                },
                messages: {
                    'obj.name': "请输入负载均衡名称"
                 /*   'obj.load_port': {required: "请输入端口号", digits: "请输入正确的端口号"},
                    'obj.outer_ip': "请选择公网IP地址"*/
                },
                errorPlacement: function (error, element) {
                    if (element.is(":radio"))
                        error.appendTo(element.parent());
                    else if (element.is("select"))
                        error.appendTo(element.parent().parent());
                    else
                        error.appendTo(element.parent());
                }
            });


            //负载类型选择事件
            /*var load_ip_html = $("#tr_load_ip").html();*/
            /*
            $("#lb_type").change(function () {
                var lbtype = $(this).children('option:selected').val();
                if (lbtype == 1) {
                    $("#tr_load_ip").html(load_ip_html);
                    //ajax render select 公网IP
                    $.getJSON("balance_ajaxGetIPList.do", function (data) {
                        $("#outer_ip_select").html('<option value="">请选择</option>');
                        var vmContent = '';
                        var hasIP = true;
                        $.each(data, function (i, obj) {
                            if (data.length == 0) {
                                hasIP = false;
                            }
                            vmContent += '<option value="' + obj.ipaddress + '">' + obj.ipaddress + '</option>';
                        });

                        //判断是否还有公网IP
                        if (hasIP) {
                            $("#outer_ip_select").append(vmContent);
                            $("#tr_load_ip").show();
                        } else {
                            $("#info").html("Sorry！您已经没有可用的公网IP");
                            $("#lb_type").val("2");
                            $("#info").show();
                        }
                    });
                } else {
                    $("#tr_load_ip").html("");
                    $("#info").hide();
                }
            });*/


            //保存
            function apply_balance() {
                var check = v.form();
                if (!check)return false;

                var params = $("#obj").serialize();
               /* $.ajax({
                    type: "POST",
                    url: "balance_exists.do",
                    data: params,
                    dataType: "json",
                    success: function (data) {
                        if (data.result == "exists") {
                            w.$.dialog.alert('这个负载已经创建，请更换IP或端口号试试');
                            return false;
                        }
                    }
                });*/


                var url = "balance_saveBalance.do";
                w.saveBalance(url, params);
            }

        });
    </script>
</head>

<body>
<div class="modal" style="width: 600px;
height: auto;
margin-left: -290px;
margin-top: -122px;
top: 50%;">
    <div class="modal-content" id="">
        <form id="obj" class="form form-horizontal">
            <input type="hidden" name="obj.id" id="id"/>
            <fieldset>
                <legend>新增负载均衡</legend>

                <p class="alert alert-error" style="width: 600px;height: auto;margin-left: 60px; display:none;"
                   id="info"></p>

                <div class="item">
                    <div class="control-label">
                        名称
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.name" autofocus="autofocus" value="${obj.name}" maxlength="20"
                               id="name"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">负载类型</div>
                    <div class="controls">
                        <div class="select-con">
                            <select class="dropdown-select" name="obj.type">
                                <option value="1">公网负载</option>
                                <option value="2" selected>内网负载</option>
                            </select>
                        </div>
                    </div>
                </div>
                <%--<div class="item" id="tr_load_ip" style="display:none">
                    <div class="control-label">公网IP</div>
                    <div class="controls">
                        <div class="select-con">
                            <select class="dropdown-select" name="obj.outer_ip" id="outer_ip_select">
                            </select>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">
                        负载端口
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.load_port" value="${obj.load_port}" maxlength="20" id="load_port"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">描述</div>
                    <div class="controls">
                        <textarea name="obj.description"
                                  style="margin: 0px; width: 370px; height: 82px;" id="description"></textarea>
                    </div>
                </div>--%>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
