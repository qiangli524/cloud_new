package com.sitech.basd.sxcloud.cloud.domain.templet;

public class TempletTypeConstant {
	public static String VMWAREIMAGE = "5"; //在VM_HOST 表里VH_TYPE = 5 代表vmWareImage
	public static String WINDOWS = "1"; //在TEMPLET_TYPE表里 ID= 1 代表WINDOWS类型
	public static String UNIX = "2"; //在TEMPLET_TYPE表里 ID= 2 代表UNIX类型
	public static String NONE = "-1"; //-1代表iamge 不属于任何类型
}
