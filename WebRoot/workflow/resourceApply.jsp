<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
 contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<title></title>
<link rel="stylesheet"
 href="<%=request.getContextPath()%>/workflow/pub-ui/css/default.css" />
<link rel="stylesheet"
 href="<%=request.getContextPath()%>/workflow/pub-ui/js/My97DatePicker/skin/WdatePicker.css" />
<script type="text/javascript"
 src="<%=request.getContextPath()%>/workflow/pub-ui/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
 src="<%=request.getContextPath()%>/workflow/pub-ui/js/ui.js"></script>
<script type="text/javascript"
 src="<%=request.getContextPath()%>/workflow/pub-ui/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
 

<script type="text/javascript">
	var modelId = "-1";
	var fileIds = "";
	var busiId = "";
$( function(){
	
	//隐藏内存选项
	$("#cpu2").hide();
	$("#cpu4").hide();
	$("#cpu8").hide();
	
	//操作系统选中
	$("#system").change(function(){
		//获取预装软件数据
		$.ajax({
			url : 'workflow_getTemListBySystem.do?systemName='+$(this).val(),
			type: "POST",
			dataType:"json",
			success: function(data){
				var html = "<option value=''>请选择预装软件</option>";
				$("#soft").html("");
				for (var i=0; i<data.length; i++){
					var cid = data[i].connectId+'_'+data[i].templateCode;
					html += '<option value="'+cid+'">'+data[i].soft_name+'</option>';
				}
				$("#soft").html(html);
			}
		});
	});
	
	//预装软件选中
	$("#soft").change(function(){
		var value=$(this).text();
		modelId = $(this).val();
	});
	
	$("#busi").change(function(){
		busiId = $(this).val();
	});
	
	//提交
	$("#add").click(function(){
		//存储取值
		var data = $(".uc-input-data");
		var storeStr = "";
		var total = 0;
		for (var i=0; i<data.length; i++){
			storeStr = storeStr + $(data[i]).val()+",";
			total = total + parseInt($(data[i]).val());
		}
		
		if (storeStr.length>0){
			storeStr = storeStr.substring(0, storeStr.length-1);
		}
		
		//cpu取值
		var cpu = $(".btn-n1.btn-n1on.cpu").attr("name");
		//memory取值
		var memory = "";
		if (cpu=="1"){
			memory = $("#cpu1 .btn-n1.btn-n1on.memory").attr("name");
		}else if (cpu=="2"){
			memory = $("#cpu2 .btn-n1.btn-n1on.memory").attr("name");
		}else if (cpu=="4"){
			memory = $("#cpu4 .btn-n1.btn-n1on.memory").attr("name");
		}else if (cpu=="8"){
			memory = $("#cpu8 .btn-n1.btn-n1on.memory").attr("name");
		}
		
		var network= $(".btn-n1.btn-n1on.network").attr("name");
		var application= $(".btn-n1.btn-n1on.application").attr("name");
		var service= $(".btn-n1.btn-n1on.service").attr("name");
		var remark = $("#remark").val();
		var projectName = $("#projectName").val();
		var validateTime = $("#validateTime").val();
		var workOrderTitle = $("#workOrderTitle").val();
		
		$("#hidcpu").val(cpu);
		$("#hidmemory").val(memory);
		$("#hidstore").val(total);
		$("#hidnetType").val(network);
		$("#hidappType").val(application);
		$("#hidserviceType").val(service);
		$("#hidmodelId").val(modelId);
		$("#hidremark").val(remark);
		$("#hidprojectName").val(projectName);
		$("#hidvalidateTime").val(validateTime);
		$("#hidmodelId").val(modelId);
		$("#hidCount").val($("#countIpt").val());
		$("#hidBusiId").val(busiId);
		$("#hidStoreStr").val(storeStr);
		$("#hidworkOrderTitle").val(workOrderTitle);

		$.ajax({
            cache: true,
            type: "POST",
            url:"workflow_saveWorkOrder.do",
            data:$('#theForm').serialize(),
            async: false,
            success: function(data) {
                alert("提交工单系统成功！");
            }
        });
	});
	
	//CPU选择切换
	$(".cpu").click(function(){
		var value = $(this).attr("name");
		if (value=="1")
		{
			$("#cpu1").show();
			$("#cpu2").hide();
			$("#cpu4").hide();
			$("#cpu8").hide();
		}else if (value=="2"){
			$("#cpu1").hide();
			$("#cpu2").show();
			$("#cpu4").hide();
			$("#cpu8").hide();
		}else if (value=="4"){
			$("#cpu1").hide();
			$("#cpu2").hide();
			$("#cpu4").show();
			$("#cpu8").hide();
		}else if (value=="8"){
			$("#cpu1").hide();
			$("#cpu2").hide();
			$("#cpu4").hide();
			$("#cpu8").show();
		}
	});
	
});

</script>

