<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>

<title>用户组管理</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("a").live('click',function(){
			
			var　_a = $(this);
			
			if(_a.hasClass("on")){
				 _a.removeClass("on");
				 $("a[name='all1']").each(function(){
					 var s=_a.attr('data-funid');
					 var ss=$(this).attr('data-funid');
					 
					 if(ss.substring(0,2)==s.substring(0,2)){
							if($(this).hasClass("on")){
								$(this).removeClass("on");
							
						 }
					 }
				 });
					 
				 $("a[name='all2']").each(function(){
						 var s=_a.attr('data-funid');
						 var ss=$(this).attr('data-funid');
						 
						 if(ss.substring(0,4)==s.substring(0,4)){
								if($(this).hasClass("on")){
									$(this).removeClass("on");
								
							 }
						 }
					 });
			
				
		}else{
				_a.addClass("on");
			}
			
		
			
			
		});
		
		
		
		  $("a[name='all1']").live('click',function(){
			  var all=$(this);
			  var s=all.attr('data-funid');
			  
				 $("a[name!='all1']").each(function(){
					 var　_a = $(this);
					 var ss=_a.attr('data-funid');
					
					 if(ss.substring(0,2)==s.substring(0,2)){
						if(all.hasClass("on")){
							_a.addClass("on");
						}else{
							_a.removeClass("on");
						}
					 }
					 		 
					 
				 })

			});
			 
	  $("a[name='all2']").live('click',function(){
		  var all=$(this);
		  var s=all.attr('data-funid');
		 
			 $("a[name!='all2']").each(function(){
				 var　_a = $(this);
				 var ss=_a.attr('data-funid');
				
				 if(ss.substring(0,4)==s.substring(0,4)){
					if(all.hasClass("on")){
						_a.addClass("on");
					}else{
						_a.removeClass("on");
					}
				 }
				 		 
				 
			 })

		});
	
	
		$("#save").click(function(){
			
			
			  var   str = "" ;
			 
			  $("a").each(function(){

				      if($(this).hasClass("on")){

			              str = str + $(this).attr('data-id');
			              str = str + ",";  
			              
			              var funid=$(this).attr('data-funid');
			              
			              if(funid.substring(4,5)!="0"||funid.substring(5,6)!="0"){
			            	  
			            	  var strfather=funid.substring(0,4)+"000000";
			            	  str=str+$("span[data-funid='"+strfather+"']").attr('data-id');
			            	  str=str+",";
			            	  
			            	 
			            	  if(funid.substring(2,3)!="0"||funid.substring(3,4)!="0"){
			            		  var strgfather=funid.substring(0,2)+"00000000";
				            	  
				            	
				            	  str=str+$("span[data-funid='"+strgfather+"']").attr('data-id');
				            	  str=str+",";
			            	  }
			            	  
			              }else{
			            	  if(funid.substring(2,3)!="0"||funid.substring(3,4)!="0"){
			            		  var strgfather=funid.substring(0,2)+"00000000";
				            	  
				            	
				            	  str=str+$("span[data-funid='"+strgfather+"']").attr('data-id');
				            	  str=str+",";
			            	  }
			            	  
			            	  
			              }
			              
				      }
			    });
			//那些不能点的父页面默认给予(待改善)
			//  $("span[name='first']").each(function(){
				  
			//	  str = str + $(this).attr('data-id');
	       //       str = str + ","; 
				  
			//  });
			  
			//  $("span[name='second']").each(function(){
				  
			//	  str = str + $(this).attr('data-id');
	       //       str = str + ","; 
				  
			//  });
			  
			  
			  
			  
			
			 
			  var groupid = theForm.GROUPID.value;
			  
			  theForm.action='ugroup_saveGroupAuthority.do?GROUPID='+groupid+'&FUNCIDS='+str;
			  document.getElementById('theForm').submit();   
	
			
		});
		
		
		var array = ["arrowbox red-arrowbox","arrowbox blue-arrowbox","arrowbox orange-arrowbox","arrowbox purple-arrowbox","arrowbox pink-arrowbox"]
		$("span[name='first']").each(function(i){
			
			$(this).addClass(array[i%5]);
			
			
			
			
			
		});
		//二级功能(有三级功能的)颜色显示和它的一级功能颜色显示对应
		$("span[name='second']").each(function(i){
			
			$(this).attr("class",($(this).parents("#dl1").find("span[name='first']").attr("class")));
		});
		
			
	})
	


</script>

