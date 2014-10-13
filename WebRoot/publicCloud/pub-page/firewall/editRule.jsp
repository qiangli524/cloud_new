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
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
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
                        callback: updateRules,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
            //后台传过来的对象为前台赋值
            setValues();

            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {required: true},
                    'obj.priority': {required: true},
                    'obj.val1': {required: true, digits: true},
                    'obj.val2': {digits: true}
                },
                messages: {
                    'obj.name': "请输入规则名称",
                    'obj.priority': "请输入优先级",
                    'obj.val1': {required: "请输入端口号", digits: "请输入正确端口号"},
                    'obj.val2': "请输入正确端口号"
                }
            });

            //快速设置
            $(".btn-shortcut").click(function () {
                var dataType = $(this).attr("data-type");
                quickRules(dataType);
            });
            //特殊协议特殊处理
            var val1_html = $(".item.val1").html();
            var val2_html = $(".item.val2").html();
            $("select[name='obj.protocol']").change(function () {
                var protocol = $(this).val();
                switch (protocol) {
                    case 'gre':
                    case 'ipip':
                        $(".item.val1").html("");
                        $(".item.val2").html("");
                        break;
                    case 'icmp':
                        renderIcmp();
                        break;
                    default :
                        $(".item.val1").html(val1_html);
                        $(".item.val2").html(val2_html);
                }
            });

            //后台传过来的对象为前台赋值
            function setValues() {
                var params = {"obj.id": $("#id").val(), "obj.firewall_id": $("#firewall_id").val()};
                $.ajax({
                    url: "rules_ajaxGetRules.do",
                    type: "POST",
                    data: params,
                    dataType: "json",
                    success: function (data) {
                        var obj = data.result;
                        if (obj != "error") {
                            $("#id").val(obj.id);
                            $("#firewall_id").val(obj.firewall_id);
                            $("input[name='obj.name']").val(obj.name);
                            $("input[name='obj.priority']").val(obj.priority);
                            $("select[name='obj.direction']").val(obj.direction);
                            $("select[name='obj.action']").val(obj.action);
                            $("select[name='obj.protocol']").val(obj.protocol);
                            $("select[name='obj.protocol']").trigger('change');
                            $("[name='obj.val1']").val(obj.val1);
                            $("[name='obj.val2']").val(obj.val2);
                            $("[name='obj.val3']").val(obj.val3);
                        }
                    }
                });
            }

            //更新规则
            function updateRules() {
                var check = v.form();
                if (!check)return false;

                var params = $("#frm").serialize();
                var url = "rules_updateRules.do";
                w.updateRules(url, params);
            }

            //渲染ICMP类型 ICMP协议代码
            function renderIcmp() {
                var val1_select_html = '<div class="control-label">ICMP类型</div>' +
                        '<div class="controls">' +
                        '<div class="select-con">' +
                        '<select class="dropdown-select" name="obj.val1">';
                $.each(icmp, function (i, icmpdata) {
                    if (i == 0) {
                        val1_select_html += '<option value="' + icmpdata.icmpType.value + '" data-value="' + icmpdata.icmpType.value + '" data-index="' + icmpdata.icmpType.date_index + '" selected>' + icmpdata.icmpType.description + '</option>';
                    } else {
                        val1_select_html += '<option value="' + icmpdata.icmpType.value + '" data-value="' + icmpdata.icmpType.value + '" data-index="' + icmpdata.icmpType.date_index + '">' + icmpdata.icmpType.description + '</option>';
                    }
                })
                val1_select_html += '</select></div></div></div>';
                $(".item.val1").html(val1_select_html);
                $("select[name='obj.val1']").trigger('change');
            }

            //级联选择ICMP协议
            $("select[name='obj.val1']").live("change", function () {
                var data_index = $(this).children("option:selected").attr("data-index");
                var val2_select_html = '<div class="control-label">ICMP代码</div>' +
                        '<div class="controls">' +
                        '<div class="select-con">' +
                        '<select class="dropdown-select" name="obj.val2">';
                $.each(icmp, function (i, icmpdata) {
                    if (null != data_index && icmpdata.icmpType.date_index == data_index) {
                        $.each(icmpdata.icmpCode, function (i, code) {
                            val2_select_html += '<option value="' + code.value + '"data-index="' + i + '">' + code.description + '</option>';
                        })
                        val2_select_html += '</select></div></div></div>';
                        $(".item.val2").html(val2_select_html);
                    }
                });
            });
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
                <s:hidden id="id" name="obj.id"></s:hidden>
                <s:hidden id="firewall_id" name="obj.firewall_id"></s:hidden>
                <p class="alert alert-info">提示：编辑完所有规则后，请点击规则列表上方的 “更新规则”
                    按钮，使得新规则生效。</p>

                <div class="shortcut">
                    <ul>
                        <li>快速设置</li>
                        <li><a class="btn-shortcut" data-type="ping"
                               href="javascript:void(0);">ping</a></li>
                        <li><a class="btn-shortcut" data-type="ssh"
                               href="javascript:void(0);">ssh</a></li>
                        <li><a class="btn-shortcut" data-type="http"
                               href="javascript:void(0);">http</a></li>
                        <li><a class="btn-shortcut" data-type="https"
                               href="javascript:void(0);">https</a></li>
                        <li><a class="btn-shortcut" data-type="ftp"
                               href="javascript:void(0);">ftp</a></li>
                        <li><a class="btn-shortcut" data-type="openvpn"
                               href="javascript:void(0);">openvpn</a></li>
                        <li><a class="btn-shortcut" data-type="remote"
                               href="javascript:void(0);">远程桌面</a></li>
                        <li><a class="btn-shortcut" data-type="gre"
                               href="javascript:void(0);">gre</a></li>
                        <li><a class="btn-shortcut" data-type="pptp"
                               href="javascript:void(0);">pptp</a></li>
                        <li><a class="btn-shortcut" data-type="ipip"
                               href="javascript:void(0);">ipip</a></li>
                    </ul>
                </div>
                <fieldset>
                    <legend>添加规则</legend>
                    <div class="item">
                        <div class="control-label">名称</div>
                        <div class="controls">
                            <input type="text" name="obj.name" autofocus="autofocus">
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">优先级</div>
                        <div class="controls">
                            <input type="text" value="2" name="obj.priority"><span
                                class="help">最多可以定义100条规则</span>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">方向</div>
                        <div class="controls">
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.direction">
                                    <option
                                            value="0" selected>下行
                                    </option>
                                    <option value="1">上行</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">操作</div>
                        <div class="controls">
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.action">
                                    <option
                                            value="1" selected>接受
                                    </option>
                                    <option value="0">拒绝</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="control-label">协议</div>
                        <div class="controls">
                            <div class="select-con">
                                <select class="dropdown-select" name="obj.protocol">
                                    <option value="tcp" selected>TCP</option>
                                    <option value="udp">UDP</option>
                                    <option value="icmp">ICMP</option>
                                    <option value="gre">GRE</option>
                                    <option value="esp">ESP</option>
                                    <option value="ah">AH</option>
                                    <option value="ipip">IPIP</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="item val1">
                        <div class="control-label">起始端口</div>
                        <div class="controls">
                            <input name="obj.val1" type="text" value="">
                        </div>
                    </div>
                    <div class="item val2">
                        <div class="control-label">结束端口</div>
                        <div class="controls">
                            <input name="obj.val2" type="text" value="">
                        </div>
                    </div>
                    <div class="item val3">
                        <div class="control-label">源IP</div>
                        <div class="controls">
                            <input name="obj.val3" type="text" value="">
                            <span class="help">例如 192.168.9.1/24，不填表示所有IP地址</span>
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
