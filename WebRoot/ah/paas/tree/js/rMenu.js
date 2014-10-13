// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addDataCenter";//添加数据中心
	menuArr[1] = "#delDataCenter";//移除数据中心
	menuArr[2] = "#editDataCenter";//编辑数据中心
	menuArr[3] = "#addService";//添加服务
	menuArr[4] = "#delService";//移除服务
	menuArr[5] = "#editService";//编辑服务
	
	//Caas 4-19
	menuArr[6] = "#addCache";//添加缓存
	menuArr[7] = "#delCache";//移除缓存
	menuArr[8] = "#editCache";//编辑缓存
	menuArr[9] = "#addCacheBusiness";//添加业务
	menuArr[10] = "#delCacheBusiness";//移除业务
	menuArr[11] = "#editCacheBusiness";//编辑业务
	menuArr[12] = "#addCacheExamples";//添加实例
	menuArr[13] = "#delCacheExamples";//移除实例
	menuArr[14] = "#editCacheExamples";//编辑实例
	menuArr[15] = "#addCacheHost";//添加主机
	menuArr[16] = "#delCacheHost";//移除主机
	menuArr[17] = "#editCacheHost";//编辑主机
	
	
	//Daas 占用20-35
	menuArr[20] = "#addDBType";//添加数据库类型
	menuArr[21] = "#delDBType";//删除数据库类型
	menuArr[22] = "#addDBBusiness";//添加业务
	menuArr[23] = "#delDBBusiness";//删除业务
	menuArr[24] = "#addDB";//添加数据库
	menuArr[25] = "#delDB";//删除数据库
	menuArr[26] = "#addDBEntity";//添加数据库实体
	menuArr[27] = "#delDBEntity";//删除数据库实体
	menuArr[28] = "#addServerHost";//添加服务主机
	menuArr[29] = "#delServerHost";//删除服务主机
	menuArr[30] = "#addServerNode";//添加服务节点
	menuArr[31] = "#delServerNode";//删除服务节点 
	menuArr[32] = "#autoGeneration";//自动生成业务下节点
	menuArr[33] = "#renameBusi";//业务重命名
	
	//Maas 占用36-49
	menuArr[36] = "#addMiddlewareType";//添加中间件类型
	menuArr[37] = "#delMiddlewareType";//删除中间件类型
	menuArr[38] = "#addMiddlewareBusiness";//添加业务
	menuArr[39] = "#delMiddlewareBusiness";//删除业务
	menuArr[40] = "#addMiddleware";//添加中间件
	menuArr[41] = "#delMiddleware";//删除中间件
	menuArr[42] = "#addMiddlewareEntity";//添加实体
	menuArr[43] = "#delMiddlewareEntity";//删除实体
	
	//Hadoop
	menuArr[100] = "#addChild";//添加子节点
	menuArr[101] = "#remoteVisit";//远程访问
	menuArr[102] = "#listTopo";//拓扑图
	
}
// 隐藏所有右键菜单
function hideMenuDiv() {
	// 隐藏所有 class="hide" 的 <li> 元素
	$("li.hide").hide();
}
// 根据参数显示右键--参数格式1:2:3:4
function showRm(menuStr) {
	var menu = menuStr;
	var arr = menu.split(':');
	hideMenuDiv();
	for (i = 0; i < arr.length; i++) {
		$(menuArr[arr[i]]).show();
	}
}