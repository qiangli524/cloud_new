<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<style type="text/css">
div.hidden {
	width: 50px;
	height: 30px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-overflow: ellipsis; /* IE/Safari */
	-ms-text-overflow: ellipsis;
	-o-text-overflow: ellipsis; /* Opera */
	-moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
}
</style>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	var exampleid = '<%=request.getAttribute("exampleid")%>';
	var executelevel = '<%=request.getAttribute("executelevel")%>';
	var api = frameElement.api;
	var w = api.opener;
	 	api.button(
	 	{
	     	id:'Ok',
	     	callback:add,
	     	name: '确定',
	     	focus: true
		 },
		 {
	     id:'cancle',
	     name: '取消'
	 	});
	
	function add(){
		var exampleid="";
		var executelevel="";
		var id ="";
		$(":checkbox:checked").each(function(){
		    id = $(this).attr("value");
		    if(id!=00){
		    exampleid+=id+","; 
		    }	
			executelevel+=$(this).parent().parent().find("[name='execute_level'] option:selected").val()+",";		
    	 });
		var userType=$("#userType").val();
		var num=$("#num").val();
		if("quickorder"==userType){
			api.get("quickcreateorder").addExample(exampleid,executelevel,num);
		}else{
		   api.get("createtask").addExample(exampleid,executelevel);
		}
	}

	$(function(){
		var exampleids=exampleid.split(",");
		var executelevels=executelevel.split(",");
		for(var i=0;i<exampleids.length-1;i++){
			$("[value='"+exampleids[i]+"']").attr("checked",true);
			$("[entityid='"+exampleids[i]+"']").find("option:eq("+(executelevels[i]-1)+")").attr("selected",true);
		}
		
		//快速创建工单和任务中，如果一个实例被一个任务选中，其他任务就不能在选这此实例
		var otherExampleid=$("#otherExampleid").val();
		var otherExampleids=otherExampleid.split(",");
		for(var i=0;i<otherExampleids.length-1;i++){
			$("[value='"+otherExampleids[i]+"']").attr("disabled","disabled");
		}
	})
	
	function selectALL(){
   var   select   =   document.getElementById("select_all");   
    var   els =  $("[name='example_id']").not("[disabled]")
   for(i=0;i<els.length;i++){   
   els[i].checked   =   select.checked;   
  }
}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="ugroup_saveGrpmember" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="userType" id="userType"></s:hidden>
		<s:hidden name="num" id="num"></s:hidden>
		<s:hidden name="otherExampleid" id="otherExampleid"></s:hidden>
		<div class="table-ct">
			<table id="theTable" width="100%" border="0" cellspacing="0"
				class="blue-table sorttable">
				<thead>
					<tr>
						<th>
							<input type="checkbox" id="select_all" value="00"
										onclick="selectALL()" />
						</th>
						<th onclick="sort(theTable,1,'string')">
							任务执行优先级
						</th>
						<th onclick="sort(theTable,2,'string')">
							部署实例主机
						</th>
						<th onclick="sort(theTable,3,'string')">
							部署用户
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="deployExampleList" id="example">
						<tr>
							<td>
								<input type="checkbox"
									value="<s:property value='#example.exampleId'/>"
									treeid="<s:property value='#example.exampleId'/>"
									name="example_id" />
							</td>
							<td>
								<select name="execute_level" style="width: 100px"
									entityid="<s:property value='#example.exampleId'/>">
									<option value="1">
										1
									</option>
									<option value="2">
										2
									</option>
									<option value="3">
										3
									</option>
									<option value="4">
										4
									</option>
									<option value="5">
										5
									</option>
									<option value="6">
										6
									</option>
									<option value="7">
										7
									</option>
									<option value="8">
										8
									</option>
									<option value="9">
										9
									</option>
									<option value="10">
										10
									</option>
								</select>
							</td>
							<td>
								<s:property value="#example.hostip" />
							</td>
							<td>
								<s:property value="#example.username" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:form>
</body>

</html:html>
