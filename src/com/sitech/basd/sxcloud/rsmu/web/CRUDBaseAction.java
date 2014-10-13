package com.sitech.basd.sxcloud.rsmu.web;

import org.apache.log4j.Logger;

public abstract class CRUDBaseAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(CRUDBaseAction.class
			.getName());
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -4256084426757638720L;
	public final static String LEADER = "leader";
	public final static String FAILURE = "failure";
	public final static String LIST = "list";
	public final static String ADD = "add";
	public final static String MODIFY = "modify";
	public final static String REDIRECT = "redirect";
	public final static String DEL = "del";
}
