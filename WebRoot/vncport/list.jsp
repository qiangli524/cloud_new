<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
   	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
	    function searchRequest(){
			$("#tbVncPortVO").submit();
		}
	    
	    $(function(){
	         $("#reset").click(function(){
	         	clear();
	         });
	         
	         $("[name='add']").click(function(){
	        	 $.dialog({
	    			id:'addvncport',
	    			title:'添加端口',
	    			width: '500px',
	    			height: '300px',
	    		    lock:true,	
	    			content: 'url:vncport_addvncport.do'
	    		});
		     });
	         
             $("[name='mod']").click(function(){
	        	var id ='';
	        	var lenth = 0;
	        	$('[name=tbVncPortVO.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	if(id==null || id ==''){
					alert('请先选择一项进行修改');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行修改');
					return false;
				}
	        	$.dialog({
	    			id:'modvncport',
	    			title:'修改端口信息',
	    			width: '500px',
	    			height: '300px',
	    		    lock:true,	
	    			content: 'url:vncport_modify.do?interfaceId='+id
	    		});
	         });
        });
	    
	    function deleteConfig(){
			var id='';
     		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			if(confirm('确定删除？')){
				$.ajax({
		         type: "port",
		         url: "vncport_deletevncport.do?tbVncPortVO.id="+id,
		         dataType: "json",
				 async: false,
  				 cache: false,
		         success : function(data){
					$("#id").attr("value","");
					searchRequest();
		         }
				});
			}
		}
	    
	    function clear() {
	    	$("#host_ip").attr("value","");
	    	$("#ifused").attr("value","");
	    }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="vncport_list.do" method="post" id="tbVncPortVO" cssStyle="tbVncPortVO">
    <div class="pd-20 bgcolor-1">
		<h2 class="utt-1">Vnc端口管理</h2>
		<div class="bord-1 pd-10">			
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
					<label class="vm">宿主机IP：</label>
					<s:textfield name="tbVncPortVO.host_ip"  cssClass="inpt-1 vm" id="host_ip"  cssStyle="height:30px;border:solid 1px #c3c3c3;width:150px;"></s:textfield>
				 </div>
				<div class="filtrate-field">
					<label class="fl">端口状态：</label>
					<s:select cssClass="select-1 vm"  list="#{'0':'未占用','1':'已占用'}" name="tbVncPortVO.ifused" id="ifused" headerKey="" headerValue="--请选择--"></s:select>
				</div>
				<div class="filtrate-field">
					<span class="ubtn-1 mgl-20"><input type="button" value="查询"
								onclick="javascript:searchRequest()" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" value="重置" id="reset" /></span>
				</div>
			</div>
		     <div  class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"  name="add" />增加</a>
<%--				<a class="icon-modify" href="javascript:void(0)" name="mod" />修改</a>--%>
				<a class="icon-del" href="javascript:void(0)" name = "del"  onclick="javascript:deleteConfig();"/>删除</a>
			</div>
			
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="20%" onclick="sort(theTable,1,'string')">宿主机IP</th>
						<th width="15%" onclick="sort(theTable,2,'string')">端口</th>
						<th width="10%" onclick="sort(theTable,3,'string')">端口状态</th>
						<th width="30%" onclick="sort(theTable,4,'string')">占用虚拟机</th>
						<th width="15%" onclick="sort(theTable,5,'string')">更新时间</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="vncPostList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>" name="tbVncPortVO.id" id="id"/>
                			</td>
                			<td><s:property value="#theBean.host_ip"/></td>
                			<td><s:property value="#theBean.port"/></td>
                			<td>
                				<s:if test="#theBean.ifused == 0 ">
                					未占用
                				</s:if>
                				<s:else>
                					已占用
                				</s:else>
                			</td>
                			<td>
                				<s:if test="#theBean.vmcode != null ">
                					<s:property value="#theBean.vmcode"/>
                				</s:if>
                				<s:else>
                					---
                				</s:else>
                			</td>
                			<td><s:date name="#theBean.updatetime" format="yyyy-MM-dd hh:mm:ss"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
             <div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=tbVncPortVO" />
			 </div>
        	</div>
        </div>
    </s:form>
</body>
