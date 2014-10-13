<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title>index</title>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/base.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/temp/pub-ui/css/skin1/all.css"></link>
    <link href="<%=request.getContextPath()%>/sxcloud/images/nresources/unitedIndex.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/all-min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#showStore").click(function(){
    			$.dialog({
    				id:'detail',
    				title:'存储设备详细信息',
    				width:'1200px',
    				height:'800px',
    				lock:true,
    				zIndex:'1975',
    				content:'url:datastore_listStoreDevice.do'
    			});
    		});
    	});
    </script>
</head>
    <body treerel="1">
 		<div class="pdr-5 pdl-5" style="height:250px ">
 			<div>
			   <div style="float: left;width: 50%;text-align: center; margin-top: 10px">
				   	<li title="总量:<s:property value="nasStoreObj.storeValid"/> 已用:<s:property value="nasStoreObj.storeUsed"/> 可用:<s:property value="nasStoreObj.StoreFree"/>">
				    	<p class="mt5">NAS总容量 ：<s:property value="nasStoreObj.storeValid"/> TB</p>
				    	<s:if test="(nasStoreObj.storeUsed ==0 || nasStoreObj.storeUsed =='' || nasStoreObj.storeUsed ==null)">
				    		<p class="percentage2 mt5">
				    			<b class="pre-white" style='height:100%'></b>
				    		<%-- <b class="pre-top" style="top:<s:property value="(100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0))/100*177-19"/>px"></b> --%>
				    		</p>
				    		</s:if>
				    		<s:elseif test="((@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0)<=60)">
				    			<p class="percentage2 mt5">
					    			<b class="pre-white" style='height:<s:property value="100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0)-10"/>%'></b>
					    			<b class="pre-top" style="top:<s:property value="(100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0))/100*177-19"/>px"></b>
				    			</p>
				    		</s:elseif>
				    		<s:elseif test="((@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100)>60)">
					    		<p class="percentage3 mt5">
					    			<b class="pre-white1" style='height:<s:property value="100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0)"/>%'></b>
					    			<b class="pre-top1" style="top:<s:property value="(100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0))/100*177-19"/>px"></b>
					    		</p>
				    	</s:elseif>
				   		<p><s:property value="@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid ) / 100.0"/> %<s:property value="()"/></p>
				  	</li>
			    </div>
			    <div style="float: left;width: 50%;text-align: center; margin-top: 10px">
				   	<li title="总量:<s:property value="sanStoreObj.storeValid"/> 已用:<s:property value="sanStoreObj.storeUsed"/> 可用:<s:property value="sanStoreObj.StoreFree"/>">
				    	<p  class="mt5">SAN总容量 ：<s:property value="sanStoreObj.storeValid"/> TB</p>
				    	<s:if test="(sanStoreObj.storeUsed ==0 || sanStoreObj.storeUsed =='' || sanStoreObj.storeUsed ==null)">
				    		<p class="percentage2 mt5">
				    			<b class="pre-white" style='height:100%'></b>
				    		<%-- <b class="pre-top" style="top:<s:property value="(100-(@java.lang.Math@round(nasStoreObj.storeUsed * 100 * 100 / nasStoreObj.storeValid )/100.0))/100*177-19"/>px"></b> --%>
				    		</p>
				    		</s:if>
				    		<s:elseif test="((@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100.0)<=60)">
				    			<p class="percentage2 mt5">
					    			<b class="pre-white" style='height:<s:property value="100-(@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100.0)"/>%'></b>
					    			<b class="pre-top" style="top:<s:property value="(100-(@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100.0))/100*177"/>px"></b>
				    			</p>
				    		</s:elseif>
				    		<s:elseif test="((@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100)>60)">
					    		<p class="percentage3 mt5">
					    			<b class="pre-white1" style='height:<s:property value="100-(@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100.0)+11"/>%'></b>
					    			<b class="pre-top1" style="top:<s:property value="(100-(@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid )/100.0))/100*177+9"/>px"></b>
					    		</p>
				    	</s:elseif>
				    	
				   		<p><s:property value="@java.lang.Math@round(sanStoreObj.storeUsed * 100 * 100 / sanStoreObj.storeValid ) / 100.0"/> %</p>
				  	</li>
				</div>
			</div>
			<div>
				<a class="mr10 fr" href="javascript:;" id="showStore">更多存储资源</a>
			</div>
		</div>
    </body>