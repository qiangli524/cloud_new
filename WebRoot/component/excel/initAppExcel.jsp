<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/js/handsontable/css/custom.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/js/handsontable/css/jquery.handsontable.full.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/handsontable/js/jquery.handsontable.full.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/mask/mask.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	  $(document).ready(function () {
		  var $container = $("#handsontableDiv");
		  //添加遮罩层
		  mask('正在加载数据.....');
		  $container.handsontable({
	   		   //data: data,
	   		   startRows: 5,
	   		   startCols: 4,
	   		   colHeaders: true,
	   		   colHeaders: ['ID','主机IP', '用户名', '密码','是否是基准', '应用名称','应用路径'],
	   		   columns: [
	   		     {data: "appId"},
	   		     {data: "hostIp"},
	   		     {data: "hostUsername"},
	   		     {data: "sshPwd",type: "password"},
	   		     {data: "ifStandardApp",readOnly: true},
	   		     {data: "appName"},
	   		     {data: "basePath"}
	   		   ],
	   		   minSpareRows: 1,
	   		   afterChange : function (change, source) {
		   			if (source === 'loadData') {
		   		      return; //don't save this change
		   		    }
	   		   }
   		  });
		  var handsontable = $container.data('handsontable');
		  $.ajax({
		       url: "appexcel_initAppExcelData.do?appId=1173",
		       dataType: "json",
		       type: "POST",
		       success: function (data) {
		    	   handsontable.loadData(data);
		    	   //移除遮罩层
		    	   removeMask();
		 	   }
		  });
	  });
	  //保存数据
	  function saveData() {
		  var $container = $("#handsontableDiv");
		  var handsontable = $container.data('handsontable');
		  $.ajax({
		       url: "appexcel_saveAppExcelData.do",
		       dataType: "json",
		       type: "POST",
		       data : {'appData':JSON.stringify(handsontable.getData())},
		       success: function (data) {
		 	   }
		  });
	  }
  </script>
</head>
<body>
	<div id="handsontableDiv" class="handsontable"></div>
	<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:saveData()" />
</body>
