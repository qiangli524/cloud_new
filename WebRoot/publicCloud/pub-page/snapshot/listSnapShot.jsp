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
                var name = $("#snapshot_name").val();
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

            //删除快照确认？
            $("#delete").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm("您确定要删除快照吗?")) {
                        deleteSnapShot();
                    }
                }
            });

            //删除快照,支持批量删除
            function deleteSnapShot() {
                mask("正在删除快照……", "info");
                var vmCode = '';
                $('input[name="bb"]:checked').each(function () {
                    vmCode += $(this).attr("objId") + "@" + $(this).attr("vmCode") + "@" + $(this).attr("connectCode") + "@" + $(this).attr("snapShotUuid") + "@" + $(this).attr("vType") + ",";
                });

                $.ajax({
                    url: "snapshot_deleteSnapShot.do?obj.vm_uuid=" + vmCode,
                    type: "POST",
                    data: "text",
                    dataType: "json",
                    success: function (data) {
                        removeMask();
                        mask(data.result, "info", 3000);
                        $("#theForm").submit();
                    }
                });
            }


            //恢复快照确认？
            $("#recovery").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm("您确定要恢复快照吗?")) {
                        recoverySnapShot();
                    }
                }
            });

            //恢复快照,支持批量恢复
            function recoverySnapShot() {
                mask("正在恢复快照……", "info");
                var vmCode = '';
                $('input[name="bb"]:checked').each(function () {
                    vmCode += $(this).attr("objId") + "@" + $(this).attr("vmCode") + "@" + $(this).attr("connectCode") + "@" + $(this).attr("snapShotUuid") + "@" + $(this).attr("vType") + ",";
                });

                $.ajax({
                    url: "snapshot_recoverySnapshot.do?obj.vm_uuid=" + vmCode,
                    type: "POST",
                    data: "text",
                    dataType: "json",
                    success: function (data) {
                        removeMask();
                        mask(data.result, "info", 3000);
                        $("#theForm").submit();
                    }
                });
            }
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
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/titile_snapshot.gif" width="65"
                    height="65"/>快照</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">快照(Snapshot) 用于云主机的备份与恢复，您可以随时从任意一个备份点恢复数据。</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/snapshot_listSnapShot.do?type=<s:property value="type"/>"
                          method="post">
                        <ul class="operate-list">
                            <!-- <li class="first"> -->
                            <li class="gray_button aa">
                                <a href="#" class="delete" id="delete">删除</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="reset" id="recovery">恢复</a>
                            </li>

                            <li class="search">
                                <input type="text" class="text-1" placeholder="快照名称" name="obj.name" id="snapshot_name"
                                       value="<s:property value='obj.name'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>

                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>应用主机</th>
                                <th>创建时间<!-- <b class="b-p"></b> --></th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'
                                               vmCode='<s:property value="#theBean.vm_uuid" />'
                                               connectCode='<s:property value="#theBean.connect_id" />'
                                               snapShotUuid='<s:property value="#theBean.snapshot_uuid" />'
                                               vType='<s:property value="#theBean.vm_type" />'/>
                                        </span>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.name"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.description"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.vm_name"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.create_time"/></span></td>
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
