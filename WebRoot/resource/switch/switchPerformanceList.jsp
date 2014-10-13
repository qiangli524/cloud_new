<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>

<head>
    <title></title>
     <style type="text/css">
		div.hidden{
		width:50px;
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#query").click(function(){
    			if($("#query-form").is(":visible")){
    				$("#query-form").slideUp("slow");
    			}else{
    				$("#query-form").slideDown("slow");
    			}
    		});
    		$("#reset").click(function(){
    			clear();
    		});
        });
      function searchRequest(){
			theForm.submit();
		}
		function clear(){
			$("#name").attr("value","");
		}
		
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="switchPerformanceAction_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="query" id="query">
	<div class="title" id="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">接口名称:</td>
			<td>
				<s:textfield name="theForm.interf_id" id="name"></s:textfield>
			</td>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置" id ="reset"/>
			</div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->
    <div class="blue-wrap noborder">
	<div class="table-head">
				<!-- <ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="add"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteConfig();"/>
                    </li>
                </ul> -->
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
       <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">接口索引</th>
						<th onclick="sort(theTable,1,'double')">利用率(%)</th>
						<th onclick="sort(theTable,2,'double')">接收利用率(%)</th>
						<th onclick="sort(theTable,3,'double')">发送利用率(%)</th>
						<th onclick="sort(theTable,4,'double')">出错率(%)</th>
						<th onclick="sort(theTable,5,'double')">丢包率(%)</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="performanceList" id="theBean">
                		<tr>
                			<td><s:property value="#theBean.interf_id"/></td>
                			<td><s:property value="#theBean.useage"/></td>
                			<td><s:property value="#theBean.recieve"/></td>
                			<td><s:property value="#theBean.send"/></td>
                			<td><s:property value="#theBean.error"/></td>
                			<td><s:property value="#theBean.lost"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>