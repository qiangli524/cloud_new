<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import="com.sitech.basd.sxcloud.rsmu.web.util.page.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户 我的工单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css" />
	<link href="<%=request.getContextPath() %>/publicCloud/pub-ui/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/js/My97DatePicker/skin/WdatePicker.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workflow.constant.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/fenye.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	td,th{
		font-size:13px;
	}
	
	</style>
  </head>
  <script type="text/javascript">
	<%
		String statusType = (String)request.getParameter("statusType");
	%>
	var statusType = '<%=statusType%>' 
 	 function searchRequest(page){
		$('#contentTr').html("");
		var stepIds = customStatus.unfinished;
		var stepId = $('#search_stepId').val();
		if(stepId.length!=0){
			stepIds = stepId;
		}else{
			if(statusType==1){
				stepIds = customStatus.completed;
			}
		}		
		var search = {
				"obj.queryType":qtype.my,
				"obj.resourceName":"workorder",
				"stepIds":stepIds,
				"obj.orderNo":$('#search_orderNo').val(),
				"obj.startDate":$('#search_starttime').val(),
				"obj.endDate":$('#search_endtime').val(),
				"obj.formData":$('#search_formData').val(),
				"PAGINATION_CURRENT_PAGE_NO":page, //分页用
				"PAGINATOR_FLAG":"true"			   //分页用
		}
 		$.ajax({
    		type : 'post',
    		async : false,
    		url : '<%=path%>/bpm/workorder_getWorkFlowList.action',
   			data:search,
   			dataType: 'json',
   			success : function(data){
   				if(data.length!=0){   						
	   				$(data.list).each(function(i,e){
	   					var html = '<tr>'+
	   								'<td class="tacenter">'+e.entryId+'</td>'+
	   								'<td class="tacenter"><a href="#"></a>'+e.dataMap.content+'</td>'+
	   								'<td class="tacenter">'+e.dataMap.type+'</td>'+
	   								'<td class="tacenter">'+orderStatus[e.resourceName+e.stepId]+'</td>'+
	   								'<td class="tacenter">'+e.submitTime+'</td>'+
	   								'<td style="color:blue" class="tacenter"><a href="<%=path%>'+customerUrl[e.resourceName+e.stepId]+'?obj.entryId='+e.entryId+'&type=52">查看</a></td>'+
	   							   '</tr>';
	   					$('#contentTr').append(html);
	   				});	
   				}
   				//添加分页
   				ajaxFenye(data.page);
   			},
   			error : function(data,textStatus){
   				console.log('error:' + data);
   			}
   		});			
	 } 
	
	//跳到指定页面
	function gotoPage(page){
		if( null != page && ""!= page) {
			searchRequest(page);
		}
	}
 	function addWorkOrder(){
 	    window.location.href="<%=path%>/bpm/workorder_edit.action?obj.resourceName=${obj.resourceName}&position=remy&obj.account=${obj.account}";
 	}
 	function schedule(){
 		var inputObj = $('input:radio[name="entryId"]:checked');		
 		if(inputObj.length!=0){
 			$(inputObj).each(function(i,e){
 			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=path%>/bpm/workorder_schedule.action',
       			data:{"obj.entryId":$(e).val(),"obj.resourceName":inputObj.attr("resourceName")},
       			dataType: 'json',
       			success : function(data){
       				window.open(data.url);			
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});			
       		});
 		}else{
 			alert("请选择一条");
 		}
 	} 
 	function history(){
 		var inputObj = $('input:radio[name="entryId"]:checked');
 		if(inputObj.length!=0){
 			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=path%>/bpm/workorder_history.action',
       			data:{"obj.entryId":inputObj.val()},
       			dataType: 'json',
       			success : function(data){
       				var html ='';
       				$(data).each(function(i,e){
       					if(e.finishDate.length!=0){
       						if(i==0){
       							html+=e.finishDate+' '+e.executor+'新建了故障单'+'\n';
       						}else{
       							if(e.proxyUser.length==0){
       								html+=e.finishDate+' '+e.executor+'审核了'+e.stepName+'\n';
       							}else{
       								html+=e.finishDate+' '+e.executor+'转发给了'+e.proxyUser+'\n';       							
       							}
       						}
       					}       					
       				});
       				alert(html);
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});			
 		}else{
 			alert("请选择一条");
 		}
 	}

 	$(document).ready(function(){
		$('#statusType'+statusType).addClass("on");
		searchRequest(1);
 	})
 	
