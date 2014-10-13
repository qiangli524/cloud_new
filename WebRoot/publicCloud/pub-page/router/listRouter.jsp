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
            //检索
            $("#searchForm").click(function () {
                $("#obj").submit();
            });

            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("#obj").submit();
                }
            }

            //申请虚拟路由器
            $("#apply").click(function () {
                $.dialog({
                    id: 'applyRouter',
                    title: '申请虚拟路由器',
                    width: '500px',
                    height: '300px',
                    lock: true,
                    content: 'url:router_addRouter.do'
                });
            });

            //重命名点击事件
            $(".btn-w8").click(function () {
                var newnameInput = $(this).parent().parent().find(".input-compile");

                if (newnameInput.val() == null || newnameInput.val().trim() == "") {
                    alertMsg("请填写名称");
                    return;
                }
                var oldnameP = $(this).parent().parent().siblings("p");
                var objId = $(this).attr("objId");
                var params = {
                    "obj.id": objId,
                    "obj.name": newnameInput.val()
                };

                $.ajax({
                    url: "router_saveRouter.do",
                    type: "POST",
                    data: params,
                    dataType: "json",
                    success: function (data) {
                        if (data != -1) {
                            mask("重命名成功！", "success", 3000);
                            oldnameP.text(newnameInput.val());
                        } else {
                            mask("重命名失败！", "error", 3000);
                        }
                    }
                });

                $(this).parents(".compile-con").hide();
            });

            //修改
            $("#update").click(function () {
                var id = '';
                var count = 0;
                $("input:checkbox[name='bb']:checked").each(function () {
                    id = $(this).val();
                    count++;
                });
                if (count > 1) {
                    return false;
                    alertMsg('请选择一项进行修改');
                }
                $.dialog({
                    id: 'editRouter',
                    title: '申请虚拟路由器',
                    width: '500px',
                    height: '300px',
                    lock:true,
                    content: 'url:router_addRouter.do?obj.id=' + id
                });
            });
            //删除路由
            $("#delete").click(function () {
                var id = '';
                var count = 0;
                $("input:checkbox[name='bb']:checked").each(function () {
                    id = $(this).val();
                    count++;
                });
                if (count > 1) {
                    return false;
                    alertMsg('请选择一项进行修改');
                }
                if (confirm("确认删除虚拟路由器吗？")) {
                    var url = "router_deleteRouter.do?obj.id=" + id;
                    $.ajax({
                        type: 'post',
                        url: url,
                        dataType: 'text',
                        success: function (msg) {
                            if (msg == -1) {
                                alertMsg("删除虚拟路由器失败");
                            } else {
                                $("#obj").submit();
                            }
                        }
                    });
                }
            });
            //删除公网ip
            $("#release").click(function () {
                var ip = "";
                var id = "";
                var status = 0;
                var count = 0;
                $("input:checkbox[name='bb']:checked").each(function () {
                    ip += $(this).attr("ipaddr") + ",";
                    id += $(this).val() + ",";
                    count++;
                    status = $(this).attr("status");
                    if (status == 1) {//该IP被虚拟机使用
                        alertMsg("该IP被绑定在虚拟机上，请先与云主机解除绑定");
                        return false;
                    }
                });
                var url = "publicip_release.do?ipObj.ipaddress=" + ip + "&ipObj.id=" + id;
                if (confirm("您确认要删除选中的公网ip吗？")) {
                    $.ajax({
                        type: 'post',
                        url: url,
                        dataType: 'text',
                        success: function (msg) {
                            if (msg == -1) {
                                alertMsg("公网ip地址删除失败");
                            } else {
                                $("#theForm").submit();
                            }
                        }
                    });
                }
            });
        });

        function applyRouter(url) {
            $.ajax({
                type: 'post',
                dataType: 'text',
                url: url,
                success: function (msg) {
                    if (msg == -1) {
                        alertMsg("申请虚拟路由器失败");
                    } else {
                        $("#obj").submit();
                    }
                }
            });
        }

    </script>
</head>

<body>
<!--container star-->
<div class="container">
    <!--col-c7 star-->
    <div class="col-c7">
        <!--left star-->
       <%-- <jsp:include page="../order.jsp"/>--%>
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_router.gif" width="65"
                    height="65"/>虚拟路由器</h2>

            <p class="p-con4">
                虚拟路由器用于受管私有网络之间互联。
            </p>

            <div class="pd-15">
                <form id="obj" action="router_listRouters.do?type=<s:property value="type"/>" method="post">
                    <div class="col-c8">
                        <ul class="operate-list">
                            <li class="first">
                                <a href="javascript:;" class="add" id="apply"></a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="edit" id="update">修改</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="delete" id="delete">删除</a>
                            </li>
                            <!--
                            <li class="gray_button aa">
                                <a href="#" class="more js_more">更多操作...</a>
                                <dl class="operate-dl gray_button aa">

                                    <dd><a href="javascript:;" class="pwd " id="bind">绑定到主机</a></dd>
                                    <dd><a href="javascript:;" class="pwd  " id="unbind">从主机解绑</a></dd>

                                </dl>
                            </li>
                            -->
                            <li class="search">
                                <input type="text" class="text-1" size="22" name="obj.name"
                                       value="<s:property value='obj.name'/>"/>
                                <input type="button" class="search-btn" id="searchForm"/>
                            </li>

                        </ul>
                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>申请时间</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <a><input type="checkbox" name="bb" class="code"
                                                  value="<s:property value="#theBean.id"/>"/></a>
                                    </td>
                                    <td>
                                        <div class="compile js_compile">
                                            <p><s:property value="#theBean.name"/></p>

                                            <div class="compile-con">
                                                <h3 class="fs14">编辑名称：</h3>

                                                <div class="mgt-10"><input type="text" maxlength="20"
                                                                           class="input-compile"
                                                                           value='<s:property value="#theBean.name" />'/>
                                                </div>
                                                <div><a class="btn-w8" href="javascript:;"
                                                        objId='<s:property value="#theBean.id" />'>提交</a><a
                                                        class="btn-w9 js_abrogate" href="javascript:void();">取消</a>
                                                </div>
                                            </div>
                                        </div>

                                    </td>
                                    <td>
                                        <s:property value="#theBean.description"/>
                                    </td>
                                    <td>
                                        <s:property value="#theBean.create_time"/>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        <div class="page mgt34-b25">
                            <jsp:include page="../../inc/fenye.jsp?formId=obj"/>
                        </div>
                    </div>
            </div>
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
        <p class="pdt-30">?2012中国电信云计算分公司版权所有 京ICP备 12022551号 增值电信业务经营许可证A2.B1.B2-20090001</p>
    </div>
</div>
<!--版权 end-->
</body>

</html>