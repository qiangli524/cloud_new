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
	  	 var api = frameElement.api;
		 var w = api.opener;
		 api.button({
		     id:'Ok',
		     name: '确定',
		     callback:saveData,
		     focus: false
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	  $(document).ready(function () {
		  var $container = $("#handsontableDiv");
		  var $window = $(window);
		  var maxed = false,availableWidth, availableHeight;
		  var calculateSize = function () {
			  var offset = $container.offset();
			  availableWidth = $window.width() - offset.left + $window.scrollLeft();
			  availableHeight = $window.height() - offset.top + $window.scrollTop();
		  };
		  $window.on('resize', calculateSize);
		  
		  $container.handsontable({
	   		   //data: data,
	   		   startRows: 5,
	   		   startCols: 4,
	   		   colHeaders: true,
	   		   colHeaders: ['ID','实例名称', '进程名称', '进程标示','同名进程数量', '启动脚本','停止脚本','进程描述'],
	   		   //colWidths:  [55, 80, 80, 80, 80, 80, 80],
	   		   //manualColumnResize: true,
	   		   minSpareRows: 1,
	   		   fillHandle : 'vertical',
	   		   //contextMenu: ["row_below", "remove_row"],
		   	   //nativeScrollbars: true,
		   	   columnSorting : true,
		   	   stretchH: 'all',
			   width: function () {
				  if (maxed && availableWidth === void 0) {
				   	calculateSize();
				  }
			   	  return maxed ? availableWidth : $window.width()-10;
			   },
			   height: function () {
			   	  if (maxed && availableHeight === void 0) {
			   	     calculateSize();
			   	  }
			   	  return maxed ? availableHeight : $window.height()- 50;
			   },
	   		   afterChange : function (change, source) {
		   			if (source === 'loadData') {
		   		      return; //don't save this change
		   		    }
	   		   }
   		  });
		  
		  var handsontable = $container.data('handsontable');
		  //加载数据
		  loadData($container,handsontable);
	  });
	  // 加载数据
	  function loadData($container,handsontable) {
		  var appId = '<s:property value="appId" />';
		  //添加遮罩层
		  mask('正在加载数据.....');
		  $.ajax({
		       url: "appexcel_initAppProcessExcelData.do?appId="+appId,
		       dataType: "json",
		       type: "POST",
		       success: function (data) {
		    	   $container.handsontable('updateSettings',{ columns: [
		    	                                      	   		     {data: "id",readOnly: true},
		    	                                    	   		     {data: "example_name",type: 'dropdown',source: data.exampleNameList},
		    	                                    	   		     {data: "process"},
		    	                                    	   		     {data: "process_KEY"},
		    	                                    	   		     {data: "process_COUNT",type : 'numeric'},
		    	                                    	   		     {data: "start_SCRIPT"},
		    	                                    	   		     {data: "stop_SCRIPT"},
		    	                                    	   		     {data: "process_DESC"}
		    	                                    	   		   ]});
		    	   handsontable.loadData(data.processList);
		    	   $container.handsontable('updateSettings',{
		 	   		  cells: function (row, col, prop) { 
			   			  var cellProperties = {} 
			   			  if(col === 0) { 
			   				  cellProperties.width = 50; 
			   			  } else if (col === 1 && handsontable.getDataAtCell(row,col) != undefined && handsontable.getDataAtCell(row,col-1) != null) {
			   				  cellProperties.readOnly = true;
			   			  }
			   			  return cellProperties; 
			   		  },
			   		 contextMenu: {
		   	   			items: {
		   	   		      "row_below": {
		   	   		        disabled: function () {
		   	   		          return ($container.handsontable('getSelected')[0] != (handsontable.countRows()-1));
		   	   		        }
		   	   		      },
		   	   		      "remove_row" : {
		   	   		    	  
		   	   		      }
		   	   			}
		   	   		   }});
		    	   //移除遮罩层
		    	   removeMask();
		 	   }
		  });
	  }
	  
	 //保存数据
	  function saveData() {
		  api.button({id:'Ok',disabled:true});
		  mask('正在保存数据.....');
		  var $container = $("#handsontableDiv");
		  var handsontable = $container.data('handsontable');
		  $.ajax({
		       url: "appexcel_saveAppProcessExcelData.do",
		       dataType: "json",
		       type: "POST",
		       data : {'appProcessData':JSON.stringify(handsontable.getData())},
		       async: false,
		       success: function (data) {
		    	   alert(data.result);
		    	   //重新加载数据
		    	   loadData($container,handsontable);
		 	   }
		  });
		  //移除遮罩层
   	      removeMask();
   	      api.button({id:'Ok',disabled:false});
		  return false;
	  }
  </script>
</head>
<body>
<%--	<input type="button" class="thickbox btn-style02" value="确定"--%>
<%--							onclick="javascript:saveData()" />--%>
	<div id="handsontableDiv"  class="handsontable"></div>
</body>
