<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
  <head>
   
    <title></title>
   <script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest() { 
	theForm.submit();
 	}
 	
 	function delRequest() {
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ROOM_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    theForm.action = 'room_deleteRoom.do'; 
		theForm.submit();
		
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ROOM_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
		var obj = $("#theForm").serialize();
		$.dialog({
			id:'adddisk',
			title:'修改',
			width:'600px',
			height:'400px',
			lock: true,
			content:'url:room_updateRoom.do?'+obj
		});
		
 	}
 	function addRequest() {
 		theForm.flag.value = 0;
	  	$.dialog({
			id:'adddisk',
			title:'添加',
			width:'600px',
			height:'400px',
			lock: true,
			content:'url:room_insertRoom.do'
		});
	  	
 	}
 	function forPassId(ID){
 	  theForm.action = 'area_queryAreaList.do?ROOM_ID='+ID;
	  theForm.submit();
 	}
 	function resetForm(){
			theForm.NAME.value = "";
			theForm.ROOM_TYPE.value="-1";
		}
 	
 	function saveArea(obj){
 		var url = 'room_saveRoom.do';
		$.ajax({
            type: "POST",
            url: url,
            data: obj,
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
  
  <body>
  
   <s:form action="room_queryRoomList.do" id="theForm" method="post">
		<s:hidden name="theForm.ROOM_ID" id="ROOM_ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">机房管理</h2>
	        	<div class="bord-1 pd-10">
					<table width="100%" border="0">
						<tr>
							<td class="til">机房名称：</td>
							<td align="left">
								<s:textfield name="theForm.ROOM_NAME" cssClass="txt" id="NAME"></s:textfield>
							</td>
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" onclick = "javascript:searchRequest()" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" onclick="javascript:resetForm(document.getElementById('theForm'))" /></span>
								</div>
							</td>
						</tr>
					</table>
					<div class="utt-2 mgt-10">
						<a class="icon-add" href="javascript:void(0)" onclick = "addRequest();return false;">新增</a>
						<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;">修改</a>
						<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
					</div>	
					
					
					
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										机房名称
									</th>
									<th onclick="sort(theTable,2,'int')">
										区域个数
									</th>
									<th onclick="sort(theTable,3,'string')">
										机房编码
									</th>
									<th onclick="sort(theTable,4,'string')">
										所在城市
									</th>
									<th onclick="sort(theTable,5,'string')">
										详细地址
									</th>
									<th onclick="sort(theTable,6,'string')">
										联系人
									</th>
									<th onclick="sort(theTable,7,'string')">
										联系人电话
									</th>
									<th onclick="sort(theTable,8,'string')">
										机房性质(自用/租用)
									</th>
									<th onclick="sort(theTable,9,'int')">
										面积(平方米)
									</th>
									<th onclick="sort(theTable,10,'date')">
										创建时间
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.RoomList" id="theBean">
									<tr >
										<td>
											<input name="checkboxid" type="checkbox" 
												value="<s:property value='#theBean.ROOM_ID'/>"/>
										</td>
										<td>
											<s:property value="#theBean.ROOM_NAME" />
										</td>
										<td><s:if test="#theBean.shu!=0">
										<a href="#" onclick="return forPassId('<s:property value="#theBean.ROOM_ID"/>');">
											<s:property value="#theBean.shu" />
											</a>
											</s:if>
											<s:else><s:property value="#theBean.shu" /></s:else>
										</td>
										<!--<td>
										 <s:if test="#theBean.shu!=0">
										<a href="#" onclick="return forPassId('<s:property value="#theBean.ID"/>');">
										<s:property value="#theBean.shu" />
										</a>
										</s:if>
										<s:else>
										<s:property value="#theBean.shu" />
										</s:else>
										</td> -->
										<td>
											<s:property value="#theBean.ROOM_CODE" />
										</td>
										<td>
											<s:property value="#theBean.ROOM_CITY" />
										</td>
											<td>
											<s:property value="#theBean.ROOM_ADDRESS" />
										</td>
										<td>
											<s:property value="#theBean.LINK_MAN" />
										</td>
										<td>
											<s:property value="#theBean.PHONE" />
										</td>
										<td>
											<s:if test="#theBean.ROOM_TYPE==1">自用</s:if>
											<s:else>租用</s:else>
										</td>
										<td>
											<s:property value="#theBean.ROOM_SIZE" />
										</td>
										<td>
											<s:property value="#theBean.INS_DATE" />
										</td>
										
									</tr>
								</s:iterator>
							</tbody>


						</table>
					</div>
				</div>
	</s:form>
   
 </body>

