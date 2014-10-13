<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/hlj/report/customReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/underscore/underscore-1.4.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/customReporKpis.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/hlj/report/js/jsonsql-0.1.js"></script>



<script type="text/javascript">
	$(document).ready(function(){
		loadKpis();
	})
	var api = frameElement.api;
	w = api.opener;
	
	function loadKpis(){
		var objs = [];
		var kpiName = $('#kpiName').val();
		if(kpiName==''){
			objs = kpis.${obj.resourceType};
		}else{
			objs = jsonsql.query("select * from kpis.${obj.resourceType} where (name=='"+kpiName+"')",kpis,true);		
		}	
		var tml = $('#temp_kpis').html();
		var tmp = _.template(tml, {'datas':objs} );
		$('#kpiTbody').html(tmp);
		updateCheckBox();
	}
	
	function save(){
		var sel = $(".bon-chb");
		if(sel.length==0){
			$('#tip').html("*请选择指标!");
		}else{
			var id = sel.attr('kpiid');
			var name = sel.attr('kpiname');
			var unit = sel.attr('kpiunit');	
			w.addKpi(name,id,unit);
			api.close();
		}
	}
	function back(){
		api.close();
	}
	function updateCheckBox(){	
	//修改默认checkbox样式
	$("input[type='checkbox'].inp-chb").each(function(){
		var _inpChb = $(this),
			_bChb = _inpChb.next();
		if(_inpChb.is(":checked")){
			_bChb.addClass("bon-chb");
		}else{
			if(_bChb.hasClass("bon-chb"))_bChb.removeClass("bon-chb");
		}
		_bChb.click(function(){
			_inpChb.trigger("click");
			$(".bon-chb").removeClass("bon-chb");
			if(_inpChb.is(":checked")){
				_bChb.addClass("bon-chb");
				$('#tip').html("");
			}else{
				_bChb.removeClass("bon-chb");
			}
		})
	})
	}
</script>
</head>
  
  <body>
        <table  width="100%" class="mgt-10" border="0">
              <tr>
              	<td class="til">指标名称:</td>
				<td>
					<input type="text"  id="kpiName" class="select-1 fl" value=""/>
					<span class="ubtn-1 mgl-20"><input type = "button" onclick="loadKpis()"  value = "查询" id="query"/></span>
				</td>
			  </tr>   
		</table>  			
    	<table id="tab" width="100%" class="blue-table sorttable mgt-10" border="0" cellspacing="0">
			<thead>
				<tr>
					<th>选择</th>
					<th>指标名称</th>
					<th>指标说明</th>								
				</tr>		
			</thead>
			<tbody id="kpiTbody">
				
			</tbody>	
		</table>	
		<div id="tip" style="margin-top:10px;color: red">
		</div>
		<div align="center" style="width: 100%;height:20%;margin-top:20px">
		 	<span class="ubtn-1 mgl-3"><input type="button" value="确定" onclick="save();" > </input></span>
			<span class="ubtn-2 mgl-3"><input type="button" value="返回" onclick="back();" ></input></span>
		</div>
  </body>
  <script type ="text/template" id="temp_kpis">
		<@_.each(datas, function(e){@>
				<tr>
					<td>
						<input type="checkbox" class="inp-chb" value="<@=e.id@>"/>
			  			<span class="b-chb bon-chb" kpiid="<@=e.id@>" kpiname="<@=e.name@>"  kpiunit="<@=e.unit@>" ><b></b></span>
					</td>
					<td><@=e.name@></td>
					<td><@=e.desc@></td>
				</tr>
		<@})@>		
</script>
</html>
