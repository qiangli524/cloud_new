<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/dateformat/dateformat.js"></script>
	
    <script type="text/javascript">
    var num = '<%=request.getAttribute("num")%>';
	var userType = '<%=request.getAttribute("userType")%>';
    //创建配置文件
    var api = frameElement.api;
	var w = api.opener;
    $(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createTactics,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		
	});
		
	function createTactics(){
		var executetime = document.getElementById("executetime").value;
	    var selval= $("#isAddTActics").find("select option:selected").val();
        if(1!=selval){
			if (executetime == null || executetime == "") {
				alert("执行时间不能为空");
				return false;
			}
        }
	    if("quickaddtask"==userType){
	        var selval= $("#selIsAddTActics option:selected").val();
	        api.get("quickcreateorder").saveTactics($("#theForm").serialize(),num,selval,executetime);
	    }else{
			api.get("strategy").saveTactics($("#theForm").serialize());
	    }
    }
    
    $(function() {
		$("#timemode").change(function(){
			var tm = $(this).val();
			if (tm == 0) {
				var now = new Date(); 
				var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
				document.getElementById("executetime").value = nowStr;
			}
		});
		
		//如果是从快速添加订单过来的页面就把这个现实出来，否则隐藏掉
		if("quickaddtask"==userType){
	        $("#isAddTActics").show();
	        var selval= $("#isAddTActics").find("select option:selected").val();
	        if(1==selval){
		        $("#isAddTActics").siblings().attr("disabled","disabled");
	        }else{
	             $("#isAddTActics").siblings().removeAttr("disabled");
	        }
	    }else{
			$("#isAddTActics").hide();
	    }
	    
	    $("#selIsAddTActics").change(function(){
	        if($(this).val()==2){
	           $("#isAddTActics").siblings().removeAttr("disabled");
	        }else{
	           $("#isAddTActics").siblings().attr("disabled","disabled");
	        }
	    });
	});
   
   </script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="taskId" id="taskId"></s:hidden>
	<s:hidden name="tacticsId" id="tacticsId" ></s:hidden>
	<s:hidden name="oper" id="oper"></s:hidden>
<!-- 	<s:hidden name="num" id="num"></s:hidden> -->
<!-- 	<s:hidden name="userType" id="userType" ></s:hidden> -->
		<div class="table-ct" >
		<table width="100%" border="0" cellspacing="0"
			class="blue-table sorttable">
			<tr style="display: none" id="isAddTActics">
				<td class="til" align="left">是否添加策略<font color="red">*</font></td>
				<td align="left">
					<s:select list="#{'1':'否','2':'是'}" name="tacticsObj.isAddTactics" id="selIsAddTActics" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">执行方式<font color="red">*</font></td>
				<td align="left">
					<s:select list="#{'1':'并行执行','2':'按优先级逐个执行','3':'按优先级并行执行'}"
					 name="tacticsObj.EXECUTEMETHOD" id="executemethod"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">执行时间模式<font color="red">*</font></td>
				<td align="left">
					<s:select list="#{'1':'按指定时间执行','0':'立即执行'}"
						name="tacticsObj.TIMEMODE" id="timemode"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">执行时间<font color="red">*</font></td>
				<td align="left" >
			   		<input id="executetime" style="txt" type="text" name="tacticsObj.EXECUTETIME" size="20" value="${tacticsObj.EXECUTETIME }" class="Wdate"
			   		onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			    </td>
			</tr>
			<tr>
				<td class="til" align="left">是否备份 <font color="red">*</font>
				</td>
				<td align="left">
					<s:select list="#{'0':'不备份','1':'备份' }" name="tacticsObj.ISCOPY" id="iscopy"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">是否进行环境检测<font color="red">*</font></td>
				<td align="left">
					<s:select list="#{'0':'不检测','1':'检测' }" name="tacticsObj.ISNEEDCHECK" id="isneedcheck"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">是否重启实例<font color="red">*</font></td>
				<td align="left">
					<s:select list="#{'0':'否','1':'是' }" name="tacticsObj.ISRESTART" id="isrestart"></s:select>
				</td>
			</tr>
		</table>
	</div>
    </s:form>
</body>
