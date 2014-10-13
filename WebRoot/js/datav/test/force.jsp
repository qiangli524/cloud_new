<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/seajs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>Bubble Graph</title>
	<style type="text/css">
	#chart {
		
	}
	.textArea {
	    border: 2px solid black;
	    color: black;
	    font-family: monospace;
	    height: 3in;
	    overflow: auto;
	    padding: 0.5em;
	    width: 750px;
	}
	</style>
</head>

<body>
  <div class="body">
    <div class="gallery" id="chart"></div>
  </div>
</body>
<script>
	seajs.use(["Force", "DataV"], function (Force, DataV) {
	    // DataV.changeTheme("datav");
	    var net = new Force("chart", {
	      width: 800,
	      height: 600,
	      tag: true
	    });
	    DataV.csv("../js/datav/test/force.csv", function (source) {
	      net.setSource(source, {id: 0, name: 1, nValue: 2, source: 3, target: 4, lValue: 5});
	      net.render();
	    });
	  });
</script>
</html>
