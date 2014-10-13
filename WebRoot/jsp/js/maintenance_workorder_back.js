/*
 * 运维客服看到的聊天记录
 */
/*
 * 客户看到的聊天记录
 */
//获得流程
function getWorkFlowByEntryId(entryId) {
	basePath=$("base").attr("href");
	$.ajax({
		async : false,
		type : 'post',
		url :  basePath+'/bpm/workorder_getWorkFlow.do',
		data : {
			"obj.entryId" : entryId,
		},
		dataType : 'json',
		success : function(data) {
			$(".record-id").text("工单编号:" + data.entryId);
			//$("#orderTitle").text("工单标题：" + data.orderTitle);
			if (data.stepId == 99) {
				$(".record-status").text("状态：已完成");
			}
			;
			if (data.stepId == 2) {
				$(".record-status").text("状态：待处理");
			}
			;
			try{
				$(".submitTime").text(					
						"提交时间："
						+ new Date(data.createTime.time)
								.Format("yyyy年MM月dd日 hh时mm分ss秒"));					
			}catch(err){}
		},
		error : function(data, textStatus) {
		}
	});
}

// 获得步骤list
function getStepListByEntryId(entryId) {
	basePath=$("base").attr("href");
	$.ajax({
				async : false,
				type : 'post',
				url : basePath+'/bpm/workorder_getStepListById.do',
				data : {
					"obj.entryId" : entryId
				},
				dataType : 'json',
				success : function(data) {
					// 左边用户提交的
					var txt1 = "<li><div class='bubble-main left' ><p class='bubble-content' style='color:#444;white-space:pre-wrap'>";
					var content = "";
					var txt2 = "</p><div class='arrow-left'></div></div><span class='tips left-floating'>";
					var time = "";
					var txt3 = "</span></li>";
					
					$.each(
									data,
									function(i, step) {
										if (step.stepId == 1) {
											content = "问题类型："
													+ step.objData.type
													+ '</br>' + "问题："
													+ step.objData.question
													+ '</br>' + "问题描述："
													+ step.objData.content
													//+ '</br>' + "手机："
													//+ step.objData.phone
													//+ '</br>' + "邮箱："
													//+ step.objData.email
													;
											time = step.finishDate;
											$("#before_me").before(
													txt1 + content + txt2
															+ time  + txt3 );

										}
									});
				},
				error : function(data, textStatus) {
				}
			});
}
