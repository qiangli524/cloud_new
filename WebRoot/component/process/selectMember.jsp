<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
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
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	var group_id = '<%=request.getAttribute("id")%>';
	var api = frameElement.api;
	 	var w = api.opener;
	 	api.button({
	     	id:'Ok',
	     	callback:add,
	     	name: '确定',
	     	focus: true
		 },
		 {
	     id:'cancle',
	     name: '取消'
	 	});
   /**
    *check object
    */
    function cf_isNotObject(obj)
    {
      if(typeof(obj) != "object")
        {
          alert(obj+"\nis not a object");
          return true;
        }
        return false;
    }
   
	function add() {
		var str = '';
		$('[name=theForm.id]:checkbox:checked').each(function() {
			str += $(this).val() + ",";
		});
		w.saveProcessList(str,group_id);
	}
	</script>
</head>
<body class="pop-body scrollbody">
<s:form action="ugroup_saveGrpmember" method="post" cssStyle="theForm"
		id="theForm">
			 <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">进程名</th>
						<th onclick="sort(theTable,2,'string')">主机/用户</th>
						<th onclick="sort(theTable,3,'string')">启动脚本</th>
						<th onclick="sort(theTable,4,'string')">停止脚本</th>
						<th onclick="sort(theTable,5,'string')">类别</th>
						<th onclick="sort(theTable,6,'string')">描述</th>
					</tr>
				</thead>
                <tbody>
                <s:iterator value="selectedList" id="selectedList">
                		<tr>
                			<td>
                			<s:if test="#selectedList.PROCESS_GROUP_ID==null">
                			    <input type="checkbox" value="<s:property value="#selectedList.ID"/>" name="processObj.ID" id="id"/>
                			</s:if>
                			<s:else>
                			   <input type="checkbox" value="<s:property value="#selectedList.ID"/>" name="processObj.ID" id="id" checked="checked"/>
                			</s:else>
                			</td>
                			<td><s:property value="#selectedList.PROCESS"/></td>
                			<td><s:property value="#selectedList.IP"/>/<s:property value="#selectedList.USERNAME"/></td>
                			<td align="center">
                			   <div class="hidden" title='<s:property value="#selectedList.START_SCRIPT"/>'>
                				<s:property value="#selectedList.START_SCRIPT"/>
                				</div>
                			</td>
                			<td align="center">
                			   <div class="hidden" title='<s:property value="#selectedList.STOP_SCRIPT"/>'>
                				<s:property value="#selectedList.STOP_SCRIPT"/>
                				</div>
                			</td>
                			<td>
								<s:if test="#selectedList.TYPE==0">通用</s:if>
								<s:elseif test="#selectedList.TYPE==1">部署使用</s:elseif>
								<s:else>其他</s:else>
                			</td> 
                			<td><s:property value="#selectedList.PROCESS_DESC"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
			</table>
			</div>
	</s:form>
</body>

</html:html>
