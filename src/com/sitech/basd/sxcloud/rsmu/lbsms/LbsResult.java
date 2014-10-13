package com.sitech.basd.sxcloud.rsmu.lbsms;

public class LbsResult {

	/** 成功标志 */
	public static int FLAG_OK = 0;
	/** web service 网络等环境异常标志 */
	public static int FLAG_EXCEPTION = 1;
	/** web service axis调用过程中异常标志,如用户身份验证没通过等 */
	public static int FLAG_AXIS_FAULT = 2;
	/** 业务层异常, 如数据库联接,业务执行失败等 */
	public static int FLAG_BUSINESS = 3;

	/** 见以上FLAG */
	public int code;
	/** 内容或错误等详细信息 */
	public String info;

	public LbsResult() {
	}

	public LbsResult(int code, String info) {
		this.code = code;
		this.info = info;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
