<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <base href="<%=basePath%>">

    <title>上传ISO镜像</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/publicCloud/pub-ui/js/webuploader-0.1.0-dist/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/uploadimage.css"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/webuploader-0.1.0-dist/jquery-1.10.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/webuploader-0.1.0-dist/webuploader.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/image/md5_md5blob.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/image/uploadimage.js"></script>
    <script type="text/javascript">
    </script>

</head>
<body>
<div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="thelist" class="uploader-list"></div>
    <div class="statusBar" style="display:none;">
        <div class="progress">
            <span class="text">0%</span>
            <span class="percentage"></span>
        </div>
        <div class="info"></div>
    </div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>

</div>
</body>
</html>
