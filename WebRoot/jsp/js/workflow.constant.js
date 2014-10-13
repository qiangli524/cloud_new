var orderStatus = {
	maintenance_workorder2:"待处理",
	maintenance_workorder99:"已完成",
	  workorder2:"待分配",
	  workorder3:"待处理",
	  workorder4:"已处理",
	  workorder7:"待反馈",
	  workorder5:"待确定",
	  workorder11:"已确定",
	  workorder99:"已关闭"
}
var qtype = {
	 my:1,
	 join:2,
	 wait:3
}
var customerUrl = {
		  workorder2:"/workorder_customerView.do",
		  workorder3:"/workorder_customerView.do",
		  workorder4:"/workorder_customerView.do",
		  workorder7:"/workorder_feedback.do",
		  workorder5:"/workorder_confirm.do",
		  workorder11:"/workorder_evaluate.do",
		  workorder99:"/workorder_customerView.do"
}

var serviceUrl = {
		  workorder2:"/workorder_serviceCheck.do",
		  workorder3:"/workorder_view.do",
		  workorder4:"/workorder_serviceVerify.do",
		  workorder7:"/workorder_view.do",
		  workorder5:"/workorder_view.do",
		  workorder11:"/workorder_view.do",
		  workorder99:"/workorder_view.do"
}

var mechanicUrl = {
		  workorder2:"/workorder_view.do",
		  workorder3:"/workorder_mechanicCheck.do",
		  workorder4:"/workorder_view.do",
		  workorder7:"/workorder_view.do",
		  workorder5:"/workorder_view.do",
		  workorder11:"/workorder_view.do",
		  workorder99:"/workorder_view.do"
}
var maintenanceUrl = {
		maintenance_workorder99:"/maintenanceWorkorder_view.do",
		maintenance_workorder2:"/maintenanceWorkorder_maintenanceCheck.do"
}

var customStatus = {
		unfinished:"2,3,7",
		completed:"4,5,11,99"
}
