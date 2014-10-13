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

    //查找
    $(".search-btn").click(function () {
        var name = $("#physicalVlan").val();
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

    //进入Vlan申请页面
    $("#apply").unbind().live('click', function () {
        var url = "url:physicalVlan_applyPhysicalVlanPage.do";
        $.dialog({
            id: 'applyImage',
            title: '申请虚拟网络',
            height: 170,
            width: 420,
            lock: true,
            content: url
        });
    });
    /*
     //进入重命名页面
     $("#edit").unbind().live('click',function(){
     var id = '';
     var count = 0;
     $("input:checkbox[name='bb']:checked").each(function(){
     id =$(this).val();
     count ++ ;
     $current = $(this);
     });
     if(count>1){
     return false;
     alert('请选择一项进行修改');
     }
     var url = 'url:physicalVlan_renamePhysicalVlan.do?obj.id='+id;
     $.dialog({
     id:'renamePhysicalVlan',
     title:'重命名虚拟网络',
     height:220,
     width:420,
     lock:true,
     content: url
     });
     });
     */

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
            url: "physicalVlan_updatePhysicalVlan.do",
            type: "POST",
            data: params,
            dataType: "json",
            success: function (data) {
                if (data.result == "success") {
                    mask("重命名成功！", "success", 3000);
                    oldnameP.text(newnameInput.val());
                } else {
                    mask("重命名失败！", "error", 3000);
                }
            }
        });

        $(this).parents(".compile-con").hide();
    });

    //进入链接路由器页面
    $("#connect_router").unbind().live('click', function () {
        var id = '';
        var count = 0;
        $("input:checkbox[name='bb']:checked").each(function () {
            id = $(this).val();
            count++;
            $current = $(this);
        });
        if (count > 1) {
            alertMsg('请选择一项进行修改');
            return false;
        }
        var url = 'url:physicalVlan_connectRouterPage.do?obj.id=' + id;
        $.dialog({
            id: 'connectRouter',
            title: '连接路由器',
            height: 220,
            width: 420,
            lock: true,
            content: url
        });
    });

    //断开路由器链接
    $("#disconnect_router").unbind().live('click', function () {
        var id = '';
        var count = 0;
        var $td = null;
        $("input:checkbox[name='bb']:checked").each(function () {
            id = $(this).val();
            count++;
            $td = $(this);
        });
        if (count > 1) {
            alertMsg('请选择一项进行修改');
            return false;
        }
        if (confirm("确认要断开路由器吗？")) {
            $td.parent().siblings("#router").html('<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" />' + '正在断开');
            var url = "physicalVlan_disconnectRouter.do?obj.router_id=" + "" + "&obj.id=" + id;
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data == -1) {
                        mask("断开路由器失败", "error", 3000);
                        //$("#theForm").submit();
                    } else {
                        mask("断开路由器成功", "success", 3000);
                        $("#theForm").submit();
                    }
                }
            });
        }
    });
});
//执行申请Vlan操作
function applyPhysicalVlan(url, params) {
    mask("正在执行申请Vlan操作", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("申请虚拟网络成功！", "success", 3000);
                $("#theForm").submit();
            } else {
                mask("申请虚拟网络失败", "error", 3000);
            }
        }
    });
}
//链接路由器
function connectRouter(url) {
    $current.parent().siblings("#router").html('<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" />' + '链接中');
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data == -1) {
                mask("链接路由器失败", "error", 3000);
                //$("#theForm").submit();
            } else {
                mask("链接路由器成功", "success", 3000);
                $("#theForm").submit();
            }

        }
    });
}

//vlan修改名称
function renameVlan(url, params) {
    mask("正在执行重命名操作", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("重命名成功", "success", 3000);
                $("#theForm").submit();
            } else {
                mask("重命名失败", "error", 3000);
            }
        }
    });
}

function refresh() {
    parent.location.reload();
}

