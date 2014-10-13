<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
        var ipmap = {};
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: saveServer,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });

            var v = $("#frm").validate({
                //debug:true,
                rules: {
                    'obj.name': {required: true},
                    'host.name': {required: true},
                    'obj.inner_ip': {required: true},
                    'obj.inner_port': {required: true}
                },
                messages: {
                    'obj.name': "请输入防火墙名称",
                    'host.name': "请选择主机",
                    'obj.inner_ip': "请选择IP地址",
                    'obj.inner_port': "请填写端口号"
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

            //ajax render select 主机名
            $.getJSON("server_getVmIpMap.do", function (data) {
                $("#vm_select").html('<option value="">请选择</option>');
                ipmap = data;
                var vmContent = '';
                $.each(data, function (key, value) {
                    vmContent += '<option value="' + key + '">' + key + '</option>';
                });
                $("#vm_select").append(vmContent);
            });


            $.each(ipmap, function (key, values) {
                console.log(key);
                $(values).each(function () {
                    console.log("/t" + this);
                });
            });

            //根据主机名 render ips
            $("#vm_select").change(function () {
                $("#td_ip").html("");
                var vm_name = $(this).children('option:selected').val();
                if (vm_name != "") {
                    var tdContent = '';
                    $.each(ipmap, function (key, values) {
                        console.log(key);
                        if (key == vm_name) {
                            $(values).each(function () {
                                tdContent += '<input type="radio" class="options radio-item" name="obj.inner_ip" value="' + this + '"/>' + this + '</br>';
                            });
                        }
                    });

                    $("#td_ip").append(tdContent);
                    $("#tr_ip").show();
                } else {
                    $("#tr_ip").hide();
                }
            });

            function saveServer() {
                var check = v.form();
                if (!check)    return false;

                var params = $("form").serialize();
                /*
                 $.ajax({
                 type:"POST",
                 url:"server_exists.do",
                 data:params,
                 dataType:"json",
                 success:function(data){
                 if(data.result == "exists"){
                 w.$.dialog.alert('这个实例已经创建，请更换端口号试试');
                 return false;
                 }
                 }
                 });

                 */
                var url = "server_saveServer.do";
                api.get('updateBalanceListener').saveServer(url, params);
            }
        });


    </script>
</head>
<body>
<!-- 添加start -->
<s:hidden name="ipMap" id="ipMap"></s:hidden>
<div class="modal" style="width: 600px;height: auto;margin-left: -310px;margin-top: -160px;top: 50%;">
    <div class="modal-content" id="">
        <form id="frm" class="form form-horizontal">
            <input type="hidden" name="obj.id" id="id"/>
            <s:hidden name="obj.group_id" id="group_id"></s:hidden>
            <fieldset>
                <legend>新建负载实例</legend>
                <div class="item">
                    <div class="control-label">
                        实例名
                    </div>
                    <div class="controls">
                        <input type="text" name="obj.name" autofocus="autofocus" value="${obj.name}" maxlength="20"
                               id="name"> </input>
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">主机名</div>
                    <div class="controls">
                        <div class="select-con">
                            <select id="vm_select" name="host.name" class="dropdown-select"></select>
                        </div>
                    </div>
                </div>
                <div class="item" id="tr_ip" style="display:none">
                    <div class="control-label">IP地址</div>
                    <div class="controls" id="td_ip">
                    </div>
                </div>
                <div class="item">
                    <div class="control-label">
                        负载端口
                    </div>
                    <div class="controls">
                        <input type="text" id="inner_port" name="obj.inner_port" maxlength="10" class="input-1c"
                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                               onafterpaste="this.value=this.value.replace(/\D/g,'')"/></input>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<!-- 添加end -->
</body>
</html>
