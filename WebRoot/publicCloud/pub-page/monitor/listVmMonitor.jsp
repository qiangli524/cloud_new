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
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/openpostwindow.js"></script>
    <script type="text/javascript">

        $(function () {
            $(".search-btn").click(function () {
                $("#theForm").submit();
            })
            //回车搜索
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("#theForm").submit();
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
                 window.focus();   //将焦点移回原窗口，即本页面窗口 */

                //实现方式3：弹出框
                /*
                 var x = document.body.scrollWidth;
                 var y = document.body.scrollHeight - 45;
                 var url = "url:vmmonitor_detail.do?obj.vmCode="+$(this).attr("vmCode")
                 +"obj.connectCode="+$(this).attr("connectCode");
                 $.dialog({
                 id:'detail',
                 title:'资源使用率详细',
                 width: x,
                 height: y,
                 //fixed: true,
                 lock:true,
                 content: url
                 });
                 */
            });

            //点击名称主机详细页面
            $(".vmdetail").unbind().live('click', function () {
                var vmUuid = $(this).attr("vmCode");
                var connectCode = $(this).attr("connectCode");
                var url = "vm_detailPage.do?type=11&obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode;
                location.href = url;
            })
            //点击名称告警规则页面
            $(".alarm").unbind().live('click', function () {
                var vmid = $(this).attr("vmId");
                var vmUuid = $(this).attr("vmCode");
                var connectCode = $(this).attr("connectCode");
                var url = "vmalarm_list.do?type=32&vm.VH_UUID=" + vmUuid + "&vm.connectId=" + connectCode + "&vm.VH_ID=" + vmid;
                location.href = url;
            })

        })
        //保存快照
        function saveSnapshot(url, params) {
            mask("正在保存快照……", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("保存快照成功！", "success", 3000);
                        oldnameP.text(newnameInput.val());
                    } else {
                        mask("保存快照失败！", "error", 3000);
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
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_firewall.gif" width="65"
                    height="65"/>云主机监控</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">为了加强位于基础网络中的主机或路由器的安全性，您可以在主机或路由器之前放置防火墙 (Fire Wall)。为每个用户提供了一个缺省防火墙（ID
                之后带有星标）。您也可以自建更多的防火墙。自建防火墙在初始状态下，所有端口都是封闭的，您需要建立规则以打开相应的端口。</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm" action="vmmonitor_list.do?type=<s:property value='type'/>" method="post">
                        <ul class="operate-list">
                            <li class="search">
                                <input placeholder="主机名称" class="text-1" value="" id="VH_NAME" name="obj.name"/>
                                <input type="button" class="search-btn"/>
                            </li>
                        </ul>
                        <table id="theTable" border="0" width="100%" class="table-f4 js_table_f4"
                        ">
                        <thead>
                        <tr>
                            <th onclick="sort(theTable,1,'string')">名称</th>
                            <th onclick="sort(theTable,2,'string')">CPU使用率(%)</th>
                            <th onclick="sort(theTable,3,'string')">内存使用率(%)</th>
                            <th onclick="sort(theTable,4,'string')">存储使用率(%)</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="resultList" id="theBean">
                            <tr>
                                <td>
                                    <a class="font-blue vmdetail" vmCode='<s:property value="#theBean.vmCode" />'
                                       connectCode='<s:property value="#theBean.connectCode" />'>
                                        <s:property value="#theBean.name"/>
                                    </a>
                                </td>
                                <td><span class="font-blue"><s:property value="#theBean.cpu_usage"/></span></td>
                                <td><span class="font-blue"><s:property value="#theBean.mem_usage"/></span></td>
                                <td><span class="font-blue"><s:property value="#theBean.store_usage"/></span></td>
                                <td><span class="font-blue">
                            		<a href="javascript:void(0)" class="alarm"
                                       vmCode='<s:property value="#theBean.vmCode" />'
                                       connectCode='<s:property value="#theBean.connectCode" />'
                                       vmId='<s:property value="#theBean.vmId" />'>告警规则</a>
                            		<a href="javascript:void(0)" class="detail"
                                       vmCode='<s:property value="#theBean.vmCode" />'
                                       connectCode='<s:property value="#theBean.connectCode" />'>监控详情</a>
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
