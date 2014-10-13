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
			$("#orderTitle").text("工单标题：" + data.orderTitle);
			if (data.stepId == 7) {
				$(".record-status").text("状态：待反馈");
			}
			;
			if (data.stepId == 5) {
				$(".record-status").text("状态：待分配");
			}
			;
			if (data.stepId == 0) {
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
					// 右边公司提交的
					var txt11 = "<li><div class='bubble-main fr' ><p class='bubble-content' >";
					var txt22 = "</p><div class='arrow-right'></div></div><span class='tips right'>";
					//附件
					var attachId1 = "<p id='attachId1'></p>";
					var attachId7 = "<p id='attachId7'></p>";
					
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
													+ '</br>' + "手机："
													+ step.objData.phone
													+ '</br>' + "邮箱："
													+ step.objData.email;
											time = step.finishDate;
											$("#before_me").before(
													txt1 + content + txt2
															+ time + attachId1 + txt3 );
											
											if(step.objData.attachmentId){
												var ops = {"operator":"download","ids":step.objData.attachmentId}
												$("#attachId1").attachment(ops);
											}
										}

										if (step.stepId == 5) {
											try {
												
												if(step.objData.isOk == "1"){
													content = "确认：已解决" ;
												}else{
													content = "确认：未解决" ;
												}
												content += "</br>"
														+ step.objData.question;
												time = step.finishDate;
												$("#before_me").before(
														txt1 + content + txt2
																+ time + txt3);
											} catch (err) {
											}

										}

										if (step.stepId == 7) {
											try {
												content = "反馈："
														+ step.objData.question;
												time = step.finishDate;
												$("#before_me").before(
														txt1 + content + txt2
																+ time + attachId7 + txt3 );
												
												if(step.objData.attachmentId){
													var ops = {"operator":"download","ids":step.objData.attachmentId}
													$("#attachId7").attachment(ops);
												}
											} catch (err) {}
										}

										if (step.stepId == 11) {
											try {
												
												if(step.objData.satisfaction == "1")
												{
													content = "评价：满意"													
												} else{
													content = "评价：不满意"	
													
												}
												if(step.objData.evaluateText != undefined){
													content += "</br>"
														+ step.objData.evaluateText;
												}
												time = step.finishDate;
												$("#before_me").before(txt1 + content + txt2	+ time + txt3);
											} catch (err) {}
										}
										//客服处理
										if (step.stepId == 2) {
											try {
												content = step.objData.answer;
												time = step.finishDate;
												if(content != "" && step.objData.answer != undefined){
													$("#before_me").before(
															txt11 + content + txt22	+ time + txt3);
												}
											} catch (err) {}
										}
										//运维处理
										if (step.stepId == 3) {
											try{
												content = "";
												if(step.nextStepId != 0){ //运维第一次处理
													if(step.objData == null || step.objData ==""){
														content = "运维没有提供任何信息";
													}else{
														if(step.objData.malfunctionType != undefined && step.objData.malfunctionType != null){													
															content += "故障类型：" + step.objData.malfunctionType;
														}
														if(step.objData.dateRange != undefined && step.objData.dateRange != null && step.objData.dateUnit != undefined && step.objData.dateUnit != null){													
															content += "故障类型：" + step.objData.dateRange + step.objData.dateUnit;
														}
														if(step.objData.answer != undefined && step.objData.answer != null){													
															content += "运维处理结果：" +step.objData.answer;
														}
													}
													 														
													 time = step.finishDate;
													 if(content!=""){
															$("#before_me").before(	txt11 + content + txt22	+ time + txt3);
													 }
												}
												
											}catch(err){}
											
										}
										//客服审核
										if (step.stepId == 4){
											try {
												if(step.objData.answer == undefined){
													content = "客服没有提供任何信息";
												}else{
													content = step.objData.answer;
												}
												time = step.finishDate;												
												if(content!=""){
													$("#before_me").before(	txt11 + content + txt22	+ time + txt3);
												}
											} catch (err) {}
										}
									});
				},
				error : function(data, textStatus) {
				}
			});
}
