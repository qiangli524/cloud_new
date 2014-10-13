package com.sitech.shop.webservice.service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.shop.webservice.dao.TbSysUserinfoShopDAO;
import com.sitech.shop.webservice.domain.TbSysUserinfo;
import com.sitech.shop.webservice.domain.TbSysUserinfoExample;
import com.sitech.ssd.billing.vo.user.UserVO;
import com.sitech.utils.common.CommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * 商城用户同步接口实现类 Created by Kevin on 14-5-27.
 */
@Service("publicUserSyncService")
public class UserSyncImpl implements UserSync {

	Logger logger = LoggerFactory.getLogger(UserSyncImpl.class);

	@Resource
	private TbSysUserinfoShopDAO sysUserinfoShopDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserVO processing(UserVO orderVO) throws Exception {
		logger.info("商城同步过来的用户信息为："+JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
		orderVO.setIsSuccess(true);

		try {

			// 将传过来的用户信息入库
			TbSysUserinfo obj = new TbSysUserinfo();

			// 商城用户ID
			obj.setShopUserId(orderVO.getUSER_ID());
			// 帐号
			if (CommonUtil.isNotEmpty(orderVO.getACCOUNT())) {
				obj.setAccount(orderVO.getACCOUNT());
			}
			// 邮件
			if (CommonUtil.isNotEmpty(orderVO.getEMAIL())) {
				obj.setEmail(orderVO.getEMAIL());
			}
			// 姓名
			if (CommonUtil.isNotEmpty(orderVO.getNAME())) {
				obj.setName(orderVO.getNAME());
			}else {
                obj.setName(orderVO.getACCOUNT());
            }
			// 手机
			if (CommonUtil.isNotEmpty(orderVO.getMOBILE())) {
				obj.setMobile(orderVO.getMOBILE());
			}
			// 用户级别
			if (CommonUtil.isNotEmpty(orderVO.getUSERLEVEL())) {
				obj.setUserlevel(orderVO.getUSERLEVEL());
			}
			// 用户类型
			if (CommonUtil.isNotEmpty(orderVO.getUSER_TYPE())) {
				obj.setUserType(orderVO.getUSER_TYPE());
			}
			// 用户付费类型
			if (CommonUtil.isNotEmpty(orderVO.getPAYMENT_TYPE())) {
				obj.setPaymentType(orderVO.getPAYMENT_TYPE());
			}
            //创建时间
            obj.setCreatetime(new Date());
            
            //状态
            obj.setStatus((long) 1);
            obj.setCreateuser((long) 1);

            //判断用户是否存在
            TbSysUserinfoExample example = new TbSysUserinfoExample();
            example.createCriteria().andAccountEqualTo(orderVO.getACCOUNT());
            List userList = sysUserinfoShopDao.selectByExample(example);
            if (userList.size() == 0) {
                // 创建用户
                if (orderVO.getACTION_TYPE().equals("1")) {
                    logger.debug("创建用户："+JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
                    sysUserinfoShopDao.insertSelective(obj);
                }
            }

			// 修改用户
			if (orderVO.getACTION_TYPE().equals("2")) {
				TbSysUserinfoExample ex = new TbSysUserinfoExample();
				ex.createCriteria().andAccountEqualTo(orderVO.getACCOUNT());
                logger.debug("修改用户："+JacksonUtil.formatJson(JacksonUtil.toJson(obj)));
				sysUserinfoShopDao.updateByExampleSelective(obj, ex);
			}

		} catch (Exception e) {
			orderVO.setIsSuccess(false);
			logger.error(e.getMessage(), e);
		}
		logger.info("返回给商城的同步结果："+JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
		return orderVO;
	}
}
