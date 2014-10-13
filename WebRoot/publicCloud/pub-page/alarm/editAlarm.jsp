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
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/alarm_kpi.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>

    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: addAlarm,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });

            //加载kpi指标
            renderKpiInfo();
            function renderKpiInfo() {
                var kpi_value_html = "";
                $.each(alarm_kpi, function (i, kpi) {
                    kpi_value_html += '<option value="' + kpi.kpi_id + '" >' + kpi.kpi_name + '</option>';
                })
                $("#kpi_value").append(kpi_value_html);
                $("#kpi_value").trigger('change');
            }


            //监控项选择事件
            $("#kpi_value").unbind().live("change", function () {
                var kpi_id = $(this).children("option:selected").val();
                $.each(alarm_kpi, function (i, kpi) {
                    if (kpi_id == kpi.kpi_id) {
                        $("#description").html(kpi.description);
                        $("#unit").html(kpi.unit);
                        $("input[name='obj.unit']").val(kpi.unit);
                    }
                });
            });

            //填充字段值
            renderInfo();

            var notice
            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.notice_group_id': {required: true},
                    'obj.threshold': {required: true}
                },
                messages: {
                    'obj.notice_group_id': "请选择告警组",
                    'obj.threshold': "请输入阀值"
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

            //加载告警组
            renderAlarmGroup();
            var notice_group_id = "${obj.notice_group_id}";
            //var groups = notice_group_id.split(",");

            function renderAlarmGroup() {
                $.ajax({
                    url: "subscriberGroup_ajaxGetSubscriberGroups.do",
                    type: "POST",
                    //data:params,
                    dataType: "json",
                    success: function (data) {
                        if (data.length > 0) {
                            var option_html = ""
                            $.each(data, function (index, group) {
                                if (notice_group_id.match(group.id) != null) {
                                    option_html += '<option value="' + group.id + '" selected>' + group.name + '</option>';
                                } else {
                                    option_html += '<option value="' + group.id + '">' + group.name + '</option>';
                                }
                            })
                            $("#group_id").append(option_html);
                        }
                    }
                });
            }

            //添加告警
            function addAlarm() {
                var check = v.form();
                if (!check)return false;
                $("#kpi_name").val($("#kpi_value").find("option:selected").text());
                $("#statistics").val($("#statistics_text").find("option:selected").text());
                var params = $("#frm").serialize();
                var url = "vmalarm_saveAlarm.do";
                w.saveAlarm(url, params);
            }
        });
        function renderInfo() {
            var kpi_value = "${obj.kpi_value}";
            $("#kpi_id").val(kpi_value);
            $.each(alarm_kpi, function (i, kpi) {
                if (kpi_value == kpi.kpi_id) {
                    $('#kpi_value option[value="' + kpi.kpi_id + '"]').attr("selected", true);
                    $("#description").html(kpi.description);
                    $("#unit").html(kpi.unit);
                    $("input[name='obj.unit']").val(kpi.unit);
                }
            })
            var statistics = "${obj.statistics}";
            $('#statistics_text option[value="' + statistics + '"]').attr("selected", true);

            var comparison_operator = "${obj.comparison_operator}";
            $('#comparison_operator option[value="' + comparison_operator + '"]').attr("selected", true);

            var alarm_count = "${obj.alarm_count}";
            $('#alarm_count option[value="' + alarm_count + '"]').attr("selected", true);

        }
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
                <p class="alert alert-info">提示：同一个主机的每个监控项只能添加一份。</p>
                <fieldset>
                    <legend>修改告警</legend>
                    <div class="item">
                        <div class="control-label">主机名称</div>
                        <div class="controls">
                            <input type="text" name="obj.vm_name" value="${obj.vm_name}" readonly="readonly">
                            <input type="hidden" name="obj.vm_id" value="${obj.vm_id }">
                            <input type="hidden" name="obj.connect_id" value="${obj.connect_id }">
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">监控项</div>
                        <div class="controls">
                            <input type="hidden" name="obj.kpi_name" id="kpi_name"/>
                            <input type="hidden" name="obj.kpi_value" id="kpi_id"/>

                            <div class="select-con">
                                <select class="dropdown-select" id="kpi_value" disabled="disabled">
                                </select>
                            </div>
                            <span class="help inline" id="description"></span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">统计方法</div>
                        <div class="controls">
                            <div class="select-con">
                                <input type="hidden" name="statistics" id="statistics"/>
                                <select class="dropdown-select" name="obj.statistics" id="statistics_text">
                                    <option value="SampleCount">采样计数值</option>
                                </select>
                            </div>
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.comparison_operator" id="comparison_operator">
                                    <option value=">=">&gt;=</option>
                                    <option value=">">&gt;</option>
                                    <option value="<">&lt;</option>
                                    <option value="<=">&lt;=</option>
                                    <option value="=">=</option>
                                    <option value="!=">!=</option>
                                </select>
                            </div>
                            <span style="width: 130px;font-size: 14px;vertical-align: middle;padding-right: 7px;">阈值</span>
                            <input class="mini" type="text" name="obj.threshold" value="${obj.threshold }">
                            <span class="help inline" id="unit">%</span>
                            <input type="hidden" name="obj.unit" value="%"/>
                            <span class="help inline">阈值仅支持输入数字</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">告警级别</div>
                        <div class="controls">
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.alarm_level" value="${obj.alarm_level}">
                                    <option value="1">严重</option>
                                    <option value="2">重要</option>
                                    <option value="3">一般</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="item val1">
                        <div class="control-label">告警次数</div>
                        <div class="controls">
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.alarm_count" id="alarm_count">
                                    <option value="1">1</option>
                                    <option value="3">3</option>
                                    <option value="5">5</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="item val1">
                        <div class="control-label">告警组</div>
                        <div class="controls">
                            <select multiple="multiple" name="obj.notice_group_id" id="group_id"
                                    style="min-width: 200px;min-height: 70px;">
                            </select>
                            <span class="help">出现问题通知这些告警联系人,蓝色表示选中,支持多选</span>
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
