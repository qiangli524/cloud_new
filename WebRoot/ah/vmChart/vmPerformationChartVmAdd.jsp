<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<html>
<head>
    <title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <style>
    	  div { 
                float:left; 
                position:relative; 
                top:5px; 
                left:5px;
            }
           .divinput{
           		position:relative;	
           		top:240px; 
           		left:13px;
           }
           #addVm{
           		margin-right: 5px;
           }
    </style>
    <script type="text/javascript">
    	var api = frameElement.api;
			w = api.opener;
    	var arr = new Array();
    	var vm = "";
    	var boo = true;
    	
    	function addVmhost(){
    		var vmNames = "";
    		var vmIds="";
    		var vm = $("#vmIdSpace").html();
    		if(vm != ""){
    			arr =vm.split(",");//存放已添加的名字
    		}
    		//父页面调用iframe里页面的对象或方法
    		//var boxs=document.frames["vmAdd"].document.getElementsByName('vmButtName');IE8,IE9兼容，火狐不行
    		//var boxs=vmAdd.window.document.getElementsByName('vmButtName');//也可以：IE8,IE9,火狐兼容
    		var boxs=vmAdd.document.getElementsByName('vmButtName');//IE8,IE9,火狐兼容
			for(i=0;i<boxs.length;i++){
					if(boxs[i].checked==true ){
						var vmName = boxs[i].value;	
						var vmId = boxs[i].title;
						if(vm!=""){				
						//判断ID是否已存在框内
						for(j=0;j<arr.length;j++){
							if(vmId == arr[j]){
								boo =false; 
							}
						}
						}
						if(boo){
							vmIds = vmIds+vmId+",";
							vmNames = vmNames+"<div name='vn' id=''  onclick='mclick(this);' onmouseover='mover(this);' onmouseout='mout(this);' >"+vmName+"<input type='hidden' title='"+vmName+"' name='hiid' t value="+vmId+"></div><br/>";//用于展示							
						}
						boo=true;
					}
	     	}
	     	$("#vmNameSpace").append(vmNames);//在div内添加元素，或字符串 
	     	$("#vmIdSpace").append(vmIds); 
    	}
    	
  		function resert(){
    		document.getElementById("vmNameSpace").innerHTML="";//清空div
    		document.getElementById("vmIdSpace").innerHTML="";
    		/* var boxs=document.frames["vmAdd"].document.getElementsByName('vmButtName');
    		for(var i=0;i<boxs.length;i++){
    			boxs[i].checked=false;
    		} */
    	}
 		function save(){		
 			var vmNames2 = "";
 			var vmIds2 = $("#vmIdSpace").html();
 			var vm="";
	     	$('input[name="hiid"]').each(function(){    
    				vm=vm+ $(this).attr("title")+",";
			});  
    		if(vm != ""){
    			//存放已添加的名字
    			arr = vm.split(",");
    			for(var i=0;i<arr.length-1;i++){
	    			if(i == arr.length-2){
	    				vmNames2=vmNames2+arr[i];
	    			}else{
	    				vmNames2=vmNames2+arr[i]+",";
	    			}
	    		}
	 			w.addValue(vmNames2,vmIds2);
	    		api.close();
    		}else{
    			alert("请至少选择一台虚拟机！");
    		}
 		}
 		//鼠标移入时
 		function mover(a){
 			a.style.backgroundColor = "#DCDCDC";
 		}
 		//鼠标移出时
 		function mout(a){
 			$("div[name='vn']").each(function(){
 				if($(this).attr("id") != "checked"){
 					$(this).css("background", "");
 				}   
			}); 
 		}
 		//鼠标点击后
 		function mclick(a){
 			$("div[name='vn']").each(function(){
 				$(this).attr("id","");
			});
 			a.style.backgroundColor = "#DCDCDC";
 			a.id="checked";
 			$("div[name='vn']").each(function(){
 				if($(this).attr("id") != "checked"){
 					$(this).css("background", "");
 				}   
			}); 
 		}
 		//移除
 		function deleteVmhost(){
 			//获取id为checked的div
 			var my = document.getElementById("checked");
 			if(my==null){
 				alert("请您选定虚拟机！");
 			}
 			var idresult="";
 			my.parentNode.removeChild(my);
 			//清空vmIdSpace中的id，实时加入新的id字符串
 			document.getElementById("vmIdSpace").innerHTML="";
 			$('input[name="hiid"]').each(function(){    
    				idresult=idresult+ $(this).val()+",";
			});
    		$("#vmIdSpace").append(idresult); 
 		}
    </script>
</head>
<body>
	<div style="width:70%;height:95%">
		<iframe style="width: 100%;height:100%" src="vmReportForm_vmPerformanceForAdd.do" name="vmAdd" frameborder="0">Load Failed?</iframe>
	</div>
	<div style="width:10%;height:20%"  class="divinput" >
		<span class="ubtn-1 mgl-3"><input type="button" value="添加" onclick="addVmhost();" /></span><br/><br/>
		<span class="ubtn-1 mgl-3"><input type="button" value="移除" onclick="deleteVmhost();" /></span>
	</div>
	<div style="width:18%;height:90%" id="addVm" ><br/><br/>
		已添加的虚拟机：<div style="overflow-y:auto;
		width:100%;height:80%;border: 1px solid #B0E0E6;" id="vmNameSpace"></div>	
		<div align="center" style="width: 100%;height:20%">
			<span class="ubtn-2 mgl-3"><input type="button" value="清空" onclick="resert();" ></input></span>
		 	<span class="ubtn-1 mgl-3"><input type="button" value="确定" onclick="save();" > </input></span>
		</div>
	</div>
	<div style="display: none;" id="vmIdSpace"></div>
</body>
</html>