</head>
<s:form action="ugroup_saveGroupAuthority" method="post" cssStyle="theForm"
		id="theForm">
		
		<input type="hidden" name="grpForm.GROUPID" id="GROUPID" value="<%=(String)request.getAttribute("groupID") %>"/>
		
   <body>
    <div class="mainbody">
    	<div class="pd-20 bgcolor-1">
        	<h2 class="utt-1">权限设置</h2>
            <div class="bord-1 pdl-20 pdr-20">
           			<s:iterator value="#request.funcsList" id="first">
           				<dl id="dl1" class="dl-1">
                   			<dt ><span name='first' data-id='<s:property value="#first.id_p" />' data-funid='<s:property value="#first.id" />' ><s:property value="#first.name"/> </span></dt>
                   			
                    		<dd id='dd1'> 
                    		     
                        		<a name="all1" data-id='<s:property value="#first.id_p" />' data-funid='<s:property value="#first.id" />' href="javascript:;">全选</a>
                        		 
						          
                        		  <s:iterator value="#first.list" id="second">
                        		       
                   							 
                        		       <s:if test="#second.list.size()!=0">
                        		         <dl class="dl-1 dl1-first">
                        		           <dt><span name="second" data-id='<s:property value="#second.id_p" />' data-funid='<s:property value="#second.id" />' ><s:property value="#second.name"/> </span></dt>
                   							      
                    							<dd id='dd2'>
                    							     <a name="all2" data-id='<s:property value="#second.id_p" />' data-funid='<s:property value="#second.id" />'  href="javascript:void(0)">全选</a>
					                    					<s:iterator value="#second.list" id="third">
					                    					       
					                    							<s:set name="flag" value="'true'" scope="request"  />
								                        		                <s:iterator value="#request.groupFuncsList" id="group">
								                        		                       <s:if test="#group.FUNCID == #third.id_p &&#group.GROUPID == #request.groupID"> 
								                    		                                   <a  name='a' data-id='<s:property value="#third.id_p" />' data-funid='<s:property value="#third.id" />' class="on" href="javascript:;"> <s:property value="#third.name"/>   </a>
								                    		                                 
								                    		                                  <%  String flag=(String)request.getAttribute("flag");
								                    		                                      
								                    		                                     request.setAttribute("flag","false");
								                    		                                  %>
								                    		                           </s:if>
								                        		                 </s:iterator>
					                        		                 
					                        		                    <s:if test="#request.flag=='true'">
					                        		                   <a name='a' data-id='<s:property value="#third.id_p" />' data-funid='<s:property value="#third.id" />' href="javascript:;"> <s:property value="#third.name"/> </a>
					                        		                    </s:if>
                    							          
                    							       </s:iterator>
                    							</dd>
                        					</dl>
                        					
                        		       </s:if>
                        		       
                        		       <s:else>
                        		           
                        		              <s:set name="flag2" value="'true'" scope="request"  />
                        		              
                        		                <s:iterator value="#request.groupFuncsList" id="group">
                        		                
                        		                       <s:if test="#group.FUNCID == #second.id_p &&#group.GROUPID == #request.groupID"> 
                        		                       
                    		                                   <a name='a' data-id='<s:property value="#second.id_p" />' data-funid='<s:property value="#second.id" />' class="on" href="javascript:;"> <s:property value="#second.name"/>   </a>
                    		                                 
                    		                                  <%  String flag2=(String)request.getAttribute("flag2");
                    		                                      
                    		                                     request.setAttribute("flag2","false");
                    		                                  %>
                    		                                 
                    		                             
                    		                           </s:if>
                    		                       
                        		                 </s:iterator>
                        		                 
                        		                    
                        		                    <s:if test="#request.flag2=='true'">
                        		                  
                        		                   <a name='a' data-id='<s:property value="#second.id_p" />'  data-funid='<s:property value="#second.id" />' href="javascript:;"> <s:property value="#second.name"/> </a>
                        		                   
                        		                    </s:if>
                        		                    
                        		               
                        		        </s:else>
                        		  </s:iterator>
                        		
                        		
                   		 	</dd>
             			</dl>
           			</s:iterator> 
           			
            
                
   </div>
   </div>
   </div>
   
   
   <div class="filtrate-field">
                           
                           <tr>
                           <td colspan="2">
                           <div style="width:20%;margin:auto;">
                           <span class="ubtn-1 mgl-20"><input id="save" type="button" value="确定"/>
                           </span>
							<span class="ubtn-2 mgl-20"><input style="float:right;" type="button" value="返回" onclick="window.history.back()"/>
							
							
							
							</div>
							</td>
							</tr>
                           
                           
                         </div>
</body>

</s:form>
</html>
