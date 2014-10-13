<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>控制中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/openpostwindow.js"></script>
    <script type="text/javascript">

        var api = frameElement.api, W = api.opener;

        $(function () {
            $("#checkAll").click(function () {
                $('input[name="bb"]').prop("checked", this.checked);
                var num = $('input:checkbox[name="bb"]:checked').size();
                if (num >= 1) {
                    $('.aa').removeClass("gray_button").addClass("blue_button");
                } else {
                    $('.aa').removeClass("blue_button").addClass("gray_button");
                }
            })

            $(".search-btn").click(function () {
                $("#theForm").submit();
            })
            //回车搜索
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("#theForm").submit();
                    ///alert($("#theForm").serialize());
                    // $('#name').submit();//处理事件
                }
            }

            //资源使用率详细
            $(".detail").click(function () {

                /* 不支持IE   */
                var url = "vmmonitor_detail.do";
                var args = {
                    'obj.vmCode': $(this).attr("vmCode"),
                    'obj.connectCode': $(this).attr("connectCode")
                };
                var name = "资源监控详情";
                jQueryOpenPostWindow(url, args, name, null);

                /*  //提交不隐藏参数后期改掉
                 var url = "vmmonitor_detail.do?obj.vmCode=" +$(this).attr("vmCode")+"&obj.connectCode="+$(this).attr("connectCode");
                 window.open(url); //打开新窗口
                 window.focus(); //将焦点移回原窗口，即本页面窗口 */

                /*
                 var x = document.body.scrollWidth;
                 var y = document.body.scrollHeight - 45;

                 W.$.dialog({
                 id:'detail',
                 title:'资源使用率详细',
                 width: 750,
                 height: 550,
                 fixed: true,
                 lock:true,
                 parent:api,
                 content: url
                 }); */
            });

        })
        //保存快照
        function saveSnapshot(url, params) {
            mask("正在保存快照，请稍候", "0.7", "0px");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        $.dialog.tips('保存快照成功！', 1, 'success.gif', refresh);
                        $("#theForm").submit();
                    } else {
                        $.dialog.tips('保存快照失败！');
                    }
                }
            });

        }
        //进入控制台
        function enterConsole() {
            var url = "url:united_console.do?vtype=" + $(this).attr("vType") + "&uuid=" + $(this).attr("vmCode") + "&connect_id=" + $(this).attr("connectCode");
            $.dialog({
                id: 'console',
                title: '控制台',
                max: true,
                min: true,
                height: '400px',
                width: '634px',
                content: url
            });
        }
        function refresh() {
            parent.location.reload();
        }
    </script>
</head>

<body>
<!--container star-->
<div style="width: 750px;height: auto;margin: 0 auto;">
    <div class="pd-15">
        <form id="theForm" action="vmmonitor_list.do?type=<s:property value='type'/>" method="post">
            <div class="col-c8">
                <ul class="operate-list">
                    <!-- <li class="first">
                        <a href="#" class="add"></a>
                    </li> -->
                    <!--
                    <li class="gray_button aa">
                        <a href="#" class="paly" id="powerOn">启动</a>
                    </li>
                    <li class="gray_button aa">
                        <a href="#" class="close" id="powerOff">关机</a>
                    </li>
                    <li class="gray_button aa">
                        <a href="#" class="more js_more">更多操作...</a>
                        <dl class="operate-dl gray_button aa">
                            <dd><a href="#" class="upgrade">升级</a></dd>
                            <dd><a href="#" class="reset-q more reset-q1"  id="reboot">重启</a></dd>
                            <dd><a href="#" class="reset more reset1"  id="reset">重置</a></dd>
                            <dd><a href="#" class="reset-q more reset-q1" id="snapshot">新建备份</a></dd>
                            <dd><a href="javascript:;" class="reset more reset1" id="disk">新建磁盘</a></dd>
                        </dl>
                    </li> -->
                    <li class="search">
                        <input placeholder="主机名称" class="text-1" value="" id="VH_NAME" name="obj.name"/>
                        <input type="button" class="search-btn"/>
                    </li>

                </ul>
                <table id="theTable" border="0" width="100%" class="table-f4 js_table_f4"
                ">
                <thead>
                <tr>
                    <!-- <th><input type="checkbox" class="vhid" id="checkAll"/></th> -->
                    <th onclick="sort(theTable,1,'string')">名称</th>
                    <th onclick="sort(theTable,2,'percent')">CPU使用率</th>
                    <th onclick="sort(theTable,3,'ip')">内存使用率</th>
                    <th onclick="sort(theTable,4,'string')">存储使用率</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="resultList" id="theBean">
                    <tr>
                            <%-- <td><input type="checkbox" name="bb" class="code" vmCode='<s:property value="#theBean.vmCode" />'  connectCode='<s:property value="#theBean.connectCode" />' vType='<s:property value="#theBean.VH_TYPE" />'/></span></td> --%>
                        <td><span class="font-blue"><s:property value="#theBean.name"/></span></td>
                        <td><span class="font-blue"><s:property value="#theBean.cpu_usage"/>%</span></td>
                        <td><span class="font-blue"><s:property value="#theBean.mem_usage"/>%</span></td>
                        <td><span class="font-blue"><s:property value="#theBean.store_usage"/>%</span></td>
                        <td><span class="font-blue"><a href="javascript:void(0)" class="detail"
                                                       vmCode='<s:property value="#theBean.vmCode" />'
                                                       connectCode='<s:property value="#theBean.connectCode" />'>详情</a>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
                </table>
                <!-- 分页start -->
                <div class="page mgt34-b25">
                    <jsp:include page="../../inc/fenye.jsp?formId=theForm"/>
                </div>
                <!-- 分页end -->
            </div>
    </div>
    </form>
    <!--pd-15 end-->
</div>
<!--main-c1 end-->
<div class="clear"></div>
</div>

</div>
<!--container end-->
</body>
</html>