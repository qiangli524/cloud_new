<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>请稍等</title>
        <script type="text/javascript">
            (function(url){
                var index=url.indexOf("?");
                if(index<0){index=url.length}
                var funcName = url.substr(0, index).split("/").pop().split(".")[0];
                var param = decodeURIComponent(url.substr(index+1));
                window.opener[funcName](window,param);
            })(location.href);
        </script>
    </head>
    <body>
        <h1>正在处理返回数据，请稍等。。。</h1>
    </body>
</html>
