<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectDisk,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
	
	function selectDisk(){
		var flag = $("#flag").val();
		var lenth = 0;
		var diskId = "";
		var diskNum = "";
		var diskSeq = "";
		var disk_name = "";
		$("input:checkbox[name='checkboxid']:checked").each(function(){
			diskId += $(this).attr("diskId")+",";
			diskNum += $(this).attr("diskNumber")+",";
			disk_name += $(this).attr("disk_name")+",";
			diskSeq += $(this).attr("diskNumber")+","+$(this).attr("diskId")+" ";
			lenth +=1;
		});
    	if(lenth==0){
    		alert('请选择一个磁盘');
    		return false;
    	}
    	if(flag == 'add'){
    		api.get("adddiskgroup").listDiskInfo(diskId,diskNum,diskSeq,disk_name);
    	}else if(flag == 'mod'){
    		api.get("editdisk").listDiskInfo(diskId,diskNum,diskSeq,disk_name);
    	}
	}
    	//查询
    	function searchRequest() {
    		diskInfoObj.submit();
    	}
	
	$(function(){
		$(".query").click(function(){
			if($(".query-form").is(":visible")){
				$(".query-form").slideUp("slow");
			}else{
				$(".query-form").slideDown("slow");
			}
		});
		
		$("#searchForm").click(function(){
			searchRequest();
		});
		
		$("#resetForm").click(function(){
			
		});
		
		$("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		})
		
	});
	
</script>
</head>
<body>
	<s:form action="diskinfo_list.do" method="post" id="diskInfoObj" cssStyle="diskInfoObj">
	<s:hidden name="flag" id="flag"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">磁盘详细</h2>
        	<div class="bord-1 pd-10">
				<table width="100%" border="0">
						<tr>
							<td align="right">磁盘号：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_number" id="disk_number" size="15"></s:textfield>
							</td>
							<td align="right">磁盘编号：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_id" id="disk_id" size="15"></s:textfield>
							</td>
							<td align="right">磁盘名称：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_name" id="disk_name" size="15"></s:textfield>
							</td>
							<td align="right">磁盘类型：</td>
							<td>
								<s:select cssClass="select-1" list="#{'':'--请选择--','1':'热备盘','2':'普通'}" name="diskInfoObj.disk_type" id="disk_type"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="10" class="pd-10">
								<div align="center" >
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" /></span>
								</div>
							</td>
						</tr>
					</table>
					<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th> 
									<th onclick="sort(theTable,1,'string')">磁盘号</th>
									<th onclick="sort(theTable,2,'string')">磁盘编号</th>
									<th onclick="sort(theTable,3,'string')">磁盘名称</th>
									<th onclick="sort(theTable,4,'string')">磁盘类型</th>
									<th onclick="sort(theTable,5,'string')">所属磁盘组</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="diskInfoList" id="theBean">
									 <tr>
										<td>
											<input type="checkbox" name="checkboxid" diskNumber="<s:property value='#theBean.disk_number'/>" 
											diskId="<s:property value='#theBean.disk_id'/>" disk_name="<s:property value='#theBean.disk_name'/>" />
										</td>
										<td>
											<s:property value="#theBean.disk_number"/>
										</td>
										<td>
											<s:property value="#theBean.disk_id"/>
										</td>
										<td>
											<s:property value="#theBean.disk_name"/>
										</td>
										<td>
											<s:property value="#theBean.disk_type"/>
										</td>
										<td>
											<s:property value="#theBean.diskgroup_name"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
						</div>		
			</div>
		</div>
	</s:form>
</body>