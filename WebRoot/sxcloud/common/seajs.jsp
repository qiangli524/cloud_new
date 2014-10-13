<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<!-- sea js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datav/deps.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datav//sea.js"></script>
<script>
 seajs.config({
      alias: {
        'DataV': '<%=request.getContextPath()%>/js/datav/datav.js',
        'Axis': '<%=request.getContextPath()%>/js/datav/charts/axis.js',
        'StreamAxis': '<%=request.getContextPath()%>/js/datav/charts/stream_axis.js',
        'Legend': '<%=request.getContextPath()%>/js/datav/charts/legend.js',
        'Navi': '<%=request.getContextPath()%>/js/datav/charts/navi.js',
        'Tip': '<%=request.getContextPath()%>/js/datav/charts/tip.js',
        'Percentage': '<%=request.getContextPath()%>/js/datav/charts/percentage.js',
        'HoverLine': '<%=request.getContextPath()%>/js/datav/charts/hover_line.js',
        'PathLabel': '<%=request.getContextPath()%>/js/datav/charts/path_label.js',
        'Cover': '<%=request.getContextPath()%>/js/datav/charts/cover.js',
        'Stream': '<%=request.getContextPath()%>/js/datav/charts/stream.js',
        'StreamComponent': '<%=request.getContextPath()%>/js/datav/components/stream.js',
        'Bubble':'<%=request.getContextPath()%>/js/datav/charts/bubble.js',
        'Bundle':'<%=request.getContextPath()%>/js/datav/charts/bundle.js',
        'Force':'<%=request.getContextPath()%>/js/datav/charts/force.js',
        'Tree':'<%=request.getContextPath()%>/js/datav/charts/tree.js',
        'Treemap':'<%=request.getContextPath()%>/js/datav/charts/treemap.js'
      }
    });
</script>
