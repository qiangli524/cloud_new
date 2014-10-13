<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>


<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$("[name='submit']").click(function(){
			var regular = /^[1-9][0-9]{0,3}$/;
			var connect_id=$("#connect_id").val();
			var uuid=$("#uuid").val();
<%--			var vsName=$("#vsName").val();--%>
			var vlanId = $(this).prev().val();
			var pgName = $(this).parent().prev().text();
			if(regular.test(vlanId) && vlanId < 4096 && vlanId>0){
				if (confirm("是否要修改"+pgName+"的Vlan为"+vlanId+"?")==true) {
					$.ajax({
						type : 'post',
		        		cache : false,
		        		url : 'networkvs_setVlan.do',
		       			data:{"connect_id":connect_id,"uuid":uuid,"pgName":pgName,"vlanId":vlanId},
		<%--       			dataType: 'text',--%>
		       			success : function(data){
		       				frameElement.api.opener.$.dialog.tips(data);
		       			},
		       			error : function(data,textStatus){
		       				frameElement.api.opener.$.dialog.tips('操作失败！error:'+data);
		       			}
						
					})
				}
			} else {
				alert("请输入正确的Vlan信息！(Vlan规则为 0-4096)");
			}
		});	
	})
</script>
</head>
<body class="scrollbody">
<s:form id="theForm">
<s:hidden id="connect_id" value="%{connect_id}"></s:hidden>
<s:hidden id="uuid" value="%{uuid}"></s:hidden>
<s:hidden id="pKey" value="%{pKey}"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">网络标签</th>
				   <th onclick="sort(theTable,1,'string')">VLAN　ID</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td> <s:property value="#theBean.name"/></td>
			  		<td>
			  			<input value='<s:property value="#theBean.vlanId"/>' style="width:50px;" class="inpt-1 vm" id=""/>
			  			&nbsp;&nbsp;&nbsp;&nbsp;
			  			<input type="button" class="ubtn-3 vm mgl-3" value="确定" name="submit"/>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
