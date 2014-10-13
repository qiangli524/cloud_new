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
			$("#name").val(null);
			$("#healthy").val("-1");
			$("#compare").val(0);
			$("#capacity").val(null);
		});
		
		$("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		})
		
		$("#addForm").click(function(){
			var diskGroupId = $("#diskGroupId").val();
			$.dialog({
				id:'addLun',
				title:'添加LUN块',
				width:'570px',
				height:'470px',
				lock:true,	
				content:'url:lun_add.do?lunObj.diskGroupId='+diskGroupId
			});
		});
		
		$("#deleteForm").click(function(){
			if ($(":checkbox:checked").length == 0) {
				alert("请至少选择一项进行删除");
				return false;
			}
			var lunId = "";
			var lunName = "";
			$("input:checkbox[name='checkboxid']:checked").each(function(){
				lunId += $(this).attr("lunId")+",";
				lunName += $(this).attr("lunName")+",";
			});
			if (confirm("该操作将删除该磁盘组，你确定进行该操作吗？")) {
				$.ajax({
					type:'post',
					url:'lun_delete.do?lunObj.uuid='+lunId+'&lunObj.name='+lunName,
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
			var id = $td.attr("lunId");
			$.dialog({
				id:'editLun',
				title:'修改LUN信息',
				width:'800px',
				height:'500px',
				lock:true,	
				content:'url:lun_mod.do?lunObj.uuid='+id
			});
		});
	});
	
	function saveLun(theForm){
		var url = 'lun_save.do';
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
	<s:form action="lun_list.do" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="lunObj.diskGroupId" id="diskGroupId"></s:hidden>
		<div class="scrollbody">
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">Lun管理</h2>
	        	<div class="bord-1 pd-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">名称：</td>
							<td>
								<s:textfield name="lunObj.name" id="name"></s:textfield>
							</td>
							<td class="til">健康状况：</td>
							<td>
								<s:select cssClass="select-1" list="#{'-1':'--请选择--','0':'不正常','1':'正常'}" name="lunObj.healthy" id="healthy"></s:select>
							</td>
							<td class="til">容量：</td>
							<td>
								<s:select cssClass="select-1" list="#{'0':'--请选择--','1':'>=','-1':'<='}" name="lunObj.compare" id="compare"></s:select>
								<s:textfield name="lunObj.capacity" id="capacity"></s:textfield>GB
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
									<th onclick="sort(theTable,1,'string')">名称</th>
									<th onclick="sort(theTable,2,'string')">是否映射</th>
									<th onclick="sort(theTable,3,'string')">容量</th>
									<th onclick="sort(theTable,4,'string')">健康状态</th>
									<th onclick="sort(theTable,5,'string')">所属磁盘组</th>
									<th onclick="sort(theTable,6,'string')">所属存储设备</th>
									<th>编辑</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									 <tr>
										<td>
											<input type="checkbox" name="checkboxid" lunId='<s:property value="#theBean.uuid"/>' 
												lunName='<s:property value="#theBean.name"/>' />
										</td>
										<td>
											<s:property value="#theBean.name"/>
										</td>
										<td>
											<s:if test="#theBean.is_mapping == 1">
												是
											</s:if>
											<s:else>
												否
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.capacity"/>GB
										</td>
										<td>
											<s:if test="#theBean.healthy==''">
												正常
											</s:if>
											<s:else>
												正常
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.diskGroupName"/>
										</td>
										<td>
											<s:property value="#theBean.deviceName"/>
										</td>
										<td lunId="<s:property value='#theBean.uuid'/>">
											<a href="javascript:;" name="edit" >
												修改
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