</head>
<body>
<div class="container" style="height:800px; overflow-y: auto;" >
    <ul class="list-w8 list-w8-2 js_more_c">
     <li>
     	<span class="s-t1">工单标题：</span>
     	<input type="text" id="workOrderTitle"  style="width: 350px"/>
     </li>
     <li>
     	<span class="s-t1">CPU：</span>
	     <a href="#" class="btn-n1 btn-n1on cpu" name="1" ><em>1核</em></a>
	     <a href="#" class="btn-n1 cpu" name="2"><em>2核</em></a>
	     <a href="#" class="btn-n1 cpu" name="4"><em>4核</em></a>
	     <a href="#" class="btn-n1 cpu"  name="8"><em>8核</em></a>
     </li>
     <li>
     	<span class="s-t1">内存：</span>
        <div id="cpu1">
            <a href="#" class="btn-n1 btn-n1on memory" name="1"><em>1G</em></a>
            <a href="#" class="btn-n1 memory" name="2"><em>2G</em></a>
            <a href="#" class="btn-n1 memory" name="4"><em>4G</em></a>
        </div>
        <div id="cpu2">
        	<a href="#" class="btn-n1 btn-n1on memory" name="2"><em>2G</em></a>
            <a href="#" class="btn-n1 memory" name="4"><em>4G</em></a>
            <a href="#" class="btn-n1 memory" name="8"><em>8G</em></a>
        </div>
        <div id="cpu4">
        	<a href="#" class="btn-n1 btn-n1on memory" name="4"><em>4G</em></a>
            <a href="#" class="btn-n1 memory" name="8"><em>8G</em></a>
            <a href="#" class="btn-n1 memory" name="16"><em>16G</em></a>
        </div>
        <div id="cpu8">
        	<a href="#" class="btn-n1 btn-n1on memory" name="8"><em>8G</em></a>
            <a href="#" class="btn-n1 memory" name="16"><em>16G</em></a>
            <a href="#" class="btn-n1 memory" name="32"><em>32G</em></a>
        </div>
     </li>
     <li>
     	<span class="s-t1">网络类型：</span>
        <a href="#" class="btn-n1 btn-n1on network" name="out"><em>外网</em></a>
        <a href="#" class="btn-n1 network" name="in"><em>内网</em></a>
     </li>
     <li>
     	<span class="s-t1">应用类型：</span>
     	<a href="#" class="btn-n1 btn-n1on application" name="produce"><em>生产</em></a>
     	<a href="#" class="btn-n1 application" name="test"><em>测试</em></a>
     </li>
     <li><span class="s-t1">服务类型：</span> <a href="#"
      class="btn-n1 btn-n1on service" name="web"><em>WEB服务器</em>
     </a> <a href="#" class="btn-n1 service" name="database"><em>数据库服务器</em>
     </a> <a href="#" class="btn-n1 service" name="interface"><em>接口服务器</em>
     </a> <a href="#" class="btn-n1 service"  name="produce"><em>应用服务器</em>
     </a> <a href="#" class="btn-n1 service" name="message"><em>消息中间件</em>
     </a></li>
     <li class="clearfix"><span class="s-t1">操作系统：</span>
       <s:select cssClass="select-1 vm" list="systemList"  id="system" ></s:select>
	 </li>
     <li class="clearfix"><span class="s-t1">预装软件：</span>
     	<select class="select-1 vm" id="soft" >
     		<option value="">请选择预装软件</option>
     	</select>
     </li>
     <li><span class="s-t1">数据盘：</span>
      <div class="column-data fl">
       <div class="row-li row-li-last">
        <a class="font-blue a-add">增加一块</a> <span class="disk-info"><span>您还可选配<span
          class="disk-number">4</span>块，</span><span>总容量剩余<span
          class="disk-over-capacity">2048</span>GB可选，每块最小容量为5GB</span> </span>
       </div>
      </div>
     </li>
     
     <li><span class="s-t1">业务系统：</span>
     	<s:select cssClass="select-1 vm"  listKey="id" listValue="name"  list="busiList"  id="busi" ></s:select>
     </li>
     
     <li><span class="s-t1">创建个数：</span>
     	<input type="text" id="countIpt"/> 
     </li>
     <li>
      <p class="line-th30 pdl-70 jsMore">
       <a href="#none" class="font-blue">填写更多</a>
      </p>
     </li>
     <li class="clearfix">
      <div class="opBox">
       <ul class="list-w8 list-w8-2 js_more_c">
        <li class="clearfix" style="padding-top:0">
        	<span class="s-t1">到期时间：</span>
        	<input type="text" id="validateTime" onclick="WdatePicker()" class="Wdate fl input-1c">
        </li>
        <li class="clearfix">
        	<span class="s-t1">备注：</span>
        	<textarea id="remark"  class="textarea"></textarea>
        </li>
        <li>
        <input type="file" name="file_upload" id="file_upload" />
        </li>
       </ul>
      </div>
    </ul>
    <div class="mgtb-25 pdl-70">
     <a class="btn-w8" id="add" href="#"><span>确定</span> </a>
    </div>
 </div>
 <s:form action="workflow_saveWorkOrder.do" method="post"  id="theForm">
	<s:hidden name="formObj.cpu" id="hidcpu"></s:hidden>
	<s:hidden name="formObj.memory" id="hidmemory"></s:hidden>
	<s:hidden name="formObj.store" id="hidstore"></s:hidden>
	<s:hidden name="formObj.netType" id="hidnetType"></s:hidden>
	<s:hidden name="formObj.appType" id="hidappType"></s:hidden>
	<s:hidden name="formObj.serviceType" id="hidserviceType"></s:hidden>
	<s:hidden name="formObj.modelId" id="hidmodelId"></s:hidden>
	<s:hidden name="formObj.projectName" id="hidprojectName"></s:hidden>
	<s:hidden name="formObj.validateTime" id="hidvalidateTime"></s:hidden>
	<s:hidden name="formObj.remark" id="hidremark"></s:hidden>
	<s:hidden name="formObj.fileIds" id="hidfileIds"></s:hidden>
	<s:hidden name="formObj.count" id="hidCount"></s:hidden>
	<s:hidden name="formObj.busiId" id="hidBusiId"></s:hidden>
	<s:hidden name="formObj.storeStr" id="hidStoreStr"></s:hidden>
	<s:hidden name="formObj.workOrderTitle" id="hidworkOrderTitle"></s:hidden>
</s:form>
</body>
</html>