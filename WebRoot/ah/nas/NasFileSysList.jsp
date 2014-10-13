<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	$(function(){
		$("a[name='filenum']").click(function(){
			var api = frameElement.api; 
			var w = api.opener;
			var name = $(this).attr("fsname");
			var device = $(this).attr("device");
			w.$.dialog({
	    			id:'fileList1',
	    			title:'挂载实体列表',
	    			width: '1000px',
	    			height: '800px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:nasManage_showMount.do?nasFileSystemObj.FS_NAME='+ name +'&nasFileSystemObj.NAS_DEVICE_ID='+device      
	    			});
		})
		$("#resetForm").click(function(){
			$("#name").val("");
			$("#status").val("-1");
		})
		$("#searchForm").click(function(){
			searchRequest();
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
<div >
	<div class="bord-1 pd-10">			
	<div class="clearfix filtrate-area">
		<div class="filtrate-field">
	        <label class="vm">文件系统名称:</label>
			<s:textfield name="nasFileSystemObj.FS_NAME" id="name"></s:textfield>
		</div>
		<div class="filtrate-field">
	  		<label class="vm">文件系统状态:</label>
				<s:select list="#{'online':'online','other':'other'}" name="nasFileSystemObj.FS_STATUS" id="status" headerKey="-1" headerValue="--请选择--"></s:select>
	 	</div>
		<div class="filtrate-field">
	        <span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"  value="查询" /></span>
			<span class="ubtn-2 mgl-20"><input type="button" id="resetForm"  value="重置" /></span>
		</div>
	</div>
	<div class="utt-2 mgt-20">
	</div>
	
		<div class="table-ct" >
				<table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
            		<tr>
						<th class="til" align="center">
							文件系统名称
						</th>
						<th align="center">
							运行状态
						</th>
						<th align="center">
							容量(G)
						</th>
						<th align="center">
							利用率
						</th>
						<th align="center">
							共享方式
						</th>
						<!-- <th align="center">
							接入端IP
						</th> -->
						<!-- <th align="center">
							接入端类型
						</th> -->
						<th align="center">
							共享设备个数
						</th>
					</tr>
            	</thead>
            	<tbody>
            		<s:iterator value="list" id="theBean">
                		<tr>
                			<td><s:property value="#theBean.FS_NAME"/></td>
                			<td><s:property value="#theBean.FS_STATUS"/></td>
                			<td><fmt:formatNumber maxFractionDigits="2" value="${theBean.FS_SIZE/1024}"/></td>
                			<td><fmt:formatNumber maxFractionDigits="2" value="${(theBean.FS_USED_SIZE/theBean.FS_SIZE)*100}"/>%</td>
                			<td><s:property value="#theBean.FS_SHARED_TYPE"/></td>
                			<td>
                			<s:if test="#theBean.SHARENUM ==1">
	                			<a name="filenum" href="javascript:void(0);" device='<s:property value="#theBean.NAS_DEVICE_ID"/>' fsname='<s:property value="#theBean.FS_NAME"/>'>
	                				ALL
	                			</a>
                			</s:if>
                			<s:else>
                			<a name="filenum" href="javascript:void(0);" device='<s:property value="#theBean.NAS_DEVICE_ID"/>' fsname='<s:property value="#theBean.FS_NAME"/>'>
                				<s:property value="#theBean.SHARENUM"/>
                			</a>
                			</s:else>
                			</td>
                		</tr>
                	</s:iterator>
            	</tbody>
			</table>
			 <div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>	
		</div>
	</div><!--blue-wrap end -->
</s:form>
</body>
