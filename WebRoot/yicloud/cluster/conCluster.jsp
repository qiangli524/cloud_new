<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">
    $(function(){
    	
    	$("#right").attr("src","cluster_goConfigHA.do");
    })
    	function configDRS(){
    		$("#right").attr("src","cluster_goConfigDRS.do");
    	}
    	
    	function configHA(){
    		$("#right").attr("src","cluster_goConfigHA.do");
    	}
    	
    	function drsGroup(){
    		$("#right").attr("src","cluster_goDRSGroup.do");
    	}
    </script>
</head>

<body >
  
  <iframe src="clusterSet.jsp" width="200" height="700" name="left_set" id="left"></iframe>
  <iframe src="" width="899" height="700" name="right_set" id="right" ></iframe>



</body>
</html>