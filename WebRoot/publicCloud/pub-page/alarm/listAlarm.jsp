<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>控制中心</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
<script type="text/javascript"  src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
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
        var name = $("#vmalarm").val();
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
        var url = "url:vmalarm_createAlarm.do?vm.VH_ID=" + $("#vm_id").val() + "&vm.VH_UUID=" + $("#vm_uuid").val() + "&vm.connectId=" + $("#vm_connect_id").val();
        $.dialog({
            id: 'addAlarm',
            title: '添加告警',
            //height: 60,
            height: 510,
            width: 600,
            lock: true,
            content: url
        });
    });
    //启动监控告警
    $("#startup").unbind().live('click', function () {
        mask("正在启动监控告警……", "info");
        var ckbox = $('input[name="bb"]:checked');
        if (ckbox.length == 1) {
            var id = '';
            ckbox.each(function () {
                id = $(this).attr("objId");
            });
            $.ajax({
                url: "vmalarm_startupAlarm.do?obj.id=" + id,
                type: "POST",
                data: "text",
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("启动监控告警成功！", "success", 3000);
                        ckbox.parent().parent().find(".status").html("<img src='<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif' alt='正在运行' />");
                        //$("#theForm").submit();
                    } else {
                        mask("启动监控告警失败！", "error", 3000);
                    }

                }
            });
        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg('请仅选择一个告警！');
            }
        }
    });
    //关闭监控告警
    $("#shutdown").unbind().live('click', function () {
        mask("正在关闭监控告警……", "info");
        var ckbox = $('input[name="bb"]:checked');
        if (ckbox.length == 1) {
            var id = '';
            ckbox.each(function () {
                id = $(this).attr("objId");
            });
            $.ajax({
                url: "vmalarm_shutdownAlarm.do?obj.id=" + id,
                type: "POST",
                data: "text",
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("关闭监控告警成功！", "success", 3000);
                        ckbox.parent().parent().find(".status").html("<img src='<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif' alt='已停止' />");
                        //$("#theForm").submit();
                    } else {
                        mask("关闭监控告警失败！", "error", 3000);
                    }
                }
            });
        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg('请仅选择一个告警！');
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
            var url = "url:vmalarm_updateAlarmPage.do?obj.id=" + id;
            $.dialog({
                id: 'editAlarm',
                title: '修改告警',
                height: 510,
                width: 600,
                lock: true,
                content: url
            });
        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg('请仅选择一个告警！');
            }
        }
    });

    //删除确认？
    $("#del").unbind().live('click', function () {
        if ($('input[name="bb"]:checked').length == 1) {
            if (confirm('确定要删除告警吗？')) {
                var id = '';
                $('input[name="bb"]:checked').each(function () {
                    id = $(this).attr("objId");
                });
                deleteAlarm(id);
            } else {
                if ($('input[name="bb"]:checked').length > 0) {
                    alertMsg('请仅选择一个告警！');
                }
            }
        }
    });

    //删除
    function deleteAlarm(id) {
        mask("正在删除告警……", "info");
        $.ajax({
            url: "vmalarm_deleteAlarm.do?obj.id=" + id,
            type: "POST",
            data: "text",
            dataType: "json",
            success: function (data) {
                removeMask();
                if (data.result == "success") {
                    mask("删除告警成功！", "success", 3000);
                    $("#theForm").submit();
                } else {
                    mask("删除告警失败！", "error", 3000);
                }
            }
        });
    }

})
function saveAlarm(url, params) {
    mask("正在保存告警……", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("保存告警成功！", "success", 3000);
                $("#theForm").submit();
            } else {
                mask("保存告警失败！", "error", 3000);
            }
        }
    });
}
function updateAlarm(url, params) {
    mask("正在修改告警……", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("更新告警成功！", "success", 3000);
                $("#theForm").submit();
            } else {
                mask("更新告警失败！", "error", 3000);
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
        <jsp:include page="../order.jsp"/>
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
            <!--标题 star-->
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif" width="65"
                    height="65"/>告警</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">云服务监控 <font color="red"> ${vm.VH_DESC}</font> <a href="javascript:history.back();">返回</a>
            </p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm" action="vmalarm_list.do?type=<s:property value="type"/>" method="post">
                        <s:hidden name="vm.VH_ID" id="vm_id"></s:hidden>
                        <s:hidden name="vm.VH_UUID" id="vm_uuid"></s:hidden>
                        <s:hidden name="vm.connectId" id="vm_connect_id"></s:hidden>
                        <ul class="operate-list">
                            <!-- <li class="first"> -->
                            <s:if test='vm.VH_DESC != null && vm.VH_DESC != ""'>
                                <li class="blue_button">
                                    <a href="javascript:void(0);" class="add" id="add"></a>
                                </li>
                            </s:if>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="edit" id="edit">修改</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="delete" id="del">删除</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="paly" id="startup">启用</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:;" class="close" id="shutdown">停止</a>
                            </li>
                            <li class="search">
                                <input type="text" class="text-1" placeholder="监控项" name="obj.kpi_name" id="vmalarm"
                                       value="<s:property value='obj.kpi_name'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>

                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>监控项</th>
                                <th>描述</th>
                                <th>应用主机</th>
                                <th>状态</th>
                                <th>创建时间</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'/>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.kpi_name"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.description"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.vm_name"/></span></td>
                                    <td class="status">
                                        <s:if test="#theBean.status==1"><img
                                                src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif"
                                                alt="正在运行"/></s:if>
                                        <s:else><img
                                                src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif"
                                                alt="已停止"/></s:else>
                                    </td>
                                    <td><span class="font-blue"><fmt:formatDate value="${theBean.create_time}"
                                                                                pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                    </td>
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
