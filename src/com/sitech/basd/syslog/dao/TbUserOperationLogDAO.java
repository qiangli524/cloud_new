package com.sitech.basd.syslog.dao;

import com.sitech.basd.syslog.domain.TbUserOperationLog;
import com.sitech.basd.syslog.domain.TbUserOperationLogExample;
import java.util.List;

public interface TbUserOperationLogDAO {

    /**
     *条件查找记录个数
     * @param example
     * @return
     */
	int countByExample(TbUserOperationLogExample example);

    /**
     *条件删除记录
     * @param example
     * @return
     */
	int deleteByExample(TbUserOperationLogExample example);

    /**
     *主键删除记录
     * @param id
     * @return
     */
	int deleteByPrimaryKey(String id);

    /**
     *选择性插入
     * @param record
     */
	void insertSelective(TbUserOperationLog record);

    /**
     *条件查询记录
     * @param example
     * @return
     */
	List selectByExample(TbUserOperationLogExample example);

    /**
     *主键查询记录对象
     * @param id
     * @return
     */
	TbUserOperationLog selectByPrimaryKey(String id);

    /**
     *根据条件选择性更新
     * @param record
     * @param example
     * @return
     */
	int updateByExampleSelective(TbUserOperationLog record, TbUserOperationLogExample example);

    /**
     *根据主键选择性更新
     * @param record
     * @return
     */
	int updateByPrimaryKeySelective(TbUserOperationLog record);

}