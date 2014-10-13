(function($) {  
	$.fn.attachment = function(options){
		var defaults = {
			operator:'upload',  //upload绑定添加附件按钮、delete删除附件功能、download下载附件
			maxNum:3, //附件上限、默认为3个
			validFormat:'jpg,bmp,png,gif',//默认'jpg', 'bmp', 'png', 'gif'
			validSize:'2',//单位M
			ids:null //download用到，附件的ids，用逗号隔开	
		};		
		var opts = $.extend(defaults, options);		
		//初始化
		init(opts, $(this));
		function init(opts,current){
			if(opts.operator=='upload'){				
				uploadInit(opts,current);
			}else if(opts.operator=='download'){
				downloadInit(opts,current);
			}else{
				alert("参数operator无效");
			}
		}
		
		/**
		 * 下载功能初始化
		 */
		function downloadInit(opts,current){
			if(opts.ids!=null){
				var idsz = opts.ids.split(',');
				var formhtml = '<form id="downloadForm" action="'+getContextPath()+'/bpm/attachment_download.do?id=66"  method="post" ></form>';
				$(current).append(formhtml);
				$(idsz).each(function (i, id) { 
					$.ajax({
		    			type : 'post',
		    			async : false,
		    			url : getContextPath()+'/bpm/attachment_getAttachmentById.do',
		    			data:{"id":id},
		    			dataType: 'json',
		    			success : function(data){
		    				var html = '<div style="height:27px;font-size:12px">'+data.fileName+' <a id="attachmentDownload_'+data.id+'" style="margin-left:5px;color:blue;">下载</a></div>';
		    				$('#downloadForm').append(html);
		    				$('#downloadForm').after('<input type="hidden" value="'+data.id+'" name="step.objData.attachmentId">');		    						    				
		    				//绑定事件
		    				$('#attachmentDownload_'+data.id).click(function(){
		    					downloadClick(opts,current,data.id)
		    				});
		    			},
		    			error : function(data,textStatus){
		    				console.log('error:' + data);
		    			}
		    		});
				})
			}
		}
		
		function downloadClick(opts,current,id){
//			var options = {
//					success:function(xml){
//						downloadFinish(opts,current,xml);
//					},
//					resetForm:true,						
//					converterTz:false,
//					url:getContextPath()+"/bpm/attachment_download.do?id="+id
//			};
			 var form  = $("#downloadForm"); 
			 form.attr('action',getContextPath()+"/bpm/attachment_download.do?id="+id) ;
			 form.submit(); 
		}
		
		function downloadFinish(opts,current,xml){
			
		}
		/**
		 * 上传功能初始化
		 */
		function uploadInit(opts,current){
			var jattachment_file = $('#jattachment_file');
			if(jattachment_file.length==0){				
				var html = '<form id="uploadForm" action="'+getContextPath()+'/bpm/attachment_upload.do"  method="post" enctype="multipart/form-data" ><input type="file" name="upload" id="jattachment_file" style="display:none;"/></form>'; 
				var forms = $('form');
				if(forms.length!=0){
					$(forms[0]).after(html);
				}else{
					$('body').append(html);
				}
				$(current).after('<div id="attachmentDescDiv" style="margin-top:5px;color:red;font-size:10px"></div>');
				//绑定事件
				$(current).click(function(){
					UploadClick(opts,current)
				});
			}
		}
		
		function UploadClick(opts,current){
			var doms = $('.attachmentClass');
			if(doms.length<opts.maxNum){
				 var file = document.getElementById('jattachment_file');  
				 var _url = getContextPath()+'/bpm/attachment_upload.do?validFormat='+opts.validFormat+'&validSize='+opts.validSize;
		         file.onchange = function (){  
		            var options = {
							success:function(xml){
								uploadFinish(opts,current,xml);
							},
							resetForm:true,						
							converterTz:false,
							url:_url
					};
					 $("#uploadForm").ajaxSubmit(options); 
		         };  
		         if(document.all){  
		              file.click();  
		         }  
		         else{  
		             var evt =  document.createEvent("MouseEvents");   
		             evt.initEvent("click", true, true);  
		             file.dispatchEvent(evt);  
		         }  
			}else{
				$('#attachmentDescDiv').html('*附件最多可以上传'+opts.maxNum+"个");
			}
		}  
		//导入完成，返回的结果
		function uploadFinish(opts,current,xml){		
			var data = JSON.parse(xml);
			if(data.result=='success'){
				$('#attachmentDescDiv').html('');
				var html='<div style="height:27px;font-size:12px">'+data.fileName+' <a id="attachmentDelete_'+data.id+'" style="margin-left:5px;color:blue;">删除</a> <input type="hidden" class="attachmentClass" value="'+data.id+'" name="step.objData.attachmentId"> </div>';
				$(current).before(html);
				$('#attachmentDelete_'+data.id).click(function(){
					var id = data.id;
					deleteAttachment(id,this);
				});				
			}else{
				$('#attachmentDescDiv').html(data.result);
			}
		}
		/**
		 * 删除附件
		 */
		function deleteAttachment(id,obj){
			$.ajax({
    			type : 'post',
    			async : false,
    			url : getContextPath()+"/bpm/attachment_delete.do",
    			data:{"id":id},
    			dataType: 'json',
    			success : function(data){
    				$(obj).parent().remove();
    			},
    			error : function(data,textStatus){
    				console.log('error:' + data);
    			}
    		});
		}
		/**
		 * 获取path
		 */
		function getContextPath() {
		    var pathName = document.location.pathname;
		    var index = pathName.substr(1).indexOf("/");
		    var result = pathName.substr(0,index+1);
		    return result;
		}
	}
})(jQuery); 