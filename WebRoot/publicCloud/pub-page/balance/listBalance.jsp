<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
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
                search();

            });

            //Enter 搜索
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    search();
                }
            }

            //重命名点击事件
            $(".btn-w8").click(function () {
                var newnameInput = $(this).parent().parent().find(".input-compile");

                if (newnameInput.val() == null || newnameInput.val().trim() == "") {
                    alartMsg('请填写名称！');
                    return;
                }
                var oldnameP = $(this).parent().parent().siblings("p");
                var objId = $(this).attr("objId");
                var params = {
                    "obj.id": objId,
                    "obj.name": newnameInput.val()
                };

                $.ajax({
                    url: "balance_rename.do",
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


            $("[name='add']").click(function () {
                currentEdit = $(this);
                $.dialog({
                    id: 'applyBalance',
                    title: '新建负载均衡',
                    width: '500px',
                    height: '400px',
                    lock: true,
                    content: 'url:balance_applyPage.do'
                });
            });

            //点击名称进入负载监听器页面
            $(".listener").unbind().live('click', function () {
                var id = $(this).attr("objId");
                var url = "loadBalanceListener_listLoadBalanceListener.do?type=25&obj.load_id=" + id;
                location.href = url;
            })

            $("[name='mod']").click(function () {
                currentEdit = $(this);
                var lenth = $('[name=bb]:checkbox:checked').length;
                if (lenth == 0) {
                    alertMsg('请先选择一项进行修改');
                    return false;
                } else if (lenth > 1) {
                    alertMsg('只能选择一项进行修改');
                    return false;
                }
                var id = $('[name=bb]:checkbox:checked').val();
                $.dialog({
                    id: 'updateBalance',
                    title: '修改负载均衡器属性',
                    width: '770px',
                    height: '500px',
                    max: true,
                    min: true,
                    lock: true,
                    content: 'url:balance_editPage.do?obj.id=' + id
                });
            });

            $("[name='del']").click(function () {
                currentEdit = $(this);
                var lenth = $('[name=bb]:checkbox:checked').length;
                if (lenth == 0) {
                    alertMsg('请先选择一项进行删除');
                    return false;
                } else if (lenth > 1) {
                    alertMsg('只能选择一项进行删除');
                    return false;
                }
                var id = $('[name=bb]:checkbox:checked').val();
                var url = 'balance_deleteBalance.do?obj.id=' + id;
                $.ajax({
                    type: "GET",
                    url: url,
                    data: "text",
                    async: true,
                    cache: false,
                    success: function (msg) {
                        search();
                    }
                });
            });
        })
        //保存负载
        function saveBalance(url, params) {
            $.ajax({
                type: "POST",
                url: url,
                data: params,
                dataType: "json",
                async: true,
                cache: false,
                success: function (msg) {
                    search();
                }
            });
        }
        function search() {
            obj.action = "balance_listBalance.do";
            obj.submit();
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
            <h2 class="title-common6">
                <a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_lb.gif"
                    width="65" height="65"/>负载均衡
            </h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">可以将来自多个公网地址的访问流量分发到多台主机上。同时，你还可以随时通过添加或删减主机来调整你的服务能力，而且这些操作不会影响业务的正常访问</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <s:form action="balance_listBalance.do" id="obj" method="GET">
                <div class="col-c8">
                    <ul class="operate-list">
                        <li class="first"><a href="#" class="add" name="add"></a></li>
                        <%--<li class="gray_button aa"><a href="javascript:void(0);" class="delete" name="del">删除</a></li>
								--%><!-- <li class="gray_button aa">
									<a href="javascript:void(0);" class="close" name="mod">实例修改</a>
								</li> -->
                        <li class="search"><input type="text" class="text-1"
                                                  placeholder="名称"/> <input type="button" class="search-btn"/>
                        </li>
                    </ul>
                    <table border="0" width="100%" class="table-f4 ">
                        <tr>
                            <th><input type="checkbox" class="vhid"/>
                            </th>
                            <th>ID</th>
                            <th>名称</th>
                            <th>类型</th>
                            <th>状态</th>
                            <th>付费方式</th>
                        </tr>
                        <s:iterator value="resultList" id="theBean">
                            <tr>
                                <td>
                                    <input type="checkbox" name="bb" id="id" value='<s:property value="#theBean.id"/>'
                                           class="code"/>
                                </td>
                                <td>
										<span class="font-blue">
										 <a class="listener" href="javascript:void(0);" title="配置监听器"
                                            objid="<s:property value="#theBean.id" />">
                                             <s:property value="#theBean.id"/>
                                         </a>
										</span>
                                </td>
                                <td>
                                    <div class="compile js_compile">
                                        <p><s:property value="#theBean.name"/></p>

                                        <div class="compile-con">
                                            <h3 class="fs14">编辑名称：</h3>

                                            <div class="mgt-10"><input type="text" maxlength="20" class="input-compile"
                                                                       value='<s:property value="#theBean.name" />'/>
                                            </div>
                                            <div><a class="btn-w8" href="javascript:;"
                                                    objId='<s:property value="#theBean.id" />'>提交</a><a
                                                    class="btn-w9 js_abrogate" href="javascript:;">取消</a></div>
                                        </div>
                                    </div>

                                </td>
                                <td><em class="em-c"> <s:if test="#theBean.type==1">公网负载</s:if>
                                    <s:else>内网负载</s:else> </em></td>
                                <td><em class="em-c"> <s:if test="#theBean.state==1">正在使用</s:if>
                                    <s:else>未使用</s:else> </em></td>
                                <td>
                                        <span class="font-blue">

                                            <s:if test="#theBean.purchase_way==2">包年包月</s:if>
                                            <s:else>按小时</s:else><br/>
                                            <fmt:formatDate value="${theBean.begin_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            -
                                           <fmt:formatDate value="${theBean.end_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</span>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <!-- 分页start -->
                    <div class="page mgt34-b25">
                        <jsp:include page="../../inc/fenye.jsp?formId=obj"/>
                    </div>
                    <!-- 分页end -->
                </div>
            </div>
            </s:form>
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
        <p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号
            增值电信业务经营许可证A2.B1.B2-20090001</p>
    </div>
</div>
<!--版权 end-->

</body>
</html>
