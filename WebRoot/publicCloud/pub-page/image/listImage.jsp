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
                var name = $("#image_name").val();
                if (null != name && "" != name) {
                    $("#theForm").submit();
                }

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
                    "obj.alias": newnameInput.val()
                };

                $.ajax({
                    url: "images_updateImage.do",
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

            //ISo文件上传页面
            $("#upload").click(function () {
                var url = "url:images_uploadPage.do";
                $.dialog({
                    id: 'uploadImage',
                    title: 'ISO镜像上传',
                    width: 848,
                    height: 269,
                    //lock:true,
                    content: url
                });
            });

            //Enter 搜索
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("#theForm").submit();
                }
            }

            //删除镜像确认？
            $("#delete").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length > 0) {
                    if (confirm('确定要删除镜像吗？')) {
                        deleteimage();
                    }
                }
            });

            //重命名
            $("#rename").unbind().live('click', function () {
                if ($('input[name="bb"]:checked').length == 1) {
                    var id = '';
                    $('input[name="bb"]:checked').each(function () {
                        id = $(this).attr("objId");
                    });
                    var url = "url:images_rename.do?obj.id=" + id;
                    $.dialog({
                        id: 'renameImg',
                        title: '修改镜像信息',
                        height: 300,
                        width: 500,
                        lock:true,
                        content: url
                    });
                } else {
                    if ($('input[name="bb"]:checked').length > 0) {
                        alertMsg('请仅选择一个镜像！');
                    }
                }

            });

            //删除镜像,支持批量删除
            function deleteimage() {
                mask("正在删除镜像……", "info");
                var vmCode = '';
                $('input[name="bb"]:checked').each(function () {
                    vmCode += $(this).attr("objId") + ",";
                });

                $.ajax({
                    url: "images_deleteImage.do?obj.id=" + vmCode,
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

        //执行重命名操作
        function renameImage(url, params) {
            mask("正在执行重命名操作……", "info");
            $.ajax({
                url: url,
                type: "POST",
                data: params,
                dataType: "json",
                success: function (data) {
                    removeMask();
                    if (data.result == "success") {
                        mask("重命名成功！", "success", 3000);
                        $("#theForm").submit();
                    } else {
                        mask("重命名失败！", "error", 3000);
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
            <!--标题 star-->
            <h2 class="title-common6"><a href="#" class="home"></a><img
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_image.gif" width="65"
                    height="65"/>镜像</h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">镜像 (Image) 是带有操作系统的主机模板。青云官方会提供主流的 Linux、Windows
                模板，并根据上游厂商更新版本时及时制作新模板。用户也可以在将自己名下的主机制作成模板，以备后用。</p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/images_listImage.do?type=<s:property value="type"/>"
                          method="post">
                        <%--             	<input type="hidden" name="type" value='<s:property value="type"/>' id="navType"/> --%>
                        <ul class="operate-list">
                            <!-- <li class="first"> -->
                            <%--<li class="first">
                                <a href="#" class="add" id="create"></a>
                            </li>
                            --%>
                            <li class="gray_button aa">
                                <a href="#" class="edit" id="rename">修改</a>
                            </li>
                            <li class="gray_button aa">
                                <a href="#" class="delete" id="delete">删除</a>
                            </li>

                            <!--  <li class="blue_button">
                                 <a href="javascript:void(0)" class="upload" id="upload">上传</a>
                             </li> -->

                            <li class="search">
                                <input type="text" class="text-1" placeholder="镜像名称" name="obj.alias" id="image_name"
                                       value="<s:property value='obj.alias'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>
                        <table border="0" width="100%" class="table-f4">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>名称</th>
                                <th>操作系统</th>
                                <!--  <th>类型</th>
                                 <th>是否公有</th>
                                 <th>是否为物理模板</th> -->
                                <th>是否可用</th>
                                <th>配置(CPU|内存|存储)</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td><input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'
                                               vmCode='<s:property value="#theBean.templateCode" />'
                                               connectCode='<s:property value="#theBean.connectId" />'/></td>
                                    <td>
                                        <div class="compile js_compile">
                                            <p><s:property value="#theBean.alias"/></p>

                                            <div class="compile-con">
                                                <h3 class="fs14">编辑名称：</h3>

                                                <div class="mgt-10"><input type="text" maxlength="20"
                                                                           class="input-compile"
                                                                           value='<s:property value="#theBean.alias" />'/>
                                                </div>
                                                <div><a class="btn-w8" href="javascript:;"
                                                        objId='<s:property value="#theBean.id" />'>提交</a><a
                                                        class="btn-w9 js_abrogate" href="javascript:void(0);">取消</a>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.system"/></span></td>
                                        <%-- <td>
                                            <span class="font-blue">
                                            <s:if test="#theBean.type==1">Vmware虚拟机模板</s:if>
                                            <s:elseif test="#theBean.type==2">Xen虚拟机模板</s:elseif>
                                            <s:else>OVF模板</s:else>
                                            </span>
                                        </td>

                                        <td>
                                            <span class="font-blue">
                                                <s:if test="#theBean.isPublic==0">公有</s:if>
                                                <s:else>私有</s:else>
                                            </span>
                                        </td>

                                        <td>
                                            <span class="font-blue">
                                                <s:if test="#theBean.isPhysical==1" >是</s:if>
                                                <s:else>否</s:else>
                                            </span>
                                        </td>
                                         --%>

                                    <td>
	                            	<span class="font-blue">
		                            	<s:if test="#theBean.usable==null || #theBean.usable==''||#theBean.usable==0">可用</s:if>
		                				<s:else>不可用</s:else>
                					</span>
                                    </td>

                                    <td>
                                        <span class="font-blue"><s:property value="#theBean.cpu"/>核</span>|
                                        <span class="font-blue"><s:property
                                                value="@java.lang.Math@round(#theBean.mem/1024)"/>G</span>|
                                        <span class="font-blue"><s:property
                                                value="@java.lang.Math@round(#theBean.store/1024)"/>G</span>
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
