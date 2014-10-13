//默认新窗口配置 
var windowDefaultConfig = new Object; 
windowDefaultConfig['directories'] = 'no'; 
windowDefaultConfig['location'] = 'no'; 
windowDefaultConfig['menubar'] = 'no'; 
windowDefaultConfig['resizable'] = 'yes'; 
windowDefaultConfig['scrollbars'] = 'yes'; 
windowDefaultConfig['status'] = 'no'; 
windowDefaultConfig['toolbar'] = 'no'; 
/**
 * 以POST表单方式打开新窗口的JQUERY实现
 * 
 * @param:url 需要打开的URL
 * @param:args URL的参数，数据类型为object
 * @param:name 打开URL窗口的名字，如果同一按钮需要重复地打开新窗口，而不是在第一次打开的窗口做刷新，此参数应每次不同
 * @param:windowParam 新打开窗口的参数配置
 * @author: wanglei_bj@si-tech.com.cn
 */
function jQueryOpenPostWindow(url, args, name, windowParam) {
	// 创建表单对象
	var _form = $("<form></form>", {
		'id' : 'tempForm',
		'method' : 'post',
		'action' : url,
		'target' : name,
		'style' : 'display:none'
	}).appendTo($("body"));

	// 将隐藏域加入表单
	for ( var i in args) {
		_form.append($("<input>", {
			'type' : 'hidden',
			'name' : i,
			'value' : args[i]
		}));
	}

	// 克隆窗口参数对象
	var windowConfig;
	$.extend(windowConfig,windowDefaultConfig);
	//var windowConfig = clone(windowDefaultConfig);

	// 配置窗口
	for ( var i in windowParam) {
		windowConfig[i] = windowParam[i];
	}

	// 窗口配置字符串
	var windowConfigStr = "";

	for ( var i in windowConfig) {
		windowConfigStr += i + "=" + windowConfig[i] + ",";
	}

	// 绑定提交触发事件
	_form.bind('submit', function() {
		window.open("about:blank", name, windowConfigStr);
	});

	// 触发提交事件
	_form.trigger("submit");
	// 表单删除
	_form.remove();
}