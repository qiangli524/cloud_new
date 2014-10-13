package com.sitech.shop.webservice.dao;


import com.sitech.shop.webservice.domain.TbOrderStandard;
import com.sitech.shop.webservice.domain.TbOrderStandardExample;

import java.util.List;

public interface TbOrderStandardDAO {
    /**
     * 根据条件查询记录数
     *
     * @param example 条件对象
     * @return 记录数
     */
    int countByExample(TbOrderStandardExample example);

    /**
     * 根据条件删除记录
     *
     * @param example 条件对象
     * @return 删除记录数
     */
    int deleteByExample(TbOrderStandardExample example);

    /**
     * 根据主键删除记录
     *
     * @param uuid 主键
     * @return 删除记录数
     */
    int deleteByPrimaryKey(String uuid);


    /**
     * 选择性插入记录
     *
     * @param record 内容对象
     */
    void insertSelective(TbOrderStandard record);

    /**
     * 根据条件查询记录
     *
     * @param example 条件对象
     * @return 结果集
     */
    List<TbOrderStandard> selectByExample(TbOrderStandardExample example);

    /**
     * 根据主键查询对象
     *
     * @param uuid 主键
     * @return 结果对象
     */
    TbOrderStandard selectByPrimaryKey(String uuid);

    /**
     * 根据条件更新记录
     *
     * @param record  欲更新的对象
     * @param example 条件对象
     * @return 更新记录数
     */
    int updateByExampleSelective(TbOrderStandard record, TbOrderStandardExample example);

    /**
     * 根据主键更新记录
     *
     * @param record 欲更新的对象
     * @return 更新记录数
     */
    int updateByPrimaryKeySelective(TbOrderStandard record);


}