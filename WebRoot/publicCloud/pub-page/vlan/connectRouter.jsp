<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@    taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'createSnapShot.jsp' starting page</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: connectRouter,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });

            //根据 物理Vlan个数渲染页面
            renderInfo();
        });


        function connectRouter() {
            var url = "physicalVlan_connectRouter.do?" + $("#obj").serialize();
            w.connectRouter(url);
        }
    </script>
</head>
<body>
<div class="modal" style="width: 600px;height: auto;margin-left: -270px;margin-top: -120px;top: 50%;">
    <div class="modal-content">
        <form id="obj" class="form form-horizontal">
            <fieldset>
                <legend>连接路由器</legend>
                <div class="item">
                    <div class="control-label">
                        路由器
                    </div>
                    <div class="controls">
                        <div class="select-con">
                            <s:select list="resultList" listKey="id"
                                      listValue="name" id="router_id" name="obj.router_id" headerKey="0"
                                      headerValue="--请选择--" class="dropdown-select"></s:select>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
