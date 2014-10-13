<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/seajs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>Hierarchical Edge Bundling</title>
    <style type="text/css">
      .node {
        font: 12px sans-serif;
      }
      .link {
        stroke: steelblue;
        stroke-opacity: .4;
        fill: none;
      }
      </style>
</head>

<body>
	 <div id="chart"></div>
</body>
<script>
	seajs.use(["Bundle", "DataV"], function (Bundle, DataV) {
	    var bundle = new Bundle("chart", {
	        "diameter": 1000
	    });
	    DataV.json("../js/datav/test/bundle.json", function (source) {
	        bundle.setSource(source);
	        bundle.render();
	    });
	    /*
	    DataV.csv("bundle.csv", function(source){
	        bundle.setSource(source);
	        bundle.render();
	    });
	    */
	});
</script>
</html>
