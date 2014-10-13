<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>

<head>
<title></title>
<script type="text/javascript">
	var example_id = '<%=request.getAttribute("example_id")%>';
    var script_id = '<%=request.getAttribute("script_id")%>';
    var type = '<%=request.getAttribute("type")%>';
	$(function(){
		$("#open").click(function(){
				var command = $("#path").val()+'';
				mask('正在执行','0.7','');
			   var url = "deployscript_start.do?script_id="+script_id+'&example_id='+example_id+'&type='+type+'&command='+command;
			  	$.ajax({
			 	 	type:"GET",
              		url:url,
              		data:"text",
              		async: true,
             		cache: false,
	          		success: function(msg){
                	if(msg==null){
                		}else{
                		removeMask();
                		$("#textareaContent").text(msg);
                	}
             	 }
			});
		});
	});

</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
<div class="scrollbody">
	<div class="box on">
        <div class="query-form" style="width: 100%;height: 100%">
                <table style="width: 100%;height: 100%" border="0">
                  <tr>
                  	<td colspan="2" align="left">
                  	    &nbsp;&nbsp;&nbsp;&nbsp;<label style="font-size:medium;margin-bottom:15px">
                  	    命令：<label/><input type="text" id="path" style="width: 420px;height: 20px" /><input type="button" id="open" value="执行" class="btn-style02"/>
                  	</td>
                  </tr>
                  <tr>
                    <td align="left" >	
                      <div style="width:686px" class="blue-wrap noborder" id="content">
                            <textarea rows="27" cols="100" id="textareaContent"></textarea>
	                  </div>
	                 </td>
	                 <td width="100%;" valign="top">
        </div>
	</div>
</div>
</s:form>
</body>
