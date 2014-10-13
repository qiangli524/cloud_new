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

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/jquery_ui/jquery_ui_base/jquery.ui.all.css">

<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.core.js"></script>
<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.widget.js"></script>
<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.tooltip.js"></script>
<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.position.js"></script>
<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.menu.js"></script>
<script src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery_ui/jquery.ui.autocomplete.js"></script>


<script type="text/javascript">
$(function () {

    $(".font-blue").tooltip({
        show: null,
        position: {
            my: "left top",
            at: "left bottom"
        },
        open: function (event, ui) {
            ui.tooltip.animate({ top: ui.tooltip.position().top + 5 }, "fast");
        }
    });


    $("#checkAll").click(function () {
        $('input[name="bb"]').prop("checked", this.checked);
        var num = $('input:checkbox[name="bb"]:checked').size();
        if (num >= 1) {
            $('.aa').removeClass("gray_button").addClass("blue_button");
        } else {
            $('.aa').removeClass("blue_button").addClass("gray_button");
        }
    })

    //回车搜索
    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            $("#theForm").submit();
        }
    }


    //重命名点击事件
    $(".btn-w8").click(function () {
        var newnameInput = $(this).parent().parent().find(".input-compile");
        if (newnameInput.val() == null || newnameInput.val().trim() == "") {
            alertMsg("请填写名称");
            return;
        }
        var oldnameP = $(this).parent().parent().siblings("p");
        var vmCode = $(this).attr("vmCode");
        var connectCode = $(this).attr("connectCode");

        var params = {
            "obj.VH_UUID": vmCode,
            "obj.connectId": connectCode,
            "obj.VH_DESC": newnameInput.val()
        };

        $.ajax({
            url: "vm_rename.do",
            type: "POST",
            data: params,
            dataType: "json",
            success: function (data) {
                if (data.result == "success") {
                    //$.dialog.tips('重命名成功！');
                    mask("重命名成功！", "info", 3000);
                    oldnameP.text(newnameInput.val());
                } else {
                    mask("重命名失败！", "error", 3000);
                    //$.dialog.tips('重命名失败！');
                }
            }
        });

        oldnameP.text(newnameInput.val());
        $(this).parents(".compile-con").hide();
    });

    //开启虚拟机电源状态
    $('#powerOn').unbind().live('click', function () {
        $('input[name="bb"]:checked').each(function () {
            var vmCode = $(this).attr("vmCode");
            var connect_id = $(this).attr("connectCode");
            var $td = $(this);
            if (confirm("您确定要开启云主机电源吗?")) {

                mask("正在开启云主机电源……", "info");
                var url = "vm_putPowerState.do?obj.VH_UUID=" + vmCode + "&obj.connectId=" + connect_id + "&obj.VH_STAT=" + "powerOn";
                $(this).parent().siblings("#power_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />' + '启动中');
                $.ajax({
                    type: "GET",
                    url: url,
                    data: "text",
                    async: true,
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data == "success") {
                            removeMask();
                            mask("开启云主机电源成功", "success", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        } else {
                            removeMask();
                            mask("开启云主机电源失败", "error", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif" alt="已停止" />');
                        }
                    }
                });
            }
        });
    });
    //关闭虚拟机电源状态
    $('#powerOff').unbind().live('click', function () {
        $('input[name="bb"]:checked').each(function () {
            var $td = $(this);
            var vmCode = $(this).attr("vmCode");
            var connect_id = $(this).attr("connectCode");
            if (confirm("您确定要关闭云主机电源吗?")) {
                mask("正在关闭云主机电源……", "info");
                var url = "vm_putPowerState.do?obj.VH_UUID=" + vmCode + "&obj.connectId=" + connect_id + "&obj.VH_STAT=" + "powerOff";
                $(this).parent().siblings("#power_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />' + '关机中');
                $.ajax({
                    type: "GET",
                    url: url,
                    //data:"text",
                    async: true,
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        removeMask();
                        if (data == "success") {
                            mask("关闭云主机电源成功", "success", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif" alt="已停止" />');
                        } else {
                            mask("关闭云主机电源失败", "error", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        }
                    }
                });
            }
        });
    });
    //重启虚拟机
    $('#reboot').unbind().live('click', function () {
        $('input[name="bb"]:checked').each(function () {
            var vmCode = $(this).attr("vmCode");
            var connect_id = $(this).attr("connectCode");
            var $td = $(this);
            if (confirm("您确定要重启云主机吗?")) {
                mask("正在重启云主机……", "info");
                var url = "vm_putPowerState.do?obj.VH_UUID=" + vmCode + "&obj.connectId=" + connect_id + "&obj.VH_STAT=" + "reboot_guest";
                $(this).parent().siblings("#power_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />' + '重启中');
                $.ajax({
                    type: "GET",
                    url: url,
                    data: "text",
                    async: true,
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data == "success") {
                            //更新虚拟机状态
                            removeMask();
                            mask("重启云主机成功", "success", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        } else {
                            removeMask();
                            mask("重启云主机失败", "error", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        }
                    }
                });
            }
        });
    });

    //重置虚拟机
    $('#reset').unbind().live('click', function () {
        $('input[name="bb"]:checked').each(function () {
            var vmCode = $(this).attr("vmCode");
            var connect_id = $(this).attr("connectCode");
            var $td = $(this);
            if (confirm("您确定要重置云主机吗?")) {
                mask("正在重置云主机……", "info");
                var url = "vm_putPowerState.do?obj.VH_UUID=" + vmCode + "&obj.connectId=" + connect_id + "&obj.VH_STAT=" + "reset";
                $(this).parent().siblings("#power_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />' + '重置中');
                $.ajax({
                    type: "GET",
                    url: url,
                    data: "text",
                    async: true,
                    cache: false,
                    dataType: "json",
                    success: function (data) {
                        if (data == "success") {
                            removeMask();
                            mask("重置云主机成功", "success", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        } else {
                            removeMask();
                            mask("重置云主机失败", "error", 3000);
                            $td.parent().siblings("#power_state").html('<img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif" alt="正在运行" />');
                        }
                    }
                });
            }
        });
    });
    //进入资源使用率界面monitor unused
    $("#monitor").unbind().live('click', function () {
        if ($('input[name="bb"]').length > 0) {
            var url = "url:vmmonitor_list.do";
            $.dialog({
                id: 'monitor',
                title: '资源使用率',
                lock: true,
                height: '500px',
                width: '760px',
                content: url
            });
        } else {
            alertMsg("您还没有云主机，赶快去创建一个吧！");
        }
    });

    //进入控制台
    $(".console").click(function () {
        var url = "url:united_console.do?vtype=" + "1" + "&uuid=" + $(this).attr("vmCode") + "&connect_id=" + $(this).attr("connectCode");
        $.dialog({
            id: 'console',
            title: '控制台',
            max: true,
            min: true,
            height: '400px',
            width: '634px',
            content: url
        });
    });

    //进入创建虚拟机快照页面
    $("#snapshot").unbind().live('click', function () {
        if ($('input[name="bb"]:checked').length == 1) {
            var vmCode = '';
            $('input[name="bb"]:checked').each(function () {
                vmCode += $(this).attr("vmCode") + "@" + $(this).attr("connectCode") + "@" + $(this).attr("vType") + ",";
            });
            var url = "url:snapshot_createSnapShotPage.do?obj.vm_uuid=" + vmCode;
            $.dialog({
                id: 'createSnapshot',
                title: '创建快照',
                width: '550px',
                height: '300px',
                lock: true,
                content: url
            });
        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg("请仅选择一个主机创建快照！");
            }
        }

    });

    //进入创建虚拟机模板页面
    $("#image").unbind().live('click', function () {
        if ($('input[name="bb"]:checked').length == 1) {
            var vmCode = '';
            var vmUuid = '';
            var connectCode = '';
            $('input[name="bb"]:checked').each(function () {
                vmCode += $(this).attr("vmCode") + "@" + $(this).attr("connectCode") + "@" + $(this).attr("vType");
                vmUuid = $(this).attr("vmCode");
                connectCode = $(this).attr("connectCode");
            });

            //验证：是否存在快照
            var validExist = checkExistSnapshot(vmUuid, connectCode);
            if (validExist == "1") {
                alertMsg("此云主机存在快照，请先删除快照再进行此次操作！");
                return;
            } else if (validExist == "error") {
                alertMsg("通信异常！请稍后再试！");
                return;
            }

            var url = "url:images_createImagePage.do?obj.hostCode=" + vmCode;
            $.dialog({
                id: 'createImage',
                title: '创建镜像',
                width: '550px',
                height: '300px',
                lock: true,
                content: url
            });
        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg("请仅选择一个主机创建模板！");
            }
        }

    });


    //创建虚拟磁盘
    $("#disk").click(function () {
        var count = 0;
        var vmCode = "";
        $("input:checkbox[name='bb']:checked").each(function () {
            count++;
            vmCode += $(this).attr("vmCode") + "@" + $(this).attr("connectCode") + "@" + $(this).attr("vType");
        });
        if (count > 1) {
            alertMsg("一次只能为一台主机创建一个或多个磁盘！");
            return false;
        }
    });
    //网络页面
    $("#network").click(function () {
        var vmUuid = '';
        var connectCode = '';
        $("input:checkbox[name='bb']:checked").each(function () {
            vmUuid = $(this).attr("vmCode");
            connectCode = $(this).attr("connectCode");
        });

        //验证：是否存在快照
        var validExist = checkExistSnapshot(vmUuid, connectCode);
        if (validExist == "1") {
            alertMsg("此云主机存在快照，请先删除快照再进行此次操作！");
            return;
        } else if (validExist == "error") {
            alertMsg("通信异常！请稍后再试！");
            return;
        }

        var url = "url:vm_joinNetworkPage.do?obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode;
        $.dialog({
            id: 'network',
            title: '加入网络',
            max: true,
            min: true,
            lock: true,
            height: 200,
            width: 420,
            content: url
        });
    });

    //点击名称主机详细页面
    $(".detail").unbind().live('click', function () {
        var vmUuid = $(this).attr("vmCode");
        var connectCode = $(this).attr("connectCode");
       var url = "vm_detailPage.do?type=11&obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode;
        // var url = "/cloud/detail/11/" + vmUuid + "/" + connectCode;
        location.href = url;
    })
})
//保存快照
function saveSnapshot(url, params) {
    //mask("正在保存快照，请稍候","0.7","0px");
    mask("正在保存快照……", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("保存快照成功", "success", 3000);
                //$("#theForm").submit();
            } else {
                mask("保存快照失败", "error", 3000);
            }
        }
    });
}

