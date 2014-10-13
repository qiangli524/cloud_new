<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/seajs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>treemap</title>
	<style type="text/css">
	#chart {
	    padding-left: 20px;
	}
	</style>
</head>

<body>
	<div id="hover">hover node info</div>
    <div id="info">click leaf node info</div>
    <div id="chart"></div>
</body>
<script>
	seajs.use(["Treemap", "DataV"], function (Treemap, DataV) {
	    DataV.changeTheme("theme0");
	    var treemap = new Treemap("chart");
	    d3.json("../js/datav/test/treemap.json", function (source) {
	        treemap.setSource(source);
	        treemap.on("leafNodeClick", function () {
	            $("#info").html(this.name + " is a leaf node.");
	        });
	        treemap.on("hoverIn", function () {
	            $("#hover").html("hover " + this.name);
	        });
	        treemap.on("hoverOut", function () {
	            $("#hover").html("leave " + this.name);
	        });
	        treemap.render();
	    });
	});
</script>
</html>
