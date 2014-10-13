package com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow;

public class WorkFlowConstant {
	
	public static final String FILEKIND_APP = "APP"; //应用部署
	
	public static final String SUCCESS = "SUCCESS";  //操作成功
	
	public static final String FAILED = "FAILED";	 //操作失败
		
	public static final String YES = "YES";			//可以操作	
	
	public static final String NO = "NO";   		//不可以操作
	
	public static final int NEEDSTATUS_DRAFT = 0;   //需求状态  0 草稿   
	
	public static final int NEEDSTATUS_DOING = 1; 	//需求状态  1 正在处理
	
	public static final int NEEDSTATUS_FINSHED = 2; //需求状态  2 处理结束
	
	public static final String COMMAND_SAVE = "0";    //新增草稿箱
	
	public static final String COMMAND_PUBLISTH = "1"; //新增发布
	
	public static final String COMMAND_REPUBLISH = "2";//草稿箱修改发布
	
	public static final String COMMAND_RESAVE = "3";   //草稿箱修改保存	
	
	public static final String COMMAND_BACKPUBLISH = "4";//被打回修改发布
	
	public static final String COMMAND_BACKSAVE = "5";   //被打回修改保存	
	
	
}
