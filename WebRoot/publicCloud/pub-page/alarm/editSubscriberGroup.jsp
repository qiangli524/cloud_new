<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/alarmcss.css" type="text/css"/>
    <script type="text/javascript"  src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/rules.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/jsp/css/validation.css"/>
    <script type="text/javascript"  src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>

    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {required: true}
                },
                messages: {
                    'obj.name': "请输入告警组名称"
                }
            });
            //渲染页面
            renderSubscribers();

            //allCheck点击事件
            $('input[name="allCheck"]').live("click", function () {
                $(this).parent().parent().find(":checkbox").prop("checked", this.checked);
            });

            //phoneCheck 点击事件
            $('input[name="phoneCheck"]').live("click", function () {
                var emailCheck = $(this).parent().parent().parent().find('input[name="emailCheck"]');
                var allCheck = $(this).parent().parent().parent().find('input[name="allCheck"]');
                if ($(this).prop("checked") && emailCheck.prop("checked")) {
                    allCheck.prop('checked', true);
                } else {
                    allCheck.prop('checked', '');
                }
            });
            //emailCheck 点击事件
            $('input[name="emailCheck"]').live("click", function () {
                var phoneCheck = $(this).parent().parent().parent().find('input[name="phoneCheck"]');
                var allCheck = $(this).parent().parent().parent().find('input[name="allCheck"]');
                if ($(this).prop("checked") && phoneCheck.prop("checked")) {
                    allCheck.prop('checked', true);
                } else {
                    allCheck.prop('checked', '');
                }
            });

            //添加告警
            function addSubscriberGroup() {
                var check = v.form();
                if (!check)    return false;

                var params = $("#frm").serialize();
                var url = "subscriberGroup_saveSubscriberGroup.do";
                w.saveSubscriberGroup(url, params);
            }

            //构建订阅者表格
            function renderSubscribers() {
                var allList = new Array();
                var groupList = new Array();
                //获取所有告警通知人
                $.ajax({
                    url: "subscriber_ajaxGetSubscribers.do",
                    async: false,
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        allList = data;
                    }
                });

                //获取告警组内告警通知人
                $.ajax({
                    url: "subscriber_ajaxGetExtendSubscribers.do",
                    async: false,
                    type: "POST",
                    data: {"obj.group_id": $("#group_id").val()},
                    dataType: "json",
                    success: function (data) {
                        groupList = data;
                    }
                });

                //渲染告警通知人表格
                if (allList.length > 0) {
                    var tr_html = ""
                    $.each(allList, function (index, user) {
                        //如果是组内用户判断订阅信息
                        if (index % 2 == 0) {
                            tr_html = '<tr><td>';
                        } else {
                            tr_html = '<tr class="odd"><td>';
                        }
                        var flag = false; //标示 组中是否存在这个人
                        $.each(groupList, function (index, grpuser) {
                            if (user.id == grpuser.id) {
                                flag = true;

                                if (!((grpuser.phone == null || grpuser.phone == "") && (grpuser.phone == null || grpuser.phone == ""))) {
                                    if (grpuser.subscribe_type == "all") {
                                        tr_html += '<input type="checkbox" name="allCheck" checked>';
                                    } else {
                                        tr_html += '<input type="checkbox" name="allCheck" >';
                                    }
                                }
                                tr_html += '</td><td><nobr>' + grpuser.name + '</nobr></td><td><nobr>';
                                if (grpuser.phone != null && grpuser.phone != "") {
                                    if (grpuser.subscribe_type == "all" || grpuser.subscribe_type == "sms") {
                                        tr_html += '<input type="checkbox" name="phoneCheck" checked value="' + grpuser.id + '">' + grpuser.phone;
                                    } else {
                                        tr_html += '<input type="checkbox" name="phoneCheck" value="' + grpuser.id + '">' + grpuser.phone;
                                    }
                                }
                                tr_html += '</nobr></td><td><nobr>';
                                if (grpuser.email != null && grpuser.email != "") {
                                    if (grpuser.subscribe_type == "all" || grpuser.subscribe_type == "email") {
                                        tr_html += '<input type="checkbox" name="emailCheck" checked value="' + grpuser.id + '">' + grpuser.email;
                                    } else {
                                        tr_html += '<input type="checkbox" name="emailCheck" value="' + grpuser.id + '">' + grpuser.email;
                                    }
                                }
                            }
                        })
                        if (!flag) {
                            tr_html += '<input type="checkbox" name="allCheck" >';
                            tr_html += '</td><td><nobr>' + user.name + '</nobr></td><td><nobr>';
                            tr_html += '<input type="checkbox" name="phoneCheck" value="' + user.id + '">' + user.phone;
                            tr_html += '</nobr></td><td><nobr>';
                            tr_html += '<input type="checkbox" name="emailCheck" value="' + user.id + '">' + user.email;
                        }
                        tr_html += '</nobr></td></tr>';
                        $("#user_row").append(tr_html);
                    })
                    api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: addSubscriberGroup,
                        focus: true
                    }, {
                        id: 'cancle',
                        name: '取消'
                    });
                } else {
                    $(".item.group").hide();
                    $("#info").show();
                    api.button({
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
<div class="add-sg-rules" style="">
    <div class="modal"
         style="left: 0px; top: 203.5px; width: 600px; height: auto; margin-top: -200px; z-index: 1000;margin-left: -50;">
        <div class="modal-content" id="">
            <form class="form form-horizontal" id="frm">
                <s:hidden id="group_id" name="obj.id"></s:hidden>
                <p class="alert alert-error" id="info" style="display:none;margin-left: 60;">提示：您还没有添加告警用户，不能创建告警组。</p>
                <fieldset>
                    <legend>添加告警组</legend>
                    <div class="item">
                        <div class="control-label"><font color="red">*</font>告警组名称</div>
                        <div class="controls">
                            <input type="text" name="obj.name" value="${obj.name}">
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">描述</div>
                        <div class="controls">
                            <textarea name="obj.description"
                                      style="margin: 0px;width: 450px;height: 157px;">${obj.description}</textarea>
                        </div>
                    </div>
                    <div class="item group">
                        <div class="control-label">选择组成员</div>
                        <div class="controls">
                            <div id="group_member_list_select" style="height: 230px;width: 450px;overflow: auto;">
                                <table cellspacing="0" cellpadding="0" class="table_select"
                                       style="font-size: 14px;text-align: left;">
                                    <tbody id="user_row">
                                    <tr>
                                        <th>
                                            <nobr>选择</nobr>
                                        </th>
                                        <th>
                                            <nobr>联系人</nobr>
                                        </th>
                                        <th>
                                            <nobr>手机号码(短信)</nobr>
                                        </th>
                                        <th>
                                            <nobr>Email</nobr>
                                        </th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
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