//跳转至商城相关页面
$(function () {
    ///购买公网带宽
    $("#buy_bandwidth").unbind().live('click', function () {
        var url = 'physicalVlan_buyBandwidthPage.do';
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
        if (urls == "-1") {
            alertMsg("您已经购买过公网带宽，不可以多次购买");
        } else {
            window.open(urls, "_blank"); //打开新的标签页面
        }
    });
    ///带宽升级
    $("#expand").unbind().live('click', function () {
        var url = 'physicalVlan_buyBandwidthPage.do';
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
    ///带宽续订
    $("#delay").unbind().live('click', function () {
        var url = 'physicalVlan_delayBandwidthPage.do';
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
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_vlan.gif" width="65"
                    height="65"/>虚拟网络</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">VLAN(Virtual Local Area Network)，中文名为"虚拟局域网"，
                VLAN是一种将局域网(LAN)设备从逻辑上划分(注意，不是从物理上划分)成一个个网段(或者说是更小的局域网LAN)，从而实现虚拟工作组(单元)的数据交换技术。</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/physicalVlan_listPhysicalVlanForCustomer.do?type=<s:property value="type"/>"
                          method="post">
                        <ul class="operate-list">
                            <!-- <li class="first">
                            <li class="blue_button">
                                <a href="#" class="add" id="apply"></a>
                            </li>
                            -->

                            <li class="blue_button">
                                <a href="#" class="edit" id="buy_bandwidth">购买带宽</a>
                            </li>
                            <!--
                            <li class="gray_button aa">
                               <a href="#" class="delete" id="">删除</a>
                            </li> -->
                            <li class="gray_button aa">
                                <a href="#" class="more js_more">更多操作...</a>
                                <dl class="operate-dl gray_button aa">
                                    <dd><a href="javascript:;" class="linkrouter" id="connect_router">连接路由器</a></dd>
                                    <dd><a href="javascript:;" class="breakrouter" id="disconnect_router">断开路由器</a></dd>
                                    <!--
                                    <dd><a href="javascript:;" class="pwd " id="bind">绑定到主机</a></dd>
                                    <dd><a href="javascript:;" class="pwd  " id="unbind">从主机解绑</a></dd>
                                     -->
                                </dl>
                            </li>
                            <li class="search">
                                <input type="text" class="text-1" placeholder="网络名称" name="obj.name" id="physicalVlan"
                                       value="<s:property value='obj.name'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>

                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <!-- <th>IP地址</th>
                                <th>子网掩码</th>
                                <th>网关</th>-->
                                <th>状态<!-- <b class="b-p"></b> --></th>
                                <th>类型<!-- <b class="b-p"></b> --></th>
                                <th>带宽(Mbps)</th>
                                <th>路由器</th>
                                <th>申请时间</th>
                                <th>带宽管理</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'
                                               value='<s:property value="#theBean.id" />'/>
                                        </span>
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
                                        <%-- <td><span class="font-blue"><s:property value="#theBean.ip" /></span></td>
                                        <td><span class="font-blue"><s:property value="#theBean.subnet_mask" /></span></td>
                                        <td><span class="font-blue"><s:property value="#theBean.gateway" /></span></td> --%>
                                    <td>
                                        <s:if test="#theBean.isused!=0">
                                            已使用
                                        </s:if>
                                        <s:else>
                                            未使用
                                        </s:else>
                                    </td>
                                    <td>
                                        <s:if test="#theBean.vlan_type==1">
                                            内网
                                        </s:if>
                                        <s:elseif test="#theBean.vlan_type==2">
                                            公网
                                        </s:elseif>
                                    </td>
                                    <td><s:property value="#theBean.flow_size"/></td>
                                    <td id="router"><s:property value="#theBean.router_name"/></td>
                                    <td><s:property value="#theBean.create_time"/></td>
                                    </td>
                                    <td>
                                        <s:if test="#theBean.vlan_type==2">
                                            <a href="javascript:;" id="expand"><span
                                                    class="font-blue">升级</span></a>&nbsp;&nbsp;<a href="javascript:;" id="delay"><span class="font-blue">续订</span></a>
                                        </s:if>
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
