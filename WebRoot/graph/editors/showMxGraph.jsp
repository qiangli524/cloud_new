<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <%@include file="import-mxgraph.jsp" %>
        <title>拓扑图编辑器</title>
        <script type="text/javascript">
            $(function(){
                //var oMxGraph=<s:property default="{}" value="#toJSON(mxGraph)" escape="false"/>;
                //由Struts配置文件进行JSON转换
                var oMxGraph=<s:property default="{}" value="mxGraph" escape="false"/>;
                var graph,hideSplash = function(){
                    var splash = document.getElementById('splash');
                    if (splash != null){
                        try{
                            mxEvent.release(splash);
                            mxEffects.fadeOut(splash, 100, true);
                        }catch (e) {
                            splash.parentNode.removeChild(splash);
                        }
                    }
                };
                try{
                    if (!mxClient.isBrowserSupported()){
                        mxUtils.error('您的浏览器不支持此功能！', 200, false);
                    }else{
                        graph = new mxGraph(document.getElementById('graph'));
                        $("#graph").data("graph", graph);
                        var node = mxUtils.load('config/wfgraph-commons.xml').getDocumentElement();
                        var dec = new mxCodec(node.ownerDocument);
                        dec.decode(node,graph);
                        //new mxCellTracker(graph, '#AAAAAA');//鼠标悬停时高亮
                        graph.setTooltips(true);//允许弹出提示
                        graph.setCellsLocked(true);//禁止编辑
                        //graph.setResizeContainer(true);
                        if(oMxGraph&&oMxGraph.graphId){
                            var doc = mxUtils.parseXml(oMxGraph.graphContent);
                            var codec = new mxCodec(doc);
                            codec.decode(doc.documentElement, graph.getModel());
                            document.title=oMxGraph.graphName;
                        }
                        graph.addListener(mxEvent.DOUBLE_CLICK,function(sender, evt){
                            var subGraphId=evt.getProperty('cell').getAttribute("subGraphId");
                            if(subGraphId){
                                location.href="<s:url/>?mxGraph.graphId="+subGraphId+"&backurl="+encodeURIComponent(location.href);
                            }
                        });
                    }
                }catch (e){
                    // Shows an error message if the editor cannot start
                    mxUtils.alert('Cannot start application: '+e.message);
                    throw e; // for debugging
                }finally{
                    // Shows the application
                    hideSplash();
                }
            });
        </script>
    </head>
    <body>
        <table id="splash" width="100%" height="100%"
               style="background:white;position:absolute;top:0px;left:0px;z-index:4;">
            <tr>
                <td align="center" valign="middle">
                    <img src="images/loading.gif">
                </td>
            </tr>
        </table>
        <div id="graph" class="base" style="width: 99.9%;border: none">
            <!-- Graph Here -->
        </div>
    </body>
</html>
