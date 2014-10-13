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
var $current = null;
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
        $("#theForm").submit();
    });

    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            $("#theForm").submit();
        }
    }

    //申请ip
    $("#apply").click(function () {
        $.dialog({
            id: 'applyIp',
            title: '申请公网ip',
            width: '500px',
            height: '300px',
            lock: true,
            content: 'url:publicip_gotoApplyPage.do'
        });
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
        var url = "publicip_delete.do?ipObj.ipaddress=" + ip + "&ipObj.id=" + id;
        if (confirm("您确认要删除选中的公网IP吗?")) {
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

    //解除绑定
    $("#unbind").click(function () {
        var ip = "";
        var id = '';
        var count = 0;
        var status = 0;
        var $td = null;
        $("input:checkbox[name='bb']:checked").each(function () {
            ip += $(this).attr("ipaddr");
            id = $(this).val();
            count++;
            status = $(this).attr("status");
            $td = $(this);
        });
        if (count > 1) {
            alertMsg("一次只能解除一条绑定关系");
            return false;
        }
        if (status == 0) {
            alertMsg("该IP没有与主机绑定，无需解绑");
            return false;
        }
        var url = "publicip_unbind.do?ipObj.ipaddress=" + ip + "&ipObj.id=" + id;
        if (confirm("您确认要解除绑定关系吗？")) {
            $td.parent().siblings("#state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />' + '解绑中');
            $.ajax({
                type: 'post',
                url: url,
                dataType: 'text',
                success: function (msg) {
                    if (msg == -1) {
                        $td.parent().siblings("#state").html('已绑定');
                    } else {
                        $("#theForm").submit();
                    }
                }
            });
        }
    });

    $("#bind").click(function () {
        var id = "";
        var ip = "";
        var count = 0;
        var status = 0;
        $("input:checkbox[name='bb']:checked").each(function () {
            ip = $(this).attr("ipaddr");
            id = $(this).val();
            count++;
            status = $(this).attr("status");
            $current = $(this);
        });

        if (count > 1) {
            alertMsg("一次只能绑定一个IP");
            return false;
        }
        if (status == 1) {
            alertMsg("该IP已经进行了绑定，不能再次绑定");
            return false;
        }

        $.dialog({
            id: 'bind',
            title: '绑定弹性IP',
            width: '700px',
            height: '400px',
            lock:true,
            content: 'url:publicip_listVMForUser.do?ipObj.ipaddress=' + ip + '&ipObj.id=' + id
        });
    });

    $("#update").click(function () {
        var id = "";
        var count = 0;
        $("input:checkbox[name='bb']:checked").each(function () {
            id += $(this).attr("ipaddr");
            count++;
        });
        if (count > 1) {
            alertMsg("一次只能修改一个IP");
            return false;
        }
        $.dialog({
            id: 'update',
            title: '修改IP',
            width: '600px',
            height: '400px',
            lock:true,
            content: 'url:publicip_update.do?ipObj.ipaddress=' + id
        });
    });

});

function apply(descr, ipnums) {
    $.ajax({
        type: 'get',
        dataType: 'text',
        url: 'publicip_apply.do?ipObj.ipNums=' + ipnums + '&ipObj.description=' + descr + '&ipObj.region_id=c0ba3dfad670429095045fab86d5b45d',
        success: function (msg) {
            if (msg == -1) {
                alertMsg("没有 足够的公网IP可供使用")
            } else {
                $("#theForm").submit();
            }
        }
    });
}

function selectVM(url) {
    $current.parent().siblings("#state").html('<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" />' + '绑定中');
    $.ajax({
        type: 'get',
        dataType: 'text',
        url: url,
        success: function (msg) {
            if (msg == -1) {
                $cunrrent.parent().siblings("#state").html('未绑定');
            } else {
                $("#theForm").submit();
            }
        }
    })
}

function save(theForm) {
    $.ajax({
        type: 'post',
        dataType: 'text',
        url: 'publicip_save.do?' + theForm,
        success: function (msg) {
            if (msg == -1) {
                alertMsg("保存失败");
            } else {
                $("#theForm").submit();
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
        <jsp:include page="../order.jsp"/>
        <!--left end-->
        <!--main-c1 star-->
        <div class="main-c1 fr">
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/titile_ip.gif" width="65" height="65"/>弹性IP
            </h2>

            <p class="p-con4">
                公网 IP 地址是在互联网上合法的静态 IP 地址。您可以将申请到的公网 IP 地址分配到任意绑定至主机，并随时可以解绑、再分配到其他主机。
            </p>

            <div class="pd-15">
                <form id="theForm" action="publicip_list.do?type=<s:property value="type"/>" method="post">
                    <div class="col-c8">
                        <ul class="operate-list">
                            <li class="first">
                                <a href="javascript:;" class="add" id="apply"></a>
                            </li>
                            <!--
                             <li class="gray_button aa">
                                 <a href="javascript:;" class="close" id="release">删除</a>
                             </li>
                             -->
                            <li class="gray_button aa">
                                <a href="#" class="more js_more"> 更多操作...</a>
                                <dl class="operate-dl gray_button aa">
                                    <dd><a href="javascript:;" class="bindtovm" id="bind">绑定到主机</a></dd>
                                    <dd><a href="javascript:;" class="unbindfromvm" id="unbind">从主机解绑</a></dd>
                                </dl>
                            </li>
                            <li class="search">
                                <input type="text" class="text-1" placeholder="地址" size="22" name="ipObj.ipaddress"
                                       value="<s:property value='ipObj.ipaddress'/>"/>
                                <input type="button" class="search-btn" id="searchForm"/>
                            </li>

                        </ul>
                        <table border="0" width="100%" class="table-f4">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>IP地址</th>
                                <th>ICP备案</th>
                                <th>状态</th>
                                <th>应用主机</th>
                                <th>描述</th>
                                <th>申请时间</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               status='<s:property value="#theBean.status"/>'
                                               ipaddr='<s:property value="#theBean.ipaddress" />'
                                               value="<s:property value="#theBean.id"/>"/>
                                    </td>
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.ipaddress"/></span>
                                    </td>
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.icp_id"/></span>
                                    </td>
                                    <td id="state">
                                        <s:if test="#theBean.status==1">
                                            <span class="font-blue">已绑定</span>
                                        </s:if>
                                        <s:else>
                                            <span class="font-blue">未绑定</span>
                                        </s:else>
                                    </td>
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.VH_NAME"/></span>
                                    </td>
                                    <td name="updesc">
                                        <span class="font-blue"><s:property value="#theBean.description"/></span>
                                    </td>
                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.create_time"/></span>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                        <div class="page mgt34-b25">
                            <jsp:include page="../../inc/fenye.jsp?formId=theForm"/>
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