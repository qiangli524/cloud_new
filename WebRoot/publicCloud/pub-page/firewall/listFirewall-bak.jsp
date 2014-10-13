<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link4a.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<html:html locale="true">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>控制中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>

    <script type="text/javascript">
        $(function () {

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

            //查找
            $(".search-btn").click(function () {
                var name = $("#firewall").val();
                if (null != name && "" != name) {
                    $("#theForm").submit();
                }

            });

            //Enter 搜索
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("#theForm").submit();
                }
            }
            //进入添加页面
            $("#add").unbind().live('click', function () {
                var url = "url:firewall_addFirewallPage.do";
                $.dialog({
                    id: 'addFirewall',
                    title: '添加防火墙',
                    //height: 60,
                    height: 170,
                    width: 420,
                    lock: true,
                    content: url
                });
            });

            //进入规则配置页面
            $("#rule").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length == 1) {
                    var id = '';
                    $('input[name="bb"]:checked').each(function () {
                        id = $(this).attr("objId");
                    });
                    var url = "rules_listRules.do?type=24&obj.firewall_id=" + id;

                    location.href = url;
                } else {
                    if ($('input[name="bb"]:checked').length > 0) {
                        alertMsg("请仅选择一个防火墙");
                    }
                }
            });
            //进入修改页面
            $("#edit").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length == 1) {
                    var id = '';
                    $('input[name="bb"]:checked').each(function () {
                        id = $(this).attr("objId");
                    });
                    var url = "url:firewall_editFirewall.do?obj.id=" + id;
                    $.dialog({
                        id: 'editFirewall',
                        title: '修改防火墙',
                        width: 600,
                        height: 190,
                        lock:true,
                        content: url
                    });
                } else {
                    if ($('input[name="bb"]:checked').length > 0) {
                        alertMsg("请仅选择一个防火墙");
                    }
                }
            });

            //删除确认？
            $("#del").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm('确定要删除防火墙吗？')) {
                        deleteFirewall();
                    }
                }
            });

            //点击名称进入规则设置页面
            $(".rule").unbind().live('click', function () {
                var id = $(this).attr("objId");
                var url = "rules_listRules.do?type=24&obj.firewall_id=" + id;
                location.href = url;
            })

            //删除,支持批量删除
            function deleteFirewall() {
                mask("正在删除防火墙，请稍候", "info");
                var ids = '';
                $('input[name="bb"]:checked').each(function () {
                    ids += $(this).attr("objId") + ",";
                });

                var vmCode = '';
                $('input[name="bb"]:checked').each(function () {
                    vmCode += $(this).attr("objId") + ",";
                });

                $.ajax({
                    url: "firewall_deleteFirewall.do?obj.id=" + ids,
                    type: "POST",
                    data: "text",
                    dataType: "json",
                    success: function (data) {
                        removeMask();
                        $.dialog.tips(data.result, 3, 'success.gif', refresh);
                        $("#theForm").submit();
                    }
                });
            }

        })
        function addFirewall(url, params) {
            mask("正在添加防火墙，请稍候", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("添加防火墙成功！", "success", 3000);
                        $("#theForm").submit();
                    } else {
                        mask("添加防火墙失败！", "error", 3000);
                    }
                }
            });
        }
        function updateFirewall(url, params) {
            mask("正在修改防火墙，请稍候", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("修改防火墙成功！", "success", 3000);
                        $("#theForm").submit();
                    } else {
                        mask("修改防火墙失败！", "error", 3000);
                    }
                }
            });
        }
        function refresh() {
            parent.location.reload();
        }
    </script>
</head>

<body>
<!--container star-->
<div class="mainbody">
    <form id="theForm"
          action="<%=request.getContextPath() %>/firewall_listFirewall.do?type=<s:property value="type"/>"
          method="post">
        <div class="pd-20 bgcolor-1">
            <div class="bord-1 pd-10">
            <div class="utt-2 mgt-20">
                <a class="icon-add" href="javascript:void(0)" id="add">新增</a>
                <a class="icon-modify" href="javascript:void(0)" id="edit">修改</a>
                <a class="icon-del" href="javascript:void(0)" id="del">删除</a>
            </div>

            <table border="0" width="100%"  class="blue-table" >
            <thead>
            <tr>
                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                <th>名称</th>
                <th>描述</th>
                <th>创建时间</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="resultList" id="theBean">
                <tr>
                    <td>
                        <input type="checkbox" name="bb" class="code"
                               objId='<s:property value="#theBean.id" />'/>
                    </td>
                    <td><span  ><a class="rule" href="javascript:void(0);" title="设置规则"
                                                   objId="<s:property value="#theBean.id" />"><s:property
                            value="#theBean.name"/></a></span></td>
                    <td><span  ><s:property value="#theBean.description"/></span></td>
                    <td><span  ><s:property value="#theBean.create_time"/></span></td>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
            </table>
            <div class="pages mgb-10"><!-- 分页 -->
                <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
            </div>
        </div>
       </div>
    </form>
</div>
</body>
</html:html>