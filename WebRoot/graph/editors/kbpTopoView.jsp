<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/WEB-INF/ibnms/common/base.jsp" %>
        <%@include file="/WEB-INF/ibnms/common/mxgraph.jsp" %>
        <title>KBP拓扑展现</title>
        <script type="text/javascript">
            $(function(){
                // Checks if the browser is supported
                if (!mxClient.isBrowserSupported()){
                    mxUtils.error('Browser is not supported!', 200, false);
                }else{
                    // Enables crisp rendering of all shapes in SVG
                    mxShape.prototype.crisp = true;
                    var $container = $('#graphContainer');
                    var graph = new mxGraph($container[0]);
                    //定义图标样式
                    var style = new Object();
                    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_IMAGE;
                    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
                    style[mxConstants.STYLE_IMAGE] = '../images/topo/SERVER.png';
                    style[mxConstants.STYLE_FONTCOLOR] = '#000000';
                    style[mxConstants.STYLE_SPACING_TOP] = '56';
                    graph.getStylesheet().putCellStyle('boxstyle', style);
                    graph.setTooltips(true);
                    graph.setCellsLocked(true);
                    var param = {
                        type:'<s:property value="#parameters.type"/>',
                        kbpClass:'<s:property value="#parameters.kbpClass"/>',
                        bzType:'<s:property value="#parameters.bzType"/>'
                    };
                   	$.get('<s:url action="findKbpTopoElementInfo" namespace="/display"/>',param,function(kbpArray){
                        graph.getModel().beginUpdate();
                        try{
                            drawTopo(kbpArray);
                        }finally{
                            // Updates the display
                            graph.getModel().endUpdate();
                        }
                   	});
                    graph.addListener(mxEvent.CLICK, function(sender, evt){
                        var cell = evt.getProperty('cell'); // cell may be null
                        if(cell && cell.id){
                            if(cell.id.substr(0, 1)=='$'){
                                cell = cell.parent;
                            }
                            if($.isFunction(window.parent.onTopoNodeClick)){
                                var kbp = $container.data('kbpMap')[cell.id];
                                window.parent.onTopoNodeClick.call(kbp, kbp);
                            }
                        }
                    });
                    graph.addListener(mxEvent.DOUBLE_CLICK, function(sender, evt){
                        var cell = evt.getProperty('cell'); // cell may be null
                        if(cell && cell.id){
                            if(cell.id.substr(0, 1)=='$'){
                                cell = cell.parent;
                            }
                            if($.isFunction(window.parent.onTopoNodeDoubleClick)){
                                var kbp = $container.data('kbpMap')[cell.id];
                                window.parent.onTopoNodeDoubleClick.call(kbp, kbp);
                            }
                        }
                    });
                }

                function drawTopo(kbpArray){
                    var width = parseInt($(window).width()/10)*10+1;
                    var cols = parseInt(width/70);
                    var height = (parseInt(kbpArray.length/cols)+1)*70+11;
                    $container.css({width:width,height:height});
                    var kbpMap={};
                    $.each(kbpArray, function(i){
                        kbpMap[this.kbpClass]=this;
                        var nodeName=this.kbpCaption.replace(/\:/g,function(k){return k+'\n'}).replace(/(\(.+\))/g,function(k){return '\n'+k});
                        var v=graph.insertVertex(graph.getDefaultParent(), this.kbpClass, nodeName, 10+70*(i%cols), 10+70*parseInt(i/cols), 48, 48, getStyle());
                        if(this.eventCount>0){
                            var g=v.geometry,style=getAlarmStyle(this.eventClass);
                            graph.insertVertex(v.parent,'$'+this.kbpClass,this.eventCount,g.x+g.width-18,g.y,18,16,style);
                        }
                    });
                    $container.data('kbpMap',kbpMap);
                }

                function getStyle(kbpClass){
                    return 'boxstyle';
                }
                var eventColorDict= <s:property default="{}" value="#toJSON(#getSysDict('136').itemsMap)" escape="false"/>
                function getAlarmStyle(eventClass){
                    var style={};
                    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE;
                    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.EllipsePerimeter;
                    style[mxConstants.STYLE_FONTCOLOR] = '#FFFFFF';
                    style[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD;
                    if(eventClass==3){
                        style[mxConstants.STYLE_FONTCOLOR] = '#AAAAAA';
                    }
                    style[mxConstants.STYLE_FILLCOLOR] = eventColorDict[eventClass].itemName;
                    style[mxConstants.STYLE_STROKECOLOR] = style[mxConstants.STYLE_FILLCOLOR];
                    var str='';
                    for(var key in style){
                        str+=key+'='+style[key]+';';
                    }
                    return str;
                }
            });
        </script>
    </head>
    <body>
        <!-- Creates a container for the graph with a grid wallpaper -->
        <div id="graphContainer"
             style="overflow:hidden;width:100%;height:81px;background:url('${mxBasePath}/editors/images/grid.gif')">
        </div>
    </body>
</html>
