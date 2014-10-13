package com.sitech.basd.resource.util;

/**
 * 
 * <p>
 * Title: ResourceStatisticsKey
 * </p>
 * <p>
 * Description: 通过key查询资源
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-22 下午7:30:10
 * 
 */
public class ResourceStatisticsKey {
	//vCPU总个数
	public static final String VCPU_ALL_COUNT = "vcpu_all_count";
	//vCPU使用量
	public static final String VCPU_USED_COUNT = "vcpu_used_count";
	//物理内存总mb
	public static final String MEM_ALL_MB = "mem_all_mb";
	//物理内存已使用mb
	public static final String MEM_USED_MB = "mem_used_mb";
	//存储有效容量(总量)
	public static final String STORAGE_VALID_MB = "storage_valid_mb";
	//存储接入容量
	public static final String STORAGE_MOUNT_MB = "storage_mount_mb";
	//存储置备容量(使用量)
	public static final String STORAGE_ALLO_MB = "storage_allo_mb";
	//存储使用容量
	public static final String STORAGE_USED_MB = "storage_used_mb";
}
