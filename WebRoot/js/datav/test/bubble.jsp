<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/seajs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>Bubble Graph</title>
</head>

<body>
	 <div id="chart"></div>
</body>
<script>
	seajs.use([ "Bubble", "DataV" ], function(Bubble, DataV) {
		var bubble = new Bubble("chart", {
			"width" : 600,
			"height" : 400,
			// "showLegend": false
			"margin" : [ 10, 10, 80, 200 ]
		});
		DataV.csv("../js/datav/test/bubble.csv", function(dataSource) {
			bubble.setSource(dataSource);
			bubble.render();
		});
	});
</script>
</html>
