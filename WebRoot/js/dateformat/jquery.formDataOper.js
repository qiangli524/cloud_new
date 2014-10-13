;(function($){
	//全局函数
	$.extend({
		"formDataFormat":function(){
			var formData = "";
			var datas = arguments[0];
			var array = datas.split("&");
			for(var i = 0 ; i< array.length; i++){
				var singleData = array[i].split("=");
				for(var j = 0 ; j < singleData.length ; j++){
					if(j == 0){
						var  tagName = singleData[j].split("theForm.");
						if(tagName.length>1){
							formData += tagName[1];
						}else{
							formData += tagName;
						}
					}else {
						formData += "=" + singleData[j];
					}
				}
				if(i != array.length-1){
					formData += "&";
				}
			}
			return formData;
		}
		
	});
	//jQuery对象函数
	$.fn.extend({
		
	});
})(jQuery);