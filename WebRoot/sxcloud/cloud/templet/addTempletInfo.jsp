<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%> 
<head>
<title></title>
<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	var xmlHttp;  
	var check;
	var str1=[]; //用来保存ajax返回的数组
	var str2=[];	//用来保存ajax返回的数组
	var count1=1;  //用来控制只保存一回返回的数组
//	var count2=0;	//用来只保存一回返回的数组
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
//	function init()
//	{
//		getSelect('TYPE');
		//getSelect('CONFIG_NAME');
//	}
	function getSelect() {
    	createXmlHttp();
    //	var TYPE=document.theForm.TYPE.value;
    	var TYPE = document.getElementById("TYPE").value;
    	xmlHttp.open("GET", "ajax_getTempletList.do?TYPE="+TYPE, false);     
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
    	if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");       	
		//	var  SelectNode = document.all.CONFIG_NAME;
			var  SelectNode = document.getElementById("CONFIG_NAME");
		//	var  SelectNode = addFileRow('DCtable','');
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","--请选择--"));
      		
      			
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
					if(count1<SelectNode.length){
      					str1.push(o);
      					str2.push(pageInfo[o]);				//将AJAX返回的内容保存在数组，在新增一项时读取数组的内容
      					count1++;
      				}
      		}
      		
    	}
	}
	function change(){		//判断下拉列表中的值是否改变，如果改变则清空数组的内容
    		str1=[];
    		str2=[];
    		str1.push("");
      		str2.push("--请选择--");
      		if(cNum>1){			//如果进行新增几项后再进行模板类型设置则会对页面进行刷新
      			window.navigate(location);
      		}
    	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	

	
	function resetForm(theForm){
		theForm.TEM_ID.value = '';
		theForm.TEM_NAME.value = '';
		theForm.TYPE.value = '';
		theForm.CONFIG_NAME.value = '';
		theForm.CONFIG_VALUE.value = '';
	}
	function submitRequest(theForm){
		if(flag){
			 if(theForm.TEM_ID.value.length ==0){
	     		alert("服务编号不能为空！");
	     		theForm.TEM_ID.focus;
	   	 		return false  ;
	  	 	 }
			 if(theForm.TEM_NAME.value.length ==0){
	     		alert("服务名称不能为空！");
	     		theForm.TEM_NAME.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(theForm.TYPE.value ==0){
	     		alert("服务类型不能为空！");
	     		theForm.TYPE.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(theForm.TEM_DESC.value ==0){
	     		alert("服务描述不能为空！");
	     		theForm.TEM_DESC.focus;
	   	 		return false  ;
	  	 	 }
	  	 	 if(theForm.CONFIG_NAME.value ==0){
	     		alert("请选择配置项！");
	     		theForm.CONFIG_NAME.focus;
	     		theForm.CONFIG_NAME.select;
	   	 		return false  ;
	  	 	 }
	  	 	 if(theForm.CONFIG_VALUE.value ==0){
	     		alert("配置值不能为空！");
	     		theForm.CONFIG_VALUE.focus;
	   	 		return false  ;
	  	 	 }
			theForm.action = 'templet_saveTempletInfo.do'
			theForm.submit();
		}else{
		  alert("编号错误，请更改需求编号！");
		}
	}
   	function searchRequest() { 
		theForm.submit();
 	} 	
 	//新增表格行
 	var cNum=1;
	function addFileRow(tabelName,Tag){
	var type = document.getElementById("TYPE").options[0].selected;
		if(type ==true){
			alert("请先选择服务类型！");
		}else{
			var app  = "theForm.CONFIG_NAME";
			var value = "theForm.CONFIG_VALUE";
		
			var newTr = document.getElementById(tabelName).insertRow();//新增一行
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			newTd0.id=++cNum;
			var rowIndex = newTr.rowIndex;
			newTd0.innerHTML = newTr.rowIndex;
			newTd1.innerHTML = '<select id="num'+cNum+'\" name='+app+'></select>'; 
			newTd2.innerHTML = '<input type=text name='+value+'>&nbsp;&nbsp;<a href="javascript:void(0);" onclick="javascript:deleteFileRow('+tabelName+',this);">删除此行</a>';
    	//	var  selectTag = document.getElementById("CONFIG_NAME");   
    	//	var  selectTag = document.all.CONFIG_NAME; 	
   			var  selectTag2 = document.getElementById("num"+cNum); //取得innerHTML中select的ID
 
	   	//	selectTag2.appendChild(addOption());
	   	
		//	for(var i=0;i<selectTag.length;i++){
		 //		selectTag2.options.add(new Option(selectTag[i].text,selectTag[i].value));//取得Select中的内容赋值给innerHTML
		//	}
			for(var i=0;i<str1.length;i++){
				selectTag2.options.add(new Option(str2[i],str1[i]));
			}
		}
	}
	//function addOption(){ 
 	//	var opt = document.createElement("option"); 
	//	opt.setAttribute("value","Beijing");  
 	//    opt.appendChild(document.createTextNode("北京"));
 	//	return opt; 
 //  }
	//删除表的指定行
	function deleteFileRow(newTB,row){
		var rowIndex=row.parentNode.parentNode.rowIndex;
		//alert(rowIndex);
		//删除一行
			if(rowIndex==-99999){
 					alert("系统提示：没有选中行号!");
			}else{
 					newTB.deleteRow(rowIndex);
				 	rowIndex==-99999;//将rowIndex恢复默认值。
				}
		
	}
	var flag= true;
	function validateForm(){
				document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "";
				//异步方式判断 需求编码是否唯一
				var TEM_ID = document.getElementById("theForm").TEM_ID.value;
				if(TEM_ID == null || "" == TEM_ID){
					document.getElementById("NEED_NUMBERS_SPAN").innerHTML = "需求编号不能为空";
					flag=false;
					return false;
				}else{
					flag =true;
				}
				var url = "templet_uniqueJudgement.do?TEM_ID=" + TEM_ID+"&Date"+(new Date());
				 $.getJSON(url,function(data){
				 	if("NO" == data ){
				 		document.getElementById("NEED_NUMBERS_SPAN").innerHTML = TEM_ID+"已经存在，请更改需求编号!";
				 		flag=false;
				 	}else{
				 		flag=true;
				 	}
				 })
			}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="templet_addTempletInfo.do" method="post" cssStyle="theForm" id="theForm">
<!--  <s:hidden value="thForm.flag"></s:hidden> -->
<div class="scrollbody">
	<div>
		<div class="tit-zzi">
			<div id="zi">服务基本信息 	</div>
			<font color="red">*选择服务类型后再编辑配置项和配置值，如果设置几项后再重新设置服务类型则会刷新界面</font>
			<div id="zi"></div>	
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						服务编号 <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.TEM_ID" cssClass="txt" onblur="validateForm()" id="TEM_ID"></s:textfield>
						<span id="NEED_NUMBERS_SPAN" style="color: RED"></span>
					</td>
					<td class="til">
						服务名称  <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:textfield name="theForm.TEM_NAME" cssClass="txt" id="TEM_NAME"></s:textfield>           
					</td>
				</tr>
				<tr>
					<td class="til">
						服务类型 <font style="color:#FF3401">*</font>
					</td>
					<td>
						<s:select id="TYPE" list="theForm.typeList" listKey="TYPE" name="theForm.TYPE" id="TYPE" listValue="TYPE_NAME" onchange="change()" onclick="getSelect()" headerKey="0" headerValue="-请选择-">
						</s:select> 
					</td>
					<td class="til">
						服务描述<font style="color:#FF3401">*</font>
					</td>
					<td colspan="3">
						<s:textarea name="theForm.TEM_DESC" cols="77" rows="3" id="TEM_DESC"></s:textarea>
					</td>
				</tr>
			</table>
		</div>
	</div>		
		
		<div class="tit-zzi">
			<div id="zi">服务配置&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="addFileRow('DCtable','')" style="color:#FF3401">新增一项</a></div>
			<div id="zi"></div>					
		</div>
		<div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="DCtable">
				<tr>
		      		<th  class="til">序号</th>
					<th  class="til">配置项</th>
					<th  class="til">配置值</th>
				</tr>
				<tr>
					<td class="til">1</td>
					<td class="til">
                    	<s:select list="#{0:'-请选择-'}" name="theForm.CONFIG_NAME" id="CONFIG_NAME"></s:select>
					</td>
					<td>
						<s:textfield name="theForm.CONFIG_VALUE" id="CONFIG_VALUE" cssClass="txt"></s:textfield>   
					</td>
				</tr>				
			</table>
		</div>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="window.history.back()" />
				</td>
			</tr>
		</table>
</div>
</s:form>
</body>
