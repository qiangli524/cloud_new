<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>控制中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
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
<div class="container">
    <!--col-c7 star-->
    <div class="col-c7">
        <!--left star-->
        <%--<jsp:include page="../order.jsp"/>--%>
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
            <!--标题 star-->
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_firewall.gif" width="65"
                    height="65"/>防火墙</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">为了加强位于基础网络中的主机或路由器的安全性，您可以在主机或路由器之前放置防火墙 (Fire Wall)。为每个用户提供了一个缺省防火墙（ID
                之后带有星标）。您也可以自建更多的防火墙。自建防火墙在初始状态下，所有端口都是封闭的，您需要建立规则以打开相应的端口。</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/firewall_listFirewall.do?type=<s:property value="type"/>"
                          method="post">
                        <ul class="operate-list">
                            <!-- <li class="first"> -->
                            <li class="blue_button">
                                <a href="javascript:void(0);" class="add" id="add"></a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="edit" id="edit">修改</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="delete" id="del">删除</a>
                            </li>
                            <!--   <li class="gray_button aa">
                                 <a href="#" class="reset" id="rule">配置规则</a>
                             </li> -->
                            <li class="search">
                                <input type="text" class="text-1" placeholder="防火墙名称" name="obj.name" id="firewall"
                                       value="<s:property value='obj.name'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>

                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>创建时间</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'/>
                                    </td>
                                    <td><span class="font-blue"><a class="rule" href="javascript:void(0);" title="设置规则"
                                                                   objId="<s:property value="#theBean.id" />"><s:property
                                            value="#theBean.name"/></a></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.description"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.create_time"/></span></td>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>

                        <!-- 分页start -->
                        <div class="page mgt34-b25">
                            <jsp:include page="../../inc/fenye.jsp?formId=theForm"/>
                        </div>
                        <!-- 分页end -->
                    </form>
                </div>
            </div>
            <!--pd-15 end-->
        </div>
        <!--main-c1 end-->
        <div class="clear"></div>
    </div>
    <!--col-c7 end-->
</div>
<!--container end-->
<!--版权 star-->
<div class="copy">
    <div class="copy-con ac">
        <p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号 增值电信业务经营许可证A2.B1.B2-20090001</p>
    </div>
</div>
<!--版权 end-->
</body>

</html>
