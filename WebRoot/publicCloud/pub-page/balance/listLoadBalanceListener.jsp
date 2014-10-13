<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>控制中心</title>
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
        var name = $("#loadBalanceListener").val();
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
            url: "loadBalanceListener_rename.do",
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

    //进入添加页面
    $("#add").unbind().live('click', function () {
        var url = "url:loadBalanceListener_addLoadBalanceListenerPage.do?obj.load_id=" + $("#load_id").val();
        $.dialog({
            id: 'addLoadBalanceListener',
            title: '添加负载监听器',
            height: 220,
            width: 420,
            lock: true,
            content: url
        });
    });

    //进入配置页面
    $(".servercfg").unbind().live('click', function () {
        var listener_id = $(this).attr("objid");
        $.dialog({
            id: 'updateBalanceListener',
            title: '配置负载监听器',
            width: '770px',
            height: '570px',
            lock: true,
            content: 'url:loadBalanceListener_config.do?obj.id=' + listener_id
        });
    });
    /*
     //进入修改页面
     $("#edit").unbind().live('click',function(){
     if($('input[name="bb"]:checked').length == 1){
     var id='';
     $('input[name="bb"]:checked').each(function(){
     id = $(this).attr("objId");
     });
     var url = "url:loadBalanceListener_editLoadBalanceListener.do?obj.id="+id;
     $.dialog({
     id:'editLoadBalanceListener',
     title:'修改负载监听器',
     width:600,
     height: 190,
     content: url
     });
     } else{
     if($('input[name="bb"]:checked').length > 0){
     alertMsg("请仅选择一个负载监听器");
     }
     }
     });
     */
    //删除 验证  & 确认？
    $("#del").unbind().live('click', function () {
        if ($('input[name="bb"]:checked').length == 1) {
            var id = '';
            $('input[name="bb"]:checked').each(function () {
                id = $(this).attr("objId");
            });

            //删除前验证：是否存在后端服务器、是否未解绑公网IP
            $.ajax({
                url: "loadBalanceListener_validateBalanceListener.do?obj.id=" + id,
                type: "POST",
                data: "text",
                dataType: "json",
                success: function (data) {
                    if ("exist" == data.servers) {
                        alertMsg("您要删除的监听器存在后端服务器，请先删除其后端服务器后再进行此次操作!");
                    } else if ("unbind" == data.ip) {
                        alertMsg("您要删除的监听器存在后端服务器，请先删除其后端服务器后再进行此次操作!");
                    } else {
                        if (confirm('确定要删除负载监听器吗？')) {
                            deleteLoadBalanceListener(id);
                        }
                    }
                }
            });

        } else {
            if ($('input[name="bb"]:checked').length > 0) {
                alertMsg("请仅选择一个负载监听器");
            }
        }
    });


    //删除
    function deleteLoadBalanceListener(id) {
        mask("正在删除负载监听器，请稍候", "info");
        $.ajax({
            url: "loadBalanceListener_deleteLoadBalanceListener.do?obj.id=" + id,
            type: "POST",
            data: "text",
            dataType: "json",
            success: function (data) {
                removeMask();
                if ("success" == data.result) {
                    mask("删除负载监听器成功！", "success", 3000);
                    $("#theForm").submit();
                } else {
                    mask("删除负载监听器成功！", "error", 3000);
                }

            }
        });
    }

})
function addLoadBalanceListener(url, params) {
    mask("正在添加负载监听器，请稍候", "info");
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        dataType: "json",
        success: function (data) {
            removeMask();
            if (data.result == "success") {
                mask("添加负载监听器成功！", "success", 3000);
                $("#theForm").submit();
            } else {
                mask("添加负载监听器失败！", "error", 3000);
            }
        }
    });
}
/*
 function updateLoadBalanceListener(url,params){
 mask("正在修改负载监听器，请稍候","info");
 $.ajax({
 url:url,
 type:"POST",
 data:params,
 dataType:"json",
 success:function(data){
 removeMask();
 if(data.result == "success"){
 mask("修改负载监听器成功！","success",3000);
 $("#theForm").submit();
 }else{
 mask("修改负载监听器失败！","error",3000);
 }
 }
 });
 }
 function refresh()
 {
 parent.location.reload();
 }
 */
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
                    src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_lb.gif" width="65" height="65"/>负载监听器
            </h2>
            <!--标题 end-->
            <!--文字介绍 star-->
            <p class="p-con4">负载均衡器：<font color="red">${lbObj.name} </font></p>
            <!--文字介绍 end-->
            <!--pd-15 star-->
            <div class="pd-15">
                <div class="col-c8">
                    <form id="theForm"
                          action="<%=request.getContextPath() %>/loadBalanceListener_listLoadBalanceListener.do?type=<s:property value="type"/>"
                          method="post">
                        <s:hidden name="lbObj.name"></s:hidden>
                        <s:hidden name="obj.load_id" id="load_id"></s:hidden>
                        <ul class="operate-list">
                            <!-- <li class="first"> -->
                            <li class="blue_button">
                                <a href="javascript:void(0);" class="add" id="add"></a>
                            </li>
                            <%--
                                                    <li class="gray_button aa">
                                                        <a href="#" class="edit" id="edit">修改</a>
                                                    </li>
                                                    --%>
                            <li class="gray_button aa">
                                <a href="#" class="delete" id="del">删除</a>
                            </li>
                            <%--<li class="gray_button aa">
                                <a href="#" class="upgrade" id="servercfg">后端服务器配置</a>
                            </li>
                            --%>
                            <%--公网负载才有绑定公网IP操作 公网负载 1:，内网负载 2 --%>
                            <s:if test="#lbObj.type==1">
                                <li class="gray_button aa">
                                    <a href="#" class="more js_more"> 更多操作...</a>
                                    <dl class="operate-dl gray_button aa">
                                        <dd><a href="javascript:;" class="bindtovm" id="bind">绑定公网IP</a></dd>
                                        <dd><a href="javascript:;" class="unbindfromvm" id="unbind">解绑公网IP</a></dd>
                                    </dl>
                                </li>
                            </s:if>
                            <li class="search">
                                <input type="text" class="text-1" placeholder="负载监听器名称" name="obj.name"
                                       id="loadBalanceListener" value="<s:property value='obj.name'/>"/>
                                <input type="button" class="search-btn">
                            </li>
                        </ul>

                        <table border="0" width="100%" class="table-f4 ">
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>id</th>
                                <th>监听器名称</th>
                                <th>公网IP</th>
                                <th>监听端口</th>
                            </tr>
                            <s:iterator value="resultList" id="theBean">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="bb" class="code"
                                               objId='<s:property value="#theBean.id" />'/>
                                    </td>
                                    <td>
        <span class="font-blue">
         <a class="servercfg" href="javascript:void(0);" title="配置后端服务器"
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

                                                <div class="mgt-10"><input type="text" maxlength="20"
                                                                           class="input-compile"
                                                                           value='<s:property value="#theBean.name" />'/>
                                                </div>
                                                <div><a class="btn-w8" href="javascript:;"
                                                        objId='<s:property value="#theBean.id" />'>提交</a><a
                                                        class="btn-w9 js_abrogate" href="javascript:;">取消</a>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td><span class="font-blue"><s:property value="#theBean.public_ip"/></span></td>
                                    <td><span class="font-blue"><s:property value="#theBean.load_port"/></span></td>
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
