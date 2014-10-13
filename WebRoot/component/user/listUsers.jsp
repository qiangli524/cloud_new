<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
        $(function(){
        	$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
        
              $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'增加用户信息',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    			content: 'url:usermanage_editUser.do'
    			});
              });
              
             $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id ='';
        	var lenth = 0;
        	$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert("请勾选一条信息");
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行修改');
				return false;
			}
    		$.dialog({
    			id:'vdi',
    			title:'修改用户信息',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    			content: 'url:usermanage_editUser.do?id='+id
    			});
              });
           });   
      function searchRequest(){
			theForm.submit();
		}
	function deleteConfig(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("theForm.id");
 	    if(checkboxids!=null&&checkboxids.length>0){
	 	    for(var i=0;i<checkboxids.length;i++){
	 	      if(checkboxids[i].checked){
	 	      	couterNum = couterNum + 1 ;
	 	      }
	 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息");
 	    return false ;
 	    }
 	    
		var id='';
		$('[name=theForm.id]:checkbox:checked').each(function(){
       		id +=$(this).val()+",";
       		
       	 });
     	 if(confirm("确定要删除?")){
		theForm.action="usermanage_delete.do?id="+id;
		theForm.submit();
		}
	}
		
	function resetForm(theForm){
		theForm.ip.value = '';
		theForm.username.value = '';
	}
    </script>
</head>
</head>
<body class="pop-body scrollbody">
    <s:form action="usermanage_listUsers.do" method="post" id="theForm" cssStyle="theForm">
	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">部署机用户管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">IP地址:</label>
				<s:textfield name="theForm.ip" id="ip" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">用户名：</label>
				<s:textfield name="theForm.username" id="username" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
	<!--query-form end -->
             <div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"  name="add" >新增</a>
				<a class="icon-modify" href="javascript:void(0)"  name="mod" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="javascript:deleteConfig();">删除</a>
			</div>   
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">IP地址</th>
						<th onclick="sort(theTable,2,'string')">用户名</th>
						<th onclick="sort(theTable,3,'string')">密码</th>
						<th onclick="sort(theTable,4,'string')">用户类型</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr >
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.ip"/></td>
                			<td><s:property value="#theBean.username"/></td>
                			<td><s:property value="#theBean.password"/></td>
                			<td>
                				<s:if test="#theBean.type==0">
                					普通用户
                				</s:if>
                				<s:elseif test="#theBean.type==1">
                					管理员用户
                				</s:elseif>
                				<s:elseif test="#theBean.type==2">
                					信息关系用户
                				</s:elseif>
                				<s:elseif test="#theBean.type==3">
                					oracle安装用户
                				</s:elseif>
                				<s:elseif test="#theBean.type==4">
                					监控用户
                				</s:elseif>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
            <div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
        </div>
    </s:form>
</body>