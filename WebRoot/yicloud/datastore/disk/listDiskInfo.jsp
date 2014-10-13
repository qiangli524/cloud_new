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
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<script type="text/javascript">
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
			$("#diskInfoObj").submit();
		});
		
		$("#resetForm").click(function(){
			$("#disk_number").val("");
			$("#disk_id").val("");
			$("#disk_name").val("");
			$("#disk_type").val("");
		});
		
		$("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		})
		
		$("#addForm").click(function(){
			$.dialog({
				id:'adddisk',
				title:'添加磁盘',
				width:'600px',
				height:'400px',
				lock: true,
				content:'url:diskinfo_add.do?flag=add'
			});
		});
		
		$("#deleteForm").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("请至少选择一项进行删除");
				return false;
			}
			var diskId = "";
			$("input:checkbox[name='checkboxid']:checked").each(function(){
				diskId += $(this).attr("diskId")+",";
			});
			if (confirm("该操作将删除该磁盘，你确定进行该操作吗？")) {
				$.ajax({
					type:'post',
					url:'diskinfo_delete.do?diskInfoObj.disk_id='+diskId,
					success:function(msg){
						$("#diskInfoObj").submit();
					}
				});
			}
		});
		
		$("[name='createDisk']").unbind().live("click",function(){
			var $td = $(this).parent();
			var diskId = $td.attr("diskId");
			var diskNumber = $td.attr("diskNumber");
			var disk_type = $td.attr("diskType");
			if (confirm("确定创建热备盘?")) {
				$.ajax({
		            type: "POST",
		            url: 'diskinfo_createDisk.do?diskInfoObj.disk_id='+ diskId +'&diskInfoObj.disk_number='+ diskNumber,
		            async:false,
		            cache:false,
		            success : function(data){
						if (data == -1) {
							alert("保存失败");
						} else {
							searchRequest();
						}
		            }
		    	});
			}
		});
		
		
		$("[name='deleteDisk']").unbind().live("click",function(){
			var $td = $(this).parent();
			var diskId = $td.attr("diskId");
			var diskNumber = $td.attr("diskNumber");
			var disk_type = $td.attr("diskType");
			if (confirm("确定删除热备盘?")) {
				$.ajax({
		            type: "POST",
		            url: 'diskinfo_deleteDisk.do?diskInfoObj.disk_id='+diskId+'&diskInfoObj.disk_number='+diskNumber,
		            async:false,
		            cache:false,
		            success : function(data){
						if (data == -1) {
							alert("保存失败");
						} else {
							searchRequest();
						}
		            }
		    	});
			}
		});
		
	});
	
	function saveDisk(diskInfoObj){
		var url = 'diskinfo_save.do';
		$.ajax({
            type: "POST",
            url: url,
            data: diskInfoObj,
            async:false,
            cache:false,
            success : function(data){
				if (data == -1) {
					alert("保存失败");
				} else {
					searchRequest();
				}
            }
    	});
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="diskinfo_list.do" method="post" id="diskInfoObj" cssStyle="diskInfoObj">
		<div class="scrollbody">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">磁盘管理</h2>
	        	<div class="bord-1 pd-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">磁盘号：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_number" id="disk_number" size="15"></s:textfield>
							</td>
							<td class="til">磁盘编号：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_id" id="disk_id" size="15"></s:textfield>
							</td>
							<td class="til">磁盘名称：</td>
							<td>
								<s:textfield name="diskInfoObj.disk_name" id="disk_name" size="15"></s:textfield>
							</td>
							<td class="til">磁盘类型：</td>
							<td>
								<s:select cssClass="select-1" list="#{'':'--请选择--','1':'热备盘','2':'普通'}" name="diskInfoObj.disk_type" id="disk_type"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" /></span>
								</div>
							</td>
						</tr>
					</table>
					<div class="utt-2 mgt-10">
						<a class="icon-add" href="javascript:void(0)" id="addForm" >新增</a>
						<a class="icon-del" href="javascript:void(0)" id="deleteForm" >删除</a>
					</div>	
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th> 
									<th onclick="sort(theTable,1,'string')">磁盘号</th>
									<th onclick="sort(theTable,2,'string')">磁盘编号</th>
									<th onclick="sort(theTable,3,'string')">磁盘名称</th>
									<th onclick="sort(theTable,4,'string')">磁盘类型</th>
									<th onclick="sort(theTable,5,'string')">磁盘大小</th>
									<th onclick="sort(theTable,6,'string')">所属磁盘组</th>
									<th>编辑</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									 <tr>
										<td>
											<input type="checkbox" name="checkboxid" diskId="<s:property value='#theBean.disk_id'/>" 
											diskNumber="<s:property value='#theBean.disk_number'/>" />
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
											<s:if test='#theBean.disk_type == 1'>
												热磁盘
											</s:if><s:else>
												普通磁盘
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.disk_size"/>G
											<%-- <s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/>--%>
										</td>
										<td>
											<s:property value="#theBean.diskgroup_name" default="—"/>
										</td>
										<td diskNumber="<s:property value='#theBean.disk_number'/>" diskType="<s:property value='#theBean.disk_type'/>"
										diskId="<s:property value='#theBean.disk_id'/>">
											<s:if test="#theBean.disk_type == 2">
												<s:if test="#theBean.diskgroup_name == '' || #theBean.diskgroup_name == null ">
													<a href="javascript:;" name="createDisk" id="createDisk">
														创建热备盘
													</a>
												</s:if>
												<s:else>
													—
												</s:else>
											</s:if>
											<s:if test="#theBean.disk_type == 1">
												<a href="javascript:;" name="deleteDisk" id="deleteDisk">
													删除热备盘
												</a>
											</s:if>
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
		</div>
	</s:form>
</body>