//保存快照
function saveImage(url, params) {
    //mask("正在创建镜像，请稍候","0.7","0px");
    mask("正在创建镜像……", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("创建镜像成功", "success", 3000);
            } else {
                mask("创建镜像失败", "error", 3000);
            }
        }
    });
}
//加入网络
function joinNetwork(url, params) {
    //mask("正在加入网络，请稍候","0.7","0px");
    mask("正在加入网络……", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("加入网络成功", "success", 3000);
                // $("#theForm").submit();
            } else {
                mask("加入网络失败", "error", 3000);
            }
        }
    });

}
function refresh() {
    parent.location.reload();
}
//跳转至商城相关页面
$(function () {
    $("#reconfig").unbind().live("click", function () {
        $("input:checkbox[name='bb']:checked").each(function () {
            vmUuid = $(this).attr("vmCode");
            connectCode = $(this).attr("connectCode");
        });
        var urls = '';
        $.ajax({
            url: "vm_reconfigPage.do?obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode,
            type: "POST",
            dataType: "text",
            async: false,
            success: function (data) {
                urls = data;
            }
        });
        window.open(urls, "_blank"); //打开新的标签页面
    })
    //跳转至商城购买虚拟机页面
    $("#buy").unbind().live("click", function () {
        var urls = '';
        $.ajax({
            url: "vm_buyPage.do",
            type: "POST",
            dataType: "text",
            async: false,
            success: function (data) {
                urls = data;
            }
        });
        window.open(urls, "_blank"); //打开新的标签页面
    })
    ///主机续订
    $("#delay").unbind().live('click', function () {
        var vmUuid = $(this).attr("vmCode");
        var connectCode = $(this).attr("connectCode");
        var url = "vm_delayVMTime.do?obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode;
        var urls = '';
        $.ajax({
            url: url,
            type: "POST",
            dataType: "text",
            async: false,
            success: function (data) {
                urls = data;
            }
        });
        window.open(urls, "_blank"); //打开新的标签页面
    });
})
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
            <h2 class="title-common6"><a href="javascript:void(0);" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif" width="65"
                    height="65"/>主机</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">
                云主机（Elastic Compute Service, 简称ECS）是一种处理能力可弹性伸缩的计算服务，其管理方式比物理服务器更简单高效。<br/>云主机帮助您快速构建更稳定、安全的应用，降低开发运维的难度和整体IT成本，使您能够更专注于核心业务创新。
            </p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <form id="theForm" action="vm_listVm.do?type=<s:property value='type'/>" method="post">
                    <div class="col-c8">
                        <ul class="operate-list">
                            <li class="first">
                                <a href="javascript:void(0);" class="add" id="buy"></a>
                            </li>
                            <%--
                                                     <li class="blue_button">
                                                        <a href="javascript:void(0);" class="monitor" id="monitor">监控</a>
                                                    </li>
                                                    --%>
                            <li class="gray_button aa">
                                <a href="javascript:void(0);" class="paly" id="powerOn">启动</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:void(0);" class="close" id="powerOff">关机</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="javascript:void(0);" class="more js_more">更多操作...</a>
                                <dl class="operate-dl gray_button aa">
                                    <dd><a href="javascript:void(0);" class="upgrade" id="reconfig">配置</a></dd>
                                    <dd><a href="javascript:void(0);" class="reset-q more reset-q1" id="reboot">重启</a>
                                    </dd>
                                    <dd><a href="javascript:void(0);" class="reset more reset1" id="reset">重置</a></dd>
                                    <dd><a href="javascript:void(0);" class="newsnapshot" id="network">加入网络</a></dd>
                                    <dd><a href="javascript:void(0);" class="newsnapshot" id="snapshot">新建快照</a></dd>
                                    <dd><a href="javascript:void(0);" class="newsnapshot" id="image">新建镜像</a></dd>
                                    <dd><a href="javascript:void(0);" class="newdisk" id="disk">新建磁盘</a></dd>
                                </dl>
                            </li>
                            <li class="search">
                                <input class="text-1" value="<s:property value='obj.VH_NAME'/>" placeholder="云主机名称"
                                       id="VH_NAME" name="obj.VH_NAME"/>
                                <input type="button" class="search-btn"/>
                            </li>

                        </ul>
                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>运行状态
                                    <b class="b-p">

                                    </b>
                                </th>

                                <!-- <th>网络</th>  -->
                                <th>公网IP/内网IP</th>
                                <!--
                                <th>操作系统</th> -->
                                <!--                             <th>配置</th> -->
                                <!-- <th>性能</th>  -->
                                <th>付费方式</th>
                                <th>操作</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td><input type="checkbox" name="bb" class="code"
                                               vmCode='<s:property value="#theBean.VH_UUID" />'
                                               connectCode='<s:property value="#theBean.connectId" />'
                                               vType='<s:property value="#theBean.VH_TYPE" />'/></span></td>
                                    <td>
                                        <a class="font-blue detail" href="javascript:;"
                                           vmCode='<s:property value="#theBean.VH_UUID" />'
                                           connectCode='<s:property value="#theBean.connectId" />'>
                                            <s:property value="#theBean.VH_NAME"/></a>

                                        <div class="compile js_compile">
                                            <p><s:property value="#theBean.VH_DESC"/>&nbsp;</p>

                                            <div class="compile-con">
                                                <h3 class="fs14">编辑名称：</h3>

                                                <div class="mgt-10"><input type="text" maxlength="20"
                                                                           class="input-compile"
                                                                           value='<s:property value="#theBean.VH_DESC" />'/>
                                                </div>
                                                <div><a class="btn-w8" href="javascript:;"
                                                        vmCode='<s:property value="#theBean.VH_UUID" />'
                                                        connectCode='<s:property value="#theBean.connectId" />'>提交</a><a
                                                        class="btn-w9 js_abrogate" href="javascript:void();">取消</a>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td id="power_state">
                                            <%--<em class="em-c">
                                                --%>
                                        <s:if test="#theBean.VH_STAT==1"><img
                                                src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif"
                                                alt="正在运行"/></s:if>
                                        <s:else><img
                                                src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif"
                                                alt="已停止"/></s:else>
                                            <%--</em>
                                        --%></td>
                                    <!--    	<td><span class="font-blue"><s:property value="#theBean.VH_NETWORK" /></span></td>  -->
                                    <td><span class="font-blue">
                            			<s:if test="#theBean.public_ip!=null && #theBean.public_ip!=''">
                                            <s:property value="#theBean.public_ip"/>(公网)<br/>
                                            <s:property value="#theBean.VH_IP"/>(内网)
                                        </s:if>
                            			<s:else>
                                          	  暂无公网(公网)<br/>
                                            <s:property value="#theBean.VH_IP"/>(内网)
                                        </s:else>
		                            </span></td>

                                    <!--
                            	<td><span class="font-blue"><s:property value="#theBean.VH_SYSTEM" /></span></td>
                            	 -->
                                    <!--                             	<td> -->
                                        <%--                             		<span class="font-blue"><s:property value="#theBean.VH_CPU" />核</span>| --%>
                                        <%--                             		<span class="font-blue"><s:property value="#theBean.VH_MEM" />M</span>| --%>
                                        <%--                             		<span class="font-blue"><s:property value="@java.lang.Math@round(#theBean.VH_STORAGE/1024)" />G</span> --%>
                                    <!--                             	</td> -->
                                    <!-- <td><a href="">性能</a></td>  -->
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.VH_STATUS"/></span><br/>
                                        <!-- 
                                        <span class="font-blue">购买时间：<s:property
                                                value="#theBean.VH_STARTED"/></span><br/>
                                        <span class="font-blue">到期时间：<s:property value="#theBean.VH_COMPLETED"/></span>
                                         -->
                                    </td>
                                    <td><a href="javascript:void(0)" class="console"
                                           vmCode='<s:property value="#theBean.VH_UUID" />'
                                           connectCode='<s:property value="#theBean.connectId" />'
                                           vType='<s:property value="#theBean.VH_TYPE" />'><span
                                            class="font-blue">链接</a></span>
                                        <a href="javascript:;" id="delay"
                                           vmCode='<s:property value="#theBean.VH_UUID" />'
                                           connectCode='<s:property value="#theBean.connectId" />'
                                           vType='<s:property value="#theBean.VH_TYPE" />'><span
                                                class="font-blue">续订</a></span></td>
                                </tr>
                            </s:iterator>
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
<%--<div class="overall-prompt bg-blue" ><a href="javascript:;" class="js_p_close"></a>正在操作！</div>
--%></body>
</html>