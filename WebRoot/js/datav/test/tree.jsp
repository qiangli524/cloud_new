<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/seajs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<title>tree</title>
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
	 <div id="chart"></div>
</body>
<script>
seajs.use(["Tree", "DataV"], function (Tree, DataV) {
    var tree = new Tree("chart", {width:960});
    DataV.csv("../js/datav/test/tree.csv", function(source){
      tree.setSource(source);
      tree.render();
    });
  });
</script>
</html>
