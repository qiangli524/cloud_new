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
	$(function(){
		$(".query").click(function(){
			if($(".query-form").is(":visible")){
				$(".query-form").slideUp("slow");
			}else{
				$(".query-form").slideDown("slow");
			}
		});
		
		$("#searchForm").click(function(){
			$("#theForm").submit();
		});
		
		$("#resetForm").click(function(){
			$("#disk_name").val(null);
			$("#raidLevel").val("-1");
			$("#compare").val(0);
			$("#capacity").val(null);
			$("#deviceId").val("-1");
		});
		
		/* $("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		}); */
		
		$("#addForm").click(function(){
			$.dialog({
				id:'adddiskgroup',
				title:'添加磁盘组',
				width:'600px',
				height:'250px',
				lock:true,	
				content:'url:diskgroup_add.do?flag=add'
			});
		});
		
		$("#deleteForm").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("请至少选择一项进行删除");
				return false;
			}
			var diskId = "";
			var lunNum = 0;
			var groupName = "";
			$("input:checkbox[name='checkboxid']:checked").each(function(){
				diskId += $(this).attr("diskId")+",";
				groupName += $(this).attr("groupName")+",";
				lunNum += parseInt($(this).attr("lunNum"));
			});
			if (lunNum > 0) {
				alert("选中的磁盘组上有Lun块，无法删除");
				return false;
			}
			if (confirm("该操作将删除该磁盘组，你确定进行该操作吗？")) {
				$.ajax({
					type:'post',
					url:'diskgroup_delete.do?diskGroupObj.uuid='+diskId+'&diskGroupObj.name='+ groupName,
					success:function(msg){
						if (msg == -1) {
							alert("删除失败");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});
		
		$("[name='edit']").unbind().live("click",function(){
			var $td = $(this).parent();
			var id = $td.attr("diskId");
			$.dialog({
				id:'editdisk',
				title:'修改磁盘组',
				width:'400px',
				height:'300px',
				lock:true,
				content:'url:diskgroup_mod.do?diskGroupObj.uuid='+id+'&flag=mod'
			});
		});
		
		$("[name='lunNum']").unbind().live("click",function(){
			var diskId = $(this).parent().attr("diskId");
			$.dialog({
				id:'viewLun',
				title:'LUN列表',
				width:'850px',
				height:'600px',
				zIndex:1976,
				lock:true,	
				content:'url:lun_list.do?lunObj.diskGroupId='+diskId
			});
		});
		
		$("[name='diskNum']").unbind().live("click",function(){
			var diskId = $(this).parent().attr("diskId");
			$.dialog({
				id:'diskList',
				title:'磁盘列表',
				width:'850px',
				height:'600px',
				zIndex:1976,
				lock:true,	
				content:'url:diskinfo_list.do?diskInfoObj.diskgroup_id='+diskId
			});
		});
		
	});
	
	function saveDiskGroup(theForm){
		var url = 'diskgroup_save.do';
		$.ajax({
            type: "POST",
            url: url,
            data: theForm,
            async:false,
            cache:false,
            success : function(data){
				if (data == -1) {
					alert("保存失败");
				} else {
	                $("#theForm").submit();
				}
            }
    	});
	}
</script>
</head>
<body>
	<s:form action="diskgroup_list.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">磁盘组管理</h2>
	        	<div class="bord-1 pd-10">
					<table width="100%" border="0">
						<tr>
							<td class="til">磁盘组名称：</td>
							<td>
								<s:textfield name="diskGroupObj.name" id="disk_name" size="15"></s:textfield>
							</td>
							<td class="til">RAID级别：</td>
							<td>
								<s:select cssClass="select-1" list="#{'-1':'--请选择--','0':'RAID 0','1':'RAID 1','2':'RAID 2','3':'RAID 3','4':'RAID 4',
									'5':'RAID 5','10':'RAID 10'}" name="diskGroupObj.raidLevel" id="raidLevel"></s:select>
							</td>
							<td class="til">所属存储设备：</td>
							<td>
								<s:select cssClass="select-1" list="deviceList" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--" id="deviceId" name="diskGroupObj.storeDeviceId"></s:select>
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
					<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)" id="addForm" >新增</a>
						<a class="icon-del" href="javascript:void(0)" id="deleteForm" >删除</a>
					</div>
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th>选择</th> 
									<th onclick="sort(theTable,1,'string')">磁盘组名称</th>
									<th onclick="sort(theTable,2,'string')">RAID级别</th>
									<th onclick="sort(theTable,4,'string')">所属存储设备</th>
									<th onclick="sort(theTable,5,'string')">磁盘数目</th>
									<th onclick="sort(theTable,6,'string')">LUN块数目</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									 <tr>
										<td>
											<input type="checkbox" name="checkboxid" diskId='<s:property value="#theBean.uuid"/>'
											 lunNum='<s:property value="#theBean.lun_num"/>'  groupName='<s:property value="#theBean.name"/>' />
										</td>
										<td>
											<s:property value="#theBean.name"/>
										</td>
										<td>
											RAID <s:property value="#theBean.raidLevel"/>
										</td>
										<td>
											<s:property value="#theBean.deviceName"/>
										</td>
										<td diskId="<s:property value='#theBean.uuid'/>">
											<a href="javascript:;" name="diskNum">
												<s:property value="#theBean.disk_num"/>
											</a>
										</td>
										<td diskId="<s:property value='#theBean.uuid'/>">
											<a href="javascript:;" name="lunNum">
												<s:property value="#theBean.lun_num"/>
											</a>
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