</script>
  <body>
	<!--container star-->
	<div class="container">
		<!--col-c7 star-->
		<div class="col-c7">
			<!--left star-->
			<jsp:include page="/publicCloud/pub-page/order.jsp" />
			<!--left end-->
			<!--main-c1 star-->
			<div class="main-c1 fr">
			<!--标题 star-->
				<h2 class="title-common6">
					<a href="#" class="home"></a><img
						src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif"
						width="65" height="65" />工单列表
				</h2>
			<!--标题 end-->
			<div class="box on" style="display: block;">
                    	<p class="title-common8">问题列表</p>
                        
                        <div class="border-2 pd-10 clearfix">
                         <form id="search" method="get" action="">
                        <input type="hidden" name="statusType" value="0">
                        <input type="hidden" name="obj.entryId" id="entryId" value="${obj.entryId }">
						<input type="hidden" name="obj.resourceName" id="resourceName" value="${obj.resourceName }">
                        
                         <p class="fl mgr-5">工单编号：<input type="text" id="search_orderNo" name="obj.orderNo" type="text" size="8" value="" class="input-1c input-1c-3"></p>
                         <p class="fl">关键字：<input type="text" id="search_formData" name="obj.formData" type="text" size="15" value="" class="input-1c input-1c-3"></p>
                         <p class="fl mgr-5">&nbsp;时间范围：
                        <input type="text" id="search_starttime" name="obj.startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="height: 25px; width: 160;" class="Wdate input-1c" value="">
                        <input type="text" id="search_endtime"  name="obj.endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="height: 25px;width: 160;" class="Wdate input-1c" value="">
</p>
                        <p class="fl mgr-5">状态：
                         		<select id="search_stepId"  style="height: 25px;" name="obj.stepId">
									<option value="">所有</option>
									<option value="2">待分配</option>
									<option value="3">处理中</option>
									<option value="7">待反馈</option>
									<option value="4">已处理</option>
									<option value="99">已关闭</option>
								</select>  
						</p>                         
                        <a id="search-btn" style="cursor:pointer" onclick="searchRequest();" class="btn-w4"><span>查看</span></a>
                    </form>
             
                        </div>
                        
                        <div class="show-tab show-tab2 js_click_tab">
                            <div class="tab-caption js_click_caption">
                                <ul class="list-w4 clearfix">
                                    <li class="" id="statusType0">
		                                <a href="workorder_customer_my.do?type=52&statusType=0" style="text-decoration:none;">未完成</a>
		                            </li >
		                            <li class="" id="statusType1">
		                                <a href="workorder_customer_my.do?type=52&statusType=1" style="text-decoration:none;">已完成</a>
		                            </li>
                                </ul>       
                            </div>
                            <!--tab-content star-->
                            <div class="tab-content border-2 pdtb-10">
                                <!--box star-->
                                <div class="box" style="display: block;">
                                    	<table id="contentTable" border="0" width="100%" class="table-f4 js_table_f4">
			                            <tbody>
			                                <tr>
			                                    <th width="10%" class="tacenter">工单编号</th>
			                                    <th width="25%" class="tacenter">问题内容</th>
			                                    <th width="15%" class="tacenter">问题类型</th>
			                                    <th width="15%" class="tacenter">状态</th>
			                                    <th width="15%" class="tacenter">提交时间</th>
			                                    <th width="15%" class="tacenter">操作</th>
			                                </tr>
			                            </tbody>
			                            <tbody id="contentTr">
			                           
			                            </tbody>
			                        </table>       
                                </div>
                                <!--box end-->
                               <!-- 分页start -->
							<div class="page mgt34-b25" id="ajaxpage">

							</div>
							<!-- 分页end -->
                            <!--tab-content star-->
                        </div>
                        <p class="p-con5 mgt-10"> 为了能够更加准确的了解您遇到的问题，并能更加完整的收集到和问题相关的必要排查信息，建议您先通过工单提交问题，我们会及时响应和处理。
                    如果多次提交工单仍不能妥善解决您的问题，欢迎您拨打中国电信云商城服务热线 <b style="color:#000;">110</b> 联系我们的技术支持,感谢您的配合！</p>
                    </div>
			     						
						
                    </div>
                </div>
			<!--main-c1 end-->
			<div class="clear"></div>
		</div>
		<!--col-c7 end-->
	</div>
	<!--container end-->
	<!--版权 star-->
	<div class="copy">
		<div class="copy-con ac">
			<p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号
				增值电信业务经营许可证A2.B1.B2-20090001</p>
		</div>
	</div>
	<!--版权 end-->
  </body>
</html>
