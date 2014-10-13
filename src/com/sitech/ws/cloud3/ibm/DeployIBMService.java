package com.sitech.ws.cloud3.ibm;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sitech.basd.rest.workloads.domain.WorkloadInfo;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;

/**
 * 
 * <p>
 * Title: DeployIBMService
 * </p>
 * <p>
 * Description: 部署山西移动二期IBM虚拟化
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jan 29, 2013 8:10:27 AM
 * 
 */
@Controller
public class DeployIBMService {
	private final static Logger logger = Logger
			.getLogger(DeployIBMService.class);
	@Autowired
	private ImageService imageService;
	@Autowired
	private VirtualService virtualService;

	/**
	 * 
	 * @Title: deployImage
	 * @Description: 部署IBM虚拟机镜像
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jan 29, 2013 8:12:40 AM
	 */
	@RequestMapping(value = "/ibm/deploy/image/{params}", method = RequestMethod.GET)
	public @ResponseBody
	String deployImage(@PathVariable("params")
	String params) {
		String imageId = params;
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
		obj.setIM_ID(imageId);
		WorkloadInfo info = imageService.deployImage(obj);
		return info.getId();
	}

	@RequestMapping(value = "/ibm/start/workload/{params}", method = RequestMethod.POST)
	public @ResponseBody
	String startWorkload(@RequestBody
	String params) {
		JSONObject jo = JSONObject.fromObject(params);
		String vh_id_ibm = jo.getString("vh_id_ibm");
		String im_name = jo.getString("im_name");
		String vh_cpu = jo.getString("vh_cpu");
		String vh_mem = jo.getString("vh_mem");
		String vh_desc = jo.getString("vh_desc");
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(vh_id_ibm);
		obj.setVH_NAME(im_name);
		// 暂时写死Project——id
		obj.setPROJECT_ID("51");
		obj.setVH_CPU(vh_cpu);
		obj.setVH_MEM(vh_mem);
		obj.setVH_DESC(vh_desc);
		obj.setVH_STAT("EXECUTING");
		virtualService.updateByObj(obj);
		int result = imageService.deployVirtualStatToRunStat(obj);
		return String.valueOf(result);
	}
}
