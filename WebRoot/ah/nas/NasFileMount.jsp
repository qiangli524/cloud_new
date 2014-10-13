<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>

<script type="text/javascript">
	$(function(){
		$("a[name='filenum']").click(function(){
			var name = $(this).attr("poolname");
			var deviceId = $(this).attr("deviceId");
			$.dialog({
    			id:'fileList',
    			title:'文件系统列表',
    			width: '600px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:ah/nas/addMFrame.jsp'       
    			});
		})
	})
	function searchRequest(){
		$("#storeDevice").attr("action","nasManage_showNasFileSys.do");
		$("#storeDevice").submit();
	}

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="storeDevice">
<div>
<!-- 	<div class="query" id="query"> -->
<!-- 	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div> -->
<!-- 	</div> -->
<div class="box on">
<!-- 	<div class="query-form" id="query-form"> -->
<!-- 	<table width="100%" class="querytable" border="0"> -->
<!-- 		<tr> -->
<!-- 			<td class="til">文件系统名称:</td> -->
<!-- 			<td> -->
<!-- 				<s:textfield name="nasFileSystemObj.FS_NAME" id="name"></s:textfield> -->
<!-- 			</td> -->
<!-- 			<td class="til">文件系统状态:</td> -->
<!-- 			<td> -->
<!-- 				<s:select list="#{'online':'online','other':'other'}" name="nasFileSystemObj.FS_STATUS" id="status" headerKey="-1" headerValue="--请选择--"></s:select> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td colspan="8" class="btns"> -->
<!-- 			<div><input type="button" class="thickbox btn-style02" value="查询" onclick="javascript:searchRequest();" /> -->
<!-- 				 <input type="button" class="btn-style02" value="重置" id="reset" /> -->
<!-- 			</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- 	</div> -->
	<div class="table-head">
		<div class="table-ct" >
			<table>
				<table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
            		<tr>
						<th class="til" align="center">
							挂载实体名称
						</th>
						<th align="center">
							实体类型
						</th>
						<th align="center">
							实体业务地址
						</th>
						<th align="center">
							实体NAS地址
						</th>
						<th align="center">
							实体所属业务
						</th>
					</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="list" id="theBean">
                		<tr>
                			<td><s:property value="#theBean.mountName"/></td>
                			<td><s:property value="#theBean.type"/></td>
                			<td><s:property value="#theBean.ip" default="-"/></td>
                			<td><s:property value="#theBean.nasIp" default="-"/></td>
                			<td><s:property value="#theBean.busi" default="-"/></td>
                		</tr>
                	</s:iterator>
            	</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
</s:form>
</body>
