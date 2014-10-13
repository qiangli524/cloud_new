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
    			width: '1000px',
    			height: '800px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:nasManage_showNasFileSys.do?poolname='+name+'&deviceId='+deviceId       
    			});
		})
	})

</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" >
<div class="scrollbody">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr>
					<td class="til" width="23%" align="right">
						存储设备名称
					</td>
					<td align="left">
						<!-- 待修改 -->
						NAS
					</td>
				</tr>
				<tr>
					<td class="til" width="20%" align="right">
						设备总容量(T)
					</td>
					<td align="left">
					   <fmt:formatNumber maxFractionDigits="0" value="${nasKpiObj.storesize/1024/1024}"/>
					  
					</td>
				</tr> 
				<tr>
					<td class="til" width="20%" align="right">
						已分配文件系统个数
					</td>
					<td align="left">
						<s:property value="nasKpiObj.filenum"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="20%" align="right">
						已分配文件系统容量(T)
					</td>
					<td align="left">
					   <fmt:formatNumber maxFractionDigits="2" value="${nasKpiObj.allfilesize/1024/1024}"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="20%" align="right">
						已分配文件系统使用量(T)
					</td>
					<td align="left">
					     <fmt:formatNumber maxFractionDigits="2" value="${nasKpiObj.usedfilesize/1024/1024}"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="table-ct" >
			<table>
				 <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
            		<tr>
						<th class="til" align="center">
							存储池名称
						</th>
						<th align="center">
							总量(G)
						</th>
						<th align="center">
							空闲量(G)
						</th>
						<th align="center">
							文件系统个数
						</th>
					</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="nasStorePoolList" id="theBean">
                		<tr>
                			<td><s:property value="#theBean.naspoolname"/></td>
                			<td><fmt:formatNumber maxFractionDigits="2" value="${theBean.totalspace/1024}"/></td>
                			<td><fmt:formatNumber maxFractionDigits="2" value="${theBean.freespace/1024}"/></td>
                			<td><a href="javascript:void();" name="filenum" poolname="<s:property value='#theBean.naspoolname'/>" deviceId="<s:property value='#theBean.nas_device_id'/>"><s:property value="#theBean.fileNum"/></a></td>
                		</tr>
                	</s:iterator>
            	</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
</s:form>
</body>
