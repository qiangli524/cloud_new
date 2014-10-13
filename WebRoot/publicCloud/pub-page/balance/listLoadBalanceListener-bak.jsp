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
<div class="mainbody">
    <form id="theForm"  action="<%=request.getContextPath() %>/loadBalanceListener_listLoadBalanceListener.do?type=<s:property value="type"/>"   method="post">
        <s:hidden name="lbObj.name"></s:hidden>
        <s:hidden name="obj.load_id" id="load_id"></s:hidden>
        <div class="pd-20 bgcolor-1">
            <div class="bord-1 pd-10">

                <div class="clearfix filtrate-area">
                    <div class="filtrate-field">
                        <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:history.back()" value="返回"></span>
                    </div>

                </div>

                <div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" id="add">新增</a>
                    <a class="icon-del" href="javascript:void(0)" id="del">删除</a>
                    <%--<a href="javascript:;" class="bindtovm" id="bind">绑定公网IP</a>
                    <a href="javascript:;" class="unbindfromvm" id="unbind">解绑公网IP</a>--%>
                </div>

                <table border="0" width="100%"  class="blue-table" >
                    <thead>
                            <tr>
                                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                                <th>id</th>
                                <th>监听器名称</th>
                                <th>公网IP</th>
                                <th>监听端口</th>
                            </tr>
                    </thead>
                    <tbody>
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