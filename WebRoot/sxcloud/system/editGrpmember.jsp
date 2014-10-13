<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
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
    //按添加按钮时触发的函数
    function addselect(forselectForm,selectedForm) {
    var forForm = eval(forselectForm);
	var selectedForm = eval(selectedForm);
    if (cf_isNotObject(forForm))
       return false;
	if (cf_isNotObject(selectedForm))
       return false;
    var k;
    var len;
    len = selectedForm.length;
    for (k = 0; k < forForm.length; k++)
	{
       if (forForm.options[k].selected)
	   {
          olength = selectedForm.length;
     	  otext = forForm.options[k].text;
          ovalue = forForm.options[k].value;
          tag = 0
          for (i = 0; i < olength; i++)
		  {
            if (ovalue == selectedForm.options[i].value)
			{
               tag = 1;
            }
          }
          if (tag == 0)
		  {
             selectedForm.options[len] = new Option(otext, olength);
             selectedForm.options[len].value = ovalue;
             selectedForm.options[len].selected = true;
             len = len + 1;
          }
       }
    }
        deleteselected(forselectForm); 
  }
  //按删除按钮实现的功能
  function deleteselected(selectedForm){
   var objForm = eval(selectedForm);
   if(cf_isNotObject(objForm)) return false;
   var olength=objForm.length;
   var s=olength - 1;
   var j=0;
   for(i=s; i>=0; i--)
   {
	 if (objForm.options[i].selected)
	 {
       objForm.options[i]=null;
	   j=j+1;
 	 }
   }
   objForm.length=olength-j;
 }
 function submitRequest(){ 
	 
	 var ul = document.getElementById('listTarget');
	  var lis = ul.getElementsByTagName('li');
	   var   str = "" ;
	   len = lis.length;
	 
       for (k = 0; k < len; k++)
	   {
	      if(k>0)
	      str = str + ",";
          str = str + lis[k].getAttribute('data-id');
          
	   }
	    $("input[name='USERIDS']").attr("value", str);	    
	    var groupid = theForm.GROUPID.value;
	    theForm.action='ugroup_saveGrpmember.do?GROUPID='+groupid;
	    document.getElementById('theForm').submit();
	}	
 
 
 
 
 
 $(function(){
		$(".listStyle1").delegate("li","click",function(){
			var　_li = $(this);
			if(_li.hasClass("on")){
				_li.removeClass("on");
			}else{
				_li.addClass("on");
			}
		}).delegate("li","dblclick",function(){
			var dataId = $(this).attr("data-id");
			if(!$("#listTarget li[data-id='"+dataId+"']").length){
				$("#listTarget").append($(this).clone());
			}
		});
		
		$("#add").click(function(){
			$("#listFrom li.on").each(function(){
				$(this).remove();
				var dataId = $(this).attr("data-id");
				if(!$("#listTarget li[data-id='"+dataId+"']").length){
					$("#listTarget").append($(this).clone());
				}
			});
		})
		$("#remove").click(function(){
			$("#listTarget li.on").each(function(){
				$(this).remove();
				var dataId = $(this).attr("data-id");
				if(!$("#listFrom li[data-id='"+dataId+"']").length){
					$("#listFrom").append($(this).clone());
				}
			});
		})
	})


</script>
</head>

<s:form action="ugroup_saveGrpmember" method="post" cssStyle="theForm"
		id="theForm">
		<input type="hidden" name="grpForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>" />
		<input type="hidden" name="grpForm.GROUPID" id="GROUPID" value="<%=(String)request.getParameter("GROUPID") %>"/>
		<input type="hidden" id="grpForm.USERIDS" name="USERIDS" value=""/>
		<div class="bord-1 pd-20">
            	<div class="wrapper-790">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <colgroup><col width="33%" /><col /><col width="33%" /></colgroup>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				 <td>
                            <div class="pdl-10 pdr-10">
                                <h3 class="utt-3">备选用户</h3>
                                <div class="pd10 bord-1 js_selindexh" style="height:280px;overflow:auto;">
                                    <ul class="listStyle1" id="listFrom">
                                       <s:iterator value="grpForm.selectedUsers" id="Bean"> 
    

                                        <li data-id='<s:property value="#Bean.ID" />'> <s:property value="#Bean.NAME" /> </li>
                                       </s:iterator>
                                    </ul>
                                    
                                </div>
                            </div>
                            </td>

					
				
					<td>
                            <p class="tc"><span class="ubtn-blue ubtnblue-1" id="add"><input  type="button"  value=">>" /></span></p>
                                <p class="tc mgt-30"><span class="ubtn-blue ubtnblue-1" id="remove"><input type="button"  value="<<"/></span></p>
                     </td>
					
					
					 <td>
                                <div class="pdl-10 pdr-10">
                                    <h3 class="utt-3">已选用户</h3>
                                    <div class="pd10 bord-1 js_selindexh" style="height:280px;overflow:auto;">
                                        <ul class="listStyle1" id="listTarget">
                                           <s:iterator value="grpForm.userForSelect" id="Bean2"> 
                                             <li data-id='<s:property value="#Bean2.USERID" />'><s:property value="#Bean2.USERNAME"/></li>
                                             </s:iterator>
                                        </ul>
                                    </div>
                                </div>
                            </td>

					
					
				</tr>
				<tr>
					<td colspan="10" class="btnCenter">
						<span class="ubtn-1 mgl-20">
							<input type="button"  value="确定" onclick="javascript:submitRequest();return false;" />
						</span>
						<span class="ubtn-2 mgl-20">
							<input type="button"  value="返回" onclick="window.history.back()" />
						</span>
					</td>
				</tr>
			</table>
	</s:form>


</html:html>
