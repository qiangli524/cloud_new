<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<html>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
    <title></title>
    <script type="text/javascript">
        
        function modRequest(){
            var id = $(":checkbox[checked='checked']").val();
            if(id == null || id == ''){
                alert("请选择要修改的日志对象!");
                return false;
            }
            $.dialog({
                id:"updateLog",
                title:'修改日志信息',
                width: '500px',
                height: '300px',
                content: 'url:hostLog_updateLog.do?id='+id,
            });
        }
        function delRequest(){
            var id = $(":checkbox[checked='checked']").val();
            if(id == null || id == ''){
                alert("请选择要删除的日志对象!");
                return false;
            }
            theForm.logId.value = id;
            theForm.action = "hostLog_deleteLog.do";
            $.dialog.confirm('你确定要删除该日志对象吗？', function(){
                $.dialog.tips('执行删除操作');
                theForm.submit();
            }, function(){
                //$.dialog.tips('执行取消操作');
            });
        }

        
        
        function orderDetail(orderId,productId){
        	$.dialog({
    			id:'orderDetailId',
    			title:'配置信息',
    			max:true,
    			min:false,
    			resize:true,
    			width: '1024px',
    			height: '800px',
    			content: 'url:orderRelation_orderRelationDetail.do?orderId='+orderId+'&productId='+productId
    		});
        }
        
        $(function () {

            //检索
            $("#search-btn").click(function () {
                $("#theForm").submit();
            });

            //条件重置
            $("#reset-btn").click(function () {
                $("#orderId").val("");
                $("#productInstanceId").val("");
                $("#customerId").val("");
                $("#productId").val("");
                $("#status").val("");
            });

            //发送给云平台
            $("#sendCloud").click(function () {
                var id =$('input:radio:checked').val();
                if (id == null || id == '') {
                    alert("请选择相应的记录!");
                    return false;
                }
                $.dialog.confirm('你确定要发送个云平台吗？', function () {
                    $.ajax({
                        url:'orderRelation_sendCloud.do',
                        type:'post',
                        dataType:'json',
                        data : {Ids:$('input:radio:checked').val()},
                        success:function(data){
							alert(data.returnMessage);
                        }
                    });
            });
        });
            //发送给云平台
            $("#deleteOrder").click(function () {
                var id =$('input:radio:checked').val();
                if (id == null || id == '') {
                    alert("请选择相应的记录!");
                    return false;
                }
                $.dialog.confirm('你确定要发送个云平台吗？', function () {
                    $.ajax({
                        url:'orderRelation_deleteOrder.do',
                        type:'post',
                        dataType:'json',
                        data : {Ids:$('input:radio:checked').val()},
                        success:function(data){
							alert(data.returnMessage);
                        }
                    });
            });
        });
	});       
        
	</script>
    <style type="text/css">
        div.hidden {
            width: 170px;
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
</head>
<body >
<div class="mainbody">
    <form action="orderRelation_orderRelationList.do" method="post" id="theForm" cssStyle="theForm">
        <div class="pd-20 bgcolor-1">
            <h2 class="utt-1">订购关系接口管理</h2>
            <div class="bord-1 pd-10">
                <div class="clearfix filtrate-area">
                    <div class="filtrate-field">
                        <label class="vm">订单ID：</label><s:textfield name="orderId"  id="orderId"/>
                    </div>

                    <div class="filtrate-field">
                        <label class="vm">订购关系ID：</label><s:textfield name="productInstanceId"  id="productInstanceId"/>
                    </div>

                    <div class="filtrate-field">
                        <label class="vm">商城用户：</label><s:textfield name="customerId"  id="customerId"/>
                    </div>

                    <div class="filtrate-field">
                        <label class="vm">产品：</label>
                        <s:select cssClass="select-1" list="#{1:'云主机',2:'CPU',3:'内存',4:'数据盘',5:'带宽',7:'IP',8:'负载均衡'}" name="productId"  headerKey="" headerValue="请选择" />
                    </div>

                    <div class="filtrate-field">
                        <label class="vm">商城状态：</label>
                        <s:select cssClass="select-1" list="#{1:'新增',2:'修改'}" name="status"  headerKey="" headerValue="请选择" />
                    </div>

                    <div class="filtrate-field">
                        <span class="ubtn-1 mgl-20">
                        	<input  id="search-btn" type="button" value="查询" /> 
                        </span> 
                        <span class="ubtn-2 mgl-20">
                        	<input id="reset-btn" type="button" value="重置" /> 
                        </span>
                    </div>
                </div>
            </div>
            <div class="utt-2 mgt-20">
            	<a id="sendCloud"  class="icon-add" href="#" >订单发送给云平台</a>
                <a id="deleteOrder" class="icon-del" href="#" >删除关系</a>
            </div>
            <table id="theTable" width="100%" class="blue-table sorttable imgTableCenter" border="0" cellspacing="0">
                <thead>
                <tr>
                    <th>选择</th>
                    <th onclick="sort(theTable,1,'string')">订单ID</th>
                    <th onclick="sort(theTable,2,'string')">订购关系ID</th>
                    <th onclick="sort(theTable,3,'string')">商城用户ID</th>
                    <th onclick="sort(theTable,4,'string')">产品</th>
                    <th onclick="sort(theTable,5,'string')">商城状态</th>
                    <th onclick="sort(theTable,6,'string')">创建时间</th>
                    <th onclick="sort(theTable,7,'string')">用户账号</th>
                    <th onclick="sort(theTable,8,'string')">关系状态</th>
                    <th onclick="sort(theTable,9,'string')">服务开始时间</th>
                    <th onclick="sort(theTable,10,'string')">服务结束时间</th>
                    <th onclick="sort(theTable,12,'string')">是否第一次</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#request.resultList" id="theBean">
                    <tr>
                        <td><input type="radio" name="uuid" value="<s:property value='#theBean.uuid'/>" /></td>
                        <td><s:property value="#theBean.orderId" /></td>
                        <td><s:property value="#theBean.productInstanceId" /></td>
                        <td><s:property value="#theBean.customerId" /></td>
                        <td>
                                <s:if test="#theBean.productId == 1"><a href="#" onclick="orderDetail('<s:property value="#theBean.orderId" />','<s:property value="#theBean.productId" />')">云主机</a></s:if>
								<s:if test="#theBean.productId == 2">CPU</s:if>
								<s:if test="#theBean.productId == 3">内存</s:if>
								<s:if test="#theBean.productId == 4"><a href="#" onclick="orderDetail('<s:property value="#theBean.orderId" />','<s:property value="#theBean.productId" />')">数据盘</a></s:if>
								<s:if test="#theBean.productId == 5"><a href="#" onclick="orderDetail('<s:property value="#theBean.orderId" />','<s:property value="#theBean.productId" />')">带宽</a></s:if>
								<s:if test="#theBean.productId == 7"><a href="#" onclick="orderDetail('<s:property value="#theBean.orderId" />','<s:property value="#theBean.productId" />')">IP</a></s:if>
								<s:if test="#theBean.productId == 8"><a href="#" onclick="orderDetail('<s:property value="#theBean.orderId" />','<s:property value="#theBean.productId" />')">负载均衡</a></s:if>
                        </td>
                        
                        <td>
                        		<s:if test="#theBean.status == 1">新购</s:if>
                        		<s:else>修改</s:else>
                        </td>
                        <td><s:date name="#theBean.createDate" format="yyyy-MM-dd hh:mm:ss" /></td>
                        <td><s:property value="#theBean.boattr1" /></td>
                        <td class="utt-2 mgt-20" style="text-align: left;">
                                <s:if test="#theBean.changeStatus == 1"><a class="icon-modify" href="#">接收关系成功</a></s:if>
								<s:if test="#theBean.changeStatus == 2"><a class="icon-modify" href="#">关系解析成功</a></s:if>
								<s:if test="#theBean.changeStatus == 3"><a class="icon-del" href="#">关系解析失败</a></s:if>
								<s:if test="#theBean.changeStatus == 4"><a class="icon-modify" href="#">主机配置发送给运维成功</a></s:if>
								<s:if test="#theBean.changeStatus == 5"><a class="icon-del" href="#">主机信息发送给运维失败</a></s:if>
								<s:if test="#theBean.changeStatus == 6"><a class="icon-modify" href="#">商城对象信息构建成功</a></s:if>
								<s:if test="#theBean.changeStatus == 7"><a class="icon-del" href="#">商城对象信息构建失败</a></s:if>
								<s:if test="#theBean.changeStatus == 8"><a class="icon-modify" href="#">竣工通知单发送成功</a></s:if>
								<s:if test="#theBean.changeStatus == 9"><a class="icon-del" href="#">竣工通知单发送失败</a></s:if>
								<s:if test="#theBean.changeStatus == 10"><a class="icon-modify" href="#">变更配置发送成功</a></s:if>
								<s:if test="#theBean.changeStatus == 11"><a class="icon-del" href="#">变更配置发送失败</a></s:if>
								<s:if test="#theBean.changeStatus == 12"><a class="icon-modify" href="#">时长发送运维成功</a></s:if>
								<s:if test="#theBean.changeStatus == 13"><a class="icon-del" href="#">时长发送运维失败</a></s:if>
								<s:if test="#theBean.changeStatus == 14"><a class="icon-modify" href="#">时长变更_成功</a></s:if>
								<s:if test="#theBean.changeStatus == 15"><a class="icon-del" href="#">时长变更_失败</a></s:if>
								<s:if test="#theBean.changeStatus == 16"><a class="icon-modify" href="#">状态发送运维_成功</a></s:if>
								<s:if test="#theBean.changeStatus == 17"><a class="icon-del" href="#">状态发送运维_失败</a></s:if>
								<s:if test="#theBean.changeStatus == 18"><a class="icon-modify" href="#">状态变更_成功</a></s:if>
								<s:if test="#theBean.changeStatus == 19"><a class="icon-del" href="#">状态变更_失败</a></s:if>
                        </td>
                        <td><s:date name="#theBean.serviceBeginTime" format="yyyy-MM-dd hh:mm:ss" /></td>
                        <td><s:date name="#theBean.serviceEndTime" format="yyyy-MM-dd hh:mm:ss" /></td>
                        <td>
                            <s:if test="#theBean.boattr5 == 1">第一次</s:if>
                            <s:else>不是</s:else>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
            <div class="pages mgb-10">
                <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
            </div>
        </div>
    </form>
</div>
</body>
</html>