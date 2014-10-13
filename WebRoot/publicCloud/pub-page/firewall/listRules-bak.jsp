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
                var name = $("#rules").val();
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
                var url = "url:rules_addRulesPage.do?obj.firewall_id=" + $("#firewall_id").val();
                $.dialog({
                    id: 'addRules',
                    //title:'为防火墙('+$("#firewall_id").val()+')添加规则',
                    title: '添加规则',
                    height: 510,
                    width: 600,
                    lock: true,
                    content: url
                });
            });

            //进入修改页面
            $("#edit").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length == 1) {
                    var id = '';
                    $('input[name="bb"]:checked').each(function () {
                        id = $(this).attr("objId");
                    });
                    var url = "url:rules_editRules.do?obj.id=" + id + "&obj.firewall_id=" + $("#firewall_id").val();
                    ;
                    $.dialog({
                        id: 'editRules',
                        title: '修改防火墙规则',
                        height: 510,
                        width: 600,
                        lock: true,
                        content: url
                    });
                } else {
                    if ($('input[name="bb"]:checked').length > 0) {
                        alertMsg('请仅选择一条防火墙规则！');
                    }
                }
            });

            //删除确认？
            $("#del").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm('确定要删除防火墙规则吗？')) {
                        deleteRules();
                    }
                }
            });

            $("#controll").unbind().live('click', function () {
                var url = 'rules_setRulePage.do';
                $.dialog({
                    id: 'rule',
                    title: '设置规则',
                    lock:true,
                    content: url
                });
            })

            //删除,支持批量删除
            function deleteRules() {
                mask("正在删除防火墙规则", "info");
                var ids = '';
                $('input[name="bb"]:checked').each(function () {
                    ids += $(this).attr("objId") + ",";
                });

                var vmCode = '';
                $('input[name="bb"]:checked').each(function () {
                    vmCode += $(this).attr("objId") + ",";
                });

                $.ajax({
                    url: "rules_deleteRules.do?obj.id=" + ids,
                    type: "POST",
                    data: "text",
                    dataType: "json",
                    success: function (data) {
                        removeMask();
                        mask(data.result, "success", 3000);
                        $("#theForm").submit();
                    }
                });
            }

        })
        function addRules(url, params) {
            mask("正在添加防火墙规则", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("添加防火墙规则成功！", "success", 3000);
                        $("#theForm").submit();
                    } else {
                        mask("添加防火墙规则失败！", "error", 3000);
                    }
                }
            });
        }
        function updateRules(url, params) {
            mask("正在修改防火墙规则", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("修改防火墙规则成功！", "success", 3000);
                        $("#theForm").submit();
                    } else {
                        mask("修改防火墙规则失败！", "error", 3000);
                    }
                }
            });
        }
    </script>
</head>

<body>
<!--container star-->
<div class="mainbody">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/rules_listRules.do?type=<s:property value="type"/>"
                          method="post">


                        <div class="pd-20 bgcolor-1">
                            <div class="bord-1 pd-10">

                                <div class="clearfix filtrate-area">
                                    <div class="filtrate-field">
                                        <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:history.back()" value="返回"></span>
                                    </div>
                                </div>
                                <div class="utt-2 mgt-20">
                                    <a class="icon-add" href="javascript:void(0)" id="add">新增</a>
                                    <a class="icon-modify" href="javascript:void(0)" id="edit">修改</a>
                                    <a class="icon-del" href="javascript:void(0)" id="del">删除</a>
                                </div>
                                <s:hidden id="firewall_id" name="obj.firewall_id"></s:hidden>
                                <table border="0" width="100%"  class="blue-table" >
                                    <thead>
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>优先级</th>
                                <th>协议</th>
                                <th>行为</th>
                                <th>方向</th>
                                <th>起始端口</th>
                                <th>结束端口</th>
                                <th>源IP</th>
                            </tr>
                            </thead>
                                    <tbody>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'
                                                />
                                        </span>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.name"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.priority"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.protocol"/></span></td>
                                    <td><span class="font-blue">
                            		<s:if test="#theBean.action =='1'">
                                        接受
                                    </s:if>
                            		<s:else>
                                        拒绝
                                    </s:else>
                            	</span></td>
                                    <td><span class="font-blue">
                            		<s:if test="#theBean.direction =='1'">
                                        上行
                                    </s:if>
                            		<s:else>
                                        下行
                                    </s:else>
                            	</span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.val1"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.val2"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.val3"/></span></td>
                                </tr>
                            </s:iterator>
                        </table>
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