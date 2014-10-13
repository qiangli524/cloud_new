<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>应用修改</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/fenye.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            /* api.button({
             id : 'OkAnd',
             name : '确定',
             callback : updateBalance,
             focus : true
             }, {
             id : 'cancle',
             name : '取消'
             }); */
            api.button({
                id: 'cancle',
                name: '确定'
            });
            //全选
            $("#checkAll").click(function () {
                $('input[name="bb"]').prop("checked", this.checked);

                var num = $('input:checkbox[name="bb"]:checked').size();
                if (num >= 1) {
                    $('.aa').removeClass("gray_button").addClass("blue_button");
                } else {
                    $('.aa').removeClass("blue_button").addClass("gray_button");
                }
            });

            //加载负载实例
            renderSer(1);

            //添加实例
            $("#addSer").click(function () {
                var url = "url:server_createServer.do?obj.group_id=" + $("#group_id").val();
                w.$.dialog({
                    id: 'createServer',
                    title: '添加负载实例',
                    width: '500px',
                    height: '350px',
                    max: true,
                    min: true,
                    fixed: true,
                    lock: true,
                    parent: api,
                    content: url
                });
            })


            //删除实例
            $("#deleteSer").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm("确定要删除负载实例吗？")) {
                        deleteSer();
                    }
                }
            });


        });

        //删除实例,支持批量删除
        function deleteSer() {
            mask("正在删除负载实例，请稍候", "info");
            var ids = '';
            $('input[name="bb"]:checked').each(function () {
                ids += $(this).attr("value") + ",";
            });

            $.ajax({
                url: "server_deleteServer.do?obj.id=" + ids,
                type: "POST",
                data: "text",
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == 'success') {
                        mask("删除负载实例成功！", "success", 3000);
                        renderSer(1);
                    } else {
                        mask("删除负载实例失败！", "error", 3000);
                    }
                }
            });
        }

        //保存负载实例
        function saveServer(url, params) {
            //1.保存负载实例
            mask("正在负载实例，请稍候", "0.7", "0px");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        renderServer(data.server);
                    }
                }
            });
        }
        //save render
        function renderServer(ser) {
            var html = '<tr>' +
                    '<td><input type="checkbox" name="bb" class="code" value="' + ser.id + '"/></td>' +
                    '<td><span class="font-blue">' + ser.id + '</td>' +
                    '<td><span class="font-blue">' + ser.name + '</td>' +
                    '<td><span class="font-blue">' + ser.inner_ip + '</td>' +
                    '<td><span class="font-blue">' + ser.inner_port + '</td>' +
                    '</tr>';
            $('#ser_tbody').append(html);
            renderSer(1);
        }

        //跳到指定页面
        function gotoPage(page) {
            if (null != page && "" != page) {
                renderSer(page);
            }
        }

        //edit render
        function renderSer(page) {
            $('#ser_tbody').html("");
            var params = {
                "PAGINATION_CURRENT_PAGE_NO": page, //分页用
                "PAGINATOR_FLAG": "true", //分页用
                "PGSZIE": "5",
                "obj.group_id": $("#group_id").val()
            }
            $.ajax({
                type: 'post',
                data: params,
                dataType: 'json',
                url: 'server_listServer.do',
                success: function (data) {
                    if (data.length != 0) {
                        $(data.list).each(function (i, e) {
                            var html = '<tr>' +
                                    '<td><input type="checkbox" name="bb" class="code" value="' + e.id + '"/></td>' +
                                    '<td><span class="font-blue">' + e.id + '</td>' +
                                    '<td><span class="font-blue">' + e.name + '</td>' +
                                    '<td><span class="font-blue">' + e.inner_ip + '</td>' +
                                    '<td><span class="font-blue">' + e.inner_port + '</td>' +
                                    '</tr>';

                            $('#ser_tbody').append(html);
                        });
                        ajaxFenye(data.page);
                    }
                },
                error: function (data, textStatus) {
                    console.log('error:' + data);
                }
            });
        }
        //更新负载
        function updateBalance() {
            var params = $("form").serialize();
            var url = "balance_saveBalance.do";
            w.saveBalance(url, params);
        }
    </script>
</head>

<body>

<s:form action="" method="post" id="obj">
    <s:hidden name="obj.id" id="listener_id"></s:hidden>
    <s:hidden name="grpObj.id" id="group_id"></s:hidden>
    <div style="width: 750px;height: auto;margin: 0 auto;">
        <!--文字介绍 star-->
        <p style="font-size: 15px;line-height: 30px;padding: 10px 20px;">
            <span style="font-color:red">监听器名称</span><s:property value="obj.name"/>
            <s:if test='#obj.public_ip != null && #obj.public_ip != ""'>
                <span> 外网IP:</span>
                <s:property value="obj.outer_ip"/>
            </s:if>
            <span> 监听端口:</span><s:property value="obj.load_port"/>
        </p>
        <!--文字介绍 end-->
        <div class="pd-15">
            <div class="col-c8" style="padding: 10px;
											height: 360px;
											border: 1px solid #DCDCDC;
											box-shadow: 1px 2px 5px #ccc;
											-webkit-box-shadow: 1px 2px 5px #ccc;">
                <ul class="operate-list">
                    <li class="first">
                        <a href="#" class="add" id="addSer"></a>
                    </li>
                    <li class="gray_button aa">
                        <a href="#" class="delete" id="deleteSer">删除</a>
                    </li>
                </ul>
                <table id="theTable" border="0" width="100%" class="table-f4 js_table_f4"
                ">
                <thead>
                <tr>
                    <th><input type="checkbox" class="serid" id="checkAll"/></th>
                    <th>ID</th>
                    <th>名称</th>
                    <th>内网IP</th>
                    <th>端口</th>
                </tr>
                </thead>
                <tbody id="ser_tbody">
                </tbody>
                </table>
            </div>
            <!-- 分页start -->
            <div class="page mgt34-b25" id="ajaxpage">
            </div>
            <!-- 分页end -->
        </div>
        <!--pd-15 end-->
    </div>
</s:form>
</body>
</html>
